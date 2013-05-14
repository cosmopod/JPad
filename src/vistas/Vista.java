package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.BorderLayout;

import javax.swing.JColorChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JSeparator;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JEditorPane;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyledEditorKit;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;

import clases.Acciones;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class Vista {

	public JFrame frmJavapad;
	public JMenuItem mAcercaDe;
	public JMenuItem mBienvenida;
	public JMenu ayuda;
	public JCheckBoxMenuItem chkColor;
	public JCheckBoxMenuItem chkCursiva;
	public JCheckBoxMenuItem chkNegrita;
	public JMenuItem mSalir;
	public JMenuItem mCerrarArchivo;
	public JMenuItem mGuardar;
	public JMenuItem mNuevo;
	public JInternalFrame internalFrame;
	public JDesktopPane desktopPane;
	public int numVentanasTexto;
	public JTextPane miTexto;
	public Acciones accion;
	private JMenuItem mAbrir;
	private Color color = Color.lightGray;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vista window = new Vista();
					window.frmJavapad.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Vista() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		numVentanasTexto = 2;
		frmJavapad = new JFrame();
		frmJavapad.setTitle("JavaPad");
		frmJavapad.setBounds(100, 100, 800, 600);
		frmJavapad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frmJavapad.setJMenuBar(menuBar);

		JMenu archivo = new JMenu("Archivo");
		menuBar.add(archivo);

		mNuevo = new JMenuItem("Nuevo");
		mNuevo.setIcon(new ImageIcon(Vista.class
				.getResource("/com/sun/java/swing/plaf/windows/icons/File.gif")));
		mNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				InputEvent.ALT_MASK | InputEvent.SHIFT_MASK));

		archivo.add(mNuevo);

		// Guardar archivo =====================================================

		mGuardar = new JMenuItem("Guardar");
		mGuardar.setIcon(new ImageIcon(
				Vista.class
						.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
		mGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,
				InputEvent.CTRL_MASK));
		mGuardar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				accion = new Acciones(internalFrame.getTitle(), miTexto
						.getText());
				accion.guardarArchivo();
			}
		});
		// Abrir archivo ======================================================
		mAbrir = new JMenuItem("Abrir");
		mAbrir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				accion = new Acciones();
				String contenido = accion.leerArchivo();
				if (contenido == null){
				}
				else {
					String titulo = accion.tituloArchivo;
					abrirDocumento(contenido, titulo);
				}
			}
		});
		mAbrir.setIcon(new ImageIcon(
				Vista.class
						.getResource("/javax/swing/plaf/metal/icons/ocean/directory.gif")));
		archivo.add(mAbrir);
		archivo.add(mGuardar);

		JSeparator separator = new JSeparator();
		archivo.add(separator);

		// Creacion de estilo
		// ========================================================

		miTexto = new JTextPane();
		miTexto.setBackground(new Color(255, 255, 204));

		mCerrarArchivo = new JMenuItem("Cerrar archivo");
		mCerrarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		archivo.add(mCerrarArchivo);

		mSalir = new JMenuItem("Salir");
		mSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				frmJavapad.dispose();
			}
		});
		archivo.add(mSalir);

		JMenu editar = new JMenu("Editar");
		menuBar.add(editar);

		JMenu mnFormato = new JMenu("Formato");
		editar.add(mnFormato);

		chkNegrita = new JCheckBoxMenuItem("Negrita");
		chkNegrita.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				InputEvent.CTRL_MASK));
		chkNegrita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (chkNegrita.getState() == false)
					new StyledEditorKit.BoldAction().actionPerformed(null);
				else
					new StyledEditorKit.BoldAction().actionPerformed(null);
			}

		});
		mnFormato.add(chkNegrita);

		chkCursiva = new JCheckBoxMenuItem("Cursiva");
		chkCursiva.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.CTRL_MASK));
		chkCursiva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (chkNegrita.getState() == false)
					new StyledEditorKit.ItalicAction().actionPerformed(null);
				else
					new StyledEditorKit.ItalicAction().actionPerformed(null);

			}
		});
		mnFormato.add(chkCursiva);

		// cambiar color Color chooser ==================================== 
		
		chkColor = new JCheckBoxMenuItem("Color");
		chkColor.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
				InputEvent.CTRL_MASK));
		chkColor.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				SimpleAttributeSet atributo = new SimpleAttributeSet();
				if (chkColor.getState() == true) {
					
				 color = JColorChooser.showDialog(desktopPane, "Escoja un color", color);
			     if ( color == null )
	                  color = Color.BLACK;
					StyleConstants.setForeground(atributo, color);
					miTexto.setCharacterAttributes(atributo, false);
				} else {
					StyleConstants.setForeground(atributo, Color.BLACK);
					miTexto.setCharacterAttributes(atributo, false);
				}

			}
		});
		mnFormato.add(chkColor);

		ayuda = new JMenu("Ayuda");
		menuBar.add(ayuda);

		mBienvenida = new JMenuItem("Bienvenida");
		ayuda.add(mBienvenida);

		mAcercaDe = new JMenuItem("Acerca de");
		ayuda.add(mAcercaDe);

		desktopPane = new JDesktopPane();
		frmJavapad.getContentPane().add(desktopPane, BorderLayout.CENTER);

		internalFrame = new JInternalFrame("Mi Texto");
		internalFrame.setMaximizable(true);
		internalFrame.setResizable(true);
		internalFrame.setClosable(true);
		internalFrame.setBounds(238, 91, 386, 341);
		desktopPane.add(internalFrame);

		internalFrame.getContentPane().add(miTexto, BorderLayout.CENTER);
		internalFrame.setVisible(true);

		mNuevo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				nuevaVentanaTexto();

			}
		});
	}

	public JInternalFrame getInternalFrame() {
		return internalFrame;
	}

	public void nuevaVentanaTexto() {
		if (numVentanasTexto >= 2) {
			internalFrame = new JInternalFrame("Mi Texto " + numVentanasTexto);
		} else
			internalFrame = new JInternalFrame("Mi Texto");
		internalFrame.setMaximizable(true);
		internalFrame.setResizable(true);
		internalFrame.setClosable(true);
		internalFrame.setBounds(238, 91, 386, 341);
		desktopPane.add(internalFrame);

		miTexto = new JTextPane();
		miTexto.setBackground(new Color(255, 255, 204));
		internalFrame.getContentPane().add(miTexto, BorderLayout.CENTER);
		internalFrame.setVisible(true);
		numVentanasTexto++;
	}

	public void abrirDocumento(String contenido, String titulo) {

		internalFrame = new JInternalFrame(titulo);
		internalFrame.setMaximizable(true);
		internalFrame.setResizable(true);
		internalFrame.setClosable(true);
		internalFrame.setBounds(238, 91, 386, 341);
		desktopPane.add(internalFrame);

		miTexto = new JTextPane();
		miTexto.setBackground(new Color(255, 255, 204));
		internalFrame.getContentPane().add(miTexto, BorderLayout.CENTER);
		internalFrame.setVisible(true);
		miTexto.setText(contenido);

	}
}
