package comedor.bd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class LogEjecucion {
			// FUNCION PARA REGISTRAR DATOS EN EL LOG
			public void grabarArchivo(String registro) 
			{		
				try 
				{
					//CARGA EL ARCHIVO
					PrintStream Salida = new PrintStream(new FileOutputStream("System-Comedor" + File.separator + "LogEjecución.txt", true));

					//ENVIA REGISTRO AL SCRIPT
					Salida.println(registro);
					Salida.close();
				}
				catch (FileNotFoundException e) 
				{
					//ERROR EN CASO DE NO GRABAR EL ARCHIVO
					e.printStackTrace();
				}
			}
}
