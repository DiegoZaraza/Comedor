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

public class PDF_Carta_3 {

	private static Document document;
	public PDF_Carta_3(ComedorGUI principal,String nia) throws DocumentException, MalformedURLException, IOException {
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
				

					document.add(new Paragraph("\n"));
				Paragraph texto22 = new Paragraph("NOTIFICACI� DE BAIXA DE MENJADOR", FontFactory.getFont("arial",12,
						Font.BOLD, BaseColor.BLACK));
				texto22.setAlignment(Chunk.ALIGN_CENTER);
				document.add(new Paragraph("\n\n\n"));
				document.add(texto22);
				
//			    texto22 = new Paragraph("Rebuts pendents. Devoluci� banc�ria", FontFactory.getFont("arial",12,
//						Font.BOLD, BaseColor.BLACK));
//				texto22.setAlignment(Chunk.ALIGN_CENTER);
//				document.add(texto22);
				
				texto22 = new Paragraph("A l'atenci� del pare/mare dels alumnes:", FontFactory.getFont("arial",10,
							Font.NORMAL, BaseColor.BLACK));
				texto22.setAlignment(Chunk.ALIGN_LEFT);
				document.add(new Paragraph("\n\n"));
				document.add(texto22);
				
				texto22 = new Paragraph(""+principal.getBaseDeDatos().getApellidosYNombres(nia)+", del grup "+principal.getBaseDeDatos().getCodigoGrupo(nia)	, FontFactory.getFont("arial",10,
						Font.NORMAL, BaseColor.BLACK));
				texto22.setAlignment(Chunk.ALIGN_LEFT);
				document.add(new Paragraph("\n\n"));
				document.add(texto22);
			
			
				new DecimalFormat("###.##");
				texto22 = new Paragraph("La Secret�ria de l'IES El Saler li informa que, despr�s d'haver estat informat en diverses ocasions, encara mant� un deute amb el centre de quotes de menjador, i per tant la Direcci� ha decidit donar de baixa els seus fills/filles del servei de menjador a partir del dia ______________________.", FontFactory.getFont("arial",10,
						Font.NORMAL, BaseColor.BLACK));
				texto22.setAlignment(Chunk.ALIGN_JUSTIFIED);
				document.add(new Paragraph("\n\n"));
				document.add(texto22);
				texto22 = new Paragraph("Li recordem aix� mateix, que l'assist�ncia al menjador escolar no �s obligat�ria i que l'�s d'este servei comporta unes obligacions, entre les quals figura el pagament de les quotes en els terminis establerts en el Reglament de R�gim Interior, i que en el cas dels seus fills/filles ja han passat.", FontFactory.getFont("arial",10,
						Font.NORMAL, BaseColor.BLACK));
				texto22.setAlignment(Chunk.ALIGN_JUSTIFIED);
				document.add(new Paragraph("\n\n"));
				document.add(texto22);
			
			
				texto22 = new Paragraph("Li preguem que es pose en contacte amb la Direcci� per resoldre aquesta situaci� el m�s aviat possible.", FontFactory.getFont("arial",10,
						Font.NORMAL, BaseColor.BLACK));
				texto22.setAlignment(Chunk.ALIGN_JUSTIFIED);
				document.add(new Paragraph("\n\n"));
				document.add(texto22);
				
				texto22 = new Paragraph("I li recordem una volta m�s que ha de fer l'ingr�s directament al compte de Bankia de l'IES El Saler :", FontFactory.getFont("arial",10,
						Font.NORMAL, BaseColor.BLACK));
				texto22.setAlignment(Chunk.ALIGN_LEFT);
//				document.add(new Paragraph("\n\n"));
				document.add(texto22);
				
				
				
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
							texto22 = new Paragraph("Quedem a la seua disposici� per a qualsevol consulta.", FontFactory.getFont("arial",10,
									Font.NORMAL, BaseColor.BLACK));
							texto22.setAlignment(Chunk.ALIGN_LEFT);
//							document.add(new Paragraph("\n\n"));
							document.add(texto22);
							texto22 = new Paragraph("Atentament,", FontFactory.getFont("arial",10,
									Font.NORMAL, BaseColor.BLACK));
							texto22.setAlignment(Chunk.ALIGN_LEFT);
//							document.add(new Paragraph("\n\n"));
							document.add(texto22);
							texto22 = new Paragraph("El Saler, a _____ de/ d�_________________ de 20____", FontFactory.getFont("arial",10,
									Font.NORMAL, BaseColor.BLACK));
							texto22.setAlignment(Chunk.ALIGN_LEFT);
							document.add(new Paragraph("\n"));
							document.add(texto22);
							
							texto22 = new Paragraph("La Secret�ria de l'IES El Saler", FontFactory.getFont("arial",10,
									Font.NORMAL, BaseColor.BLACK));
							texto22.setAlignment(Chunk.ALIGN_RIGHT);
							document.add(new Paragraph("\n\n"));
							document.add(texto22);
							
							texto22 = new Paragraph("* Es prega escriure exactament este concepte en l'ingr�s", FontFactory.getFont("arial",10,
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
