package cl.altair.utiles.ws.perfilamiento;

import java.util.HashMap;
import java.util.Vector;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import cl.mycompany.perfilamiento.model.Aplicacion;
import cl.mycompany.perfilamiento.model.Empresa;
import cl.mycompany.perfilamiento.model.UsuarioRol;


@XmlRootElement(name="perfil")
public class PerfilWrapper {
	private HashMap<UsuarioRol, HashMap<Empresa, Vector<Aplicacion>>> elPerfil = null;
	private String email;
	@XmlAttribute(name="email")
	public String getRut() {
		return email;
	}

	public void setRut(String email) {
		this.email = email;
	}

	public PerfilWrapper(){}
	
	public PerfilWrapper(HashMap<UsuarioRol, HashMap<Empresa, Vector<Aplicacion>>> arg0, String elEmail){
		this.elPerfil = arg0;
		this.email = elEmail;
	}
	
	@XmlJavaTypeAdapter(PerfilAdapter.class)
	public HashMap<UsuarioRol, HashMap<Empresa, Vector<Aplicacion>>> getElPerfil() {
		return elPerfil;
	}

	public void setElPerfil(
		HashMap<UsuarioRol, HashMap<Empresa, Vector<Aplicacion>>> elPerfil) {
		this.elPerfil = elPerfil;
	}
}
