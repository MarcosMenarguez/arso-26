package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Utils {
	
	
	public static String formatoFecha(Calendar fecha) {
		
		DateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
		
		return formateador.format(fecha.getTime());
	}
	
	public static Date dateFromString(String fechaString) {
		
		DateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
		
		try {
			return formateador.parse(fechaString);
		} 
		catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String createId() {
		
		return UUID.randomUUID().toString();
	}
}
