package cl.altair.perfilamiento.model.dao;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import cl.mycompany.perfilamiento.model.Perfil;

/**
 	* A data access object (DAO) providing persistence and search support for Perfil entities.
 	 		* Transaction control of the save(), update() and delete() operations must be handled externally by senders of these methods 
 		  or must be manually added to each of these methods for data to be persisted to the JPA datastore.	
 	 * @see com.acepta.perfilamiento.eclipselink.Perfil
  * @author MyEclipse Persistence Tools 
 */

public class PerfilDAO  {
	//property constants





	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}	
	
		/**
	 Perform an initial save of a previously unsaved Perfil entity. 
	 All subsequent persist actions of this entity should use the #update() method.
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist} operation.
	 	 
	 * <pre> 
	 *   EntityManagerHelper.beginTransaction();
	 *   PerfilDAO.save(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity Perfil entity to persist
	  @throws RuntimeException when the operation fails
	 */
    public void save(Perfil entity) {
    				EntityManagerHelper.log("saving Perfil instance", Level.INFO, null);
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
	 Delete a persistent Perfil entity.
	  This operation must be performed 
	 within the a database transaction context for the entity's data to be
	 permanently deleted from the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete} operation.
	 	  
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   PerfilDAO.delete(entity);
	 *   EntityManagerHelper.commit();
	 *   entity = null;
	 * </pre>
	   @param entity Perfil entity to delete
	 @throws RuntimeException when the operation fails
	 */
    public void delete(Perfil entity) {
    				EntityManagerHelper.log("deleting Perfil instance", Level.INFO, null);
	        try {
        	entity = getEntityManager().getReference(Perfil.class, entity.getId());
            getEntityManager().remove(entity);
            			EntityManagerHelper.log("delete successful", Level.INFO, null);
	        } catch (RuntimeException re) {
	        	EntityManagerHelper.closeEntityManager();
        				EntityManagerHelper.log("delete failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    /**
	 Persist a previously saved Perfil entity and return it or a copy of it to the sender. 
	 A copy of the Perfil entity parameter is returned when the JPA persistence mechanism has not previously been tracking the updated entity. 
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence
	 store, i.e., database. This method uses the {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge} operation.
	 	 
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   entity = PerfilDAO.update(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity Perfil entity to update
	 @return Perfil the persisted Perfil entity instance, may not be the same
	 @throws RuntimeException if the operation fails
	 */
    public Perfil update(Perfil entity) {
    				EntityManagerHelper.log("updating Perfil instance", Level.INFO, null);
	        try {
            Perfil result = getEntityManager().merge(entity);
            			EntityManagerHelper.log("update successful", Level.INFO, null);
	            return result;
        } catch (RuntimeException re) {
        	EntityManagerHelper.closeEntityManager();
        				EntityManagerHelper.log("update failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    public Perfil findById( Integer id) {
    				EntityManagerHelper.log("finding Perfil instance with id: " + id, Level.INFO, null);
	        try {
            Perfil instance = getEntityManager().find(Perfil.class, id);
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
	 * Find all Perfil entities with a specific property value.  
	 
	  @param propertyName the name of the Perfil property to query
	  @param value the property value to match
	  	  @return List<Perfil> found by query
	 */
    @SuppressWarnings("unchecked")
    public List<Perfil> findByProperty(String propertyName, final Object value
        ) {
    				EntityManagerHelper.log("finding Perfil instance with property: " + propertyName + ", value: " + value, Level.INFO, null);
			try {
			final String queryString = "select model from Perfil model where model." 
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
	 * Find all Perfil entities.
	  	  @return List<Perfil> all Perfil entities
	 */
	@SuppressWarnings("unchecked")
	public List<Perfil> findAll(
		) {
					EntityManagerHelper.log("finding all Perfil instances", Level.INFO, null);
			try {
			final String queryString = "select model from Perfil model";
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