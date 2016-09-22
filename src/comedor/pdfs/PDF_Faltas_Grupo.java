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

public class PDF_Faltas_Grupo {

	private static Document document;
	private static PdfPCell celda;
	private static Paragraph t2;

	
	public PDF_Faltas_Grupo(java.util.Date date,
			java.util.Date date2, ArrayList<ArrayList<String>> ar,ComedorGUI principal) throws FileNotFoundException, DocumentException {
		// TODO Auto-generated constructor stub
		// TODO Auto-generated method stub
				document = new Document();
				PdfWriter.getInstance(document,
						new FileOutputStream("System-Comedor"+File.separator+"Temp"+File.separator+"listado_faltas_alumno.pdf"));
				document.open();
				Paragraph texto22 = new Paragraph("FALTAS DE ASISTENCIA AL COMEDOR", FontFactory.getFont("arial",12,
						Font.NORMAL, BaseColor.BLACK));
				texto22.setAlignment(Chunk.ALIGN_CENTER);
				document.add(texto22);
				
				String fecha1=principal.getBaseDeDatos().getFechaHumana(date);
				String fecha2=principal.getBaseDeDatos().getFechaHumana(date2);
				Paragraph texto212M = new Paragraph("GRUPO: 1SOVA", FontFactory.getFont("arial",12,
						Font.NORMAL, BaseColor.BLACK));
				texto212M.setAlignment(Chunk.ALIGN_CENTER);
				document.add(texto212M);
				Paragraph texto212 = new Paragraph("Desde: "+fecha1+" Hasta:"+fecha2+"", FontFactory.getFont("arial",9,
						Font.NORMAL, BaseColor.BLACK));
				texto212.setAlignment(Chunk.ALIGN_CENTER);
				document.add(texto212);
				document.add(new Paragraph("\n"));
				
				
			
				for(int index=0; index<ar.size();index++){
//					System.out.println(index);
					ArrayList<String> a = ar.get(index);
					System.out.println(a.size());
					if(a.size()!=0){
				String nia= a.get(0).split("@1")[0];
				Paragraph texto22a = new Paragraph(""+principal.getBaseDeDatos().getApellidosYNombres(nia), FontFactory.getFont("arial",8,
						Font.NORMAL, BaseColor.BLACK));
				texto22a.setAlignment(Chunk.ALIGN_LEFT);
//				document.add(texto22a);
				Paragraph texto22ax = new Paragraph("\t\t\tNIA: "+nia, FontFactory.getFont("arial",8,
						Font.NORMAL, BaseColor.BLACK));
				texto22ax.setAlignment(Chunk.ALIGN_LEFT);
				texto22a.add(texto22ax);
//				document.add(texto22ax);
//				document.add(texto22a);
				document.add(new Paragraph("\n"));
				
				
				PdfPTable tabla = new PdfPTable(2);// tabla
				tabla.setWidthPercentage(105);
				float[] medidaCeldas = { 0.5f, 0.5f,};
				tabla.setWidths(medidaCeldas);
				int tam=7;
				
				celda = new PdfPCell(texto22a); // color
				celda.setColspan(1);// abarca 1 fila
				celda.setHorizontalAlignment(Element.ALIGN_LEFT);
				celda.setPadding(5.4f);
				celda.setColspan(8);
				celda.setBackgroundColor(new BaseColor(new Color(230, 223, 223)));
				tabla.addCell(celda);
				
				celda = new PdfPCell(new Paragraph("Fecha", FontFactory.getFont("arial", // fuente
						tam, // tamaño
						Font.NORMAL, // estilo
						BaseColor.BLACK))); // color
				celda.setColspan(1);// abarca 1 fila
				celda.setHorizontalAlignment(Element.ALIGN_CENTER);
				celda.setPadding(5.4f);
				celda.setBackgroundColor(new BaseColor(new Color(230, 223, 223)));
				tabla.addCell(celda);
				celda = new PdfPCell(new Paragraph("Observacion", FontFactory.getFont("arial", // fuente
						tam, // tamaño
						Font.NORMAL, // estilo
						BaseColor.BLACK))); // color
				celda.setColspan(1);// abarca 1 fila
				celda.setHorizontalAlignment(Element.ALIGN_CENTER);
				celda.setPadding(5.4f);
				celda.setBackgroundColor(new BaseColor(new Color(230, 223, 223)));
				tabla.addCell(celda);
				
//				int count=0;
//				System.out.println(a);
				for(int i=0;i<a.size();i++){
//				String pp=arrayList.get(index);
					System.out.println(i);
				String S = a.get(i);
				System.out.println(S);
//				tabla.addCell(t2);
//				System.out.println(S.split("@1")[1]);
					t2 = new Paragraph(S.split("@1")[1]+"/"+S.split("@1")[2], FontFactory.getFont("arial", // fuente
							11, // tamaño
							Font.NORMAL, // estilo
							BaseColor.BLACK));
					tabla.addCell(t2);
					t2 = new Paragraph(""+S.split("@1")[3], FontFactory.getFont("arial", // fuente
							11, // tamaño
							Font.NORMAL, // estilo
							BaseColor.BLACK));
					tabla.addCell(t2);
				
//					count+=+p.getFaltas();
					
//					tabla.addCell(t2);
						}
					
				document.add(tabla);
					}
				}
				document.add(new Paragraph("\n"));
//				PdfPTable tablatotal = new PdfPTable(1);// tabla1
//				tablatotal.setHorizontalAlignment(Element.ALIGN_RIGHT);
//				tablatotal.getDefaultCell()
//						.setHorizontalAlignment(Element.ALIGN_CENTER);
//				float medidas[]={0.9f};
//				tablatotal.setWidths(medidas);
//
//				tablatotal.setWidthPercentage(32.5f);
//				PdfPCell uc1 = new PdfPCell(new Paragraph("Ausencias Totales: "+count, FontFactory.getFont(
//						"arial", // fuente
//						8, // tamaño
//						Font.BOLD, // estilo
//						BaseColor.BLACK))); // color
//
//				uc1.setColspan(1);// abarca 1 fila
//
//				uc1.setHorizontalAlignment(Element.ALIGN_CENTER);
//				uc1.setVerticalAlignment(Element.ALIGN_CENTER);
//				// celda1.setPadding (30.4f);
//
//				// celda1.setBackgroundColor(new BaseColor(new Color(230,223,223)));
//				tablatotal.addCell(uc1);
//
//
////				tablatotal.addCell("");
//				document.add(tablatotal);
				
				document.close();
				try {
					Desktop.getDesktop().open(
							new File("System-Comedor"+File.separator+"Temp"+File.separator+"listado_faltas_alumno.pdf"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				if(principal.getBaseDeDatos().getImpresionDirecta()){
//					  new Impresora(principal).print(new File("System-Comedor"+File.separator+"Temp"+File.separator+"listado_faltas_alumno.pdf").toString());
//				}else{
//					try {
//						Desktop.getDesktop().open(
//								new File("System-Comedor"+File.separator+"Temp"+File.separator+"listado_faltas_alumno.pdf"));
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
	}

}
