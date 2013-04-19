package cl.altair.perfilamiento.model.dao;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import cl.mycompany.perfilamiento.model.Aplicacion;
import cl.mycompany.perfilamiento.model.Empresa;
import cl.mycompany.perfilamiento.model.Usuario;


/**
 	* A data access object (DAO) providing persistence and search support for Empresa entities.
 	 		* Transaction control of the save(), update() and delete() operations must be handled externally by senders of these methods 
 		  or must be manually added to each of these methods for data to be persisted to the JPA datastore.	
 	 * @see com.acepta.perfilamiento.eclipselink.Empresa
  * @author MyEclipse Persistence Tools 
 */

public class EmpresaDAO  {
	//property constants
	public static final String NOMBRE = "nombre";
	public static final String RUT = "rut";
	public static final String DV = "dv";
	public static final String TELEFONO = "telefono";
	public static final String ESTADO = "estado";
	public static final String NUSUARIOS = "nusuarios";




	private static EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}	
	
		/**
	 Perform an initial save of a previously unsaved Empresa entity. 
	 All subsequent persist actions of this entity should use the #update() method.
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist} operation.
	 	 
	 * <pre> 
	 *   EntityManagerHelper.beginTransaction();
	 *   EmpresaDAO.save(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity Empresa entity to persist
	  @throws RuntimeException when the operation fails
	 */
    public void save(Empresa entity) {
    		EntityManagerHelper.log("saving Empresa instance", Level.INFO, null);
	        try {
	        	getEntityManager().persist(entity);
            	EntityManagerHelper.log("save successful", Level.INFO, null);
	        } catch (RuntimeException re) {
	        	EntityManagerHelper.closeEntityManager();
        		EntityManagerHelper.log("save failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    /**
	 Delete a persistent Empresa entity.
	  This operation must be performed 
	 within the a database transaction context for the entity's data to be
	 permanently deleted from the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete} operation.
	 	  
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   EmpresaDAO.delete(entity);
	 *   EntityManagerHelper.commit();
	 *   entity = null;
	 * </pre>
	   @param entity Empresa entity to delete
	 @throws RuntimeException when the operation fails
	 */
    public void delete(Empresa entity) {
    				EntityManagerHelper.log("deleting Empresa instance", Level.INFO, null);
	        try {
        	entity = getEntityManager().getReference(Empresa.class, entity.getId());
            getEntityManager().remove(entity);
            			EntityManagerHelper.log("delete successful", Level.INFO, null);
	        } catch (RuntimeException re) {
	        	EntityManagerHelper.closeEntityManager();
        		EntityManagerHelper.log("delete failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    /**
	 Persist a previously saved Empresa entity and return it or a copy of it to the sender. 
	 A copy of the Empresa entity parameter is returned when the JPA persistence mechanism has not previously been tracking the updated entity. 
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence
	 store, i.e., database. This method uses the {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge} operation.
	 	 
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   entity = EmpresaDAO.update(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity Empresa entity to update
	 @return Empresa the persisted Empresa entity instance, may not be the same
	 @throws RuntimeException if the operation fails
	 */
    public Empresa update(Empresa entity) {
    				EntityManagerHelper.log("updating Empresa instance", Level.INFO, null);
	        try {
            Empresa result = getEntityManager().merge(entity);
            			EntityManagerHelper.log("update successful", Level.INFO, null);
	            return result;
        } catch (RuntimeException re) {
        	EntityManagerHelper.closeEntityManager();
        				EntityManagerHelper.log("update failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    public Empresa findById( Integer id) {
    				EntityManagerHelper.log("finding Empresa instance with id: " + id, Level.INFO, null);
	        try {
            Empresa instance = getEntityManager().find(Empresa.class, id);
            return instance;
        } catch (RuntimeException re) {
        	EntityManagerHelper.closeEntityManager();
        		EntityManagerHelper.log("find failed", Level.SEVERE, re);
	            throw re;
        } finally{
        	EntityManagerHelper.closeEntityManager();
        }
    }    
    

/**
	 * Find all Empresa entities with a specific property value.  
	 
	  @param propertyName the name of the Empresa property to query
	  @param value the property value to match
	  	  @return List<Empresa> found by query
	 */
    @SuppressWarnings("unchecked")
    public List<Empresa> findByProperty(String propertyName, final Object value
        ) {
    				EntityManagerHelper.log("finding Empresa instance with property: " + propertyName + ", value: " + value, Level.INFO, null);
			try {
			final String queryString = "select model from Empresa model where model." 
			 						+ propertyName + "= :propertyValue";
								Query query = getEntityManager().createQuery(queryString);
					query.setParameter("propertyValue", value);
					return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.closeEntityManager();
						EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
				throw re;
		} finally{
			EntityManagerHelper.closeEntityManager();
		}
	}			
	public List<Empresa> findByNombre(Object nombre
	) {
		return findByProperty(NOMBRE, nombre
		);
	}
	
	public List<Empresa> findByRut(Object rut
	) {
		return findByProperty(RUT, rut
		);
	}
	
	public List<Empresa> findByDv(Object dv
	) {
		return findByProperty(DV, dv
		);
	}
	
	public List<Empresa> findByTelefono(Object telefono
	) {
		return findByProperty(TELEFONO, telefono
		);
	}
	
	public List<Empresa> findByEstado(Object estado) {
		return findByProperty(ESTADO, estado);
	}
	
	public List<Empresa> findByNusuarios(Object nusuarios) {
		return findByProperty(NUSUARIOS, nusuarios);
	}

	/**
	 * Find all Empresa entities.
	  	  @return List<Empresa> all Empresa entities
	 */
	@SuppressWarnings("unchecked")
	public List<Empresa> findAll(
		) {
					EntityManagerHelper.log("finding all Empresa instances", Level.INFO, null);
			try {
			final String queryString = "select model from Empresa model order by model.nombre";
								Query query = getEntityManager().createQuery(queryString);
					return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.closeEntityManager();
						EntityManagerHelper.log("find all failed", Level.SEVERE, re);
				throw re;
		} finally{
			EntityManagerHelper.closeEntityManager();
		}
	}
	/**
	 * Encuentra todos los usuarios que pertenecen a una empresa.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Usuario> los usuarios que pertenecen a la empresa
	 */
	@SuppressWarnings("unchecked")
	public List<Usuario> findEnterpriseUsers(Integer rutEmpresa) {
		EntityManagerHelper.log("Encontrando los usuarios de la empresa RUT: " + rutEmpresa.toString(), Level.INFO, null);
		try {
			final String queryString = "select * from " + 
			"(select idusuario from " + 
				"(select p.idusuariorol from " +
					"(select ea.id,ea.idempresa from " +  
						"(SELECT * from empresa where rut=?1) e " + 
					"join empresa_app ea on ea.idempresa = e.id) x " +
						"join perfil p on p.idempresaapp = x.\"id\" GROUP BY p.idusuariorol) y " +
							"join usuario_rol ur on ur.\"id\" = y.idusuariorol GROUP BY idusuario) z " +
								"join usuario u on u.\"id\" = z.idusuario WHERE idestado = 1 and u.email NOT LIKE '%@acepta.com' ORDER BY u.dni";
			Query query = getEntityManager().createNativeQuery(queryString, Usuario.class);
			query.setParameter(1, rutEmpresa);
			return query.getResultList();			
		} catch (RuntimeException re) {
        	EntityManagerHelper.closeEntityManager();
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		} finally{
        	EntityManagerHelper.closeEntityManager();
		}
	}
	
	/**
	 * Cuenta todos los usuarios que pertenecen a una empresa, descartando a los usuarios internos de ACEPTA
	 * 
	 * @param rutEmpresa: El rut de la empresa en donde se quiere contar los usuarios
	 * @return int numero de usuarios que pertenecen a la empresa
	 */
	public int countEnterpriseUsers(Integer rutEmpresa) {
		EntityManagerHelper.log("Contando los usuarios de la empresa RUT: " + rutEmpresa.toString(), Level.INFO, null);
		try {
			final String queryString = "select count(*) from " + 
			"(select idusuario from " + 
				"(select p.idusuariorol from " +
					"(select ea.id,ea.idempresa from " +  
						"(SELECT * from empresa where rut=?1) e " + 
					"join empresa_app ea on ea.idempresa = e.id) x " +
						"join perfil p on p.idempresaapp = x.\"id\" GROUP BY p.idusuariorol) y " +
							"join usuario_rol ur on ur.\"id\" = y.idusuariorol GROUP BY idusuario) z " +
								"join usuario u on u.\"id\" = z.idusuario WHERE idestado = 1  and u.email NOT LIKE '%@acepta.com'";
			Query query = getEntityManager().createNativeQuery(queryString);
			query.setParameter(1, rutEmpresa);
			Long result = (Long)query.getSingleResult();
			return result.intValue();			
		} catch (RuntimeException re) {
        	EntityManagerHelper.closeEntityManager();
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		} finally{
        	EntityManagerHelper.closeEntityManager();
		}
	}
	
	/**
	 * @param rutEmpresa: ID de la empresa a verificar
	 * @param idUsuario: ID del usuario que se quiere verificar
	 * @return: verdadero si la empresa tiene a este usuario
	 * 				falso si la empresa no tiene a este usuario asignado a ninguna de sus aplicaciones con niguno de los roles
	 */
	@SuppressWarnings("unchecked")
	public boolean tieneUsuario(Integer idEmpresa, Integer idUsuario) {
		EntityManagerHelper.log("Encontrando los usuarios de la empresa ID: " + idEmpresa.toString(), Level.INFO, null);
		try {
			final String queryString = "select * from " + 
			"(select idusuario from " + 
				"(select p.idusuariorol from " +
					"(select ea.id,ea.idempresa from " +  
						"(SELECT * from empresa where id=?1) e " + 
					"join empresa_app ea on ea.idempresa = e.id) x " +
						"join perfil p on p.idempresaapp = x.\"id\" GROUP BY p.idusuariorol) y " +
							"join usuario_rol ur on ur.\"id\" = y.idusuariorol GROUP BY idusuario) z " +
								"join usuario u on u.\"id\" = z.idusuario WHERE idestado = 1 and u.email NOT LIKE '%@acepta.com' and id=?2";
			Query query = getEntityManager().createNativeQuery(queryString, Usuario.class);
			query.setParameter(1, idEmpresa);
			query.setParameter(2, idUsuario);
			List<Usuario> losUsuarios = (List<Usuario>)query.getResultList();
			if(losUsuarios.size()==0)
				return false;
			else 
				return true;
//			return !query.getResultList().isEmpty();
		} catch (RuntimeException re) {
        	EntityManagerHelper.closeEntityManager();
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		} finally{
        	EntityManagerHelper.closeEntityManager();
		}
	}
	/**
	 * @param rutEmpresa: ID de la empresa a verificar
	 * @param nombreAplicacion: Nombre de la aplicacion que se quiere consultar
	 * @return: verdadero si la empresa tiene esta aplicacion
	 * 				falso si la empresa no tiene esta aplicacion dentro de sus contratadas
	 */
	@SuppressWarnings("unchecked")
	public static boolean tieneAplicacion(Integer idEmpresa, String nombreAplicacion) {
		EntityManagerHelper.log("Encontrando la aplicacion de nombre: " + nombreAplicacion, Level.INFO, null);
		try {
			final String queryString = "SELECT aplicacion.\"id\", aplicacion.nombre, aplicacion.webroot, aplicacion.inicio FROM " +
			"empresa , empresa_app , aplicacion WHERE " +
			"empresa.\"id\" = ?1 AND aplicacion.nombre=?2 AND " +
			"empresa.\"id\" = empresa_app.idempresa AND " +
			"empresa_app.idapp = aplicacion.\"id\"";
			Query query = getEntityManager().createNativeQuery(queryString, Aplicacion.class);
			query.setParameter(1, idEmpresa);
			query.setParameter(2, nombreAplicacion);
			List<Aplicacion> lasAplicaciones = (List<Aplicacion>)query.getResultList();
			if(lasAplicaciones.size()==0)
				return false;
			else 
				return true;
		} catch (RuntimeException re) {
        	EntityManagerHelper.closeEntityManager();
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		} finally{
        	EntityManagerHelper.closeEntityManager();
		}
	}
}