package cl.mycompany.perfilamiento.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the pais database table.
 * 
 */
@Entity
@Table(name="pais")
public class Pais implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PAIS_ID_GENERATOR", sequenceName="ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PAIS_ID_GENERATOR")
	private Integer id;

	private String nombre;

	//bi-directional many-to-one association to PaisEmpresa
	@OneToMany(mappedBy="pai", fetch=FetchType.EAGER)
	private List<PaisEmpresa> paisEmpresas;

	public Pais() {
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

	public List<PaisEmpresa> getPaisEmpresas() {
		return this.paisEmpresas;
	}

	public void setPaisEmpresas(List<PaisEmpresa> paisEmpresas) {
		this.paisEmpresas = paisEmpresas;
	}

}