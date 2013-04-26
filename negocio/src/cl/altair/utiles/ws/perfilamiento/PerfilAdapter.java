package cl.altair.utiles.ws.perfilamiento;

import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import cl.mycompany.perfilamiento.model.Aplicacion;
import cl.mycompany.perfilamiento.model.Empresa;
import cl.mycompany.perfilamiento.model.UsuarioRol;

public class PerfilAdapter extends XmlAdapter<Roles[], HashMap<UsuarioRol, HashMap<Empresa, Vector<Aplicacion>>>>{

	@Override
	public Roles[] marshal(HashMap<UsuarioRol, HashMap<Empresa, Vector<Aplicacion>>> arg0) throws Exception {
		Roles[] losRoles = new Roles[arg0.size()];	
		Set<UsuarioRol> rols = arg0.keySet();
		int i = 0;
		for(UsuarioRol unRol: rols){
			Roles rolEmp = new Roles();
			rolEmp.setRol(unRol.getRol().getNombre());
//			rolEmp.setDesde(unRol.getDesde());
//			rolEmp.setHasta(unRol.getHasta());
			rolEmp.setEmpresa(getEmpresaApps(arg0.get(unRol)));
			losRoles[i] = rolEmp;
			i++;
		}
		return losRoles;
	}

	@Override
	public HashMap<UsuarioRol, HashMap<Empresa, Vector<Aplicacion>>> unmarshal(Roles[] arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	private EmpresaAppXml[] getEmpresaApps(HashMap<Empresa, Vector<Aplicacion>> arg0){
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
}
