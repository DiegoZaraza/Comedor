package comedor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import jcMousePanel.jcMousePanel;

import org.jdesktop.swingx.JXTaskPane;

import com.github.sarxos.webcam.Webcam;
import com.leyer.JKComboBox;
import com.leyer.JKCoreBar;
import com.leyer.JKMenu;
import com.leyer.JKMenuBar;
import com.leyer.JKStatusBar;
import com.leyer.JKTable;

import comedor.bd.BD;
import comedor.bd.BackupBD;
import comedor.pdfs.Persona;

public class ComedorGUI extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JKMenuBar bar;
	private JMenu menu;
	private JMenu menu2;
	private JDesktopPane desktop;
	private JMenuItem item;
	private jcMousePanel jcMousePanel1;
	private jcMousePanel jcMousePanel11;
	private RegistrarAlumno a;
	private jcMousePanel jcMousePanel111;
	private JMenu menu3;	
	private JMenuItem menuItemUpdate;
	private JMenuItem menuItemBackup;
	private JMenuItem menuItemExit;
	private JLabel menuAbout;
	private jcMousePanel jcMousePanel1111;
	private JMenuItem jMenuItemList;
	private JToggleButton btnScanner;
	private jcMousePanel jcMousePanel11112;
	private BD bd;
	private int y;
	private int x;
	private JLabel labelDatosCentro;
	private JKComboBox comboBox;
	private JKStatusBar bar1;
	private JMenuItem iteml;
	private jcMousePanel jcMousePanel111121;
	private JTextField fieldCode;
	private String nroAlumnos;
	private JLabel nAlumnos;
	private boolean escontrado = false;
	private JTextField fieldNombre;
	private ArrayList<String> arrayListGroups;
	private JLabel labelAsistencia;
	private JLabel label1Provistos;
	private JKTable tableBusqueda;
	private JMenu menuz;
	private JLabel labelDenominacion;
	private JKTable table2;
	private JKTable tableBusqueda2;
	private MenuPrincipalComedor menuPrincipalComedor;
	
	public ComedorGUI getInstance()
	{
		return this;
	}
	
	public synchronized boolean isEscontrado() 
	{
		return escontrado;
	}
	
	public synchronized void setEscontrado(boolean escontrado) 
	{
		this.escontrado = escontrado;
	}
	
	public void updateNroAlumnos(String n)
	{
		nAlumnos.setText("<html> <body> <center> Nro. de Alumnos: <font color='blue'> <b>" + n + "</b> </font> </center> </body> </html>");
	}
	
	public BD getBaseDeDatos()
	{
		return bd;
	}
	
	public void updateDatosCentro()
	{
		getBaseDeDatos().getDatosCentro(labelDatosCentro);
		labelDenominacion.setText("" + getBaseDeDatos().getDenominacion());
		desktop.updateUI();
	}
	
	public void updateGroups()
	{
		try
		{
			arrayListGroups = new ArrayList<>();
			comboBox.removeAllItems();
			getBaseDeDatos().getGroups(comboBox,arrayListGroups);
		}
		catch(Exception e){ }
	}
	
	public JDesktopPane getDesktop()
	{
		return desktop;
	}
	
	public ComedorGUI() 
	{
		item = new JMenuItem("Importar datos de ITACA");
		KeyStroke ctrlP11 = KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK);
		item.setAccelerator(ctrlP11);
		
		item .setIcon(new ImageIcon(getClass().getResource("/resource/A1-restart.png")));
		
		item.addActionListener(
				new ActionListener() 
				{
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						ImporDataITACA dataITACA = new ImporDataITACA(getInstance());
						desktop.add(dataITACA);
						dataITACA.setVisible(true);
					}
				});
		
		addComponentListener(
				new ComponentListener() 
				{
					@Override
					public void componentShown(ComponentEvent arg0) { }
					
					@Override
					public void componentResized(ComponentEvent arg0) 
					{
						jcMousePanel11.setBounds(10, getHeight()-130, 160, 30);
						btnScanner.setBounds(10, getHeight()-260, 210, 80);
						jcMousePanel11112.setBounds(395, getHeight()-130, 340, 30);
						jcMousePanel111.setBounds(180, getHeight()-130, 210, 30);
						jcMousePanel1111.setBounds(10, getHeight()-170, 460, 35);
						jcMousePanel111121.setBounds(getWidth()-450, getHeight()-130, 200, 30);
						jcMousePanel1111xxxx.setBounds(240, getHeight()-260, 400, 80);
						labelDenominacion.setBounds(16, getHeight()-330, 400, 80);
					}
					
					@Override
					public void componentMoved(ComponentEvent arg0) { }
					
					@Override
					public void componentHidden(ComponentEvent arg0) { }
				});
		
		menu2 = new JMenu("ITACA");
		menu2.setFont(new Font("tahoma", Font.BOLD,12));
		menu2.setIcon(new ImageIcon(getClass().getResource("/resource/a8.png")));
		menu2.add(item);
		menu = new JMenu("Archivo");
		menu.setFont(new Font("tahoma", Font.BOLD,12));
		menuAbout = new JLabel("Acerca de..");
		menuAbout.setForeground(Color.WHITE);
		menuAbout.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		menuAbout.addMouseListener(
				new MouseListener() 
				{
					@Override
					public void mouseReleased(MouseEvent arg0) 
					{
						new About(getInstance()).setVisible(true);
					}
					
					@Override
					public void mousePressed(MouseEvent arg0) { }
					
					@Override
					public void mouseExited(MouseEvent arg0) { }
					
					@Override
					public void mouseEntered(MouseEvent arg0) { }
					
					@Override
					public void mouseClicked(MouseEvent arg0) { }
				});
		
		menuAbout.setFont(new Font("tahoma", Font.BOLD,12));
		menuAbout.setIcon(new ImageIcon(getClass().getResource("/resource/a8.png")));
		menu.setIcon(new ImageIcon(getClass().getResource("/resource/a8.png")));
		menu3 = new JMenu("Base de Datos");
		iteml =new JMenuItem("Ajustes de Conexion");
		
		KeyStroke ctrlP1 = KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK);
		iteml.setAccelerator(ctrlP1);
		
		iteml.setIcon(new ImageIcon(getClass().getResource("/resource/config-icon.png")));
		iteml.addActionListener(
				new ActionListener() 
				{
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						new DialogoConexion(null, getInstance());
					}
				});
		
		menu3.add(iteml);
		menu3.add(new JSeparator());
		menu3.setFont(new Font("tahoma", Font.BOLD,12));
		menu3.setIcon(new ImageIcon(getClass().getResource("/resource/a8.png")));
		setTitle("Software de Administracion y Gestion Comedor v0.1");
		bar = new JKMenuBar(200, 300, Color.WHITE);
		setJMenuBar(bar);
		menuItemExit = new JMenuItem("Salir");
		
		menuItemExit.addActionListener(
				new ActionListener() 
				{
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						System.exit(0);
					}
				});
		
		menuItemExit .setIcon(new ImageIcon(getClass().getResource("/resource/A1-stop.png")));
		
		bar.add(menu);
		JMenuItem itemFotos=new JMenuItem("Importar Fotos");
		JMenuItem itemExFotos=new JMenuItem("Exportar Fotos");
		
		itemExFotos.addActionListener(
				new ActionListener() 
				{
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						new DialogoExportFotos(getInstance()).setVisible(true);
					}
				});
		
		KeyStroke ctrlP111 = KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK);
		itemFotos.setAccelerator(ctrlP111);
		
		KeyStroke ctrlP1111 = KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK);
		itemExFotos.setAccelerator(ctrlP1111);
		
		setIconImage(new ImageIcon(ImporDataITACA.class.getResource("/resource/software.png")).getImage());
		itemExFotos.setIcon(new ImageIcon(getClass().getResource("/resource/export.png")));
		itemFotos.setIcon(new ImageIcon(getClass().getResource("/resource/import.png")));
		menu.add(itemFotos);
		menu.add(new JSeparator());
		menu.add(itemExFotos);
		menu.add(new JSeparator());
		JMenuItem item=new JMenuItem("Configurar Email");
		
		item.addActionListener(
				new ActionListener() 
				{
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						ConfigurarEmail configurarEmail= new ConfigurarEmail(getInstance());
						desktop.add(configurarEmail);
						configurarEmail.setVisible(true);
					}
				});
		
		item.setVisible(false);
		menu.add(item);
		menu.add(new JSeparator());
		menu.add(menuItemExit);
		
		itemFotos.addActionListener(
				new ActionListener() 
				{
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						JFileChooser chooser = new JFileChooser();
						chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
						int y = chooser.showOpenDialog(getInstance());
						
						if(y == JFileChooser.APPROVE_OPTION)
						{
							final File dir = chooser.getSelectedFile();
							final JDialog dialog = new JDialog(getInstance(),true);
							dialog.setSize(400, 400);
							dialog.setLocationRelativeTo(getInstance());
							final JTextArea area = new JTextArea();
							JScrollPane jScrollPane = new JScrollPane(area);
							dialog.getContentPane().setLayout(new BorderLayout());
							area.setEditable(false);
							dialog.setTitle("Cargar Fotos");
							dialog.getContentPane().add(jScrollPane, BorderLayout.CENTER);
							JButton button = new JButton("Cerrar");
							final JProgressBar progressBar = new JProgressBar();
							progressBar.setIndeterminate(true);
							progressBar.setStringPainted(true);
							progressBar.setString("Procesando...");
							button.setFont(new Font("arial", Font.BOLD, 11));
							
							button.addActionListener(new ActionListener() 
							{
								@Override
								public void actionPerformed(ActionEvent arg0) 
								{
									dialog.dispose();
								}
							});
							
							dialog.getContentPane().add(progressBar,BorderLayout.NORTH);
							dialog.setFont(new Font("arial", Font.BOLD, 11));
							dialog.getContentPane().add(button, BorderLayout.SOUTH);
							
							new Thread(
									new Runnable() 
									{	
										@Override
										public void run() 
										{
											getBaseDeDatos().insertFotos(dir.toString(), area,dialog,progressBar);	
										}
									}).start();
							dialog.setVisible(true);
						}
					}
				});
		
		menuItemUpdate = new JMenuItem("Actualizar Datos en la Nube");
		menuItemUpdate.setIcon(new ImageIcon(getClass().getResource("/resource/A1-connections.png")));
		menuItemBackup = new JMenuItem("Respaldar Base de Datos");
		menuItemBackup.addActionListener(
				new ActionListener() 
				{
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						new BackupBD().start();
					}
				});
		
		menuItemBackup.setIcon(new ImageIcon(getClass().getResource("/resource/A1-dword.png")));
		jMenuItemList = new JMenuItem("Respaldos Realizados");
		jMenuItemList.setVisible(false);
		jMenuItemList.setIcon(new ImageIcon(getClass().getResource("/resource/A1-folder.png")));
		
		desktop = new JDesktopPane()
		{
			private static final long serialVersionUID = 1L;
			
			@Override
			public void paintComponent(Graphics g) 
			{
				g.drawImage(new ImageIcon(getClass().getResource("/resource/e1.jpg")).getImage(), 0, 0, getWidth(), getHeight(), null);
			}
		};
		
		menu3.add(menuItemUpdate);
		menu3.add(new JSeparator());
		menu3.add(menuItemBackup);
		menu3.add(new JSeparator());
		menu3.add(jMenuItemList);
		bar.add(menu2);
		
		bar.add(menu3);
		
		menuz = new JMenu("Cursos");
		menuz.setFont(new Font("tahoma", Font.BOLD,12));
		menuz.setIcon(new ImageIcon(getClass().getResource("/resource/a8.png")));
		
		if(bd != null)
			getBaseDeDatos().getCursos(menuz);
		
		bar.add(menuz);
		
		mnBecas = new JLabel("Becas");
		mnBecas.setForeground(Color.WHITE);
		mnBecas.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		mnBecas.addMouseListener(
				new MouseListener() 
				{
					@Override
					public void mouseReleased(MouseEvent arg0) 
					{
						new AgregarBecas(getInstance()).setVisible(true);
					}
					
					@Override
					public void mousePressed(MouseEvent arg0) { }
					
					@Override
					public void mouseExited(MouseEvent arg0) { }
					
					@Override
					public void mouseEntered(MouseEvent arg0) { }
					
					@Override
					public void mouseClicked(MouseEvent arg0) { }
				});
		
		mnBecas.setFont(new Font("tahoma", Font.BOLD,12));
		mnBecas.setIcon(new ImageIcon(getClass().getResource("/resource/a8.png")));
		
		
		bar.add(mnBecas);
		//		bar.add(menuAbout);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		y = (int )Toolkit.getDefaultToolkit().getScreenSize().height;
		x = (int)Toolkit.getDefaultToolkit().getScreenSize().width;
		setSize(x, y - 90);
		
		contentPane = new JPanel();
		
		JXTaskPane taskPane = new JXTaskPane();
		taskPane.setTitle("Menu Principal");
		taskPane.setIcon(new ImageIcon(getClass().getResource("/resource/a23.png")));
		
		JPanel panelCenter = new JPanel(new BorderLayout());
		panelCenter.add( desktop,BorderLayout.CENTER);
		
		menuPrincipalComedor = new MenuPrincipalComedor(this);
		menuPrincipalComedor.setPreferredSize(new Dimension(230, 0));
		panelCenter.add(menuPrincipalComedor, BorderLayout.WEST);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);	
		desktop.setLayout(null);
		getContentPane().add(panelCenter, BorderLayout.CENTER);
		bar1 = new JKStatusBar(JKStatusBar.ON_LEFT, new Font("arial", Font.BOLD,12));
		String codigoCentro = "00000";
		String denominacion = "Sin Definir";
		String domicilio = "Sin Definir";
		String telefono = "Sin Definir";
		String fax = "Sin Definir";
		
		labelDenominacion = new JLabel("");
		desktop.add(labelDenominacion);
		labelDenominacion.setFont(new Font("arial", Font.BOLD | Font.HANGING_BASELINE, 30));
		labelDenominacion.setBounds(16, getHeight()-330, 500, 80);
		{
			jcMousePanel1 = new jcMousePanel();
			
			jcMousePanel1.setLayout(new FlowLayout(FlowLayout.LEFT));
			labelDatosCentro=new JLabel("<html>Codigo de Centro: <font color='white'> <b>" + codigoCentro + "</b> </font> <br>Denomincación:<b>" + denominacion 
					+ "</b> <BR> Domicilio: <b>" + domicilio + "</b> <br>Telefono: <b>" + telefono + "</b> <br>Fax:  <b>" + fax + "</b> </html>");
			jcMousePanel1.add(labelDatosCentro);
			
			if(bd != null && bd.isActive())
				getBaseDeDatos().getDatosCentro(labelDatosCentro);
			
			jcMousePanel1.setPreferredSize(new Dimension(420, 110));
			jcMousePanel1.setBorder(new TitledBorder(null, "Datos del Centro", TitledBorder.LEADING, TitledBorder.TOP, new Font("arial", Font.BOLD, 11), Color.black));
			jcMousePanel1.setModo(2);
			jcMousePanel1.setOpaque(true);
			jcMousePanel1.setVisible(true);
			
			desktop.add(jcMousePanel1);
		}
		JLabel jLabel4 = new JLabel("");
		{
			javax.swing.GroupLayout dpaneLayout = new javax.swing.GroupLayout(desktop);
			
			desktop.setLayout(dpaneLayout);
			dpaneLayout
			.setHorizontalGroup(dpaneLayout
					.createParallelGroup(
							javax.swing.GroupLayout.Alignment.LEADING)
							.addGroup(
									dpaneLayout
									.createSequentialGroup()
									.addContainerGap(10, getWidth() - 200)
									.addGroup(
											dpaneLayout
											.createParallelGroup(
													javax.swing.GroupLayout.Alignment.LEADING)
													.addComponent(
															jcMousePanel1,
															javax.swing.GroupLayout.PREFERRED_SIZE,
															javax.swing.GroupLayout.DEFAULT_SIZE,
															javax.swing.GroupLayout.PREFERRED_SIZE)
															.addGroup(
																	dpaneLayout
																	.createSequentialGroup()
																	.addGap(137,
																			137,
																			137)
																			.addComponent(
																					jLabel4)))
																					.addContainerGap(12, 40)));
			dpaneLayout
			.setVerticalGroup(dpaneLayout
					.createParallelGroup(
							javax.swing.GroupLayout.Alignment.LEADING)
							.addGroup(
									dpaneLayout
									.createSequentialGroup()
									.addContainerGap(
											30,
											javax.swing.GroupLayout.DEFAULT_SIZE)
											.addComponent(
													jcMousePanel1,
													javax.swing.GroupLayout.PREFERRED_SIZE,
													javax.swing.GroupLayout.DEFAULT_SIZE,
													javax.swing.GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(
															javax.swing.LayoutStyle.ComponentPlacement.RELATED,
															15, 1000).addComponent(jLabel4)
															.addContainerGap()));
			
			desktop.setLayer(jcMousePanel1,javax.swing.JLayeredPane.PALETTE_LAYER);
		}
		desktop.add(jcMousePanel1);
		desktop.setLayer(jcMousePanel1,javax.swing.JLayeredPane.DEFAULT_LAYER);
		{
			{
				btnScanner = new JToggleButton();
				btnScanner.setCursor(new Cursor(Cursor.HAND_CURSOR));
				btnScanner.addActionListener(
						new ActionListener() 
						{
							@Override
							public void actionPerformed(ActionEvent arg0) 
							{
								if(btnScanner.isSelected())
								{
									btnScanner.setText("<html><body><h5><font color='white'>Lector</font></h5><font color='white'>Estado:</font> <b><font color='orange'><br>Activado!</font></b></body></html>");
									fieldCode.requestFocus();
								}
								else
									btnScanner.setText("<html><body><h5<font color='white'>Lector</font></h5><font color='white'>Estado:</font> <b><font color='red'><br>Desactivado</font></b></body></html>");
							}
						});
				
				btnScanner.setText("<html><body><h5><font color='white'>Lector</font></h5><font color='white'>Estado:</font><b><font color='red'><br>Desactivado</font></b></body></html>");
				btnScanner.setIcon(new ImageIcon(getClass().getResource("/resource/scanner.png")));
				
				desktop.add(btnScanner);
				btnScanner.setBounds(10, getHeight()-260,210, 80);
			}
		}
		{
			nroAlumnos = "0";
			{
				jcMousePanel11 = new jcMousePanel();
				jcMousePanel11.setLayout(new FlowLayout(FlowLayout.CENTER));
				nAlumnos=new JLabel("<html><body><center>Nro. de Alumnos: <font color='blue'><b>" +nroAlumnos+"</b> " 
						+ " </font></center></body> </html>");
				nAlumnos.setHorizontalAlignment(SwingConstants.CENTER);
				jcMousePanel11.add(nAlumnos);
				jcMousePanel11.setPreferredSize(new Dimension(290, 80));
				jcMousePanel11.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, new Font("arial", Font.BOLD, 11), new Color(0, 0, 128)));
				jcMousePanel11.setModo(2);
				jcMousePanel11.setOpaque(true);
				jcMousePanel11.setVisible(true);
				
				desktop.add(jcMousePanel11);
			}
			jcMousePanel11.setLayout(new GridLayout());
			jcMousePanel11.setBounds(10, getHeight()-130,160, 30);
			jcMousePanel11.setVisible(true);
		}
		{
			{
				jcMousePanel111 = new jcMousePanel();
				jcMousePanel111.setLayout(new FlowLayout(FlowLayout.CENTER));
				fieldCode = new JTextField(10);
				fieldCode.addKeyListener(
						new KeyListener()
						{
							@Override
							public void keyTyped(KeyEvent arg0) { }
							
							@Override
							public void keyReleased(KeyEvent arg0) 
							{
								if(isEscontrado())
									setEscontrado(false);
								else
									if(!isEscontrado())
										new Thread(
												new Runnable()
												{
													@Override
													public synchronized void run() 
													{
														String g = "";
														
														try
														{
															g = getScanned();
														}
														catch (InterruptedException e1)
														{
															e1.printStackTrace();
														}
														
														boolean n = false;
														
														if(g.length() >= 9)
														{
															g = getBaseDeDatos().getNia(g);
															boolean n1 = getBaseDeDatos().verificarSiEsUsuarioComedor(g);
															
															if(n1)
															{
																synchronized (this) 
																{
																	setEscontrado(true);
																}
																
																String tipo = getBaseDeDatos().getTipoDeUsuario(g);
																
																if(tipo.equalsIgnoreCase("2"))
																{
																	if(dia.equalsIgnoreCase("jueves"))
																	{
																		int t = JOptionPane.showConfirmDialog(getInstance(), "Posee Ticked?", "Ticked Necesario", JOptionPane.INFORMATION_MESSAGE);
																		
																		if(t == JOptionPane.OK_OPTION)
																			getBaseDeDatos().addAsistencia(g,"Si");
																		else
																		{
																			JOptionPane.showMessageDialog(getInstance(), "No Autorizado!", "Denegado", JOptionPane.WARNING_MESSAGE);
																			return;
																		}
																	}
																}
																else
																{
																	boolean z = false;
																	
																	if(dia.equalsIgnoreCase("lunes") || dia.equalsIgnoreCase("martes"))
																		z = getBaseDeDatos().verificarSiEstaAutorizadoLunesMartes(g);
																	else if(dia.equalsIgnoreCase("jueves"))
																		z = getBaseDeDatos().verificarSiEstaAutorizadoJueves(g);
																	
																	if(!z)
																	{
																		JOptionPane.showMessageDialog(getInstance(), "No Autorizado!","Denegado",JOptionPane.WARNING_MESSAGE);
																		return;
																	}
																	getBaseDeDatos().addAsistencia(g,"No");
																}
															}
															else
															{
																boolean t1 = getBaseDeDatos().verificarFechas(g);
																
																if(t1)
																{
																	// Los alumnos no asiduos saltara ticket si el dia que entran 
																	// esta fuera de subperiodo alta bajo y si son de dos dias tb ssltaran los jueves
																	if(getBaseDeDatos().verificarUsuario(g))
																	{
																		int t = JOptionPane.showConfirmDialog(getInstance(), "Posee Ticked?", "Ticked Necesario", JOptionPane.INFORMATION_MESSAGE);
																		
																		if(t==JOptionPane.OK_OPTION)
																			getBaseDeDatos().addAsistencia(g,"Si");
																		else
																		{
																			JOptionPane.showMessageDialog(getInstance(), "No Autorizado!", "Denegado", JOptionPane.WARNING_MESSAGE);
																			return;
																		}
																	}
																}
																else
																{
																	if(getBaseDeDatos().verificarSiExiste(g))
																	{
																		if(getBaseDeDatos().verificarAsistencia(g))
																			return;
																		else
																		{
																			int t = JOptionPane.showConfirmDialog(getInstance(), "Posee Ticked?", "Ticked Necesario", JOptionPane.INFORMATION_MESSAGE);
																			
																			if(t == JOptionPane.OK_OPTION)
																				getBaseDeDatos().addAsistencia(g, "Si");
																			else
																			{
																				JOptionPane.showMessageDialog(getInstance(), "No Autorizado!","Denegado",JOptionPane.WARNING_MESSAGE);
																				return;
																			}
																		}
																	}
																	else
																	{
																		int x = JOptionPane.showConfirmDialog(getInstance(), "<html><body>No se encuentro Registrado.<br>Desea Registrar?</body></html>", "No Registrado", JOptionPane.WARNING_MESSAGE);
																		
																		if(x == JOptionPane.OK_OPTION)
																		{
																			if(a == null)
																				a = new RegistrarAlumno(getInstance(), getBaseDeDatos().getNia(g), true);
																			else 
																				return;
																			
																			desktop.add(a);
																			a.setVisible(true);
																			
																			synchronized (this) 
																			{
																				setEscontrado(true);
																			}
																			a = null;
																		}
																		else
																		{
																			return;
																		}
																	}
																}
																
																String alergias = getBaseDeDatos().verificarAlergias(g);
																
																if(!alergias.equals(""))
																	JOptionPane.showMessageDialog(getInstance(), "El Usuario tiene la siguiente restricción: " + alergias, "Advertencia", JOptionPane.WARNING_MESSAGE);
																
																updateAsistencias();
																fieldCode.setText("");
																
																if(a == null)
																	a = new RegistrarAlumno(getInstance(),getBaseDeDatos().getNia(g),true);
																else 
																	return;
																
																desktop.add(a);
																
																a.setEnabledNIA(false);
																a.loadDatos();
																
																n = true;
																
																synchronized (this) 
																{
																	setEscontrado(true);
																}
																
																//																a = null;
																fieldCode.requestFocus();
															}
															
															if(!n)
															{
																JOptionPane.showMessageDialog(getInstance(), "No encontrado","",JOptionPane.WARNING_MESSAGE);
																fieldCode.setText("");
																return;
															}
														}
														else if(g.length() == 8)
														{
															boolean b = getBaseDeDatos().verificarSiExiste(g);
															
															if(b)
															{
																synchronized (this) 
																{
																	setEscontrado(true);
																}
																fieldCode.setText("");
																
																boolean n1 = getBaseDeDatos().verificarSiEsUsuarioComedor(g);
																
																if(n1)
																{
																	String tipo = getBaseDeDatos().getTipoDeUsuario(g);
																	
																	if(tipo.equalsIgnoreCase("2"))
																	{
																		if(dia.equalsIgnoreCase("jueves"))
																		{
																			int t = JOptionPane.showConfirmDialog(getInstance(), "Posee Ticked?","Ticked Necesario",JOptionPane.INFORMATION_MESSAGE);
																			if(t == JOptionPane.OK_OPTION)
																				getBaseDeDatos().addAsistencia(g,"Si");
																			else
																			{
																				JOptionPane.showMessageDialog(getInstance(), "No Autorizado!","Denegado",JOptionPane.WARNING_MESSAGE);
																				return;
																			}
																		}
																	}
																	else
																	{
																		boolean z = false;
																		if(dia.equalsIgnoreCase("lunes") || dia.equalsIgnoreCase("martes"))
																			z = getBaseDeDatos().verificarSiEstaAutorizadoLunesMartes(g);
																		else if(dia.equalsIgnoreCase("jueves"))
																			z = getBaseDeDatos().verificarSiEstaAutorizadoJueves(g);
																		if(!z)
																		{
																			JOptionPane.showMessageDialog(getInstance(), "No Autorizado!","Denegado",JOptionPane.WARNING_MESSAGE);
																			return;
																		}
																		getBaseDeDatos().addAsistencia(g,"No");
																	}
																}
																else
																{
																	boolean t1 = getBaseDeDatos().verificarFechas(g);
																	if(t1)
																	{
																		if(getBaseDeDatos().verificarUsuario(g))
																		{
																			int t = JOptionPane.showConfirmDialog(getInstance(), "Posee Ticked?", "Ticked Necesario", JOptionPane.INFORMATION_MESSAGE);
																			
																			if(t == JOptionPane.OK_OPTION)
																				getBaseDeDatos().addAsistencia(g,"Si");
																			else
																			{
																				JOptionPane.showMessageDialog(getInstance(), "No Autorizado!", "Denegado", JOptionPane.WARNING_MESSAGE);
																				return;
																			}
																		}
																	}
																	else
																	{
																		if(getBaseDeDatos().verificarSiExiste(g))
																		{
																			/** Se agrega la función VerificchrarAsistencia para que un usuario no pase el mismo día varias veces**/
																			if(getBaseDeDatos().verificarAsistencia(g))
																			{
																				return;
																			}
																			else
																			{
																				int t = JOptionPane.showConfirmDialog(getInstance(), "Posee Ticked?", "Ticked Necesario", JOptionPane.INFORMATION_MESSAGE);
																				
																				if(t==JOptionPane.OK_OPTION)
																					getBaseDeDatos().addAsistencia(g,"Si");
																				else
																				{
																					JOptionPane.showMessageDialog(getInstance(), "No Autorizado!", "Denegado", JOptionPane.WARNING_MESSAGE);
																					return;
																				}
																			}
																		}
																		else
																		{
																			int x = JOptionPane.showConfirmDialog(getInstance(), "<html><body>No se encuentro Registrado.<br>Desea Registrar?</body></html>", "No Registrado", JOptionPane.WARNING_MESSAGE);
																			
																			if(x == JOptionPane.OK_OPTION)
																			{
																				if(a == null)
																					a = new RegistrarAlumno(getInstance(),getBaseDeDatos().getNia(g),true);
																				else 
																					return;
																				
																				desktop.add(a);
																				a.setVisible(true);
																				synchronized (this) 
																				{
																					setEscontrado(true);
																				}
																				a = null;
																			}
																			else
																			{
																				return;
																			}
																		}
																	}
																}
																
																String alergias = getBaseDeDatos().verificarAlergias(g);
																
																if(!alergias.equals(""))
																	JOptionPane.showMessageDialog(getInstance(), "El Usuario tiene la siguiente restricción: " + alergias, "Advertencia", JOptionPane.WARNING_MESSAGE);
																
																updateAsistencias();
																fieldCode.setText("");
																
																if(a == null)
																	a = new RegistrarAlumno(getInstance(),g,true);
																else 
																	return;
																
																desktop.add(a);
																
																a.setEnabledNIA(false);	
																a.loadDatos();
																n = true;
																
																try
																{
																	a.setVisible(true);
																}
																catch(Exception e) { }
																
																//																a = null;
																
																synchronized (this) 
																{
																	setEscontrado(true);
																}
																
																fieldCode.requestFocus();
															}
															
															if(!n)
															{
																JOptionPane.showMessageDialog(getInstance(), "No encontrado","",JOptionPane.WARNING_MESSAGE);
																fieldCode.setText("");
																return;
															}
														}
														else{ }
													}
												}).start();
							}
							@Override
							public void keyPressed(KeyEvent arg0) { }
						});
				
				JLabel jLabel = new JLabel("<html><body><b>NIA/Documento:</b></body></html>");
				jLabel.setHorizontalAlignment(SwingConstants.CENTER);
				jcMousePanel111.add(jLabel);
				jcMousePanel111.add(fieldCode);
				jcMousePanel111.setPreferredSize(new Dimension(290, 80));
				jcMousePanel111.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, new Font("arial", Font.BOLD, 11), new Color(0, 0, 128)));
				jcMousePanel111.setModo(2);
				jcMousePanel111.setOpaque(true);
				jcMousePanel111.setVisible(true);
				
				desktop.add(jcMousePanel111);
			}
			
			jcMousePanel111.setLayout(new GridLayout());
			jcMousePanel111.setBounds(180, getHeight()-130,210, 30);
			jcMousePanel111.setVisible(true);
		}
		{
			{
				jcMousePanel11112 = new jcMousePanel();
				jcMousePanel11112.setLayout(new FlowLayout(FlowLayout.CENTER));
				fieldNombre = new JTextField(10);
				tableBusqueda = new JKTable();
				
				JLabel label = new JLabel("Busqueda Por Nombre:");
				JKCoreBar coreBar = new JKCoreBar(JKCoreBar.MODE_1);
				JKMenu jkMenu = new JKMenu("Busqueda Por Nombre", JKCoreBar.MODE_1);
				
				label.setHorizontalAlignment(SwingConstants.CENTER);
				coreBar.add(jkMenu);
				JPanel jPanel = new JPanel(new BorderLayout());
				
				tableBusqueda.addColumn("Apellidos y Nombres");
				tableBusqueda.addColumn("NIA");
				
				JScrollPane jScrollPane=new JScrollPane(tableBusqueda);
				jPanel.add(fieldNombre,BorderLayout.NORTH);
				fieldNombre.addKeyListener(
						new KeyListener() 
						{
							@Override
							public void keyTyped(KeyEvent arg0) { }
							
							@Override
							public void keyReleased(KeyEvent arg0) 
							{
								tableBusqueda.search(fieldNombre.getText().toUpperCase());
							}
							
							@Override
							public void keyPressed(KeyEvent arg0) { }
						});
				
				jPanel.add(jScrollPane,BorderLayout.CENTER);
				jkMenu.add(jPanel);
				jPanel.setPreferredSize(new Dimension(300,200));
				jcMousePanel11112.add(coreBar);
				jcMousePanel11112.setPreferredSize(new Dimension(290, 80));
				jcMousePanel11112.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, new Font("arial", Font.BOLD, 11), new Color(0, 0, 128)));
				jcMousePanel11112.setModo(2);
				jcMousePanel11112.setOpaque(true);
				jcMousePanel11112.setVisible(true);
				
				desktop.add(jcMousePanel11112);
			}
			jcMousePanel11112.setLayout(new GridLayout());
			jcMousePanel11112.setBounds(395, getHeight()-130,340, 30);
			jcMousePanel11112.setVisible(true);
		}
		{
			{
				jcMousePanel111121 = new jcMousePanel();
				jcMousePanel111121.setLayout(new FlowLayout(FlowLayout.CENTER));
				JButton button=new JButton("Salir...");
				button.setIcon(new ImageIcon(ImporDataITACA.class.getResource("/resource/exit-hi.png")));
				
				button.addActionListener(
						new ActionListener() 
						{
							@Override
							public void actionPerformed(ActionEvent arg0) 
							{
								System.exit(0);
							}
						});
				jcMousePanel111121.add(button);
				jcMousePanel111121.setPreferredSize(new Dimension(290, 80));
				jcMousePanel111121.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, new Font("arial", Font.BOLD, 11), new Color(0, 0, 128)));
				jcMousePanel111121.setModo(2);
				jcMousePanel111121.setOpaque(true);
				jcMousePanel111121.setVisible(true);
				
				desktop.add(jcMousePanel111121);
			}
			
			jcMousePanel111121.setLayout(new GridLayout());
			jcMousePanel111121.setBounds(getWidth()-450, getHeight()-130, 200, 30);
			jcMousePanel111121.setVisible(true);
		}
		{
			jcMousePanel1111xxxx = new jcMousePanel();
			jcMousePanel1111xxxx.setLayout(new GridLayout(2,0));
			
			SimpleDateFormat dateFormat=new SimpleDateFormat("EEEEE");
			
			label1Provistos=new JLabel("  Provistos: " + nAutorizados);
			jcMousePanel1111xxxx.add(label1Provistos);
			
			labelAsistencia=new JLabel("  Asisten: " +nAsistencia);
			jcMousePanel1111xxxx.add(labelAsistencia);
			
			String fechaInicio = new java.sql.Date(new java.util.Date().getTime()).toString();
			String ano1 = fechaInicio.toString().split("-")[0];
			String mes1 = fechaInicio.toString().split("-")[1];
			String dia1 = fechaInicio.toString().split("-")[2];
			
			String fecha1 = dia1 + "/" + mes1 + "/" + ano1;
			
			try
			{
				jcMousePanel1111xxxx.setBorder(new TitledBorder(null, "Fecha: " + fecha1 + " (" + dateFormat.format(new Date()) + ")         Curso Actual: < " + getBaseDeDatos().getCursoActual() + " >", TitledBorder.LEADING, TitledBorder.TOP, new Font("arial", Font.BOLD, 11), new Color(0, 0, 128)));
			}
			catch(Exception e){ }
			
			jcMousePanel1111xxxx.setModo(2);
			jcMousePanel1111xxxx.setOpaque(true);
			jcMousePanel1111xxxx.setVisible(true);
			
			desktop.add(jcMousePanel1111xxxx);
		}
		
		jcMousePanel1111xxxx.setBounds(240, getHeight()-260, 400, 80);
		jcMousePanel1111xxxx.setVisible(true);
		{
			jcMousePanel1111 = new jcMousePanel();
			jcMousePanel1111.setLayout(new GridLayout());
			comboBox = new JKComboBox();
			comboBox.setPreferredSize(new Dimension(280, 24));
			comboBox.setFont(new Font("tahoma", Font.BOLD, 12));	
			
			if(bd != null && bd.isActive())
				getBaseDeDatos().getGroups(comboBox);
			
			JLabel label = new JLabel("Busqueda Por Grupo:");
			label.setIcon(new ImageIcon(ImporDataITACA.class.getResource("/resource/users.png")));
			label.setHorizontalAlignment(SwingConstants.CENTER);
			jcMousePanel1111.add(label);
			JKCoreBar coreBar2=new JKCoreBar(JKCoreBar.MODE_1);
			final JKMenu jkMenu2=new JKMenu("Seleccionar Grupo...", JKCoreBar.MODE_1);
			coreBar2.setPreferredSize(new Dimension(230, 24));
			jkMenu2.setLayout(new GridLayout(2, 2));
			
			coreBar2.add(jkMenu2);
			{
				table2 = new JKTable();
				table2.setFont(new Font("arial", Font.BOLD,12));
				table2.addColumn("Codigo");
				table2.addColumn("Grupo");
				table2.addMouseListener(new MouseListener() 
				{
					@Override
					public void mouseReleased(MouseEvent arg0)
					{
						String code = table2.getValueAt(table2.getSelectedRow(), 0).toString();
						
						try
						{
							tableBusqueda2.clearTable();
						}
						catch(Exception e){ }
						
						getBaseDeDatos().getAlumnos(code, tableBusqueda2, new JLabel());
					}
					
					@Override
					public void mousePressed(MouseEvent arg0) { }
					
					@Override
					public void mouseExited(MouseEvent arg0) { }
					
					@Override
					public void mouseEntered(MouseEvent arg0) { }
					
					@Override
					public void mouseClicked(MouseEvent arg0) { }
				});
				
				table2.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
				JScrollPane jScrollPane = new JScrollPane(table2);
				jScrollPane.setPreferredSize(new Dimension(490, 330));
				jkMenu2.add(jScrollPane);
				coreBar2.setPreferredSize(new Dimension(130, 24));
				
				if(getBaseDeDatos() != null)
					getBaseDeDatos().getGroups(table2, new JLabel(), getBaseDeDatos().getCursoActual());
				
				tableBusqueda2 = new JKTable();
				tableBusqueda2.addColumn("Apellidos y Nombres");
				tableBusqueda2.addColumn("NIA");
				
				JScrollPane jScrollPane1 = new JScrollPane(tableBusqueda2);
				
				tableBusqueda2.addMouseListener(
						new MouseListener()
						{
							@Override
							public void mouseReleased(MouseEvent arg0) 
							{
								if(tableBusqueda2.getSelectedRow() != -1)
								{
									jkMenu2.setPopupMenuVisible(false);
									String g = tableBusqueda2.getValueAt(tableBusqueda2.getSelectedRow(), 1).toString().toUpperCase();
									boolean b = getBaseDeDatos().verificarSiExiste(g);
									
									if(b)
									{
										synchronized (this) 
										{
											setEscontrado(true);
										}
										fieldCode.setText("");
										
										boolean n1 = getBaseDeDatos().verificarSiEsUsuarioComedor(g);
										
										if(n1)
										{
											String tipo = getBaseDeDatos().getTipoDeUsuario(g);
											
											if(tipo.equalsIgnoreCase("2"))
											{
												if(dia.equalsIgnoreCase("jueves"))
												{
													int t = JOptionPane.showConfirmDialog(getInstance(), "Posee Ticked?", "Ticked Necesario", JOptionPane.INFORMATION_MESSAGE);
													
													if(t == JOptionPane.OK_OPTION)
														getBaseDeDatos().addAsistencia(g,"Si");
													else
														JOptionPane.showMessageDialog(getInstance(), "No Autorizado!", "Denegado", JOptionPane.WARNING_MESSAGE);
													return;
												}
											}
										}
										else
										{
											boolean z = false;
											if(dia.equalsIgnoreCase("lunes") || dia.equalsIgnoreCase("martes"))
												z = getBaseDeDatos().verificarSiEstaAutorizadoLunesMartes(g);
											else if(dia.equalsIgnoreCase("jueves"))
												z = getBaseDeDatos().verificarSiEstaAutorizadoJueves(g);
											
											if(!z)
											{
												JOptionPane.showMessageDialog(getInstance(), "No Autorizado!", "Denegado", JOptionPane.WARNING_MESSAGE);
												return;
											}
											
											getBaseDeDatos().addAsistencia(g,"No");
										}
									}
									else
									{
										boolean t1 = getBaseDeDatos().verificarFechas(g);
										
										if(t1)
										{
											//os alumnos no asiduos saltara ticket si el dia que entran 
											//esta fuera de subperiodo alta bajo y si son de dos dias tb ssltaran los jueves
											
											if(getBaseDeDatos().verificarUsuario(g))
											{
												int t = JOptionPane.showConfirmDialog(getInstance(), "Posee Ticked?", "Ticked Necesario", JOptionPane.INFORMATION_MESSAGE);
												
												if(t == JOptionPane.OK_OPTION)
													getBaseDeDatos().addAsistencia(g,"Si");
												else
												{
													JOptionPane.showMessageDialog(getInstance(), "No Autorizado!", "Denegado",JOptionPane.WARNING_MESSAGE);
													return;
												}
											}
											
										}
										else
										{
											if(getBaseDeDatos().verificarSiExiste(g))
											{
												if(getBaseDeDatos().verificarAsistencia(g))
													return;
												else
												{
													int t = JOptionPane.showConfirmDialog(getInstance(), "Posee Ticked?", "Ticked Necesario", JOptionPane.INFORMATION_MESSAGE);
													if(t == JOptionPane.OK_OPTION)
													{
														getBaseDeDatos().addAsistencia(g, "Si");
													}
													else
													{
														JOptionPane.showMessageDialog(getInstance(), "No Autorizado!", "Denegado", JOptionPane.WARNING_MESSAGE);
														return;
													}
												}
											}
											else
											{
												int  x = JOptionPane.showConfirmDialog(getInstance(), "<html><body>No se encuentro Registrado.<br>Desea Registrar?</body></html>","No Registrado",JOptionPane.WARNING_MESSAGE);
												if(x == JOptionPane.OK_OPTION)
												{
													if(a == null)
														a = new RegistrarAlumno(getInstance(), getBaseDeDatos().getNia(g), true);
													else 
														return;
													
													desktop.add(a);
													a.setVisible(true);
													synchronized (this) 
													{
														setEscontrado(true);
													}
													//													a = null;
												}
												else
													return;
											}
										}
									}
									
									String alergias = getBaseDeDatos().verificarAlergias(g);
									
									if(!alergias.equals(""))
										JOptionPane.showMessageDialog(getInstance(), "El Usuario tiene la siguiente restricción: " + alergias, "Advertencia", JOptionPane.WARNING_MESSAGE);
									
									updateAsistencias();
									fieldCode.setText("");
									if(a == null)
										a = new RegistrarAlumno(getInstance(), g, true);
									else  
										return;
									desktop.add(a);
									
									a.setEnabledNIA(false);	
									a.loadDatos();
									
									try
									{
										a.setVisible(true);
									}
									catch(Exception e) { }
									
									//									a = null;
									synchronized (this) 
									{
										setEscontrado(true);
									}
								}
							}
							
							@Override
							public void mousePressed(MouseEvent arg0) { }
							
							@Override
							public void mouseExited(MouseEvent arg0) { }
							
							@Override
							public void mouseEntered(MouseEvent arg0) { }
							
							@Override
							public void mouseClicked(MouseEvent arg0) { }
						});
				
				JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
				panel.add(new JLabel("Termino de Busqueda:"));
				final JTextField textField = new JTextField(35);
				textField.addKeyListener(
						new KeyListener() 
						{
							
							@Override
							public void keyTyped(KeyEvent arg0) { }
							
							@Override
							public void keyReleased(KeyEvent arg0) 
							{
								tableBusqueda2.search(textField.getText());
							}
							
							@Override
							public void keyPressed(KeyEvent arg0) { }
						});
				
				panel.add(textField);
				JPanel panel2 = new JPanel(new BorderLayout());
				panel2.add(panel, BorderLayout.NORTH);
				panel2.add(jScrollPane1, BorderLayout.CENTER);
				panel2.setPreferredSize(new Dimension(0,230));
				jkMenu2.add(panel2);
			}
			
			jcMousePanel1111.add(coreBar2);
			jcMousePanel1111.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, new Font("arial", Font.BOLD, 11), new Color(0, 0, 128)));
			jcMousePanel1111.setModo(2);
			jcMousePanel1111.setOpaque(true);
			jcMousePanel1111.setVisible(true);
			
			desktop.add(jcMousePanel1111);
		}
		
		jcMousePanel1111.setBounds(10, getHeight()-170,460, 35);
		jcMousePanel1111.setVisible(true);
		
		String fechaInicio = new java.sql.Date(new java.util.Date().getTime()).toString();
		String ano1 = fechaInicio.toString().split("-")[0];
		String mes1 = fechaInicio.toString().split("-")[1];
		String dia1 = fechaInicio.toString().split("-")[2];
		
		String fecha1 = dia1 + "/" + mes1 + "/" + ano1;
		
		if(bd!=null)
			bar1.addComponent("Estatus:", "Iniciado Correctamente!",new ImageIcon(getClass().getResource("/resource/A1-connect.png")), Color.blue);
		else
			bar1.addComponent("Estatus:", "Esperando Conexion...",new ImageIcon(getClass().getResource("/resource/A1-connect.png")), Color.blue);
		
		bar1.addComponent("Estatus de Camara:", "Desconectado", new ImageIcon(getClass().getResource("/resource/A1-connect.png")), Color.blue);
		bar1.addComponent("Fecha Actual:", "" + fecha1, new ImageIcon(getClass().getResource("/resource/calendar.png")),Color.blue);
		checkCamara();
		
		fieldCode.addMouseListener(
				new MouseListener() 
				{
					@Override
					public void mouseReleased(MouseEvent arg0) { }
					
					@Override
					public void mousePressed(MouseEvent arg0) { }
					
					@Override
					public void mouseExited(MouseEvent arg0) { }
					
					@Override
					public void mouseEntered(MouseEvent arg0) 
					{
						if(!btnScanner.isSelected())
							btnScanner.doClick();
					}
					
					@Override
					public void mouseClicked(MouseEvent arg0) {	}
				});
		
		getContentPane().add(bar1,BorderLayout.SOUTH);
		
		if(bd != null)
			updateNroAlumnos(bd.getNroAlumnos());
	}
	
	public void repaintData()
	{
		updateNroAlumnos(bd.getNroAlumnos());
		updateDatosCentro();
		updateGroups();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEE");
		
		try 
		{
			dia = dateFormat.format(new Date());
			
			if(dia.equalsIgnoreCase("lunes"))
				nAutorizados = getBaseDeDatos().getComedorLunesMartes();
			
			else if(dia.equalsIgnoreCase("martes")) 
				nAutorizados=getBaseDeDatos().getComedorLunesMartes();
			else if(dia.equalsIgnoreCase("jueves"))
				nAutorizados=getBaseDeDatos().getComedorJueves();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		label1Provistos.setText("  Provistos: " + nAutorizados);
		labelAsistencia.setText("  Asisten: " + nAsistencia);
		
		String fechaInicio = new java.sql.Date(new java.util.Date().getTime()).toString();
		String ano1 = fechaInicio.toString().split("-")[0];
		String mes1 = fechaInicio.toString().split("-")[1];
		String dia1 = fechaInicio.toString().split("-")[2];
		
		String fecha1 = dia1 + "/" + mes1 + "/" + ano1;
		
		try 
		{
			jcMousePanel1111xxxx.setBorder(new TitledBorder(null, "Fecha: " +fecha1+" (" +dateFormat.format(new Date())+")         Curso Actual: < " +getBaseDeDatos().getCursoActual()+" >",
					TitledBorder.LEADING, TitledBorder.TOP, new Font("arial", Font.BOLD, 11), new Color(0, 0, 128)));
		}
		catch(Exception e) { }
	}
	
	public void updateAsistencias()
	{
		nAsistencia = getBaseDeDatos().getnAsistenciasDia();
		labelAsistencia.setText("  Asisten: " + nAsistencia);
	}
	
	public void checkCamara()
	{
		new Thread(
				new Runnable() 
				{
					@Override
					public void run() 
					{
						while(true)
						{
							try 
							{
								Thread.sleep(4000);
							} 
							catch (InterruptedException e) 
							{
								e.printStackTrace();
							}
							
							Webcam webcam = Webcam.getDefault();
							
							if(webcam == null)
								bar1.setTextLabel(1, "Estatus de Camara", "Desconectado");
							else
								bar1.setTextLabel(1, "Estatus de Camara", "Conectado");
						}
					}
				}).start();
	}
	
	public void setBD(BD bd2) 
	{
		bar1.setTextLabel(0, "Estatus", "Iniciado Correctamente!");
		
		this.bd = bd2;
		updateNroAlumnos(bd.getNroAlumnos());
		updateDatosCentro();
		updateGroups();
		
		if(bd != null)
			getBaseDeDatos().getCursos(menuz);
		
		SimpleDateFormat dateFormat=new SimpleDateFormat("EEEEE");
		
		try 
		{
			dia = dateFormat.format(new Date());
			
			if(dia.equalsIgnoreCase("lunes"))
				nAutorizados=getBaseDeDatos().getComedorLunesMartes();
			else if(dia.equalsIgnoreCase("martes"))
				nAutorizados=getBaseDeDatos().getComedorLunesMartes();
			else if(dia.equalsIgnoreCase("jueves"))
				nAutorizados=getBaseDeDatos().getComedorJueves();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		getBaseDeDatos().getGroups(table2,new JLabel(),getBaseDeDatos().getCursoActual());
		
		try
		{
			Calendar calendar = Calendar.getInstance();
			java.sql.Date date = new java.sql.Date(new Date().getTime());
			
			calendar.setTimeInMillis(date.getTime());
			calendar.add(Calendar.DATE, -7);			
			Date dato = new java.sql.Date(calendar.getTimeInMillis());
			
			java.sql.Date date3 = new java.sql.Date(dato.getTime());
			
			while(date.getTime() >= date3.getTime())
			{
				String fecha = date.toString();
				String array[] = fecha.split("-");
				int v = 0;
				
				v = Integer.parseInt(array[2]);
				v = v - 1;
				
				String k = "";
				
				if(v <= 9)
					k = "0" + v;
				else
					k = "" + v;
				
				String nueva = "" + array[0] + "-" + array[1] + "-" + k;
				SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
				Date dat = dateFormat2.parse(nueva);
				String anterior = dateFormat.format(dat);
				
				if(anterior.equalsIgnoreCase("lunes") || anterior.equalsIgnoreCase("martes"))
				{
					ArrayList<Persona> a = getBaseDeDatos().getUsuariosComedor(getBaseDeDatos().getCursoActual());
					
					for(int index = 0; index < a.size(); index++)
					{
						Boolean n1 = getBaseDeDatos().getSelectedLunesMartes(a.get(index).getNia());
						
						if(n1)
						{
							boolean n = getBaseDeDatos().verificarAsistrencia(a.get(index).getNia(), getBaseDeDatos().getCursoActual(), dat, anterior);
							
							if(!n)
								getBaseDeDatos().agregarFalta(a.get(index).getNia(), getBaseDeDatos().getCursoActual(), date, dat, anterior);
						}
					}
				}
				else if(anterior.equalsIgnoreCase("jueves"))
				{
					ArrayList<Persona> a = getBaseDeDatos().getUsuariosComedor(getBaseDeDatos().getCursoActual());
					
					for(int index = 0; index < a.size(); index++)
					{
						Boolean n1 = getBaseDeDatos().getSelectedJueves(a.get(index).getNia());
						
						if(n1) 
						{
							boolean n = getBaseDeDatos().verificarAsistrencia(a.get(index).getNia(), getBaseDeDatos().getCursoActual(), dat, anterior);
							
							if(!n)
								getBaseDeDatos().agregarFalta(a.get(index).getNia(), getBaseDeDatos().getCursoActual(), date, dat, anterior);
						}
					}
				}

				calendar.setTimeInMillis(date.getTime());
				calendar.add(Calendar.DATE, -1);
				date = new java.sql.Date(calendar.getTimeInMillis());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		label1Provistos.setText("  Provistos: " + nAutorizados);
		updateAsistencias();
		labelAsistencia.setText("  Asisten: " + nAsistencia);
		
		String fechaInicio = new java.sql.Date(new java.util.Date().getTime()).toString();
		String ano1 = fechaInicio.toString().split("-")[0];
		String mes1 = fechaInicio.toString().split("-")[1];
		String dia1 = fechaInicio.toString().split("-")[2];
		
		String fecha1 = dia1 + "/" + mes1 + "/" + ano1;
		
		try
		{
			jcMousePanel1111xxxx.setBorder(new TitledBorder(null, "Fecha: " + fecha1 + " (" + dateFormat.format(new Date()) + ")         Curso Actual: < " + getBaseDeDatos().getCursoActual() + " >",
					TitledBorder.LEADING, TitledBorder.TOP, new Font("arial", Font.BOLD, 11), new Color(0, 0, 128)));
		}
		catch(Exception e){ }
		
		labelAsistencia.setFont(new Font("arial", Font.BOLD,12));
		label1Provistos.setFont(new Font("arial", Font.BOLD,12));
		tableBusqueda.getColumn("Apellidos y Nombres").setPreferredWidth(200);
		tableBusqueda.getColumn("Apellidos y Nombres").setWidth(200);
		
		new Thread(
				new Runnable() 
				{
					@Override
					public void run() 
					{ 
						String cursoActual = getBaseDeDatos().getCursoActual();
						ArrayList<Persona> xz = getBaseDeDatos().getTodosLosAlumnos(cursoActual);
						
						for(int index = 0; index<xz.size(); index++)
							tableBusqueda.addRow(xz.get(index).getNombreCompleto(), xz.get(index).getNia());	
					}
				}).start();
		
		tableBusqueda.addMouseListener(
				new MouseListener() 
				{
					@Override
					public void mouseReleased(MouseEvent arg0) 
					{
						if(arg0.getClickCount() == 2)
						{
							String g = tableBusqueda.getValueAt(tableBusqueda.getSelectedRow(), 1).toString();
							boolean n1 = getBaseDeDatos().verificarSiEsUsuarioComedor(g);
							
							if(n1)
							{
								String tipo = getBaseDeDatos().getTipoDeUsuario(g);
								
								if(tipo.equalsIgnoreCase("2"))
								{
									if(dia.equalsIgnoreCase("jueves"))
									{
										int t = JOptionPane.showConfirmDialog(getInstance(), "Posee Ticked?", "Ticked Necesario", JOptionPane.INFORMATION_MESSAGE);
										
										if(t==JOptionPane.OK_OPTION)
											getBaseDeDatos().addAsistencia(g,"Si");
										else 
										{
											JOptionPane.showMessageDialog(getInstance(), "No Autorizado!", "Denegado", JOptionPane.WARNING_MESSAGE);
											return;
										}
									}
								}
								else
								{
									boolean z = false;
									
									if(dia.equalsIgnoreCase("lunes") || dia.equalsIgnoreCase("martes"))
										z = getBaseDeDatos().verificarSiEstaAutorizadoLunesMartes(g);
									else
										if(dia.equalsIgnoreCase("jueves"))
											z = getBaseDeDatos().verificarSiEstaAutorizadoJueves(g);
									
									if(!z)
									{
										JOptionPane.showMessageDialog(getInstance(), "No Autorizado!", "Denegado", JOptionPane.WARNING_MESSAGE);
										return;
									}
									getBaseDeDatos().addAsistencia(g,"No");
								}
							}
							else
							{
								boolean t1 = getBaseDeDatos().verificarFechas(g);
								
								if(t1)
								{
									if(getBaseDeDatos().verificarUsuario(g))
									{
										int t = JOptionPane.showConfirmDialog(getInstance(), "Posee Ticked?","Ticked Necesario", JOptionPane.INFORMATION_MESSAGE);
										
										if(t == JOptionPane.OK_OPTION)
											getBaseDeDatos().addAsistencia(g, "Si");
										else
										{
											JOptionPane.showMessageDialog(getInstance(), "No Autorizado!", "Denegado", JOptionPane.WARNING_MESSAGE);
											return;
										}
									}
								}
								else
								{
									int t = JOptionPane.showConfirmDialog(getInstance(), "Posee Ticked?", "Ticked Necesario", JOptionPane.INFORMATION_MESSAGE);
									
									if(t == JOptionPane.OK_OPTION)
										getBaseDeDatos().addAsistencia(g, "Si");
									else
									{
										JOptionPane.showMessageDialog(getInstance(), "No Autorizado!","Denegado",JOptionPane.WARNING_MESSAGE);
										return;
									}
								}
							}
							
							if(a == null)
								a = new RegistrarAlumno(getInstance(),getBaseDeDatos().getNia(g),true);
							else 
								return;
							desktop.add(a);
							
							a.setEnabledNIA(false);
							a.loadDatos();
							updateAsistencias();
							a.setVisible(true);
						}
					}
					
					@Override
					public void mousePressed(MouseEvent arg0) { }
					
					@Override
					public void mouseExited(MouseEvent arg0) { }
					
					@Override
					public void mouseEntered(MouseEvent arg0) { }
					
					@Override
					public void mouseClicked(MouseEvent arg0) { }
				});
	}
	
	public synchronized String getScanned() throws InterruptedException 
	{
		if(a != null)
		{
			a.dispose();
			a = null;
		}
		
		return fieldCode.getText();
	}
	
	public void activeScanner() 
	{
		if(!btnScanner.isSelected())
			btnScanner.doClick();
		else
			fieldCode.requestFocus();
	}
	
	private jcMousePanel jcMousePanel1111xxxx;
	private String nAsistencia = "0";
	private String dia;
	private String nAutorizados = "0";
	private boolean noCerrar = false;
	private JLabel mnBecas;
	
	public boolean isNoCerrar() 
	{
		return noCerrar;
	}
	
	public void setNoCerrar(boolean noCerrar) 
	{
		this.noCerrar = noCerrar;
	}
	
	public void setConsultaNull() {}
	
	public void updateCursos() 
	{
		if(bd!=null)
			getBaseDeDatos().getCursos(menuz);
	}
	
	public void controlDePrecencia(boolean off)
	{
		jcMousePanel11.setVisible(off);
		btnScanner.setVisible(off);
		jcMousePanel11112.setVisible(off);
		jcMousePanel111.setVisible(off);
		jcMousePanel1111.setVisible(off);
		jcMousePanel111121.setVisible(off);
		jcMousePanel1111xxxx.setVisible(off);
	}
	
	public void permiso(int permiso) 
	{
		switch (permiso) 
		{
			case 6:
				menuPrincipalComedor.contabilidad();
				item.setVisible(false);
				menu3.setVisible(false);
				controlDePrecencia(false);
				break;
				
			case 5:
				menuPrincipalComedor.contabilidadOff();
				item.setVisible(false);
				menu3.setVisible(false);
				controlDePrecencia(true);
				break;
				
			case 4:
				break;
				
			default:
				break;
		}
	}
}