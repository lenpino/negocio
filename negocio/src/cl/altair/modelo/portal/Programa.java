package cl.altair.modelo.portal;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.math.BigInteger;
import java.sql.Date;
import javax.persistence.*;

import cl.mycompany.perfilamiento.model.Empresa;

/**
 * Entity implementation class for Entity: Programa
 *
 */
@Entity

public class Programa implements Serializable {


	private Integer id;
	private Date activacion;
	private BigInteger serial;
	private String estado;
	private String version;
	private Empresa empresa;
	private static final long serialVersionUID = 1L;

	public Programa() {
		super();
	}   
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="programa_id_seq")
    @SequenceGenerator(name="programa_id_seq", allocationSize=1)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}  
	
	@Column(name = "activacion")
	public Date getActivacion() {
		return this.activacion;
	}

	public void setActivacion(Date activacion) {
		this.activacion = activacion;
	}
	
	@Column(name = "serial")
	public BigInteger getSerial() {
		return this.serial;
	}

	public void setSerial(BigInteger serial) {
		this.serial = serial;
	}  
	
	@Column(name = "estado")
	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}   
	
	@Column(name = "version")
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}   
	
	@ManyToOne(fetch=FetchType.EAGER)
	@PrimaryKeyJoinColumn(name="id")
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
   
}
