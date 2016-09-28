package comedor.pdfs;

import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import comedor.ComedorGUI;
import comedor.Impresora;

public class PDF_RecibosPendientes 
{
	private static Document document;
	private static PdfPCell celda;
	private static Paragraph t2;

	public PDF_RecibosPendientes(ArrayList<Persona> arrayList, ComedorGUI principal) throws FileNotFoundException, DocumentException 
	{
		document = new Document();
		PdfWriter.getInstance(document,
				new FileOutputStream("System-Comedor" + File.separator + "Temp" + File.separator + "listado_pendientes.pdf"));
		document.open();

		Paragraph texto22 = new Paragraph("estado de recibos pendientes de cobro".toUpperCase(), FontFactory.getFont("arial",12, Font.NORMAL, BaseColor.BLACK));
		texto22.setAlignment(Chunk.ALIGN_CENTER);
		document.add(texto22);
		document.add(new Paragraph("\n\n"));

		PdfPTable tabla = new PdfPTable(2);
		tabla.setWidthPercentage(105);
		float[] medidaCeldas = { 1.5f, 0.5f };
		tabla.setWidths(medidaCeldas);
		int tam = 7;

		celda = new PdfPCell(new Paragraph("Apellidos y Nombre", FontFactory.getFont("arial", tam, Font.NORMAL, BaseColor.BLACK)));
		celda.setColspan(1);// abarca 1 fila
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(5.4f);
		celda.setBackgroundColor(new BaseColor(new Color(230, 223, 223)));
		tabla.addCell(celda);

		celda = new PdfPCell(new Paragraph("Debe", FontFactory.getFont("arial", tam, Font.NORMAL, BaseColor.BLACK))); 
		celda.setColspan(1);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(5.4f);
		celda.setBackgroundColor(new BaseColor(new Color(230, 223, 223)));
		tabla.addCell(celda);

		for(int index = 0; index < arrayList.size(); index++)
		{
			Persona p = arrayList.get(index);
			t2 = new Paragraph(""+p.getNombreCompleto(), FontFactory.getFont("arial", 11, Font.NORMAL, BaseColor.BLACK));
			tabla.addCell(t2);
			DecimalFormat df = new DecimalFormat("###.##");

			t2 = new Paragraph(df.format(p.getDeuda())+"€", FontFactory.getFont("arial", 11, Font.NORMAL, BaseColor.BLACK));
			tabla.addCell(t2);
		}
		document.add(tabla);
		document.close();

		if(principal.getBaseDeDatos().getImpresionDirecta())
			new Impresora(principal).print(new File("System-Comedor"+File.separator+"Temp"+File.separator+"listado_pendientes.pdf").toString());
		else
		{
			try 
			{
				Desktop.getDesktop().open(new File("System-Comedor"+File.separator+"Temp"+File.separator+"listado_pendientes.pdf"));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
}