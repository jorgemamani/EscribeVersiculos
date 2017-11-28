package EscribeVersiculos;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Escribe {
	private String ruta;
	private String libro;
	private int capitulo,versiculo;
	private int nummaxVersiculo,nummaxCapitulo;
	
	public Escribe (){
		ruta = "";
		libro = "";
		capitulo = 0;
		versiculo = 0 ;
		nummaxVersiculo = 0 ;
		nummaxCapitulo = 0 ;
	}
	
	public void setRuta (String s){ ruta =s; }
	public String getRuta (){ return ruta; }
	
	public void setLibro (String s){libro = s ;}
	public String getLibro () { return libro ; }
	
	public void setCapitulo (int n) { capitulo = n ; }
	public int getCapitulo () { return capitulo ; }
	public void setVersiculo (int n) { versiculo = n ; }
	public int getVersiculo () { return versiculo ; }
	
	public void setMaxV (int n) { nummaxVersiculo = n ; }
	public int getMaxV () { return nummaxVersiculo ; }
	
	public void setMaxC (int n) { nummaxCapitulo = n ; }
	public int getMaxC () { return nummaxCapitulo ; }
	
	public void crearTexto (String ver){
		RandomAccessFile archivo ;
		
		try {
    		archivo = new RandomAccessFile(ruta,"r");		// "r" significa que el archivo tiene permiso de lectura y escritura -- NOTA esta en Inles Read por eso es "r"
    		String nombreArchivoTxt = new String(ruta+"versiculo.txt");
    	    RandomAccessFile nuevoArchivoTxt = new RandomAccessFile( nombreArchivoTxt , "rw" );	// "rw" significa que el archivo tiene permiso de lectura y escritura -- NOTA esta en Inles Read-Write por eso es "rw"							
    	    
    
    	    nuevoArchivoTxt.writeChars(ver);
   		    
    	    nuevoArchivoTxt.close();
   		    archivo.close();
   		    
		}
		catch(IOException io) {
			System.err.println("Un poblema con " + ruta);
		}
		
	}
	
	public void cargarRuta (String r ){
		this.setRuta(r);
	}
	
	public void cargarVersiculo (String r , String Libro , int Capitulo , int Versiculo ){
		String versiculoEncontrado ="" ;
		cargarRuta (r);
		//versiculoEncontrado = BuscarVersiculo();
		crearTexto(versiculoEncontrado);
		
	}
	
	public void cargarv () {
		String versiculoEncontrado = "Todo lo puedo en Cristo que me fortalece\nFilipenses 4:13" ;
		crearTexto(versiculoEncontrado);
	}

}
