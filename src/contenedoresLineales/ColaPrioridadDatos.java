package contenedoresLineales;

public class ColaPrioridadDatos extends clsColaPrioridad {
	
	public ColaPrioridadDatos (){
		super();
	}
	
	public boolean esMenor(Object objA, Object objB) {
		return ( (Datos)objA ).getFrecuencia()  < ( (Datos)objB ).getFrecuencia();
	}
	
	public boolean esMayor(Object objA, Object objB) {
		return ( (Datos)objA ).getFrecuencia()  > ( (Datos)objB ).getFrecuencia();
	}
	
	public boolean iguales(Object objA, Object objB) {
		return ( (Datos)objA ).getFrecuencia()  == ( (Datos)objB ).getFrecuencia();
	}
	
	public ColaPrioridadDatos hacerCopia () {		// Usa el motodo "cargar"  d
		ColaPrioridadDatos copia = new ColaPrioridadDatos();
		clsNode temp ;
		
		if (!colaVacia()){
			temp=this.frenteC;
			
			do{
				copia.cargar( (Datos) temp.getNodeInfo() );
				temp=temp.getNextNode();
			}while (temp!=null);
			
		}else{
			System.out.println("Error al hacer la copia , Cola vacia");
			return copia = null;
		}
		
		return copia;
	}
	
	public ColaPrioridadDatos hacerCopiaOrdenada () {		// Usa "meter" por eso es ordenada ya que en este metodo esta la prioridad de esta cola que para esta aplicacion es por su numero de frecuencia
		ColaPrioridadDatos copia = new ColaPrioridadDatos();
		clsNode temp ;
		
		if (!colaVacia()){
			temp=this.frenteC;
			
			do{
				copia.meter( (Datos) temp.getNodeInfo() );
				temp=temp.getNextNode();
			}while (temp!=null);
			
		}else{
			System.out.println("Error al hacer la copia , Cola vacia");
			return copia = null;
		}
		
		return copia;
	}
	
	public Datos sacar(){
		return (Datos) super.sacar();
	}
	
	public Datos buscarElemento ( char elem){
		clsNode temp , ant = null ;
		boolean encontrado = false;
		
		if (!colaVacia()){
			temp=this.frenteC;
			
			do{
				ant = temp;
				if ( elem == ( (Datos) ant.getNodeInfo() ).getElemento() ){
					encontrado = true ; }
				else{	temp=temp.getNextNode();	}
				
			}while (temp!=null && !encontrado );
			
			if (encontrado)
				return (Datos) ant.getNodeInfo();
			else
				return null;
		}else{
			System.out.println("Error muestra. Cola vacia");
			return null;
		}
		
	}
	
	public Datos buscarCodigo ( String codi){
		clsNode temp , ant = null ;
		boolean encontrado = false;
		
		if (!colaVacia()){
			temp=this.frenteC;
			
			do{
				ant = temp;
				if ( codi.equals( ( (Datos) ant.getNodeInfo() ).getCodigo() )   ){
					encontrado = true ; }
				else{	temp=temp.getNextNode();	}
				
			}while (temp !=null && !encontrado );
			
			if (encontrado)
				return (Datos) ant.getNodeInfo();
			else
				return null;
		}else{
			System.out.println("Error muestra. Cola vacia");
			return null;
		}
		
	}
	
	public void muestra(){
		clsNode temp;		
		if (!colaVacia()){
			temp=this.frenteC;
			while (temp!=null){
				System.out.println("Simbolo : " + (char) ( (Datos) temp.getNodeInfo() ).getElemento() 
						+" Frecuencia : " + ( (Datos) temp.getNodeInfo() ).getFrecuencia());
						//+" Codigo : " + ( (Datos) temp.getNodeInfo() ).getCodigo()    );
				temp=temp.getNextNode();
			}			
		}else{
			System.out.println("Error muestra. Cola vacia");
		}		
	}

}
