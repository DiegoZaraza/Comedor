import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

import comedor.bd.BD;
import comedor.pdfs.Persona;


public class Copy {

	public static void main(String[] args) 
	{
		BD base = new BD(null, "postgres", "1234", "localhost", null);
		ArrayList<Persona> xz  =  base.getPas("2016/17");

		for(int index = 0; index < xz.size(); index++)
		{
			try{
				FileInputStream fis = new FileInputStream("C:\\Users\\Naty&Diego\\Documents\\Java\\Comedor\\System-Comedor\\Fotos\\FotoPrueba.jpg"); //inFile -> Archivo a copiar
				FileOutputStream fos = new FileOutputStream("C:\\Users\\Naty&Diego\\Documents\\Java\\Comedor\\System-Comedor\\Fotos\\" + xz.get(index).getDocumento() + ".jpg"); //outFile -> Copia del archivo
				FileChannel inChannel = fis.getChannel(); 
				FileChannel outChannel = fos.getChannel(); 
				inChannel.transferTo(0, inChannel.size(), outChannel); 
				fis.close(); 
				fos.close();
			}catch (IOException ioe) {
				System.err.println("Error al Generar Copia");
			}

		}
	}

}
