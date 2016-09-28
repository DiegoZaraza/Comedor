package comedor.pdfs;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import comedor.ComedorGUI;
import comedor.Impresora;

public class PDF_Listado 
{
	private ComedorGUI principal;
	private Document document;

	public boolean crearPDF5(ComedorGUI gui, String curso, String codigoGrupo, String nombreGrupo, String tutor, ArrayList<Persona> alumnos, boolean f) throws Exception 
	{
		this.principal = gui;
		this.alumnos = alumnos;
		document = new Document();

		this.curso = curso;
		this.tutor = tutor;
		this.f = f;

		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("System-Comedor" +File.separator+"Temp"+File.separator+tutor + ".pdf"));
		FooterPiePaginaiText EncabezadoyPie = new FooterPiePaginaiText();
		writer.setPageEvent(EncabezadoyPie);
		document.open();

		addAlumnos(alumnos.size());

		document.close();
		
		if(principal.getBaseDeDatos().getImpresionDirecta())
			new Impresora(principal).print(new File("System-Comedor"+File.separator+"Temp"+File.separator+tutor + ".pdf").toString());
		else
			Desktop.getDesktop().open(new File("System-Comedor"+File.separator+"Temp"+File.separator+tutor + ".pdf"));

		return true;
	}


	private ArrayList<Persona> alumnos;
	private float x = 640f;
	private int b = 1;

	/** AJUSTES PARA LA GENERACION DE ORLAS, SE CAMBIA A USO DE TABLA PARA QUE ESTAS NO SALGAN DESCUADRADAS COMO ESTABA OCURRIENDO ANTERIORMENTE */
	public void addAlumnos(int j)
	{		
		try 
		{
			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));
		} 
		catch (DocumentException e1) 
		{
			e1.printStackTrace();
		}

		PdfPTable tabla1 = new PdfPTable(7);
		tabla1.getDefaultCell().setBorder(0);
		
		try
		{
			for(int i = 0; i < j; i++)
			{
				if(i % 35 == 0 && i != 0)
				{
					document.add(tabla1);
					tabla1 = new PdfPTable(7);
					tabla1.getDefaultCell().setBorder(0);
					
					document.newPage();
					
					document.add(new Paragraph("\n"));
					document.add(new Paragraph("\n"));
					document.add(new Paragraph("\n"));
					document.add(new Paragraph("\n"));
					document.add(new Paragraph("\n"));
					document.add(new Paragraph("\n"));
				}
				
				Persona nb = alumnos.get(i);			
				Image foto = null;

				if(nb.getBytesFoto() == null)
					foto = Image.getInstance(getClass().getResource("/resource/default1.gif"));
				else
					foto = Image.getInstance(nb.getBytesFoto());

				foto.scaleAbsolute(70, 70);
				foto.scaleToFit(70, 70);
				foto.setAbsolutePosition((39 * b), x);
				float border = 2.0f;
				foto.setBorderWidthBottom(border);
				foto.setBorderWidthLeft(border);
				foto.setBorderWidthLeft(border);
				foto.setBorderWidthRight(border);
				foto.setBorderWidthTop(border);
				foto.setBorderColor(BaseColor.BLACK);

				PdfPTable table2 = new PdfPTable(1);
				table2.getDefaultCell().setBorder(0);

				Paragraph nia = new Paragraph(nb.getNia(), FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.BLACK));
				Paragraph apellidos = new Paragraph(nb.getApellidos(), FontFactory.getFont("arial", 5, Font.NORMAL, BaseColor.BLACK));
				Paragraph nombre = new Paragraph(nb.getNombres(),FontFactory.getFont("arial",5, Font.NORMAL, BaseColor.BLACK));

				table2.addCell(foto);
				table2.addCell(nia);
				table2.addCell(apellidos);
				table2.addCell(nombre);				

				tabla1.addCell(table2);	
			}

			document.add(tabla1);
		} 
		catch (DocumentException e) 
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private String curso;
	private String tutor;
	private boolean f;

	/** SE AGREGA ENCABEZADO Y PIE DE PAGINA PARA LA IMPRESION EN TODAS LAS HOJAS DEL DOCUMENTO */
	public class FooterPiePaginaiText extends  PdfPageEventHelper 
	{
		@Override
		public void onStartPage(PdfWriter writer, Document document) 
		{      
			Paragraph texto1 = new Paragraph("" + principal.getBaseDeDatos().getCentro(), FontFactory.getFont("arial", 14, Font.BOLD, BaseColor.BLACK));
			Paragraph texto21 = new Paragraph("" + curso, FontFactory.getFont("arial", 9, Font.NORMAL, BaseColor.BLACK));
			Paragraph texto22 = new Paragraph("" + principal.getBaseDeDatos().getCodigoCentro(), FontFactory.getFont("arial", 9, Font.NORMAL, BaseColor.BLACK));
			
			texto21.setAlignment(Chunk.ALIGN_RIGHT);
			texto22.setAlignment(Chunk.ALIGN_RIGHT);
			texto1.setAlignment(Chunk.ALIGN_CENTER);

			Paragraph a11 = null;
			
			if(!f)
				a11 = new Paragraph("TUTOR/A: " + tutor, FontFactory.getFont("tahoma", 10, Font.NORMAL, BaseColor.BLACK));
			else
				a11 = new Paragraph(tutor, FontFactory.getFont("tahoma", 10, Font.NORMAL, BaseColor.BLACK));

			a11.setAlignment(Element.ALIGN_CENTER);

			ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, texto1, (document.right() - document.left()) / 2 + document.leftMargin(), document.top() - 20, 0);
			ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, texto21, document.right() - document.leftMargin(), document.top() - 20,0);
			ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, texto22, document.right() - document.leftMargin(), document.top() - 35, 0);
			ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, a11, (document.right() - document.left()) / 2 + document.leftMargin(), document.top() - 35, 0);    		
		}

		@Override
		public void onEndPage(PdfWriter writer, Document document) 
		{
			ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Pagina : " + nPage, FontFactory.getFont("arial", 9, Font.NORMAL, BaseColor.BLACK)), 55, 20, 0);
			nPage++;
		}
	}
	private int nPage=1;
}