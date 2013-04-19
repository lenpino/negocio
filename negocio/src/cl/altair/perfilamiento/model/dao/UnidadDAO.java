package cl.altair.perfilamiento.model.dao;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import cl.mycompany.perfilamiento.model.Unidad;

/**
 	* A data access object (DAO) providing persistence and search support for Unidad entities.
 	 		* Transaction control of the save(), update() and delete() operations must be handled externally by senders of these methods 
 		  or must be manually added to each of these methods for data to be persisted to the JPA datastore.	
 	 * @see com.acepta.perfilamiento.eclipselink.Unidad
  * @author MyEclipse Persistence Tools 
 */

public class UnidadDAO  {
	//property constants
	public static final String NOMBRE = "nombre";





	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}	
	
		/**
	 Perform an initial save of a previously unsaved Unidad entity. 
	 All subsequent persist actions of this entity should use the #update() method.
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist} operation.
	 	 
	 * <pre> 
	 *   EntityManagerHelper.beginTransaction();
	 *   UnidadDAO.save(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity Unidad entity to persist
	  @throws RuntimeException when the operation fails
	 */
    public void save(Unidad entity) {
    				EntityManagerHelper.log("saving Unidad instance", Level.INFO, null);
	        try {
            getEntityManager().persist(entity);
            			EntityManagerHelper.log("save successful", Level.INFO, null);
	        } catch (RuntimeException re) {
        				EntityManagerHelper.log("save failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    /**
	 Delete a persistent Unidad entity.
	  This operation must be performed 
	 within the a database transaction context for the entity's data to be
	 permanently deleted from the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete} operation.
	 	  
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   UnidadDAO.delete(entity);
	 *   EntityManagerHelper.commit();
	 *   entity = null;
	 * </pre>
	   @param entity Unidad entity to delete
	 @throws RuntimeException when the operation fails
	 */
    public void delete(Unidad entity) {
    				EntityManagerHelper.log("deleting Unidad instance", Level.INFO, null);
	        try {
        	entity = getEntityManager().getReference(Unidad.class, entity.getId());
            getEntityManager().remove(entity);
            			EntityManagerHelper.log("delete successful", Level.INFO, null);
	        } catch (RuntimeException re) {
        				EntityManagerHelper.log("delete failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    /**
	 Persist a previously saved Unidad entity and return it or a copy of it to the sender. 
	 A copy of the Unidad entity parameter is returned when the JPA persistence mechanism has not previously been tracking the updated entity. 
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence
	 store, i.e., database. This method uses the {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge} operation.
	 	 
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   entity = UnidadDAO.update(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity Unidad entity to update
	 @return Unidad the persisted Unidad entity instance, may not be the same
	 @throws RuntimeException if the operation fails
	 */
    public Unidad update(Unidad entity) {
    				EntityManagerHelper.log("updating Unidad instance", Level.INFO, null);
	        try {
            Unidad result = getEntityManager().merge(entity);
            			EntityManagerHelper.log("update successful", Level.INFO, null);
	            return result;
        } catch (RuntimeException re) {
        				EntityManagerHelper.log("update failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    public Unidad findById( Integer id) {
    				EntityManagerHelper.log("finding Unidad instance with id: " + id, Level.INFO, null);
	        try {
            Unidad instance = getEntityManager().find(Unidad.class, id);
            return instance;
        } catch (RuntimeException re) {
        				EntityManagerHelper.log("find failed", Level.SEVERE, re);
	            throw re;
        }
    }    
    

/**
	 * Find all Unidad entities with a specific property value.  
	 
	  @param propertyName the name of the Unidad property to query
	  @param value the property value to match
	  	  @return List<Unidad> found by query
	 */
    @SuppressWarnings("unchecked")
    public List<Unidad> findByProperty(String propertyName, final Object value
        ) {
    				EntityManagerHelper.log("finding Unidad instance with property: " + propertyName + ", value: " + value, Level.INFO, null);
			try {
			final String queryString = "select model from Unidad model where model." 
			 						+ propertyName + "= :propertyValue";
								Query query = getEntityManager().createQuery(queryString);
					query.setParameter("propertyValue", value);
					return query.getResultList();
		} catch (RuntimeException re) {
						EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
				throw re;
		}
	}			
	public List<Unidad> findByNombre(Object nombre
	) {
		return findByProperty(NOMBRE, nombre
		);
	}
	
	
	/**
	 * Find all Unidad entities.
	  	  @return List<Unidad> all Unidad entities
	 */
	@SuppressWarnings("unchecked")
	public List<Unidad> findAll(
		) {
					EntityManagerHelper.log("finding all Unidad instances", Level.INFO, null);
			try {
			final String queryString = "select model from Unidad model";
								Query query = getEntityManager().createQuery(queryString);
					return query.getResultList();
		} catch (RuntimeException re) {
						EntityManagerHelper.log("find all failed", Level.SEVERE, re);
				throw re;
		}
	}
	
}