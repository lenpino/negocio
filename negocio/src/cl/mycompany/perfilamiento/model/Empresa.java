package cl.mycompany.perfilamiento.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import javax.xml.bind.annotation.XmlTransient;

import cl.altair.modelo.portal.Programa;

import com.sun.xml.bind.CycleRecoverable;


/**
 * Empresa entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="empresa")
@XmlRootElement(name = "empresa")
public class Empresa  implements java.io.Serializable, CycleRecoverable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
     private Holding holding;
     private String nombre;
     private Integer rut;
     private String dv;
     private String telefono;
 	 private String estado;
	 private Integer nusuarios;
	 private Set<Unidad> unidads = new HashSet<Unidad>(0);
     private Set<PaisEmpresa> paisEmpresas = new HashSet<PaisEmpresa>(0);
     private Set<EmpresaApp> empresaApps = new HashSet<EmpresaApp>(0);
     private Set<Programa> programas = new HashSet<Programa>(0);


    // Constructors

    /** default constructor */
    public Empresa() {
    }

	/** minimal constructor */
    public Empresa(Integer id, String nombre, Integer rut, String dv) {
        this.id = id;
        this.nombre = nombre;
        this.rut = rut;
        this.dv = dv;
    }
    
	/** full constructor */
	public Empresa(Integer id, Holding holding, String nombre, Integer rut,
			String dv, String telefono, String estado, Integer nusuarios, Set<Unidad> unidads,
			Set<PaisEmpresa> paisEmpresas, Set<EmpresaApp> empresaApps) {
		this.id = id;
		this.holding = holding;
		this.nombre = nombre;
		this.rut = rut;
		this.dv = dv;
		this.telefono = telefono;
		this.estado = estado;
		this.nusuarios = nusuarios;
		this.unidads = unidads;
		this.paisEmpresas = paisEmpresas;
		this.empresaApps = empresaApps;
	}

   
    // Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="empresa_id_seq")
    @SequenceGenerator(name="empresa_id_seq", allocationSize=1)
	@Column(name = "id", nullable = false)
	@XmlTransient
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="idholding")

    public Holding getHolding() {
        return this.holding;
    }
    
    public void setHolding(Holding holding) {
        this.holding = holding;
    }
    
    @Column(name="nombre", nullable=false, length=100)

    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Column(name="rut", nullable=false)

    public Integer getRut() {
        return this.rut;
    }
    
    public void setRut(Integer rut) {
        this.rut = rut;
    }
    
    @Column(name="dv", nullable=false, length=1)

    public String getDv() {
        return this.dv;
    }
    
    public void setDv(String dv) {
        this.dv = dv;
    }
    
    @Column(name="telefono", length=20)

    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    @Column(name = "estado", length = 15)
    public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Column(name = "nusuarios", nullable = false)
	public Integer getNusuarios() {
		return this.nusuarios;
	}

	public void setNusuarios(Integer nusuarios) {
		this.nusuarios = nusuarios;
	}

@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="empresa")
@XmlTransient
    public Set<Unidad> getUnidads() {
        return this.unidads;
    }
    
    public void setUnidads(Set<Unidad> unidads) {
        this.unidads = unidads;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="empresa")
@XmlTransient
    public Set<PaisEmpresa> getPaisEmpresas() {
        return this.paisEmpresas;
    }
    
    public void setPaisEmpresas(Set<PaisEmpresa> paisEmpresas) {
        this.paisEmpresas = paisEmpresas;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="empresa")
@XmlTransient
    public Set<EmpresaApp> getEmpresaApps() {
        return this.empresaApps;
    }
    
    public void setEmpresaApps(Set<EmpresaApp> empresaApps) {
        this.empresaApps = empresaApps;
    }
    
    public boolean equals(Object objetoJPA){
    	return (objetoJPA instanceof Empresa) ? getId().equals(((Empresa)objetoJPA).id): super.equals(objetoJPA);
    }
    
    public int hashCode(){
    	return this.id.hashCode();
    }
    public Object onCycleDetected(Context cntxt) {
	    System.out.println("CycleRecoverable.onCycleDetected # ".concat(this.toString()));
	    return this;
    }
    
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="empresa")
    @XmlTransient
	public Set<Programa> getProgramas() {
		return programas;
	}

	public void setProgramas(Set<Programa> programas) {
		this.programas = programas;
	}
}