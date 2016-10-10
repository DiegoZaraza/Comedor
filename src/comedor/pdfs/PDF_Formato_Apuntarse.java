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
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import comedor.ComedorGUI;
import comedor.Impresora;

public class PDF_Formato_Apuntarse 
{
	private Document document;
	private PdfPCell celda;
	private int nPage = 1;
	private ArrayList<String> arrayList;
	private String selMes;
	private String tipoInf;
	private PdfPTable tabla;

	public class FooterPiePaginaiText extends PdfPageEventHelper 
	{
		@Override
		public void onStartPage(PdfWriter writer, Document document) 
		{
			Paragraph textoTitulo = new Paragraph("I'IES EL SALER", FontFactory.getFont("arial", 20, Font.BOLD, BaseColor.BLACK));
			textoTitulo.setAlignment(Chunk.ALIGN_CENTER);

			String mesx = "" + getSelMes().split("-")[1];
			Paragraph texto22 = new Paragraph("FORMATO REGISTRO PROFESORES PARA EL MES DE ", FontFactory.getFont("arial", 12, Font.NORMAL, BaseColor.BLACK));
			texto22.setAlignment(Chunk.ALIGN_CENTER);

			Paragraph texto221x = new Paragraph(mesx, FontFactory.getFont("arial", 12, Font.NORMAL | Font.UNDERLINE, BaseColor.BLACK));
			texto221x.setAlignment(Chunk.ALIGN_CENTER);

			texto22.add(texto221x);

			Paragraph texto222x = new Paragraph("LISTADO DIAS " + getTipoInf() + "S", FontFactory.getFont("arial", 12, Font.NORMAL | Font.UNDERLINE, BaseColor.BLACK));
			texto222x.setAlignment(Chunk.ALIGN_CENTER);

			ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, textoTitulo, (document.right() - document.left()) / 2 + document.leftMargin(), document.top(), 0);
			ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, texto22, (document.right() - document.left()) / 2 + document.leftMargin(), document.top() - 20, 0);
			ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER,	texto222x, (document.right() - document.left()) / 2 + document.leftMargin(), document.top() - 35, 0);
		}

		@Override
		public void onEndPage(PdfWriter writer, Document document) 
		{
			Paragraph Pie = new Paragraph("Pagina: " + nPage, FontFactory.getFont("arial", 12, Font.NORMAL, BaseColor.BLACK));

			ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, Pie, 42, 20, 0);
			nPage++;
		}
	}

	public PDF_Formato_Apuntarse(ComedorGUI principal, ArrayList<Persona> personas2, String selected, String n, String tipo) 
	{
		setSelMes(selected);
		setTipoInf(tipo);

		document = new Document();

		try 
		{
			Date date = new Date(new java.util.Date().getTime());
			
			String g = date.toString();
			String ano = g.split("-")[0];
			String m = selected.split("-")[1];
			String ruta = "System-Comedor" + File.separator + "Temp" + File.separator + "" + n + "_" + m + ".pdf";
			
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(ruta));
			FooterPiePaginaiText footer = new FooterPiePaginaiText();
			writer.setPageEvent(footer);
			document.setPageSize(PageSize.A4.rotate());
			document.setMargins(4, 10, 30, 20);

			document.open();

			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));
			
			int y = Integer.parseInt(selected.split("-")[0]);
			String tem = "";
			if (y <= 9)
				tem = "0" + y;
			else
				tem = "" + y;

			String mes = tem;
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("EEEE");
			arrayList = new ArrayList<>();

			for (int index = 1; index <= 32; index++) 
			{
				String fecha = ano + "-" + mes + "-" + index;
				try 
				{
					String h = new Date(dateFormat.parse(fecha).getTime()).toString();
					m = h.split("-")[1];
					String dia = dateFormat2.format(
							new Date(dateFormat.parse(fecha).getTime()))
							.toString();
					if (!dia.equalsIgnoreCase("sábado") && !dia.equalsIgnoreCase("domingo")) 
					{
						if (m.equalsIgnoreCase(mes)) 
						{
							if (tipo.equalsIgnoreCase("ORDINARIO") && (dia.equalsIgnoreCase("lunes") || dia.equalsIgnoreCase("martes") || dia.equalsIgnoreCase("jueves"))) 
								arrayList.add(h + "@1" + dia);

							if (tipo.equalsIgnoreCase("EXTRAORDINARIO") && (dia.equalsIgnoreCase("miércoles") || dia.equalsIgnoreCase("viernes"))) 
								arrayList.add(h + "@1" + dia);
						}
						else 
							break;
					}
				} 
				catch (ParseException e) 
				{
					e.printStackTrace();
				}
			}


			setTabla(new PdfPTable(arrayList.size() + 1));
			headerTable();

			for (int index = 0; index < personas2.size() ; index++) 
			{
				if(index % 24 == 0 && index != 0)
				{
					document.add(getTabla());

					document.newPage();

					setTabla(new PdfPTable(arrayList.size() + 1));
					headerTable();

					document.add(new Paragraph("\n"));
					document.add(new Paragraph("\n"));
					document.add(new Paragraph("\n"));
				}

				Paragraph t2 = null;

				t2 = new Paragraph(personas2.get(index).getNombreCompleto(), FontFactory.getFont("arial", 10, Font.NORMAL, BaseColor.BLACK));
				getTabla().addCell(t2);

				for (int index1 = 0; index1 < arrayList.size(); index1++) 
				{
					t2 = new Paragraph("", FontFactory.getFont("arial", 10, Font.NORMAL, BaseColor.BLACK));
					getTabla().addCell(t2);
				}
			}

			document.add(getTabla());
			document.close();

			if (principal.getBaseDeDatos().getImpresionDirecta()) 
				new Impresora(principal).print(new File(ruta).toString());
			else
			{
				try
				{
					Desktop.getDesktop().open(new File(ruta));
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (DocumentException e) 
		{
			e.printStackTrace();
		}
	}

	public void headerTable()
	{
		getTabla().setWidthPercentage(92);

		float[] medidaCeldas = new float[arrayList.size() + 1];

		for (int index = 0; index < medidaCeldas.length; index++) 
		{
			if (index == 0)
				medidaCeldas[index] = 1.6f;
			else
				medidaCeldas[index] = 0.065f;
		}

		try 
		{
			this.tabla.setWidths(medidaCeldas);
		}
		catch (DocumentException e) 
		{
			e.printStackTrace();
		}
		
		int tam = 9;
		
		celda = new PdfPCell(new Paragraph("", FontFactory.getFont("arial", tam, Font.NORMAL, BaseColor.BLACK)));
		celda.setColspan(1);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(5.4f);

		getTabla().addCell(celda);

		for (int index = 0; index < arrayList.size(); index++) 
		{
			int fg = Integer.parseInt(arrayList.get(index).split("@1")[0].split("-")[2]);

			celda = new PdfPCell(new Paragraph("" + fg, FontFactory.getFont("arial", 7, Font.NORMAL, BaseColor.BLACK)));
			celda.setColspan(1);
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			celda.setPadding(5.4f);
			getTabla().addCell(celda);
		}

		celda = new PdfPCell(new Paragraph("APELLIDOS Y NOMBRES", FontFactory.getFont("arial", 14, Font.BOLD, BaseColor.BLACK)));
		celda.setColspan(1);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(5.4f);
		celda.setBackgroundColor(new BaseColor(new Color(230, 223, 223)));
		getTabla().addCell(celda);

		for (int index = 0; index < arrayList.size(); index++) 
		{
			if (arrayList.get(index).split("@1")[1].equalsIgnoreCase("jueves") || arrayList.get(index).split("@1")[1].equalsIgnoreCase("viernes")) 
			{
				StringBuffer buffer = new StringBuffer(arrayList.get(index).split("@1")[1].toUpperCase());
				celda = new PdfPCell(new Paragraph("" + buffer, FontFactory.getFont("arial", tam, Font.BOLD, BaseColor.BLUE)));
				celda.setColspan(1);
				celda.setHorizontalAlignment(Element.ALIGN_CENTER);
				celda.setPadding(5.4f);
				celda.setBackgroundColor(new BaseColor(new Color(230, 223, 223)));
			} 
			else 
			{
				StringBuffer buffer = new StringBuffer(arrayList.get(index).split("@1")[1].toUpperCase());
				celda = new PdfPCell(new Paragraph("" + buffer, FontFactory.getFont("arial", tam, Font.NORMAL, BaseColor.BLACK)));
				celda.setColspan(1);
				celda.setHorizontalAlignment(Element.ALIGN_CENTER);
				celda.setPadding(5.4f);
				celda.setBackgroundColor(new BaseColor(new Color(230, 223, 223)));
			}
			getTabla().addCell(celda);
		}
		getTabla().setHeaderRows(2);
	}

	public String getSelMes() 
	{
		return selMes;
	}

	public void setSelMes(String selMes) 
	{
		this.selMes = selMes;
	}

	public String getTipoInf() 
	{
		return tipoInf;
	}

	public void setTipoInf(String tipoInf) 
	{
		this.tipoInf = tipoInf;
	}

	public PdfPTable getTabla()
	{
		return tabla;
	}

	public void setTabla(PdfPTable tabla) 
	{
		this.tabla = tabla;
	}
}