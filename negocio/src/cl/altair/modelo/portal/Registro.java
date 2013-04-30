package cl.altair.modelo.portal;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Registro entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "registro", schema = "public")
public class Registro implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer rut;
	private String dv;
	private String nombres;
	private String apellido;
	private String apellidoMaterno;
	private String email;
	private String telefono;
	private Integer rutEmpresa;
	private String dvEmpresa;
	private String giro;
	private String razonSocial;
	private String direccion;
	private Integer comuna;
	private String ciudad;
	private Long codigoAuditoria;
	private Timestamp fechaRegistro;
	private String tipo;
	private String estado;
	private Timestamp fechaUltimaActualizacion;
    private Date fechaNacimiento;
    private String clave;

	// Constructors

	/** default constructor */
	public Registro() {
	}

	/** minimal constructor */
	public Registro(Integer id, Integer rut, String dv, String nombres,
			String apellido, String email, Long codigoAuditoria,
			Timestamp fechaRegistro, String tipo) {
		this.id = id;
		this.rut = rut;
		this.dv = dv;
		this.nombres = nombres;
		this.apellido = apellido;
		this.email = email;
		this.codigoAuditoria = codigoAuditoria;
		this.fechaRegistro = fechaRegistro;
		this.tipo = tipo;
	}

	/** full constructor */
	public Registro(Integer id, Integer rut, String dv, String nombres,
			String apellido, String apellidoMaterno, String email,
			String telefono, Integer rutEmpresa, String dvEmpresa, String giro,
			String razonSocial, String direccion, Integer comuna, String ciudad,
			Long codigoAuditoria, Timestamp fechaRegistro, String tipo,
			String estado, Timestamp fechaUltimaActualizacion,
			Date fechaNacimiento) {
		this.id = id;
		this.rut = rut;
		this.dv = dv;
		this.nombres = nombres;
		this.apellido = apellido;
		this.apellidoMaterno = apellidoMaterno;
		this.email = email;
		this.telefono = telefono;
		this.rutEmpresa = rutEmpresa;
		this.dvEmpresa = dvEmpresa;
		this.giro = giro;
		this.razonSocial = razonSocial;
		this.direccion = direccion;
		this.comuna = comuna;
		this.ciudad = ciudad;
		this.codigoAuditoria = codigoAuditoria;
		this.fechaRegistro = fechaRegistro;
		this.tipo = tipo;
		this.estado = estado;
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
		this.fechaNacimiento = fechaNacimiento;
	}

	// Property accessors
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="registro_id_seq")
    @SequenceGenerator(name="registro_id_seq", allocationSize=1)
	@Column(name = "id", unique = true, nullable = false)
	@XmlTransient
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "rut")
	@XmlTransient
	public Integer getRut() {
		return this.rut;
	}

	public void setRut(Integer rut) {
		this.rut = rut;
	}

	@Column(name = "dv", length = 1)
	@XmlTransient
	public String getDv() {
		return this.dv;
	}

	public void setDv(String dv) {
		this.dv = dv;
	}

	@Column(name = "nombres")
	@XmlTransient
	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	@Column(name = "apellido", length = 100)
	@XmlTransient
	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@Column(name = "apellido_materno", length = 100)
	@XmlTransient
	public String getApellidoMaterno() {
		return this.apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	@Column(name = "email", nullable = false, length = 100)
	@XmlTransient
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "telefono", length = 100)
	@XmlTransient
	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Column(name = "rut_empresa")
	@XmlTransient
	public Integer getRutEmpresa() {
		return this.rutEmpresa;
	}

	public void setRutEmpresa(Integer rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}

	@Column(name = "dv_empresa", length = 1)
	@XmlTransient
	public String getDvEmpresa() {
		return this.dvEmpresa;
	}

	public void setDvEmpresa(String dvEmpresa) {
		this.dvEmpresa = dvEmpresa;
	}

	@Column(name = "giro")
	@XmlTransient
	public String getGiro() {
		return this.giro;
	}

	public void setGiro(String giro) {
		this.giro = giro;
	}

	@Column(name = "razon_social")
	@XmlTransient
	public String getRazonSocial() {
		return this.razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	@Column(name = "direccion")
	@XmlTransient
	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Column(name = "comuna")
	@XmlTransient
	public Integer getComuna() {
		return this.comuna;
	}

	public void setComuna(Integer comuna) {
		this.comuna = comuna;
	}

	@Column(name = "ciudad", length = 100)
	@XmlTransient
	public String getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	@Column(name = "codigo_auditoria", nullable = false)
	@XmlElement(name = "refid")
	public Long getCodigoAuditoria() {
		return this.codigoAuditoria;
	}

	public void setCodigoAuditoria(Long codigoAuditoria) {
		this.codigoAuditoria = codigoAuditoria;
	}

	@Column(name = "fecha_registro", nullable = false, length = 29)
	@XmlTransient
	public Timestamp getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Timestamp fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	@Column(name = "tipo", length = 15)
	@XmlTransient
	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Column(name = "estado", length = 20)
	@XmlTransient
	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Column(name = "fecha_ultima_actualizacion", length = 29)
	@XmlTransient
	public Timestamp getFechaUltimaActualizacion() {
		return this.fechaUltimaActualizacion;
	}

	public void setFechaUltimaActualizacion(Timestamp fechaUltimaActualizacion) {
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
	}

	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * @return the fechaNacimiento
	 */
	@Column(name = "fecha_nacimiento")
	@Temporal(TemporalType.DATE) 
	@XmlTransient
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	@Column(name = "clave")
	@XmlElement(name="ref")
	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

}