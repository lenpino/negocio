package cl.altair.utiles.ws.perfilamiento;

import java.util.HashMap;
import java.util.Vector;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import cl.mycompany.perfilamiento.model.Aplicacion;
import cl.mycompany.perfilamiento.model.Empresa;


@XmlRootElement
public class EmpresaAppWrapper {
	private HashMap<Empresa, Vector<Aplicacion>> empresas;
	public EmpresaAppWrapper(){}
	public EmpresaAppWrapper(HashMap<Empresa, Vector<Aplicacion>> arg){
		this.setEmpresas(arg);
	}
	@XmlJavaTypeAdapter(EmpresaAppAdapter.class)
	public HashMap<Empresa, Vector<Aplicacion>> getEmpresas() {
		return empresas;
	}
	public void setEmpresas(HashMap<Empresa, Vector<Aplicacion>> empresas) {
		this.empresas = empresas;
	}
	
}
