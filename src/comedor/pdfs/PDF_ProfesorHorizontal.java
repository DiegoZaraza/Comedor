package comedor.pdfs;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import comedor.ComedorGUI;
import comedor.Impresora;
	
public class PDF_ProfesorHorizontal {


	private Document document;
	/**	
	 * @param args
	 * @throws DocumentException 
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	private int agregados=0;
	public int addAlumnos(int j,int index) throws MalformedURLException, IOException, DocumentException{
		{
		try{
			Persona nb=arrayList.get(j);	
			Image foto=null;
	
			
			 if(nb.getBytesFoto()==null){
				foto =Image.getInstance(getClass().getResource("/resource/default1.gif"));
			 }else
			     foto=Image.getInstance(nb.getBytesFoto());
				foto.scaleAbsoluteHeight(70);
				foto.scaleAbsoluteWidth(70);
			foto.scaleAbsolute(70, 70);
			foto.scaleToFit(70, 70);
		
			foto.setAbsolutePosition(16,426);
			float border=2.0f;
			foto.setBorderWidthBottom(border);
			foto.setBorderWidthLeft(border);
			foto.setBorderWidthLeft(border);
			foto.setBorderWidthRight(border);
			foto.setBorderWidthTop(border);
			foto.setBorderColor(BaseColor.BLACK);
			document.add(foto);	 
			agregados++;
		}catch(Exception e){
			
		   }
		  }
		{
			try{
				Persona nb=arrayList.get(j+1);	
				Image foto=null;
				 if(nb.getBytesFoto()==null){
					foto =Image.getInstance(getClass().getResource("/resource/default1.gif"));
				 }else
				     foto=Image.getInstance(nb.getBytesFoto());
					foto.scaleAbsoluteHeight(70);
					foto.scaleAbsoluteWidth(70);
				foto.scaleAbsolute(70, 70);
				foto.scaleToFit(70, 70);
				foto.setAbsolutePosition(16,350);
				float border=2.0f;
				foto.setBorderWidthBottom(border);
				foto.setBorderWidthLeft(border);
				foto.setBorderWidthLeft(border);
				foto.setBorderWidthRight(border);
				foto.setBorderWidthTop(border);
				foto.setBorderColor(BaseColor.BLACK);
				document.add(foto);	 
				agregados++;
			}catch(Exception e){
				
			}
		}
		{
			try{
				Persona nb=arrayList.get(j+2);	
				Image foto=null;
				 if(nb.getBytesFoto()==null){
					foto =Image.getInstance(getClass().getResource("/resource/default1.gif"));
				 }else
				     foto=Image.getInstance(nb.getBytesFoto());
					foto.scaleAbsoluteHeight(70);
					foto.scaleAbsoluteWidth(70);
				foto.scaleAbsolute(70, 70);
				foto.scaleToFit(70, 70);

				foto.setAbsolutePosition(16,276);
				float border=2.0f;
				foto.setBorderWidthBottom(border);
				foto.setBorderWidthLeft(border);
				foto.setBorderWidthLeft(border);
				foto.setBorderWidthRight(border);
				foto.setBorderWidthTop(border);
				foto.setBorderColor(BaseColor.BLACK);
				document.add(foto);	 
				agregados++;
			}catch(Exception e){
				
			}
		}
		{
			try{
				Persona nb=arrayList.get(j+3);	
				Image foto=null;
				 if(nb.getBytesFoto()==null){
					foto =Image.getInstance(getClass().getResource("/resource/default1.gif"));
				 }else
				     foto=Image.getInstance(nb.getBytesFoto());
					foto.scaleAbsoluteHeight(70);
					foto.scaleAbsoluteWidth(70);
				foto.scaleAbsolute(70, 70);
				foto.scaleToFit(70, 70);
	
				foto.setAbsolutePosition(16,200);
				float border=2.0f;
				foto.setBorderWidthBottom(border);
				foto.setBorderWidthLeft(border);
				foto.setBorderWidthLeft(border);
				foto.setBorderWidthRight(border);
				foto.setBorderWidthTop(border);
				foto.setBorderColor(BaseColor.BLACK);
				document.add(foto);	 
				agregados++;
			}catch(Exception e){				
			}
		}
		{
			try{
			Persona nb=arrayList.get(j+4);	
			Image foto=null;
			 if(nb.getBytesFoto()==null){
				foto =Image.getInstance(getClass().getResource("/resource/default1.gif"));
			 }else
			     foto=Image.getInstance(nb.getBytesFoto());
				foto.scaleAbsoluteHeight(70);
				foto.scaleAbsoluteWidth(70);
			foto.scaleAbsolute(70, 70);
			foto.scaleToFit(70, 70);

			foto.setAbsolutePosition(16,125);
			float border=2.0f;
			foto.setBorderWidthBottom(border);
			foto.setBorderWidthLeft(border);
			foto.setBorderWidthLeft(border);
			foto.setBorderWidthRight(border);
			foto.setBorderWidthTop(border);
			foto.setBorderColor(BaseColor.BLACK);
			document.add(foto);	 
			agregados++;
			}catch(Exception e){
				
			}
		}
		{
			try{
			Persona nb=arrayList.get(j+5);	
			Image foto=null;
			 if(nb.getBytesFoto()==null){
				foto =Image.getInstance(getClass().getResource("/resource/default1.gif"));
			 }else
			     foto=Image.getInstance(nb.getBytesFoto());
				foto.scaleAbsoluteHeight(70);
				foto.scaleAbsoluteWidth(70);
			foto.scaleAbsolute(70, 70);
			foto.scaleToFit(70, 70);

			foto.setAbsolutePosition(16,50);
			float border=2.0f;
			foto.setBorderWidthBottom(border);
			foto.setBorderWidthLeft(border);
			foto.setBorderWidthLeft(border);
			foto.setBorderWidthRight(border);
			foto.setBorderWidthTop(border);
			foto.setBorderColor(BaseColor.BLACK);
			document.add(foto);	 
			agregados++;
			}catch(Exception e){
				
			}
		}
		
		
		if(agregados>=index){
			// 1
			{
				try{
			Paragraph nombre= new Paragraph(arrayList.get(index).getNombreCompleto(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
			nombre.setIndentationLeft(68);
			nombre.setSpacingBefore(39f);
			document.add(nombre);
				}catch(Exception e){
					
				}
			}
			{
				try{
			Paragraph nombre= new Paragraph("Fecha Nac:"+arrayList.get(index).getFechaNacimiento()+"        NIA:"+arrayList.get(index).getNia(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
			nombre.setIndentationLeft(68);
			nombre.setSpacingBefore(1f);
			document.add(nombre);
				}catch(Exception e){
					
				}
			}
			{
				try{
			Paragraph nombre= new Paragraph("Telef:"+arrayList.get(index).getTelefono(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
			nombre.setIndentationLeft(68);
			nombre.setSpacingBefore(1f);
			document.add(nombre);
				}catch(Exception e){
					
				}
			}
			{
				try{
					arrayList.get(index);
			Paragraph nombre= new Paragraph("Padres:",FontFactory.getFont("ARIAL",6, Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
			nombre.setIndentationLeft(68);
			nombre.setSpacingBefore(1f);
			document.add(nombre);
				}catch(Exception e){
					
				}
			}
			{
				try{
			Paragraph nombre= new Paragraph(""+arrayList.get(index).getPadre(),FontFactory.getFont("ARIAL",6, Font.ITALIC, BaseColor.BLACK));
			nombre.setIndentationLeft(68);
			nombre.setSpacingBefore(1f);
			document.add(nombre);
				}catch(Exception e){
					
				}
			}
			{
				try{
			Paragraph nombre= new Paragraph(""+arrayList.get(index).getMadre(),FontFactory.getFont("ARIAL",6, Font.ITALIC, BaseColor.BLACK));
			nombre.setIndentationLeft(68);
			nombre.setSpacingBefore(1f);
			document.add(nombre);
				}catch(Exception e){
					
				}
			}
			
		}
		index++;
		if(agregados>=index){
		//2
		    {
		    	try{
		    
			Paragraph nombre= new Paragraph(arrayList.get(index).getNombreCompleto(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
			nombre.setIndentationLeft(68);
			nombre.setSpacingBefore(16f);
			document.add(nombre);
				}catch(Exception e){
					
				}
			}
			{
				try{
			Paragraph nombre= new Paragraph("Fecha Nac:"+arrayList.get(index).getFechaNacimiento()+"        NIA:"+arrayList.get(index).getNia(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
			nombre.setIndentationLeft(68);
			nombre.setSpacingBefore(1f);
			document.add(nombre);
				}catch(Exception e){
					
				}
			}
			{
				try{
			Paragraph nombre= new Paragraph("Telef:"+arrayList.get(index).getTelefono(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
			nombre.setIndentationLeft(68);
			nombre.setSpacingBefore(1f);
			document.add(nombre);
				}catch(Exception e){
					
				}
			}
			{
				try{
					arrayList.get(index);
			Paragraph nombre= new Paragraph("Padres:",FontFactory.getFont("ARIAL",6, Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
			nombre.setIndentationLeft(68);
			nombre.setSpacingBefore(1f);
			document.add(nombre);
				}catch(Exception e){
					
				}
			}
			{
				try{
			Paragraph nombre= new Paragraph(""+arrayList.get(index).getPadre(),FontFactory.getFont("ARIAL",6, Font.ITALIC, BaseColor.BLACK));
			nombre.setIndentationLeft(68);
			nombre.setSpacingBefore(1f);
			document.add(nombre);
				}catch(Exception e){
					
				}
			}
			{
				try{
			Paragraph nombre= new Paragraph(""+arrayList.get(index).getMadre(),FontFactory.getFont("ARIAL",6, Font.ITALIC, BaseColor.BLACK));
			nombre.setIndentationLeft(68);
			nombre.setSpacingBefore(1f);
			document.add(nombre);
				}catch(Exception e){
					
				}
			}
		}
		index++;
		if(agregados>=index){
			{
				try{
				Paragraph nombre= new Paragraph(arrayList.get(index).getNombreCompleto(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
				nombre.setIndentationLeft(68);
				nombre.setSpacingBefore(16f);
				document.add(nombre);
				}catch(Exception e){
					
				}
				}
				{
					try{
				Paragraph nombre= new Paragraph("Fecha Nac:"+arrayList.get(index).getFechaNacimiento()+"        NIA:"+arrayList.get(index).getNia(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
				nombre.setIndentationLeft(68);
				nombre.setSpacingBefore(1f);
				document.add(nombre);
					}catch(Exception e){
						
					}
				}
				{
					try{
				Paragraph nombre= new Paragraph("Telef:"+arrayList.get(index).getTelefono(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
				nombre.setIndentationLeft(68);
				nombre.setSpacingBefore(1f);
				document.add(nombre);
					}catch(Exception e){
						
					}					
				}
				{
					try{
						arrayList.get(index);
				Paragraph nombre= new Paragraph("Padres:",FontFactory.getFont("ARIAL",6, Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
				nombre.setIndentationLeft(68);
				nombre.setSpacingBefore(1f);
				document.add(nombre);
					}catch(Exception e){
						
					}
				}
				{
					try{
				Paragraph nombre= new Paragraph(""+arrayList.get(index).getPadre(),FontFactory.getFont("ARIAL",6, Font.ITALIC, BaseColor.BLACK));
				nombre.setIndentationLeft(68);
				nombre.setSpacingBefore(1f);
				document.add(nombre);
					}catch(Exception e){
						
					}
				}
				{
					try{
				Paragraph nombre= new Paragraph(""+arrayList.get(index).getMadre(),FontFactory.getFont("ARIAL",6, Font.ITALIC, BaseColor.BLACK));
				nombre.setIndentationLeft(68);
				nombre.setSpacingBefore(1f);
				document.add(nombre);
					}catch(Exception e){
						
					}
				}
		}
		index++;
		if(agregados>=index){		
			{
				try{
				Paragraph nombre= new Paragraph(arrayList.get(index).getNombreCompleto(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
				nombre.setIndentationLeft(68);
				nombre.setSpacingBefore(16f);
				document.add(nombre);
				}catch(Exception e){
					
				}
				}
				{
					try{
				Paragraph nombre= new Paragraph("Fecha Nac:"+arrayList.get(index).getFechaNacimiento()+"        NIA:"+arrayList.get(index).getNia(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
				nombre.setIndentationLeft(68);
				nombre.setSpacingBefore(1f);
				document.add(nombre);
					}catch(Exception e){
						
					}
				}
				{
					try{
				Paragraph nombre= new Paragraph("Telef:"+arrayList.get(index).getTelefono(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
				nombre.setIndentationLeft(68);
				nombre.setSpacingBefore(1f);
				document.add(nombre);
					}catch(Exception e){
						
					}
				}
				{
					try{
						arrayList.get(index);
				Paragraph nombre= new Paragraph("Padres:",FontFactory.getFont("ARIAL",6, Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
				nombre.setIndentationLeft(68);
				nombre.setSpacingBefore(1f);
				document.add(nombre);
					}catch(Exception e){
						
					}
				}
				{
					try{
				Paragraph nombre= new Paragraph(""+arrayList.get(index).getPadre(),FontFactory.getFont("ARIAL",6, Font.ITALIC, BaseColor.BLACK));
				nombre.setIndentationLeft(68);
				nombre.setSpacingBefore(1f);
				document.add(nombre);
					}catch(Exception e){
						
					}
				}
				{
					try{
				Paragraph nombre= new Paragraph(""+arrayList.get(index).getMadre(),FontFactory.getFont("ARIAL",6, Font.ITALIC, BaseColor.BLACK));
				nombre.setIndentationLeft(68);
				nombre.setSpacingBefore(1f);
				document.add(nombre);
									}catch(Exception e){
						
					}
				}
		}
		index++;
		if(agregados>=index){
			{
				try{
				Paragraph nombre= new Paragraph(arrayList.get(index).getNombreCompleto(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
				nombre.setIndentationLeft(68);
				nombre.setSpacingBefore(16f);
				document.add(nombre);
				}catch(Exception e){
					
				}
				}
				{
					try{
				Paragraph nombre= new Paragraph("Fecha Nac:"+arrayList.get(index).getFechaNacimiento()+"        NIA:"+arrayList.get(index).getNia(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
				nombre.setIndentationLeft(68);
				nombre.setSpacingBefore(1f);
				document.add(nombre);
					}catch(Exception e){
						
					}
				}
				{
					try{
				Paragraph nombre= new Paragraph("Telef:"+arrayList.get(index).getTelefono(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
				nombre.setIndentationLeft(68);
				nombre.setSpacingBefore(1f);
				document.add(nombre);
					}catch(Exception e){
						
					}
				}
				{
					try{
						arrayList.get(index);
				Paragraph nombre= new Paragraph("Padres:",FontFactory.getFont("ARIAL",6, Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
				nombre.setIndentationLeft(68);
				nombre.setSpacingBefore(1f);
				document.add(nombre);
					}catch(Exception e){
						
					}
				}
				{
					try{
				Paragraph nombre= new Paragraph(""+arrayList.get(index).getPadre(),FontFactory.getFont("ARIAL",6, Font.ITALIC, BaseColor.BLACK));
				nombre.setIndentationLeft(68);
				nombre.setSpacingBefore(1f);
				document.add(nombre);
					}catch(Exception e){
						
					}
				}
				{
					try{
				Paragraph nombre= new Paragraph(""+arrayList.get(index).getMadre(),FontFactory.getFont("ARIAL",6, Font.ITALIC, BaseColor.BLACK));
				nombre.setIndentationLeft(68);
				nombre.setSpacingBefore(1f);
				document.add(nombre);
					}catch(Exception e){
						
					}
				}
		}
		index++;
		if(agregados>=index){
			{
				try{
				Paragraph nombre= new Paragraph(arrayList.get(index).getNombreCompleto(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
				nombre.setIndentationLeft(68);
				nombre.setSpacingBefore(16f);
				document.add(nombre);
				}catch(Exception e){
					
				}
				}
				{
					try{
				Paragraph nombre= new Paragraph("Fecha Nac:"+arrayList.get(index).getFechaNacimiento()+"        NIA:"+arrayList.get(index).getNia(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
				nombre.setIndentationLeft(68);
				nombre.setSpacingBefore(1f);
				document.add(nombre);
					}catch(Exception e){
						
					}
				}
				{
					try{
				Paragraph nombre= new Paragraph("Telef:"+arrayList.get(index).getTelefono(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
				nombre.setIndentationLeft(68);
				nombre.setSpacingBefore(1f);
				document.add(nombre);
					}catch(Exception e){
						
					}
				}
				{
					try{
						arrayList.get(index);
				Paragraph nombre= new Paragraph("Padres:",FontFactory.getFont("ARIAL",6, Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
				nombre.setIndentationLeft(68);
				nombre.setSpacingBefore(1f);
				document.add(nombre);
					}catch(Exception e){
						
					}
				}
				{
					try{
				Paragraph nombre= new Paragraph(""+arrayList.get(index).getPadre(),FontFactory.getFont("ARIAL",6, Font.ITALIC, BaseColor.BLACK));
				nombre.setIndentationLeft(68);
				nombre.setSpacingBefore(1f);
				document.add(nombre);
					}catch(Exception e){
						
					}
				}
				{
					try{
				Paragraph nombre= new Paragraph(""+arrayList.get(index).getMadre(),FontFactory.getFont("ARIAL",6, Font.ITALIC, BaseColor.BLACK));
				nombre.setIndentationLeft(68);
				nombre.setSpacingBefore(1f);
				document.add(nombre);
					}catch(Exception e){
						
					}
				}
		}
		return index;
	}
	public class FooterPiePaginaiText extends  PdfPageEventHelper {
		 @Override
		 public void onEndPage(PdfWriter writer, Document document) {
		  ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Pagina:"+nPage), 42,20,0);
		  nPage++;
		 }
	}
	private int nPage=1;
	private ArrayList<Persona> arrayList;
	public PDF_ProfesorHorizontal(String nombreCurso,String tutor,ArrayList<Persona> arrayList,ComedorGUI principal) throws DocumentException, MalformedURLException, IOException{
	   document = new Document();
	   this.arrayList=arrayList;
	   PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("System-Comedor"+File.separator+"listadoHorizontal.pdf"));
	   FooterPiePaginaiText footer = new FooterPiePaginaiText();
	   writer.setPageEvent(footer);
	   
		document.setPageSize(PageSize.A4.rotate());
		document.setMargins(4, 10, 10, 20);
		document.open();

		{
		Paragraph nombre= new Paragraph("CURS: "+nombreCurso,FontFactory.getFont("arial",10, Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
		nombre.setIndentationLeft(10);
		nombre.setSpacingBefore(-8f);
		document.add(nombre);
		}
		{
		Paragraph nombre= new Paragraph("Tutor: "+tutor,FontFactory.getFont("arial",10, Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
		nombre.setIndentationLeft(10);
		nombre.setSpacingBefore(20f);
		document.add(nombre);
		}
		

		Image fondo = Image.getInstance(getClass().getResource("/resource/fondo.png"));
			
		fondo.scaleToFit(810, 500);
		fondo.setAbsolutePosition(8, 50);
		document.add(fondo);
		int l=6;
		addAlumnos(agregados,0);
		boolean n=true;
		while(n){
		if(agregados==l){
		{
			try{
			arrayList.get(agregados);
			}catch(Exception e){
				break;
			}
			{
					document.newPage();
					Paragraph nombre= new Paragraph("CURS: "+nombreCurso,FontFactory.getFont("arial",10, Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
					nombre.setIndentationLeft(10);
					nombre.setSpacingBefore(-8f);
					document.add(nombre);
					   }
					{
					Paragraph nombre= new Paragraph("Tutor: "+tutor,FontFactory.getFont("arial",10, Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
					nombre.setIndentationLeft(10);
					nombre.setSpacingBefore(20f);
					document.add(nombre);
					}
	  			    fondo = Image.getInstance(getClass().getResource("/resource/fondo.png"));	
					fondo.scaleToFit(810, 500);
					fondo.setAbsolutePosition(8, 50);
					document.add(fondo);
					addAlumnos(agregados,agregados);
					l+=6;
					
		   		}
			}else break;
		}

		
		
		
		document.close();
		if(principal.getBaseDeDatos().getImpresionDirecta()){
			  new Impresora(principal).print(new File("System-Comedor"+File.separator+"listadoHorizontal.pdf").toString());
		}else{
			
		
		try {
			
			Desktop.getDesktop().open(
					new File("System-Comedor"+File.separator+"listadoHorizontal.pdf"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		}
		
		
	}
}
