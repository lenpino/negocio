package cl.altair.perfilamiento.model.dao;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import cl.altair.modelo.portal.Programa;


/**
 	* A data access object (DAO) providing persistence and search support for Empresa entities.
 	 		* Transaction control of the save(), update() and delete() operations must be handled externally by senders of these methods 
 		  or must be manually added to each of these methods for data to be persisted to the JPA datastore.	
 	 * @see com.acepta.perfilamiento.eclipselink.Empresa
  * @author MyEclipse Persistence Tools 
 */

public class ProgramaDAO  {
	//property constants
	public static final String ACTIVACION = "activacion";
	public static final String SERIAL = "serial";
	public static final String ESTADO = "estado";
	public static final String VERSION = "version";




	private static EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}	
	
		/**
	 Perform an initial save of a previously unsaved Programa entity. 
	 All subsequent persist actions of this entity should use the #update() method.
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist} operation.
	 	 
	 * <pre> 
	 *   EntityManagerHelper.beginTransaction();
	 *   ProgramaDAO.save(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity Programa entity to persist
	  @throws RuntimeException when the operation fails
	 */
    public void save(Programa entity) {
    		EntityManagerHelper.log("saving Programa instance", Level.INFO, null);
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
	 Delete a persistent Programa entity.
	  This operation must be performed 
	 within the a database transaction context for the entity's data to be
	 permanently deleted from the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete} operation.
	 	  
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   ProgramaDAO.delete(entity);
	 *   EntityManagerHelper.commit();
	 *   entity = null;
	 * </pre>
	   @param entity Programa entity to delete
	 @throws RuntimeException when the operation fails
	 */
    public void delete(Programa entity) {
    				EntityManagerHelper.log("deleting Programa instance", Level.INFO, null);
	        try {
        	entity = getEntityManager().getReference(Programa.class, entity.getId());
            getEntityManager().remove(entity);
            			EntityManagerHelper.log("delete successful", Level.INFO, null);
	        } catch (RuntimeException re) {
	        	EntityManagerHelper.closeEntityManager();
        		EntityManagerHelper.log("delete failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    /**
	 Persist a previously saved Programa entity and return it or a copy of it to the sender. 
	 A copy of the Programa entity parameter is returned when the JPA persistence mechanism has not previously been tracking the updated entity. 
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence
	 store, i.e., database. This method uses the {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge} operation.
	 	 
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   entity = ProgramaDAO.update(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity Programa entity to update
	 @return Programa the persisted Programa entity instance, may not be the same
	 @throws RuntimeException if the operation fails
	 */
    public Programa update(Programa entity) {
    				EntityManagerHelper.log("updating Programa instance", Level.INFO, null);
	        try {
            Programa result = getEntityManager().merge(entity);
            			EntityManagerHelper.log("update successful", Level.INFO, null);
	            return result;
        } catch (RuntimeException re) {
        	EntityManagerHelper.closeEntityManager();
        				EntityManagerHelper.log("update failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    public Programa findById( Integer id) {
    				EntityManagerHelper.log("finding Programa instance with id: " + id, Level.INFO, null);
	        try {
            Programa instance = getEntityManager().find(Programa.class, id);
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
	 * Find all Programa entities with a specific property value.  
	 
	  @param propertyName the name of the Programa property to query
	  @param value the property value to match
	  	  @return List<Programa> found by query
	 */
    @SuppressWarnings("unchecked")
    public List<Programa> findByProperty(String propertyName, final Object value
        ) {
    				EntityManagerHelper.log("finding Programa instance with property: " + propertyName + ", value: " + value, Level.INFO, null);
			try {
			final String queryString = "select model from Programa model where model." 
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
	public List<Programa> findByActivacion(Object nombre
	) {
		return findByProperty(ACTIVACION, nombre
		);
	}
	
	public List<Programa> findBySerial(Object rut
	) {
		return findByProperty(SERIAL, rut
		);
	}
	
	public List<Programa> findByVersion(Object telefono
	) {
		return findByProperty(VERSION, telefono
		);
	}
	
	public List<Programa> findByEstado(Object estado) {
		return findByProperty(ESTADO, estado);
	}

	/**
	 * Find all Programa entities.
	  	  @return List<Programa> all Programa entities
	 */
	@SuppressWarnings("unchecked")
	public List<Programa> findAll(
		) {
					EntityManagerHelper.log("finding all Programa instances", Level.INFO, null);
			try {
			final String queryString = "select model from Programa model order by model.nombre";
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
}