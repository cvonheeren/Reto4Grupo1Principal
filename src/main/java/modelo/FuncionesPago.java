package modelo;
/**
 * Esta clase se encarga de los calculos del dinero del programa
 */
public class FuncionesPago {
	
	Modelo modelo;
	/**
	 * Constructor de la clase FuncionesPago
	 * 
	 * @param modelo Instancia de la clase modelo para poder utilizarlo
	 */
	public FuncionesPago (Modelo modelo) {
		this.modelo = modelo;
	}
	/**
	 * Metodo que se encarga de redimensionar un array que se le pasa por parametro a uno una posicion mayor
	 * 
	 * @param arrayRedimensionar Es el array que se desea redimensionar, se le para como parametro a la funcion
	 * 
	 * @return Devuelve el array una vez ha sido redimensionado y se ha copiado el contenido del array que se queria redimensionar
	 */
	public float[] redimensionarArrayMayor(float[] arrayRedimensionar) {
		int tamano = arrayRedimensionar.length + 1;
		float[] arrayRedimensionado = new float[tamano];
		
		System.arraycopy(arrayRedimensionar, 0, arrayRedimensionado, 0, arrayRedimensionar.length); // Copia arrayRedimensionar en arrayRedimensionado, el cuel es una posicion mas largo.
		return arrayRedimensionado; // Devuelve el resultado.
	}
	/**
	 * Metodo que se encarga de redimensionar un array que se le pasa por parametro a uno una posicion menor
	 * 
	 * @param arrayRedimensionar Es el array que se desea redimensionar, se le para como parametro a la funcion
	 * 
	 * @return Devuelve el array una vez ha sido redimensionado y se ha copiado el contenido del array que se queria redimensionar
	 */
	public float[] redimensionarArrayMenor(float[] arrayRedimensionar) {
		int tamano = arrayRedimensionar.length - 1;
		float[] arrayRedimensionado = new float[tamano];
		
		System.arraycopy(arrayRedimensionar, 0, arrayRedimensionado, 0, arrayRedimensionar.length - 1); // Copia arrayRedimensionar en arrayRedimensionado, el cuel es una posicion mas largo.
		return arrayRedimensionado; // Devuelve el resultado.
	}
	/**
	 * Metodo que se encarga de calcular el dinero faltante
	 * 
	 * @param total Dinero total que se debe introducir
	 * @param dinero Dinero que se ha introducido hasta el momento
	 * 
	 * @return Retorna el dinero que falta por introducir
	 */
	public float falta(float total, float dinero){	
		
		float falta = 0f;//dinero que le falta por introducir.
		     
		falta = total - dinero;//obtenemos la diferencia.
		falta = Math.round(falta*100);//redondeamos el numero.
		falta = falta/100;
						 		
		return falta;						
	}
	/**
	 * Metodo que se encarga de calcular el menor numero de monedas que se tienen que dar de devolucion
	 * 
	 * @param total Dinero total que se debe introducir
	 * @param dinero Dinero total que se ha introducido
	 * 
	 * @return Retorna el menor numero de monedas que se deben retornar
	 */
	public String sobra(float total, float dinero){
		float sobra = 0f;
		int cont = 0;
		String devolver = "";
		
		if(dinero > total)//Si el dinero intro es mayor que el importe se calcula la devolucion.
		{ 
			sobra = dinero-total;
			if(sobra >= 500) //si la cantidad es mayor que este num, se divide entre el para saber la cantidad de billetes de este tipo,y calcula el resto para saber el dinero que le queda por analizar.
			{
				cont = (int) sobra/500;
	            sobra = sobra%500;
	            devolver=devolver+Integer.toString(cont)+" billetes de 500€" + "\n" ;
			}           
	        cont = 0;
	        if(sobra >= 200)
	        {
	             cont = (int) sobra/200;
	             sobra = sobra%200;
	             devolver = devolver+Integer.toString(cont)+" billetes de 200€" + "\n" ;
	        }           
	        cont = 0;
	        if(sobra >= 100)
	        {
	             cont = (int) sobra/100;
	             sobra = sobra%100;
	             devolver = devolver+Integer.toString(cont)+" billetes de 100€" + "\n" ;
	        }             
	        cont = 0;
	        if(sobra >= 50)
	        {
	             cont = (int)sobra/50;
	             sobra = sobra%50;
	             devolver = devolver+Integer.toString(cont)+" billetes de 50€" + "\n" ;
	        }             
	        cont=0;
	        if(sobra >= 20)
	        {
	        	cont = (int) sobra/20;
	        	sobra = sobra%20;
	            devolver = devolver+Integer.toString(cont)+" billetes de 20€" + "\n" ;
	        }
	        cont = 0;
	        if(sobra >= 10)
	        {
	        	cont = (int) sobra/10;
	            sobra = sobra%10;
	            devolver = devolver+Integer.toString(cont)+" billetes de 10€ " + "\n" ;
	        }                    
	        cont = 0;
	        if(sobra >= 5)
	        {
	        	cont = (int) sobra/5;
	            sobra = sobra%5;
	            devolver = devolver+Integer.toString(cont)+" billetes de 5€" + "\n" ;
	        }             
	        cont = 0;
	        if(sobra >= 2)
	        {
	        	cont = (int) sobra/2;
	            sobra = sobra%2;
	            devolver = devolver+Integer.toString(cont)+" monedas de 2€" + "\n" ;
	        }             
	        cont = 0;
	        if(sobra >= 1)
	        {
	            cont = (int)sobra/1;
	            sobra = sobra%1;
	            devolver = devolver+Integer.toString(cont)+" monedas de 1€" + "\n" ;
	        }
	        cont = 0;
	        sobra = Math.round(sobra*100);
			if(sobra >= 50)
			{
				cont = (int) (sobra/50);
				sobra = (float) (sobra%50);
				devolver = devolver+Integer.toString(cont)+" monedas de 0,50€" + "\n";
			}
			cont = 0;
			sobra = Math.round(sobra); 
			if(sobra >= 20)
			{
				cont = (int) (sobra/20);
				sobra = (float) (sobra%20);
				devolver = devolver+Integer.toString(cont)+" monedas de 0,20€" + "\n"; 		 
			}
			cont = 0;
			sobra = Math.round(sobra);   
			if(sobra >= 10)
			{
				cont = (int) (sobra/10);
				sobra = (float) (sobra%10);
				devolver = devolver+Integer.toString(cont)+" monedas de 0,10€" + "\n";	
			}			   
			cont = 0;
			sobra = Math.round(sobra);			      
			if(sobra >= 5)
			{
				cont = (int) (sobra/5);
				sobra = (float) (sobra%5);
				devolver = devolver+Integer.toString(cont)+" monedas de 0,05€" + "\n";		    	  
			}    
			cont = 0;
			sobra = Math.round(sobra);
			if(sobra >= 2)
			{
				cont = (int) (sobra/2);
				sobra = (float) (sobra%2);
				devolver = devolver+Integer.toString(cont)+" monedas de 0,02€" + "\n";	  
			}
			cont = 0;
			sobra = Math.round(sobra);
			if(sobra >= 1)
			{
				cont = (int) (sobra/1);
				devolver = devolver+Integer.toString(cont)+" monedas de 0,01€" + "\n";	
			}    		
		}
		return devolver;		
	}

}