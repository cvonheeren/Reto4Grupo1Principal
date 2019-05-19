package modelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class GestorDeFechas {

	/**
	 * Metodo que comprueba si una fecha es festivo
	 * @param fecha La fecha a comprobar
	 * @return true si es festivo, false si no lo es
	 */
	public boolean comprobarFestivo(LocalDate fecha) {
		
		//Los dias que son festivos
		LocalDate fecha1 = LocalDate.of(2019, 7, 25);
		LocalDate fecha2 = LocalDate.of(2019, 8, 15);
		LocalDate fecha3 = LocalDate.of(2019, 10, 12);
		LocalDate fecha4 = LocalDate.of(2019, 11, 1);
		LocalDate fecha5 = LocalDate.of(2019, 11, 9);
		LocalDate fecha6 = LocalDate.of(2019, 12, 6);
		LocalDate fecha7 = LocalDate.of(2019, 12, 8);
		LocalDate fecha8 = LocalDate.of(2019, 12, 25);
		
		LocalDate[] fechasFestivo = {fecha1, fecha2, fecha3, fecha4, fecha5, fecha6, fecha7, fecha8};
		
		for (int i = 0; i < fechasFestivo.length; i++) {
			if (fecha.isEqual(fechasFestivo[i]))
				return true;
		}
		
		return false;
	}
	
	/**
	 * Comprueba si una fecha esta en la epoca de verano
	 * @param fecha La fecha a comprobar
	 * @return True si esta en verano false si no lo esta
	 */
    public boolean comprobarSiEsVerano(LocalDate fecha) {
        LocalDate fecha1 = LocalDate.of(2019, 6, 30);
        LocalDate fecha2 = LocalDate.of(2019, 9, 1);
    	
        if (fecha.isAfter(fecha1) && fecha.isBefore(fecha2))
        	return true;
        else
        	return false;
    }
    
    /**
	 * Crea un arraylist de LocalDate con todas las fecha qeu hay entre dos fechas introducidas incluyéndolas
	 * @param fecha1
	 * @param fecha2
	 * @return
	 */
	public ArrayList<LocalDate> setDiasSeleccionados(LocalDate fecha1, LocalDate fecha2) {
		ArrayList<LocalDate> dias = new ArrayList<LocalDate>();
		LocalDate fechaAux;
		
		for (int i = 0; i < (fecha2.getDayOfYear() - fecha1.getDayOfYear()); i++) {
			fechaAux = fecha1.plusDays(i);
			dias.add(fechaAux);
		}
		
		return dias;
	}
	
}
