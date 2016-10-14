//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.nio.channels.FileChannel;
//import java.util.ArrayList;
//
//import comedor.bd.BD;
//import comedor.pdfs.Persona;
//
//
//public class Copy {
//
//	public static void main(String[] args) 
//	{
//		BD base = new BD(null, "postgres", "1234", "localhost", null);
//		ArrayList<Persona> xz  =  base.getTodosLosAlumnos("2016/17");
//
//		for(int index = 0; index < xz.size(); index++)
//		{
//			try{
//				FileInputStream fis = new FileInputStream("System-Comedor"+File.separator+"Fotos"+File.separator+"FotoPrueba.jpg"); //inFile -> Archivo a copiar
//				FileOutputStream fos = new FileOutputStream("System-Comedor"+File.separator+"Fotos"+File.separator+ xz.get(index).getNia() + ".jpg"); //outFile -> Copia del archivo
//				FileChannel inChannel = fis.getChannel(); 
//				FileChannel outChannel = fos.getChannel(); 
//				inChannel.transferTo(0, inChannel.size(), outChannel); 
//				fis.close(); 
//				fos.close();
//			}catch (IOException ioe) {
//				ioe.printStackTrace();
//			}
//
//		}
//	}
//
//}


import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class Copy {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Copy().createPDF();
	}
	public void createPDF(){
		Document document = new Document (PageSize.A4);
		try {
			PdfWriter.getInstance(document, new FileOutputStream("C:/Nueva Carpeta/AddImage.pdf"));
			document.open ();
			document.addCreator("Binod by Demo.java");
			document.addAuthor("Binod Suman");
			document.addTitle("First PDF By Binod");
			Image image = Image.getInstance("https://snaplogic-h.s3.amazonaws.com/uploads/partner/logo/24/stratappa.jpg?u=1382443264");
			image.scaleAbsolute(50,50);
			document.add(image);

			Paragraph paragraph = new Paragraph(
					" Factors",new Font(Font.FontFamily.HELVETICA, 25));
			document.add(paragraph);
			document.add(Chunk.NEWLINE);

			PdfPTable table = new PdfPTable(8);
			table.setWidthPercentage(100);
			float[] columnWidths = new float[] { 7, 20, 9, 9, 9, 9, 5, 3 };
			table.setWidths(columnWidths);

			PdfPCell cell = new PdfPCell();
			cell.setColspan(8);
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(cell);
			table.addCell(" ");
			table.addCell(" ");
			table.addCell("");
			table.addCell("");
			table.addCell("%");
			table.addCell("");
			table.addCell(" ");
			table.addCell(" ");
			document.add(table);
			document.close ();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("******** PDF Created ***************");
	}
}