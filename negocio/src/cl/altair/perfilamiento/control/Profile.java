package cl.altair.perfilamiento.control;

import java.util.List;
import java.util.Vector;

import cl.mycompany.perfilamiento.model.*;


public abstract class Profile {
	Usuario usuario = null;
	List<Empresa> empresas = null;
	List<Aplicacion> aplicaciones = null;
	List<Rol> roles = null;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Empresa> getEmpresas() {
		return empresas;
	}
	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}
	public List<Aplicacion> getAplicaciones() {
		return aplicaciones;
	}
	public void setAplicaciones(List<Aplicacion> aplicaciones) {
		this.aplicaciones = aplicaciones;
	}
	public List<Rol> getRoles() {
		return roles;
	}
	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}
	public Vector<Aplicacion> getAplicaciones(Empresa unaEmpresa){
		List<EmpresaApp> lasRelaciones = (List<EmpresaApp>) unaEmpresa.getEmpresaApps();
		Vector<Aplicacion> lasAplicaciones = new Vector<Aplicacion>();
		for(EmpresaApp unaRelacion: lasRelaciones){
			lasAplicaciones.add(unaRelacion.getAplicacion());
		}
		return lasAplicaciones;
	}
}
