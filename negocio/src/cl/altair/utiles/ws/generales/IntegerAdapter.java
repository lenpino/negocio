package cl.altair.utiles.ws.generales;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class IntegerAdapter extends XmlAdapter<String, Integer> {
	
	public Integer unmarshal(String v){
		return Integer.parseInt(v);
	}	
	public String marshal(Integer v) {
		return v.toString();
	}

}

