package clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import vistas.Vista;

public class Acciones {

	public FileWriter fs = null;
	public byte[] buffer = new byte[81];
	public int nbytes;
	public String nombreFichero = "";
	public String str = null;
	public File fichero = null;
	public JFileChooser fileChooser = new JFileChooser();
	public Vista mivista = new Vista();
	public JTextPane miTexto;
	public FileReader fr;
	public String tituloArchivo = "";
	public String ruta = "";
	

	public Acciones(String nombrefichero, String str) {
		this.nombreFichero = nombrefichero;
		this.str = str;
	}

	public Acciones() {

	}

	public void guardarArchivo() {

		FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt",
				"txt");
		fileChooser.setFileFilter(filter);
		int seleccion = fileChooser.showSaveDialog(mivista.miTexto);
	

		if (seleccion == JFileChooser.APPROVE_OPTION) {
			
			ruta = fileChooser.getSelectedFile().getParent();
			nombreFichero = fileChooser.getSelectedFile().getName();

			fichero = new File(ruta, nombreFichero);

			try {
				fs = new FileWriter(fichero);
				fs.write(str, 0, str.length());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				fs.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else{
			
			mivista.desktopPane.requestFocus();
		}
	}

	public String leerArchivo() {
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt .rtf",
				"txt", "rtf");
		fileChooser.setFileFilter(filter);

		int sel = fileChooser.showOpenDialog(mivista.desktopPane);
		
		String cadena = "";
		if (sel == fileChooser.APPROVE_OPTION) {
			
			ruta = fileChooser.getSelectedFile().getAbsolutePath();
			tituloArchivo = fileChooser.getSelectedFile().getName();
			try {
				fr = new FileReader(ruta);

				BufferedReader br = new BufferedReader(fr);
				String entrada;

				while ((entrada = br.readLine()) != null) {
					cadena = cadena + entrada;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			cadena = null;
		}

		return cadena;
	}
}
