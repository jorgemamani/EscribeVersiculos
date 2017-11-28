package contenedoresLineales;

public class Datos {
	private char elemento;
	private int frecuencia;
	private String codigo;
	
	public Datos (){
		setFrecuencia (0);
		setCodigo (null);
	}
	
	public Datos ( char e , int f ){
		setElemento (e);
		setFrecuencia (f);
		setCodigo (null);
	}
	
	public char getElemento () { return this.elemento ; }
	public void setElemento (char e) { this.elemento = e ; }
	
	public int getFrecuencia () { return this.frecuencia ; }
	public void setFrecuencia (int f) { this.frecuencia = f ; }
	
	public String getCodigo () { return this.codigo ; }
	public void setCodigo (String c) { this.codigo = c ; }

}
