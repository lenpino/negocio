package cl.mycompany.perfilamiento.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the unidad database table.
 * 
 */
@Entity
public class Unidad implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Id
	@SequenceGenerator(name="UNIDAD_ID_GENERATOR", sequenceName="ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="UNIDAD_ID_GENERATOR")
	private Integer id;

	private String nombre;

	//bi-directional many-to-one association to Empresa
	@ManyToOne
	@JoinColumn(name="idempresa")
	private Empresa empresa;

	//bi-directional many-to-one association to Unidad
	@ManyToOne
	@JoinColumn(name="idpadre")
	private Unidad unidad;

	//bi-directional many-to-one association to Unidad
	@OneToMany(mappedBy="unidad", fetch=FetchType.EAGER)
	private List<Unidad> unidads;

	//bi-directional many-to-one association to UsuarioUnidad
	@OneToMany(mappedBy="unidad", fetch=FetchType.EAGER)
	private List<UsuarioUnidad> usuarioUnidads;

	public Unidad() {
	}

	/** minimal constructor */
    public Unidad(Integer id, Unidad unidad, String nombre) {
        this.id = id;
        this.unidad = unidad;
        this.nombre = nombre;
    }
    
    /** full constructor */
    public Unidad(Integer id, Empresa empresa, Unidad unidad, String nombre, List<UsuarioUnidad> usuarioUnidads, List<Unidad> unidads) {
        this.id = id;
        this.empresa = empresa;
        this.unidad = unidad;
        this.nombre = nombre;
        this.usuarioUnidads = usuarioUnidads;
        this.unidads = unidads;
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

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Unidad getUnidad() {
		return this.unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

	public List<Unidad> getUnidads() {
		return this.unidads;
	}

	public void setUnidads(List<Unidad> unidads) {
		this.unidads = unidads;
	}

	public List<UsuarioUnidad> getUsuarioUnidads() {
		return this.usuarioUnidads;
	}

	public void setUsuarioUnidads(List<UsuarioUnidad> usuarioUnidads) {
		this.usuarioUnidads = usuarioUnidads;
	}

}