package Ventana;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DecimalFormat;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ProgressMonitor;
import javax.swing.SwingConstants;

import EscribeVersiculos.Escribe;
import Fecha_Hora.Fecha_Hora;


@SuppressWarnings("serial")
public class Ventana extends JFrame implements ActionListener{

	private Container contenedor;
	
	private JButton buscar , ejecutar , cerrar , ajuste , atras , adelante , ver , exportar ;	//declaramos el objeto Boton
	private JButton msjError, Detalle ; // Simulando que son JTextPane
	private JButton icono ; 	// simula el icono
	private JLabel ll, lc ,lv , lnc , lnv ;	//ll= label de libro , lc= label de capitulo , lv = label de versiculo , lnc = label de numero de capitulos , lnv =label numero de versiculos 													
	//private JButton RelojVisual ;		// Simula un reloj 
	
	private JTextField Direccion , l , c , v ;
	private JFileChooser fileChooser;
	
    //private ProgressMonitor progressMonitor;
    private Thread hilo ;	// Thread en español es "hilo"
    
	private ImageIcon icon , otroicon ;
	private Image img , otraimg ;
	
	private File fichero;
	private String ruta , detalle ;
	
	private Escribe escribe ;
	private Fecha_Hora Reloj = new Fecha_Hora () ;
	
	public Ventana(){
		
		//setUndecorated(true) ; // quita el marco de la ventana
		JLabel label = null;
		try {
			//label = new JLabel(new ImageIcon( Ventana.class.getResource("/Imagen/doradomagico.jpg") )); // cargamos la imagen
		    
		} catch (Exception e) {				
			e.printStackTrace();
		}
		if(label != null)
			setContentPane(label); 

		setTitle("Escribe Versiculo");		// Pone titulo a la barra de la aplicacion
		setSize(576,576);			// tamaño de la ventana ( ancho , altura )
		setLocationRelativeTo(null); // la ventana se posicione en el centro de la pantalla
		setResizable(false);  // ya no se expande la ventana
		setIconImage (new ImageIcon(getClass().getResource("/Imagen/iconoh.png")).getImage());	// Pone icono en la barra de titulo y en la miniatura que aparece en la barra de la compu pero no el icno que aparece en la pc
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
		escribe = new Escribe ();
		fileChooser=new JFileChooser();
		contenedor=getContentPane();
		contenedor.setLayout(null);
		
		icono= new JButton();
		icono.setBounds(213,9,117, 113); // posicion (x , y) y tamaño (ancho , altura) --- setBounds (x,y,ancho,altura)
	    //icon = new ImageIcon(  Ventana.class.getResource("/Imagen/startbutton_cortado.png")  );
	    icon = new ImageIcon(  Ventana.class.getResource("/Imagen/iconbiblia.png")  );
	    img = icon.getImage(); //convertimos icon en una imagen
	    otraimg = img.getScaledInstance( icono.getWidth(), icono.getHeight() , java.awt.Image.SCALE_SMOOTH); //creamos una imagen nueva dándole las dimensiones que queramos a la antigua
	    otroicon = new ImageIcon(otraimg);
	    icono.setIcon(otroicon);
	    icono.addActionListener(this);   // evento al pulsar el boton	    
	    icono.setOpaque(false);
	    icono.setContentAreaFilled(false); // boton transparente 
	    icono.setBorderPainted(false);		// quita el borde al boton
	    icon = new ImageIcon(  Ventana.class.getResource("/Imagen/iconbiblia.png")  );
	    img = icon.getImage(); //convertimos icon en una imagen
	    otraimg = img.getScaledInstance( icono.getWidth(), icono.getHeight() , java.awt.Image.SCALE_SMOOTH); //creamos una imagen nueva dándole las dimensiones que queramos a la antigua
	    otroicon = new ImageIcon(otraimg);
	    icono.setRolloverIcon(otroicon);
		
		ll = new JLabel ();
		ll.setHorizontalAlignment(SwingConstants.CENTER);
		ll.setToolTipText("");
		ll.setText("LIBRO");
		ll.setBounds(29,187,200, 25);
		ll.setVerticalTextPosition(SwingConstants.CENTER);	// Es para poner el texto en el centro de forma vertical
	    ll.setHorizontalTextPosition(SwingConstants.CENTER);	// Es para poner el texto en el centro de forma horizontal
		
		lc = new JLabel();
		lc.setHorizontalAlignment(SwingConstants.CENTER);
		lc.setText("CAPITULO");
		lc.setBounds(260,187,70, 25);
		lc.setVerticalTextPosition(SwingConstants.CENTER);	// Es para poner el texto en el centro de forma vertical
	    lc.setHorizontalTextPosition(SwingConstants.CENTER);	// Es para poner el texto en el centro de forma horizontal
		
		lv = new JLabel();
		lv.setHorizontalAlignment(SwingConstants.CENTER);
		lv.setText("VERSICULO");
		lv.setBounds(342,187,70, 25);
		lv.setVerticalTextPosition(SwingConstants.CENTER);	// Es para poner el texto en el centro de forma vertical
	    lv.setHorizontalTextPosition(SwingConstants.CENTER);	// Es para poner el texto en el centro de forma horizontal
		
	    lnc = new JLabel();
	    lnc.setHorizontalAlignment(SwingConstants.CENTER);
	    lnc.setText("1 - 100");
	    lnc.setBounds(260,247,70, 25);
	    lnc.setVerticalTextPosition(SwingConstants.CENTER);	// Es para poner el texto en el centro de forma vertical
	    lnc.setHorizontalTextPosition(SwingConstants.CENTER);	// Es para poner el texto en el centro de forma horizontal
		
		lnv = new JLabel();
		lnv.setHorizontalAlignment(SwingConstants.CENTER);
		lnv.setText("1 - 100");
		lnv.setBounds(342,247,70, 25);
		lnv.setVerticalTextPosition(SwingConstants.CENTER);	// Es para poner el texto en el centro de forma vertical
		lnv.setHorizontalTextPosition(SwingConstants.CENTER);	// Es para poner el texto en el centro de forma horizontal
	    
		exportar= new JButton();
		exportar.setText("EXPORTAR"); // nombre del boton
		exportar.setBounds(453,338,105, 45);	// posicion (x , y) y tamaño (ancho , altura) --- setBounds (x,y,ancho,altura)
	    icon = new ImageIcon(  Ventana.class.getResource("/Imagen/botonfondo.png")  );
	    img = icon.getImage(); //convertimos icon en una imagen
	    otraimg = img.getScaledInstance( exportar.getWidth(), exportar.getHeight() , java.awt.Image.SCALE_SMOOTH); //creamos una imagen nueva dándole las dimensiones que queramos a la antigua
	    otroicon = new ImageIcon(otraimg);
	    exportar.setIcon(otroicon);	 // Pone el Icon osea la Imagen
	    exportar.setVerticalTextPosition(SwingConstants.CENTER);	// Es para poner el texto en el centro de forma vertical
	    exportar.setHorizontalTextPosition(SwingConstants.CENTER);	// Es para poner el texto en el centro de forma horizontal
	    exportar.setForeground(Color.WHITE);	// Color del texto	
	    exportar.addActionListener(this);   // evento al pulsar el boton
	    exportar.setOpaque(false);
	    exportar.setContentAreaFilled(false); // boton transparente 
	    exportar.setBorderPainted(false);		// quita el borde al boton
		icon = new ImageIcon(  Ventana.class.getResource("/Imagen/botonfondo_verde.png")  );
	    img = icon.getImage(); //convertimos icon en una imagen
	    otraimg = img.getScaledInstance( exportar.getWidth(), exportar.getHeight() , java.awt.Image.SCALE_SMOOTH); //creamos una imagen nueva dándole las dimensiones que queramos a la antigua
	    otroicon = new ImageIcon(otraimg);
	    exportar.setRolloverIcon(otroicon);
		
		ver= new JButton();
		ver.setText("VER"); // nombre del boton
		ver.setBounds(453,215,90, 45);	// posicion (x , y) y tamaño (ancho , altura) --- setBounds (x,y,ancho,altura)
	    icon = new ImageIcon(  Ventana.class.getResource("/Imagen/botonfondo.png")  );
	    img = icon.getImage(); //convertimos icon en una imagen
	    otraimg = img.getScaledInstance( ver.getWidth(), ver.getHeight() , java.awt.Image.SCALE_SMOOTH); //creamos una imagen nueva dándole las dimensiones que queramos a la antigua
	    otroicon = new ImageIcon(otraimg);
	    ver.setIcon(otroicon);	 // Pone el Icon osea la Imagen
	    ver.setVerticalTextPosition(SwingConstants.CENTER);	// Es para poner el texto en el centro de forma vertical
	    ver.setHorizontalTextPosition(SwingConstants.CENTER);	// Es para poner el texto en el centro de forma horizontal
	    ver.setForeground(Color.WHITE);	// Color del texto	
	    ver.addActionListener(this);   // evento al pulsar el boton
	    ver.setOpaque(false);
	    ver.setContentAreaFilled(false); // boton transparente 
	    ver.setBorderPainted(false);		// quita el borde al boton
		icon = new ImageIcon(  Ventana.class.getResource("/Imagen/botonfondo_verde.png")  );
	    img = icon.getImage(); //convertimos icon en una imagen
	    otraimg = img.getScaledInstance( ver.getWidth(), ver.getHeight() , java.awt.Image.SCALE_SMOOTH); //creamos una imagen nueva dándole las dimensiones que queramos a la antigua
	    otroicon = new ImageIcon(otraimg);
	    ver.setRolloverIcon(otroicon);
		
	    adelante= new JButton();
	    adelante.setText("ADELANTE"); // nombre del boton
	    adelante.setBounds(144,280,100, 45);	// posicion (x , y) y tamaño (ancho , altura) --- setBounds (x,y,ancho,altura)
	    icon = new ImageIcon(  Ventana.class.getResource("/Imagen/botonfondo.png")  );
	    img = icon.getImage(); //convertimos icon en una imagen
	    otraimg = img.getScaledInstance( adelante.getWidth(), adelante.getHeight() , java.awt.Image.SCALE_SMOOTH); //creamos una imagen nueva dándole las dimensiones que queramos a la antigua
	    otroicon = new ImageIcon(otraimg);
	    adelante.setIcon(otroicon);	 // Pone el Icon osea la Imagen
	    adelante.setVerticalTextPosition(SwingConstants.CENTER);	// Es para poner el texto en el centro de forma vertical
	    adelante.setHorizontalTextPosition(SwingConstants.CENTER);	// Es para poner el texto en el centro de forma horizontal
	    adelante.setForeground(Color.WHITE);	// Color del texto	
	    adelante.addActionListener(this);   // evento al pulsar el boton
	    adelante.setOpaque(false);
	    adelante.setContentAreaFilled(false); // boton transparente 
	    adelante.setBorderPainted(false);		// quita el borde al boton
		icon = new ImageIcon(  Ventana.class.getResource("/Imagen/botonfondo_verde.png")  );
	    img = icon.getImage(); //convertimos icon en una imagen
	    otraimg = img.getScaledInstance( adelante.getWidth(), adelante.getHeight() , java.awt.Image.SCALE_SMOOTH); //creamos una imagen nueva dándole las dimensiones que queramos a la antigua
	    otroicon = new ImageIcon(otraimg);
	    adelante.setRolloverIcon(otroicon);
	    
	    atras= new JButton();
		atras.setText("ATRAS"); // nombre del boton
		atras.setBounds(32,280,100, 45);	// posicion (x , y) y tamaño (ancho , altura) --- setBounds (x,y,ancho,altura)
	    icon = new ImageIcon(  Ventana.class.getResource("/Imagen/botonfondo.png")  );
	    img = icon.getImage(); //convertimos icon en una imagen
	    otraimg = img.getScaledInstance( atras.getWidth(), atras.getHeight() , java.awt.Image.SCALE_SMOOTH); //creamos una imagen nueva dándole las dimensiones que queramos a la antigua
	    otroicon = new ImageIcon(otraimg);
	    atras.setIcon(otroicon);	 // Pone el Icon osea la Imagen
	    atras.setVerticalTextPosition(SwingConstants.CENTER);	// Es para poner el texto en el centro de forma vertical
	    atras.setHorizontalTextPosition(SwingConstants.CENTER);	// Es para poner el texto en el centro de forma horizontal
	    atras.setForeground(Color.WHITE);	// Color del texto	
		atras.addActionListener(this);   // evento al pulsar el boton
		atras.setOpaque(false);
		atras.setContentAreaFilled(false); // boton transparente 
		atras.setBorderPainted(false);		// quita el borde al boton
		icon = new ImageIcon(  Ventana.class.getResource("/Imagen/botonfondo_verde.png")  );
	    img = icon.getImage(); //convertimos icon en una imagen
	    otraimg = img.getScaledInstance( atras.getWidth(), atras.getHeight() , java.awt.Image.SCALE_SMOOTH); //creamos una imagen nueva dándole las dimensiones que queramos a la antigua
	    otroicon = new ImageIcon(otraimg);
	    atras.setRolloverIcon(otroicon);
		
		buscar= new JButton();
		buscar.setText("BUSCAR"); // nombre del boton
		buscar.setBounds(29,125,90, 45);	// posicion (x , y) y tamaño (ancho , altura) --- setBounds (x,y,ancho,altura)
	    icon = new ImageIcon(  Ventana.class.getResource("/Imagen/botonfondo.png")  );
	    img = icon.getImage(); //convertimos icon en una imagen
	    otraimg = img.getScaledInstance( buscar.getWidth(), buscar.getHeight() , java.awt.Image.SCALE_SMOOTH); //creamos una imagen nueva dándole las dimensiones que queramos a la antigua
	    otroicon = new ImageIcon(otraimg);
	    buscar.setIcon(otroicon);	 // Pone el Icon osea la Imagen
	    buscar.setVerticalTextPosition(SwingConstants.CENTER);	// Es para poner el texto en el centro de forma vertical
	    buscar.setHorizontalTextPosition(SwingConstants.CENTER);	// Es para poner el texto en el centro de forma horizontal
	    buscar.setForeground(Color.WHITE);	// Color del texto	
		buscar.addActionListener(this);   // evento al pulsar el boton
		buscar.setOpaque(false);
		buscar.setContentAreaFilled(false); // boton transparente 
		buscar.setBorderPainted(false);		// quita el borde al boton
		icon = new ImageIcon(  Ventana.class.getResource("/Imagen/botonfondo_verde.png")  );
	    img = icon.getImage(); //convertimos icon en una imagen
	    otraimg = img.getScaledInstance( buscar.getWidth(), buscar.getHeight() , java.awt.Image.SCALE_SMOOTH); //creamos una imagen nueva dándole las dimensiones que queramos a la antigua
	    otroicon = new ImageIcon(otraimg);
	    buscar.setRolloverIcon(otroicon);


		Direccion = new JTextField();
		Direccion.setForeground(Color.BLACK);	// Cambia el color del texto
		Direccion.setBounds(144, 135, 345, 25);	// posicion (x , y) y tamaño (ancho , altura) --- setBounds (x,y,ancho,altura)
	    Direccion.setEditable(true); // permite escribir 
	    Direccion.setHorizontalAlignment(JTextField.LEFT);
		Direccion.setFont(new Font("Tahoma", Font.PLAIN, 12));	// es para cambiar la letra -- Font ( TipoLetra , Style , Tamaño )
		Direccion.setBackground(Color.DARK_GRAY);	 // Para cambiar el color del fondo
		Direccion.setCaretColor(Color.WHITE);		//Cambia el color del selector donde uno hace click con el mouse
	    Direccion.setToolTipText("Ingrese la Direccion");	// Cunado pones el mouse sale un cartel con lo que uno escribe
	    Direccion.setSelectionColor(Color.BLUE);			// Cambia el color del area cuando selecciona el texto
	    Direccion.setSelectedTextColor(Color.WHITE);		// Cambia el color del texto cuando es seleccionado
	    Direccion.setOpaque(false);
	    
	    l = new JTextField();
		l.setForeground(Color.WHITE);	// Cambia el color del texto
		l.setHorizontalAlignment(SwingConstants.CENTER);	// pone el texto en el centrode forma horizontal
		l.setBounds(29, 225, 200, 25);	// posicion (x , y) y tamaño (ancho , altura) --- setBounds (x,y,ancho,altura)
	    l.setEditable(true); // permite escribir 
	    l.setHorizontalAlignment(JTextField.LEFT);
		l.setFont(new Font("Tahoma", Font.PLAIN, 12));	// es para cambiar la letra -- Font ( TipoLetra , Style , Tamaño )
		l.setBackground(Color.DARK_GRAY);	 // Para cambiar el color del fondo
		l.setCaretColor(Color.WHITE);		//Cambia el color del selector donde uno hace click con el mouse
	    l.setToolTipText("Libro");	// Cunado pones el mouse sale un cartel con lo que uno escribe
	    l.setSelectionColor(Color.BLUE);			// Cambia el color del area cuando selecciona el texto
	    l.setSelectedTextColor(Color.WHITE);		// Cambia el color del texto cuando es seleccionado
	    l.setOpaque(false);
	    
	    c = new JTextField();
		c.setForeground(Color.WHITE);	// Cambia el color del texto
		c.setHorizontalAlignment(SwingConstants.CENTER);	// pone el texto en el centrode forma horizontal
		c.setBounds(260, 225, 70, 25);	// posicion (x , y) y tamaño (ancho , altura) --- setBounds (x,y,ancho,altura)
	    c.setEditable(true); // permite escribir 
	    c.setHorizontalAlignment(JTextField.LEFT);
		c.setFont(new Font("Tahoma", Font.PLAIN, 12));	// es para cambiar la letra -- Font ( TipoLetra , Style , Tamaño )
		c.setBackground(Color.DARK_GRAY);	 // Para cambiar el color del fondo
		c.setCaretColor(Color.WHITE);		//Cambia el color del selector donde uno hace click con el mouse
	    c.setToolTipText("Capitulo");	// Cunado pones el mouse sale un cartel con lo que uno escribe
	    c.setSelectionColor(Color.BLUE);			// Cambia el color del area cuando selecciona el texto
	    c.setSelectedTextColor(Color.WHITE);		// Cambia el color del texto cuando es seleccionado
	    c.setOpaque(false);
	    
	    v = new JTextField();
		v.setForeground(Color.WHITE);	// Cambia el color del texto
		v.setHorizontalAlignment(SwingConstants.CENTER);	// pone el texto en el centrode forma horizontal
		v.setBounds(342, 225, 70, 25);	// posicion (x , y) y tamaño (ancho , altura) --- setBounds (x,y,ancho,altura)
	    v.setEditable(true); // permite escribir 
	    v.setHorizontalAlignment(JTextField.LEFT);
		v.setFont(new Font("Tahoma", Font.PLAIN, 12));	// es para cambiar la letra -- Font ( TipoLetra , Style , Tamaño )
		v.setBackground(Color.DARK_GRAY);	 // Para cambiar el color del fondo
		v.setCaretColor(Color.WHITE);		//Cambia el color del selector donde uno hace click con el mouse
	    v.setToolTipText("Versiculo");	// Cunado pones el mouse sale un cartel con lo que uno escribe
	    v.setSelectionColor(Color.BLUE);			// Cambia el color del area cuando selecciona el texto
	    v.setSelectedTextColor(Color.WHITE);		// Cambia el color del texto cuando es seleccionado
	    v.setOpaque(false);
	   
	    
	    ejecutar= new JButton();
	    ejecutar.setBounds(453,13,70, 70); // posicion (x , y) y tamaño (ancho , altura) --- setBounds (x,y,ancho,altura)
	    //icon = new ImageIcon(  Ventana.class.getResource("/Imagen/startbutton_cortado.png")  );
	    icon = new ImageIcon(  Ventana.class.getResource("/Imagen/startbutton_verde.png")  );
	    img = icon.getImage(); //convertimos icon en una imagen
	    otraimg = img.getScaledInstance( ejecutar.getWidth(), ejecutar.getHeight() , java.awt.Image.SCALE_SMOOTH); //creamos una imagen nueva dándole las dimensiones que queramos a la antigua
	    otroicon = new ImageIcon(otraimg);
	    ejecutar.setIcon(otroicon);
	    ejecutar.addActionListener(this);   // evento al pulsar el boton	    
	    ejecutar.setOpaque(false);
	    ejecutar.setContentAreaFilled(false); // boton transparente 
	    ejecutar.setBorderPainted(false);		// quita el borde al boton
	    icon = new ImageIcon(  Ventana.class.getResource("/Imagen/startbutton_verde2.png")  );
	    img = icon.getImage(); //convertimos icon en una imagen
	    otraimg = img.getScaledInstance( ejecutar.getWidth(), ejecutar.getHeight() , java.awt.Image.SCALE_SMOOTH); //creamos una imagen nueva dándole las dimensiones que queramos a la antigua
	    otroicon = new ImageIcon(otraimg);
	    ejecutar.setRolloverIcon(otroicon);
	    
	    cerrar= new JButton();
	    cerrar.setBounds(453,13,70, 70); // posicion (x , y) y tamaño (ancho , altura) --- setBounds (x,y,ancho,altura)
	    //icon = new ImageIcon(  Ventana.class.getResource("/Imagen/startbutton_cortado.png")  );
	    icon = new ImageIcon(  Ventana.class.getResource("/Imagen/cancelbutton.png")  );
	    img = icon.getImage(); //convertimos icon en una imagen
	    otraimg = img.getScaledInstance( cerrar.getWidth(), cerrar.getHeight() , java.awt.Image.SCALE_SMOOTH); //creamos una imagen nueva dándole las dimensiones que queramos a la antigua
	    otroicon = new ImageIcon(otraimg);
	    cerrar.setIcon(otroicon);
	    cerrar.addActionListener(this);   // evento al pulsar el boton
	    cerrar.setEnabled(false);		// Es para que no se pueda clickear
	    cerrar.setVisible(false);		// Es para que el boton no se vea ;
	    cerrar.setOpaque(false);
	    cerrar.setContentAreaFilled(false); // boton transparente 
	    cerrar.setBorderPainted(false);	// quita el borde al boton
	    icon = new ImageIcon(  Ventana.class.getResource("/Imagen/cancelbutton2.png")  );
	    img = icon.getImage(); //convertimos icon en una imagen
	    otraimg = img.getScaledInstance( cerrar.getWidth(), cerrar.getHeight() , java.awt.Image.SCALE_SMOOTH); //creamos una imagen nueva dándole las dimensiones que queramos a la antigua
	    otroicon = new ImageIcon(otraimg);
	    cerrar.setRolloverIcon(otroicon);
	    
	    ajuste= new JButton();
	    ajuste.setBounds(10,13,35,35); // posicion (x , y) y tamaño (ancho , altura) --- setBounds (x,y,ancho,altura)
	    //icon = new ImageIcon(  Ventana.class.getResource("/Imagen/startbutton_cortado.png")  );
	    icon = new ImageIcon(  Ventana.class.getResource("/Imagen/3puntos.png")  );
	    img = icon.getImage(); //convertimos icon en una imagen
	    otraimg = img.getScaledInstance( ajuste.getWidth(), ajuste.getHeight() , java.awt.Image.SCALE_SMOOTH); //creamos una imagen nueva dándole las dimensiones que queramos a la antigua
	    otroicon = new ImageIcon(otraimg);
	    ajuste.setIcon(otroicon);
	    ajuste.addActionListener(this);   // evento al pulsar el boton	    
	    ajuste.setOpaque(false);
	    ajuste.setContentAreaFilled(false); // boton transparente 
	    ajuste.setBorderPainted(false);		// quita el borde al boton
	    icon = new ImageIcon(  Ventana.class.getResource("/Imagen/3puntos_verde.png")  );
	    img = icon.getImage(); //convertimos icon en una imagen
	    otraimg = img.getScaledInstance( ajuste.getWidth(), ajuste.getHeight() , java.awt.Image.SCALE_SMOOTH); //creamos una imagen nueva dándole las dimensiones que queramos a la antigua
	    otroicon = new ImageIcon(otraimg);
	    ajuste.setRolloverIcon(otroicon);
	    
	    
		msjError= new JButton();
		//msjError.setText("Error"); // nombre del boton
		msjError.setBounds(32, 498, 526, 30);	// posicion (x , y) y tamaño (ancho , altura) --- setBounds (x,y,ancho,altura)
	    icon = new ImageIcon(  Ventana.class.getResource("/Imagen/botonsquaremensaje.png")  );
	    img = icon.getImage(); //convertimos icon en una imagen
	    otraimg = img.getScaledInstance( msjError.getWidth(), msjError.getHeight() , java.awt.Image.SCALE_SMOOTH); //creamos una imagen nueva dándole las dimensiones que queramos a la antigua
	    otroicon = new ImageIcon(otraimg);
	    msjError.setIcon(otroicon);		// Pone el Icon osea la Imagen que queremos poner
	    msjError.setVerticalTextPosition(SwingConstants.CENTER);	// Es para poner el texto en el centro de forma vertical
	    msjError.setHorizontalTextPosition(SwingConstants.CENTER);	// Es para poner el texto en el centro de forma horizontal
	    msjError.setForeground(Color.GREEN);	// Color del texto
	    msjError.setOpaque(false);
	    msjError.setContentAreaFilled(false); // boton transparente 
	    msjError.setBorderPainted(false);		// quita el borde al boton	
		
	    
		Detalle= new JButton();
		//Detalle.setText(" detalle asdsd "); // nombre del boton
		Detalle.setBounds(32, 338, 410, 145);	// posicion (x , y) y tamaño (ancho , altura) --- setBounds (x,y,ancho,altura)
	    icon = new ImageIcon(  Ventana.class.getResource("/Imagen/botonsquaremensaje.png")  );
	    img = icon.getImage(); //convertimos icon en una imagen
	    otraimg = img.getScaledInstance( Detalle.getWidth(), Detalle.getHeight() , java.awt.Image.SCALE_SMOOTH); //creamos una imagen nueva dándole las dimensiones que queramos a la antigua
	    otroicon = new ImageIcon(otraimg);
	    Detalle.setIcon(otroicon);		// Pone el Icon osea la Imagen que queremos poner
	    //Detalle.setFont(new Font("Arial Black", Font.PLAIN, 15));	// es para cambiar la letra -- Font ( Fuente , Estilo , Tamaño )
	    Detalle.setVerticalTextPosition(SwingConstants.CENTER);	// Es para poner el texto en el centro de forma vertical
	    Detalle.setHorizontalTextPosition(SwingConstants.CENTER);	// Es para poner el texto en el centro de forma horizontal
	    Detalle.setForeground(Color.GREEN);		// Color del texto
	    Detalle.setOpaque(false);
	    Detalle.setContentAreaFilled(false); // boton transparente 
	    Detalle.setBorderPainted(false);		// quita el borde al boton	
	    
	    // Este era para poner un Reloj visual , mi idea era ponerlo en JOptionPane pero por lo que lei no podia
	    /*
	    RelojVisual= new JButton();
	    //RelojVisual.setBounds(198,335,70, 70); // posicion (x , y) y tamaño (ancho , altura) --- setBounds (x,y,ancho,altura)
	    RelojVisual.setSize(70, 70); 		// tamaño (ancho , altura) --- setSize (ancho,altura) , NOTA : no define la posicion , pero queda en x=0 y Y=0
	    icon = new ImageIcon(  Ventana.class.getResource("/Imagen/reloj_naranja_chico.png")  );
	    img = icon.getImage(); //convertimos icon en una imagen
	    otraimg = img.getScaledInstance( RelojVisual.getWidth(), RelojVisual.getHeight() , java.awt.Image.SCALE_SMOOTH); //creamos una imagen nueva dándole las dimensiones que queramos a la antigua
	    otroicon = new ImageIcon(otraimg);
	    RelojVisual.setIcon(otroicon);
	    //RelojVisual.addActionListener(this);   // evento al pulsar el boton	    
	    RelojVisual.setOpaque(false);
	    RelojVisual.setContentAreaFilled(false); // boton transparente 
	    RelojVisual.setBorderPainted(false);		// quita el borde al boton
	    */
		
	    contenedor.add(icono);
	    contenedor.add(Direccion);
	    contenedor.add(l);
	    contenedor.add(c);
	    contenedor.add(v);
	    contenedor.add(ll);
	    contenedor.add(lc);
	    contenedor.add(lv);
	    contenedor.add(lnc);
	    contenedor.add(lnv);
		contenedor.add(buscar);
		contenedor.add(atras);
		contenedor.add(adelante);
		contenedor.add(ver);
		contenedor.add(exportar);
		contenedor.add(ejecutar);
		contenedor.add(cerrar);
		contenedor.add(ajuste);
		contenedor.add(msjError);
		contenedor.add(Detalle);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent evento ) {

		if (evento.getSource()==buscar){ 
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fileChooser.showOpenDialog(this);
			fichero = fileChooser.getSelectedFile();
			ruta = fichero.getPath();
			Direccion.setText(ruta);
			msjError.setText("");
			//detalle = " ARCHIVO ORIGINAL \n Peso : " + pesoArchivo(fichero);
			//detalle = "<html>ARCHIVO ORIGINAL<br>" + "Peso : " + pesoArchivo(fichero);
			//Detalle.setText(detalle+"</html>");
			
		}
		
		if (evento.getSource()==ejecutar){
		}
		
		if (evento.getSource()==cerrar){
		}
		
		if (evento.getSource()==atras){
			
		}
		
		if (evento.getSource()==adelante){
			
		}
		
		if (evento.getSource()==ver){
			
		}
		
		if (evento.getSource()==exportar){
			escribe.cargarRuta(Direccion.getText().toString());
			escribe.cargarv();
		}
		
		
		if (evento.getSource()==ajuste){
			String acerca_de_la_App ;
			acerca_de_la_App = "ESCRIBE VERSICULO \nVersion : v1.0.0\n\nDESARROLLADORES\nMamani Jorge Luis\n Alejandro Sanchez\n\nCORREO DE LOS DESARROLLADORES\njorgemamani297@gmail.com\n";
			JOptionPane.showMessageDialog( null , acerca_de_la_App );	// showInputDialog ( parentComponent, message, title, messageType ):  nos permite personalizar aun más el dialogo, con un titulo y un icono (error, información, advertencia, pregunta o plano). Por ejemplo, JOptionPane.showInputDialog(null, “Introduce un dato”, “Titulo”, JOptionPane.INFORMATION_MESSAGE);			
		}
		
	}
}
