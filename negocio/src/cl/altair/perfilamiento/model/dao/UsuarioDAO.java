package cl.altair.perfilamiento.model.dao;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import cl.mycompany.perfilamiento.model.Empresa;
import cl.mycompany.perfilamiento.model.Usuario;



/**
 	* A data access object (DAO) providing persistence and search support for Usuario entities.
 	 		* Transaction control of the save(), update() and delete() operations must be handled externally by senders of these methods 
 		  or must be manually added to each of these methods for data to be persisted to the JPA datastore.	
 	 * @see com.acepta.perfilamiento.eclipselink.Usuario
  * @author MyEclipse Persistence Tools 
 */

public class UsuarioDAO  {
	//property constants
	public static final String APELLIDO_MATERNO = "apellidoMaterno";
	public static final String APELLIDO_PATERNO = "apellidoPaterno";
	public static final String LOGIN = "login";
	public static final String EMAIL = "email";
	public static final String CLAVE = "clave";
	public static final String SEGUNDO_NOMBRE = "segundoNombre";
	public static final String DNI = "dni";
	public static final String NOMBRE = "nombre";
	public static final String IDESTADO = "idestado";
	public static final String SEXO = "sexo";
	public static final String TIPO = "tipo";
	public static final String DV = "dv";
	public static final String FONO = "fono";
	public static final String MOVIL = "movil";





	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}	
	
		/**
	 Perform an initial save of a previously unsaved Usuario entity. 
	 All subsequent persist actions of this entity should use the #update() method.
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist} operation.
	 	 
	 * <pre> 
	 *   EntityManagerHelper.beginTransaction();
	 *   UsuarioDAO.save(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity Usuario entity to persist
	  @throws RuntimeException when the operation fails
	 */
    public void save(Usuario entity) {
    		EntityManagerHelper.log("saving Usuario instance", Level.INFO, null);
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
	 Delete a persistent Usuario entity.
	  This operation must be performed 
	 within the a database transaction context for the entity's data to be
	 permanently deleted from the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete} operation.
	 	  
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   UsuarioDAO.delete(entity);
	 *   EntityManagerHelper.commit();
	 *   entity = null;
	 * </pre>
	   @param entity Usuario entity to delete
	 @throws RuntimeException when the operation fails
	 */
    public void delete(Usuario entity) {
    		EntityManagerHelper.log("deleting Usuario instance", Level.INFO, null);
	        try {
	        	entity = getEntityManager().getReference(Usuario.class, entity.getId());
	        	getEntityManager().remove(entity);
            	EntityManagerHelper.log("delete successful", Level.INFO, null);
	        } catch (RuntimeException re) {
	        	EntityManagerHelper.closeEntityManager();
        		EntityManagerHelper.log("delete failed", Level.SEVERE, re);
	            throw re;
        } 
    }
    
    /**
	 Persist a previously saved Usuario entity and return it or a copy of it to the sender. 
	 A copy of the Usuario entity parameter is returned when the JPA persistence mechanism has not previously been tracking the updated entity. 
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence
	 store, i.e., database. This method uses the {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge} operation.
	 	 
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   entity = UsuarioDAO.update(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity Usuario entity to update
	 @return Usuario the persisted Usuario entity instance, may not be the same
	 @throws RuntimeException if the operation fails
	 */
    public Usuario update(Usuario entity) {
    		EntityManagerHelper.log("updating Usuario instance", Level.INFO, null);
	        try {
	        	Usuario result = getEntityManager().merge(entity);
            	EntityManagerHelper.log("update successful", Level.INFO, null);
	            return result;
        } catch (RuntimeException re) {
        	EntityManagerHelper.closeEntityManager();
        	EntityManagerHelper.log("update failed", Level.SEVERE, re);
	        throw re;
        } 
    }
    
    public Usuario findById( Integer id) {
    		EntityManagerHelper.log("finding Usuario instance with id: " + id, Level.INFO, null);
	        try {
	        	Usuario instance = getEntityManager().find(Usuario.class, id);
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
	 * Find all Usuario entities with a specific property value.  
	 
	  @param propertyName the name of the Usuario property to query
	  @param value the property value to match
	  	  @return List<Usuario> found by query
	 */
    @SuppressWarnings("unchecked")
    public List<Usuario> findByProperty(String propertyName, final Object value) {
    		EntityManagerHelper.log("finding Usuario instance with property: " + propertyName + ", value: " + value, Level.INFO, null);
			try {
			final String queryString = "select model from Usuario model where model." 
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
	public List<Usuario> findByApellidoMaterno(Object apellidoMaterno
	) {
		return findByProperty(APELLIDO_MATERNO, apellidoMaterno
		);
	}
	
	public List<Usuario> findByApellidoPaterno(Object apellidoPaterno
	) {
		return findByProperty(APELLIDO_PATERNO, apellidoPaterno
		);
	}
	
	public List<Usuario> findByLogin(Object login
	) {
		return findByProperty(LOGIN, login
		);
	}
	
	public List<Usuario> findByEmail(Object email
	) {
		return findByProperty(EMAIL, email
		);
	}
	
	public List<Usuario> findByClave(Object clave
	) {
		return findByProperty(CLAVE, clave
		);
	}
	
	public List<Usuario> findBySegundoNombre(Object segundoNombre
	) {
		return findByProperty(SEGUNDO_NOMBRE, segundoNombre
		);
	}
	
	public List<Usuario> findByDni(Object dni
	) {
		return findByProperty(DNI, dni
		);
	}
	
	public List<Usuario> findByNombre(Object nombre
	) {
		return findByProperty(NOMBRE, nombre
		);
	}
	
	public List<Usuario> findBySexo(Object sexo
	) {
		return findByProperty(SEXO, sexo
		);
	}
	
	public List<Usuario> findByTipo(Object tipo
	) {
		return findByProperty(TIPO, tipo
		);
	}
	
	public List<Usuario> findByDv(Object dv
	) {
		return findByProperty(DV, dv
		);
	}
	
	public List<Usuario> findByFono(Object fono
	) {
		return findByProperty(FONO, fono
		);
	}
	
	public List<Usuario> findByMovil(Object movil
	) {
		return findByProperty(MOVIL, movil
		);
	}
	
	
	/**
	 * Find all Usuario entities.
	  	  @return List<Usuario> all Usuario entities
	 */
	@SuppressWarnings("unchecked")
	public List<Usuario> findAll() {
			EntityManagerHelper.log("finding all Usuario instances", Level.INFO, null);
			try {
				final String queryString = "select model from Usuario model";
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
	/**
	 * Find all Usuario entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Usuario property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Usuario> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Usuario> findByProperty(String propertyName,
		final Object value, final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding Usuario instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Usuario model where model."
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
        	EntityManagerHelper.closeEntityManager();
			EntityManagerHelper.log("find by property name failed", 	Level.SEVERE, re);
			throw re;
		} finally{
        	EntityManagerHelper.closeEntityManager();
		}
	}

	public List<Usuario> findByApellidoMaterno(Object apellidoMaterno,
			int... rowStartIdxAndCount) {
		return findByProperty(APELLIDO_MATERNO, apellidoMaterno,
				rowStartIdxAndCount);
	}

	public List<Usuario> findByApellidoPaterno(Object apellidoPaterno,
			int... rowStartIdxAndCount) {
		return findByProperty(APELLIDO_PATERNO, apellidoPaterno,
				rowStartIdxAndCount);
	}

	public List<Usuario> findByLogin(Object login, int... rowStartIdxAndCount) {
		return findByProperty(LOGIN, login, rowStartIdxAndCount);
	}

	public List<Usuario> findByEmail(Object email, int... rowStartIdxAndCount) {
		return findByProperty(EMAIL, email, rowStartIdxAndCount);
	}

	public List<Usuario> findByClave(Object clave, int... rowStartIdxAndCount) {
		return findByProperty(CLAVE, clave, rowStartIdxAndCount);
	}

	public List<Usuario> findBySegundoNombre(Object segundoNombre,
			int... rowStartIdxAndCount) {
		return findByProperty(SEGUNDO_NOMBRE, segundoNombre,
				rowStartIdxAndCount);
	}

	public List<Usuario> findByDni(Object dni, int... rowStartIdxAndCount) {
		return findByProperty(DNI, dni, rowStartIdxAndCount);
	}

	public List<Usuario> findByNombre(Object nombre, int... rowStartIdxAndCount) {
		return findByProperty(NOMBRE, nombre, rowStartIdxAndCount);
	}

	public List<Usuario> findByIdestado(Object idestado,
			int... rowStartIdxAndCount) {
		return findByProperty(IDESTADO, idestado, rowStartIdxAndCount);
	}

	public List<Usuario> findBySexo(Object sexo, int... rowStartIdxAndCount) {
		return findByProperty(SEXO, sexo, rowStartIdxAndCount);
	}

	public List<Usuario> findByTipo(Object tipo, int... rowStartIdxAndCount) {
		return findByProperty(TIPO, tipo, rowStartIdxAndCount);
	}

	public List<Usuario> findByDv(Object dv, int... rowStartIdxAndCount) {
		return findByProperty(DV, dv, rowStartIdxAndCount);
	}

	public List<Usuario> findByFono(Object fono, int... rowStartIdxAndCount) {
		return findByProperty(FONO, fono, rowStartIdxAndCount);
	}

	public List<Usuario> findByMovil(Object movil, int... rowStartIdxAndCount) {
		return findByProperty(MOVIL, movil, rowStartIdxAndCount);
	}

	
	/**
	 * Find all Usuario entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Usuario> all Usuario entities
	 */
	@SuppressWarnings("unchecked")
	public List<Usuario> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding all Usuario instances", Level.INFO, null);
		try {
			final String queryString = "select model from Usuario model";
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
        	EntityManagerHelper.closeEntityManager();
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		} finally{
        	EntityManagerHelper.closeEntityManager();
		}
	}

	/**
	 * Encuentra todos los usuarios que pertenecen a una empresa.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Usuario> los usuarios que pertenecen a la empresa
	 */
	@SuppressWarnings("unchecked")
	public List<Usuario> findEnterpriseUsers(Integer rutUsuario, final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("Encontrando los usuarios de la empresa RUT: " + rutUsuario.toString(), Level.INFO,	null);
		try {
			final String queryString = "select * from " + 
			"(select idusuario from " + 
				"(select p.idusuariorol from " +
					"(select ea.id,ea.idempresa from " +  
						"(SELECT * from empresa where rut=?1) e " + 
					"join empresa_app ea on ea.idempresa = e.id) x " +
						"join perfil p on p.idempresaapp = x.\"id\" GROUP BY p.idusuariorol) y " +
							"join usuario_rol ur on ur.\"id\" = y.idusuariorol) z " +
								"join usuario u on u.\"id\" = z.idusuario";
			Query query = getEntityManager().createNativeQuery(queryString, Usuario.class);
			query.setParameter(1, rutUsuario);
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
        	EntityManagerHelper.closeEntityManager();
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		} finally{
        	EntityManagerHelper.closeEntityManager();
		}
	}

	/**
	 * Encuentra todos los usuarios que pertenecen a una empresa.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Usuario> los usuarios que pertenecen a la empresa
	 */
	@SuppressWarnings("unchecked")
	public List<Usuario> findEnterpriseUsers(Integer rutUsuario) {
		EntityManagerHelper.log("Encontrando los usuarios de la empresa RUT: " + rutUsuario.toString(), Level.INFO, null);
		try {
			final String queryString = "select * from " + 
			"(select idusuario from " + 
				"(select p.idusuariorol from " +
					"(select ea.id,ea.idempresa from " +  
						"(SELECT * from empresa where rut=?1) e " + 
					"join empresa_app ea on ea.idempresa = e.id) x " +
						"join perfil p on p.idempresaapp = x.\"id\" GROUP BY p.idusuariorol) y " +
							"join usuario_rol ur on ur.\"id\" = y.idusuariorol) z " +
								"join usuario u on u.\"id\" = z.idusuario WHERE idestado = 1";
			Query query = getEntityManager().createNativeQuery(queryString, Usuario.class);
			query.setParameter(1, rutUsuario);
			return query.getResultList();			
		} catch (RuntimeException re) {
        	EntityManagerHelper.closeEntityManager();
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		} finally{
        	EntityManagerHelper.closeEntityManager();
		}
	}

	/**
	 * Encuentra todos los usuarios que pertenecen a una empresa.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Usuario> las empresas que pertenecen al usuario
	 */
	@SuppressWarnings("unchecked")
	public List<Empresa> findEnterprise(Integer rutEmpresa, final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("Encontrando los usuarios de la empresa RUT: " + rutEmpresa.toString(), Level.INFO,	null);
		try {
			final String queryString = "select empr.id, empr.nombre,empr.idholding,empr.rut, empr.dv, empr.telefono from " + 
			"(select * from " + 
				"(select ea.idempresa from " +
					"(select p.id, p.idempresaapp from " +
						"(select ur.id from " +
							"(select u.id from usuario u where u.dni=?1) usr " +
							"join usuario_rol ur on ur.idusuario = usr.\"id\") usrrol " + 
						"join perfil p on p.idusuariorol = usrrol.\"id\") per " +
					"join empresa_app ea on ea.id = per.idempresaapp GROUP BY ea.idempresa) emp) identidad " +
			"join empresa empr on empr.id = identidad.idempresa order by empr.id ";
			Query query = getEntityManager().createNativeQuery(queryString, Empresa.class);
			query.setParameter(1, rutEmpresa);
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
        	EntityManagerHelper.closeEntityManager();
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		} finally{
        	EntityManagerHelper.closeEntityManager();
		}
	}

	/**
	 * Encuentra todos los usuarios que pertenecen a una empresa.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Empresa> las empresas que pertenecen al usuario
	 */
	@SuppressWarnings("unchecked")
	public List<Empresa> findEnterprise(Integer rutEmpresa) {
		EntityManagerHelper.log("Encontrando los usuarios de la empresa RUT: " + rutEmpresa.toString(), Level.INFO, null);
		try {
			final String queryString = "select empr.id, empr.nombre,empr.idholding,empr.rut, empr.dv, empr.telefono from " + 
			"(select * from " + 
				"(select ea.idempresa from " +
					"(select p.id, p.idempresaapp from " +
						"(select ur.id from " +
							"(select u.id from usuario u where u.dni=?1) usr " +
							"join usuario_rol ur on ur.idusuario = usr.\"id\") usrrol " + 
						"join perfil p on p.idusuariorol = usrrol.\"id\") per " +
					"join empresa_app ea on ea.id = per.idempresaapp GROUP BY ea.idempresa) emp) identidad " +
			"join empresa empr on empr.id = identidad.idempresa order by empr.id ";
			Query query = getEntityManager().createNativeQuery(queryString, Empresa.class);
			query.setParameter(1, rutEmpresa);
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