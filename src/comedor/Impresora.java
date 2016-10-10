package comedor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import comedor.bd.LogEjecucion;

public class Impresora 
{
	LogEjecucion log = new LogEjecucion();
	
	public List<PrintService> getImpresoras()
	{
		List<PrintService> x;
		PrintService[] ps = PrintServiceLookup.lookupPrintServices(null, null);

		for (PrintService printService : ps) 
			log.grabarArchivo(printService.getName());
		
		x = Arrays.asList(ps);

		return x;
	}

	public void printCarnet(String pdf)
	{
		try 
		{
			PrintService[] ps = PrintServiceLookup.lookupPrintServices(null, null);

			if (ps.length == 0) { }

			PrintService myService = null;

			for (PrintService printService : ps) 
			{
				log.grabarArchivo(printService.getName());
				
				if (printService.getName().equals(principal.getBaseDeDatos().getImpresoraCarnet())) 
				{
					myService = printService;
					break;
				}
			}

			FileInputStream fis;

			fis = new FileInputStream(pdf);
			Doc pdfDoc = new SimpleDoc(fis, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
			DocPrintJob printJob = myService.createPrintJob();

			printJob.print(pdfDoc, new HashPrintRequestAttributeSet());
			fis.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			log.grabarArchivo(e.getMessage()+ "," + e.getLocalizedMessage());
		} 
		catch (PrintException e) 
		{
			e.printStackTrace();
			log.grabarArchivo(e.getMessage()+ "," + e.getLocalizedMessage());
		}      
	}

	public void print(String pdf)
	{
		PrintService[] ps = PrintServiceLookup.lookupPrintServices(null, null);

		if (ps.length == 0) {}
		PrintService myService = null;
		for (PrintService printService : ps)
		{
			if (printService.getName().equals(principal.getBaseDeDatos().getImpresoraEstandar())) 
			{
				myService = printService;
				break;
			}
		}
		try
		{
			FileInputStream fis = new FileInputStream(pdf);
			Doc pdfDoc = new SimpleDoc(fis, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
			DocPrintJob printJob = myService.createPrintJob();
			printJob.print(pdfDoc, new HashPrintRequestAttributeSet());
			fis.close();        
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.grabarArchivo(e.getMessage()+ "," + e.getLocalizedMessage());
		}
	}

	private ComedorGUI principal;

	public Impresora(ComedorGUI comedorGUI)
	{
		this.principal= comedorGUI;
	}
}
