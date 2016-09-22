package comedor.pdfs;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import comedor.ComedorGUI;
import comedor.Impresora;

public class PDF_Carta_2 {

	private static Document document;
	public PDF_Carta_2(ComedorGUI principal,String nia, String ultimno) throws DocumentException, MalformedURLException, IOException {
		// TODO Auto-generated constructor stub
		// TODO Auto-generated method stub
				document = new Document();
				PdfWriter.getInstance(document,
						new FileOutputStream("System-Comedor"+File.separator+"Temp"+File.separator+"carta1.pdf"));
				document.open();
				
				     Image fondo = Image.getInstance(getClass().getResource("/resource/imagePDF.png"));	
					fondo.scaleToFit(540, 200);
					fondo.setAbsolutePosition(30, 730);
					document.add(fondo);
				

				
				Paragraph texto22 = new Paragraph("AVÍS DE MENJADOR 2", FontFactory.getFont("arial",12,
						Font.BOLD, BaseColor.BLACK));
				texto22.setAlignment(Chunk.ALIGN_CENTER);
				document.add(new Paragraph("\n\n\n\n\n"));
				document.add(texto22);
				
			    texto22 = new Paragraph("Rebuts pendents. Devolució bancària", FontFactory.getFont("arial",12,
						Font.BOLD, BaseColor.BLACK));
				texto22.setAlignment(Chunk.ALIGN_CENTER);
				document.add(texto22);
				
				texto22 = new Paragraph("A l'atenció del pare/mare dels alumnes:", FontFactory.getFont("arial",10,
							Font.NORMAL, BaseColor.BLACK));
				texto22.setAlignment(Chunk.ALIGN_LEFT);
				document.add(new Paragraph("\n"));
				document.add(texto22);
				
				texto22 = new Paragraph(""+principal.getBaseDeDatos().getApellidosYNombres(nia)+", del grup "+principal.getBaseDeDatos().getCodigoGrupo(nia)	, FontFactory.getFont("arial",10,
						Font.NORMAL, BaseColor.BLACK));
				texto22.setAlignment(Chunk.ALIGN_LEFT);
				document.add(new Paragraph("\n\n"));
				document.add(texto22);
			
			
				DecimalFormat df = new DecimalFormat("###.##");
				texto22 = new Paragraph("La Secretària de l'IES El Saler li recorda que estan al cobrament en la secretaria del Centre els rebuts de menjador dels seus fills / filles  per un total de "+df.format(Float.parseFloat(ultimno.split("@1")[1]))+"€,  corresponents a les quotes "+ultimno.split("@1")[0]+".", FontFactory.getFont("arial",10,
						Font.NORMAL, BaseColor.BLACK));
				texto22.setAlignment(Chunk.ALIGN_JUSTIFIED);
				document.add(new Paragraph("\n\n"));
				document.add(texto22);
				texto22 = new Paragraph("Li comuniquem que, tal i com està contemplat en el Reglament de Règim Interior del centre, en cas de no fer efectiu el pagament abans del dia_________________________, els seus fills/filles", FontFactory.getFont("arial",10,
						Font.NORMAL, BaseColor.BLACK));
				texto22.setAlignment(Chunk.ALIGN_JUSTIFIED);
				document.add(new Paragraph("\n\n"));
				
				Paragraph texto221 = new Paragraph(" no podran fer ús del servici de menjador fins que abone els deutes corresponents.", FontFactory.getFont("arial",10,
						Font.BOLD, BaseColor.BLACK));
//				texto22.setAlignment(Chunk.ALIGN_JUSTIFIED);
				texto22.add(texto221);
				document.add(texto22);
			
			
//				texto22 = new Paragraph("Li preguem que efectue l'ingrés al compte de Bankia de l'IES El Saler abans del dia:", FontFactory.getFont("arial",10,
//						Font.NORMAL, BaseColor.BLACK));
//				texto22.setAlignment(Chunk.ALIGN_JUSTIFIED);
//				document.add(new Paragraph("\n\n"));
//				document.add(texto22);
				
//				texto22 = new Paragraph("___________________________", FontFactory.getFont("arial",10,
//						Font.NORMAL, BaseColor.BLACK));
//				texto22.setAlignment(Chunk.ALIGN_LEFT);
////				document.add(new Paragraph("\n\n"));
//				document.add(texto22);
				
				
				
				  texto22 = new Paragraph("ES64 2038 6589 5260 0000 5038", FontFactory.getFont("arial",12,
							Font.BOLD, BaseColor.BLACK));
					texto22.setAlignment(Chunk.ALIGN_CENTER);
					document.add(new Paragraph("\n\n"));
					document.add(texto22);
					  texto22 = new Paragraph("Concepte: Menjador _________________________________________________*", FontFactory.getFont("arial",12,
								Font.BOLD, BaseColor.BLACK));
						texto22.setAlignment(Chunk.ALIGN_CENTER);
//						document.add(new Paragraph("\n"));
						texto22.setSpacingBefore(5.30f);
						document.add(texto22);
						 texto22 = new Paragraph("Import: _______________", FontFactory.getFont("arial",12,
									Font.BOLD, BaseColor.BLACK));
							texto22.setAlignment(Chunk.ALIGN_CENTER);
							texto22.setSpacingBefore(5.30f);
//							document.add(new Paragraph("\n"));
							document.add(texto22);
							document.add(new Paragraph("\n\n"));
							texto22 = new Paragraph("Quedem a la seua disposició per a qualsevol consulta.", FontFactory.getFont("arial",10,
									Font.NORMAL, BaseColor.BLACK));
							texto22.setAlignment(Chunk.ALIGN_LEFT);
//							document.add(new Paragraph("\n\n"));
							document.add(texto22);
							texto22 = new Paragraph("Atentament,", FontFactory.getFont("arial",10,
									Font.NORMAL, BaseColor.BLACK));
							texto22.setAlignment(Chunk.ALIGN_LEFT);
//							document.add(new Paragraph("\n\n"));
							document.add(texto22);
							texto22 = new Paragraph("El Saler, a _____ de/ d´_________________ de 20____", FontFactory.getFont("arial",10,
									Font.NORMAL, BaseColor.BLACK));
							texto22.setAlignment(Chunk.ALIGN_LEFT);
							document.add(new Paragraph("\n"));
							document.add(texto22);
							
							texto22 = new Paragraph("La Secretària de l'IES El Saler", FontFactory.getFont("arial",10,
									Font.NORMAL, BaseColor.BLACK));
							texto22.setAlignment(Chunk.ALIGN_RIGHT);
							document.add(new Paragraph("\n\n"));
							document.add(texto22);
							
							texto22 = new Paragraph("* Es prega escriure exactament este concepte en l'ingrés", FontFactory.getFont("arial",10,
									Font.NORMAL, BaseColor.BLACK));
							texto22.setAlignment(Chunk.ALIGN_RIGHT);
							document.add(new Paragraph("\n\n"));
							document.add(texto22);
				
				
//				
//				String fecha1=principal.getBaseDeDatos().getFechaHumana(date);
//				String fecha2=principal.getBaseDeDatos().getFechaHumana(date2);
//				Paragraph texto212 = new Paragraph("Desde: "+fecha1+" Hasta:"+fecha2+"", FontFactory.getFont("arial",9,
//						Font.NORMAL, BaseColor.BLACK));
//				texto212.setAlignment(Chunk.ALIGN_CENTER);
//				document.add(texto212);
//				document.add(new Paragraph("\n"));
				
				
				
				
				
				document.close();

				if(principal.getBaseDeDatos().getImpresionDirecta()){
					  new Impresora(principal).print(new File("System-Comedor"+File.separator+"Temp"+File.separator+"carta1.pdf").toString());
				}else{
					try {
						Desktop.getDesktop().open(
								new File("System-Comedor"+File.separator+"Temp"+File.separator+"carta1.pdf"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	}

}
