package cl.mycompany.perfilamiento.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the funcion database table.
 * 
 */
@Entity
public class Funcion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="FUNCION_ID_GENERATOR", sequenceName="ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FUNCION_ID_GENERATOR")
	private Integer id;

	private String nombre;

	//bi-directional many-to-one association to AppFuncion
	@OneToMany(mappedBy="funcion", fetch=FetchType.EAGER)
	private List<AppFuncion> appFuncions;

	//bi-directional many-to-one association to Funcion
	@ManyToOne
	@JoinColumn(name="idpadre")
	private Funcion funcion;

	//bi-directional many-to-one association to Funcion
	@OneToMany(mappedBy="funcion", fetch=FetchType.EAGER)
	private List<Funcion> funcions;

	//bi-directional many-to-one association to PerfilFuncion
	@OneToMany(mappedBy="funcion", fetch=FetchType.EAGER)
	private List<PerfilFuncion> perfilFuncions;

	public Funcion() {
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

	public List<AppFuncion> getAppFuncions() {
		return this.appFuncions;
	}

	public void setAppFuncions(List<AppFuncion> appFuncions) {
		this.appFuncions = appFuncions;
	}

	public Funcion getFuncion() {
		return this.funcion;
	}

	public void setFuncion(Funcion funcion) {
		this.funcion = funcion;
	}

	public List<Funcion> getFuncions() {
		return this.funcions;
	}

	public void setFuncions(List<Funcion> funcions) {
		this.funcions = funcions;
	}

	public List<PerfilFuncion> getPerfilFuncions() {
		return this.perfilFuncions;
	}

	public void setPerfilFuncions(List<PerfilFuncion> perfilFuncions) {
		this.perfilFuncions = perfilFuncions;
	}

}