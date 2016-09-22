package comedor.pdfs;

import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

public class PDF_Faltas {

	private static Document document;
	private static PdfPCell celda;
	private static Paragraph t2;

	public PDF_Faltas(ArrayList<Persona> arrayList, java.util.Date date,
			java.util.Date date2,ComedorGUI principal) throws FileNotFoundException, DocumentException {
		// TODO Auto-generated constructor stub
		// TODO Auto-generated method stub
				document = new Document();
				PdfWriter.getInstance(document,
						new FileOutputStream("System-Comedor"+File.separator+"Temp"+File.separator+"listado_faltas.pdf"));
				document.open();
				Paragraph texto22 = new Paragraph("FALTAS DE ASISTENCIA AL COMEDOR", FontFactory.getFont("arial",12,
						Font.NORMAL, BaseColor.BLACK));
				texto22.setAlignment(Chunk.ALIGN_CENTER);
				document.add(texto22);
				
				String fecha1=principal.getBaseDeDatos().getFechaHumana(date);
				String fecha2=principal.getBaseDeDatos().getFechaHumana(date2);
				Paragraph texto212 = new Paragraph("Desde: "+fecha1+" Hasta:"+fecha2+"", FontFactory.getFont("arial",9,
						Font.NORMAL, BaseColor.BLACK));
				texto212.setAlignment(Chunk.ALIGN_CENTER);
				document.add(texto212);
				document.add(new Paragraph("\n"));
				
				
				
				
				PdfPTable tabla = new PdfPTable(4);// tabla
				tabla.setWidthPercentage(105);
				float[] medidaCeldas = { 0.5f, 0.5f, 2.0f, 0.6f };
				tabla.setWidths(medidaCeldas);
				int tam=7;
				celda = new PdfPCell(new Paragraph("NIA", FontFactory.getFont("arial", // fuente
						tam, // tamaño
						Font.NORMAL, // estilo
						BaseColor.BLACK))); // color
				celda.setColspan(1);// abarca 1 fila
				celda.setHorizontalAlignment(Element.ALIGN_CENTER);
				celda.setPadding(5.4f);
				celda.setBackgroundColor(new BaseColor(new Color(230, 223, 223)));
				tabla.addCell(celda);
				celda = new PdfPCell(new Paragraph("GRUP", FontFactory.getFont("arial", // fuente
						tam, // tamaño
						Font.NORMAL, // estilo
						BaseColor.BLACK))); // color
				celda.setColspan(1);// abarca 1 fila
				celda.setHorizontalAlignment(Element.ALIGN_CENTER);
				celda.setPadding(5.4f);
				celda.setBackgroundColor(new BaseColor(new Color(230, 223, 223)));
				tabla.addCell(celda);
				celda = new PdfPCell(new Paragraph("APELLIDOS Y NOMBRE", FontFactory.getFont("arial", // fuente
						tam, // tamaño
						Font.NORMAL, // estilo
						BaseColor.BLACK))); // color
				celda.setColspan(1);// abarca 1 fila
				celda.setHorizontalAlignment(Element.ALIGN_CENTER);
				celda.setPadding(5.4f);
				celda.setBackgroundColor(new BaseColor(new Color(230, 223, 223)));
				tabla.addCell(celda);
				celda = new PdfPCell(new Paragraph("NUMERO DE AUSENCIAS", FontFactory.getFont("arial", // fuente
						6, // tamaño
						Font.NORMAL, // estilo
						BaseColor.BLACK))); // color
				celda.setColspan(1);// abarca 1 fila
				celda.setHorizontalAlignment(Element.ALIGN_CENTER);
				celda.setVerticalAlignment(Element.ALIGN_CENTER);
//				celda.set
				celda.setPadding(5.4f);
				celda.setBackgroundColor(new BaseColor(new Color(230, 223, 223)));
				tabla.addCell(celda);
				
				int count=0;
				for(int index=0;index<arrayList.size();index++){
					Persona p=arrayList.get(index);
					t2 = new Paragraph(""+p.getNia(), FontFactory.getFont("arial", // fuente
							11, // tamaño
							Font.NORMAL, // estilo
							BaseColor.BLACK));
					tabla.addCell(t2);
					t2 = new Paragraph(p.getGrupo(), FontFactory.getFont("arial", // fuente
							11, // tamaño
							Font.NORMAL, // estilo
							BaseColor.BLACK));
					tabla.addCell(t2);
					t2 = new Paragraph(p.getApellido1()+" "+p.getApellido2()+", "+p.getNombres(), FontFactory.getFont("arial", // fuente
							11, // tamaño
							Font.NORMAL, // estilo
							BaseColor.BLACK));
					tabla.addCell(t2);
					t2 = new Paragraph(""+p.getFaltas(), FontFactory.getFont("arial", // fuente
							11, // tamaño
							Font.NORMAL, // estilo
							BaseColor.BLACK));
					count+=+p.getFaltas();
					
					tabla.addCell(t2);
				}
				document.add(tabla);
				document.add(new Paragraph("\n"));
				PdfPTable tablatotal = new PdfPTable(1);// tabla1
				tablatotal.setHorizontalAlignment(Element.ALIGN_RIGHT);
				tablatotal.getDefaultCell()
						.setHorizontalAlignment(Element.ALIGN_CENTER);
				float medidas[]={0.9f};
				tablatotal.setWidths(medidas);

				tablatotal.setWidthPercentage(32.5f);
				PdfPCell uc1 = new PdfPCell(new Paragraph("Ausencias Totales: "+count, FontFactory.getFont(
						"arial", // fuente
						8, // tamaño
						Font.BOLD, // estilo
						BaseColor.BLACK))); // color

				uc1.setColspan(1);// abarca 1 fila

				uc1.setHorizontalAlignment(Element.ALIGN_CENTER);
				uc1.setVerticalAlignment(Element.ALIGN_CENTER);
				// celda1.setPadding (30.4f);

				// celda1.setBackgroundColor(new BaseColor(new Color(230,223,223)));
				tablatotal.addCell(uc1);


//				tablatotal.addCell("");
				document.add(tablatotal);
				
				document.close();

				if(principal.getBaseDeDatos().getImpresionDirecta()){
					  new Impresora(principal).print(new File("System-Comedor"+File.separator+"Temp"+File.separator+"listado_faltas.pdf").toString());
				}else{
					try {
						Desktop.getDesktop().open(
								new File("System-Comedor"+File.separator+"Temp"+File.separator+"listado_faltas.pdf"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	}

}
