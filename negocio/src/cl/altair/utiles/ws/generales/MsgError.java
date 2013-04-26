package cl.altair.utiles.ws.generales;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "error")
public class MsgError {
	private String mensaje;

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
