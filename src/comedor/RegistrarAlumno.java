package comedor;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;

import com.leyer.JKComboBox;
import com.leyer.JKPanel;
import com.leyer.JKTable;
//import com.orsonpdf.Stream;
import com.toedter.calendar.JDateChooser;

import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.BorderLayout;

import javax.swing.JTabbedPane;

import java.awt.GridLayout;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;

import org.jdesktop.swingx.JXBusyLabel;
import org.jdesktop.xswingx.PromptSupport;

import java.awt.Font;
import java.io.File;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.SwingConstants;

public class RegistrarAlumno extends JInternalFrame implements MouseListener 
{
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textFieldApellido1;
	private JTextField textField_2;
	private JKPanel jkPanel;
	private ComedorGUI principal;
	private JTextField textField_9;
	private JTextField textField_10;
	private JKComboBox comboBox;
	private ArrayList<String> arrayListgrupos;
	private JTextArea textArea_2;
	private boolean actualizar = false;
	private JButton btnNewButton_1;
	private JButton btnNewButton;
	private JXBusyLabel lblNewLabel_7;

	private RegistrarAlumno getInstance()
	{
		return this;
	}

	private boolean found=false;

	public synchronized boolean isFound() 
	{
		return found;
	}

	public synchronized void setFound(boolean found) 
	{
		this.found = found;
	}

	private JKComboBox comboBoxSexo;
	private JTabbedPane tabbedPane;

	private boolean mode = false;
	private JKComboBox comboBox_1;
	private JPanel panel_4;
	private JKTable tableFechas;
	private ArrayList<String> listFechas;

	public RegistrarAlumno(final ComedorGUI principal,String c, boolean b) 
	{
		setTitle("Registro y Actualizacion de Datos de Alumnos");
		this.principal = principal;
		setBounds(new Random().nextInt(50), 10, 977, 571);
		this.mode = b;

		JPanel panel_7 = new JPanel();

		JPanel panel_8 = new JPanel()
		{
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) 
			{
				setOpaque(false);
				g.drawImage(new ImageIcon(getClass().getResource("/resource/e2.jpg")).getImage(), 0, 0, getWidth(), getHeight(), null);
			}
		};

		GroupLayout groupLayout = new GroupLayout(getContentPane());

		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_8, GroupLayout.DEFAULT_SIZE, 961, Short.MAX_VALUE)
				.addComponent(panel_7, GroupLayout.DEFAULT_SIZE, 961, Short.MAX_VALUE)
				);

		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(panel_8, GroupLayout.PREFERRED_SIZE, 488, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_7, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, " Datos del Alumno", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JLabel lblNewLabel = new JLabel("Nombres:");

		textField = new JTextField();
		textField.setColumns(10);

		JLabel lblApellidos = new JLabel("Apellido1:");

		textFieldApellido1 = new JTextField();
		textFieldApellido1.setColumns(10);

		JLabel lblNia = new JLabel("NIA:");
		lblNia.setHorizontalAlignment(SwingConstants.CENTER);

		String g = "";
		if(!c.equalsIgnoreCase("0"))
			g = c;
		else
			g = "";

		textField_2 = new JTextField(g);

		lblNewLabel_7 = new JXBusyLabel();
		lblNewLabel_7.setVisible(false);

		textField_2.addKeyListener(new KeyListener() 
		{
			@Override
			public void keyTyped(KeyEvent arg0) { }

			@Override
			public void keyReleased(KeyEvent arg0) 
			{
				if(!isFound())
				{
					arg0.consume();
					new Thread(
							new Runnable() 
							{

								@Override
								public synchronized void run() 
								{
									final String h = textField_2.getText();

									setFound((found = principal.getBaseDeDatos().verificarSiExiste(h)));

									if(isFound()) 
									{
										lblNewLabel_7.setVisible(true);
										lblNewLabel_7.setBusy(true);

										if(!h.equalsIgnoreCase("0"))
										{
											actualizar = true;
											btnNewButton.setText("Actualizar");
											btnNewButton.setIcon(new ImageIcon(getClass().getResource("/resource/update.png")));

											principal.getBaseDeDatos().getDatosAlumno(textField_2.getText(),getInstance());
											lblNewLabel_7.setBusy(false);
											lblNewLabel_7.setVisible(false);
											found=true;
											setFound(true);
											principal.getBaseDeDatos().getFechasPeriodo(getNia(),tableFechas,listFechas);
										}
										else
										{
											int y = JOptionPane.showConfirmDialog(principal, "<html><body>Alumno ya registrado<br>Desea cargar sus Datos?</body></html>","Existente",JOptionPane.INFORMATION_MESSAGE);

											if(y == JOptionPane.OK_OPTION)
											{
												actualizar = true;
												btnNewButton.setText("Actualizar");
												btnNewButton.setIcon(new ImageIcon(getClass().getResource("/resource/update.png")));
												principal.getBaseDeDatos().getDatosAlumno(textField_2.getText(),getInstance());
												panel_4.updateUI();
												panel_4.validate();
												lblNewLabel_7.setBusy(false);
												lblNewLabel_7.setVisible(false);
												setFound(true);
												principal.getBaseDeDatos().getFechasPeriodo(getNia(),tableFechas,listFechas);
											}
											else { }
										}
									}
								}
							}).start();
				}
			}

			@Override
			public void keyPressed(KeyEvent arg0) { }
		});

		JLabel lblGrupo = new JLabel("Grupo:");
		lblGrupo.setIcon(new ImageIcon(RegistrarAlumno.class.getResource("/resource/users.png")));

		comboBox = new JKComboBox();
		arrayListgrupos = new ArrayList<>();

		principal.getBaseDeDatos().getGroups(comboBox, arrayListgrupos);
		comboBox.setFont(new Font("arial", Font.BOLD, 12));

		new ArrayList<>();
		comboBox.addActionListener(
				new ActionListener() 
				{
					@Override
					public void actionPerformed(ActionEvent arg0) { }
				});

		JLabel lblSexo = new JLabel("Sexo:");

		comboBoxSexo = new JKComboBox();
		comboBoxSexo.setFont(new Font("arial", Font.BOLD,12));

		comboBoxSexo.addItem("H");
		comboBoxSexo.addItem("F");
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);

		textFieldEmail = new JTextField();
		textFieldEmail.setBorder(BorderFactory.createTitledBorder(""));
		textFieldEmail.setColumns(10);
		PromptSupport.setPrompt("Correo electronico", textFieldEmail);

		JLabel lblTelf = new JLabel("Telefono 1:");

		textFieldTelefono1 = new JTextField();
		PromptSupport.setPrompt("Telefono", textFieldTelefono1 );
		textFieldTelefono1.setBorder(BorderFactory.createTitledBorder(""));
		textFieldTelefono1.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Fecha Nac:");
		lblNewLabel_8.setIcon(new ImageIcon(RegistrarAlumno.class.getResource("/resource/calendar.png")));

		textFieldFechaNacimiento = new JDateChooser();
		textFieldFechaNacimiento.setBorder(BorderFactory.createTitledBorder(""));
		textFieldFechaNacimiento.getDateEditor().setEnabled(false);

		JLabel lblApellido = new JLabel("Apellido2:");

		textFieldApellido2 = new JTextField();
		textFieldApellido2.setColumns(10);

		JLabel lblDocumento = new JLabel("Documento:");

		textFieldDocumento = new JTextField();
		textFieldDocumento.setColumns(10);

		JLabel lblNewLabelExpediente = new JLabel("Expediente:");

		textFieldExpediente = new JTextField();
		textFieldExpediente.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("Sip:");

		textFieldSip = new JTextField();
		textFieldSip.setColumns(10);

		JLabel lblTelefono_1 = new JLabel("Telefono 2:");

		textFieldTelefono2 = new JTextField();
		textFieldTelefono2.setColumns(10);

		JLabel lblTipoDoc = new JLabel("Tipo Doc:");
		lblTipoDoc.setHorizontalAlignment(SwingConstants.CENTER);

		comboBox_1 = new JKComboBox();
		comboBox_1.addItem("NIF");
		comboBox_1.addItem("NIE");

		JLabel lblCurso = new JLabel("Curso:");

		textFieldCurso = new JTextField();
		textFieldCurso.setColumns(10);

		new JPanel();

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Datos Familiares", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		JButton btnAgregarFamiliar = new JButton("Agregar Familiar");

		btnAgregarFamiliar.addActionListener(
				new ActionListener() 
				{
					public void actionPerformed(ActionEvent arg0) 
					{	
						String bigList[] = { "Madre", "Padre", "Otro"};
						Object s = JOptionPane.showInputDialog(getInstance(), "Seleccionar", "Seleccionar", JOptionPane.QUESTION_MESSAGE, null, bigList, "Titan");

						if(s!=null)
						{
							String parentesco = "";

							if(s.toString().equalsIgnoreCase("Madre"))
								parentesco = "1";
							else if(s.toString().equalsIgnoreCase("Padre"))
								parentesco = "2";
							else
								parentesco = "3";

							addFamiliar(parentesco, "", "", "", "", "1", getNia(), null, "");
						}
					}
				});

		btnAgregarFamiliar.setIcon(new ImageIcon(RegistrarAlumno.class.getResource("/resource/newElement.png")));

		jkPanel = new JKPanel();
		panel_4 = new JPanel(new GridLayout());
		panel_4.add(jkPanel);
		panel_4.setOpaque(false);
		panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Foto del Alumno", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		btnNewButton_1 = new JButton("Tomar Foto");
		btnNewButton_1.addActionListener(
				new ActionListener() 
				{
					public void actionPerformed(ActionEvent arg0) 
					{
						new Camara(principal, getInstance());
					}
				});

		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setIcon(new ImageIcon(RegistrarAlumno.class.getResource("/resource/1.png")));

		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new TitledBorder(null, "Otros Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_9.setOpaque(false);
		JButton btnBuscarFoto = new JButton("Buscar Foto");

		btnBuscarFoto.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				JFileChooser chooser = new JFileChooser();
				javax.swing.filechooser.FileFilter filtro2 = new FileNameExtensionFilter("Imagenes (jpg, jpeg, png)", "jpg", "png", "jpeg");
				chooser.setFileFilter(filtro2);
				chooser.addChoosableFileFilter(filtro2);
				chooser.setAcceptAllFileFilterUsed(false);

				int n = chooser.showOpenDialog(principal);

				if (n == JFileChooser.APPROVE_OPTION) 
				{
					fileFoto = chooser.getSelectedFile();
					jkPanel.setBackground(new ImageIcon(chooser.getSelectedFile().getPath()));
				}
			}
		});

		btnBuscarFoto.setIcon(new ImageIcon(RegistrarAlumno.class.getResource("/resource/2.png")));
		btnBuscarFoto.setFont(new Font("Tahoma", Font.BOLD, 11));

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setOpaque(false);
		panel_3.setLayout(new BorderLayout(0, 0));

		tableFechas = new JKTable();
		tableFechas.addColumn("F. Alta");
		tableFechas.addColumn("F. Bajas");
		tableFechas.addColumn("Beca");
		tableFechas.addColumn("Tipo Usuario");

		JScrollPane scrollPane = new JScrollPane(tableFechas);
		listFechas = new ArrayList<>();

		principal.getBaseDeDatos().getFechasPeriodo(getNia(), tableFechas, listFechas);
		tableFechas.getTableHeader().setReorderingAllowed(false);
		tableFechas.getTableHeader().setResizingAllowed(false);
		panel_3.add(scrollPane);

		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5, BorderLayout.NORTH);

		JButton btnNewButton_4 = new JButton("Nuevo Periodo");
		btnNewButton_4.setIcon(new ImageIcon(RegistrarAlumno.class.getResource("/resource/newElement.png")));

		btnNewButton_4.addActionListener(
				new ActionListener() 
				{
					public void actionPerformed(ActionEvent arg0) 
					{
						new NuevoPeriodoComedor(principal, getNia(), false, "").setVisible(true);	
						principal.getBaseDeDatos().getFechasPeriodo(getNia(), tableFechas, listFechas);
					}
				});

		panel_5.add(btnNewButton_4);
		JButton btnNewButton_5 = new JButton("Actualizar");

		btnNewButton_5.addActionListener(
				new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						if(tableFechas.getSelectedRow() != -1)
						{
							String id = listFechas.get(tableFechas.getSelectedRow());

							new NuevoPeriodoComedor(principal, getNia(), true, id).setVisible(true);
							principal.getBaseDeDatos().getFechasPeriodo(getNia(), tableFechas, listFechas);
						}
						else
						{
							JOptionPane.showMessageDialog(principal, "Debe seleccionar un Periodo!", "Seleccion Vacia", JOptionPane.WARNING_MESSAGE);
							return;
						}
					}
				});

		panel_5.add(btnNewButton_5);

		GroupLayout gl_panel_8 = new GroupLayout(panel_8);

		gl_panel_8.setHorizontalGroup(
				gl_panel_8.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_8.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel_8.createParallelGroup(Alignment.LEADING, false)
								.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel_8.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_8.createSequentialGroup()
												.addGroup(gl_panel_8.createParallelGroup(Alignment.LEADING, false)
														.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(btnBuscarFoto, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(panel_9, GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE))
														.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE))
														.addContainerGap())
				);

		gl_panel_8.setVerticalGroup(
				gl_panel_8.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_8.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel_8.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_8.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_panel_8.createSequentialGroup()
												.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(btnBuscarFoto, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
												.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addComponent(panel_9, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(gl_panel_8.createParallelGroup(Alignment.LEADING, false)
														.addComponent(panel_3, 0, 0, Short.MAX_VALUE)
														.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE))
														.addGap(22))
				);

		JLabel lblNewLabel_1 = new JLabel("Persona de contacto de la familia:");

		textField_9 = new JTextField();
		textField_9.setColumns(10);

		new ButtonGroup();
		new ButtonGroup();

		JLabel lblNewLabel_4 = new JLabel("Alergias/ informe m\u00E9dico:");
		JPanel panel_10 = new JPanel();
		JLabel lblNewLabel_5 = new JLabel("Medicamentos:");

		textField_10 = new JTextField();
		textField_10.setColumns(10);
		new ButtonGroup();

		JLabel lblCuentaBamcaria = new JLabel("Cuenta Bancaria:");

		textFieldCuenta = new JTextField();
		textFieldCuenta.setColumns(10);

		JLabel lblReferenciaDeMandato = new JLabel("Referencia de Mandato:");

		textFieldReferenciaMandato = new JTextField();
		textFieldReferenciaMandato.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textFieldReferenciaMandato.setColumns(10);

		GroupLayout gl_panel_9 = new GroupLayout(panel_9);

		gl_panel_9.setHorizontalGroup(
				gl_panel_9.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_9.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel_9.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_10, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
								.addComponent(lblNewLabel_1)
								.addComponent(textField_9, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
								.addGroup(gl_panel_9.createSequentialGroup()
										.addComponent(lblCuentaBamcaria)
										.addPreferredGap(ComponentPlacement.RELATED, 152, GroupLayout.PREFERRED_SIZE))
										.addComponent(textFieldCuenta, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
										.addGroup(gl_panel_9.createSequentialGroup()
												.addComponent(lblNewLabel_5)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(textField_10, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE))
												.addComponent(lblNewLabel_4)
												.addGroup(gl_panel_9.createSequentialGroup()
														.addComponent(lblReferenciaDeMandato)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(textFieldReferenciaMandato, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)))
														.addContainerGap())
				);

		gl_panel_9.setVerticalGroup(
				gl_panel_9.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_9.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblNewLabel_1)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(textField_9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblCuentaBamcaria)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(textFieldCuenta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_9.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblReferenciaDeMandato)
								.addComponent(textFieldReferenciaMandato, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(12)
								.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panel_10, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel_9.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblNewLabel_5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(textField_10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addContainerGap(21, Short.MAX_VALUE))
				);

		panel_10.setLayout(new GridLayout(0, 1, 0, 0));

		JScrollPane scrollPane_2 = new JScrollPane();
		panel_10.add(scrollPane_2);

		textArea_2 = new JTextArea();
		scrollPane_2.setViewportView(textArea_2);
		panel_9.setLayout(gl_panel_9);
		panel_8.setLayout(gl_panel_8);
		panel_7.setLayout(new GridLayout(1, 0, 0, 0));

		JButton btnNewButton_2 = new JButton("Cerrar");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));

		btnNewButton_2.addActionListener(
				new ActionListener() 
				{
					public void actionPerformed(ActionEvent arg0) 
					{
						dispose();

						if(mode)
							principal.activeScanner();
					}
				});

		JButton btnNewButton_3 = new JButton("Incidencias");
		btnNewButton_3.setIcon(new ImageIcon(RegistrarAlumno.class.getResource("/resource/ina.png")));

		btnNewButton_3.addActionListener(
				new ActionListener() 
				{
					public void actionPerformed(ActionEvent arg0) 
					{
						new DialogoIncidencias(getInstance(), principal, getNia()).setVisible(true);
					}
				});

		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_7.add(btnNewButton_3);

		JButton btnFaltas = new JButton("Faltas");
		btnFaltas.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnFaltas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				final JDialog dialog= new JDialog(principal,true);
				dialog.setTitle("Faltas");
				JPanel panel= new JPanel(new GridLayout());
				JButton button = new JButton("Cerrar");

				button.setIcon(new ImageIcon(RegistrarAlumno.class.getResource("/resource/close1.png")));
				final JKTable jkTable = new JKTable();
				button.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						dialog.dispose();
					}
				});

				JButton jButton = new JButton("Agregar Observacion a la Falta");
				final ArrayList<String> arrayList = new ArrayList<>();
				jButton.setIcon(new ImageIcon(RegistrarAlumno.class.getResource("/resource/newElement.png")));

				jButton.addActionListener( 
						new ActionListener() 
						{
							@Override
							public void actionPerformed(ActionEvent arg0) 
							{
								if(jkTable.getSelectedRow() != -1)
								{

									final String id = "" + arrayList.get(jkTable.getSelectedRow());

									final JDialog frame = new JDialog(principal,true);
									frame.getContentPane().setLayout(new GridLayout());
									JPanel panelx = new JPanel(new BorderLayout());
									panelx.setBorder(BorderFactory.createTitledBorder("Observacion"));

									final JTextArea area = new JTextArea();
									area.setLineWrap(true);
									area.setWrapStyleWord(true);
									JScrollPane scrollPane = new JScrollPane(area);
									panelx.add(scrollPane,BorderLayout.CENTER);

									JPanel panela = new JPanel(new GridLayout());
									JButton b1 = new JButton("Cancelar");
									JButton b2 = new JButton("Aceptar");
									b1.addActionListener(
											new ActionListener()
											{
												@Override
												public void actionPerformed(ActionEvent arg0)  
												{
													frame.dispose();
												}
											});
									b2.addActionListener(
											new ActionListener() 
											{
												@Override
												public void actionPerformed(ActionEvent arg0) 
												{
													String observacion = area.getText();
													principal.getBaseDeDatos().addObservacionFalta(id,observacion);
													principal.getBaseDeDatos().getFaltasPorAlumno(getNia(),jkTable,arrayList);
													frame.dispose();
												}
											});
									panela.add(b1);
									panela.add(b2);
									panelx.add(panela, BorderLayout.SOUTH);
									frame.getContentPane().add(panelx);

									frame.setSize(300, 200);
									frame.setLocationRelativeTo(principal);
									frame.setVisible(true);
								}
								else
								{
									JOptionPane.showMessageDialog(principal, "Debe seleccionar una Falta!", "sin seleccion", JOptionPane.WARNING_MESSAGE);
									return;
								}
							}
						});

				panel.add(button);
				panel.add(jButton);
				dialog.getContentPane().setLayout(new BorderLayout());
				JPanel panelCenter = new JPanel(new GridLayout());

				jkTable.addColumn("Fecha");
				jkTable.addColumn("Dia");
				jkTable.addColumn("Observacion");
				jkTable.getTableHeader().setReorderingAllowed(false);
				jkTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				jkTable.getColumn("Observacion").setPreferredWidth(420);
				jkTable.getColumn("Observacion").setWidth(420);

				jkTable.getColumn("Fecha").setPreferredWidth(10);
				jkTable.getColumn("Fecha").setWidth(10);

				jkTable.getColumn("Dia").setPreferredWidth(6);
				jkTable.getColumn("Dia").setWidth(6);
				jkTable.setRowHeight(40);

				principal.getBaseDeDatos().getFaltasPorAlumno(getNia(),jkTable,arrayList);
				JScrollPane jScrollPane = new JScrollPane(jkTable);
				panelCenter.add(jScrollPane);

				dialog.getContentPane().add(panelCenter,BorderLayout.CENTER);
				dialog.getContentPane().add(panel,BorderLayout.SOUTH);
				dialog.setSize(700, 300);
				dialog.setLocationRelativeTo(principal);
				dialog.setVisible(true);
			}
		});

		btnFaltas.setIcon(new ImageIcon(RegistrarAlumno.class.getResource("/resource/ina.png")));
		panel_7.add(btnFaltas);
		btnNewButton_2.setIcon(new ImageIcon(RegistrarAlumno.class.getResource("/resource/A1-close.png")));
		panel_7.add(btnNewButton_2);

		btnNewButton = new JButton("Registrar");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(
				new ActionListener() 
				{
					public void actionPerformed(ActionEvent arg0) 
					{
						if(actualizar)
						{
							if(getNia().length() <= 0)
							{
								JOptionPane.showMessageDialog(getInstance(), "Debe ingresar el NIA!","Vacio",JOptionPane.WARNING_MESSAGE);
								return;
							}

							if(getNombres().length() <= 0)
							{
								JOptionPane.showMessageDialog(getInstance(), "Debe ingresar el nombre!","Vacio",JOptionPane.WARNING_MESSAGE);
								return;
							}
							if(getApellido1().length()<=0){
								JOptionPane.showMessageDialog(getInstance(), "Debe ingresar el apellido!", "Vacio", JOptionPane.WARNING_MESSAGE);
								return;
							}
							boolean b = principal.getBaseDeDatos().actualizarDatosAlumno(getNia(), getNombres(), getApellido1(), getFoto(), getGrupo(), fileFoto, getSexo(), getEmail(), getTelefono1(), getTelefono2(), getfechaNacimiento().toString(), getDocumento(), getSIP(), getExpediente(), getApellido2(), getTipoDoc(), getCurso(), getPersonaDeContacto(), getCuentaBancaria(), getInformedico(), getMedicamentos(), getReferenciaMandato());

							if(b)
							{
								for(int index = 0; index < arrayListFamiliar.size(); index++)
									principal.getBaseDeDatos().actualizarFamiliar(arrayListFamiliar.get(index));

								JOptionPane.showMessageDialog(getInstance(), "Se han actualizado los datos correctamente!","Exio al Actualizar!",JOptionPane.INFORMATION_MESSAGE);

								dispose();

								if(mode)
									principal.activeScanner();
							}
							else
								JOptionPane.showMessageDialog(getInstance(), "ha ocurrido un error al actualizar!","Error al actualizar",JOptionPane.ERROR_MESSAGE);

							return;
						}	

						if(getNia().length() <= 0)
						{
							JOptionPane.showMessageDialog(getInstance(), "Debe ingresar el NIA!","Vacio",JOptionPane.WARNING_MESSAGE);
							return;
						}

						if(getNombres().length() <= 0)
						{
							JOptionPane.showMessageDialog(getInstance(), "Debe ingresar el nombre!", "Vacio", JOptionPane.WARNING_MESSAGE);
							return;
						}

						if(getApellido1().length() <= 0)
						{
							JOptionPane.showMessageDialog(getInstance(), "Debe ingresar el apellido!", "Vacio", JOptionPane.WARNING_MESSAGE);
							return;
						}

						boolean nia = principal.getBaseDeDatos().newAlumno(getNia(), getNombres(), getApellido1(), getFoto(),
								getGrupo(), fileFoto, getSexo(), getEmail(), getTelefono1(), getTelefono2(), getfechaNacimiento().toString(),
								getDocumento(), getSIP(), getExpediente(), getApellido2(), getTipoDoc(), getCurso(), getReferenciaMandato());

						if(nia)
						{
							for(int index = 0; index < arrayListFamiliar.size(); index++)
								principal.getBaseDeDatos().actualizarFamiliar(arrayListFamiliar.get(index));

							JOptionPane.showMessageDialog(principal, "Se ha registrado Correctamente!","Exito!",JOptionPane.INFORMATION_MESSAGE);
							dispose();
						}
					}
				});

		btnNewButton.setIcon(new ImageIcon(RegistrarAlumno.class.getResource("/resource/41.png")));
		panel_7.add(btnNewButton);

		getContentPane().setLayout(groupLayout);

		textField_2.setBorder(BorderFactory.createTitledBorder(""));
		PromptSupport.setPrompt("00000000", textField_2);


		textField.setBorder(BorderFactory.createTitledBorder(""));
		PromptSupport.setPrompt("Nombres", textField);

		textFieldApellido1.setBorder(BorderFactory.createTitledBorder(""));
		PromptSupport.setPrompt("Apellidos", textFieldApellido1);

		textArea_2.setBorder(BorderFactory.createTitledBorder(""));
		PromptSupport.setPrompt("Informe...", textArea_2);
		textFieldCurso.setText(principal.getBaseDeDatos().getCursoActual());
		textFieldDocumento.setBorder(BorderFactory.createTitledBorder(""));
		textFieldApellido2.setBorder(BorderFactory.createTitledBorder(""));
		textFieldExpediente.setBorder(BorderFactory.createTitledBorder(""));
		textFieldSip.setBorder(BorderFactory.createTitledBorder(""));
		textFieldTelefono2.setBorder(BorderFactory.createTitledBorder(""));

		textFieldCuenta.setBorder(BorderFactory.createTitledBorder(""));
		panel_1.setOpaque(false);
		tabbedPane.setPreferredSize(new Dimension(0, 300));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);

		gl_panel_1.setHorizontalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 468, GroupLayout.PREFERRED_SIZE)
				.addComponent(btnAgregarFamiliar, GroupLayout.PREFERRED_SIZE, 468, GroupLayout.PREFERRED_SIZE)
				);

		gl_panel_1.setVerticalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
						.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAgregarFamiliar))
				);

		panel_1.setLayout(gl_panel_1);
		panel.setOpaque(false);
		GroupLayout gl_panel = new GroupLayout(panel);

		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
										.addComponent(lblGrupo)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(comboBox, GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE))
										.addGroup(gl_panel.createSequentialGroup()
												.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
														.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblApellidos)
														.addComponent(lblApellido)
														.addComponent(lblNia, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
														.addGap(6)
														.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
																.addComponent(textField_2)
																.addComponent(textFieldApellido2)
																.addComponent(textField)
																.addComponent(textFieldApellido1, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
																.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
																		.addGroup(gl_panel.createSequentialGroup()
																				.addGap(8)
																				.addComponent(lblNewLabel_8)
																				.addPreferredGap(ComponentPlacement.RELATED)
																				.addComponent(textFieldFechaNacimiento, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
																				.addGroup(gl_panel.createSequentialGroup()
																						.addPreferredGap(ComponentPlacement.RELATED)
																						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
																								.addComponent(lblTipoDoc, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																								.addComponent(lblDocumento, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
																								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
																										.addGroup(gl_panel.createSequentialGroup()
																												.addPreferredGap(ComponentPlacement.RELATED)
																												.addComponent(textFieldDocumento, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
																												.addGroup(gl_panel.createSequentialGroup()
																														.addGap(5)
																														.addComponent(comboBox_1, 0, 125, Short.MAX_VALUE))))
																														.addGroup(gl_panel.createSequentialGroup()
																																.addGap(10)
																																.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																																.addGap(13)
																																.addComponent(lblCurso)
																																.addPreferredGap(ComponentPlacement.RELATED)
																																.addComponent(textFieldCurso, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))))
																																.addGroup(gl_panel.createSequentialGroup()
																																		.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
																																				.addComponent(lblNewLabelExpediente)
																																				.addComponent(lblNewLabel_9)
																																				.addComponent(lblSexo))
																																				.addPreferredGap(ComponentPlacement.RELATED)
																																				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
																																						.addGroup(gl_panel.createSequentialGroup()
																																								.addComponent(textFieldExpediente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																																								.addPreferredGap(ComponentPlacement.RELATED)
																																								.addComponent(lblTelf)
																																								.addPreferredGap(ComponentPlacement.RELATED)
																																								.addComponent(textFieldTelefono1, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
																																								.addGroup(gl_panel.createSequentialGroup()
																																										.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
																																												.addComponent(comboBoxSexo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																																												.addComponent(textFieldSip))
																																												.addPreferredGap(ComponentPlacement.RELATED)
																																												.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
																																														.addComponent(lblEmail, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																																														.addComponent(lblTelefono_1, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE))
																																														.addPreferredGap(ComponentPlacement.RELATED)
																																														.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
																																																.addComponent(textFieldEmail, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
																																																.addComponent(textFieldTelefono2, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))))))
																																																.addContainerGap())
				);

		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
										.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(lblNewLabel_7, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
												.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
														.addComponent(lblNia)
														.addComponent(textField_2)))
														.addGap(11))
														.addGroup(gl_panel.createSequentialGroup()
																.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
																		.addComponent(lblCurso, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																		.addComponent(textFieldCurso, Alignment.LEADING))
																		.addGap(15)))
																		.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
																				.addComponent(lblNewLabel)
																				.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																				.addComponent(lblNewLabel_8)
																				.addComponent(textFieldFechaNacimiento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																				.addPreferredGap(ComponentPlacement.RELATED)
																				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
																						.addComponent(lblApellidos)
																						.addComponent(textFieldApellido1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																						.addComponent(lblTipoDoc)
																						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																						.addPreferredGap(ComponentPlacement.RELATED)
																						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
																								.addComponent(lblApellido)
																								.addComponent(textFieldApellido2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																								.addComponent(lblDocumento)
																								.addComponent(textFieldDocumento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																								.addPreferredGap(ComponentPlacement.RELATED)
																								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
																										.addComponent(lblNewLabelExpediente)
																										.addComponent(textFieldExpediente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																										.addComponent(lblTelf)
																										.addComponent(textFieldTelefono1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																										.addPreferredGap(ComponentPlacement.RELATED)
																										.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
																												.addComponent(lblNewLabel_9)
																												.addComponent(textFieldSip, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																												.addComponent(lblTelefono_1)
																												.addComponent(textFieldTelefono2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																												.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
																														.addComponent(lblSexo)
																														.addComponent(textFieldEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																														.addComponent(lblEmail)
																														.addComponent(comboBoxSexo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																														.addPreferredGap(ComponentPlacement.RELATED)
																														.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
																																.addComponent(lblGrupo)
																																.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																																.addContainerGap())
				);

		panel.setLayout(gl_panel);
		principal.getBaseDeDatos().getFechasPeriodo(getNia(),tableFechas,listFechas);
		addMouseListener(this);
	}

	private ArrayList<PanelFamiliar> arrayListFamiliar = new ArrayList<>();

	public synchronized void addFamiliar(String parentesco, String nombre, String apellido1, String apellido2, String documento,
			String tipo_doc, String alumno, String id, String es)
	{
		PanelFamiliar panelFamiliar = new PanelFamiliar(parentesco, nombre, apellido1, apellido2, documento, tipo_doc, alumno, id, es);
		arrayListFamiliar.add(panelFamiliar);
		String tipo = "";

		if(parentesco == null)
			tipo="Otro";
		else if(parentesco.equalsIgnoreCase("1"))
			tipo = "Madre";
		else if(parentesco.equalsIgnoreCase("2"))
			tipo = "Padre";
		else
			tipo = "Otro";

		tabbedPane.addTab("" + tipo, new ImageIcon(getClass().getResource("/resource/a8.png")), panelFamiliar);
	}

	public void setFoto(BufferedImage bufferedImage)
	{
		jkPanel.setBackground(new ImageIcon(bufferedImage));
	}

	public String getNia ()
	{
		return textField_2.getText().toUpperCase();
	}

	public void setNia (String n)
	{
		textField_2.setText( n);
	}

	public String getNombres()
	{
		return textField.getText();
	}

	public void setNombres(String n)
	{
		textField.setText(n);
	}

	public String getApellido1 ()
	{
		return textFieldApellido1.getText();
	}

	public String getApellido2 ()
	{
		return textFieldApellido2.getText();
	}

	public void setApellido2 (String n)
	{
		textFieldApellido2.setText(n);
	}

	public String getDocumento ()
	{
		return textFieldDocumento.getText();
	}

	public void setDocumento (String n)
	{
		textFieldDocumento.setText(n);
	}

	public void setApellido1 (String n)
	{
		textFieldApellido1.setText(n);
	}

	public String getGrupo()
	{
		return arrayListgrupos.get(comboBox.getSelectedIndex());
	}

	private BufferedImage foto;
	private File fileFoto;

	public BufferedImage getFoto() 
	{
		return foto;
	}

	public File _getFoto()
	{
		return fileFoto;
	}

	public String getCuentaBancaria()
	{
		return textFieldCuenta.getText();
	}

	public void setCuentaBancaria(String j)
	{
		textFieldCuenta.setText(j);
	}

	public String getInformedico()
	{
		return textArea_2.getText();
	}

	public void setInformeMedico(String n)
	{
		textArea_2.setText(n);
	}

	public synchronized void loadDatos()
	{
		actualizar = true;
		btnNewButton.setText("Actualizar");
		btnNewButton.setIcon(new ImageIcon(getClass().getResource("/resource/update.png")));

		principal.getBaseDeDatos().getDatosAlumno(textField_2.getText(),getInstance());
		lblNewLabel_7.setBusy(false);
		lblNewLabel_7.setVisible(false);
		found = true;

		new Thread(
				new Runnable() 
				{
					@Override
					public void run() 
					{
						try 
						{
							/** SE AGREGA WHILE PARA MANTENER LA VENTANA ACTIVA MIENTRAS SE DIGITA
							 ** UN NUENO NUMERO DE DOCUMENTO **/
							while(principal.getScanned().length() == 0)
								Thread.sleep(2500);
							
							dispose();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}).start();
	}

	public Date getfechaNacimiento() 
	{
		try 
		{
			new Date(textFieldFechaNacimiento.getDateEditor().getDate().getTime());
		} 
		catch (Exception c) 
		{
			return new Date(new java.util.Date().getTime());
		}
		return new Date(textFieldFechaNacimiento.getDateEditor().getDate().getTime());
	}

	public void setFechaNacimiento(String fechaAlta)
	{
		SimpleDateFormat dateFormat = null;

		if(fechaAlta != null)
		{
			if(fechaAlta.indexOf("-") != -1)
				dateFormat = new SimpleDateFormat("yyy-MM-dd");
			else
				dateFormat = new SimpleDateFormat("dd/MM/yyy");

			try 
			{
				textFieldFechaNacimiento.setDate(dateFormat.parse(fechaAlta));

			} 
			catch (ParseException e) 
			{
				e.printStackTrace();
			}
		}
	}

	public String getTipoDoc()
	{
		if(comboBox_1.getSelectedItem().toString().equalsIgnoreCase("NIF"))
			return "N";
		else
			return "E";
	}

	public void setTipoDoc(String n)
	{
		if(n!=null)
		{
			if(n.equalsIgnoreCase("N"))
				comboBox_1.setSelectedIndex(0);
			else
				comboBox_1.setSelectedIndex(1);
		}
	}
	public String getMedicamentos()
	{
		return textField_10.getText();
	}

	public String getPersonaDeContacto()
	{
		return textField_9.getText();
	}

	public String getExpediente()
	{
		return textFieldExpediente.getText();
	}

	public void setExpediente(String n)
	{
		textFieldExpediente.setText(n);
	}

	public void setPersonaDeContacto(String n)
	{
		textField_9.setText(n);
	}

	public void setFoto(byte[] bytes)
	{
		jkPanel.setBackground(new ImageIcon(bytes));
		panel_4.updateUI();
	}

	public void setFoto(BufferedImage image, File fileFotox) 
	{
		this.foto = image;
		this.fileFoto = fileFotox;
		jkPanel.setBackground(new ImageIcon(image));
		jkPanel.repaint();
	}

	public void setGrupo(String codigoGrupo) 
	{
		for(int index=0; index<arrayListgrupos.size();index++)
		{
			if(arrayListgrupos.get(index).equalsIgnoreCase(codigoGrupo))
				comboBox.setSelectedIndex(index);
		}
	}

	public String getReferenciaMandato()
	{
		return textFieldReferenciaMandato.getText();
	}

	public void setReferenciaMandato(String ref)
	{
		textFieldReferenciaMandato.setText(ref);
	}

	public String getSexo()
	{
		return comboBoxSexo.getSelectedItem().toString();
	}

	public void setSexo(String sex)
	{
		if(sex == null)
			sex = "M";

		if(sex.equalsIgnoreCase("H"))
			comboBoxSexo.setSelectedIndex(0);
		else
			comboBoxSexo.setSelectedIndex(1);
	}

	public String getTelefono1()
	{
		return textFieldTelefono1.getText();
	}

	public void setTelefono1(String telefono)
	{
		textFieldTelefono1.setText(telefono);
	}

	public String getSIP()
	{
		return textFieldSip.getText();
	}

	public void setSIP(String telefono)
	{
		textFieldSip.setText(telefono);
	}

	public String getTelefono2()
	{
		return textFieldTelefono2.getText();
	}

	public void setTelefono2(String telefono)
	{
		textFieldTelefono2.setText(telefono);
	}

	public String getEmail()
	{
		return textFieldEmail.getText();
	}

	public void setEmail(String email)
	{
		textFieldEmail.setText(email);
	}

	public void setTutor(String tutorDeGrupo) { }

	private JTextField textFieldEmail;
	private JTextField textFieldTelefono1;
	private JDateChooser textFieldFechaNacimiento;
	private JTextField textFieldApellido2;
	private JTextField textFieldDocumento;
	private JTextField textFieldExpediente;
	private JTextField textFieldSip;
	private JTextField textFieldTelefono2;
	private JTextField textFieldCuenta;
	private JTextField textFieldCurso;
	private JTextField textFieldReferenciaMandato;

	public void setIDOtrosDatos(String id) { }

	public String getCurso()
	{
		return textFieldCurso.getText();
	}

	public void setCurso(String curso)
	{
		textFieldCurso.setText(curso);
	}

	public void setMedicamentos(String medicamentos) 
	{
		textField_10.setText(medicamentos);
	}

	public void setEnabledNIA(boolean b) 
	{
		textField_2.setEditable(b);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) { }

	@Override
	public void mouseEntered(MouseEvent arg0) { }

	@Override
	public void mouseExited(MouseEvent arg0) { }

	@Override
	public void mousePressed(MouseEvent arg0) { }

	@Override
	public void mouseReleased(MouseEvent arg0) 
	{
		principal.setNoCerrar(true);	
	}
}