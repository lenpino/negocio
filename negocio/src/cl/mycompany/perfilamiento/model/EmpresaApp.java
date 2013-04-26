package cl.mycompany.perfilamiento.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import cl.altair.utiles.ws.generales.IntegerAdapter;

import com.sun.xml.bind.CycleRecoverable;

import java.util.List;


/**
 * The persistent class for the empresa_app database table.
 * 
 */
@Entity
@Table(name="empresa_app")
public class EmpresaApp implements Serializable, CycleRecoverable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EMPRESA_APP_ID_GENERATOR", sequenceName="ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EMPRESA_APP_ID_GENERATOR")
	@XmlID
	@XmlJavaTypeAdapter(value = IntegerAdapter.class, type = String.class)
	private Integer id;

	//bi-directional many-to-one association to Aplicacion
	@ManyToOne
	@JoinColumn(name="idapp")
	private Aplicacion aplicacion;

	//bi-directional many-to-one association to Empresa
	@ManyToOne
	@JoinColumn(name="idempresa")
	private Empresa empresa;

	//bi-directional many-to-one association to Perfil
	@OneToMany(mappedBy="empresaApp", fetch=FetchType.EAGER)
	private List<Perfil> perfils;

	public EmpresaApp() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@XmlIDREF
	public Aplicacion getAplicacion() {
		return this.aplicacion;
	}

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Perfil> getPerfils() {
		return this.perfils;
	}

	public void setPerfils(List<Perfil> perfils) {
		this.perfils = perfils;
	}

    public Object onCycleDetected(Context cntxt) {
	    System.out.println("CycleRecoverable.onCycleDetected # ".concat(this.toString()));
	    return this;
    }

}