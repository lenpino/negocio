package cl.altair.perfilamiento.model.dao;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import cl.mycompany.perfilamiento.model.UsuarioRol;


/**
 	* A data access object (DAO) providing persistence and search support for UsuarioRol entities.
 	 		* Transaction control of the save(), update() and delete() operations must be handled externally by senders of these methods 
 		  or must be manually added to each of these methods for data to be persisted to the JPA datastore.	
 	 * @see com.acepta.perfilamiento.eclipselink.UsuarioRol
  * @author MyEclipse Persistence Tools 
 */

public class UsuarioRolDAO  {
	//property constants
	public static final String ESTADO = "estado";





	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}	
	
		/**
	 Perform an initial save of a previously unsaved UsuarioRol entity. 
	 All subsequent persist actions of this entity should use the #update() method.
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist} operation.
	 	 
	 * <pre> 
	 *   EntityManagerHelper.beginTransaction();
	 *   UsuarioRolDAO.save(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity UsuarioRol entity to persist
	  @throws RuntimeException when the operation fails
	 */
    public void save(UsuarioRol entity) {
    				EntityManagerHelper.log("saving UsuarioRol instance", Level.INFO, null);
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
	 Delete a persistent UsuarioRol entity.
	  This operation must be performed 
	 within the a database transaction context for the entity's data to be
	 permanently deleted from the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete} operation.
	 	  
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   UsuarioRolDAO.delete(entity);
	 *   EntityManagerHelper.commit();
	 *   entity = null;
	 * </pre>
	   @param entity UsuarioRol entity to delete
	 @throws RuntimeException when the operation fails
	 */
    public void delete(UsuarioRol entity) {
    				EntityManagerHelper.log("deleting UsuarioRol instance", Level.INFO, null);
	        try {
        	entity = getEntityManager().getReference(UsuarioRol.class, entity.getId());
            getEntityManager().remove(entity);
            			EntityManagerHelper.log("delete successful", Level.INFO, null);
	        } catch (RuntimeException re) {
	        	EntityManagerHelper.closeEntityManager();
        				EntityManagerHelper.log("delete failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    /**
	 Persist a previously saved UsuarioRol entity and return it or a copy of it to the sender. 
	 A copy of the UsuarioRol entity parameter is returned when the JPA persistence mechanism has not previously been tracking the updated entity. 
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence
	 store, i.e., database. This method uses the {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge} operation.
	 	 
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   entity = UsuarioRolDAO.update(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity UsuarioRol entity to update
	 @return UsuarioRol the persisted UsuarioRol entity instance, may not be the same
	 @throws RuntimeException if the operation fails
	 */
    public UsuarioRol update(UsuarioRol entity) {
    				EntityManagerHelper.log("updating UsuarioRol instance", Level.INFO, null);
	        try {
            UsuarioRol result = getEntityManager().merge(entity);
            			EntityManagerHelper.log("update successful", Level.INFO, null);
	            return result;
        } catch (RuntimeException re) {
        	EntityManagerHelper.closeEntityManager();
        				EntityManagerHelper.log("update failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    public UsuarioRol findById( Integer id) {
    				EntityManagerHelper.log("finding UsuarioRol instance with id: " + id, Level.INFO, null);
	        try {
            UsuarioRol instance = getEntityManager().find(UsuarioRol.class, id);
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
	 * Find all UsuarioRol entities with a specific property value.  
	 
	  @param propertyName the name of the UsuarioRol property to query
	  @param value the property value to match
	  	  @return List<UsuarioRol> found by query
	 */
    @SuppressWarnings("unchecked")
    public List<UsuarioRol> findByProperty(String propertyName, final Object value
        ) {
    				EntityManagerHelper.log("finding UsuarioRol instance with property: " + propertyName + ", value: " + value, Level.INFO, null);
			try {
			final String queryString = "select model from UsuarioRol model where model." 
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
	
	public List<UsuarioRol> findByEstado(Object estado) {
		return findByProperty(ESTADO, estado);
	}
	
	/**
	 * Find all UsuarioRol entities.
	  	  @return List<UsuarioRol> all UsuarioRol entities
	 */
	@SuppressWarnings("unchecked")
	public List<UsuarioRol> findAll(
		) {
					EntityManagerHelper.log("finding all UsuarioRol instances", Level.INFO, null);
			try {
			final String queryString = "select model from UsuarioRol model";
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