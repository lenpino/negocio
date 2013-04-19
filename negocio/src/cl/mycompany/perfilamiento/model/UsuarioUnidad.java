package cl.mycompany.perfilamiento.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usuario_unidad database table.
 * 
 */
@Entity
@Table(name="usuario_unidad")
public class UsuarioUnidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USUARIO_UNIDAD_ID_GENERATOR", sequenceName="ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USUARIO_UNIDAD_ID_GENERATOR")
	private Integer id;

	//bi-directional many-to-one association to Unidad
	@ManyToOne
	@JoinColumn(name="idunidad")
	private Unidad unidad;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="idusuario")
	private Usuario usuario;

	public UsuarioUnidad() {
	}

    /** full constructor */
    public UsuarioUnidad(Integer id, Unidad unidad, Usuario usuario) {
        this.id = id;
        this.unidad = unidad;
        this.usuario = usuario;
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Unidad getUnidad() {
		return this.unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}