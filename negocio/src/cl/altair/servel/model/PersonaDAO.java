package cl.altair.servel.model;

import cl.altair.servel.model.EntityManagerHelperServel;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class PersonaDAO {

	public static final String RUT = "rut";
	public static final String DV = "dv";
	public static final String NOMBRE = "nombre";
	public static final String SEGUNDONOMBRE = "segundonombre";
	public static final String NOMBRELARGO = "nombrelargo";
	public static final String APELLIDO = "apellido";
	public static final String APELLIDO_MATERNO = "apellidomaterno";
	public static final String DIRECCION = "direccion";
	public static final String SEXO = "sexo";
	public static final String CIRCUNSCRIPCION = "circunscripcion";


	
	private EntityManager getEntityManager() {
		return EntityManagerHelperServel.getEntityManager();
	}
	
	/**
	 * Perform an initial save of a previously unsaved Persona entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * RegistroDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Persona entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Persona entity) {
		EntityManagerHelperServel.log("saving Persona instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelperServel.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelperServel.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Persona entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * RegistroDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Persona entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Persona entity) {
		EntityManagerHelperServel.log("deleting Persona instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Persona.class,
					entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelperServel.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelperServel.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Persona entity and return it or a copy of it
	 * to the sender. A copy of the Persona entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = PersonaDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Persona entity to update
	 * @return Persona the persisted Persona entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Persona update(Persona entity) {
		EntityManagerHelperServel.log("updating Persona instance", Level.INFO, null);
		try {
			Persona result = getEntityManager().merge(entity);
			EntityManagerHelperServel.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelperServel.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Persona findById(Integer id) {
		EntityManagerHelperServel.log("finding Persona instance with id: " + id,
				Level.INFO, null);
		try {
			Persona instance = getEntityManager().find(Persona.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelperServel.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}
	
	/**
	 * Find all Persona entities with a specific property value.  
	 
	  @param propertyName the name of the Persona property to query
	  @param value the property value to match
	  	  @return List<Persona> found by query
	 */
    @SuppressWarnings("unchecked")
    public List<Persona> findByProperty(String propertyName, final Object value) {
    		EntityManagerHelperServel.log("finding Persona instance with property: " + propertyName + ", value: " + value, Level.INFO, null);
			try {
			final String queryString = "select model from Persona model where model." 
			 						+ propertyName + "= :propertyValue";
								Query query = getEntityManager().createQuery(queryString);
					query.setParameter("propertyValue", value);
					return query.getResultList();
		} catch (RuntimeException re) {
        	EntityManagerHelperServel.closeEntityManager();
				EntityManagerHelperServel.log("find by property name failed", Level.SEVERE, re);
				throw re;
		} finally{
        	EntityManagerHelperServel.closeEntityManager();
		}
	}			

	public List<Persona> findByRut(Object rut) {
		return findByProperty(RUT, rut);
	}

	/**
	 * Find all Persona entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Persona property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Persona> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Persona> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		EntityManagerHelperServel.log("finding Persona instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Persona model where model."
					+ propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelperServel.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	public List<Persona> findByRut(Object rut, int... rowStartIdxAndCount) {
		return findByProperty(RUT, rut, rowStartIdxAndCount);
	}
	public List<Persona> findByDv(Object dv, int... rowStartIdxAndCount) {
		return findByProperty(DV, dv, rowStartIdxAndCount);
	}

	public List<Persona> findByNombreLargo(Object nombrelargo,
			int... rowStartIdxAndCount) {
		return findByProperty(NOMBRELARGO, nombrelargo, rowStartIdxAndCount);
	}

	public List<Persona> findBySegundoNombre(Object nombre,
			int... rowStartIdxAndCount) {
		return findByProperty(SEGUNDONOMBRE, nombre, rowStartIdxAndCount);
	}

	public List<Persona> findByNombres(Object nombre,
			int... rowStartIdxAndCount) {
		return findByProperty(NOMBRE, nombre, rowStartIdxAndCount);
	}

	public List<Persona> findByApellido(Object apellido,
			int... rowStartIdxAndCount) {
		return findByProperty(APELLIDO, apellido, rowStartIdxAndCount);
	}

	public List<Persona> findByApellidoMaterno(Object apellidoMaterno,
			int... rowStartIdxAndCount) {
		return findByProperty(APELLIDO_MATERNO, apellidoMaterno,
				rowStartIdxAndCount);
	}
	
	public List<Persona> findByDireccion(Object direccion,
			int... rowStartIdxAndCount) {
		return findByProperty(DIRECCION, direccion, rowStartIdxAndCount);
	}
	
	public List<Persona> findBySexo(Object sexo,
			int... rowStartIdxAndCount) {
		return findByProperty(SEXO, sexo, rowStartIdxAndCount);
	}
	
	public List<Persona> findByCircunscripcion(Object circunscripcion,
			int... rowStartIdxAndCount) {
		return findByProperty(CIRCUNSCRIPCION, circunscripcion, rowStartIdxAndCount);
	}
	
	/**
	 * Find all Persona entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Persona> all Persona entities
	 */
	@SuppressWarnings("unchecked")
	public List<Persona> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelperServel.log("finding all Persona instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Persona model";
			Query query = getEntityManager().createQuery(queryString);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelperServel.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}
