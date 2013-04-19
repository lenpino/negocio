package cl.altair.perfilamiento.model.dao;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import cl.mycompany.perfilamiento.model.PerfilFuncion;

/**
 	* A data access object (DAO) providing persistence and search support for PerfilFuncion entities.
 	 		* Transaction control of the save(), update() and delete() operations must be handled externally by senders of these methods 
 		  or must be manually added to each of these methods for data to be persisted to the JPA datastore.	
 	 * @see com.acepta.perfilamiento.eclipselink.PerfilFuncion
  * @author MyEclipse Persistence Tools 
 */

public class PerfilFuncionDAO  {
	//property constants





	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}	
	
		/**
	 Perform an initial save of a previously unsaved PerfilFuncion entity. 
	 All subsequent persist actions of this entity should use the #update() method.
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist} operation.
	 	 
	 * <pre> 
	 *   EntityManagerHelper.beginTransaction();
	 *   PerfilFuncionDAO.save(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity PerfilFuncion entity to persist
	  @throws RuntimeException when the operation fails
	 */
    public void save(PerfilFuncion entity) {
    				EntityManagerHelper.log("saving PerfilFuncion instance", Level.INFO, null);
	        try {
            getEntityManager().persist(entity);
            			EntityManagerHelper.log("save successful", Level.INFO, null);
	        } catch (RuntimeException re) {
        				EntityManagerHelper.log("save failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    /**
	 Delete a persistent PerfilFuncion entity.
	  This operation must be performed 
	 within the a database transaction context for the entity's data to be
	 permanently deleted from the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete} operation.
	 	  
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   PerfilFuncionDAO.delete(entity);
	 *   EntityManagerHelper.commit();
	 *   entity = null;
	 * </pre>
	   @param entity PerfilFuncion entity to delete
	 @throws RuntimeException when the operation fails
	 */
    public void delete(PerfilFuncion entity) {
    				EntityManagerHelper.log("deleting PerfilFuncion instance", Level.INFO, null);
	        try {
        	entity = getEntityManager().getReference(PerfilFuncion.class, entity.getId());
            getEntityManager().remove(entity);
            			EntityManagerHelper.log("delete successful", Level.INFO, null);
	        } catch (RuntimeException re) {
        				EntityManagerHelper.log("delete failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    /**
	 Persist a previously saved PerfilFuncion entity and return it or a copy of it to the sender. 
	 A copy of the PerfilFuncion entity parameter is returned when the JPA persistence mechanism has not previously been tracking the updated entity. 
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence
	 store, i.e., database. This method uses the {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge} operation.
	 	 
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   entity = PerfilFuncionDAO.update(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity PerfilFuncion entity to update
	 @return PerfilFuncion the persisted PerfilFuncion entity instance, may not be the same
	 @throws RuntimeException if the operation fails
	 */
    public PerfilFuncion update(PerfilFuncion entity) {
    				EntityManagerHelper.log("updating PerfilFuncion instance", Level.INFO, null);
	        try {
            PerfilFuncion result = getEntityManager().merge(entity);
            			EntityManagerHelper.log("update successful", Level.INFO, null);
	            return result;
        } catch (RuntimeException re) {
        				EntityManagerHelper.log("update failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    public PerfilFuncion findById( Integer id) {
    				EntityManagerHelper.log("finding PerfilFuncion instance with id: " + id, Level.INFO, null);
	        try {
            PerfilFuncion instance = getEntityManager().find(PerfilFuncion.class, id);
            return instance;
        } catch (RuntimeException re) {
        				EntityManagerHelper.log("find failed", Level.SEVERE, re);
	            throw re;
        }
    }    
    

/**
	 * Find all PerfilFuncion entities with a specific property value.  
	 
	  @param propertyName the name of the PerfilFuncion property to query
	  @param value the property value to match
	  	  @return List<PerfilFuncion> found by query
	 */
    @SuppressWarnings("unchecked")
    public List<PerfilFuncion> findByProperty(String propertyName, final Object value
        ) {
    				EntityManagerHelper.log("finding PerfilFuncion instance with property: " + propertyName + ", value: " + value, Level.INFO, null);
			try {
			final String queryString = "select model from PerfilFuncion model where model." 
			 						+ propertyName + "= :propertyValue";
								Query query = getEntityManager().createQuery(queryString);
					query.setParameter("propertyValue", value);
					return query.getResultList();
		} catch (RuntimeException re) {
						EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
				throw re;
		}
	}			
	
	/**
	 * Find all PerfilFuncion entities.
	  	  @return List<PerfilFuncion> all PerfilFuncion entities
	 */
	@SuppressWarnings("unchecked")
	public List<PerfilFuncion> findAll(
		) {
					EntityManagerHelper.log("finding all PerfilFuncion instances", Level.INFO, null);
			try {
			final String queryString = "select model from PerfilFuncion model";
								Query query = getEntityManager().createQuery(queryString);
					return query.getResultList();
		} catch (RuntimeException re) {
						EntityManagerHelper.log("find all failed", Level.SEVERE, re);
				throw re;
		}
	}
	
}