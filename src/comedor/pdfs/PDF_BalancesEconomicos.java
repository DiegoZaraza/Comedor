package comedor.pdfs;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import comedor.ComedorGUI;
import comedor.Impresora;

public class PDF_BalancesEconomicos {

	private static Document document;
	private Paragraph texto22;

	public PDF_BalancesEconomicos(ComedorGUI principal, float total_generado, float valor_total_remesas, float valor_total_remesas_comision, int total_remesas_emitidas) throws FileNotFoundException, DocumentException {
				document = new Document();
				PdfWriter.getInstance(document,
						new FileOutputStream("System-Comedor"+File.separator+"Temp"+File.separator+"listado_balances.pdf"));
				document.open();
			texto22 = new Paragraph("BALANCES ECONOMICOS MENSUALES", FontFactory.getFont("arial",12,
						Font.BOLD, BaseColor.BLACK));
				texto22.setAlignment(Chunk.ALIGN_CENTER);	
				document.add(texto22);
				
//				texto22 = new Paragraph("Desde: "+principal.getBaseDeDatos().getFechaHumana(date), FontFactory.getFont("arial",12,
//						Font.NORMAL, BaseColor.BLACK));
//				texto22.setAlignment(Chunk.ALIGN_CENTER);	
//				document.add(texto22);
//			
//				
//				
//				texto22 = new Paragraph("Hasta: "+principal.getBaseDeDatos().getFechaHumana(date2), FontFactory.getFont("arial",12,
//						Font.NORMAL, BaseColor.BLACK));
//				texto22.setAlignment(Chunk.ALIGN_CENTER);	
//				document.add(texto22);
//				
				document.add(new Paragraph("\n\n"));
//				
				DecimalFormat df = new DecimalFormat("###.##");
		
				texto22 = new Paragraph("Nro de remesas emitidas: "+total_remesas_emitidas	, FontFactory.getFont("arial",11,
						Font.NORMAL, BaseColor.BLACK));
				texto22.setAlignment(Chunk.ALIGN_LEFT);
				document.add(texto22);
				document.add(new Paragraph("\n"));
				
				texto22 = new Paragraph("Valor del total de todas las remesas : "+df.format(total_generado)+"€", FontFactory.getFont("arial",11,
						Font.NORMAL, BaseColor.BLACK));
				texto22.setAlignment(Chunk.ALIGN_LEFT);
				document.add(texto22);
				document.add(new Paragraph("\n"));
			
				texto22 = new Paragraph("Valor del total de  en estado pagado o pagado con comision: "+df.format(valor_total_remesas_comision)+"€", FontFactory.getFont("arial",11,
						Font.NORMAL, BaseColor.BLACK));
				texto22.setAlignment(Chunk.ALIGN_LEFT);
				document.add(texto22);
				document.add(new Paragraph("\n"));

				texto22 = new Paragraph("Valor del total de  en estado pagado o pagado con comision: "+df.format(valor_total_remesas_comision)+"€", FontFactory.getFont("arial",11,
						Font.NORMAL, BaseColor.BLACK));
				texto22.setAlignment(Chunk.ALIGN_LEFT);
				document.add(texto22);
				document.add(new Paragraph("\n"));
				
				document.close();

				if(principal.getBaseDeDatos().getImpresionDirecta()){
					  new Impresora(principal).print(new File("System-Comedor"+File.separator+"Temp"+File.separator+"listado_balances.pdf").toString());
				}else{
					try {
						Desktop.getDesktop().open(
								new File("System-Comedor"+File.separator+"Temp"+File.separator+"listado_balances.pdf"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	}

}
