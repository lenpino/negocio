package cl.altair.utiles.ws.perfilamiento;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import cl.altair.utiles.ws.generales.DateAdapter;

public class Roles {
	
	private String rol;
	private Date desde;
	private Date hasta;
	
	private EmpresaAppXml[] empresa;
	
	public Roles(){}
	public Roles(String elRol, Date desde, Date hasta, EmpresaAppXml[] empresas){
		this.desde = desde;
		this.rol = elRol;
		this.hasta = hasta;
		this.empresa = empresas;
	}
	@XmlAttribute(name="nombre")
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	
	@XmlAttribute(name="hasta")
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getHasta() {
		return hasta;
	}
	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}
	
	@XmlJavaTypeAdapter(DateAdapter.class)
	@XmlAttribute(name="desde")
	public Date getDesde() {
		return desde;
	}
	public void setDesde(Date desde) {
		this.desde = desde;
	}

	public EmpresaAppXml[] getEmpresa() {
		return empresa;
	}
	public void setEmpresa(EmpresaAppXml[] empresa) {
		this.empresa = empresa;
	}
}
