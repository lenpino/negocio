package cl.mycompany.perfilamiento.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

import com.sun.xml.bind.CycleRecoverable;

import java.util.List;


/**
 * The persistent class for the rol database table.
 * 
 */
@Entity
public class Rol implements Serializable, CycleRecoverable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ROL_ID_GENERATOR", sequenceName="ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ROL_ID_GENERATOR")
	private Integer id;

	private String nombre;

	//bi-directional many-to-one association to UsuarioRol
	@OneToMany(mappedBy="rol", fetch=FetchType.EAGER)
	private List<UsuarioRol> usuarioRols;

	public Rol() {
	}

	@XmlTransient
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
	
	@XmlTransient
	public List<UsuarioRol> getUsuarioRols() {
		return this.usuarioRols;
	}

	public void setUsuarioRols(List<UsuarioRol> usuarioRols) {
		this.usuarioRols = usuarioRols;
	}
    public boolean equals(Object objetoJPA){
    	return (objetoJPA instanceof Rol) ? getId().equals(((Rol)objetoJPA).id): super.equals(objetoJPA);
    }
    
    public int hashCode(){
    	return this.id.hashCode();
    }
    public Object onCycleDetected(Context cntxt) {
	    System.out.println("CycleRecoverable.onCycleDetected # ".concat(this.toString()));
	    return this;
    }
}