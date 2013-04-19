package cl.altair.perfilamiento.model.dao;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import cl.mycompany.perfilamiento.model.*;

/**
 * A data access object (DAO) providing persistence and search support for
 * Aplicacion entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see com.acepta.perfilamiento.eclipselink.Aplicacion
 * @author MyEclipse Persistence Tools
 */

public class AplicacionDAO {
	// property constants
	public static final String NOMBRE = "nombre";
	public static final String WEBROOT = "webroot";
	public static final String INICIO = "inicio";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Aplicacion entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * AplicacionDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Aplicacion entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Aplicacion entity) {
		EntityManagerHelper.log("saving Aplicacion instance", Level.INFO, null);
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
	 * Delete a persistent Aplicacion entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * AplicacionDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Aplicacion entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Aplicacion entity) {
		EntityManagerHelper.log("deleting Aplicacion instance", Level.INFO,
				null);
		try {
			entity = getEntityManager().getReference(Aplicacion.class,
					entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
        	EntityManagerHelper.closeEntityManager();
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Aplicacion entity and return it or a copy of
	 * it to the sender. A copy of the Aplicacion entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = AplicacionDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Aplicacion entity to update
	 * @return Aplicacion the persisted Aplicacion entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Aplicacion update(Aplicacion entity) {
		EntityManagerHelper.log("updating Aplicacion instance", Level.INFO,
				null);
		try {
			Aplicacion result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
        	EntityManagerHelper.closeEntityManager();
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Aplicacion findById(Integer id) {
		EntityManagerHelper.log("finding Aplicacion instance with id: " + id,
				Level.INFO, null);
		try {
			Aplicacion instance = getEntityManager().find(Aplicacion.class, id);
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
	 * Find all Aplicacion entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Aplicacion property to query
	 * @param value
	 *            the property value to match
	 * @return List<Aplicacion> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Aplicacion> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log("finding Aplicacion instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Aplicacion model where model."
					+ propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
        	EntityManagerHelper.closeEntityManager();
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		} finally{
        	EntityManagerHelper.closeEntityManager();
		}
	}

	public List<Aplicacion> findByNombre(Object nombre) {
		return findByProperty(NOMBRE, nombre);
	}

	public List<Aplicacion> findByWebroot(Object webroot) {
		return findByProperty(WEBROOT, webroot);
	}

	public List<Aplicacion> findByInicio(Object inicio) {
		return findByProperty(INICIO, inicio);
	}

	/**
	 * Find all Aplicacion entities.
	 * 
	 * @return List<Aplicacion> all Aplicacion entities
	 */
	@SuppressWarnings("unchecked")
	public List<Aplicacion> findAll() {
		EntityManagerHelper.log("finding all Aplicacion instances", Level.INFO, null);
		try {
			final String queryString = "select model from Aplicacion model";
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