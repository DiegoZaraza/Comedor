package comedor.pdfs;

import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

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

public class PDF_Listado_Usuarios 
{

	private PdfPTable tabla;
	private Document document;
	public PDF_Listado_Usuarios( final ComedorGUI principal,ArrayList<Persona> arrayList) throws FileNotFoundException, DocumentException
	{
		document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream("System-Comedor"+File.separator+"Temp"+File.separator+"listado_usuarios.pdf"));
		document.open();


		Paragraph texto22 = new Paragraph("LISTADO", FontFactory.getFont("arial",12,
				Font.BOLD, BaseColor.BLACK));
		texto22.setAlignment(Chunk.ALIGN_CENTER);
		document.add(texto22);

		document.add(new Paragraph("\n"));

		document.add(new Paragraph("\n"));

		tabla = new PdfPTable(5);// tabla
		tabla.setWidthPercentage(105);
		// float[] medidaCeldas = { 0.160f, 0.260f, 1.2f, 0.160f,0.340f,
		// 0.7f,0.3f };

		float[] medidaCeldas = { 0.210f, 0.360f, 1.6f, 0.5f, 1.2f };
		tabla.setWidths(medidaCeldas);
		// encabezado
		PdfPCell celda = new PdfPCell(new Paragraph("",
				FontFactory.getFont("arial", // fuente
						11, // tama�o
						Font.NORMAL, // estilo
						BaseColor.BLACK))); // color

		celda.setColspan(8);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(5.0f);
		celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
		celda.setBackgroundColor(new BaseColor(new Color(230, 223, 223)));

		tabla.addCell(celda);

		// columna2
		celda = new PdfPCell(new Paragraph("N�", FontFactory.getFont("arial", // fuente
				7, // tama�o
				Font.NORMAL, // estilo
				BaseColor.BLACK))); // color

		celda.setColspan(1);// abarca 1 fila
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(5.4f);

		celda.setBackgroundColor(new BaseColor(new Color(230, 223, 223)));
		tabla.addCell(celda);

		celda = new PdfPCell(new Paragraph("NIA", FontFactory.getFont(
				"arial", // fuente
				8, // tama�o
				Font.NORMAL, // estilo
				BaseColor.BLACK))); // color

		celda.setColspan(1);// abarca 1 fila
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(5.4f);

		celda.setBackgroundColor(new BaseColor(new Color(230, 223, 223)));
		tabla.addCell(celda);

		// columna3
		celda = new PdfPCell(new Paragraph("Apellidos Y Nombre",
				FontFactory.getFont("arial", // fuente
						10, // tama�o
						Font.NORMAL, // estilo
						BaseColor.BLACK))); // color

		celda.setColspan(1);// abarca 1 fila
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(5.0f);
		celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
		celda.setBackgroundColor(new BaseColor(new Color(230, 223, 223)));
		tabla.addCell(celda);

		// columna4
		celda = new PdfPCell(new Paragraph("Telefono", FontFactory.getFont(
				"arial", // fuente
				10, // tama�o
				Font.NORMAL, // estilo
				BaseColor.BLACK))); // color

		celda.setColspan(1);// abarca 1 fila
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(5.0f);
		celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
		celda.setBackgroundColor(new BaseColor(new Color(230, 223, 223)));
		tabla.addCell(celda);
		// columna4
		celda = new PdfPCell(new Paragraph("Email", FontFactory.getFont(
				"arial", // fuente
				10, // tama�o
				Font.NORMAL, // estilo
				BaseColor.BLACK))); // color

		celda.setColspan(1);// abarca 1 fila
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(5.0f);
		celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
		celda.setBackgroundColor(new BaseColor(new Color(230, 223, 223)));
		tabla.addCell(celda);

		int valor = 0;
		int findPase = 0;
		int dd = 1;
		for (int i = 0; i < arrayList.size(); i++) {

			Persona persona= arrayList.get(i);
			valor = i;
			findPase++;
			Paragraph a = new Paragraph("" + (valor+1) + "",
					FontFactory.getFont("arial", // fuente
							8, // tama�o
							Font.NORMAL, // estilo
							BaseColor.BLACK));
			Paragraph b = new Paragraph("" + persona.getNia() + "",
					FontFactory.getFont("arial", // fuente
							8, // tama�o
							Font.NORMAL, // estilo
							BaseColor.BLACK));

			Paragraph c = new Paragraph("" + persona.getNombreCompleto() + "",
					FontFactory.getFont("arial", // fuente
							8, // tama�o
							Font.NORMAL, // estilo
							BaseColor.BLACK));

			Paragraph d = new Paragraph("" +persona.getTelefono() + "",
					FontFactory.getFont("arial", // fuente
							8, // tama�o
							Font.NORMAL, // estilo
							BaseColor.BLACK));

			//			String cedula = A.getString1();

			Paragraph e = new Paragraph("" + persona.getEmail(), FontFactory.getFont(
					"arial", // fuente
					8, // tama�o
					Font.NORMAL, // estilo
					BaseColor.BLACK));

			tabla.addCell(a);
			tabla.addCell(b);
			tabla.addCell(c);
			tabla.addCell(d);
			tabla.addCell(e);

			String dia = "";
			String mes = "";
			String ano = "";

			if (findPase == 50) {
				Paragraph numpage = new Paragraph("Pagina: " + dd,
						FontFactory.getFont("arial", // fuente
								11, // tama�o
								Font.NORMAL, // estilo
								BaseColor.BLACK));
				numpage.setAlignment(Chunk.ALIGN_CENTER);
				Date f = new Date();

				java.sql.Date gg = new java.sql.Date(f.getTime());

				ano = gg.toString().split("-")[0];
				mes = gg.toString().split("-")[1];
				dia = gg.toString().split("-")[2];

				String fecha = dia + "/" + mes + "/" + ano;

				Paragraph fechai = new Paragraph(
						"Fecha de Impresi�n: " + fecha, FontFactory.getFont(
								"arial", // fuente
								11, // tama�o
								Font.NORMAL, // estilo
								BaseColor.BLACK));
				fechai.setAlignment(Chunk.ALIGN_LEFT);

				dd++;

				document.add(tabla);
				document.add(new Paragraph("\n"));
				document.add(fechai);
				document.add(numpage);
				document.newPage();




				tabla = new PdfPTable(5);// tabla
				tabla.setWidthPercentage(105);
				// float[] medidaCeldas = { 0.160f, 0.260f, 1.2f, 0.160f,0.340f,
				// 0.7f,0.3f };

				//				float[] medidaCeldas = { 0.210f, 0.360f, 1.6f, 0.5f, 1.2f };
				tabla.setWidths(medidaCeldas);
				// encabezado
				celda = new PdfPCell(new Paragraph("",
						FontFactory.getFont("arial", // fuente
								11, // tama�o
								Font.NORMAL, // estilo
								BaseColor.BLACK))); // color

				celda.setColspan(8);
				celda.setHorizontalAlignment(Element.ALIGN_CENTER);
				celda.setPadding(5.0f);
				celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
				celda.setBackgroundColor(new BaseColor(new Color(230, 223, 223)));

				tabla.addCell(celda);

				// columna2
				celda = new PdfPCell(new Paragraph("N�", FontFactory.getFont("arial", // fuente
						7, // tama�o
						Font.NORMAL, // estilo
						BaseColor.BLACK))); // color

				celda.setColspan(1);// abarca 1 fila
				celda.setHorizontalAlignment(Element.ALIGN_CENTER);
				celda.setPadding(5.4f);

				celda.setBackgroundColor(new BaseColor(new Color(230, 223, 223)));
				tabla.addCell(celda);

				celda = new PdfPCell(new Paragraph("NIA", FontFactory.getFont(
						"arial", // fuente
						8, // tama�o
						Font.NORMAL, // estilo
						BaseColor.BLACK))); // color

				celda.setColspan(1);// abarca 1 fila
				celda.setHorizontalAlignment(Element.ALIGN_CENTER);
				celda.setPadding(5.4f);

				celda.setBackgroundColor(new BaseColor(new Color(230, 223, 223)));
				tabla.addCell(celda);

				// columna3
				celda = new PdfPCell(new Paragraph("Apellidos Y Nombre",
						FontFactory.getFont("arial", // fuente
								10, // tama�o
								Font.NORMAL, // estilo
								BaseColor.BLACK))); // color

				celda.setColspan(1);// abarca 1 fila
				celda.setHorizontalAlignment(Element.ALIGN_CENTER);
				celda.setPadding(5.0f);
				celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
				celda.setBackgroundColor(new BaseColor(new Color(230, 223, 223)));
				tabla.addCell(celda);

				// columna4
				celda = new PdfPCell(new Paragraph("Telefono", FontFactory.getFont(
						"arial", // fuente
						10, // tama�o
						Font.NORMAL, // estilo
						BaseColor.BLACK))); // color

				celda.setColspan(1);// abarca 1 fila
				celda.setHorizontalAlignment(Element.ALIGN_CENTER);
				celda.setPadding(5.0f);
				celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
				celda.setBackgroundColor(new BaseColor(new Color(230, 223, 223)));
				tabla.addCell(celda);
				// columna4
				celda = new PdfPCell(new Paragraph("Email", FontFactory.getFont(
						"arial", // fuente
						10, // tama�o
						Font.NORMAL, // estilo
						BaseColor.BLACK))); // color

				celda.setColspan(1);// abarca 1 fila
				celda.setHorizontalAlignment(Element.ALIGN_CENTER);
				celda.setPadding(5.0f);
				celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
				celda.setBackgroundColor(new BaseColor(new Color(230, 223, 223)));
				tabla.addCell(celda);



				findPase = 1;
			}

		}

		// tabla.addCell(programa);

		document.add(tabla);

		int cantidad = 0;
		for (int i = 0; i < tabla.getRows().size(); i++) {
			cantidad++;
		}
		cantidad = cantidad + 1;
		while (cantidad <= 50) {
			document.add(new Paragraph("\n"));
			cantidad++;
		}

		Paragraph numpage1 = new Paragraph("Pagina: " + dd,
				FontFactory.getFont("arial", // fuente
						11, // tama�o
						Font.NORMAL, // estilo
						BaseColor.BLACK));
		numpage1.setAlignment(Chunk.ALIGN_CENTER);
		Date f = new Date();

		java.sql.Date gg = new java.sql.Date(f.getTime());

		String ano = gg.toString().split("-")[0];
		String mes = gg.toString().split("-")[1];
		String dia = gg.toString().split("-")[2];

		String fecha = dia + "/" + mes + "/" + ano;

		Paragraph fechai = new Paragraph("Fecha de Impresi�n: " + fecha,
				FontFactory.getFont("arial", // fuente
						11, // tama�o
						Font.NORMAL, // estilo
						BaseColor.BLACK));
		fechai.setAlignment(Chunk.ALIGN_LEFT);

		document.add(new Paragraph("\n"));
		document.add(fechai);
		document.add(numpage1);

		document.close();


		if(principal.getBaseDeDatos().getImpresionDirecta()){
			new Impresora(principal).print(new File("System-Comedor"+File.separator+"Temp"+File.separator+"listado_usuarios.pdf").toString());
		}else{
			try {
				Desktop.getDesktop().open(
						new File("System-Comedor"+File.separator+"Temp"+File.separator+"listado_usuarios.pdf"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//		return true;


	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
