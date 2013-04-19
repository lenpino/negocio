package cl.mycompany.perfilamiento.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the perfil database table.
 * 
 */
@Entity
public class Perfil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PERFIL_ID_GENERATOR", sequenceName="ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PERFIL_ID_GENERATOR")
	private Integer id;

	//bi-directional many-to-one association to EmpresaApp
	@ManyToOne
	@JoinColumn(name="idempresaapp")
	private EmpresaApp empresaApp;

	//bi-directional many-to-one association to UsuarioRol
	@ManyToOne
	@JoinColumn(name="idusuariorol")
	private UsuarioRol usuarioRol;

	//bi-directional many-to-one association to PerfilFuncion
	@OneToMany(mappedBy="perfil", fetch=FetchType.EAGER)
	private List<PerfilFuncion> perfilFuncions;

	public Perfil() {
	}

	/** minimal constructor */
    public Perfil(Integer id, UsuarioRol usuarioRol, EmpresaApp empresaApp) {
        this.id = id;
        this.usuarioRol = usuarioRol;
        this.empresaApp = empresaApp;
    }
    
    /** full constructor */
    public Perfil(Integer id, UsuarioRol usuarioRol, EmpresaApp empresaApp, List<PerfilFuncion> perfilFuncions) {
        this.id = id;
        this.usuarioRol = usuarioRol;
        this.empresaApp = empresaApp;
        this.perfilFuncions = perfilFuncions;
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EmpresaApp getEmpresaApp() {
		return this.empresaApp;
	}

	public void setEmpresaApp(EmpresaApp empresaApp) {
		this.empresaApp = empresaApp;
	}

	public UsuarioRol getUsuarioRol() {
		return this.usuarioRol;
	}

	public void setUsuarioRol(UsuarioRol usuarioRol) {
		this.usuarioRol = usuarioRol;
	}

	public List<PerfilFuncion> getPerfilFuncions() {
		return this.perfilFuncions;
	}

	public void setPerfilFuncions(List<PerfilFuncion> perfilFuncions) {
		this.perfilFuncions = perfilFuncions;
	}

}