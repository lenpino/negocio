package cl.altair.modelo.portal;

import cl.altair.perfilamiento.model.dao.EntityManagerHelper;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * Registro entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see com.acepta.certificado.publico.modelo.Registro
 * @author MyEclipse Persistence Tools
 */

public class RegistroDAO {
	// property constants
	public static final String RUT = "rut";
	public static final String DV = "dv";
	public static final String NOMBRES = "nombres";
	public static final String APELLIDO = "apellido";
	public static final String APELLIDO_MATERNO = "apellidoMaterno";
	public static final String EMAIL = "email";
	public static final String TELEFONO = "telefono";
	public static final String RUT_EMPRESA = "rutEmpresa";
	public static final String DV_EMPRESA = "dvEmpresa";
	public static final String GIRO = "giro";
	public static final String RAZON_SOCIAL = "razonSocial";
	public static final String DIRECCION = "direccion";
	public static final String COMUNA = "comuna";
	public static final String CIUDAD = "ciudad";
	public static final String CODIGO_AUDITORIA = "codigoAuditoria";
	public static final String TIPO = "tipo";
	public static final String SERIE = "serie";
	public static final String ESTADO = "estado";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Registro entity. All
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
	 *            Registro entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Registro entity) {
		EntityManagerHelper.log("saving Registro instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Registro entity. This operation must be performed
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
	 *            Registro entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Registro entity) {
		EntityManagerHelper.log("deleting Registro instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Registro.class,
					entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Registro entity and return it or a copy of it
	 * to the sender. A copy of the Registro entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = RegistroDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Registro entity to update
	 * @return Registro the persisted Registro entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Registro update(Registro entity) {
		EntityManagerHelper.log("updating Registro instance", Level.INFO, null);
		try {
			Registro result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Registro findById(Integer id) {
		EntityManagerHelper.log("finding Registro instance with id: " + id,
				Level.INFO, null);
		try {
			Registro instance = getEntityManager().find(Registro.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}
	
	/**
	 * Find all Registro entities with a specific property value.  
	 
	  @param propertyName the name of the Registro property to query
	  @param value the property value to match
	  	  @return List<Registro> found by query
	 */
    @SuppressWarnings("unchecked")
    public List<Registro> findByProperty(String propertyName, final Object value) {
    		EntityManagerHelper.log("finding Registro instance with property: " + propertyName + ", value: " + value, Level.INFO, null);
			try {
			final String queryString = "select model from Registro model where model." 
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

	public List<Registro> findByRut(Object rut) {
		return findByProperty(RUT, rut);
	}

	/**
	 * Find all Registro entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Registro property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Registro> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Registro> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding Registro instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Registro model where model."
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
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	public List<Registro> findByRut(Object rut, int... rowStartIdxAndCount) {
		return findByProperty(RUT, rut, rowStartIdxAndCount);
	}

	public List<Registro> findByDv(Object dv, int... rowStartIdxAndCount) {
		return findByProperty(DV, dv, rowStartIdxAndCount);
	}

	public List<Registro> findByNombres(Object nombres,
			int... rowStartIdxAndCount) {
		return findByProperty(NOMBRES, nombres, rowStartIdxAndCount);
	}

	public List<Registro> findByApellido(Object apellido,
			int... rowStartIdxAndCount) {
		return findByProperty(APELLIDO, apellido, rowStartIdxAndCount);
	}

	public List<Registro> findByApellidoMaterno(Object apellidoMaterno,
			int... rowStartIdxAndCount) {
		return findByProperty(APELLIDO_MATERNO, apellidoMaterno,
				rowStartIdxAndCount);
	}

	public List<Registro> findByEmail(Object email, int... rowStartIdxAndCount) {
		return findByProperty(EMAIL, email, rowStartIdxAndCount);
	}

	public List<Registro> findByTelefono(Object telefono,
			int... rowStartIdxAndCount) {
		return findByProperty(TELEFONO, telefono, rowStartIdxAndCount);
	}

	public List<Registro> findByRutEmpresa(Object rutEmpresa,
			int... rowStartIdxAndCount) {
		return findByProperty(RUT_EMPRESA, rutEmpresa, rowStartIdxAndCount);
	}

	public List<Registro> findByDvEmpresa(Object dvEmpresa,
			int... rowStartIdxAndCount) {
		return findByProperty(DV_EMPRESA, dvEmpresa, rowStartIdxAndCount);
	}

	public List<Registro> findByGiro(Object giro, int... rowStartIdxAndCount) {
		return findByProperty(GIRO, giro, rowStartIdxAndCount);
	}

	public List<Registro> findByRazonSocial(Object razonSocial,
			int... rowStartIdxAndCount) {
		return findByProperty(RAZON_SOCIAL, razonSocial, rowStartIdxAndCount);
	}

	public List<Registro> findByDireccion(Object direccion,
			int... rowStartIdxAndCount) {
		return findByProperty(DIRECCION, direccion, rowStartIdxAndCount);
	}

	public List<Registro> findByComuna(Object comuna,
			int... rowStartIdxAndCount) {
		return findByProperty(COMUNA, comuna, rowStartIdxAndCount);
	}

	public List<Registro> findByCiudad(Object ciudad,
			int... rowStartIdxAndCount) {
		return findByProperty(CIUDAD, ciudad, rowStartIdxAndCount);
	}

	public List<Registro> findByCodigoAuditoria(Object codigoAuditoria,
			int... rowStartIdxAndCount) {
		return findByProperty(CODIGO_AUDITORIA, codigoAuditoria,
				rowStartIdxAndCount);
	}

	public List<Registro> findByTipo(Object tipo, int... rowStartIdxAndCount) {
		return findByProperty(TIPO, tipo, rowStartIdxAndCount);
	}

	public List<Registro> findBySerie(Object serie, int... rowStartIdxAndCount) {
		return findByProperty(SERIE, serie, rowStartIdxAndCount);
	}

	public List<Registro> findByEstado(Object estado,
			int... rowStartIdxAndCount) {
		return findByProperty(ESTADO, estado, rowStartIdxAndCount);
	}

	/**
	 * Find all Registro entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Registro> all Registro entities
	 */
	@SuppressWarnings("unchecked")
	public List<Registro> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding all Registro instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Registro model";
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
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}