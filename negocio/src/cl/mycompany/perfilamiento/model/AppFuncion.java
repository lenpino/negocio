package cl.mycompany.perfilamiento.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the app_funcion database table.
 * 
 */
@Entity
@Table(name="app_funcion")
public class AppFuncion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="APP_FUNCION_ID_GENERATOR", sequenceName="ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="APP_FUNCION_ID_GENERATOR")
	private Integer id;

	//bi-directional many-to-one association to Aplicacion
	@ManyToOne
	@JoinColumn(name="idaplicacion")
	private Aplicacion aplicacion;

	//bi-directional many-to-one association to Funcion
	@ManyToOne
	@JoinColumn(name="idfuncion")
	private Funcion funcion;

	public AppFuncion() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Aplicacion getAplicacion() {
		return this.aplicacion;
	}

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}

	public Funcion getFuncion() {
		return this.funcion;
	}

	public void setFuncion(Funcion funcion) {
		this.funcion = funcion;
	}

}