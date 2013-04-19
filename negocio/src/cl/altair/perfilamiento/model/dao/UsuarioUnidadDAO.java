package cl.altair.perfilamiento.model.dao;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import cl.mycompany.perfilamiento.model.UsuarioUnidad;

/**
 	* A data access object (DAO) providing persistence and search support for UsuarioUnidad entities.
 	 		* Transaction control of the save(), update() and delete() operations must be handled externally by senders of these methods 
 		  or must be manually added to each of these methods for data to be persisted to the JPA datastore.	
 	 * @see com.acepta.perfilamiento.eclipselink.UsuarioUnidad
  * @author MyEclipse Persistence Tools 
 */

public class UsuarioUnidadDAO  {
	//property constants





	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}	
	
		/**
	 Perform an initial save of a previously unsaved UsuarioUnidad entity. 
	 All subsequent persist actions of this entity should use the #update() method.
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist} operation.
	 	 
	 * <pre> 
	 *   EntityManagerHelper.beginTransaction();
	 *   UsuarioUnidadDAO.save(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity UsuarioUnidad entity to persist
	  @throws RuntimeException when the operation fails
	 */
    public void save(UsuarioUnidad entity) {
    				EntityManagerHelper.log("saving UsuarioUnidad instance", Level.INFO, null);
	        try {
            getEntityManager().persist(entity);
            			EntityManagerHelper.log("save successful", Level.INFO, null);
	        } catch (RuntimeException re) {
        				EntityManagerHelper.log("save failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    /**
	 Delete a persistent UsuarioUnidad entity.
	  This operation must be performed 
	 within the a database transaction context for the entity's data to be
	 permanently deleted from the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete} operation.
	 	  
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   UsuarioUnidadDAO.delete(entity);
	 *   EntityManagerHelper.commit();
	 *   entity = null;
	 * </pre>
	   @param entity UsuarioUnidad entity to delete
	 @throws RuntimeException when the operation fails
	 */
    public void delete(UsuarioUnidad entity) {
    				EntityManagerHelper.log("deleting UsuarioUnidad instance", Level.INFO, null);
	        try {
        	entity = getEntityManager().getReference(UsuarioUnidad.class, entity.getId());
            getEntityManager().remove(entity);
            			EntityManagerHelper.log("delete successful", Level.INFO, null);
	        } catch (RuntimeException re) {
        				EntityManagerHelper.log("delete failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    /**
	 Persist a previously saved UsuarioUnidad entity and return it or a copy of it to the sender. 
	 A copy of the UsuarioUnidad entity parameter is returned when the JPA persistence mechanism has not previously been tracking the updated entity. 
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence
	 store, i.e., database. This method uses the {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge} operation.
	 	 
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   entity = UsuarioUnidadDAO.update(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity UsuarioUnidad entity to update
	 @return UsuarioUnidad the persisted UsuarioUnidad entity instance, may not be the same
	 @throws RuntimeException if the operation fails
	 */
    public UsuarioUnidad update(UsuarioUnidad entity) {
    				EntityManagerHelper.log("updating UsuarioUnidad instance", Level.INFO, null);
	        try {
            UsuarioUnidad result = getEntityManager().merge(entity);
            			EntityManagerHelper.log("update successful", Level.INFO, null);
	            return result;
        } catch (RuntimeException re) {
        				EntityManagerHelper.log("update failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    public UsuarioUnidad findById( Integer id) {
    				EntityManagerHelper.log("finding UsuarioUnidad instance with id: " + id, Level.INFO, null);
	        try {
            UsuarioUnidad instance = getEntityManager().find(UsuarioUnidad.class, id);
            return instance;
        } catch (RuntimeException re) {
        				EntityManagerHelper.log("find failed", Level.SEVERE, re);
	            throw re;
        }
    }    
    

/**
	 * Find all UsuarioUnidad entities with a specific property value.  
	 
	  @param propertyName the name of the UsuarioUnidad property to query
	  @param value the property value to match
	  	  @return List<UsuarioUnidad> found by query
	 */
    @SuppressWarnings("unchecked")
    public List<UsuarioUnidad> findByProperty(String propertyName, final Object value
        ) {
    				EntityManagerHelper.log("finding UsuarioUnidad instance with property: " + propertyName + ", value: " + value, Level.INFO, null);
			try {
			final String queryString = "select model from UsuarioUnidad model where model." 
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
	 * Find all UsuarioUnidad entities.
	  	  @return List<UsuarioUnidad> all UsuarioUnidad entities
	 */
	@SuppressWarnings("unchecked")
	public List<UsuarioUnidad> findAll(
		) {
					EntityManagerHelper.log("finding all UsuarioUnidad instances", Level.INFO, null);
			try {
			final String queryString = "select model from UsuarioUnidad model";
								Query query = getEntityManager().createQuery(queryString);
					return query.getResultList();
		} catch (RuntimeException re) {
						EntityManagerHelper.log("find all failed", Level.SEVERE, re);
				throw re;
		}
	}
	
}