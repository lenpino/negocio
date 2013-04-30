package cl.altair.perfilamiento.control;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import cl.altair.modelo.portal.Programa;
import cl.altair.modelo.portal.Registro;
import cl.altair.modelo.portal.RegistroDAO;
import cl.altair.perfilamiento.model.dao.*;
import cl.altair.utiles.seguridad.Certificados;
import cl.altair.utiles.ws.generales.MsgError;
import cl.altair.utiles.ws.perfilamiento.PerfilWrapper;
import cl.altair.utiles.ws.perfilamiento.RegClienteWrapper;
import cl.mycompany.perfilamiento.model.*;

import com.sun.jersey.spi.resource.Singleton;
@Singleton
@Produces("application/xml")
@Path("/usuario")
public class Perfilador {
	
	/**
	 * @param args
	 */
	protected PerfilAltair perfil = new PerfilAltair();
	private final static Logger LOGGER = Logger.getLogger(Perfilador.class.getName());
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("negocio");
	
	public static void main(String[] args) {
		Perfilador yo = new Perfilador();
		Usuario elUsuario = yo.getUsuario("102734262");
		PerfilAltair elPerfil = yo.getPerfil(elUsuario);
		if(elPerfil.tieneRol(75))
			System.out.println("Funca");
		else
			System.out.println("No Funca"); 
		

	}
	
	public boolean esUsuario(String login, String password){
		UsuarioDAO userDAO = new UsuarioDAO();
		try {
			Usuario elUsuario = (Usuario) userDAO.findByLogin(login);
			if(!elUsuario.getClave().equalsIgnoreCase(hashPassword(password)))
				return false;
			return true;
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean esUsuario(X509Certificate certificado){
		String rut="";
		try {
			rut = Certificados.getRut(certificado);
		} catch (CertificateParsingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return esUsuario(rut);
	}
	@SuppressWarnings("unchecked")
	public boolean esUsuario(String login){
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("select x from Usuario x where x.login='"+ login + "'");
			List<Usuario> results = (List<Usuario>) query.getResultList();
			//No existe el usuario con ese login en la BD
			if(results.size() == 0)
				return false;
			return true;
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public Usuario getUsuario(String login, String password){
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("select x from Usuario x where x.login='"+ login + "'");
			List<Usuario> results = (List<Usuario>) query.getResultList();
			//No existe el usuario con ese login en la BD
			if(results.size() == 0)
				return null;
			Usuario elUsuario = results.get(0);
			if(!elUsuario.getClave().equalsIgnoreCase(hashPassword(password)))
				return null;
			return elUsuario;
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Usuario getUsuario(String login){
		UsuarioDAO userDAO = new UsuarioDAO();
		try {
			List<Usuario> elUsuario = userDAO.findByLogin(login);
			//No existe el usuario con ese login en la BD
			if(elUsuario == null || elUsuario.isEmpty())
				return null;
			return elUsuario.get(0);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * @param elUsuario
	 * 	Un objeto JPA con la definicion del usuario y los datos de este segun su "login"
	 * @return Perfil
	 * 	Objeto que posee todos los datos necesarios para el perfilamiento del usuario
	 */
	@SuppressWarnings({ "unused" })
	public PerfilAltair getPerfil(Usuario elUsuario){
		LOGGER.info("Obteniendo la informacion desde el perfil del usuario");
		UsuarioDAO userDAO = new UsuarioDAO();
		perfil.setUsuario(elUsuario);
		try {
			//Conjunto de relaciones existentes entre el usuario y el rol. Esto es el listado de roles asociados al usuario
			List<UsuarioRol> relacion = elUsuario.getUsuarioRols();
			//Inicializaciòn de las estructuras necesarias para contener los datos. Se trata de un arbol donde la raiz representa el usuario, luego
			// sus roles, seguido de las empresas x rol seguido de las aplicaciones x empresa
			//Esta estructura corresponde a la definicion funcional del perfil segun acepta y debe ser capaz de ser flexible
			HashMap<Rol, HashMap<Empresa, Vector<Aplicacion>>> raiz = new HashMap<Rol, HashMap<Empresa, Vector<Aplicacion>>>();
			HashMap<Empresa, Vector<Aplicacion>> hijo = new HashMap<Empresa, Vector<Aplicacion>> ();
			Vector<Aplicacion> nieto = null;
			Empresa enterprise = null;
			//Cicla por cada rol (en la relacion usuario_rol) para extraer cada uno de los roles
			for(UsuarioRol roles:relacion){
				List<Perfil> perfiles = roles.getPerfils();
				//Cicla dentro de cada rol buscando en la relaciones de la tabla perfil las empresas y sus aplicaciones. 
				//Estan en el mismo nivel por lo tanto es posible invertir la relacion empresa_aplicacion
				for(Perfil perfil:perfiles){
					if(hijo.isEmpty() || !hijo.containsKey(perfil.getEmpresaApp().getEmpresa())){
						hijo.put(perfil.getEmpresaApp().getEmpresa(), new Vector<Aplicacion>());
					}
					hijo.get(perfil.getEmpresaApp().getEmpresa()).add(perfil.getEmpresaApp().getAplicacion());
				}
				raiz.put(roles.getRol(), hijo);
				hijo = new HashMap<Empresa, Vector<Aplicacion>> ();
			}
			//Obtiene la lista de roles que el usuario tiene
			List<Rol> roles = new Vector<Rol>(raiz.keySet());
			perfil.setRoles(roles);
			
			//Obtiene la empresa a la que pertenece a traves de sus unidades
			List<Empresa> empresas = new Vector<Empresa>();
			Unidad unidad = new Unidad();
			List<UsuarioUnidad> rel = elUsuario.getUsuarioUnidads();
			for(UsuarioUnidad unidades:rel){
				empresas.add(unidades.getUnidad().getEmpresa());
			}
			perfil.setEmpresas(empresas);
			perfil.setEmpresasQueOpera(userDAO.findEnterprise(elUsuario.getDni()));
			perfil.setElPerfil(raiz);
			return perfil;
			
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	/**
	 * @param elUsuario
	 * 	Un objeto JPA con la definicion del usuario y los datos de este segun su "login"
	 * @return Perfil
	 * 	Objeto que posee todos los datos necesarios para el perfilamiento del usuario
	 */
	@SuppressWarnings({ "unused" })
	@GET
	@Path("{rut}")
	public Response getPerfil(@PathParam("rut") String login){
		EntityManager em = emf.createEntityManager();
		try {
			Usuario elUsuario = getUsuario(login);
			perfil.setUsuario(elUsuario);
			//Conjunto de relaciones existentes entre el usuario y el rol. Esto es el listado de roles asociados al usuario
			List<UsuarioRol> relacion = elUsuario.getUsuarioRols();
			//Inicializaciòn de las estructuras necesarias para contener los datos. Se trata de un arbol donde la raiz representa el usuario, luego
			// sus roles, seguido de las empresas x rol seguido de las aplicaciones x empresa
			//Esta estructura corresponde a la definicion funcional del perfil segun acepta y debe ser capaz de ser flexible
			HashMap<UsuarioRol, HashMap<Empresa, Vector<Aplicacion>>> raiz = new HashMap<UsuarioRol, HashMap<Empresa, Vector<Aplicacion>>>();
			HashMap<Empresa, Vector<Aplicacion>> hijo = new HashMap<Empresa, Vector<Aplicacion>> ();
			Empresa enterprise = null;
			//Cicla por cada rol (en la relacion usuario_rol) para extraer cada uno de los roles
			for(UsuarioRol roles:relacion){
				List<Perfil> perfiles = roles.getPerfils();
				//Cicla dentro de cada rol buscando en la relaciones de la tabla perfil las empresas y sus aplicaciones. 
				//Estan en el mismo nivel por lo tanto es posible invertir la relacion empresa_aplicacion
				for(Perfil perfil:perfiles){
					if(hijo.isEmpty() || !hijo.containsKey(perfil.getEmpresaApp().getEmpresa())){
						hijo.put(perfil.getEmpresaApp().getEmpresa(), new Vector<Aplicacion>());
					}
					hijo.get(perfil.getEmpresaApp().getEmpresa()).add(perfil.getEmpresaApp().getAplicacion());
				}
				raiz.put(roles, hijo);
				hijo = new HashMap<Empresa, Vector<Aplicacion>> ();
			}
			//Obtiene la lista de roles que el usuario tiene

			PerfilWrapper wrapper = new PerfilWrapper(raiz, login);
			final GenericEntity <PerfilWrapper> entity = new GenericEntity<PerfilWrapper>(wrapper) { };
			return Response.ok().entity(entity).build();
			
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		return null;		
	}

	/**
	 * @param email: El email del usuario
	 * 	Un objeto JPA con la definicion del usuario y los datos de este segun su "login"
	 * @return Response
	 * 	XML con los datos de clave y numero de auditoria para identificar en forma unica al cliente
	 */
	@SuppressWarnings({ "unused" })
	@GET
	@Path("/cliente/{email}")
	public Response getIDCliente(@PathParam("email") String login){
		EntityManager em = emf.createEntityManager();
		RegistroDAO rdao = new RegistroDAO();
		try{	
			List<Registro> listaRegistros = rdao.findByProperty("email", login);
			if(listaRegistros.isEmpty()){
				MsgError msgError = new MsgError();
				msgError.setMensaje("NO EXISTE EL CORREO ELECTRONICO");
				final GenericEntity <MsgError> entity = new GenericEntity<MsgError>(msgError) { };
				return Response.ok().entity(entity).build();
			}
			else{
				ProgramaDAO pdao = new ProgramaDAO();
				SecureRandom random = new SecureRandom();
				java.util.Date utilDate = new java.util.Date();
				long lnMilisegundos = utilDate.getTime();
				java.sql.Timestamp fechaActual = new java.sql.Timestamp(lnMilisegundos);
				Long serial = Math.abs(new BigInteger(130, random).longValue());
				Registro elRegistro = listaRegistros.get(0);
				EmpresaDAO edao = new EmpresaDAO();
				LOGGER.fine("Empresa RUT " + elRegistro.getRutEmpresa());
				Empresa laEmpresa = edao.findByRut(elRegistro.getRutEmpresa()).get(0);
				LOGGER.fine("Empresa Nombre " + laEmpresa.getNombre());
				Programa elPrograma = new Programa();
				elPrograma.setSerial(serial);
				elPrograma.setVersion("0.2.1");
				elPrograma.setActivacion(new Date(fechaActual.getTime()));				
				elPrograma.setEmpresa(laEmpresa);
				elPrograma.setEstado("activado");
				LOGGER.info("Grabando el programa en la BD");
				EntityManagerHelper.beginTransaction();
				pdao.save(elPrograma);
				EntityManagerHelper.commit(); 
				EntityManagerHelper.closeEntityManager(); 
				RegClienteWrapper reg = new RegClienteWrapper();
				reg.setPrograma(elPrograma);
				reg.setRegistro(elRegistro);
				LOGGER.info("Enviando respuesta al cliente");
				final GenericEntity <RegClienteWrapper> entity = new GenericEntity<RegClienteWrapper>(reg) { };
				return Response.ok().entity(entity).build();		
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String hashPassword(String password){
		java.security.MessageDigest m;
		try {
			m = java.security.MessageDigest.getInstance("MD5");
			m.update(password.getBytes("UTF8"));
			byte s[] = m.digest();
			String result = "";
			for (int i = 0; i < s.length; i++) {
			  result += Integer.toHexString((0x000000ff & s[i]) | 0xffffff00).substring(6);
			}
			return result;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
