package com.gameduca.utils;

import java.util.Date;

public class GameducaUtils {
	
	public boolean entraEnRangoHorario(Date fechaInicio, Date fechaFin) {
		boolean result = false;
		Date horaActual = new Date();
		if((horaActual.equals(fechaInicio) || horaActual.after(fechaInicio)) 
				&& (horaActual.equals(fechaFin) || horaActual.before(fechaFin))) {
			result = true;
		}
		return result;
	}

}
