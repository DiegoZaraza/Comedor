package comedor.pdfs;

import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

public class PDF_Formato_Salir {

	private static Document document;
	private static PdfPCell celda;
	private static Paragraph t2;

	public PDF_Formato_Salir(ComedorGUI principal,String mes_,String selected, ArrayList<Persona> arrayList2) throws FileNotFoundException, DocumentException {
		// TODO Auto-generated constructor stub
		// TODO Auto-generated method stub
				document = new Document();
				PdfWriter.getInstance(document,
						new FileOutputStream("System-Comedor"+File.separator+"Temp"+File.separator+"listado_formatox.pdf"));
				document.open();
				Paragraph texto22 = new Paragraph("NO USUARIOS DE COMEDOR CON PERMISO PARA SALIR.",
						FontFactory.getFont("arial",12,
						Font.NORMAL, BaseColor.BLACK));
				texto22.setAlignment(Chunk.ALIGN_LEFT);
				document.add(texto22);
				
				
				Paragraph texto212 = new Paragraph("MES: "+mes_, FontFactory.getFont("arial",12,
						Font.NORMAL, BaseColor.BLACK));
				texto212.setAlignment(Chunk.ALIGN_LEFT);
				document.add(texto212);
				document.add(new Paragraph("\n"));
				
				
				
				Date date= new Date(new java.util.Date().getTime());
				
				String g= date.toString();
				String ano=g.split("-")[0];
				int y= Integer.parseInt(selected.split("-")[0]);
				String tem="";
				if(y<=9){
					tem="0"+y;
				}else{
					tem=""+y;
				}
				String mes = tem;
//				int inicial=Integer.parseInt(g.split("-")[2]);
//				System.out.println(mes);
				SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat dateFormat2= new SimpleDateFormat("EEEE");
				ArrayList<String> arrayList = new ArrayList<>();
				for(int index=1;index<=32;index++){
				String fecha= ano+"-"+mes+"-"+index;
				try {
					String h=new Date(dateFormat.parse(fecha).getTime()).toString();
					String m = h.split("-")[1];
					String dia=dateFormat2.format(new Date(dateFormat.parse(fecha).getTime())).toString();
					if(!dia.equalsIgnoreCase("sábado")&&!dia.equalsIgnoreCase("domingo")&&!
							dia.equalsIgnoreCase("MIÉRCOLES".toLowerCase())&&!dia.equalsIgnoreCase("jueves")&&!dia.equalsIgnoreCase("viernes")){
						if(m.equalsIgnoreCase(mes)){
//							System.out.println(h+" // "+dia);
							arrayList.add(h+"@1"+dia);
						}else{
//							System.out.println("FIN");
							break;
						}
					}
					
				} catch (ParseException e) {
					
					e.printStackTrace();
					}
				}
//				System.out.println("Table>"+(3+arrayList.size()));
				PdfPTable tabla = new PdfPTable((3+arrayList.size()));// tabla
				
				tabla.setWidthPercentage(105);
				float[] medidaCeldas = new float[(3+arrayList.size())];
//				System.out.println("Medidas>"+medidaCeldas.length);
				
				for(int index=0; index<medidaCeldas.length; index++){
					if(index==0){
						medidaCeldas[0]=1.3f;
					}else
					if(index==1){
						medidaCeldas[1]=1.0f;
					}else
					if(index==2){
						medidaCeldas[2]=0.8f;
					}else{
						medidaCeldas[index]=0.4f;
					}
					
				}
				tabla.setWidths(medidaCeldas);
				int tam=7;
				celda = new PdfPCell(new Paragraph("Apellidos Alumno", FontFactory.getFont("arial", // fuente
						tam, // tamaño
						Font.NORMAL, // estilo
						BaseColor.BLACK))); // color
				celda.setColspan(1);// abarca 1 fila
				celda.setHorizontalAlignment(Element.ALIGN_CENTER);
				celda.setPadding(5.4f);
				celda.setBackgroundColor(new BaseColor(new Color(230, 223, 223)));
				tabla.addCell(celda);
				celda = new PdfPCell(new Paragraph("Nombre Alumno", FontFactory.getFont("arial", // fuente
						tam, // tamaño
						Font.NORMAL, // estilo
						BaseColor.BLACK))); // color
				celda.setColspan(1);// abarca 1 fila
				celda.setHorizontalAlignment(Element.ALIGN_CENTER);
				celda.setPadding(5.4f);
				celda.setBackgroundColor(new BaseColor(new Color(230, 223, 223)));
				tabla.addCell(celda);
				celda = new PdfPCell(new Paragraph("Grupo", FontFactory.getFont("arial", // fuente
						tam, // tamaño
						Font.NORMAL, // estilo
						BaseColor.BLACK))); // color
				celda.setColspan(1);// abarca 1 fila
				celda.setHorizontalAlignment(Element.ALIGN_CENTER);
				celda.setPadding(5.4f);
				celda.setBackgroundColor(new BaseColor(new Color(230, 223, 223)));
				tabla.addCell(celda);
//				[2016-01-01@1viernes, 2016-01-04@1lunes, 2016-01-05@1martes, 2016-01-06@1miércoles, 2016-01-07@1jueves, 2016-01-08@1viernes, 2016-01-11@1lunes, 2016-01-12@1martes, 2016-01-13@1miércoles, 2016-01-14@1jueves, 2016-01-15@1viernes, 2016-01-18@1lunes, 2016-01-19@1martes, 2016-01-20@1miércoles, 2016-01-21@1jueves, 2016-01-22@1viernes, 2016-01-25@1lunes, 2016-01-26@1martes, 2016-01-27@1miércoles, 2016-01-28@1jueves, 2016-01-29@1viernes]

				for(int index=0; index<arrayList.size(); index++){
					celda = new PdfPCell(new Paragraph(""+String.valueOf(arrayList.get(index).split("@1")[1].charAt(0)).toUpperCase()+""+arrayList.get(index).split("@1")[0].split("-")[2], FontFactory.getFont("arial", // fuente
							tam, // tamaño
							Font.NORMAL, // estilo
							BaseColor.BLACK))); // color
					celda.setColspan(1);// abarca 1 fila
					celda.setHorizontalAlignment(Element.ALIGN_CENTER);
					celda.setPadding(5.4f);
					celda.setBackgroundColor(new BaseColor(new Color(230, 223, 223)));
					tabla.addCell(celda);
				}
				
//				
//				int count=0;
				for(int index=0;index<arrayList2.size();index++){
					Persona p=arrayList2.get(index);
					t2 = new Paragraph(""+p.getApellidos(), FontFactory.getFont("arial", // fuente
							11, // tamaño
							Font.NORMAL, // estilo
							BaseColor.BLACK));
					tabla.addCell(t2);
					t2 = new Paragraph(p.getNombres(), FontFactory.getFont("arial", // fuente
							11, // tamaño
							Font.NORMAL, // estilo
							BaseColor.BLACK));
					tabla.addCell(t2);
					t2 = new Paragraph(p.getGrupo(), FontFactory.getFont("arial", // fuente
							11, // tamaño
							Font.NORMAL, // estilo
							BaseColor.BLACK));
					tabla.addCell(t2);
					
					
					for(int index1=0;index1<arrayList.size();index1++){
						t2 = new Paragraph("", FontFactory.getFont("arial", // fuente
								11, // tamaño
								Font.NORMAL, // estilo
								BaseColor.BLACK));
						tabla.addCell(t2);
					}
					
					
				
					
					
//					t2 = new Paragraph(""+p.getFaltas(), FontFactory.getFont("arial", // fuente
//							11, // tamaño
//							Font.NORMAL, // estilo
//							BaseColor.BLACK));
//					count+=+p.getFaltas();
//					
//					tabla.addCell(t2);
				}
				t2 = new Paragraph("", FontFactory.getFont("arial", // fuente
						11, // tamaño
						Font.NORMAL, // estilo
						BaseColor.BLACK));
				tabla.addCell(t2);
				t2 = new Paragraph("", FontFactory.getFont("arial", // fuente
						11, // tamaño
						Font.NORMAL, // estilo
						BaseColor.BLACK));
				tabla.addCell(t2);
				t2 = new Paragraph("CONSERJE", FontFactory.getFont("arial", // fuente
						11, // tamaño
						Font.NORMAL, // estilo
						BaseColor.BLACK));
				tabla.addCell(t2);
				for(int index1=0;index1<arrayList.size();index1++){
					t2 = new Paragraph("", FontFactory.getFont("arial", // fuente
							11, // tamaño
							Font.NORMAL, // estilo
							BaseColor.BLACK));
					tabla.addCell(t2);
				}
				document.add(tabla);
				document.add(new Paragraph("\n"));
				
				document.close();

				if(principal.getBaseDeDatos().getImpresionDirecta()){
					  new Impresora(principal).print(new File("System-Comedor"+File.separator+"Temp"+File.separator+"listado_formatox.pdf").toString());
				}else{
					try {
						Desktop.getDesktop().open(
								new File("System-Comedor"+File.separator+"Temp"+File.separator+"listado_formatox.pdf"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	}

}
