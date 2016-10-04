package comedor.carnets;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.JOptionPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import comedor.ComedorGUI;
import comedor.Impresora;

public class PdfCarnet 
{
	public PdfCarnet(String fileName, BufferedImage c, int mODE,  final ComedorGUI principal)
	{
		try
		{
			Document document = new Document();
			
			if(mODE == 1)
			{
				PdfWriter.getInstance(document, new FileOutputStream(fileName));
				Rectangle one = new Rectangle(242.60f, 153.1f);
				document.setPageSize(one);
				document.setMargins(-1, 0, -1, 0);
				
				document.open();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(c, "png", baos);
				ImageIO.write(c, "png", new FileOutputStream("c:\\Nueva Carpeta\\"+Math.random()+".jpg"));
				baos.flush();
				byte[] imageInByte = baos.toByteArray();
				baos.close();
				Image imagen = Image.getInstance(imageInByte);
				imagen.scaleAbsoluteWidth(154.54f);
				imagen.scaleAbsoluteHeight(243.99f);
				imagen.setUseVariableBorders(true);
				imagen.rotate();
				imagen.setRotationDegrees(90);
				document.add(imagen);
			}
			else
			{
				PdfWriter.getInstance(document, new FileOutputStream(fileName));
				Rectangle one = new Rectangle(242.60f, 153.1f);
				document.setPageSize(one);
				document.setMargins(-1, 0, -1, 0);
				document.open();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(c, "png", baos );
				ImageIO.write(c, "png", new FileOutputStream("c:\\Nueva Carpeta\\"+Math.random()+".jpg"));
				baos.flush();
				byte[] imageInByte = baos.toByteArray();
				baos.close();
				Image imagen = Image.getInstance(imageInByte);
				imagen.scaleAbsoluteHeight(154.54f);
				imagen.scaleAbsoluteWidth(243.99f);
				imagen.setUseVariableBorders(true);
				document.add(imagen);
			}
			
			/** SE AGREGA EL CIERRE DEL DOCUMENTO YA QUE AL QUEDAR ABIERTO NO SE REALIZA LA IMPRESION DEL MISMO */
			document.close();
			
			Thread.sleep(1000);
			
			if(principal.getBaseDeDatos().isImpresoraCarnetConfigurada())
				new Impresora(principal).printCarnet(new File(fileName).toString());
			else
				if(Desktop.isDesktopSupported())
					Desktop.getDesktop().open(new File(fileName));
				else
					JOptionPane.showMessageDialog(null, "Accion no Soportada!","No Soportado!",JOptionPane.WARNING_MESSAGE);
			
//			document.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}