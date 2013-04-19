package cl.altair.perfilamiento.model.dao;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import cl.mycompany.perfilamiento.model.PaisEmpresa;

/**
 	* A data access object (DAO) providing persistence and search support for PaisEmpresa entities.
 	 		* Transaction control of the save(), update() and delete() operations must be handled externally by senders of these methods 
 		  or must be manually added to each of these methods for data to be persisted to the JPA datastore.	
 	 * @see com.acepta.perfilamiento.eclipselink.PaisEmpresa
  * @author MyEclipse Persistence Tools 
 */

public class PaisEmpresaDAO  {
	//property constants





	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}	
	
		/**
	 Perform an initial save of a previously unsaved PaisEmpresa entity. 
	 All subsequent persist actions of this entity should use the #update() method.
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist} operation.
	 	 
	 * <pre> 
	 *   EntityManagerHelper.beginTransaction();
	 *   PaisEmpresaDAO.save(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity PaisEmpresa entity to persist
	  @throws RuntimeException when the operation fails
	 */
    public void save(PaisEmpresa entity) {
    				EntityManagerHelper.log("saving PaisEmpresa instance", Level.INFO, null);
	        try {
            getEntityManager().persist(entity);
            			EntityManagerHelper.log("save successful", Level.INFO, null);
	        } catch (RuntimeException re) {
        				EntityManagerHelper.log("save failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    /**
	 Delete a persistent PaisEmpresa entity.
	  This operation must be performed 
	 within the a database transaction context for the entity's data to be
	 permanently deleted from the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete} operation.
	 	  
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   PaisEmpresaDAO.delete(entity);
	 *   EntityManagerHelper.commit();
	 *   entity = null;
	 * </pre>
	   @param entity PaisEmpresa entity to delete
	 @throws RuntimeException when the operation fails
	 */
    public void delete(PaisEmpresa entity) {
    				EntityManagerHelper.log("deleting PaisEmpresa instance", Level.INFO, null);
	        try {
        	entity = getEntityManager().getReference(PaisEmpresa.class, entity.getId());
            getEntityManager().remove(entity);
            			EntityManagerHelper.log("delete successful", Level.INFO, null);
	        } catch (RuntimeException re) {
        				EntityManagerHelper.log("delete failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    /**
	 Persist a previously saved PaisEmpresa entity and return it or a copy of it to the sender. 
	 A copy of the PaisEmpresa entity parameter is returned when the JPA persistence mechanism has not previously been tracking the updated entity. 
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence
	 store, i.e., database. This method uses the {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge} operation.
	 	 
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   entity = PaisEmpresaDAO.update(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity PaisEmpresa entity to update
	 @return PaisEmpresa the persisted PaisEmpresa entity instance, may not be the same
	 @throws RuntimeException if the operation fails
	 */
    public PaisEmpresa update(PaisEmpresa entity) {
    				EntityManagerHelper.log("updating PaisEmpresa instance", Level.INFO, null);
	        try {
            PaisEmpresa result = getEntityManager().merge(entity);
            			EntityManagerHelper.log("update successful", Level.INFO, null);
	            return result;
        } catch (RuntimeException re) {
        				EntityManagerHelper.log("update failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    public PaisEmpresa findById( Integer id) {
    				EntityManagerHelper.log("finding PaisEmpresa instance with id: " + id, Level.INFO, null);
	        try {
            PaisEmpresa instance = getEntityManager().find(PaisEmpresa.class, id);
            return instance;
        } catch (RuntimeException re) {
        				EntityManagerHelper.log("find failed", Level.SEVERE, re);
	            throw re;
        }
    }    
    

/**
	 * Find all PaisEmpresa entities with a specific property value.  
	 
	  @param propertyName the name of the PaisEmpresa property to query
	  @param value the property value to match
	  	  @return List<PaisEmpresa> found by query
	 */
    @SuppressWarnings("unchecked")
    public List<PaisEmpresa> findByProperty(String propertyName, final Object value
        ) {
    				EntityManagerHelper.log("finding PaisEmpresa instance with property: " + propertyName + ", value: " + value, Level.INFO, null);
			try {
			final String queryString = "select model from PaisEmpresa model where model." 
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
	 * Find all PaisEmpresa entities.
	  	  @return List<PaisEmpresa> all PaisEmpresa entities
	 */
	@SuppressWarnings("unchecked")
	public List<PaisEmpresa> findAll(
		) {
					EntityManagerHelper.log("finding all PaisEmpresa instances", Level.INFO, null);
			try {
			final String queryString = "select model from PaisEmpresa model";
								Query query = getEntityManager().createQuery(queryString);
					return query.getResultList();
		} catch (RuntimeException re) {
						EntityManagerHelper.log("find all failed", Level.SEVERE, re);
				throw re;
		}
	}
	
}