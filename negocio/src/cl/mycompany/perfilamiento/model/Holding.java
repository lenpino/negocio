package cl.mycompany.perfilamiento.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the holding database table.
 * 
 */
@Entity
public class Holding implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="HOLDING_ID_GENERATOR", sequenceName="ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HOLDING_ID_GENERATOR")
	private Integer id;

	private String dv;

	private String nombre;

	private Integer rut;

	//bi-directional many-to-one association to Empresa
	@OneToMany(mappedBy="holding", fetch=FetchType.EAGER)
	private List<Empresa> empresas;

	public Holding() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDv() {
		return this.dv;
	}

	public void setDv(String dv) {
		this.dv = dv;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getRut() {
		return this.rut;
	}

	public void setRut(Integer rut) {
		this.rut = rut;
	}

	public List<Empresa> getEmpresas() {
		return this.empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

}