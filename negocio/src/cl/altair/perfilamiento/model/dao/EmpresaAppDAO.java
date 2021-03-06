package cl.altair.perfilamiento.model.dao;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import cl.mycompany.perfilamiento.model.*;

/**
 	* A data access object (DAO) providing persistence and search support for EmpresaApp entities.
 	 		* Transaction control of the save(), update() and delete() operations must be handled externally by senders of these methods 
 		  or must be manually added to each of these methods for data to be persisted to the JPA datastore.	
 	 * @see com.acepta.perfilamiento.eclipselink.EmpresaApp
  * @author MyEclipse Persistence Tools 
 */

public class EmpresaAppDAO  {
	//property constants





	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}	
	
		/**
	 Perform an initial save of a previously unsaved EmpresaApp entity. 
	 All subsequent persist actions of this entity should use the #update() method.
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist} operation.
	 	 
	 * <pre> 
	 *   EntityManagerHelper.beginTransaction();
	 *   EmpresaAppDAO.save(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity EmpresaApp entity to persist
	  @throws RuntimeException when the operation fails
	 */
    public void save(EmpresaApp entity) {
    				EntityManagerHelper.log("saving EmpresaApp instance", Level.INFO, null);
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
	 Delete a persistent EmpresaApp entity.
	  This operation must be performed 
	 within the a database transaction context for the entity's data to be
	 permanently deleted from the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete} operation.
	 	  
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   EmpresaAppDAO.delete(entity);
	 *   EntityManagerHelper.commit();
	 *   entity = null;
	 * </pre>
	   @param entity EmpresaApp entity to delete
	 @throws RuntimeException when the operation fails
	 */
    public void delete(EmpresaApp entity) {
    				EntityManagerHelper.log("deleting EmpresaApp instance", Level.INFO, null);
	        try {
        	entity = getEntityManager().getReference(EmpresaApp.class, entity.getId());
            getEntityManager().remove(entity);
            			EntityManagerHelper.log("delete successful", Level.INFO, null);
	        } catch (RuntimeException re) {
	        	EntityManagerHelper.closeEntityManager();
        				EntityManagerHelper.log("delete failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    /**
	 Persist a previously saved EmpresaApp entity and return it or a copy of it to the sender. 
	 A copy of the EmpresaApp entity parameter is returned when the JPA persistence mechanism has not previously been tracking the updated entity. 
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence
	 store, i.e., database. This method uses the {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge} operation.
	 	 
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   entity = EmpresaAppDAO.update(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity EmpresaApp entity to update
	 @return EmpresaApp the persisted EmpresaApp entity instance, may not be the same
	 @throws RuntimeException if the operation fails
	 */
    public EmpresaApp update(EmpresaApp entity) {
    				EntityManagerHelper.log("updating EmpresaApp instance", Level.INFO, null);
	        try {
            EmpresaApp result = getEntityManager().merge(entity);
            			EntityManagerHelper.log("update successful", Level.INFO, null);
	            return result;
        } catch (RuntimeException re) {
        	EntityManagerHelper.closeEntityManager();
        				EntityManagerHelper.log("update failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    public EmpresaApp findById( Integer id) {
    				EntityManagerHelper.log("finding EmpresaApp instance with id: " + id, Level.INFO, null);
	        try {
            EmpresaApp instance = getEntityManager().find(EmpresaApp.class, id);
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
	 * Find all EmpresaApp entities with a specific property value.  
	 
	  @param propertyName the name of the EmpresaApp property to query
	  @param value the property value to match
	  	  @return List<EmpresaApp> found by query
	 */
    @SuppressWarnings("unchecked")
    public List<EmpresaApp> findByProperty(String propertyName, final Object value
        ) {
    				EntityManagerHelper.log("finding EmpresaApp instance with property: " + propertyName + ", value: " + value, Level.INFO, null);
			try {
			final String queryString = "select model from EmpresaApp model where model." 
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
	
	/**
	 * Find all EmpresaApp entities.
	  	  @return List<EmpresaApp> all EmpresaApp entities
	 */
	@SuppressWarnings("unchecked")
	public List<EmpresaApp> findAll(
		) {
					EntityManagerHelper.log("finding all EmpresaApp instances", Level.INFO, null);
			try {
			final String queryString = "select model from EmpresaApp model";
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