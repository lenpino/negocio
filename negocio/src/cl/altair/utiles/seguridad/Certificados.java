package cl.altair.utiles.seguridad;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertPath;
import java.security.cert.CertPathValidator;
import java.security.cert.CertPathValidatorException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateParsingException;
import java.security.cert.PKIXCertPathValidatorResult;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.InvalidNameException;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;
import javax.security.auth.x500.X500Principal;

import org.apache.commons.codec.binary.Base64;

import cl.altair.utiles.generales.Utils;


public class Certificados {

	private static final String dummyCertificateData =
		"MIIGCjCCBPKgAwIBAgIDARgsMA0GCSqGSIb3DQEBBQUAMIHGMQswCQYDVQQGEwJDTDEYMBYGA1UE"+
		"ChMPQWNlcHRhLmNvbSBTLkEuMTgwNgYDVQQLEy9BdXRvcmlkYWQgY2VydGlmaWNhZG9yYSBDbGFz" +
		"ZSAzIHBlcnNvbmEgbmF0dXJhbDFDMEEGA1UEAxM6QWNlcHRhLmNvbSBBdXRvcmlkYWQgY2VydGlm" +
		"aWNhZG9yYSBDbGFzZSAzIHBlcnNvbmEgbmF0dXJhbDEeMBwGCSqGSIb3DQEJARYPaW5mb0BhY2Vw" + 
		"dGEuY29tMB4XDTA2MDExNzE0MzMzM1oXDTA5MDExNzE0MzMzM1owgb4xCzAJBgNVBAYTAkNMMRgw" +
		"FgYDVQQKEw9BY2VwdGEuY29tIFMuQS4xLDAqBgNVBAsTI0NlcnRpZmljYWRvIENsYXNlIDMgUGVy" + 
		"c29uYSBOYXR1cmFsMSYwJAYJKoZIhvcNAQkBFhdhbmRyZXMudGFwaWFAYWNlcHRhLmNvbTEYMBYG" +
		"A1UEDBMPUEVSU09OQSBOQVRVUkFMMSUwIwYDVQQDExxBTkRSRVMgIFJBSU1VTkRPIFRBUElBIEpB" +
		"UlBBMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDCtvDgCJh6dsd3LpjIT/CotUxFrK8F7iY9" +
		"hEjRWNxnF82v5HQBN1xA+aXoj8NWuk32rwM2l4jTBMZEXqpbJ435TiRSUl3O6D5Rhd7rTXc1r6O2" +
		"wK9wWaWVK7Eyb9a5JunjHUMHKk8RE7E43KgvpvGZEzeZ6Fh+bSgYHGG6s0aBGQIDAQABo4ICiTCC" +
		"AoUwHQYIKwYBBAG1aw8EERYPQWNlcHRhLmNvbSBTLkEuMDwGA1UdEQQ1MDOgGAYIKwYBBAHBAQGg" +
		"DBYKMTU2NjQzMjMtOIEXYW5kcmVzLnRhcGlhQGFjZXB0YS5jb20wDwYIKwYBBAG1awkEAxYBIDAJ" +
		"BgNVHRMEAjAAMBEGCWCGSAGG+EIBAQQEAwIFoDALBgNVHQ8EBAMCBPAwHQYDVR0lBBYwFAYIKwYB" +
		"BQUHAwIGCCsGAQUFBwMEMB0GA1UdDgQWBBQh1OLUK+qzNmN2sFmbyz49cLfXgzAfBgNVHSMEGDAW" +
		"gBRcXMeaKik9AjAHiEPY+oVdUmxRFTAlBgNVHRIEHjAcoBoGCCsGAQQBwQECoA4WDDk2LjkxOS4w" +
		"NTAtODCB9AYDVR0gBIHsMIHpMIHmBggrBgEEAbVrAjCB2TArBggrBgEFBQcCARYfaHR0cDovL3d3" +
		"dy5hY2VwdGEuY29tL0NQUy92MS4wLzCBqQYIKwYBBQUHAgIwgZwwFhYPQWNlcHRhLmNvbSBTLkEu" +
		"MAMCAQEagYFFbCB0aXR1bGFyIGhhIHNpZG8gdmFsaWRhZG8gZW4gZm9ybWEgcHJlc2VuY2lhbCwg" +
		"cXVlZGFuZG8gaGFiaWxpdGFkbyBlbCBDZXJ0aWZpY2FkbyBwYXJhIHVzbyB0cmlidXRhcmlvLCBw" + 
		"YWdvcywgY29tZXJjaW8geSBvdHJvcy4wMwYIKwYBBQUHAQEEJzAlMCMGCCsGAQUFBzABhhdodHRw" +
		"Oi8vb2NzcC5hY2VwdGEuY29tLzA4BgNVHR8EMTAvMC2gK6AphidodHRwOi8vY3JsLmFjZXB0YS5j" +
		"b20vQ2xhc2UzUGVyc29uYS5jcmwwDQYJKoZIhvcNAQEFBQADggEBAGndJHILbbMDsaL80GEwB4Rb" +
		"xf8dE8b8FN4QR8mEcPFgKc65Qb5zUR71aulQBK0vKTRBCsm726VTPKfbKX9ZY0A2GV3GVkI8vcMO" +
		"lhq1k83a0OOzuups2akDahIZBb1v1F5gQ+cFIT+z4Jc9yeW6nk7ZdgZWN79l7Vz6B5r1pc7uu9Ot" +
		"CkWMHWtuUlK4WVUpQvI0ZhhLcA9FcRaJIP97W3W5B27zHqzuhXv3bA5HEPuWZB+UwmyI3AnTW9vV" +
		"NCrJot3TtVjQLzBM81HvToPFY7OZgXCjz3bhTc/u/knX3SyQNlFy0fZCHZYniTIOGPj3jgTxYUlo" +
		"hnU3TwO2okXdHiE=";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//		Certificados yo = new Certificados();
		X509Certificate dummyCertificate;
		try {
			dummyCertificate = Certificados.parseCertFromBase64String(dummyCertificateData);
			System.out.println(Certificados.getRut(dummyCertificate));
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// Este metodo lee un certificado desde un archivo. El certificado puede estar ya sea
	// en formato binario o codificado en base64.
	public static java.security.cert.Certificate importCertificate(File file) {
		try {
			FileInputStream is = new FileInputStream(file);

			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			java.security.cert.Certificate cert = cf.generateCertificate(is);
			return cert;
		} catch (CertificateException e) {
		} catch (IOException e) {
		}
		return null;
	}

	//Este metodo escribe un certificado a un archivo. Si binary es falso, 
	// el certificado se codifica en base64
	public static void export(java.security.cert.Certificate cert, File file, boolean binary) {
		try {
			// Obtiene la codificacion
			byte[] buf = cert.getEncoded();

			FileOutputStream os = new FileOutputStream(file);
			if (binary) {
				// Escribe la forma binaria
				os.write(buf);
			} else {
				// Lo escribe como texto
				Writer wr = new OutputStreamWriter(os, Charset.forName("UTF-8"));
				wr.write("-----BEGIN CERTIFICATE-----\n");
				wr.write(new String(Base64.encodeBase64(buf)));
				wr.write("\n-----END CERTIFICATE-----\n");
				wr.flush();
			}
			os.close();
		} catch (CertificateEncodingException e) {
		} catch (IOException e) {
		}
	}

	/**
	 * Este metodo permite extraer el rut desde un certificado. Se asume correctitud y apego a la norma vigente
	 * RUT del titular del certificado : 1.3.6.1.4.1.8321.1 (Reglamento Ley 19799)
	 * 
	 * @param certificado: Objeto JAVA representando un certificado X509
	 * @return:	El rut del usuario en formato RRRRRRRRD
	 * 
	 */
/*	public static String getRut(X509Certificate certificado){
		byte[] otherName = null;
		byte[] names = certificado.getExtensionValue("2.5.29.17");//obtiene el other name
		otherName = names;
		String PIoid = OIDtoHex("1.3.6.1.4.1.8321.1");
		String strNames=new String(names);
		if (strNames.indexOf(PIoid)>0){
			strNames = strNames.substring(strNames.indexOf(PIoid) + PIoid.length());
			names = strNames.getBytes();
		}
		else
			names = null;
		otherName = names;
		String rut = otherName == null?null:formateaRUT(new String(otherName));
		return rut;
	}*/

	public static String getRut(X509Certificate certificate) throws CertificateParsingException {
		for (List<?> generalName : certificate.getSubjectAlternativeNames()) {
			Integer nameType = (Integer) generalName.get(0);
			if (nameType.intValue() == 0) {
				byte[] otherName = (byte[]) generalName.get(1);
				if (otherName.length >= 14) {
					// OtherName type-id is 1.3.6.1.4.1.8321.1?
					if (otherName[ 0] == (byte) 0x30 && otherName[ 2] == (byte) 0x06 &&
							otherName[ 3] == (byte) 0x08 && otherName[ 4] == (byte) 0x2b &&
							otherName[ 5] == (byte) 0x06 && otherName[ 6] == (byte) 0x01 &&
							otherName[ 7] == (byte) 0x04 && otherName[ 8] == (byte) 0x01 &&
							otherName[ 9] == (byte) 0xc1 && otherName[10] == (byte) 0x01 &&
							otherName[11] == (byte) 0x01 && otherName[12] == (byte) 0xa0) {

						int offset = 14;

						while (offset < otherName.length && otherName[offset] != 0x16) {
							offset++;
						}
						offset += 2;
						String s = new String(Arrays.copyOfRange(otherName, offset, otherName.length));
						return (!s.matches("[0-9]+-[0-9Kk]")) ? null : s;
					}
				}
			}
		}
		return null;
	}

	public static HashMap<String, String> parseName(X500Principal name) throws InvalidNameException, UnsupportedEncodingException {
		HashMap<String, String> result = new HashMap<String, String>();
		LdapName ldapName = new LdapName(name.getName(X500Principal.RFC1779));
		List<Rdn> items = ldapName.getRdns();
		for (Rdn rdn : items) {
			String nameType = rdn.getType();
			Object value = rdn.getValue();
			if (value instanceof String) {
				result.put(nameType, (String) value);
			}
			if (value instanceof byte[]) {
				result.put(nameType, new String((byte[])value, "ASCII"));
			}
		}
		return result;
	}

	public static X509Certificate parseCertFromBase64String(String base64Cert) throws CertificateException, IOException {
		Certificate cert = parseCertFromDERStream(new ByteArrayInputStream(Base64.decodeBase64(
				Utils.getStreamContent(new ByteArrayInputStream(base64Cert.getBytes("ASCII"))))));
		return (X509Certificate) cert;
	}

	public static X509Certificate parseCertFromBase64Stream(InputStream stream) throws CertificateException, IOException {
		Certificate cert = parseCertFromDERStream(new ByteArrayInputStream(Base64.decodeBase64(
				Utils.getStreamContent(stream))));
		return (X509Certificate) cert;
	}

	public static X509Certificate parseCertFromDERStream(InputStream stream) throws CertificateException, IOException {
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		Certificate cert = cf.generateCertificate(stream);
		return (X509Certificate) cert;
	}

	public static X509Certificate parseCertFromDERBytes(byte[] signerCert) throws CertificateException, IOException {
		return parseCertFromDERStream(new ByteArrayInputStream(signerCert));
	}

	@SuppressWarnings("unchecked")
	public static PKIXParameters createTrustoreParams(InputStream pkcs7FileStream) 
	throws CertificateException, InvalidAlgorithmParameterException {

		CertificateFactory certificate = CertificateFactory.getInstance("X.509");

		Collection<X509Certificate> certs = (Collection<X509Certificate>) certificate.generateCertificates(pkcs7FileStream);
		Set<TrustAnchor> anchorCollections = new HashSet<TrustAnchor>(); 

		for(X509Certificate cert : certs) {
			anchorCollections.add(new TrustAnchor(cert, null));
		}
		return new PKIXParameters(anchorCollections);
	}

	/**
	 * Realiza la validacion de una cadena de certificados.
	 * 
	 * @param target 			Certificado a validar
	 * @param validateParams 	Objeto de tipo PKIXParameters con la lista de certificados 
	 * 		  					raices e intermedios para validar la cadena. 
	 * @return 					Objeto PKIXCertPathValidatorResult en caso de validacion exitosa. Null en caso contrario. 
	 * @throws CertificateException		 Si <code>target</code> no pasa la validacion de la cadena de certificados
	 * @throws NoSuchAlgorithmException
	 * @throws CertPathValidatorException
	 * @author Jjeneral
	 */
	public static PKIXCertPathValidatorResult validateCertificateChain(X509Certificate target, 
			PKIXParameters validateParams) 
	throws CertificateException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, CertPathValidatorException {

		CertificateFactory certificate = CertificateFactory.getInstance("X.509");
		CertPath certPath = certificate.generateCertPath(Arrays.asList(target));

		CertPathValidator pathValidator = CertPathValidator.getInstance(CertPathValidator.getDefaultType());

		PKIXCertPathValidatorResult result = (PKIXCertPathValidatorResult) pathValidator.validate(certPath, validateParams);
		return result;
	}

	/**
	 * Transforma un OID a un string hexadecimal
	 * @param OID OID
	 * @return String
	 */
	private static String OIDtoHex(String OID)
	{
		int oidArray[] = new int[1024]; //{ 1, 3, 6, 1, 4, 1, 8321, 1 };
		int i=0;
		while(OID.indexOf(".")!= -1)
		{
			oidArray[i] = Integer.parseInt(OID.substring(0,OID.indexOf(".")));
			OID = OID.substring(OID.indexOf(".")+1, OID.length());
			i++;
		}
		oidArray[i] = Integer.parseInt(OID); // el ultimo

		char[] buffer = new char[1024];
		int len;

		len = OIDascii2asn1(oidArray, i+1, buffer, buffer.length);
		String resultado="";
		for(int j=0;j<len;j++)
		{
			resultado = resultado + buffer[j];
		}
		return(resultado);
	}	
	/**
	 * @todo revisar que hace esto?????
	 * @param values
	 * @param length
	 * @param buffer
	 * @param buflen
	 * @return
	 */
	private static int OIDascii2asn1(int[] values, int length, char[] buffer, int buflen)
	{
		int offset = 0;
		int value;
		for(int index = 1; index < length; index++)
		{
			if(index == 1)
				value =  40 * values[0] + values[1];
			else
				value= values[index];
			int count = 1;
			while ((value >> (7 * count)) != 0)
				count++;
			offset =  offset + count;
		}
		if(offset > buflen)
			return -1;
		offset = 0;
		for (int index = 1; index < length; index++)
		{
			if(index == 1)
				value = 40 * values[0] + values[1];
			else
				value = values[index];
			int count = 1;
			while ((value >> (7 * count)) != 0)
				count++;
			for(int n = 1; n <= count; n++)
				if(n != count)
					buffer[offset++] = (char)( 0x80+(value >> (7 * (count - n)) & 0x7f));
				else
					buffer[offset++]= (char)(value >> (7 * (count - n)) & 0x7f);
		}
		return offset;
	}
	private static String formateaRUT(String rut){
		/** sacar los "." y el "-" **/
		int largo=(int)rut.charAt(1);
		int i=0;
		rut=rut.substring(3,3+largo-1); //parte desde el segundo byte
		while(i<rut.length())
		{
			if((rut.substring(i,i+1).compareTo("0")<0 || rut.substring(i,i+1).compareTo("9")>0) &&
					(rut.substring(i,i+1).compareTo("K")!=0 && rut.substring(i,i+1).compareTo("k")!=0))
			{
				rut = rut.substring(0,i) + rut.substring(i+1);
			}
			else
				i++;
		}
		//Saca los ceros a la izquierda
		while(rut.charAt(0)=='0' && rut.length()>0)
			rut=rut.substring(1);
		return rut;
	}
}
