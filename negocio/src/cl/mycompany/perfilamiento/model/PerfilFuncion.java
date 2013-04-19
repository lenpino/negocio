package cl.mycompany.perfilamiento.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the perfil_funcion database table.
 * 
 */
@Entity
@Table(name="perfil_funcion")
public class PerfilFuncion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PERFIL_FUNCION_ID_GENERATOR", sequenceName="ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PERFIL_FUNCION_ID_GENERATOR")
	private Integer id;

	//bi-directional many-to-one association to Funcion
	@ManyToOne
	@JoinColumn(name="idfuncion")
	private Funcion funcion;

	//bi-directional many-to-one association to Perfil
	@ManyToOne
	@JoinColumn(name="idperfil")
	private Perfil perfil;

	public PerfilFuncion() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Funcion getFuncion() {
		return this.funcion;
	}

	public void setFuncion(Funcion funcion) {
		this.funcion = funcion;
	}

	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

}