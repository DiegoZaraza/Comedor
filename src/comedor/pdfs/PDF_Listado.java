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
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.collection.PdfTargetDictionary;

import comedor.ComedorGUI;
import comedor.Impresora;

public class PDF_Listado 
{
	private ComedorGUI principal;
	private Document document;

	public boolean crearPDF51(ComedorGUI gui, String curso, String codigoGrupo, String nombreGrupo, String tutor, ArrayList<Persona> alumnos, boolean f) throws Exception 
	{
		this.principal=gui;
		this.alumnos = alumnos;
		document = new Document();

		this.curso = curso;
		this.codigoGrupo = codigoGrupo;
		this.tutor = tutor;
		this.nombreGrupo = nombreGrupo;
		this.f = f;

		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("System-Comedor" + File.separator + tutor + ".pdf"));
		FooterPiePaginaiText footer = new FooterPiePaginaiText();
		writer.setPageEvent(footer);

		document.open();

		Paragraph texto1 = new Paragraph("" + principal.getBaseDeDatos().getCentro(), FontFactory.getFont("arial", 14, Font.BOLD, BaseColor.BLACK));
		Paragraph texto21 = new Paragraph("" + curso, FontFactory.getFont("arial", 9, Font.NORMAL, BaseColor.BLACK));
		Paragraph texto22 = new Paragraph("\na" + principal.getBaseDeDatos().getCodigoCentro(), FontFactory.getFont("arial", 9, Font.NORMAL, BaseColor.BLACK));

		texto21.setAlignment(Chunk.ALIGN_RIGHT);
		texto1.setAlignment(Chunk.ALIGN_CENTER);

		texto21.add(texto22);

		document.add(texto1);
		texto21.setSpacingBefore(-17);
		document.add(texto21);	

		Paragraph a11 = null;

		if(!f)
			a11 = new Paragraph("TUTOR/A: " + tutor, FontFactory.getFont("tahoma", 10, Font.NORMAL, BaseColor.BLACK));
		else
			a11 = new Paragraph(tutor, FontFactory.getFont("tahoma", 10, Font.NORMAL, BaseColor.BLACK));

		a11.setAlignment(Element.ALIGN_CENTER);

		PdfPTable tabla = new PdfPTable(1);
		tabla.setWidthPercentage(50);
		float[] medidaCeldas = {1.6f};
		tabla.setWidths(medidaCeldas);
		tabla.getDefaultCell().setPadding(1);
		tabla.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
		tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		tabla.addCell(a11);

		document.add(tabla);


		addAlumnos(alumnos.size());

		document.close();
		if(principal.getBaseDeDatos().getImpresionDirecta()){
			new Impresora(principal).print(new File("System-Comedor"+File.separator+tutor+".pdf").toString());
		}else{
			Desktop.getDesktop().open(
					new File("System-Comedor"+File.separator+tutor+".pdf"));
		}
		return true;
	}

	public void addNIAS(int agregados,int index,float off)
	{
		{
			if(agregados >= index)
			{
				try
				{
					Paragraph nia = new Paragraph(alumnos.get(index).getNia(), FontFactory.getFont("arial",8, Font.BOLD, BaseColor.BLACK));
					nia.setIndentationLeft(10);
					nia.setSpacingBefore(off);
					document.add(nia);			
				}catch(Exception e){

				}
			}
		}

		index++;

		{	 
			if(agregados>=index)
			{
				try{
					Paragraph nia2= new Paragraph(alumnos.get(index).getNia(), FontFactory.getFont("arial",8, Font.BOLD, BaseColor.BLACK));
					nia2.setIndentationLeft(89	);
					nia2.setSpacingBefore(-12f);
					document.add(nia2);
				}catch(Exception e){

				}
			}
		}

		index++;

		{	 
			if(agregados>=index){
				try{
					Paragraph nia2= new Paragraph(alumnos.get(index).getNia(), FontFactory.getFont("arial",8, Font.BOLD, BaseColor.BLACK));
					nia2.setIndentationLeft(89*1.9f);
					nia2.setSpacingBefore(-12f);
					document.add(nia2);
				}catch(Exception e){

				}
			}
		}
		index++;
		{	 
			if(agregados>=index){
				try{
					Paragraph nia2= new Paragraph(alumnos.get(index).getNia(), FontFactory.getFont("arial",8, Font.BOLD, BaseColor.BLACK));
					nia2.setIndentationLeft(89*2.75f);
					nia2.setSpacingBefore(-12f);
					document.add(nia2);
				}catch(Exception e){

				}
			}
		}
		index++;
		{	 
			if(agregados>=index){
				try{
					Paragraph nia2= new Paragraph(alumnos.get(index).getNia(), FontFactory.getFont("arial",8, Font.BOLD, BaseColor.BLACK));
					nia2.setIndentationLeft(89*3.65f);
					nia2.setSpacingBefore(-12f);
					document.add(nia2);
				}catch(Exception e){

				}
			}
		}
		index++;
		{
			if(agregados>=index){
				try{
					Paragraph nia2= new Paragraph(alumnos.get(index).getNia(), FontFactory.getFont("arial",8, Font.BOLD, BaseColor.BLACK));
					nia2.setIndentationLeft(89*4.5f);
					nia2.setSpacingBefore(-12f);
					document.add(nia2);
				}catch(Exception e){

				}
			}
		}
		index++;
		{	 
			if(agregados>=index){
				try{
					Paragraph nia2= new Paragraph(alumnos.get(index).getNia(), FontFactory.getFont("arial",8, Font.BOLD, BaseColor.BLACK));
					nia2.setIndentationLeft(89*5.3f);
					nia2.setSpacingBefore(-12f);
					document.add(nia2);
				}catch(Exception e){

				}
			}
		}
	}
	private float r1=3,r2=80,r3=80*2,r4=237,r5=314,r6=392,r7=471;
	public void addApellidos(int agregados,int index){

		//----------apellidoos 1
		{
			if(agregados>=index){
				try{
					Paragraph apellido1= new Paragraph(alumnos.get(index).getApellidos(),FontFactory.getFont("arial",5, Font.NORMAL, BaseColor.BLACK));
					apellido1.setIndentationLeft(r1);
					apellido1.setSpacingBefore(1f);
					document.add(apellido1);
				}catch(Exception e){

				}
			}
		}
		index++;
		{
			if(agregados>=index){
				try{
					Paragraph apellido1= new Paragraph(alumnos.get(index).getApellidos(),FontFactory.getFont("arial",5, Font.NORMAL, BaseColor.BLACK));
					apellido1.setIndentationLeft(r2);
					apellido1.setSpacingBefore(-7f);
					document.add(apellido1);
				}catch(Exception e){

				}
			}
		}
		index++;
		{
			if(agregados>=index){
				try{
					Paragraph apellido1= new Paragraph(alumnos.get(index).getApellidos(),FontFactory.getFont("arial",5, Font.NORMAL, BaseColor.BLACK));
					apellido1.setIndentationLeft(r3);
					apellido1.setSpacingBefore(-7f);
					document.add(apellido1);
				}catch(Exception e){

				}
			}
		}
		index++;
		{
			if(agregados>=index){
				try{
					Paragraph apellido1= new Paragraph(alumnos.get(index).getApellidos(),FontFactory.getFont("arial",5, Font.NORMAL, BaseColor.BLACK));
					apellido1.setIndentationLeft(r4);
					apellido1.setSpacingBefore(-7f);
					document.add(apellido1);
				}catch(Exception e){

				}
			}
		}
		index++;
		{
			if(agregados>=index){
				try{
					Paragraph apellido1= new Paragraph(alumnos.get(index).getApellidos(),FontFactory.getFont("arial",5, Font.NORMAL, BaseColor.BLACK));
					apellido1.setIndentationLeft(r5);
					apellido1.setSpacingBefore(-8f);
					document.add(apellido1);
				}catch(Exception e){

				}
			}
		}
		index++;
		{
			if(agregados>=index){
				try{
					Paragraph apellido1= new Paragraph(alumnos.get(index).getApellidos(),FontFactory.getFont("arial",5, Font.NORMAL, BaseColor.BLACK));
					apellido1.setIndentationLeft(r6);
					apellido1.setSpacingBefore(-8f);
					document.add(apellido1);
				}catch(Exception e){

				}
			}
		}
		index++;
		{
			if(agregados>=index){
				try{
					Paragraph apellido1= new Paragraph(alumnos.get(index).getApellidos(),FontFactory.getFont("arial",5, Font.NORMAL, BaseColor.BLACK));
					apellido1.setIndentationLeft(r7);
					apellido1.setSpacingBefore(-8f);
					document.add(apellido1);
				}catch(Exception e){

				}
			}
		}

	}
	public void addNombres(int agregados, int index){
		//		int index=0;
		//		NOMBRES

		{
			if(agregados>=index){
				try{
					Paragraph nombre= new Paragraph(alumnos.get(index).getNombres(),FontFactory.getFont("arial",5, Font.NORMAL, BaseColor.BLACK));
					nombre.setIndentationLeft(r1);
					nombre.setSpacingBefore(-2f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
		}
		index++;
		{
			if(agregados>=index){
				try{
					Paragraph nombre= new Paragraph(alumnos.get(index).getNombres(),FontFactory.getFont("arial",5, Font.NORMAL, BaseColor.BLACK));
					nombre.setIndentationLeft(r2);
					nombre.setSpacingBefore(-7f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
		}
		index++;

		{
			if(agregados>=index){
				try{
					Paragraph nombre= new Paragraph(alumnos.get(index).getNombres(),FontFactory.getFont("arial",5, Font.NORMAL, BaseColor.BLACK));
					nombre.setIndentationLeft(r3);
					nombre.setSpacingBefore(-7f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
		}
		index++;

		{
			if(agregados>=index){
				try{
					Paragraph nombre= new Paragraph(alumnos.get(index).getNombres(),FontFactory.getFont("arial",5, Font.NORMAL, BaseColor.BLACK));
					nombre.setIndentationLeft(r4);
					nombre.setSpacingBefore(-7f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
		}
		index++;

		{
			if(agregados>=index){
				try{
					Paragraph nombre= new Paragraph(alumnos.get(index).getNombres(),FontFactory.getFont("arial",5, Font.NORMAL, BaseColor.BLACK));
					nombre.setIndentationLeft(r5);
					nombre.setSpacingBefore(-8f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
		}
		index++;
		{
			if(agregados>=index){
				try{
					Paragraph nombre= new Paragraph(alumnos.get(index).getNombres(),FontFactory.getFont("arial",5, Font.NORMAL, BaseColor.BLACK));
					nombre.setIndentationLeft(r6);
					nombre.setSpacingBefore(-8f);
					document.add(nombre);
				}catch(Exception e){

				}
			}
		}
		index++;
		if(agregados>=index){
			try{
				Paragraph apellido1= new Paragraph(alumnos.get(index).getNombres(),FontFactory.getFont("arial",5, Font.NORMAL, BaseColor.BLACK));
				apellido1.setIndentationLeft(r7);
				apellido1.setSpacingBefore(-8f);
				document.add(apellido1);
			}catch(Exception e){

			}
		}
	}
	private ArrayList<Persona> alumnos;
	private float x=640f;
	private int b=1;
	private int agregados=0;

	public void addAlumnos(int j)
	{		
	
		PdfPTable tabla1 = new PdfPTable(7);
		j = j * 4;
		for(int i = 0; i < j; i++)
		{
			try
			{						
				Persona nb = alumnos.get(i);
				
				if(i = 0 || i = )
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
				
				tabla1.addCell(foto);

				document.add(tabla1);
			}
			catch(Exception e)
			{
				break;
			}
		}

		//			addNIAS(agregados, j, 75f);
		//			addApellidos(agregados, j);
		//			addNombres(agregados, j);
		//		}
		//
		//		{
		//			b=1;
		//			{
		//
		//				for(int i=(j+7);i<(j+7)+7;i++){
		//					try{
		//						Persona nb=alumnos.get(i);
		//						//				 System.out.println(nb.getBytesFoto());
		//
		//						Image foto=null;
		//						if(nb.getBytesFoto()==null){
		//							foto =Image.getInstance(getClass().getResource("/resource/default1.gif"));
		//						}else{
		//							//					 System.out.println(nb.getBytesFoto().length);
		//							foto=Image.getInstance(nb.getBytesFoto());
		//						}
		//						foto.scaleAbsolute(70, 70);
		//						foto.scaleToFit(70, 70);
		//						foto.setAbsolutePosition((39*b), x-110);
		//						float border=2.0f;
		//						foto.setBorderWidthBottom(border);
		//						foto.setBorderWidthLeft(border);
		//						foto.setBorderWidthLeft(border);
		//						foto.setBorderWidthRight(border);
		//						foto.setBorderWidthTop(border);
		//						foto.setBorderColor(BaseColor.BLACK);
		//						document.add(foto);
		//						agregados++;
		//						b+=2.98f;
		//					}catch(Exception e){
		//
		//					}
		//				}
		//			}
		//			addNIAS(agregados,(j+7),85f);
		//			addApellidos(agregados,(j+7));
		//			addNombres(agregados,(j+7));
		//
		//			{
		//				b=1;
		//				{
		//					for(int i=(j+7)+7;i<(j+7)+7+7;i++){
		//						try{
		//							Persona nb=alumnos.get(i);
		//							//					 System.out.println(nb.getBytesFoto());
		//
		//							Image foto=null;
		//							if(nb.getBytesFoto()==null){
		//								foto =Image.getInstance(getClass().getResource("/resource/default1.gif"));
		//							}else{
		//								//						 System.out.println(nb.getBytesFoto().length);
		//								foto=Image.getInstance(nb.getBytesFoto());
		//							}
		//							foto.scaleAbsolute(70, 70);
		//							foto.scaleToFit(70, 70);
		//
		//							foto.setAbsolutePosition((39*b), ((x-110)-110));
		//							float border=2.0f;
		//							foto.setBorderWidthBottom(border);
		//							foto.setBorderWidthLeft(border);
		//							foto.setBorderWidthLeft(border);
		//							foto.setBorderWidthRight(border);
		//							foto.setBorderWidthTop(border);
		//							foto.setBorderColor(BaseColor.BLACK);
		//
		//							document.add(foto);
		//							agregados++;
		//							b+=2.98f;
		//						}catch(Exception e){
		//
		//						}
		//					}
		//				}
		//				addNIAS(agregados,(j+7)+7,85f);
		//				addApellidos(agregados,(j+7)+7);
		//				addNombres(agregados,(j+7)+7);
		//			}
		//			b=1;
		//			{
		//				for(int i=(j+7)+7+7;i<(j+7)+7+7+7;i++){
		//					try{
		//						Persona nb=alumnos.get(i);
		//						//				 System.out.println(nb.getBytesFoto());
		//
		//						Image foto=null;
		//						if(nb.getBytesFoto()==null){
		//							foto =Image.getInstance(getClass().getResource("/resource/default1.gif"));
		//						}else{
		//							//					 System.out.println(nb.getBytesFoto().length);
		//							foto=Image.getInstance(nb.getBytesFoto());
		//						}
		//						foto.scaleAbsolute(70, 70);
		//						foto.scaleToFit(70, 70);
		//						foto.setAbsolutePosition((39*b), ((x-110)-221));
		//						float border=2.0f;
		//						foto.setBorderWidthBottom(border);
		//						foto.setBorderWidthLeft(border);
		//						foto.setBorderWidthLeft(border);
		//						foto.setBorderWidthRight(border);
		//						foto.setBorderWidthTop(border);
		//						foto.setBorderColor(BaseColor.BLACK);
		//						document.add(foto);
		//						agregados++;
		//						b+=2.98f;
		//					}catch(Exception e){	
		//					}
		//				}
		//			}
		//			addNIAS(agregados,(j+7)+7+7,85f);
		//			addApellidos(agregados,(j+7)+7+7);
		//			addNombres(agregados,(j+7)+7+7);
		//			{
		//				b=1;
		//				{
		//					//					System.out.println(i<(j+7)+7+7+7+7);
		//					for(int i=(j+7)+7+7+7;i<(j+7)+7+7+7+7;i++){
		//						try{
		//							//						System.out.println(i);
		//							Persona nb=alumnos.get(i);
		//
		//							Image foto=null;
		//							if(nb.getBytesFoto()==null){
		//								foto =Image.getInstance(getClass().getResource("/resource/default1.gif"));
		//							}else{
		//								//						 System.out.println(nb.getBytesFoto().length);
		//								foto=Image.getInstance(nb.getBytesFoto());
		//							}
		//							foto.scaleAbsolute(70, 70);
		//							foto.scaleToFit(70, 70);
		//							foto.setAbsolutePosition((39*b), ((x-110)-331));
		//							float border=2.0f;
		//							foto.setBorderWidthBottom(border);
		//							foto.setBorderWidthLeft(border);
		//							foto.setBorderWidthLeft(border);
		//							foto.setBorderWidthRight(border);
		//							foto.setBorderWidthTop(border);
		//							foto.setBorderColor(BaseColor.BLACK);
		//							document.add(foto);
		//							agregados++;
		//							b+=2.98f;
		//						}catch(Exception e){	
		//						}
		//					}
		//				}
		//				addNIAS(agregados,(j+7)+7+7+7,85f);
		//				addApellidos(agregados,(j+7)+7+7+7);
		//				addNombres(agregados,(j+7)+7+7+7);
		//			}	
		//		}

	}

	private String curso;
	private String codigoGrupo;
	private String tutor;
	private String nombreGrupo;
	private boolean f;

	public boolean crearPDF5(ComedorGUI gui, String curso, String codigoGrupo, String nombreGrupo, String tutor, ArrayList<Persona> alumnos, boolean f) throws Exception 
	{
		this.principal=gui;
		this.alumnos = alumnos;
		document = new Document();

		this.curso = curso;
		this.codigoGrupo = codigoGrupo;
		this.tutor = tutor;
		this.nombreGrupo = nombreGrupo;
		this.f = f;

		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("System-Comedor" + File.separator + tutor + ".pdf"));
		FooterPiePaginaiText footer = new FooterPiePaginaiText();
		writer.setPageEvent(footer);

		document.open();

		Paragraph texto1 = new Paragraph("" + principal.getBaseDeDatos().getCentro(), FontFactory.getFont("arial", 14, Font.BOLD, BaseColor.BLACK));
		Paragraph texto21 = new Paragraph("" + curso, FontFactory.getFont("arial", 9, Font.NORMAL, BaseColor.BLACK));
		Paragraph texto22 = new Paragraph("\na" + principal.getBaseDeDatos().getCodigoCentro(), FontFactory.getFont("arial", 9, Font.NORMAL, BaseColor.BLACK));

		texto21.setAlignment(Chunk.ALIGN_RIGHT);
		texto1.setAlignment(Chunk.ALIGN_CENTER);

		texto21.add(texto22);

		document.add(texto1);
		texto21.setSpacingBefore(-17);
		document.add(texto21);	

		Paragraph a11 = null;

		if(!f)
			a11 = new Paragraph("TUTOR/A: " + tutor, FontFactory.getFont("tahoma", 10, Font.NORMAL, BaseColor.BLACK));
		else
			a11 = new Paragraph(tutor, FontFactory.getFont("tahoma", 10, Font.NORMAL, BaseColor.BLACK));

		a11.setAlignment(Element.ALIGN_CENTER);

		PdfPTable tabla = new PdfPTable(1);
		tabla.setWidthPercentage(50);
		float[] medidaCeldas = {1.6f};
		tabla.setWidths(medidaCeldas);
		tabla.getDefaultCell().setPadding(1);
		tabla.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
		tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		tabla.addCell(a11);

		document.add(tabla);

		{
			Paragraph b1 = null;

			if(!f)
			{
				b1 = new Paragraph("Curso/Curs: ", FontFactory.getFont("arial", 10, Font.NORMAL, BaseColor.BLACK));
				b1.setAlignment(Element.ALIGN_LEFT);

				Paragraph texto9 = new Paragraph("" + codigoGrupo, FontFactory.getFont("arial", 9, Font.BOLD, BaseColor.BLACK));
				b1.add(texto9);
			}
			else
			{
				b1=new Paragraph(" ",
						FontFactory.getFont("arial", // fuente
								10, // tamaño
								Font.NORMAL, // estilo
								BaseColor.BLACK));
				b1.setAlignment(Element.ALIGN_LEFT);

				Paragraph texto9 = new Paragraph(" ", FontFactory.getFont("arial",9, Font.BOLD, BaseColor.BLACK));
				b1.add(texto9);
			}

			Paragraph b11 =null;
			if(!f){
				b11=new Paragraph("Grupo/Grup: ",
						FontFactory.getFont("arial", // fuente
								10, // tamaño
								Font.NORMAL, // estilo
								BaseColor.BLACK));
				Paragraph texto91 = new Paragraph(""+nombreGrupo, FontFactory.getFont("arial",9, Font.BOLD, BaseColor.BLACK));
				b11.add(texto91);

				b1.add(b11);
			}else{
				b11=new Paragraph(" ",
						FontFactory.getFont("arial", // fuente
								10, // tamaño
								Font.NORMAL, // estilo
								BaseColor.BLACK));
				Paragraph texto91 = new Paragraph(" ", FontFactory.getFont("arial",9, Font.BOLD, BaseColor.BLACK));
				b11.add(texto91);

				b1.add(b11);
			}
			document.add(b1);
		}		
		addAlumnos(agregados);

		int y = 35;

		while(true)
		{
			if(agregados == y)
			{
				addNewPage();
				addAlumnos(agregados - 1);
				y += 35;
				continue;
			}
			break;
		}
		//		 
		//		 

		document.close();
		if(principal.getBaseDeDatos().getImpresionDirecta()){
			new Impresora(principal).print(new File("System-Comedor"+File.separator+tutor+".pdf").toString());
		}else{
			Desktop.getDesktop().open(
					new File("System-Comedor"+File.separator+tutor+".pdf"));
		}
		return true;
	}
	public class FooterPiePaginaiText extends  PdfPageEventHelper {

		@Override
		public void onEndPage(PdfWriter writer, Document document) {
			ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Pagina:"+nPage), 55,20,0);
			nPage++;
		}
	}

	private int nPage=1;

	public void addNewPage() throws DocumentException{

		document.add(new Paragraph("\n"));
		document.add(new Paragraph("\n"));
		document.add(new Paragraph("\n"));
		document.add(new Paragraph("\n"));
		document.add(new Paragraph("\n"));
		document.add(new Paragraph("\n"));

		Paragraph texto1 = new Paragraph("" + principal.getBaseDeDatos().getCentro(), FontFactory.getFont("arial", 14, Font.BOLD, BaseColor.BLACK));
		Paragraph texto21 = new Paragraph("" + curso, FontFactory.getFont("arial", 9, Font.NORMAL, BaseColor.BLACK));
		Paragraph texto22 = new Paragraph("\na" + principal.getBaseDeDatos().getCodigoCentro(), FontFactory.getFont("arial", 9, Font.NORMAL, BaseColor.BLACK));

		texto21.setAlignment(Chunk.ALIGN_RIGHT);
		texto1.setAlignment(Chunk.ALIGN_CENTER);
		texto21.add(texto22);

		document.add(texto1);
		texto21.setSpacingBefore(-17);
		document.add(texto21);	

		Paragraph a11 = null;

		if(!f)
		{
			a11=new Paragraph("TUTOR/A: "+tutor,
					FontFactory.getFont("tahoma", // fuente
							10, // tamaño
							Font.NORMAL, // estilo
							BaseColor.BLACK));
		}
		else
		{
			a11=new Paragraph(tutor,
					FontFactory.getFont("tahoma", // fuente
							10, // tamaño
							Font.NORMAL, // estilo
							BaseColor.BLACK));
		}
		a11.setAlignment(Element.ALIGN_CENTER);

		PdfPTable tabla = new PdfPTable(1);
		tabla.setWidthPercentage(50);
		float[] medidaCeldas = {1.6f};
		tabla.setWidths(medidaCeldas);
		tabla.getDefaultCell().setPadding(1);
		tabla.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
		tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		tabla.addCell(a11);

		document.add(tabla);
		{

			Paragraph b1 =
					null;
			if(!f){
				b1=new Paragraph("Curso/Curs: ",
						FontFactory.getFont("arial", // fuente
								10, // tamaño
								Font.NORMAL, // estilo
								BaseColor.BLACK));
				b1.setAlignment(Element.ALIGN_LEFT);

				Paragraph texto9 = new Paragraph(""+codigoGrupo, FontFactory.getFont("arial",9, Font.BOLD, BaseColor.BLACK));
				b1.add(texto9);
			}else{
				b1=new Paragraph(" ",
						FontFactory.getFont("arial", // fuente
								10, // tamaño
								Font.NORMAL, // estilo
								BaseColor.BLACK));
				b1.setAlignment(Element.ALIGN_LEFT);

				Paragraph texto9 = new Paragraph(" ", FontFactory.getFont("arial",9, Font.BOLD, BaseColor.BLACK));
				b1.add(texto9);
			}

			Paragraph b11 =null;
			if(!f){
				b11=new Paragraph("Grupo/Grup: ",
						FontFactory.getFont("arial", // fuente
								10, // tamaño
								Font.NORMAL, // estilo
								BaseColor.BLACK));
				Paragraph texto91 = new Paragraph(""+nombreGrupo, FontFactory.getFont("arial",9, Font.BOLD, BaseColor.BLACK));
				b11.add(texto91);

				b1.add(b11);
			}else{
				b11=new Paragraph(" ",
						FontFactory.getFont("arial", // fuente
								10, // tamaño
								Font.NORMAL, // estilo
								BaseColor.BLACK));
				Paragraph texto91 = new Paragraph(" ", FontFactory.getFont("arial",9, Font.BOLD, BaseColor.BLACK));
				b11.add(texto91);

				b1.add(b11);
			}
			document.add(b1);
		}
	}
}