package Fecha_Hora;

//import java.sql.Date;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Fecha_Hora {
	private Calendar calendario = new GregorianCalendar();
	private int Años , Mes , Dias ;
	private int Horas, Minutos, Segundos ;
	private long tiempoInicio , tiempoFin ;		// Esto es para contar el tiempo como un cronometro
	
	public Fecha_Hora () {
		calendario = new GregorianCalendar();
	}
	
	public int getHora() {	return this.Horas ;	}
	public void setHora()	{ this.Horas = calendario.get(Calendar.HOUR_OF_DAY);	}
	
	public int getMinutos() {	return this.Minutos ;	}
	public void setMinutos()	{ this.Minutos = calendario.get(Calendar.MINUTE);	}
	
	public int getSegundos() {	return this.Segundos ;	}
	public void setSegundos()	{ this.Segundos = calendario.get(Calendar.SECOND);	}
	
	public int getDia() {	return this.Dias ;	}
	public void setDia()	{ this.Dias = calendario.get(Calendar.DAY_OF_MONTH );	}
	
	public int getMes() {	return this.Mes ;	}
	public void setMes()	{ this.Mes = calendario.get(Calendar.MONTH );	}
	
	public int getAño() {	return this.Años ;	}
	public void setAño()	{ this.Años = calendario.get(Calendar.YEAR );	}
	
	public void obtenerHora () {
		setHora();
		setMinutos () ;
		setSegundos () ;		
	}
	
	public void obtenerFecha () {
		setDia ();
		setMes ();
		setAño();
	}
	
	public void obtenerFechaHora () {
		obtenerFecha ();
		obtenerHora ();
	}
	
	private String formatoHora (int hora , int minuto , int segundo ){
		String hh , mm , ss ;
		
		// controla la hora
		if (hora < 10)	{
			if (hora == 0)
				hh = "00";
			else
				hh = "0"+hora;
		}
		else {
			hh=""+hora ;
		}
		// controla minutos
		if (minuto < 10) {
			if (minuto == 0)
				mm = "00";
			else
				mm = "0"+minuto;
		}
		else {
			mm = ""+minuto ;
		}
		
		// controla los segundos
		if (segundo < 10) {
			if (segundo == 0)
				ss = "00";
			else
				ss = "0"+segundo;
		}
		else {
			ss=""+segundo ;
		}
		
		return hh +":"+ mm +":"+ ss ;
	}
	
	private String formatoFecha (int anio , int mes , int dia ){
		String dd , mm , aa ;
		int añoDosCifrasFinal ;
		
		if (dia < 10) {
			if (dia == 0)
				dd = "00";
			else
				dd = "0"+dia;
		}
		else {
			dd=""+dia ;
		}
		
		if (mes < 10) {
			if (mes == 0)
				mm = "00";
			else
				mm = "0"+mes;
		}
		else {
			mm =""+ mes ;
		}
		
		añoDosCifrasFinal = anio%100 ;
		
		if (añoDosCifrasFinal < 10) {
			if (añoDosCifrasFinal == 0)
				aa = "00";
			else
				aa = "0"+añoDosCifrasFinal;
		}
		else {
			aa =""+ añoDosCifrasFinal ;
		}
		
		return dd +"/"+ mm +"/"+ aa ;
	}
	
	public String sacarHora (){
		String hora ;
		obtenerHora ();
		hora = formatoHora ( getHora() , getMinutos() , getSegundos() ) ;
		return hora;
		
	}
	
	public String sacarFecha (){
		obtenerFecha ();
		return formatoFecha ( getAño() , getMes() , getDia () ) ;
	}
	
	public String sacarFechaHora (){
		return sacarFecha() +" "+ sacarHora() ;
	}
	
	public void mostrarHora () {
		System.out.println ("Hora : "+sacarHora() );
	}
	
	public void mostrarFecha () {
		System.out.println ("Fecha : "+sacarFecha() );
	}
	
	public void mostrarFechaHora () {
		System.out.println ("Fecha y Hora : "+sacarFechaHora() );
	}
	
	public void IniciarTiempo () {
		this.tiempoInicio  = System.currentTimeMillis();		// Saca el tiempo en milisegundos
	}
	
	public void TerminarTiempo () {
		this.tiempoFin = System.currentTimeMillis();		// Saca el tiempo en milisegundos
	}
	
	public String calcularTiempo ( ) {
		String tiempoCalculado ;
		int horaT , minutoT , segundoT;
		
		segundoT = (int) ((tiempoFin - tiempoInicio) / 1000) ;
		minutoT = segundoT / 60 ;	// divido todos los segundos en 60 para convertirlos en en minutos -- 1 minuto = 60 segundos

		segundoT = segundoT % 60 ;	// ahora pongo los segundos que me quedaron de sobra 
		horaT =  minutoT / 60 ;		// divido todos los minutos en 60 para convertirlos en horas -- 1 hora = 60 minutos
		
		minutoT = minutoT % 60 ;	// ahora pongo los segundos que me quedaron de sobra
		
		tiempoCalculado = formatoHora ( horaT , minutoT , segundoT ) ;
		
		return tiempoCalculado;
	}
	
	public void mostrarTiempoCalculado () {
		System.out.println ("Duracion : "+ calcularTiempo() );
	}
	
	
	// Estos son metodos que veia por Internet pero ninguno me sirvio o no los supe usar
	// NOTA : averiguar la funcion .split(" string ") ;
	
	/*
	public void calcularTiempo ( ) {
		Hora_Fecha horaFin = new Hora_Fecha () ;
		horaFin.obtenerHora();
		
		//String hora1 = "03:15:20";
		//String hora2 = "04:20:10";
		
		String hora1 = sacarHora() ;
		String hora2 = horaFin.sacarHora() ;

		String[] h1 = hora1.split(":");
		String[] h2 = hora2.split(":");
		int resto = 0;

		int segundo = Integer.parseInt( h2[2] ) - Integer.parseInt(h1[2]) ;
		if (segundo < 0){
		   resto = -1;
		   segundo = 60 - segundo;
		}

		int minuto = ( Integer.parseInt(h2[1]) - Integer.parseInt(h1[1]) ) - resto ;
		resto = 0 ;
		if (minuto < 0){
		   minuto = 60 - minuto;
		   resto = -1;
		}
		int hora = (Integer.parseInt(h2[0]) - Integer.parseInt(h1[0])) - resto ;

		System.out.println("Diferencia= "+hora+":"+minuto+":"+segundo);
	}
	*/
	
	
	/*
	 public String diferenciaFechas(String inicio, String llegada){

	        Date fechaInicio = null;
	        Date fechaLlegada = null;

	        // configuramos el formato en el que esta guardada la fecha en 
	        //  los strings que nos pasan
	        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	        try {
	            // aca realizamos el parse, para obtener objetos de tipo Date de 
	            // las Strings
	            fechaInicio = (Date) formato.parse(inicio);
	            fechaLlegada = (Date) formato.parse(llegada);

	        } catch (ParseException e) {
	           // Log.e(TAG, "Funcion diferenciaFechas: Error Parse " + e);
	        } catch (Exception e){
	            // Log.e(TAG, "Funcion diferenciaFechas: Error " + e);
	        }

	        // tomamos la instancia del tipo de calendario
	        Calendar calendarInicio = Calendar.getInstance();
	        Calendar calendarFinal = Calendar.getInstance();

	        // Configramos la fecha del calendatio, tomando los valores del date que 
	        // generamos en el parse
	        calendarInicio.setTime(fechaInicio);
	        calendarFinal.setTime(fechaLlegada);

	        // obtenemos el valor de las fechas en milisegundos
	        long milisegundos1 = calendarInicio.getTimeInMillis();
	        long milisegundos2 = calendarFinal.getTimeInMillis();

	        // tomamos la diferencia
	        long diferenciaMilisegundos = milisegundos2 - milisegundos1;

	        // Despues va a depender en que formato queremos  mostrar esa 
	        // diferencia, minutos, segundo horas, dias, etc, aca van algunos 
	        // ejemplos de conversion

	        // calcular la diferencia en segundos
	        long diffSegundos =  Math.abs (diferenciaMilisegundos / 1000);

	        // calcular la diferencia en minutos
	        long diffMinutos =  Math.abs (diferenciaMilisegundos / (60 * 1000));
	        long restominutos = diffMinutos%60;

	        // calcular la diferencia en horas
	        long diffHoras =   (diferenciaMilisegundos / (60 * 60 * 1000));

	        // calcular la diferencia en dias
	        long diffdias = Math.abs ( diferenciaMilisegundos / (24 * 60 * 60 * 1000) );

	        // devolvemos el resultado en un string
	        return String.valueOf(diffHoras + "H " + restominutos + "m ");
	    }
	 */
	
	
	 /*
	 public void muestra () {
		 Date hora1 = new SimpleDateFormat().parse("19/05/2006 05:00:00");
		 Date hora2 = new SimpleDateFormat().parse("19/05/2006 08:30:30");
		 long lantes = hora1.getTime();
		 long lahora = hora2.getTime();
		 long diferencia = (lahora - lantes);
		 System.out.println(new java.text.SimpleDateFormat( "HH:mm:ss" ).format( new Date(diferencia) )) ;
	 }
	 */
	
	
	
	
}
