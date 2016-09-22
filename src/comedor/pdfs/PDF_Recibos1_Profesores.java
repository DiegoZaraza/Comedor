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
//import comedor.pdfs.PDF_Listado.FooterPiePaginaiText;

public class PDF_Recibos1_Profesores {

	private static Document document;
	private static PdfPCell celda;
	private static Paragraph t2;
	private PdfPCell uc1;

	public PDF_Recibos1_Profesores(ArrayList<String> arrayList, ComedorGUI principal) throws FileNotFoundException, DocumentException {
		// TODO Auto-generated constructor stub
		// TODO Auto-generated method stub
				document = new Document();
				PdfWriter.getInstance(document,
						new FileOutputStream("System-Comedor"+File.separator+"Temp"+File.separator+"listado_recibos.pdf"));
				document.open();
				
				Paragraph texto22 = new Paragraph("LISTADO POR RECIBO", FontFactory.getFont("arial",12,
						Font.BOLD, BaseColor.BLACK));
				texto22.setAlignment(Chunk.ALIGN_CENTER);
				document.add(texto22);
			
				document.add(new Paragraph("\n"));
				
				
				
				
				PdfPTable tabla = new PdfPTable(4);// tabla
				tabla.setWidthPercentage(105);
				float[] medidaCeldas = { 3f, 0.9f, 0.9f, 0.6f };
				tabla.setWidths(medidaCeldas);
				int tam=7;
				celda = new PdfPCell(new Paragraph("Apelidos y Nombre", FontFactory.getFont("arial", // fuente
						tam, // tamaño
						Font.NORMAL, // estilo
						BaseColor.BLACK))); // color
				celda.setColspan(1);// abarca 1 fila
				celda.setHorizontalAlignment(Element.ALIGN_CENTER);
				celda.setVerticalAlignment(Element.ALIGN_CENTER);
				celda.setPadding(5.4f);
				celda.setBackgroundColor(new BaseColor(new Color(230, 223, 223)));
				tabla.addCell(celda);
				
				celda = new PdfPCell(new Paragraph("Dias Ordinarios", FontFactory.getFont("arial", // fuente
						tam, // tamaño
						Font.NORMAL, // estilo
						BaseColor.BLACK))); // color
				celda.setColspan(1);// abarca 1 fila
				celda.setHorizontalAlignment(Element.ALIGN_CENTER);
				celda.setPadding(5.4f);
				celda.setBackgroundColor(new BaseColor(new Color(230, 223, 223)));
				tabla.addCell(celda);
				celda = new PdfPCell(new Paragraph("Dias Extraordinarios", FontFactory.getFont("arial", // fuente
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
				celda = new PdfPCell(new Paragraph("Deusa Pendiente", FontFactory.getFont("arial", // fuente
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
				
				int sumaOrdinarios=0;
				int sumaExtraOrdinarios=0;
				for(int index=0;index<arrayList.size();index++){
					String p=arrayList.get(index);
					String nombre =p.split("@2")[0];
					String dias=""+p.split("@2")[1];
					String diasOrdinarios =""+dias.split("@1")[0];
					String diasExtraOrdinarios=dias.split("@1")[1];
					String deuda = p.split("@2")[2];
					DecimalFormat df = new DecimalFormat("###.##");
					t2 = new Paragraph(""+nombre, FontFactory.getFont("arial", // fuente
							11, // tamaño
							Font.NORMAL, // estilo
							BaseColor.BLACK));
					tabla.addCell(t2);
					sumaOrdinarios+=Integer.parseInt(diasOrdinarios);
					t2 = new Paragraph(diasOrdinarios, FontFactory.getFont("arial", // fuente
							11, // tamaño
							Font.NORMAL, // estilo
							BaseColor.BLACK));
					tabla.addCell(t2);
					sumaExtraOrdinarios+=Integer.parseInt(diasExtraOrdinarios);
					t2 = new Paragraph(diasExtraOrdinarios, FontFactory.getFont("arial", // fuente
							11, // tamaño
							Font.NORMAL, // estilo
							BaseColor.BLACK));
					tabla.addCell(t2);
					float m=Float.parseFloat(deuda);
					t2 = new Paragraph(""+df.format(m)+"€", FontFactory.getFont("arial", // fuente
							11, // tamaño
							Font.NORMAL, // estilo
							BaseColor.BLACK));
					tabla.addCell(t2);
				}
				document.add(tabla);
				document.add(new Paragraph("\n"));
				
				
				PdfPTable tablatotal = new PdfPTable(2);// tabla1
				tablatotal.setHorizontalAlignment(Element.ALIGN_RIGHT);
				tablatotal.getDefaultCell()
						.setHorizontalAlignment(Element.ALIGN_CENTER);
				float medidas[]={2.4f,2.4f};
				tablatotal.setWidths(medidas);

				tablatotal.setWidthPercentage(83f);
				float c= principal.getBaseDeDatos().getPrecioMenu(principal.getBaseDeDatos().getCursoActual());
				DecimalFormat df = new DecimalFormat("###.##");
				uc1 = new PdfPCell(new Paragraph("Suma Total dias Ordinarios: "+sumaOrdinarios+"  " +
						"IMPORTE:[ "+df.format(sumaOrdinarios*c)+"€ ]", FontFactory.getFont(
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
			
				uc1 = new PdfPCell(new Paragraph("Suma Total dias ExtraOrdinarios: "+(sumaExtraOrdinarios)+"  IMPORTE:[ "+df.format((sumaExtraOrdinarios*c))+"€ ]", FontFactory.getFont(
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
				
////				uc1 = new PdfPCell(new Paragraph("Suma Total: "+(sumaOrdinarios+sumaExtraOrdinarios), FontFactory.getFont(
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
//			
//				uc1 = new PdfPCell(new Paragraph("  "+df.format(c)+"€", FontFactory.getFont(
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
				
				
				
//				tablatotal.addCell("");
				document.add(tablatotal);
				
				document.close();

				if(principal.getBaseDeDatos().getImpresionDirecta()){
					  new Impresora(principal).print(new File("System-Comedor"+File.separator+"Temp"+File.separator+"listado" +
					  		"_recibos.pdf").toString());
				}else{
					try {
						Desktop.getDesktop().open(
								new File("System-Comedor"+File.separator+"Temp"+File.separator+"listado_recibos.pdf"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	}

}
