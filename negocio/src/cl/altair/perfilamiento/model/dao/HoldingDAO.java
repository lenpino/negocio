package cl.altair.perfilamiento.model.dao;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import cl.mycompany.perfilamiento.model.Holding;

/**
 	* A data access object (DAO) providing persistence and search support for Holding entities.
 	 		* Transaction control of the save(), update() and delete() operations must be handled externally by senders of these methods 
 		  or must be manually added to each of these methods for data to be persisted to the JPA datastore.	
 	 * @see com.acepta.perfilamiento.eclipselink.Holding
  * @author MyEclipse Persistence Tools 
 */

public class HoldingDAO  {
	//property constants
	public static final String NOMBRE = "nombre";
	public static final String RUT = "rut";
	public static final String DV = "dv";





	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}	
	
		/**
	 Perform an initial save of a previously unsaved Holding entity. 
	 All subsequent persist actions of this entity should use the #update() method.
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist} operation.
	 	 
	 * <pre> 
	 *   EntityManagerHelper.beginTransaction();
	 *   HoldingDAO.save(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity Holding entity to persist
	  @throws RuntimeException when the operation fails
	 */
    public void save(Holding entity) {
    				EntityManagerHelper.log("saving Holding instance", Level.INFO, null);
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
	 Delete a persistent Holding entity.
	  This operation must be performed 
	 within the a database transaction context for the entity's data to be
	 permanently deleted from the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete} operation.
	 	  
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   HoldingDAO.delete(entity);
	 *   EntityManagerHelper.commit();
	 *   entity = null;
	 * </pre>
	   @param entity Holding entity to delete
	 @throws RuntimeException when the operation fails
	 */
    public void delete(Holding entity) {
    				EntityManagerHelper.log("deleting Holding instance", Level.INFO, null);
	        try {
        	entity = getEntityManager().getReference(Holding.class, entity.getId());
            getEntityManager().remove(entity);
            			EntityManagerHelper.log("delete successful", Level.INFO, null);
	        } catch (RuntimeException re) {
	        	EntityManagerHelper.closeEntityManager();
        				EntityManagerHelper.log("delete failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    /**
	 Persist a previously saved Holding entity and return it or a copy of it to the sender. 
	 A copy of the Holding entity parameter is returned when the JPA persistence mechanism has not previously been tracking the updated entity. 
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence
	 store, i.e., database. This method uses the {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge} operation.
	 	 
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   entity = HoldingDAO.update(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity Holding entity to update
	 @return Holding the persisted Holding entity instance, may not be the same
	 @throws RuntimeException if the operation fails
	 */
    public Holding update(Holding entity) {
    				EntityManagerHelper.log("updating Holding instance", Level.INFO, null);
	        try {
            Holding result = getEntityManager().merge(entity);
            			EntityManagerHelper.log("update successful", Level.INFO, null);
	            return result;
        } catch (RuntimeException re) {
        	EntityManagerHelper.closeEntityManager();
        				EntityManagerHelper.log("update failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    public Holding findById( Integer id) {
    				EntityManagerHelper.log("finding Holding instance with id: " + id, Level.INFO, null);
	        try {
            Holding instance = getEntityManager().find(Holding.class, id);
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
	 * Find all Holding entities with a specific property value.  
	 
	  @param propertyName the name of the Holding property to query
	  @param value the property value to match
	  	  @return List<Holding> found by query
	 */
    @SuppressWarnings("unchecked")
    public List<Holding> findByProperty(String propertyName, final Object value
        ) {
    				EntityManagerHelper.log("finding Holding instance with property: " + propertyName + ", value: " + value, Level.INFO, null);
			try {
			final String queryString = "select model from Holding model where model." 
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
	public List<Holding> findByNombre(Object nombre
	) {
		return findByProperty(NOMBRE, nombre
		);
	}
	
	public List<Holding> findByRut(Object rut
	) {
		return findByProperty(RUT, rut
		);
	}
	
	public List<Holding> findByDv(Object dv
	) {
		return findByProperty(DV, dv
		);
	}
	
	
	/**
	 * Find all Holding entities.
	  	  @return List<Holding> all Holding entities
	 */
	@SuppressWarnings("unchecked")
	public List<Holding> findAll(
		) {
					EntityManagerHelper.log("finding all Holding instances", Level.INFO, null);
			try {
			final String queryString = "select model from Holding model";
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