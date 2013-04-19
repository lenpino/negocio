package cl.mycompany.perfilamiento.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the aplicacion database table.
 * 
 */
@Entity
public class Aplicacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="APLICACION_ID_GENERATOR", sequenceName="ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="APLICACION_ID_GENERATOR")
	private Integer id;
	private String nombre;
	private String webroot;
	private String inicio;

	//bi-directional many-to-one association to AppFuncion
	@OneToMany(mappedBy="aplicacion", fetch=FetchType.EAGER)
	private List<AppFuncion> appFuncions;

	//bi-directional many-to-one association to EmpresaApp
	@OneToMany(mappedBy="aplicacion", fetch=FetchType.EAGER)
	private List<EmpresaApp> empresaApps;

	public Aplicacion() {
	}
	
	/** minimal constructor */
    public Aplicacion(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getWebroot() {
		return this.webroot;
	}

	public void setWebroot(String webroot) {
		this.webroot = webroot;
	}
	
	public String getInicio() {
		return this.inicio;
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	public List<AppFuncion> getAppFuncions() {
		return this.appFuncions;
	}

	public void setAppFuncions(List<AppFuncion> appFuncions) {
		this.appFuncions = appFuncions;
	}

	public List<EmpresaApp> getEmpresaApps() {
		return this.empresaApps;
	}

	public void setEmpresaApps(List<EmpresaApp> empresaApps) {
		this.empresaApps = empresaApps;
	}

}