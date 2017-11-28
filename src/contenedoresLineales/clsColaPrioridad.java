package contenedoresLineales;


public abstract class clsColaPrioridad implements TAD_Cola {
	protected clsNode frenteC, finalC;
	
	public clsColaPrioridad(){
		limpiarCola();
	}
	
	public boolean colaVacia(){
		return (this.frenteC==null);
	}	

	public void limpiarCola(){
		this.frenteC=this.finalC=null;
	}	
	
	public boolean QuedaUnSoloNodo () {
		return this.frenteC == this.finalC ;
	}
	
	public abstract boolean esMenor(Object objA, Object objB);
	public abstract boolean esMayor(Object objA, Object objB);
	public abstract boolean iguales(Object objA, Object objB);
	
	public boolean esMayorIgual (Object objA, Object objB) {
		boolean esmayorigual = false;
		if (esMayor(objA, objB)) esmayorigual = true ;
		if (iguales(objA, objB)) esmayorigual = true ;
		return esmayorigual;
	}
	
	public boolean esMenorIgual (Object objA, Object objB) {
		boolean esmenorigual = false;
		if (esMenor(objA, objB)) esmenorigual = true ;
		if (iguales(objA, objB)) esmenorigual = true ;
		return esmenorigual;
	}
	
	public void cargar(Object nodoNuevo){		// Funciona como una cola simple sin prioridad -- agrega nuevo dato al final
		if(!colaVacia()){
			this.finalC.setNextNode(new clsNode(nodoNuevo));
			this.finalC=this.finalC.getNextNode();
		}else{
			this.frenteC=this.finalC= new clsNode( nodoNuevo );	
		}
	}
	
	public void meter ( Object nodoNuevo ) {
		
		if (!colaVacia()){
			if( esMenor(nodoNuevo,frenteC.getNodeInfo()) ){
				frenteC = new clsNode (nodoNuevo , frenteC) ;
			 }else{
			    if ( esMayorIgual(nodoNuevo , finalC.getNodeInfo()) ){
			    	 	finalC.setNextNode( new clsNode (nodoNuevo) );
			    	 	finalC = finalC.getNextNode() ;
			     }else{
			    	 clsNode ant= frenteC ;
			    	 clsNode temp= frenteC.getNextNode() ;
			    	 boolean band= false;
	                 while( temp!=null && !band ){
	                	if ( esMayorIgual(nodoNuevo,ant.getNodeInfo())  && esMenor(nodoNuevo,temp.getNodeInfo())  ) {
	                		 ant.setNextNode(new clsNode (nodoNuevo , temp) );
	                		 
	                		 band = true;
	                	 }else{
	                		ant = temp;
	        		    	temp = temp.getNextNode() ;
	                	}
	                } 	 
			     }
			 }
					
			// nuevo nodo es el ultimo. su sigte vale null
		}else{
			this.frenteC=this.finalC= new clsNode( nodoNuevo );			
		}
	}	

	// frente es a izquierda
	public Object sacar(){
		Object objElemento = null;
		if (!colaVacia()){
			objElemento=this.frenteC.getNodeInfo();
			this.frenteC=this.frenteC.getNextNode();
			
			if (colaVacia()){
				this.finalC=null;
			}
		}else{
			System.out.println("Error sacar. Cola vacia");			
		}
		return objElemento;
	}	
	
	
	public abstract void muestra();
	
	
}
