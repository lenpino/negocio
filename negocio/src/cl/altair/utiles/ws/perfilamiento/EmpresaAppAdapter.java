package cl.altair.utiles.ws.perfilamiento;

import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import cl.mycompany.perfilamiento.model.Aplicacion;
import cl.mycompany.perfilamiento.model.Empresa;

public class EmpresaAppAdapter extends XmlAdapter<EmpresaAppXml[], HashMap<Empresa, Vector<Aplicacion>>>{

	@Override
	public EmpresaAppXml[] marshal(HashMap<Empresa, Vector<Aplicacion>> arg0) throws Exception {
		EmpresaAppXml[] listadoEmpApp = new EmpresaAppXml[arg0.size()];
		Set<Empresa> llaves = arg0.keySet();
		int i=0;
		for(Empresa laLlave: llaves){
			EmpresaAppXml elemento = new EmpresaAppXml();
			elemento.setKey(laLlave.getNombre());
			elemento.setValue(arg0.get(laLlave));
			listadoEmpApp[i] = elemento;
			i++;
		}
		return listadoEmpApp;
	}

	@Override
	public HashMap<Empresa, Vector<Aplicacion>> unmarshal(EmpresaAppXml[] arg0) throws Exception {
		HashMap<Empresa, Vector<Aplicacion>> empresas = new HashMap<Empresa, Vector<Aplicacion>>();
		for(EmpresaAppXml laEmpresa: arg0){
			Empresa unaEmpresa = new Empresa(0,laEmpresa.getKey(),1,"9");
			empresas.put(unaEmpresa, laEmpresa.getValue());
		}
		return empresas;
	}

}
