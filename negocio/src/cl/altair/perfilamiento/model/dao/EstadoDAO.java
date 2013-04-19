package cl.altair.perfilamiento.model.dao;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import cl.mycompany.perfilamiento.model.Estado;

/**
 	* A data access object (DAO) providing persistence and search support for Estado entities.
 	 		* Transaction control of the save(), update() and delete() operations must be handled externally by senders of these methods 
 		  or must be manually added to each of these methods for data to be persisted to the JPA datastore.	
 	 * @see com.acepta.perfilamiento.eclipselink.Estado
  * @author MyEclipse Persistence Tools 
 */

public class EstadoDAO  {
	//property constants
	public static final String NOMBRE = "nombre";





	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}	
	
		/**
	 Perform an initial save of a previously unsaved Estado entity. 
	 All subsequent persist actions of this entity should use the #update() method.
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist} operation.
	 	 
	 * <pre> 
	 *   EntityManagerHelper.beginTransaction();
	 *   EstadoDAO.save(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity Estado entity to persist
	  @throws RuntimeException when the operation fails
	 */
    public void save(Estado entity) {
    				EntityManagerHelper.log("saving Estado instance", Level.INFO, null);
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
	 Delete a persistent Estado entity.
	  This operation must be performed 
	 within the a database transaction context for the entity's data to be
	 permanently deleted from the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete} operation.
	 	  
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   EstadoDAO.delete(entity);
	 *   EntityManagerHelper.commit();
	 *   entity = null;
	 * </pre>
	   @param entity Estado entity to delete
	 @throws RuntimeException when the operation fails
	 */
    public void delete(Estado entity) {
    				EntityManagerHelper.log("deleting Estado instance", Level.INFO, null);
	        try {
        	entity = getEntityManager().getReference(Estado.class, entity.getId());
            getEntityManager().remove(entity);
            			EntityManagerHelper.log("delete successful", Level.INFO, null);
	        } catch (RuntimeException re) {
	        	EntityManagerHelper.closeEntityManager();
        				EntityManagerHelper.log("delete failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    /**
	 Persist a previously saved Estado entity and return it or a copy of it to the sender. 
	 A copy of the Estado entity parameter is returned when the JPA persistence mechanism has not previously been tracking the updated entity. 
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence
	 store, i.e., database. This method uses the {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge} operation.
	 	 
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   entity = EstadoDAO.update(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity Estado entity to update
	 @return Estado the persisted Estado entity instance, may not be the same
	 @throws RuntimeException if the operation fails
	 */
    public Estado update(Estado entity) {
    				EntityManagerHelper.log("updating Estado instance", Level.INFO, null);
	        try {
            Estado result = getEntityManager().merge(entity);
            			EntityManagerHelper.log("update successful", Level.INFO, null);
	            return result;
        } catch (RuntimeException re) {
        	EntityManagerHelper.closeEntityManager();
        				EntityManagerHelper.log("update failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    public Estado findById( Integer id) {
    				EntityManagerHelper.log("finding Estado instance with id: " + id, Level.INFO, null);
	        try {
            Estado instance = getEntityManager().find(Estado.class, id);
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
	 * Find all Estado entities with a specific property value.  
	 
	  @param propertyName the name of the Estado property to query
	  @param value the property value to match
	  	  @return List<Estado> found by query
	 */
    @SuppressWarnings("unchecked")
    public List<Estado> findByProperty(String propertyName, final Object value
        ) {
    				EntityManagerHelper.log("finding Estado instance with property: " + propertyName + ", value: " + value, Level.INFO, null);
			try {
			final String queryString = "select model from Estado model where model." 
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
	public List<Estado> findByNombre(Object nombre
	) {
		return findByProperty(NOMBRE, nombre
		);
	}
	
	
	/**
	 * Find all Estado entities.
	  	  @return List<Estado> all Estado entities
	 */
	@SuppressWarnings("unchecked")
	public List<Estado> findAll(
		) {
					EntityManagerHelper.log("finding all Estado instances", Level.INFO, null);
			try {
			final String queryString = "select model from Estado model";
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