package cl.altair.utiles.ws.generales;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
 
import javax.xml.bind.annotation.adapters.XmlAdapter;
 
public class DateAdapter extends XmlAdapter<String, Date>{
 
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
 
    @Override
    public String marshal(Date date) throws Exception {
    	if(date != null)
    		return dateFormat.format(date);
    	else 
    		return dateFormat.format(Calendar.getInstance().getTime());
    }
 
    @Override
    public Date unmarshal(String date) throws Exception {
        return dateFormat.parse(date);
    }
}