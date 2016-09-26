package comedor.carnets;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.ProgressMonitor;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.jdesktop.swingx.JXImageView;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.leyer.JKTree;

import comedor.ComedorGUI;

public class Carnet extends JPanel 
{
	private static final long serialVersionUID = 1L;
	private PanelImage panelImage;
	private JXImageView imageView1;
	private JButton button1;
	private JButton button2;
	private JButton button11;
	private JButton button12;
	private JButton button16;
	private DefaultMutableTreeNode root;
	private JKTree tree;

	private Carnet getInstance()
	{
		return this;
	}

	private int countElements = 0;
	private JLabel label;

	public int getCountElements() 
	{
		return countElements;
	}

	private File fileBackground;
	private Hashtable<String, Object> hashtableElements =new Hashtable<>();
	private ProgressMonitor progressMonitor;

	public void setCountElements(int countElements) 
	{
		this.countElements = countElements;
		label.setText("<html><body>N. Elementos: <b>"+countElements+"</b></body></html>");
	}

	public void saveConfiguration()
	{
		JFileChooser chooser = new JFileChooser();

		if(mode==1)
			chooser.setSelectedFile(new File("Carnet_Frontal.xml"));
		else
			chooser.setSelectedFile(new File("Carnet_Posterior.xml"));

		int t = chooser.showSaveDialog(getInstance());

		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);

		File fileSelected = null;

		if(t == JFileChooser.APPROVE_OPTION)
			fileSelected=chooser.getSelectedFile();
		else
			return;

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;

		try 
		{
			docBuilder = docFactory.newDocumentBuilder();

			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("carnet");
			doc.appendChild(rootElement);

			Element elemento1 = doc.createElement("image");
			rootElement.appendChild(elemento1);

			Attr nombre = doc.createAttribute("background");

			if(fileBackground!=null)
				nombre.setValue(""+fileBackground.toString());
			else
				nombre.setValue(""+null);

			elemento1.setAttributeNode(nombre);

			nombre = doc.createAttribute("background-scale");

			if(fileBackground!=null)
				nombre.setValue(""+imageView1.getScale());
			else
				nombre.setValue("null");

			elemento1.setAttributeNode(nombre);

			nombre = doc.createAttribute("background-point");

			if(fileBackground!=null)
				nombre.setValue(""+getLocationFondo());
			else
				nombre.setValue("null");

			elemento1.setAttributeNode(nombre);

			Enumeration<String> keys = hashtableElements.keys();

			while(keys.hasMoreElements())
			{
				String key = keys.nextElement();
				Object obj = hashtableElements.get(key);

				Element elemento = doc.createElement("elemento");
				rootElement.appendChild(elemento);

				nombre = doc.createAttribute("type");
				if(obj instanceof PanelTexto)
					nombre.setValue("texto");
				else if(obj instanceof PanelImage)
					nombre.setValue("imagen");
				else
					nombre.setValue("CodigoBarra");

				elemento.setAttributeNode(nombre);

				nombre = doc.createAttribute("id");
				nombre.setValue(key);
				elemento.setAttributeNode(nombre);

				Object c = hashtableElements.get(key);

				if(c instanceof PanelTexto)
				{
					PanelTexto panelTexto = (PanelTexto)c;

					Element locationx = doc.createElement("locationX");
					locationx.appendChild(doc.createTextNode("" + panelTexto.getLocation().x));
					elemento.appendChild(locationx);

					Element locationy = doc.createElement("locationY");
					locationy.appendChild(doc.createTextNode("" + panelTexto.getLocation().y));
					elemento.appendChild(locationy);

					Element va = doc.createElement("width");
					va.appendChild(doc.createTextNode("" + panelTexto.getWidth()));
					elemento.appendChild(va);

					va = doc.createElement("height");
					va.appendChild(doc.createTextNode("" + panelTexto.getHeight()));
					elemento.appendChild(va);

					va = doc.createElement("text");
					va.appendChild(doc.createTextNode("" + panelTexto.getTextFormat()));
					elemento.appendChild(va);

					va = doc.createElement("foreground");
					va.appendChild(doc.createTextNode("" + panelTexto.getForeground()));
					elemento.appendChild(va);

					va = doc.createElement("transparencia");
					va.appendChild(doc.createTextNode("" + panelTexto.getTransparencia()));
					elemento.appendChild(va);

					va = doc.createElement("font");
					va.appendChild(doc.createTextNode("" + panelTexto.getFont()));
					elemento.appendChild(va);
				}
				else if(c instanceof PanelImage)
				{
					PanelImage panelImage = (PanelImage) c;

					Element locationx = doc.createElement("locationX");
					locationx.appendChild(doc.createTextNode(""+panelImage.getLocation().x));
					elemento.appendChild(locationx);

					Element locationy = doc.createElement("locationY");
					locationy.appendChild(doc.createTextNode(""+panelImage.getLocation().y));
					elemento.appendChild(locationy);

					Element va = doc.createElement("width");
					va.appendChild(doc.createTextNode(""+panelImage.getWidth()));
					elemento.appendChild(va);

					va = doc.createElement("height");
					va.appendChild(doc.createTextNode(""+panelImage.getHeight()));
					elemento.appendChild(va);

					va = doc.createElement("imagen");

					File h = panelImage.getFileImageSelected();

					if(h != null && h.exists())
						va.appendChild(doc.createTextNode("$" + key + "{" + panelImage.getFileImageSelected().toString() + "}"));
					else
						va.appendChild(doc.createTextNode(""));

					elemento.appendChild(va);
				}
				else if(c instanceof PanelCodigoBarra )
				{
					PanelCodigoBarra panelImage = (PanelCodigoBarra)c;

					Element locationx = doc.createElement("locationX");
					locationx.appendChild(doc.createTextNode(""+panelImage.getLocation().x));
					elemento.appendChild(locationx);

					Element locationy = doc.createElement("locationY");
					locationy.appendChild(doc.createTextNode(""+panelImage.getLocation().y));
					elemento.appendChild(locationy);

					Element va = doc.createElement("width");
					va.appendChild(doc.createTextNode(""+panelImage.getWidth()));
					elemento.appendChild(va);

					va = doc.createElement("height");
					va.appendChild(doc.createTextNode(""+panelImage.getHeight()));
					elemento.appendChild(va);

					va = doc.createElement("text");
					va.appendChild(doc.createTextNode(panelImage.getText()));

					elemento.appendChild(va);
				}
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(fileSelected.toString());

			transformer.transform(source, result);
			JOptionPane.showMessageDialog(getInstance(), "Se ha guardado la configuracion correctamente!","Exito",JOptionPane.INFORMATION_MESSAGE);
		} 
		catch (ParserConfigurationException e) 
		{
			JOptionPane.showMessageDialog(getInstance(), ""+e.getMessage(),""+e.getLocalizedMessage(),JOptionPane.ERROR_MESSAGE);
		} 
		catch (TransformerConfigurationException e) 
		{
			JOptionPane.showMessageDialog(getInstance(), ""+e.getMessage(),""+e.getLocalizedMessage(),JOptionPane.ERROR_MESSAGE);
		} 
		catch (TransformerException e) 
		{
			JOptionPane.showMessageDialog(getInstance(), ""+e.getMessage(),""+e.getLocalizedMessage(),JOptionPane.ERROR_MESSAGE);
		}
	}

	public void clearCarnet()
	{
		try
		{
			imageView1.setImage(new File("."));
			Enumeration<String> keys = hashtableElements.keys();

			while(keys.hasMoreElements())
			{
				String k = keys.nextElement();
				Object x = hashtableElements.get(k);

				if(x instanceof PanelTexto)
				{
					PanelTexto zx = (PanelTexto)x;
					imageView1.remove(zx);
				}
				else
				{
					PanelImage az = (PanelImage) x;
					imageView1.remove(az);
				}

				removeElement(null, k);
			}
			imageView1.repaint();
			imageView1.updateUI();
		}
		catch(Exception e){ }
	}

	public void loadConfiguration(final File selected) throws ParserConfigurationException, SAXException, IOException
	{
		clearCarnet();
		System.out.println("loadConfiguration = " + selected);
		progressMonitor = new ProgressMonitor(getInstance(), "Cargando Configuracion...", "", 0, 10);

		new Thread(
				new Runnable() 
				{
					@Override
					public void run() 
					{
						progressMonitor.setProgress(1);

						try 
						{
							File fXmlFile = selected;
							DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
							DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

							Document doc = null;

							doc = dBuilder.parse(fXmlFile);

							doc.getDocumentElement().normalize();

							int max = 0;
							{
								NodeList nList1 = doc.getElementsByTagName("elemento");
								NodeList nList2 = doc.getElementsByTagName("image");
								max += nList1.getLength();
								max += nList2.getLength();
							}

							progressMonitor.setMaximum(max);

							int value = 0;
							{
								NodeList nList = doc.getElementsByTagName("image");

								for (int temp = 0; temp < nList.getLength(); temp++) 
								{
									value++;
									progressMonitor.setProgress(value);
									Node nNode = nList.item(temp);
									Element eElement = (Element) nNode;

									String bac=eElement.getAttribute("background");
									String sca=eElement.getAttribute("background-scale");
									String point =eElement.getAttribute("background-point");

									String g="0";

									float xc =0;
									float yc =0;

									try
									{
										g = point.substring(point.indexOf("[") + 1, point.lastIndexOf("]"));
										xc = Float.parseFloat(g.split(",")[0]);
										yc = Float.parseFloat(g.split(",")[1].trim());
									}
									catch(Exception e){}

									Point2D x = new Point2D.Double(xc,yc);
									File file = new File(bac);

									if(file.exists())
										imageView1.setImage(new File(bac));

									if(!(sca.indexOf("null") != -1) && sca != null && sca.length() > 1)
									{
										imageView1.setImageLocation(x);
										imageView1.setScale(Double.parseDouble(sca));
									}
									fileBackground = new File(bac);
								}
							}

							NodeList nList = doc.getElementsByTagName("elemento");

							for (int temp = 0; temp < nList.getLength(); temp++) 
							{
								value++;
								progressMonitor.setProgress(value);
								Node nNode = nList.item(temp);
								Element eElement = (Element) nNode;

								String identificador = eElement.getAttribute("id");
								String type = eElement.getAttribute("type");

								if(type.equalsIgnoreCase("texto"))
								{
									String locationX = eElement.getElementsByTagName("locationX").item(0).getTextContent().trim(); 	
									String locationY = eElement.getElementsByTagName("locationY").item(0).getTextContent().trim(); 	
									String width = eElement.getElementsByTagName("width").item(0).getTextContent().trim(); 
									String height = eElement.getElementsByTagName("height").item(0).getTextContent().trim(); 
									String texto = "texto";

									try
									{
										texto=eElement.getElementsByTagName("text").item(0).getTextContent().trim(); 
									}
									catch(Exception e){ }

									String sub = texto.substring(texto.indexOf("{") + 1, texto.lastIndexOf("}"));
									texto = sub;

									String foreground = "javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]";

									try
									{
										foreground = eElement.getElementsByTagName("foreground").item(0).getTextContent().trim(); 
									}
									catch(Exception e){ }

									String tranaparencia = "0.4";

									try
									{
										tranaparencia = eElement.getElementsByTagName("transparencia").item(0).getTextContent().trim();
									}
									catch(Exception e)
									{
										e.printStackTrace();
									}

									String font = "java.awt.Font[family=Dialog,name=ärial,style=bold,size=11]";

									try
									{
										font = eElement.getElementsByTagName("font").item(0).getTextContent().trim();
									}
									catch(Exception e){ }

									PanelTexto panelTexto=new PanelTexto(new JPanel(),imageView1,"Texto",getInstance(),identificador);

									tree.addIconItem(identificador, new ImageIcon(getClass().getResource("/resource/iconTexto.png")));
									expandAllNodes(tree, 0, root.getChildCount());
									setCountElements(countElements+1);


									hashtableElements.put(identificador, panelTexto);

									panelTexto.setBounds(Integer.parseInt(locationX), Integer.parseInt(locationY),Integer.parseInt(width),Integer.parseInt(height));
									panelTexto.setFormatText(texto);

									String rgb=foreground.substring(foreground.indexOf("[")+1,foreground.length()-1);
									String sx[]=rgb.split(",");

									int r = Integer.parseInt(sx[0].split("=")[1]);
									int g = Integer.parseInt(sx[1].split("=")[1]);
									int b = Integer.parseInt(sx[2].split("=")[1]);

									panelTexto.setForeground(new Color(r,g,b));
									panelTexto.setTransparencia(Float.parseFloat(tranaparencia));
									{
										String g1 = font;
										String m = g1.substring(g1.indexOf("["), g1.length());
										String h[] = m.split(",");
										String STY = h[2].split("=")[1];
										int l = 0;
										if(STY.equalsIgnoreCase("plain"))
											l = Font.PLAIN;
										else if(STY.equalsIgnoreCase("bold"))
											l = Font.BOLD;
										else if(STY.equalsIgnoreCase("italic"))
											l = Font.ITALIC;

										panelTexto.setFont(new Font(h[1].split("=")[1], l, Integer.parseInt(h[3].split("=")[1].replace("]", ""))));
									}

									String text = "<HTML><BODY>" + texto + "</BODY></HTML>";
									panelTexto.setText(text);
									imageView1.add(panelTexto);

									imageView1.repaint();
								}
								else if(type.equalsIgnoreCase("Imagen"))
								{
									String locationX = eElement.getElementsByTagName("locationX").item(0).getTextContent().trim(); 	
									String locationY = eElement.getElementsByTagName("locationY").item(0).getTextContent().trim(); 	
									String width = eElement.getElementsByTagName("width").item(0).getTextContent().trim(); 
									String height = eElement.getElementsByTagName("height").item(0).getTextContent().trim(); 
									String imagen = eElement.getElementsByTagName("imagen").item(0).getTextContent().trim(); 

									try
									{
										imagen = imagen.substring(imagen.indexOf("{") + 1, imagen.lastIndexOf("}"));
									}
									catch(Exception e){ }

									tree.addItem(identificador);
									expandAllNodes(tree, 0, root.getChildCount());
									setCountElements(countElements + 1);

									JXImageView view = new JXImageView();
									panelImage = new PanelImage(view, imageView1, "Imagen", getInstance(), identificador);
									panelImage.setBounds(Integer.parseInt(locationX), Integer.parseInt(locationY), Integer.parseInt(width), Integer.parseInt(height));
									panelImage.setImagen(new File(imagen));
									panelImage.repaint();
									imageView1.add(panelImage);
									imageView1.repaint();
									hashtableElements.put(identificador, panelImage);
								}
								else
								{
									String locationX = eElement.getElementsByTagName("locationX").item(0).getTextContent().trim(); 	
									String locationY = eElement.getElementsByTagName("locationY").item(0).getTextContent().trim(); 	
									String width = eElement.getElementsByTagName("width").item(0).getTextContent().trim(); 
									String height = eElement.getElementsByTagName("height").item(0).getTextContent().trim(); 
									String texto = eElement.getElementsByTagName("text").item(0).getTextContent().trim(); 

									try
									{
										texto = texto.substring(texto.indexOf("{") + 1, texto.lastIndexOf("}"));
									}
									catch(Exception e)
									{
										e.printStackTrace();
									}

									tree.addItem(identificador);
									expandAllNodes(tree, 0, root.getChildCount());
									setCountElements(countElements+1);
									JXImageView view = new JXImageView();
									PanelCodigoBarra panelCodigoBarra = new PanelCodigoBarra(view, imageView1, "CodigoBarra", getInstance(), identificador, texto);
									panelCodigoBarra.setBounds(Integer.parseInt(locationX), Integer.parseInt(locationY), Integer.parseInt(width), Integer.parseInt(height));
									imageView1.add(panelCodigoBarra);
									imageView1.repaint();
									hashtableElements.put(identificador, panelCodigoBarra);
								}
							}

							progressMonitor.setNote("Cargado");
							pl.updateUI();
							jScrollPane.updateUI();
							Thread.sleep(1000);
							ready = true;
							progressMonitor.close();
						} 
						catch (Exception e) 
						{
							e.printStackTrace();
						}
					}
				}).start();
	}

	public boolean isReady() 
	{
		return ready;
	}

	public void setReady(boolean ready) 
	{
		this.ready = ready;
	}

	private boolean ready=false;
	private int mode=1;
	private JButton buttonImprimir;
	private JPanel pl;
	private JScrollPane jScrollPane;

	public Point2D getLocationFondo()
	{
		return imageView1.getImageLocation();
	}

	public void setLocationFondo(Point2D imageLocation)
	{
		imageView1.setImageLocation(imageLocation);
	}

	private ComedorGUI principal;

	@SuppressWarnings("deprecation")
	public Carnet(int op, ComedorGUI gui)
	{
		this.mode = op;
		this.principal = gui;

		if(mode == 1)
			setBorder(BorderFactory.createTitledBorder("Carnet Frontal"));
		else
			setBorder(BorderFactory.createTitledBorder("Carnet Posterior"));

		imageView1 = new JXImageView();
		imageView1.setLayout(null);
		imageView1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		jSplitPane.setDividerLocation(1240);
		JPanel panel1 = new JPanel(new BorderLayout());

		JPanel panelControl1 = new JPanel(new GridLayout(1,9));
		JPanel panelControl11 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelControl1.setBorder(BorderFactory.createTitledBorder(""));

		button1 = new JButton("Zoom +1");
		button2 = new JButton("Zoom -1");
		button1.addActionListener(imageView1.getZoomInAction());
		button2.addActionListener(imageView1.getZoomOutAction());
		button1.setIcon(new ImageIcon(getClass().getResource("/resource/zoom+.png")));
		button2.setIcon(new ImageIcon(getClass().getResource("/resource/zoom-.png")));

		button11 = new JButton("Cargar");
		button11.setVisible(false);
		button11.addActionListener(
				new ActionListener() 
				{
					public void actionPerformed(ActionEvent arg0) 
					{
						try 
						{
							JFileChooser chooser = new JFileChooser();
							FileNameExtensionFilter filter = new FileNameExtensionFilter("archivo xml", "xml", "xml");
							chooser.setFileFilter(filter);
							
							int t = chooser.showOpenDialog(getInstance());

							if(t == JFileChooser.APPROVE_OPTION)
							{	
								File selected= chooser.getSelectedFile();
								loadConfiguration(selected);
							}
						} 
						catch (ParserConfigurationException e) 
						{
							e.printStackTrace();
						} 
						catch (SAXException e) 
						{
							e.printStackTrace();
						}
						catch (IOException e) 
						{
							e.printStackTrace();
						}
					}
				});

		button11.setIcon(new ImageIcon(getClass().getResource("/resource/iconLoad.png")));
		button12 = new JButton("Fondo");

		button12.addActionListener(
				new ActionListener() 
				{

					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						JFileChooser chooser = new JFileChooser();
						int y = chooser.showOpenDialog(null);

						if(y == JFileChooser.APPROVE_OPTION)
						{
							File file = chooser.getSelectedFile();
							fileBackground = file;

							try
							{
								Image image = new ImageIcon(file.toString()).getImage();
								imageView1.setImage(image);

								imageView1.repaint();
								Point2D x = new Point2D.Double(100,100);
								imageView1.setImageLocation(x);
								System.out.println(imageView1.getImageLocation());
							}
							catch (Exception e) 
							{
								e.printStackTrace();
							}
						}
					}
				});

		button16 = new JButton("Guardar");
		button16.setVisible(false);
		button16.addActionListener(
				new ActionListener() 
				{
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						saveConfiguration();
					}
				});

		button16.setIcon(new ImageIcon(getClass().getResource("/resource/save.png")));

		button11.setPreferredSize(new Dimension(130,24));
		button12.setPreferredSize(new Dimension(130,24));
		button16.setPreferredSize(new Dimension(130,24));

		panelControl1.add(button1);
		panelControl1.add(button2);
		
		JSlider slider_1 = new JSlider(1,100);
		slider_1.setValue(1);
		slider_1.setMinorTickSpacing(1);
		slider_1.setPaintTicks(true);
		slider_1.setPaintLabels(true);

		slider_1.addChangeListener(
				new ChangeListener() 
				{
					@Override
					public void stateChanged(ChangeEvent e) 
					{
						int value = ((JSlider)e.getSource()).getValue();
						double b = 0.1;
						imageView1.setScale(b * value);
						imageView1.repaint();
					}
				});

		panelControl1.add(slider_1);
		panelControl11.add(button11);
		panelControl11.add(button12);
		panelControl11.add(button16);

		panel1.add(panelControl1, BorderLayout.SOUTH);
		panel1.add(panelControl11, BorderLayout.NORTH);

		root = new DefaultMutableTreeNode("Carnet");
		tree = new JKTree(false, root);
		jScrollPane = new JScrollPane(tree);
		jScrollPane.setPreferredSize(new Dimension(130,0));

		JPanel panel = new JPanel(new BorderLayout());
		pl = new JPanel(new BorderLayout());
		pl.add(jScrollPane,BorderLayout.CENTER);
		panel.add(pl, BorderLayout.CENTER);

		JPanel panel3 = new JPanel(new GridLayout(4,5));
		panel3.setBorder(BorderFactory.createTitledBorder("Elementos"));
		JButton button1 = new JButton("Texto");
		button1.setIcon(new ImageIcon(getClass().getResource("/resource/iconTexto.png")));

		button1.addActionListener(
				new ActionListener() 
				{
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						boolean b = true;
						while(b)
						{
							String identificador = (String)JOptionPane.showInputDialog(getInstance(), "Ingrese un Identificador:", "Nombre de Variable", JOptionPane.INFORMATION_MESSAGE);

							if(identificador != null)
							{
								if(identificador.length() <= 1) 
								{
									JOptionPane.showMessageDialog(getInstance(), "Identificador no Valido!", "No Valido!", JOptionPane.WARNING_MESSAGE);
									continue;
								}

								if(hashtableElements.containsKey(identificador))
								{
									JOptionPane.showMessageDialog(getInstance(), "Ya hay un elemento con este Nombre!", "Ya Agregado!", JOptionPane.WARNING_MESSAGE);
									continue;
								}

								tree.addIconItem(identificador, new ImageIcon(getClass().getResource("/resource/iconTexto.png")));
								expandAllNodes(tree, 0, root.getChildCount());
								setCountElements(countElements+1);
								PanelTexto panelTexto=new PanelTexto(new JPanel(), imageView1, "Texto", getInstance(),identificador);
								hashtableElements.put(identificador, panelTexto);
								imageView1.add(panelTexto);
								imageView1.repaint();
								break;
							}
							else
								b = false;
						}
					}
				});

		JButton button2 = new JButton("Imagen");
		button2.addActionListener(
				new ActionListener() 
				{
					@Override
					public void actionPerformed(
							ActionEvent e) 
					{
						// TODO Auto-generated method stub
						boolean b = true;
						while(b)
						{
							String identificador = (String)JOptionPane.showInputDialog(getInstance(), "Ingrese un Identificador:","Nombre de Variable",JOptionPane.INFORMATION_MESSAGE);
							if(identificador!=null){
								if(identificador.length()<=1){
									JOptionPane.showMessageDialog(getInstance(), "Identificador no Valido!","No Valido!",JOptionPane.WARNING_MESSAGE);
									continue;
								}
								if(identificador.length()>=9){
									JOptionPane.showMessageDialog(getInstance(), "Identificador demasiado largo! maximo 8 Caracteres.","No Valido!",JOptionPane.WARNING_MESSAGE);
									continue;
								}
								if(hashtableElements.containsKey(identificador)){
									JOptionPane.showMessageDialog(getInstance(), "Ya hay un elemento con este Nombre!","Ya Agregado!",JOptionPane.WARNING_MESSAGE);
									continue;
								}
								tree.addItem(identificador);
								expandAllNodes(tree, 0, root.getChildCount());
								setCountElements(countElements+1);
								JXImageView view=new JXImageView();
								panelImage = new PanelImage(view,imageView1,"Imagen",getInstance(),identificador);
								imageView1.add(panelImage);
								imageView1.repaint();
								hashtableElements.put(identificador, panelImage);

								break;
							}else{
								b=false;
							}
						}
					}
				});
		JButton button3 = new JButton("Cod. Barra");
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean b = true;
				while(b){

					String identificador=(String)JOptionPane.showInputDialog(getInstance(), "Ingrese un Identificador:","Nombre de Variable",JOptionPane.INFORMATION_MESSAGE);
					if(identificador!=null){
						if(identificador.length()<=1){
							JOptionPane.showMessageDialog(getInstance(), "Identificador no Valido!","No Valido!",JOptionPane.WARNING_MESSAGE);
							continue;
						}
						if(identificador.length()==9){
							JOptionPane.showMessageDialog(getInstance(), "Identificador demasiado largo! maximo 8 Caracteres.","No Valido!",JOptionPane.WARNING_MESSAGE);
							continue;
						}
						if(hashtableElements.containsKey(identificador)){
							JOptionPane.showMessageDialog(getInstance(), "Ya hay un elemento con este Nombre!","Ya Agregado!",JOptionPane.WARNING_MESSAGE);
							continue;
						}
						tree.addIconItem(identificador, new ImageIcon(getClass().getResource("/resource/iconTexto.png")));
						expandAllNodes(tree, 0, root.getChildCount());
						setCountElements(countElements+1);

						JXImageView view=new JXImageView();
						PanelCodigoBarra panelImage = new PanelCodigoBarra(view,imageView1,"Codigo Barra",getInstance(),identificador,"01010101");

						hashtableElements.put(identificador, panelImage);
						imageView1.add(panelImage);
						imageView1.repaint();
						break;
					}else{
						b=false;
					}
				}
			}
		});
		button3.setIcon(new ImageIcon(getClass().getResource("/resource/iconBarra.png")));
		JButton button5 = new JButton("Capturar");
		button5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				BufferedImage c = getScreenShot(imageView1);
				JFileChooser chooser=new JFileChooser();
				if(mode==2){
					chooser.setSelectedFile(new File("screenshot_posterior.png"));
				}else{
					chooser.setSelectedFile(new File("screenshot_frontal.png"));
				}
				int x = chooser.showSaveDialog(getInstance());
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);

				if(x == JFileChooser.APPROVE_OPTION){
					try {
						// write the image as a PNG
						ImageIO.write(c,"png",chooser.getSelectedFile());
						Desktop.getDesktop().open(chooser.getSelectedFile());
					} catch(Exception ex) {
						ex.printStackTrace();
					}	
				}
			}
		});
		button5.setIcon(new ImageIcon(getClass().getResource("/resource/A1-screenCapture.png")));
		panel3.add(button1);
		panel3.add(button2);
		panel3.add(button3);

		panel3.setPreferredSize(new Dimension(0,147));

		buttonImprimir = new JButton("Imprimir");
		buttonImprimir.setIcon(new ImageIcon(getClass().getResource("/resource/Pdf-icon1.png")));
		buttonImprimir.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				BufferedImage c = getScreenShot(imageView1);

				if(mode == 2)
					new PdfCarnet("carnet_posterior.pdf", c, MODE, principal);
				else
					new PdfCarnet(path, c, MODE, principal);
			}
		});

		panel.add(panel3,BorderLayout.NORTH);

		JPanel panel21=new JPanel(new GridLayout(2,1));

		panel21.add(button5);
		panel21.add(buttonImprimir);


		label=new JLabel("<html><body>N. Elementos: <b>"+countElements+"</b></body></html>");
		pl.add(label,BorderLayout.SOUTH);
		label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(panel21,BorderLayout.SOUTH);
		if(op==2){
			panel1.add(panel,BorderLayout.EAST);
		}else
			panel1.add(panel,BorderLayout.WEST);

		panel1.add(imageView1, BorderLayout.CENTER);

		tree.setFont(new java.awt.Font("tahoma",Font.BOLD,11));
		setLayout(new GridLayout());
		add(panel1);
		tree.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				try{
					String identificador=tree.getSelectionPath().getLastPathComponent().toString();
					Object o = hashtableElements.get(identificador);
					if(o instanceof PanelTexto){
						PanelTexto panelTexto=(PanelTexto)o;
						panelTexto.selected();
					}else{
						PanelImage panelImage=(PanelImage)o;
						panelImage.selected();
					}
				}catch(Exception e){}
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
			@Override
			public void mouseClicked(MouseEvent arg0) {

			}
		});
	}

	public BufferedImage getScreenShot( Component component) 
	{
		BufferedImage image = new BufferedImage(component.getWidth(), component.getHeight(), BufferedImage.TYPE_INT_RGB);
		component.paint( image.getGraphics());
		return image;
	}

	private void expandAllNodes(JKTree tree, int startingIndex, int rowCount)
	{
		for(int i=startingIndex;i<rowCount;++i)
			tree.expandRow(i);

		if(tree.getRowCount()!=rowCount)
			expandAllNodes(tree, rowCount, tree.getRowCount());
	}

	public void imprimir()
	{
		buttonImprimir.doClick();
	}

	public void removeElement(JComponent component, String identificador) 
	{
		hashtableElements.remove(identificador);
		setCountElements(hashtableElements.size());
		tree.removeItem(identificador);
	}

	private int MODE = 0;

	public void setModo(int i) 
	{
		MODE=i;
	}

	private String path = "";

	public void setPathCarnet(String string) 
	{
		this.path = string;
	}
}
