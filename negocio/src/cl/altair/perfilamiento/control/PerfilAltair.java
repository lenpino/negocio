package cl.altair.perfilamiento.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.xml.bind.annotation.XmlRootElement;

import cl.altair.perfilamiento.model.dao.*;
import cl.mycompany.perfilamiento.model.*;

@XmlRootElement(name = "perfilacepta")
public class PerfilAltair extends Profile {
	
	private HashMap<Rol, HashMap<Empresa, Vector<Aplicacion>>> elPerfil = null;
	private List<Empresa> empresasQueOpera = null;
	private Empresa empresaActiva;
	private Rol rolActivo;
	private Aplicacion aplicacionActiva;
	
	public List<Empresa> getEmpresasQueOpera() {
		return empresasQueOpera;
	}

	public void setEmpresasQueOpera(List<Empresa> empresasQueOpera) {
		this.empresasQueOpera = empresasQueOpera;
	}

	public Set<Empresa> getEmpresas(Rol unRol){
		HashMap<Empresa, Vector<Aplicacion>> listadoEmpresas = this.elPerfil.get(unRol);
		if(listadoEmpresas != null && !listadoEmpresas.isEmpty())
			return listadoEmpresas.keySet();
		else
			return Collections.emptySet();
	}
	
	/**
	 * Metodo para rescatar una empresa de un rol especifico solicitado. La referencia hacia la empresa
	 * se hace a traves del ID de la empresa
	 * @param unRol:	Rol para el cual se requiere saber una de sus empresas asociadas
	 * @param idEmpresa: ID de la empresa que se solicita
	 * @return: Devuelve la instancia del objeto Empresa solicitado a traves del ID. Si no encuentra la empresa solicitada
	 * se devuelve null
	 */
	public Empresa getEmpresa(Rol unRol, int idEmpresa){
		HashMap<Empresa, Vector<Aplicacion>> listadoEmpresas = this.elPerfil.get(unRol);
		Set<Empresa> lasEmpresas = listadoEmpresas.keySet();
		for(Empresa laEmpresa: lasEmpresas){
			if(laEmpresa.getId().intValue() == idEmpresa)
				return laEmpresa;
		}
		return null;
	}
	
	public Vector<Aplicacion> getAplicaciones(Rol unRol, Empresa laEmpresa){
		HashMap<Empresa, Vector<Aplicacion>> listadoEmpresas = this.elPerfil.get(unRol);
		if(listadoEmpresas != null && !listadoEmpresas.isEmpty())
			return listadoEmpresas.get(laEmpresa);
		else{
			Vector<Aplicacion> vacio = new Vector<Aplicacion>();
			return vacio;
		}
	}
	/**
	 * Metodo que devuelve una aplicacion solicitada a traves de su ID. 
	 * @param unRol: El rol del usuario
	 * @param laEmpresa: La empresa asociada al rol
	 * @param idAplicacion: ID de la aplicación solicitada
	 * @return: Devuelve la instancia del objeto Aplicacion solicitada a traves de su ID.
	 * Si no se encuentra la aplicacion se devuelve null
	 */
	public Aplicacion getAplicacion(Rol unRol, Empresa laEmpresa, int idAplicacion){
		HashMap<Empresa, Vector<Aplicacion>> listadoEmpresas = this.elPerfil.get(unRol);
		Vector<Aplicacion> lasAplicaciones = listadoEmpresas.get(laEmpresa);
		for(int i=0; i<lasAplicaciones.size();i++){
			if(lasAplicaciones.get(i).getId().intValue() == idAplicacion)
				return lasAplicaciones.get(i);
		}
		return null;
	}
	
	/**
	 * Metodo que inserta una nueva aplicacion a la relacion del Perfil
	 * @param unRol: El rol al que se agraga la aplicacion
	 * @param laEmpresa: La empresa asociada
	 * @param idAplicacion: ID de la aplicacion
	 * @throws Exception
	 */
	public void addAplicacion(Rol unRol, Empresa laEmpresa, int idAplicacion) throws Exception{
		//Busco la aplicacion en la BD
		AplicacionDAO adao = new AplicacionDAO();
		Aplicacion laAplicacion = adao.findById(new Integer(idAplicacion));
		//Obtengo la relacion entre la empresa y la aplicacion
		EmpresaApp laRelacionEmpApp = getRelEmpApp(laEmpresa, laAplicacion);
		if(laRelacionEmpApp == null)
			throw new Exception("Clase: PerfilAcepta - Metodo:addAplicacion - Msg: No existe la relacion entre empresa " + laEmpresa.getNombre() + " y la aplicacion " + laAplicacion.getNombre());
		//Obtengo la relacion entre el usuario y el rol
		UsuarioRol laRelacionUsrRol = getRelUsrRol(unRol);
		if(laRelacionUsrRol == null)
			throw new Exception("Clase: PerfilAcepta - Metodo:addAplicacion - Msg: No existe la relacion entre usuario " + this.usuario.getNombre() + " y el rol " + unRol.getNombre());			
		//Grabo la nueva relacion en la tabla PERFIL
		PerfilDAO pdao = new PerfilDAO();
		Perfil nuevaRelacion = new Perfil(0, laRelacionUsrRol, laRelacionEmpApp);
		EntityManagerHelper.beginTransaction();
		pdao.save(nuevaRelacion);
		EntityManagerHelper.commit();
		EntityManagerHelper.closeEntityManager();
		//Agrego la aplicacion en el listado de aplicaciones asociadas al USUARIO-ROL-EMPRESA
		Vector<Aplicacion> lasAplicaciones = getAplicaciones(unRol, laEmpresa);
		lasAplicaciones.add(laAplicacion);
	}

	/**
	 * Este metodo agrega una relacion emp-app al perfil generando un registro nuevo.
	 * Ademas agrega en la estructura de datos los objetos necesarios para mantener la consistencia
	 * con la BD sin necesidad de refrescar el objeto perfil
	 * @param unRol: El rol del usuario
	 * @param idEmpresa: ID de la empresa que se esta agregando
	 * @param idAplicacion: ID de la aplicacion asociada a la empresa
	 * @throws Exception
	 */
	public void addEmpresa(Rol unRol, int idEmpresa, int idAplicacion) throws Exception{
		//Busco la empresa en la BD
		EmpresaDAO edao = new EmpresaDAO();
		Empresa laEmpresa = edao.findById(Integer.valueOf(idEmpresa));
		//Busco la aplicacion en la BD
		AplicacionDAO adao = new AplicacionDAO();
		Aplicacion laAplicacion = adao.findById(Integer.valueOf(idAplicacion));
		//Obtengo la relacion entre la empresa y la aplicacion
		EmpresaApp laRelacionEmpApp = getRelEmpApp(laEmpresa, laAplicacion);
		if(laRelacionEmpApp == null)
			throw new Exception("Clase: PerfilAcepta - Metodo:addAplicacion - Msg: No existe la relacion entre empresa " + laEmpresa.getNombre() + " y la aplicacion " + laAplicacion.getNombre());
		//Obtengo la relacion entre el usuario y el rol
		UsuarioRol laRelacionUsrRol = getRelUsrRol(unRol);
		if(laRelacionUsrRol == null)
			throw new Exception("Clase: PerfilAcepta - Metodo:addAplicacion - Msg: No existe la relacion entre usuario " + this.usuario.getNombre() + " y el rol " + unRol.getNombre());			
		//Grabo la nueva relacion en la tabla PERFIL
		PerfilDAO pdao = new PerfilDAO();
		Perfil nuevaRelacion = new Perfil(0, laRelacionUsrRol, laRelacionEmpApp);
		EntityManagerHelper.beginTransaction();
		pdao.save(nuevaRelacion);
		EntityManagerHelper.commit();
		EntityManagerHelper.closeEntityManager();
		//Obtengo el listado de empresas que estan asociadas el USUARIO-ROL
		HashMap<Empresa, Vector<Aplicacion>> listadoEmpresas = this.elPerfil.get(unRol);
		//Agrego la empresa al listado
		Vector<Aplicacion> listaAplicaciones = new Vector<Aplicacion>();
		listaAplicaciones.add(laAplicacion);
		listadoEmpresas.put(laEmpresa, listaAplicaciones);
	}

	/**
	 * Este metodo agrega una relacion emp-app al perfil generando un registro nuevo.
	 * Ademas agrega en la estructura de datos los objetos necesarios para mantener la consistencia
	 * con la BD sin necesidad de refrescar el objeto perfil
	 * @param unRol: El rol del usuario
	 * @param laEmpresa: La empresa que se esta agregando
	 * @param laAplicacion: La aplicacion asociada a la empresa
	 * @throws Exception
	 */
	public void addEmpresa(Rol unRol, Empresa laEmpresa, Aplicacion laAplicacion) throws Exception{
		//Obtengo la relacion entre la empresa y la aplicacion
		EmpresaApp laRelacionEmpApp = getRelEmpApp(laEmpresa, laAplicacion);
		if(laRelacionEmpApp == null)
			throw new Exception("Clase: PerfilAcepta - Metodo:addAplicacion - Msg: No existe la relacion entre empresa " + laEmpresa.getNombre() + " y la aplicacion " + laAplicacion.getNombre());
		//Obtengo la relacion entre el usuario y el rol
		UsuarioRol laRelacionUsrRol = getRelUsrRol(unRol);
		if(laRelacionUsrRol == null)
			throw new Exception("Clase: PerfilAcepta - Metodo:addAplicacion - Msg: No existe la relacion entre usuario " + this.usuario.getNombre() + " y el rol " + unRol.getNombre());			
		//Grabo la nueva relacion en la tabla PERFIL
		PerfilDAO pdao = new PerfilDAO();
		Perfil nuevaRelacion = new Perfil(0, laRelacionUsrRol, laRelacionEmpApp);
		EntityManagerHelper.beginTransaction();
		pdao.save(nuevaRelacion);
		EntityManagerHelper.commit();
		EntityManagerHelper.closeEntityManager();
		//Obtengo el listado de empresas que estan asociadas el USUARIO-ROL
		HashMap<Empresa, Vector<Aplicacion>> listadoEmpresas = this.elPerfil.get(unRol);
		//Agrego la empresa al listado
		Vector<Aplicacion> listaAplicaciones = new Vector<Aplicacion>();
		listaAplicaciones.add(laAplicacion);
		listadoEmpresas.put(laEmpresa, listaAplicaciones);
	}
	
	
	/**
	 * Este metodo se usa para eliminar una empresa y sus aplicaciones asociadas
	 * Recibe el ROL y el ID de la empresa a eliminar y lo hace desde la tabla PERFIL
	 * y ademas en el objeto PerfilAcepta
	 * @param unRol: El rol del cual la empresa depende
	 * @param idEmpresa: ID de la empresa a eliminar
	 * 
	 */
	public void deleteEmpresa(Rol unRol, int idEmpresa){
		//TODO Falta implementar el manejo de errores
		//Busco la relacion validas para USUARIO-ROL
		UsuarioRol relUsrRol = getRelUsrRol(unRol);
		//Busco en la tabla PERFIL todas las relaciones asociadas a la relacion del USUARIO-ROL
		PerfilDAO pdao = new PerfilDAO();
		List<Perfil> losPerfiles = pdao.findByProperty("usuarioRol", relUsrRol);
		//Recorro la lista de relaciones y borro aquellas en que el ID de la empresa coincida
		for(Perfil unPerfil: losPerfiles){
			if(unPerfil.getEmpresaApp().getEmpresa().getId().intValue() == idEmpresa){
				EntityManagerHelper.beginTransaction();
				pdao.delete(unPerfil);
				EntityManagerHelper.commit();
				EntityManagerHelper.closeEntityManager();
			}
		}
		//Verifico si tiene alguna relacion adicional con la empresa
		EmpresaDAO edao = new EmpresaDAO();
		if(!edao.tieneUsuario(Integer.valueOf(idEmpresa), this.usuario.getId())){
			//Encuentro la empresa a la que se esta asociando el usuario
			Empresa laEmpresa = edao.findById(Integer.valueOf(idEmpresa));
			//Saco el numero de usuarios que la empresa posee actualmente
			int numeroUsuarios = laEmpresa.getNusuarios().intValue() - 1;
			//Sumo uno a la cantidad de usuarios
			laEmpresa.setNusuarios(Integer.valueOf(numeroUsuarios));
			//Reescribo el nuevo valor en la BD
			EntityManagerHelper.beginTransaction();
			edao.update(laEmpresa);
			EntityManagerHelper.commit();	
		}		
		//Busco en la lista de empresas del arbol, para el rol indicado, la empresa con el ID indicado
		HashMap<Empresa, Vector<Aplicacion>> listadoEmpresas = this.elPerfil.get(unRol);
		//Saco la empresa que corresponde para usarla en el remove. Evito ir a la BD
		Set<Empresa> lasEmpresas = listadoEmpresas.keySet();
		//Recorro la lista de empresas y cuando corresponde al ID entregado, saco la empresa y con ella el vector con todas las aplicaciones
		for(Empresa unaEmpresa: lasEmpresas){
			if(unaEmpresa.getId().intValue() == idEmpresa){
				listadoEmpresas.remove(unaEmpresa);
				//Si ya removimos el elemento salgo del FOR
				break;
			}
		}
	}
	/**
	 * Metodo para eliminar una aplicacion desde el arbol de perfil.
	 * Recibe como parametros el rol al que la empresa esta asociada, el ID de la empresa y el
	 * ID de la aplicacion que se va a borrar.
	 * Actualiza la base de datos borrando la referencia adecuada y elimina el objeto de la estructura
	 * de datos tambien
	 * 
	 * @param unRol: El rol del cual depende la empresa
	 * @param idEmpresa: ID de la empresa a la cual las aplicaciones pertenecen
	 * @param idAplicacion: ID de la aplicacion a eliminar
	 */
	public void deleteAplicacion(Rol unRol, int idEmpresa, int idAplicacion){
		//TODO Falta implementar el manejo de errores
		//Busco la relacion validas para USUARIO-ROL
		UsuarioRol relUsrRol = getRelUsrRol(unRol);
		//Busco en la tabla PERFIL todas las relaciones asociadas a la relacion del USUARIO-ROL
		PerfilDAO pdao = new PerfilDAO();
		List<Perfil> losPerfiles = pdao.findByProperty("usuarioRol", relUsrRol);
		//Recorro la lista de relaciones y borro aquellas en que el ID de la empresa y de la aplicacion coincidan
		for(Perfil unPerfil: losPerfiles){
			if(unPerfil.getEmpresaApp().getEmpresa().getId().intValue() == idEmpresa && unPerfil.getEmpresaApp().getAplicacion().getId().intValue() == idAplicacion){
				EntityManagerHelper.beginTransaction();
				pdao.delete(unPerfil);
				EntityManagerHelper.commit();
				EntityManagerHelper.closeEntityManager();
			}
		}
		//Busco en la lista de empresas del arbol, para el rol indicado, la empresa con el ID indicado
		HashMap<Empresa, Vector<Aplicacion>> listadoEmpresas = this.elPerfil.get(unRol);
		//Saco la empresa que corresponde para usarla en el remove. Evito ir a la BD
		Set<Empresa> lasEmpresas = listadoEmpresas.keySet();
		//Recorro la lista de empresas y cuando corresponde al ID entregado, saco la empresa y con ella el vector con todas las aplicaciones
		for(Empresa unaEmpresa: lasEmpresas){
			if(unaEmpresa.getId().intValue() == idEmpresa){
				//Saco el listado de aplicaciones para la relacion USR-ROL-EMP
				Vector<Aplicacion> lasAplicaciones = listadoEmpresas.get(unaEmpresa);
				//Recorro la lista de aplicaciones y cuando encuentro la del ID entregado se saca del vector
				for(int i=0; i<lasAplicaciones.size(); i++){
					if(lasAplicaciones.get(i).getId().intValue() == idAplicacion){
						lasAplicaciones.remove(i);
					}
				}
			}
		}
	}
	
	public boolean tieneRol(Rol unRol){
		return roles.contains(unRol);
	}
	
	public boolean tieneRol(String nombreRol){
		for(Rol rol:roles){
			if(rol.getNombre().equalsIgnoreCase(nombreRol))
				return true;
		}
		return false;
	}
	
	public boolean tieneRol(int idRol){
		for(Rol rol:roles){
			if(rol.getId().intValue() == idRol)
				return true;
		}
		return false;
	}
	public HashMap<Rol, HashMap<Empresa, Vector<Aplicacion>>> getElPerfil() {
		return elPerfil;
	}
	public void setElPerfil(HashMap<Rol,HashMap<Empresa,Vector<Aplicacion>>> raiz) {
		this.elPerfil = raiz;
	}
	
	private EmpresaApp getRelEmpApp(Empresa laEmpresa, Aplicacion laAplicacion) throws Exception{
		int idRelacion = 0;
		Set<EmpresaApp> lasRelaciones = laEmpresa.getEmpresaApps();
		for(EmpresaApp laRelacion: lasRelaciones){
			if(laRelacion.getAplicacion().getId().intValue() == laAplicacion.getId().intValue()){
				if(idRelacion == 0){
					idRelacion = laRelacion.getId();
					return laRelacion;
				}
				else
					throw new Exception("Clase: PerfilAcepta - Metodo: getRelEmpApp - ERROR: Relación EMPRESA-APLICACION repetida");
			}
		}
		return null;
	}
	
	private UsuarioRol getRelUsrRol(Rol elRol){
		List<UsuarioRol> lasRelaciones = this.usuario.getUsuarioRols();
		for(UsuarioRol laRelacion: lasRelaciones){
			if(laRelacion.getRol().getId().intValue() == elRol.getId().intValue()){
				return laRelacion;
			}
		}
		return null;
	}
	
	/**
	 * Metodo para devolver la empresa empleadora del usuario asociado a este perfil
	 * Se asume que todas las relaciones UsuarioUnidad, exhiben la misma empresa asociada
	 * a todas las unidades, por lo tanto se toma la primera relacion (indice 0), se extrae la unidad
	 * y luego se devuelve la empresa asociada.
	 * Si no posee ninguna relacion con unidades se devuelve null
	 * 
	 * @param elUsuario: El usuario al que se le consulta su empresa empleadora
	 * @return Objeto Empresa que corresponde a la empresa que emplea al usuario de este perfil.
	 * Si no tiene la asociacion, se devuelve null
	 */
	public Empresa getEmpresaEmpleadora(Usuario elUsuario){
		List<UsuarioUnidad> laRelacion = elUsuario.getUsuarioUnidads();
		if(laRelacion.isEmpty())
			return null;
		else
			return ((UsuarioUnidad)elUsuario.getUsuarioUnidads().toArray()[0]).getUnidad().getEmpresa();
	}
	
	/**
	 * Metodo que sirve para filtrar los roles segun el rol de administracion que este activo. De esta forma se controla que roles son
	 * visibles para los roles de admnistracion fuera de Acepta
	 * 
	 * @param rolActivo: Rol del usuario activo. Se asume que corresponde a un tipo de administrador
	 * @param losRoles: Listado de todos los roles disponibles en Acepta
	 * @return: Devuelve un listado de roles segun sea el tipo de administrador. El valor default corresponde al listado completo
	 */
	public List<Rol> filtraRoles(Rol rolActivo, List<Rol> losRoles){
		List<Rol> listado = new ArrayList<Rol>();
		if(rolActivo.getNombre().equalsIgnoreCase("Administrador Acepta")){
			//Se devuelven todos los roles
			return losRoles;			
		} else if(rolActivo.getNombre().equalsIgnoreCase("Administrador Empresa")){
			for(Rol unRol: losRoles){
				if(unRol.getNombre().equalsIgnoreCase("Consulta") || 
						//Verifica que la empresa activa tenga la aplicacion de custodia de firmas para mostrarle el rol firmante
						(unRol.getNombre().equalsIgnoreCase("Firmante") && EmpresaDAO.tieneAplicacion(getEmpresaActiva().getId(), "Custodia Firmas")))
					listado.add(unRol);
			}
			return listado;		
			//En el caso de los administradores DEC saco todos los roles internos de Acepta
		} else if(rolActivo.getNombre().equalsIgnoreCase("Administrador DEC")){
			for(Rol unRol: losRoles){
				if(!unRol.getNombre().startsWith("Administrador")
						&& !unRol.getNombre().equalsIgnoreCase("Consulta")
						&& !unRol.getNombre().equalsIgnoreCase("Soporte")
						&& !unRol.getNombre().equalsIgnoreCase("Usuario Empresas"))
					listado.add(unRol);
			}
			return listado;					
		} else
			return losRoles;
	}
	
	/**
	 * Metodo que sirve verificar si un usuario tiene o no asociada una empresa para operar 
	 * 
	 * @param int: ID de la empresa
	 * @return: Devuelve verdadero si el usuario posee la empresa dentro de su lista, falso si no la contiene
	 */
	public boolean operaConEmpresa(int idEmpresa){
		EmpresaDAO edao = new EmpresaDAO();
		//Encuentro la empresa a la que se esta asociando el usuario
		Empresa laEmpresa = edao.findById(Integer.valueOf(idEmpresa));
		return getEmpresasQueOpera().contains(laEmpresa);
	}

	public Empresa getEmpresaActiva() {
		return empresaActiva;
	}

	public void setEmpresaActiva(Empresa empresaActiva) {
		this.empresaActiva = empresaActiva;
	}

	public Rol getRolActivo() {
		return rolActivo;
	}

	public void setRolActivo(Rol rolActivo) {
		this.rolActivo = rolActivo;
	}

	public Aplicacion getAplicacionActiva() {
		return aplicacionActiva;
	}

	public void setAplicacionActiva(Aplicacion aplicacionActiva) {
		this.aplicacionActiva = aplicacionActiva;
	}
	
	/**
	 * Metodo para determinar si un usuario posee o no una aplicaciÃ³n 
	 * para el rol y la empresa que se encuentran activas
	 * @param rolActivo: El rol activo del cual depende la empresa
	 * @param empresaActiva: Empresa activa a la cual las aplicaciones pertenecen
	 * @param nombreApp: Nombre de la aplicaciÃ³n a determinar
	 * @return: Devuelve verdadero si la aplicacion existe para el rol y empresa activos, falso en caso contrario
	 */
	public boolean tieneAplicacion(Rol rolActivo, Empresa empresaActiva,  String nombreApp){
		HashMap<Empresa, Vector<Aplicacion>> listadoEmpresas = this.elPerfil.get(rolActivo);
		Vector<Aplicacion> lasAplicaciones = listadoEmpresas.get(empresaActiva);
		for(int i=0; i<lasAplicaciones.size();i++){
			if(lasAplicaciones.get(i).getNombre().equalsIgnoreCase(nombreApp) )
				return true;
		}
		return false;
	}

}
