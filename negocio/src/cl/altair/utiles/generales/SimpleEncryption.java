package cl.altair.utiles.generales;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
 
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import javax.naming.InitialContext;

import servicios.generales.WSException;
import servicios.generales.WSPgrmCallException;
import org.apache.commons.codec.binary.Base64;

/**
 * @author fsandoval
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SimpleEncryption {

	private ResourceBundle beProperties = null;
	/**
	 * Constructor for SimpleEncryption.
	 */
	private String encryptKey = "";
	private String ivx ="8888888888888888";
	
	
	/**
	 * Constructor for SimpleEncryption.
	 */
	public SimpleEncryption() {
	}

	public void setEncryptKey( String encryptKey ) {
		this.encryptKey = encryptKey;
	}

	public String getEncryptKey(){
		return this.encryptKey;
	}
	/**
	 * Method setEncryptKey.
	 * Método empleado para setear la llave de encripción en la variable privada encryptKey
	 * @return void
	 */
	private void setEncryptKey() {
		try {
			InitialContext ctx = new InitialContext();
			encryptKey = ctx.lookup("java:comp/env/ENCRIPT_KEY").toString();			
		} catch ( Exception e ) {
			System.out.println( "Error al obtener la url del backoffice." + e );
		}
		
		if( encryptKey.trim().equals("") )
			encryptKey = beProperties.getString( "encryptKey" );
	}

	/**
	 * Method encrypt.
	 * Método empleado para encriptar strings mediante un algoritmo sencillo simétrico (bidireccional), 
	 * a partir de una llave especificada.
	 * @param toEnc. String a encriptar.
	 * @return String encriptado de retorno.
	 */
    public byte[] encrypt(String toEnc) {
    	setEncryptKey();
		// encrypt using the cypher
		byte[] raw = null;
		try {
			SecretKeySpec keySpec = new SecretKeySpec(encryptKey.getBytes(), "AES");
			IvParameterSpec ivSpec = new IvParameterSpec(ivx.getBytes());
			
			Cipher cipher = getCypher(keySpec, ivSpec, Cipher.ENCRYPT_MODE);
			 
			// Gets the raw bytes to encrypt, UTF8 is needed for
			// having a standard character set
			byte[] stringBytes;
 
			stringBytes = toEnc.getBytes("UTF8"); 
			raw = cipher.doFinal(stringBytes);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return raw;
    }

	/**
	 * Method decrypt.
	 * Método empleado para desencriptar strings mediante un algoritmo sencillo simétrico (bidireccional), 
	 * a partir de una llave especificada.
	 * @param toDec. String a desencriptar.
	 * @return String original retornado.
	 */
    public String decrypt(byte[] toDec ) { 
    	setEncryptKey();
		// converts the decoded message to a String
		String clear = "";
		try {
			SecretKeySpec keySpec = new SecretKeySpec(encryptKey.getBytes(), "AES");
			IvParameterSpec ivSpec = new IvParameterSpec(ivx.getBytes());
 
			Cipher cipher = getCypher(keySpec, ivSpec, Cipher.DECRYPT_MODE);
			// decode the message
			byte[] stringBytes = cipher.doFinal(toDec);
 
			clear = new String(stringBytes, "UTF8");
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return clear;        
    }
    
	/**
	 * Method encode.
	 * Método emplado para codificar strings en Base64. 
	 * Este método es útil para codificar strings que han sido encriptados y generan caracteres no permitidos en html y javascript.
	 * @param toEncode. String a codificar en Base64
	 * @return String codificado de retorno.
	 */
    public String encode ( byte[] toEncode ) {

    	if( toEncode == null )
    		System.out.println( "[SimpleEncryption][encode] Mensaje : El parámetro viene Nulo." ); 	
			String encoded = ""; 
		try {
			encoded = new String(Base64.encodeBase64(toEncode, false, true), "UTF8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encoded;
    }

	/**
	 * Method decode.
	 * Método emplado para decodificar strings en Base64. 
	 * Relacionado con el metodo anterior en su uso.
	 * @param toEncode. String a decodificar en Base64
	 * @return String original de retorno.
	 */
    public byte[] decode (String toDecode) throws WSPgrmCallException, WSException {
    	
    	if( toDecode == null )
    		throw new WSException( "[SimpleEncryption][decode] Mensaje : El parámetro viene Nulo." );
    	
		byte[] decoded;
		
		if (toDecode != null && !toDecode.equals("")) {
			try {
				decoded = Base64.decodeBase64(toDecode.getBytes("UTF8"));
				return decoded;
			} catch( Exception e ) {
				if (e instanceof servicios.generales.WSPgrmCallException)
					throw (servicios.generales.WSPgrmCallException) e;
				else {
					if (e instanceof servicios.generales.WSException)
						throw (servicios.generales.WSException) e;
					else
						throw new servicios.generales.WSException("Error en SimpleEncryption class");
				}
			}
		}
		return null;
    }

	/**
	 * Method encodePath.
	 * Método empleado para encriptar y codificar path que puedan ser desplegados en html o javascript
	 * @param path. String de path a encriptar y codificar.
	 * @return String resultado del método
	 */
	public String encodePath( String path ) {

    	if( path == null )
    		System.out.println( "[SimpleEncryption][encodePath] Mensaje : El parámetro viene Nulo." );
		StringTokenizer st = new StringTokenizer( path, "/" );
		String result = "";
		while( st.hasMoreElements() ) {
			String toEnc = (String)st.nextElement();
			result += encode( encrypt( toEnc ) ) + "|";
		}
		return result.substring( 0, result.length()-1 );
	}

	/**
	 * Method decodePath.
	 * Método empleado para decodificar y desencriptar path que puedan ser desplegados en html o javascript.
	 * @param path. String de path a decodificar y desencriptar.
	 * @return String de path original resultado del método
	 */
	public String decodePath( String path ) throws WSException, WSPgrmCallException {
		
		if( path == null )
    		throw new WSException( "[SimpleEncryption][decodePath] Mensaje : El parámetro viene Nulo." );
		
		StringTokenizer st = new StringTokenizer( path, "|" );
		String result = "";
		while( st.hasMoreElements() ) {
			String toEnc = (String)st.nextElement();
			result += decrypt( decode( toEnc ) ) + "/";
		}
		return result.substring( 0, result.length()-1 );
	}
	
	public static String daVueltaRut(String rut){
		String nuevoRut = "";
		for (int i= rut.length() -1 ; i >= 0; i--)
			nuevoRut +=rut.charAt(i);		
		return nuevoRut;
	}
	
	public static void main(String[] args) {


	}

	/**
	 * @param keySpec
	 * @param ivSpec
	 * @param mode
	 * @return
	 * @throws CryptographyException
	 */
	public static Cipher getCypher(SecretKeySpec keySpec,
			IvParameterSpec ivSpec, int mode) throws Exception {
		// Get a cipher object.
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("invalid algorithm", e);
		} catch (NoSuchPaddingException e) {
			throw new RuntimeException("invalid padding", e);
		}
		try {
			cipher.init(mode, keySpec, ivSpec);
		} catch (InvalidKeyException e) {
			throw new InvalidKeyException("invalid key", e);
		} catch (InvalidAlgorithmParameterException e) {
			throw new RuntimeException("invalid algorithm parameter.", e);
		}
		return cipher;
	}
	
}
