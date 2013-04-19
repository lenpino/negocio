package cl.mycompany.perfilamiento.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the pais_empresa database table.
 * 
 */
@Entity
@Table(name="pais_empresa")
public class PaisEmpresa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PAIS_EMPRESA_ID_GENERATOR", sequenceName="ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PAIS_EMPRESA_ID_GENERATOR")
	private Integer id;

	//bi-directional many-to-one association to Empresa
	@ManyToOne
	@JoinColumn(name="idempresa")
	private Empresa empresa;

	//bi-directional many-to-one association to Pai
	@ManyToOne
	@JoinColumn(name="idpais")
	private Pais pai;

	public PaisEmpresa() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Pais getPai() {
		return this.pai;
	}

	public void setPai(Pais pai) {
		this.pai = pai;
	}

}