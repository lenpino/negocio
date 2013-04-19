package cl.altair.perfilamiento.model.dao;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import cl.mycompany.perfilamiento.model.Rol;

/**
 	* A data access object (DAO) providing persistence and search support for Rol entities.
 	 		* Transaction control of the save(), update() and delete() operations must be handled externally by senders of these methods 
 		  or must be manually added to each of these methods for data to be persisted to the JPA datastore.	
 	 * @see com.acepta.perfilamiento.eclipselink.Rol
  * @author MyEclipse Persistence Tools 
 */

public class RolDAO  {
	//property constants
	public static final String NOMBRE = "nombre";





	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}	
	
		/**
	 Perform an initial save of a previously unsaved Rol entity. 
	 All subsequent persist actions of this entity should use the #update() method.
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist} operation.
	 	 
	 * <pre> 
	 *   EntityManagerHelper.beginTransaction();
	 *   RolDAO.save(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity Rol entity to persist
	  @throws RuntimeException when the operation fails
	 */
    public void save(Rol entity) {
    				EntityManagerHelper.log("saving Rol instance", Level.INFO, null);
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
	 Delete a persistent Rol entity.
	  This operation must be performed 
	 within the a database transaction context for the entity's data to be
	 permanently deleted from the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete} operation.
	 	  
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   RolDAO.delete(entity);
	 *   EntityManagerHelper.commit();
	 *   entity = null;
	 * </pre>
	   @param entity Rol entity to delete
	 @throws RuntimeException when the operation fails
	 */
    public void delete(Rol entity) {
    				EntityManagerHelper.log("deleting Rol instance", Level.INFO, null);
	        try {
        	entity = getEntityManager().getReference(Rol.class, entity.getId());
            getEntityManager().remove(entity);
            			EntityManagerHelper.log("delete successful", Level.INFO, null);
	        } catch (RuntimeException re) {
	        	EntityManagerHelper.closeEntityManager();
        				EntityManagerHelper.log("delete failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    /**
	 Persist a previously saved Rol entity and return it or a copy of it to the sender. 
	 A copy of the Rol entity parameter is returned when the JPA persistence mechanism has not previously been tracking the updated entity. 
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence
	 store, i.e., database. This method uses the {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge} operation.
	 	 
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   entity = RolDAO.update(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity Rol entity to update
	 @return Rol the persisted Rol entity instance, may not be the same
	 @throws RuntimeException if the operation fails
	 */
    public Rol update(Rol entity) {
    				EntityManagerHelper.log("updating Rol instance", Level.INFO, null);
	        try {
            Rol result = getEntityManager().merge(entity);
            			EntityManagerHelper.log("update successful", Level.INFO, null);
	            return result;
        } catch (RuntimeException re) {
        	EntityManagerHelper.closeEntityManager();
        				EntityManagerHelper.log("update failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    public Rol findById( Integer id) {
    				EntityManagerHelper.log("finding Rol instance with id: " + id, Level.INFO, null);
	        try {
            Rol instance = getEntityManager().find(Rol.class, id);
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
	 * Find all Rol entities with a specific property value.  
	 
	  @param propertyName the name of the Rol property to query
	  @param value the property value to match
	  	  @return List<Rol> found by query
	 */
    @SuppressWarnings("unchecked")
    public List<Rol> findByProperty(String propertyName, final Object value
        ) {
    				EntityManagerHelper.log("finding Rol instance with property: " + propertyName + ", value: " + value, Level.INFO, null);
			try {
			final String queryString = "select model from Rol model where model." 
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
	public List<Rol> findByNombre(Object nombre
	) {
		return findByProperty(NOMBRE, nombre
		);
	}
	
	
	/**
	 * Find all Rol entities.
	  	  @return List<Rol> all Rol entities
	 */
	@SuppressWarnings("unchecked")
	public List<Rol> findAll(
		) {
					EntityManagerHelper.log("finding all Rol instances", Level.INFO, null);
			try {
			final String queryString = "select model from Rol model";
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