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
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import comedor.ComedorGUI;
import comedor.Impresora;

public class PDF_Formato_Apuntarse {

	private Document document;
	
	private PdfPCell celda;
	
	public class FooterPiePaginaiText extends  PdfPageEventHelper {
		 @Override
		 public void onEndPage(PdfWriter writer, Document document) {
		  ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Pagina:"+nPage), 42,20,0);
		  nPage++;
		 }
	}
	private int nPage=1;

	private PdfPTable tabla;

	private ArrayList<String> arrayList;
	public PDF_Formato_Apuntarse(ComedorGUI principal, ArrayList<Persona> personas2, String selected,String n) throws DocumentException{
		document = new Document();
		try {
			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream("System-Comedor"+File.separator+"Temp"+File.separator+""+n+".pdf"));
			 FooterPiePaginaiText footer = new FooterPiePaginaiText();
			   writer.setPageEvent(footer);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		document.setPageSize(PageSize.A4.rotate());
		document.setMargins(4, 10, 10, 20);
		document.open();
		{
		String mesx=""+selected.split("-")[1];
		Paragraph texto22 = new Paragraph("FORMATO PARA APUNTARSE PARA EL MES DE ", FontFactory.getFont("arial",12,
				Font.NORMAL, BaseColor.BLACK));
		texto22.setAlignment(Chunk.ALIGN_CENTER);
		
		Paragraph texto221x = new Paragraph(mesx, FontFactory.getFont("arial",12,
				Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
		texto221x.setAlignment(Chunk.ALIGN_CENTER);
		
		texto22.add(texto221x);
		document.add(texto22);
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
//		int inicial=Integer.parseInt(g.split("-")[2]);
//		System.out.println(mes);
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateFormat2= new SimpleDateFormat("EEEE");
		arrayList = new ArrayList<>();
		for(int index=1;index<=32;index++){
		String fecha= ano+"-"+mes+"-"+index;
		try {
			String h=new Date(dateFormat.parse(fecha).getTime()).toString();
			String m = h.split("-")[1];
			String dia=dateFormat2.format(new Date(dateFormat.parse(fecha).getTime())).toString();
			if(!dia.equalsIgnoreCase("sábado")&&!dia.equalsIgnoreCase("domingo")){
				if(m.equalsIgnoreCase(mes)){
//					System.out.println(h+" // "+dia);
					arrayList.add(h+"@1"+dia);
				}else{
//					System.out.println("FIN");
					break;
				}
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
		
//		[2016-01-01@1viernes, 2016-01-04@1lunes,
//		 2016-01-05@1martes, 2016-01-06@1miércoles, 2016-01-07@1jueves, 2016-01-08@1viernes, 2016-01-11@1lunes, 2016-01-12@1martes, 2016-01-13@1miércoles, 2016-01-14@1jueves, 2016-01-15@1viernes, 2016-01-18@1lunes, 2016-01-19@1martes, 2016-01-20@1miércoles, 2016-01-21@1jueves, 2016-01-22@1viernes, 2016-01-25@1lunes, 2016-01-26@1martes, 2016-01-27@1miércoles, 2016-01-28@1jueves, 2016-01-29@1viernes]

		System.out.println(arrayList);
		
		{
			PdfPTable tabla = new PdfPTable(arrayList.size()+1);// tabla
			tabla.setWidthPercentage(92);
			float[] medidaCeldas = new float[arrayList.size()+1];
			for(int index=0; index<medidaCeldas.length;index++){
				if(index==0){
					medidaCeldas[index] = 1.6f;
				}else
				medidaCeldas[index] = 0.075f;
			}
			tabla.setWidths(medidaCeldas);
			int tam=8;
			celda = new PdfPCell(new Paragraph("", FontFactory.getFont("arial", // fuente
					tam, // tamaño
					Font.NORMAL, // estilo
					BaseColor.BLACK))); // color
			celda.setColspan(1);// abarca 1 fila
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			celda.setPadding(5.4f);
//			celda.setBackgroundColor(new BaseColor(new Color(230, 223, 223)));
			tabla.addCell(celda);
			for(int index=0; index<arrayList.size(); index++){	
				int fg=Integer.parseInt(arrayList.get(index).split("@1")[0].split("-")[2]);
//System.out.println(fg);
					celda = new PdfPCell(new Paragraph(""+fg	, FontFactory.getFont("arial", // fuente
							6, // tamaño
							Font.NORMAL, // estilo
							BaseColor.BLACK))); // color
					celda.setColspan(1);// abarca 1 fila
					celda.setHorizontalAlignment(Element.ALIGN_CENTER);
					celda.setPadding(5.4f);
//					celda.setBackgroundColor(new BaseColor(new Color(230, 223, 223)));
				
				tabla.addCell(celda);
			}
		
			document.add(tabla);	
		}
	
	    tabla = new PdfPTable(arrayList.size()+1);// tabla
		tabla.setWidthPercentage(92);
		float[] medidaCeldas = new float[arrayList.size()+1];
		for(int index=0; index<medidaCeldas.length;index++){
			if(index==0){
				medidaCeldas[index] = 1.6f;
			}else
			medidaCeldas[index] = 0.075f;
		}
		tabla.setWidths(medidaCeldas);
		int tam=8;
		
		celda = new PdfPCell(new Paragraph("Apellidos y Nombre", FontFactory.getFont("arial", // fuente
				tam, // tamaño
				Font.NORMAL, // estilo
				BaseColor.BLACK))); // color
		celda.setColspan(1);// abarca 1 fila
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(5.4f);
		celda.setBackgroundColor(new BaseColor(new Color(230, 223, 223)));
		tabla.addCell(celda);
		
		for(int index=0; index<arrayList.size(); index++){
			if(arrayList.get(index).split("@1")[1].equalsIgnoreCase("jueves") ||arrayList.get(index).split("@1")[1].equalsIgnoreCase("viernes")){
				
				StringBuffer buffer=new StringBuffer(arrayList.get(index).split("@1")[1].toUpperCase());
				celda = new PdfPCell(new Paragraph(""+buffer	, FontFactory.getFont("arial", // fuente
						tam, // tamaño
						Font.BOLD, // estilo
						BaseColor.BLUE))); // color
				celda.setColspan(1);// abarca 1 fila
				celda.setHorizontalAlignment(Element.ALIGN_CENTER);
				celda.setPadding(5.4f);
				celda.setBackgroundColor(new BaseColor(new Color(230, 223, 223)));
			}else{
				StringBuffer buffer=new StringBuffer(arrayList.get(index).split("@1")[1].toUpperCase());
				celda = new PdfPCell(new Paragraph(""+buffer	, FontFactory.getFont("arial", // fuente
						tam, // tamaño
						Font.NORMAL, // estilo
						BaseColor.BLACK))); // color
				celda.setColspan(1);// abarca 1 fila
				celda.setHorizontalAlignment(Element.ALIGN_CENTER);
				celda.setPadding(5.4f);
				celda.setBackgroundColor(new BaseColor(new Color(230, 223, 223)));
			}
			tabla.addCell(celda);
		}
	}
		int p=26;
		boolean sig= false;
		int t=0;
		for(int indexxxxxxxxxx=0;indexxxxxxxxxx<personas2.size();indexxxxxxxxxx++){
			Paragraph t2=null;
			
				t2 = new Paragraph(personas2.get(indexxxxxxxxxx).getNombreCompleto(), FontFactory.getFont("arial", // fuente
					10, // tamaño
					Font.NORMAL, // estilo
					BaseColor.BLACK));
				tabla.addCell(t2);
			for(int index1=0;index1<arrayList.size();index1++){

						t2 = new Paragraph("", FontFactory.getFont("arial", // fuente
								10, // tamaño
								Font.NORMAL, // estilo
								BaseColor.BLACK));
					
					tabla.addCell(t2);
			}
			if(indexxxxxxxxxx==p){
				sig= true;
				t=indexxxxxxxxxx;
				break;
				
			}
		}
		if(sig){
			document.add(tabla);
			document.newPage();
			p+=26;
			{
				String mesx=""+selected.split("-")[1];
				Paragraph texto22 = new Paragraph("FORMATO PARA APUNTARSE PARA EL MES DE ", FontFactory.getFont("arial",12,
						Font.NORMAL, BaseColor.BLACK));
				texto22.setAlignment(Chunk.ALIGN_CENTER);
				
				Paragraph texto221x = new Paragraph(mesx, FontFactory.getFont("arial",12,
						Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
				texto221x.setAlignment(Chunk.ALIGN_CENTER);
				
				texto22.add(texto221x);
				document.add(texto22);
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
				arrayList = new ArrayList<>();
				for(int index=1;index<=32;index++){
				String fecha= ano+"-"+mes+"-"+index;
				try {
					String h=new Date(dateFormat.parse(fecha).getTime()).toString();
					String m = h.split("-")[1];
					String dia=dateFormat2.format(new Date(dateFormat.parse(fecha).getTime())).toString();
					if(!dia.equalsIgnoreCase("sábado")&&!dia.equalsIgnoreCase("domingo")){
						if(m.equalsIgnoreCase(mes)){
//							System.out.println(h+" // "+dia);
							arrayList.add(h+"@1"+dia);
						}else{
//							System.out.println("FIN");
							break;
						}
					}
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					}
				}
				
				{
					PdfPTable tabla = new PdfPTable(arrayList.size()+1);// tabla
					tabla.setWidthPercentage(92);
					float[] medidaCeldas = new float[arrayList.size()+1];
					for(int index=0; index<medidaCeldas.length;index++){
						if(index==0){
							medidaCeldas[index] = 1.6f;
						}else
						medidaCeldas[index] = 0.075f;
					}
					tabla.setWidths(medidaCeldas);
					int tam=8;
					celda = new PdfPCell(new Paragraph("", FontFactory.getFont("arial", // fuente
							tam, // tamaño
							Font.NORMAL, // estilo
							BaseColor.BLACK))); // color
					celda.setColspan(1);// abarca 1 fila
					celda.setHorizontalAlignment(Element.ALIGN_CENTER);
					celda.setPadding(5.4f);
//					celda.setBackgroundColor(new BaseColor(new Color(230, 223, 223)));
					tabla.addCell(celda);
					for(int index=0; index<arrayList.size(); index++){	
						int fg=Integer.parseInt(arrayList.get(index).split("@1")[0].split("-")[2]);
		//System.out.println(fg);
							celda = new PdfPCell(new Paragraph(""+fg	, FontFactory.getFont("arial", // fuente
									6, // tamaño
									Font.NORMAL, // estilo
									BaseColor.BLACK))); // color
							celda.setColspan(1);// abarca 1 fila
							celda.setHorizontalAlignment(Element.ALIGN_CENTER);
							celda.setPadding(5.4f);
//							celda.setBackgroundColor(new BaseColor(new Color(230, 223, 223)));
						
						tabla.addCell(celda);
					}
				
					document.add(tabla);	
				}
			
			    tabla = new PdfPTable(arrayList.size()+1);// tabla
				tabla.setWidthPercentage(92);
				float[] medidaCeldas = new float[arrayList.size()+1];
				for(int index=0; index<medidaCeldas.length;index++){
					if(index==0){
						medidaCeldas[index] = 1.6f;
					}else
					medidaCeldas[index] = 0.075f;
				}
				tabla.setWidths(medidaCeldas);
				int tam=8;
				
				celda = new PdfPCell(new Paragraph("Apellidos y Nombre", FontFactory.getFont("arial", // fuente
						tam, // tamaño
						Font.NORMAL, // estilo
						BaseColor.BLACK))); // color
				celda.setColspan(1);// abarca 1 fila
				celda.setHorizontalAlignment(Element.ALIGN_CENTER);
				celda.setPadding(5.4f);
				celda.setBackgroundColor(new BaseColor(new Color(230, 223, 223)));
				tabla.addCell(celda);
				
				for(int index=0; index<arrayList.size(); index++){
					if(arrayList.get(index).split("@1")[1].equalsIgnoreCase("jueves") ||arrayList.get(index).split("@1")[1].equalsIgnoreCase("viernes")){
						
						StringBuffer buffer=new StringBuffer(arrayList.get(index).split("@1")[1].toUpperCase());
						celda = new PdfPCell(new Paragraph(""+buffer	, FontFactory.getFont("arial", // fuente
								tam, // tamaño
								Font.BOLD, // estilo
								BaseColor.BLUE))); // color
						celda.setColspan(1);// abarca 1 fila
						celda.setHorizontalAlignment(Element.ALIGN_CENTER);
						celda.setPadding(5.4f);
						celda.setBackgroundColor(new BaseColor(new Color(230, 223, 223)));
					}else{
						StringBuffer buffer=new StringBuffer(arrayList.get(index).split("@1")[1].toUpperCase());
						celda = new PdfPCell(new Paragraph(""+buffer	, FontFactory.getFont("arial", // fuente
								tam, // tamaño
								Font.NORMAL, // estilo
								BaseColor.BLACK))); // color
						celda.setColspan(1);// abarca 1 fila
						celda.setHorizontalAlignment(Element.ALIGN_CENTER);
						celda.setPadding(5.4f);
						celda.setBackgroundColor(new BaseColor(new Color(230, 223, 223)));
					}
					tabla.addCell(celda);
				}
			}
			sig=false;
				
				for(int indexxxxxxxxxx=t+1;indexxxxxxxxxx<personas2.size();indexxxxxxxxxx++){
					Paragraph t2=null;
					
						t2 = new Paragraph(personas2.get(indexxxxxxxxxx).getNombreCompleto(), FontFactory.getFont("arial", // fuente
							10, // tamaño
							Font.NORMAL, // estilo
							BaseColor.BLACK));
						tabla.addCell(t2);
					for(int index1=0;index1<arrayList.size();index1++){

								t2 = new Paragraph("", FontFactory.getFont("arial", // fuente
										10, // tamaño
										Font.NORMAL, // estilo
										BaseColor.BLACK));
							
							tabla.addCell(t2);
					}
					if(indexxxxxxxxxx==p){
						sig= true;
						break;
						
					}
				}
				document.add(tabla);

			
			
			
		}else
		document.add(tabla);
		
		document.close();
		if(principal.getBaseDeDatos().getImpresionDirecta()){
			  new Impresora(principal).print(new File("System-Comedor"+File.separator+"Temp"+File.separator+""+n+".pdf").toString());
		}else{
			try {
				Desktop.getDesktop().open(
						new File("System-Comedor"+File.separator+"Temp"+File.separator+""+n+".pdf"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


}
