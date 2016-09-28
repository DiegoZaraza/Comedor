package comedor.pdfs;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import comedor.ComedorGUI;
import comedor.Impresora;

public class PDF_ProfesorVertical {

	private Document document;
	/**
	 * @param args
	 * @throws DocumentException 
	 * @throws FileNotFoundException 
	 */
	public class FooterPiePaginaiText extends  PdfPageEventHelper
	{
		@Override
		public void onEndPage(PdfWriter writer, Document document) 
		{
			ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Pagina:"+nPage), 42,20,0);
			nPage++;
		}
	}

	private int nPage = 1;
	private int agregados=0;
	private int yu=0;

	public void addAlumnos(int index)
	{
		int pos=800;
		int m=100;
		int y=300;
		{
			{
				for(int index1=0; index1<13;index1++){
					try{
						{
							Persona nb=alumnos.get(yu);
							Image foto=null;
							if(nb.getBytesFoto()==null){
								foto =Image.getInstance(getClass().getResource("/resource/default1.gif"));
							}else
								foto=Image.getInstance(nb.getBytesFoto());
							foto.scaleAbsolute(70, 70);
							foto.scaleToFit(70, 70);
							foto.setAbsolutePosition(46,pos-m);
							float border=2.0f;
							foto.setBorderWidthBottom(border);
							foto.setBorderWidthLeft(border);
							foto.setBorderWidthLeft(border);
							foto.setBorderWidthRight(border);
							foto.setBorderWidthTop(border);
							foto.setBorderColor(BaseColor.BLACK);
							document.add(foto);	 
							agregados++;
							yu++;
						}		
						index1++;
						{
							Persona nb=alumnos.get(yu);
							Image foto=null;
							if(nb.getBytesFoto()==null){
								foto =Image.getInstance(getClass().getResource("/resource/default1.gif"));
							}else
								foto=Image.getInstance(nb.getBytesFoto());
							foto.scaleAbsolute(70, 70);
							foto.scaleToFit(70, 70);
							foto.setAbsolutePosition(46+y,pos-m);
							float border=2.0f;
							foto.setBorderWidthBottom(border);
							foto.setBorderWidthLeft(border);
							foto.setBorderWidthLeft(border);
							foto.setBorderWidthRight(border);
							foto.setBorderWidthTop(border);
							foto.setBorderColor(BaseColor.BLACK);
							document.add(foto);	 
							m+=100;
							yu++;
							agregados++;
						}		
					}catch(Exception e){
						break;
					}
				}
			}


		}

		// 1
		if(agregados>index){
			{
				try{
					Paragraph nombre= new Paragraph(alumnos.get(index).getNombreCompleto(),FontFactory.getFont("ARIAL",6, Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
					nombre.setIndentationLeft(68);
					nombre.setSpacingBefore(22f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph("Fecha Nac:"+alumnos.get(index).getFechaNacimiento()+"        NIA:"+alumnos.get(index).getNia(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
					nombre.setIndentationLeft(68);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph("Telef:"+alumnos.get(index).getTelefono(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
					nombre.setIndentationLeft(68);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					//							arrayList.get(index);
					alumnos.get(index);
					Paragraph nombre= new Paragraph("Padres:",FontFactory.getFont("ARIAL",6, Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
					nombre.setIndentationLeft(68);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph(""+alumnos.get(index).getPadre(),FontFactory.getFont("ARIAL",6, Font.ITALIC, BaseColor.BLACK));
					nombre.setIndentationLeft(68);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph(""+alumnos.get(index).getMadre(),FontFactory.getFont("ARIAL",6, Font.ITALIC, BaseColor.BLACK));
					nombre.setIndentationLeft(68);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}

			}
		}
		if(agregados>index){
			index++;
			// 2
			{
				try{
					Paragraph nombre= new Paragraph(alumnos.get(index).getNombreCompleto(),FontFactory.getFont("ARIAL",6, Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
					nombre.setIndentationLeft(368);
					nombre.setSpacingBefore(-59f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph("Fecha Nac:"+alumnos.get(index).getFechaNacimiento()+"        NIA:"+alumnos.get(index).getNia(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
					nombre.setIndentationLeft(368);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph("Telef:"+alumnos.get(index).getTelefono(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
					nombre.setIndentationLeft(368);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					alumnos.get(index);
					Paragraph nombre= new Paragraph("Padres:",FontFactory.getFont("ARIAL",6, Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
					nombre.setIndentationLeft(368);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph(""+alumnos.get(index).getPadre(),FontFactory.getFont("ARIAL",6, Font.ITALIC, BaseColor.BLACK));
					nombre.setIndentationLeft(368);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph(""+alumnos.get(index).getMadre(),FontFactory.getFont("ARIAL",6, Font.ITALIC, BaseColor.BLACK));
					nombre.setIndentationLeft(368);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}

			}

		}
		if(agregados>index){
			index++;
			// 2
			{
				try{
					Paragraph nombre= new Paragraph(alumnos.get(index).getNombreCompleto(),FontFactory.getFont("ARIAL",6, Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
					nombre.setIndentationLeft(68);
					nombre.setSpacingBefore(42f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph("Fecha Nac:"+alumnos.get(index).getFechaNacimiento()+"        NIA:"+alumnos.get(index).getNia(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
					nombre.setIndentationLeft(68);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph("Telef:"+alumnos.get(index).getTelefono(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
					nombre.setIndentationLeft(68);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					alumnos.get(index);
					Paragraph nombre= new Paragraph("Padres:",FontFactory.getFont("ARIAL",6, Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
					nombre.setIndentationLeft(68);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph(""+alumnos.get(index).getPadre(),FontFactory.getFont("ARIAL",6, Font.ITALIC, BaseColor.BLACK));
					nombre.setIndentationLeft(68);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph(""+alumnos.get(index).getMadre(),FontFactory.getFont("ARIAL",6, Font.ITALIC, BaseColor.BLACK));
					nombre.setIndentationLeft(68);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}

			}
		}
		if(agregados>index){
			index++;

			// 4
			{
				try{
					Paragraph nombre= new Paragraph(alumnos.get(index).getNombreCompleto(),FontFactory.getFont("ARIAL",6, Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
					nombre.setIndentationLeft(368);
					nombre.setSpacingBefore(-59f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph("Fecha Nac:"+alumnos.get(index).getFechaNacimiento()+"        NIA:"+alumnos.get(index).getNia(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
					nombre.setIndentationLeft(368);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph("Telef:"+alumnos.get(index).getTelefono(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
					nombre.setIndentationLeft(368);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					alumnos.get(index);
					Paragraph nombre= new Paragraph("Padres:",FontFactory.getFont("ARIAL",6, Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
					nombre.setIndentationLeft(368);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph(""+alumnos.get(index).getPadre(),FontFactory.getFont("ARIAL",6, Font.ITALIC, BaseColor.BLACK));
					nombre.setIndentationLeft(368);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph(""+alumnos.get(index).getMadre(),FontFactory.getFont("ARIAL",6, Font.ITALIC, BaseColor.BLACK));
					nombre.setIndentationLeft(368);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}

			}

		}
		if(agregados>index){
			index++;
			// 2
			{
				try{
					Paragraph nombre= new Paragraph(alumnos.get(index).getNombreCompleto(),FontFactory.getFont("ARIAL",6, Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
					nombre.setIndentationLeft(68);
					nombre.setSpacingBefore(42f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph("Fecha Nac:"+alumnos.get(index).getFechaNacimiento()+"        NIA:"+alumnos.get(index).getNia(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
					nombre.setIndentationLeft(68);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph("Telef:"+alumnos.get(index).getTelefono(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
					nombre.setIndentationLeft(68);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					alumnos.get(index);
					Paragraph nombre= new Paragraph("Padres:",FontFactory.getFont("ARIAL",6, Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
					nombre.setIndentationLeft(68);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph(""+alumnos.get(index).getPadre(),FontFactory.getFont("ARIAL",6, Font.ITALIC, BaseColor.BLACK));
					nombre.setIndentationLeft(68);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph(""+alumnos.get(index).getMadre(),FontFactory.getFont("ARIAL",6, Font.ITALIC, BaseColor.BLACK));
					nombre.setIndentationLeft(68);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}

			}
		}
		if(agregados>index){
			index++;

			// 4
			{
				try{
					Paragraph nombre= new Paragraph(alumnos.get(index).getNombreCompleto(),FontFactory.getFont("ARIAL",6, Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
					nombre.setIndentationLeft(368);
					nombre.setSpacingBefore(-59f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph("Fecha Nac:"+alumnos.get(index).getFechaNacimiento()+"        NIA:"+alumnos.get(index).getNia(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
					nombre.setIndentationLeft(368);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph("Telef:"+alumnos.get(index).getTelefono(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
					nombre.setIndentationLeft(368);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					alumnos.get(index);
					Paragraph nombre= new Paragraph("Padres:",FontFactory.getFont("ARIAL",6, Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
					nombre.setIndentationLeft(368);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph(""+alumnos.get(index).getPadre(),FontFactory.getFont("ARIAL",6, Font.ITALIC, BaseColor.BLACK));
					nombre.setIndentationLeft(368);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph(""+alumnos.get(index).getMadre(),FontFactory.getFont("ARIAL",6, Font.ITALIC, BaseColor.BLACK));
					nombre.setIndentationLeft(368);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}

			}

		}
		if(agregados>index){
			index++;
			// 2
			{
				try{
					Paragraph nombre= new Paragraph(alumnos.get(index).getNombreCompleto(),FontFactory.getFont("ARIAL",6, Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
					nombre.setIndentationLeft(68);
					nombre.setSpacingBefore(42f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph("Fecha Nac:"+alumnos.get(index).getFechaNacimiento()+"        NIA:"+alumnos.get(index).getNia(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
					nombre.setIndentationLeft(68);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph("Telef:"+alumnos.get(index).getTelefono(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
					nombre.setIndentationLeft(68);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					alumnos.get(index);
					Paragraph nombre= new Paragraph("Padres:",FontFactory.getFont("ARIAL",6, Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
					nombre.setIndentationLeft(68);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph(""+alumnos.get(index).getPadre(),FontFactory.getFont("ARIAL",6, Font.ITALIC, BaseColor.BLACK));
					nombre.setIndentationLeft(68);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph(""+alumnos.get(index).getMadre(),FontFactory.getFont("ARIAL",6, Font.ITALIC, BaseColor.BLACK));
					nombre.setIndentationLeft(68);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}

			}
		}
		if(agregados>index){
			index++;

			// 4
			{
				try{
					Paragraph nombre= new Paragraph(alumnos.get(index).getNombreCompleto(),FontFactory.getFont("ARIAL",6, Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
					nombre.setIndentationLeft(368);
					nombre.setSpacingBefore(-59f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph("Fecha Nac:"+alumnos.get(index).getFechaNacimiento()+"        NIA:"+alumnos.get(index).getNia(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
					nombre.setIndentationLeft(368);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph("Telef:"+alumnos.get(index).getTelefono(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
					nombre.setIndentationLeft(368);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					alumnos.get(index);
					Paragraph nombre= new Paragraph("Padres:",FontFactory.getFont("ARIAL",6, Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
					nombre.setIndentationLeft(368);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph(""+alumnos.get(index).getPadre(),FontFactory.getFont("ARIAL",6, Font.ITALIC, BaseColor.BLACK));
					nombre.setIndentationLeft(368);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph(""+alumnos.get(index).getMadre(),FontFactory.getFont("ARIAL",6, Font.ITALIC, BaseColor.BLACK));
					nombre.setIndentationLeft(368);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}

			}

		}

		if(agregados>index){
			index++;
			// 2
			{
				try{
					Paragraph nombre= new Paragraph(alumnos.get(index).getNombreCompleto(),FontFactory.getFont("ARIAL",6, Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
					nombre.setIndentationLeft(68);
					nombre.setSpacingBefore(42f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph("Fecha Nac:"+alumnos.get(index).getFechaNacimiento()+"        NIA:"+alumnos.get(index).getNia(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
					nombre.setIndentationLeft(68);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph("Telef:"+alumnos.get(index).getTelefono(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
					nombre.setIndentationLeft(68);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					alumnos.get(index);
					Paragraph nombre= new Paragraph("Padres:",FontFactory.getFont("ARIAL",6, Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
					nombre.setIndentationLeft(68);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph(""+alumnos.get(index).getPadre(),FontFactory.getFont("ARIAL",6, Font.ITALIC, BaseColor.BLACK));
					nombre.setIndentationLeft(68);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph(""+alumnos.get(index).getMadre(),FontFactory.getFont("ARIAL",6, Font.ITALIC, BaseColor.BLACK));
					nombre.setIndentationLeft(68);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}

			}
		}
		if(agregados>index){
			index++;

			// 4
			{
				try{
					Paragraph nombre= new Paragraph(alumnos.get(index).getNombreCompleto(),FontFactory.getFont("ARIAL",6, Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
					nombre.setIndentationLeft(368);
					nombre.setSpacingBefore(-59f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph("Fecha Nac:"+alumnos.get(index).getFechaNacimiento()+"        NIA:"+alumnos.get(index).getNia(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
					nombre.setIndentationLeft(368);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph("Telef:"+alumnos.get(index).getTelefono(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
					nombre.setIndentationLeft(368);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					alumnos.get(index);
					Paragraph nombre= new Paragraph("Padres:",FontFactory.getFont("ARIAL",6, Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
					nombre.setIndentationLeft(368);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph(""+alumnos.get(index).getPadre(),FontFactory.getFont("ARIAL",6, Font.ITALIC, BaseColor.BLACK));
					nombre.setIndentationLeft(368);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph(""+alumnos.get(index).getMadre(),FontFactory.getFont("ARIAL",6, Font.ITALIC, BaseColor.BLACK));
					nombre.setIndentationLeft(368);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}

			}

		}
		if(agregados>index){
			index++;
			// 2
			{
				try{
					Paragraph nombre= new Paragraph(alumnos.get(index).getNombreCompleto(),FontFactory.getFont("ARIAL",6, Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
					nombre.setIndentationLeft(68);
					nombre.setSpacingBefore(42f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph("Fecha Nac:"+alumnos.get(index).getFechaNacimiento()+"        NIA:"+alumnos.get(index).getNia(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
					nombre.setIndentationLeft(68);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph("Telef:"+alumnos.get(index).getTelefono(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
					nombre.setIndentationLeft(68);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					alumnos.get(index);
					Paragraph nombre= new Paragraph("Padres:",FontFactory.getFont("ARIAL",6, Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
					nombre.setIndentationLeft(68);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph(""+alumnos.get(index).getPadre(),FontFactory.getFont("ARIAL",6, Font.ITALIC, BaseColor.BLACK));
					nombre.setIndentationLeft(68);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph(""+alumnos.get(index).getMadre(),FontFactory.getFont("ARIAL",6, Font.ITALIC, BaseColor.BLACK));
					nombre.setIndentationLeft(68);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}

			}
		}
		if(agregados>index){
			index++;

			// 4
			{
				try{
					Paragraph nombre= new Paragraph(alumnos.get(index).getNombreCompleto(),FontFactory.getFont("ARIAL",6, Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
					nombre.setIndentationLeft(368);
					nombre.setSpacingBefore(-59f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph("Fecha Nac:"+alumnos.get(index).getFechaNacimiento()+"        NIA:"+alumnos.get(index).getNia(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
					nombre.setIndentationLeft(368);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph("Telef:"+alumnos.get(index).getTelefono(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
					nombre.setIndentationLeft(368);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					alumnos.get(index);
					Paragraph nombre= new Paragraph("Padres:",FontFactory.getFont("ARIAL",6, Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
					nombre.setIndentationLeft(368);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph(""+alumnos.get(index).getPadre(),FontFactory.getFont("ARIAL",6, Font.ITALIC, BaseColor.BLACK));
					nombre.setIndentationLeft(368);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph(""+alumnos.get(index).getMadre(),FontFactory.getFont("ARIAL",6, Font.ITALIC, BaseColor.BLACK));
					nombre.setIndentationLeft(368);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}

			}

		}
		if(agregados>index){
			index++;
			// 2
			{
				try{
					Paragraph nombre= new Paragraph(alumnos.get(index).getNombreCompleto(),FontFactory.getFont("ARIAL",6, Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
					nombre.setIndentationLeft(68);
					nombre.setSpacingBefore(42f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph("Fecha Nac:"+alumnos.get(index).getFechaNacimiento()+"        NIA:"+alumnos.get(index).getNia(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
					nombre.setIndentationLeft(68);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph("Telef:"+alumnos.get(index).getTelefono(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
					nombre.setIndentationLeft(68);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					alumnos.get(index);
					Paragraph nombre= new Paragraph("Padres:",FontFactory.getFont("ARIAL",6, Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
					nombre.setIndentationLeft(68);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph(""+alumnos.get(index).getPadre(),FontFactory.getFont("ARIAL",6, Font.ITALIC, BaseColor.BLACK));
					nombre.setIndentationLeft(68);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph(""+alumnos.get(index).getMadre(),FontFactory.getFont("ARIAL",6, Font.ITALIC, BaseColor.BLACK));
					nombre.setIndentationLeft(68);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}

			}
		}
		if(agregados>=index){
			index++;

			// 4
			{
				try{
					Paragraph nombre= new Paragraph(alumnos.get(index).getNombreCompleto(),FontFactory.getFont("ARIAL",6, Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
					nombre.setIndentationLeft(368);
					nombre.setSpacingBefore(-59f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph("Fecha Nac:"+alumnos.get(index).getFechaNacimiento()+"        NIA:"+alumnos.get(index).getNia(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
					nombre.setIndentationLeft(368);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph("Telef:"+alumnos.get(index).getTelefono(),FontFactory.getFont("ARIAL",6, Font.NORMAL, BaseColor.BLACK));
					nombre.setIndentationLeft(368);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					alumnos.get(index);
					Paragraph nombre= new Paragraph("Padres:",FontFactory.getFont("ARIAL",6, Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
					nombre.setIndentationLeft(368);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph(""+alumnos.get(index).getPadre(),FontFactory.getFont("ARIAL",6, Font.ITALIC, BaseColor.BLACK));
					nombre.setIndentationLeft(368);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
			{
				try{
					Paragraph nombre= new Paragraph(""+alumnos.get(index).getMadre(),FontFactory.getFont("ARIAL",6, Font.ITALIC, BaseColor.BLACK));
					nombre.setIndentationLeft(368);
					nombre.setSpacingBefore(1f);
					document.add(nombre);
				}catch(Exception e){

				}

			}

		}
	}
	private ArrayList<Persona> alumnos;
	public PDF_ProfesorVertical(String codigoCurso,String tutor,ArrayList<Persona> alumnos,ComedorGUI principal) throws FileNotFoundException, DocumentException{
		document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("System-Comedor"+File.separator+"listadoVertical.pdf"));
		FooterPiePaginaiText footer = new FooterPiePaginaiText();
		writer.setPageEvent(footer);
		this.alumnos = alumnos;

		document.open();

		{
			Paragraph nombre= new Paragraph("CURS: ",FontFactory.getFont("arial",10, Font.NORMAL, BaseColor.BLACK));
			Paragraph paragraph = new Paragraph(codigoCurso,FontFactory.getFont("arial",10, Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
			nombre.add(paragraph);
			nombre.setIndentationLeft(10);
			document.add(nombre);
		}
		{
			Paragraph nombre= new Paragraph("Tutor: ",FontFactory.getFont("arial",10, Font.NORMAL, BaseColor.BLACK));
			Paragraph paragraph = new Paragraph(""+tutor,FontFactory.getFont("arial",10, Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
			nombre.add(paragraph);
			nombre.setIndentationLeft(100);
			nombre.setSpacingBefore(-15f);
			document.add(nombre);
		}
		addAlumnos(agregados);
		boolean n=true;
		while(n){
			if(agregados==14){
				document.newPage();
				{
					Paragraph nombre= new Paragraph("CURS: ",FontFactory.getFont("arial",10, Font.NORMAL, BaseColor.BLACK));
					Paragraph paragraph = new Paragraph(codigoCurso,FontFactory.getFont("arial",10, Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
					nombre.add(paragraph);
					nombre.setIndentationLeft(10);
					document.add(nombre);
				}
				{
					Paragraph nombre= new Paragraph("Tutor: ",FontFactory.getFont("arial",10, Font.NORMAL, BaseColor.BLACK));
					Paragraph paragraph = new Paragraph(""+tutor,FontFactory.getFont("arial",10, Font.NORMAL|Font.UNDERLINE, BaseColor.BLACK));
					nombre.add(paragraph);
					nombre.setIndentationLeft(100);
					nombre.setSpacingBefore(-15f);
					document.add(nombre);
				}
				addAlumnos(agregados);
				agregados+=14;
			}else break;
		}

		document.close();
		if(principal.getBaseDeDatos().getImpresionDirecta()){
			new Impresora(principal).print(new File("System-Comedor"+File.separator+"listadoVertical.pdf").toString());
		}else{
			try {
				Desktop.getDesktop().open(
						new File("System-Comedor"+File.separator+"listadoVertical.pdf"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	}


}
