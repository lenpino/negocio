package cl.mycompany.perfilamiento.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the usuario_rol database table.
 * 
 */
@Entity
@Table(name="usuario_rol")
public class UsuarioRol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USUARIO_ROL_ID_GENERATOR", sequenceName="ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USUARIO_ROL_ID_GENERATOR")
	private Integer id;

	@Temporal(TemporalType.DATE)
	private Date desde;

	private String estado;

	@Temporal(TemporalType.DATE)
	private Date hasta;

	//bi-directional many-to-one association to Perfil
	@OneToMany(mappedBy="usuarioRol", fetch=FetchType.EAGER)
	private List<Perfil> perfils;

	//bi-directional many-to-one association to Rol
	@ManyToOne
	@JoinColumn(name="idrol")
	private Rol rol;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="idusuario")
	private Usuario usuario;

	public UsuarioRol() {
	}

	/** minimal constructor */
    public UsuarioRol(Integer id, Rol rol, Usuario usuario) {
        this.id = id;
        this.rol = rol;
        this.usuario = usuario;
    }
    
    /** full constructor */
    public UsuarioRol(Integer id, Rol rol, Usuario usuario, List<Perfil> perfils) {
        this.id = id;
        this.rol = rol;
        this.usuario = usuario;
        this.perfils = perfils;
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDesde() {
		return this.desde;
	}

	public void setDesde(Date desde) {
		this.desde = desde;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getHasta() {
		return this.hasta;
	}

	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}

	public List<Perfil> getPerfils() {
		return this.perfils;
	}

	public void setPerfils(List<Perfil> perfils) {
		this.perfils = perfils;
	}

	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}