package cl.altair.utiles.ws.perfilamiento;

import javax.xml.bind.annotation.XmlRootElement;

import cl.altair.modelo.portal.Programa;
import cl.altair.modelo.portal.Registro;

@XmlRootElement(name="registro")
public class RegClienteWrapper {
	private Programa programa;
	private Registro registro;
	public Programa getPrograma() {
		return programa;
	}
	public void setPrograma(Programa programa) {
		this.programa = programa;
	}
	public Registro getRegistro() {
		return registro;
	}
	public void setRegistro(Registro registro) {
		this.registro = registro;
	}

}
