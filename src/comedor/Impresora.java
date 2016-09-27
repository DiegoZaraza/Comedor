package comedor;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Sides;

public class Impresora {


	public List<PrintService> getImpresoras(){
		DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PAGEABLE;
		PrintRequestAttributeSet patts = new HashPrintRequestAttributeSet();
		patts.add(Sides.ONE_SIDED);
		PrintService[] ps = PrintServiceLookup.lookupPrintServices(flavor, patts);
		if (ps.length == 0) {
			return null;
		}
		List<PrintService> x = Arrays.asList(ps);
		return x;
	}

	public void printCarnet(String pdf)
	{
		DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PAGEABLE;
		PrintRequestAttributeSet patts = new HashPrintRequestAttributeSet();
		patts.add(Sides.ONE_SIDED);
		PrintService[] ps = PrintServiceLookup.lookupPrintServices(flavor, patts);
		
		if (ps.length == 0) { }

		PrintService myService = null;

		for (PrintService printService : ps) 
		{
			if (printService.getName().equals(principal.getBaseDeDatos().getImpresoraCarnet())) 
			{
				myService = printService;
				break;
			}
		}

		try
		{
			System.out.println(pdf);
			FileInputStream fis = new FileInputStream(pdf);

			Doc pdfDoc = new SimpleDoc(fis, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
			DocPrintJob printJob = myService.createPrintJob();
			printJob.print(pdfDoc, new HashPrintRequestAttributeSet());
			fis.close();        
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void print(String pdf)
	{
		PrintService[] ps = PrintServiceLookup.lookupPrintServices(null, null);
		if (ps.length == 0) {
			//		        return null;
		}
		PrintService myService = null;
		for (PrintService printService : ps) {
			if (printService.getName().equals(principal.getBaseDeDatos().getImpresoraEstandar())) {
				myService = printService;
				break;
			}
		}
		try{
			FileInputStream fis = new FileInputStream(pdf);
			Doc pdfDoc = new SimpleDoc(fis, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
			DocPrintJob printJob = myService.createPrintJob();
			printJob.print(pdfDoc, new HashPrintRequestAttributeSet());
			fis.close();        
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	private ComedorGUI principal;
	public Impresora(ComedorGUI comedorGUI){
		this.principal= comedorGUI;
	}
}
