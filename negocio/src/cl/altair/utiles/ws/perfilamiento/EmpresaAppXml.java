package cl.altair.utiles.ws.perfilamiento;

import java.util.Vector;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import cl.mycompany.perfilamiento.model.Aplicacion;

public class EmpresaAppXml {
	private String key;
	private Vector<Aplicacion> value;
	public EmpresaAppXml(){}
	
	
	@XmlAttribute(name="nombre")
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	@XmlElement(name="aplicacion")
	public Vector<Aplicacion> getValue() {
		return value;
	}
	public void setValue(Vector<Aplicacion> value) {
		this.value = value;
	}
}
