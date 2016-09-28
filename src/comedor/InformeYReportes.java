package comedor;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import com.itextpdf.text.DocumentException;
import com.leyer.JKComboBox;
import com.leyer.JKCoreBar;
import com.leyer.JKMenu;
import com.leyer.JKTable;
import com.toedter.calendar.JDateChooser;

import comedor.pdfs.PDF_Faltas_Grupo;
import comedor.pdfs.PDF_Formato_Salir;
//import comedor.pdfs.PDF_Listado_NoUsuarios;
import comedor.pdfs.PDF_Listado_Usuarios;
import comedor.pdfs.PDF_ProfesorHorizontal;
import comedor.pdfs.PDF_ProfesorVertical;
import comedor.pdfs.Persona;
import comedor.pdfs.PDF_Listado;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JProgressBar;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JRadioButton;

public class InformeYReportes extends JInternalFrame implements Runnable
{
	private static final long serialVersionUID = 1L;
	private ComedorGUI principal;
	private JKTable jkTable;
	private JProgressBar progressBar;
	private JDateChooser textFieldX1;
	private JDateChooser textFieldX2;
	private JKCoreBar coreBar;
	private JKComboBox comboBox;
	private ArrayList<String> arrayList;
	private JKTable table;
	private JKMenu jkMenu;
	private JRadioButton check1;
	private JRadioButton check2;
	private JRadioButton checkx1;
	private JRadioButton checkx2;

	public InformeYReportes(final ComedorGUI comedorGUI) 
	{
		setTitle("Informe y Reportes");
		setBounds(10, 10, 1023, 511);
		this.principal = comedorGUI;

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(InformeYReportes.class.getResource("/resource/pdf.png")));

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 976, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblNewLabel)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(panel, GroupLayout.DEFAULT_SIZE, 727, Short.MAX_VALUE)))
										.addContainerGap())
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
								.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
								.addContainerGap())
				);

		JButton btnNewButton_2 = new JButton("Cerrar");
		btnNewButton_2.setIcon(new ImageIcon(InformeYReportes.class.getResource("/resource/close1.png")));
		btnNewButton_2.setPreferredSize(new Dimension(180,26));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		panel_2.add(btnNewButton_2);
		panel.setLayout(new BorderLayout(0, 0));

		jkTable = new JKTable();
		jkTable.addColumn("Codigo");
		jkTable.addColumn("Nombre de Grupo");
		//		jkTable.addColumn("Codigo");
		jkTable.addColumn("Tutor");

		jkTable.getColumn("Codigo").setPreferredWidth(20);
		jkTable.getColumn("Codigo").setWidth(20);

		jkTable.getColumn("Nombre de Grupo").setPreferredWidth(190);
		jkTable.getColumn("Nombre de Grupo").setWidth(190);

		jkTable.getColumn("Tutor").setPreferredWidth(320);
		jkTable.getColumn("Tutor").setWidth(320);


		JScrollPane scrollPane = new JScrollPane(jkTable);
		panel.add(scrollPane, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_1, BorderLayout.NORTH);

		JButton btnNewButton = new JButton("Imprimir Orla de Grupo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jkTable.getSelectedRow()!=-1){
					final String codigoGrupo=jkTable.getValueAt(jkTable.getSelectedRow(), 0).toString();
					final String nombre= jkTable.getValueAt(jkTable.getSelectedRow(), 1).toString();
					new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							progressBar.setIndeterminate(true);
							progressBar.setString("Procesando...");
							String cursoActual=principal.getBaseDeDatos().getCursoActual();
							ArrayList<Persona> array= principal.getBaseDeDatos().getAlumnos(codigoGrupo,cursoActual);
							try {
								new PDF_Listado().crearPDF5(principal, principal.getBaseDeDatos().getCursoActual(), codigoGrupo, nombre, principal.getBaseDeDatos().getNombreTutorDeGrupo(codigoGrupo), array,false);
								progressBar.setIndeterminate(false);
								progressBar.setString("Finalizado!.");
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								//								ex.printStackTrace();
								progressBar.setIndeterminate(false);
								progressBar.setString("Finalizado.");
								JOptionPane.showMessageDialog(principal, e1.getMessage(),e1.getLocalizedMessage(),JOptionPane.ERROR_MESSAGE);

							}

						}
					}).start();

				}else{
					JOptionPane.showMessageDialog(principal, "Debe seleccionar un Grupo.","Ningun grupo seleccionado",JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(InformeYReportes.class.getResource("/resource/Pdf-icon1.png")));

		JButton btnNewButton_1 = new JButton("Imprimir por Seleccion\r\n");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InformeSeleccion(comedorGUI).setVisible(true);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(InformeYReportes.class.getResource("/resource/Pdf-icon1.png")));

		JButton btnImprimirOrlasProfesores = new JButton("Imprimir Orlas Profesores");
		btnImprimirOrlasProfesores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						progressBar.setIndeterminate(true);
						progressBar.setString("Procesando...");
						String cursoActual=principal.getBaseDeDatos().getCursoActual();
						ArrayList<Persona> a = principal.getBaseDeDatos().getProfesores(cursoActual);
						try {
							new PDF_Listado().crearPDF5(principal, principal.getBaseDeDatos().getCursoActual(), "", "", "ORLA DE PROFESORES", a,true);
							progressBar.setIndeterminate(false);
							progressBar.setString("Finalizado!.");
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							//							e1.printStackTrace();
							progressBar.setIndeterminate(false);
							progressBar.setString("Finalizado.");
							JOptionPane.showMessageDialog(principal, e1.getMessage(),e1.getLocalizedMessage(),JOptionPane.ERROR_MESSAGE);
						}
					}
				}).start();

			}
		});
		btnImprimirOrlasProfesores.setIcon(new ImageIcon(InformeYReportes.class.getResource("/resource/Pdf-icon1.png")));

		JButton btnImprimirOrlasPas = new JButton("Imprimir Orlas P.A.S");
		btnImprimirOrlasPas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						progressBar.setIndeterminate(true);
						progressBar.setString("Procesando...");
						String cursoActual=principal.getBaseDeDatos().getCursoActual();

						ArrayList<Persona> a = principal.getBaseDeDatos().getPas(cursoActual);
						try {
							new PDF_Listado().crearPDF5(principal, principal.getBaseDeDatos().getCursoActual(), "", "", "ORLA DE PERSONAL DE ADMOM. Y SERVICIOS", a,true);
							progressBar.setIndeterminate(false);
							progressBar.setString("Finalizado!.");
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							//							e1.printStackTrace();
							progressBar.setIndeterminate(false);
							progressBar.setString("Finalizado.");
							JOptionPane.showMessageDialog(principal, e1.getMessage(),e1.getLocalizedMessage(),JOptionPane.ERROR_MESSAGE);
						}
					}
				}).start();

			}
		});
		btnImprimirOrlasPas.setIcon(new ImageIcon(InformeYReportes.class.getResource("/resource/Pdf-icon1.png")));

		JButton btnImprimirInformeTutor = new JButton("Imprimir Informe Tutor\r\n");
		btnImprimirInformeTutor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jkTable.getSelectedRow()!=-1){
					new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							final String codigoGrupo=jkTable.getValueAt(jkTable.getSelectedRow(), 0).toString();
							jkTable.getValueAt(jkTable.getSelectedRow(), 1).toString();
							String bigList[] = { "Informe Reduc. Tutor", "Informe Ancho. Tutor"};
							String selected = JOptionPane.showInputDialog(principal, "Seleccionar",
									"Seleccionar", JOptionPane.QUESTION_MESSAGE,null, bigList, "Titan").toString();
							if(selected!=null){
								if(selected.equalsIgnoreCase("Informe Ancho. Tutor")){
									progressBar.setIndeterminate(true);
									progressBar.setString("Procesando...");
									String cursoActual=principal.getBaseDeDatos().getCursoActual();
									ArrayList<Persona> array = principal.getBaseDeDatos().getAlumnos(codigoGrupo,cursoActual);
									try {
										new PDF_ProfesorHorizontal(codigoGrupo, principal.getBaseDeDatos().getNombreTutorDeGrupo(codigoGrupo), array,principal);
										progressBar.setIndeterminate(false);
										progressBar.setString("Finalizado.");
									} catch (MalformedURLException e1) {
										// TODO Auto-generated catch block
										progressBar.setIndeterminate(false);
										progressBar.setString("Finalizado.");
										JOptionPane.showMessageDialog(principal, e1.getMessage(),e1.getLocalizedMessage(),JOptionPane.ERROR_MESSAGE);
									} catch (DocumentException e1) {
										progressBar.setIndeterminate(false);
										progressBar.setString("Finalizado.");
										// TODO Auto-generated catch block
										JOptionPane.showMessageDialog(principal, e1.getMessage(),e1.getLocalizedMessage(),JOptionPane.ERROR_MESSAGE);
									} catch (IOException e1) {
										progressBar.setIndeterminate(false);
										progressBar.setString("Finalizado.");
										// TODO Auto-generated catch block
										JOptionPane.showMessageDialog(principal, e1.getMessage(),e1.getLocalizedMessage(),JOptionPane.ERROR_MESSAGE);
									}
								}else{


									progressBar.setIndeterminate(true);
									progressBar.setString("Procesando...");
									String cursoActual=principal.getBaseDeDatos().getCursoActual();
									ArrayList<Persona> array = principal.getBaseDeDatos().getAlumnos(codigoGrupo,cursoActual);
									try {
										new PDF_ProfesorVertical(codigoGrupo, principal.getBaseDeDatos().getNombreTutorDeGrupo(codigoGrupo), array,principal);
									} catch (FileNotFoundException
											| DocumentException e1) {
										// TODO Auto-generated catch block
										progressBar.setIndeterminate(false);
										progressBar.setString("Finalizado.");
										JOptionPane.showMessageDialog(principal, e1.getMessage(),e1.getLocalizedMessage(),JOptionPane.ERROR_MESSAGE);

									}
									progressBar.setIndeterminate(false);
									progressBar.setString("Finalizado.");





								}
							}
						}
					}).start();

				}else{

					JOptionPane.showMessageDialog(principal, "Debe seleccionar un grupo.","Seleccion nula",JOptionPane.WARNING_MESSAGE);
					return;
				}



			}
		});
		btnImprimirInformeTutor.setIcon(new ImageIcon(InformeYReportes.class.getResource("/resource/Pdf-icon1.png")));

		JButton btnImprimirOrlaGeneral = new JButton("Imprimir Orla General");
		btnImprimirOrlaGeneral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				new Thread(
						new Runnable() 
						{	
							@Override
							public void run() 
							{
								progressBar.setIndeterminate(true);
								progressBar.setString("Procesando...");
								String cursoActual = principal.getBaseDeDatos().getCursoActual();

								ArrayList<Persona> array = principal.getBaseDeDatos().getTodosLosAlumnos(cursoActual);

								try 
								{
									new PDF_Listado().crearPDF51(principal, principal.getBaseDeDatos().getCursoActual(), " ", " ", "Orla General", array, true);
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									//									e.printStackTrace();

									// TODO Auto-generated catch block
									progressBar.setIndeterminate(false);
									progressBar.setString("Finalizado.");
									JOptionPane.showMessageDialog(principal, e1.getMessage(),e1.getLocalizedMessage(),JOptionPane.ERROR_MESSAGE);

								}
								progressBar.setIndeterminate(false);
								progressBar.setString("Finalizado.");

							}
						}).start();




			}
		});
		btnImprimirOrlaGeneral.setIcon(new ImageIcon(InformeYReportes.class.getResource("/resource/Pdf-icon1.png")));

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informes por Fechas", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JButton btnListadoDeUsuarios = new JButton("Listado de Usuarios");
		btnListadoDeUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String bigList[] = { "Usuarios Comedor", "No Usuarios"};

				final String selected = JOptionPane.showInputDialog(principal, "Seleccionar",
						"Seleccionar", JOptionPane.QUESTION_MESSAGE,null, bigList, "Titan").toString();

				if(selected!=null){



					String bigList1[] = { "3 Dias", "2 Dias"};

					String selected1 = JOptionPane.showInputDialog(principal, "Seleccionar",
							"Seleccionar", JOptionPane.QUESTION_MESSAGE,null, bigList1, "Titan").toString();


					final String dias= selected1.split(" ")[0];
					if(selected.equalsIgnoreCase("Usuarios Comedor")){
						new Thread(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								progressBar.setIndeterminate(true);
								progressBar.setString("Procesando...");

								ArrayList<Persona> x = principal.getBaseDeDatos().getUsuariosComedor(principal.getBaseDeDatos().getCursoActual(),selected,dias);

								try {
									new PDF_Listado_Usuarios(principal, x);
								} catch (FileNotFoundException e) {
									e.printStackTrace();
									JOptionPane.showMessageDialog(principal, e.getMessage(),e.getLocalizedMessage(),JOptionPane.ERROR_MESSAGE);
								} catch (DocumentException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									JOptionPane.showMessageDialog(principal, e.getMessage(),e.getLocalizedMessage(),JOptionPane.ERROR_MESSAGE);

								}

								progressBar.setIndeterminate(false);
								progressBar.setString("Finalizado.");
							}
						}).start();
						//					}else{
						////						PDF_Listado_NoUsuarios
						//						new Thread(new Runnable() {
						//							
						//							@Override
						//							public void run() {
						//								// TODO Auto-generated method stub
						//								progressBar.setIndeterminate(true);
						//								progressBar.setString("Procesando...");
						//						
						//									ArrayList<Persona> x = principal.getBaseDeDatos().getNoUsuariosComedor(principal.getBaseDeDatos().getCursoActual());
						//									
						//									try {
						//										new PDF_Listado_NoUsuarios(principal, x);
						//									} catch (FileNotFoundException e) {
						//										e.printStackTrace();
						//										JOptionPane.showMessageDialog(principal, e.getMessage(),e.getLocalizedMessage(),JOptionPane.ERROR_MESSAGE);
						//									} catch (DocumentException e) {
						//										// TODO Auto-generated catch block
						//										e.printStackTrace();
						//										JOptionPane.showMessageDialog(principal, e.getMessage(),e.getLocalizedMessage(),JOptionPane.ERROR_MESSAGE);
						//				
						//									}
						//									
						//									progressBar.setIndeterminate(false);
						//									progressBar.setString("Finalizado.");
						//							}
						//					    }).start();
						//						
						//						
						//						


					}
				}
			}
		});
		btnListadoDeUsuarios.setIcon(new ImageIcon(InformeYReportes.class.getResource("/resource/Pdf-icon1.png")));

		JButton btnBalancesEconmicosMensuales = new JButton("Balances Econ\u00F3micos Mensuales");
		btnBalancesEconmicosMensuales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String bigList[] = { "01-ENERO", "02-FEBRERO",
						"03-MARZO", "04-ABRIL", "05-MAYO",
						"06-JUNIO", "07-JULIO", "08-AGOSTO","09-SEPTIEMBRE","10-OCTUBRE","11-NOVIEMBRE","12-DICIEMBRE"};
				final String selected = JOptionPane.showInputDialog(principal, "Seleccionar",
						"Seleccionar", JOptionPane.QUESTION_MESSAGE,null, bigList, "Titan").toString();

				String bigList1[] = { "Alumnos", "Profesores"};
				final String selected1 = JOptionPane.showInputDialog(principal, "Seleccionar",
						"Seleccionar", JOptionPane.QUESTION_MESSAGE,null, bigList1, "Titan").toString();
				if(selected1.equalsIgnoreCase("Alumnos")){
					new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub

							progressBar.setIndeterminate(true);
							progressBar.setString("Procesando...");
							//						principal.getBaseDeDatos().getAlumnosConFaltas(textFieldX1.getDate(),textFieldX2.getDate());

							principal.getBaseDeDatos().getBalancesEconomicos(selected);

							progressBar.setIndeterminate(false);
							progressBar.setString("Finalizado.");

						}
					}).start();
				}else{
					new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub

							progressBar.setIndeterminate(true);
							progressBar.setString("Procesando...");
							//						principal.getBaseDeDatos().getAlumnosConFaltas(textFieldX1.getDate(),textFieldX2.getDate());

							principal.getBaseDeDatos().getBalancesEconomicosProfesores(selected);

							progressBar.setIndeterminate(false);
							progressBar.setString("Finalizado.");

						}
					}).start();
				}


			}
		});
		btnBalancesEconmicosMensuales.setIcon(new ImageIcon(InformeYReportes.class.getResource("/resource/Pdf-icon1.png")));

		JButton btnListadoDePendientes = new JButton("Listado de Pendientes por Cobro");
		btnListadoDePendientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String bigList[] = { "01-ENERO", "02-FEBRERO",
						"03-MARZO", "04-ABRIL", "05-MAYO",
						"06-JUNIO", "07-JULIO", "08-AGOSTO","09-SEPTIEMBRE","10-OCTUBRE","11-NOVIEMBRE","12-DICIEMBRE"};
				final String selectedMes = JOptionPane.showInputDialog(principal, "Seleccionar",
						"Seleccionar", JOptionPane.QUESTION_MESSAGE,null, bigList, "Titan").toString();


				String bigList1[] = { "Alumnos", "Profesores"};
				final String selected1 = JOptionPane.showInputDialog(principal, "Seleccionar",
						"Seleccionar", JOptionPane.QUESTION_MESSAGE,null, bigList1, "Titan").toString();
				if(selected1.equalsIgnoreCase("Alumnos")){
					new Thread(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							progressBar.setIndeterminate(true);
							progressBar.setString("Procesando...");
							//						principal.getBaseDeDatos().getAlumnosConFaltas(textFieldX1.getDate(),textFieldX2.getDate());
							principal.getBaseDeDatos().getPendientesCobro(selectedMes);
							progressBar.setIndeterminate(false);
							progressBar.setString("Finalizado.");
						}
					}).start();
				}else{
					new Thread(new Runnable() {
						@Override
						public void run() {
							progressBar.setIndeterminate(true);
							progressBar.setString("Procesando...");
							//						principal.getBaseDeDatos().getAlumnosConFaltas(textFieldX1.getDate(),textFieldX2.getDate());
							principal.getBaseDeDatos().getPendientesCobroProfesores(selectedMes);
							progressBar.setIndeterminate(false);
							progressBar.setString("Finalizado.");
						}
					}).start();
				}
			}
		});
		btnListadoDePendientes.setIcon(new ImageIcon(InformeYReportes.class.getResource("/resource/Pdf-icon1.png")));

		JButton btnFormatoSalir = new JButton("Formato Salir");
		btnFormatoSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String bigList[] = { "01-ENERO", "02-FEBRERO",
						"03-MARZO", "04-ABRIL", "05-MAYO",
						"06-JUNIO", "07-JULIO", "08-AGOSTO","09-SEPTIEMBRE","10-OCTUBRE","11-NOVIEMBRE","12-DICIEMBRE"};
				final String selectedMes = JOptionPane.showInputDialog(principal, "Seleccionar",
						"Seleccionar", JOptionPane.QUESTION_MESSAGE,null, bigList, "Titan").toString();
				if(selectedMes!=null){
					try {
						new PDF_Formato_Salir(principal, selectedMes.split("-")[1], 
								selectedMes.split("-")[0],principal.getBaseDeDatos().getNoUsuariosComedor2(principal.getBaseDeDatos().getCursoActual(),selectedMes.split("-")[0]));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (DocumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});
		btnFormatoSalir.setIcon(new ImageIcon(InformeYReportes.class.getResource("/resource/Pdf-icon1.png")));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
						.addGap(5)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
								.addGroup(gl_panel_1.createSequentialGroup()
										.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(btnListadoDeUsuarios, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(btnImprimirOrlasPas, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
														.addGroup(gl_panel_1.createSequentialGroup()
																.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
																		.addComponent(btnImprimirInformeTutor, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
																		.addComponent(btnNewButton_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
																		.addGap(2)
																		.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
																				.addComponent(btnImprimirOrlaGeneral, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																				.addComponent(btnImprimirOrlasProfesores, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
																				.addPreferredGap(ComponentPlacement.RELATED)
																				.addComponent(btnFormatoSalir, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE))
																				.addGroup(gl_panel_1.createSequentialGroup()
																						.addComponent(btnBalancesEconmicosMensuales, GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
																						.addPreferredGap(ComponentPlacement.UNRELATED)
																						.addComponent(btnListadoDePendientes, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
																						.addPreferredGap(ComponentPlacement.RELATED)))
																						.addGap(40))))
				);
		gl_panel_1.setVerticalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
						.addGap(5)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton)
								.addComponent(btnNewButton_1)
								.addComponent(btnImprimirOrlasProfesores))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnImprimirOrlasPas)
										.addComponent(btnImprimirInformeTutor)
										.addComponent(btnImprimirOrlaGeneral)
										.addComponent(btnFormatoSalir))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
												.addComponent(btnListadoDeUsuarios)
												.addComponent(btnBalancesEconmicosMensuales)
												.addComponent(btnListadoDePendientes))
												.addPreferredGap(ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
												.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
												.addContainerGap())
				);

		JLabel lblFechaInicio = new JLabel("Fecha Inicio:");

		JLabel lblFechaFin = new JLabel("Fecha Fin:");

		textFieldX1 = new JDateChooser();
		//		textFieldX1.setColumns(10);

		textFieldX2 = new JDateChooser();
		//		textFieldX2.setColumns(10);
		textFieldX1.getDateEditor().setEnabled(false);
		textFieldX2.getDateEditor().setEnabled(false);
		JButton btnInformeDeFaltas = new JButton("Informe de Faltas");
		btnInformeDeFaltas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						try{
							Date da1 = textFieldX1.getDate();
							if(da1==null){
								JOptionPane.showMessageDialog(principal, "Debe seleccionar una fecha de inicio!","Selecciona fechas",JOptionPane.WARNING_MESSAGE);
								return;
							}
							Date da2 = textFieldX2.getDate();
							if(da2==null){
								JOptionPane.showMessageDialog(principal, "Debe seleccionar una fecha de fin!","Selecciona fechas",JOptionPane.WARNING_MESSAGE);
								return;
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						// TODO Auto-generated method stub
						progressBar.setIndeterminate(true);
						progressBar.setString("Procesando...");
						principal.getBaseDeDatos().getAlumnosConFaltas(textFieldX1.getDate(),textFieldX2.getDate());
						progressBar.setIndeterminate(false);
						progressBar.setString("Finalizado.");
					}
				}).start();

			}
		});
		btnInformeDeFaltas.setIcon(new ImageIcon(InformeYReportes.class.getResource("/resource/Pdf-icon1.png")));

		JButton btnInformeDeAsistencias = new JButton("Informe de Asistencias");
		btnInformeDeAsistencias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						try{
							Date da1 = textFieldX1.getDate();
							if(da1==null){
								JOptionPane.showMessageDialog(principal, "Debe seleccionar una fecha de inicio!","Selecciona fechas",JOptionPane.WARNING_MESSAGE);
								return;
							}
							Date da2 = textFieldX2.getDate();
							if(da2==null){
								JOptionPane.showMessageDialog(principal, "Debe seleccionar una fecha de fin!","Selecciona fechas",JOptionPane.WARNING_MESSAGE);
								return;
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						// TODO Auto-generated method stub
						progressBar.setIndeterminate(true);
						progressBar.setString("Procesando...");
						principal.getBaseDeDatos().getAsistenciaAlumnos(textFieldX1.getDate(),textFieldX2.getDate());
						progressBar.setIndeterminate(false);
						progressBar.setString("Finalizado.");
					}
				}).start();
			}
		});
		btnInformeDeAsistencias.setIcon(new ImageIcon(InformeYReportes.class.getResource("/resource/Pdf-icon1.png")));

		comboBox = new JKComboBox();
		arrayList=new ArrayList<>();

		principal.getBaseDeDatos().getCodigosGroups(comboBox, arrayList);
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub


				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try{
							Date da1 = textFieldX1.getDate();
							if(da1==null){
								JOptionPane.showMessageDialog(principal, "Debe seleccionar una fecha de inicio!","Selecciona fechas",JOptionPane.WARNING_MESSAGE);
								return;
							}
							Date da2 = textFieldX2.getDate();
							if(da2==null){
								JOptionPane.showMessageDialog(principal, "Debe seleccionar una fecha de fin!","Selecciona fechas",JOptionPane.WARNING_MESSAGE);
								return;
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						progressBar.setIndeterminate(true);
						progressBar.setString("Procesando...");

						if(checkx1.isSelected()){
							principal.getBaseDeDatos().getFaltasDeGrupo(textFieldX1.getDate(),textFieldX2.getDate(),comboBox.getSelectedItem().toString());
						}else{
							ArrayList<ArrayList<String>> ar = principal.getBaseDeDatos().getFaltasGrupo(comboBox.getSelectedItem().toString(),textFieldX1.getDate(),textFieldX2.getDate());
							try {
								new PDF_Faltas_Grupo(textFieldX1.getDate(), textFieldX2.getDate(),ar,principal);

							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (DocumentException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						progressBar.setIndeterminate(false);
						progressBar.setString("Finalizado.");
					}
				}).start();

			}
		});

		coreBar = new JKCoreBar(JKCoreBar.MODE_1);
		//		textField.setColumns(10);

		jkMenu = new JKMenu("Alumnos", JKCoreBar.MODE_1);
		jkMenu.setPreferredSize(new Dimension(220,24));
		coreBar.add(jkMenu);

		JPanel jpanel=new JPanel(new BorderLayout());
		JPanel panel2=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel jLabel=new JLabel("NIA/Nombre: ");
		panel2.add(jLabel);
		final JTextField textField=new JTextField(35);
		panel2.add(textField);
		textField.addKeyListener(new KeyListener(){

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				table.search(textField.getText().toUpperCase());
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		table=new JKTable();

		table.addColumn("Apellidos y Nombres");
		table.addColumn("NIA");
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				jkMenu.setPopupMenuVisible(false);
				try{
					//					tableFechas.clearTable();
					//					listFechas.clear();
				}catch(Exception e){

				}

				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try{
							Date da1 = textFieldX1.getDate();
							if(da1==null){
								JOptionPane.showMessageDialog(principal, "Debe seleccionar una fecha de inicio!","Selecciona fechas",JOptionPane.WARNING_MESSAGE);
								return;
							}
							Date da2 = textFieldX2.getDate();
							if(da2==null){
								JOptionPane.showMessageDialog(principal, "Debe seleccionar una fecha de fin!","Selecciona fechas",JOptionPane.WARNING_MESSAGE);
								return;
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						progressBar.setIndeterminate(true);
						progressBar.setString("Procesando...");
						String nia=table.getValueAt(table.getSelectedRow(), 1).toString();
						if(check1.isSelected()){
							principal.getBaseDeDatos().getFaltasPorAlumno(textFieldX1.getDate(),textFieldX2.getDate(),nia);
						}
						if(check2.isSelected()){
							principal.getBaseDeDatos().getIncidenciasPorAlumno(textFieldX1.getDate(),textFieldX2.getDate(),nia);
						}
						progressBar.setIndeterminate(false);
						progressBar.setString("Finalizado.");
					}
				}).start();
				//				principal.getBaseDeDatos().getFechasPeriodo(table.getValueAt(table.getSelectedRow(), 1).toString(),tableFechas,listFechas);
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


			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		table.setFont(new Font("arial",Font.BOLD,12));
		JScrollPane scrollPane1=new JScrollPane(table);

		jpanel.add(panel2, BorderLayout.NORTH);
		jpanel.add(scrollPane1,BorderLayout.CENTER);
		jkMenu.add(jpanel);
		jkMenu.setFont(new Font("arial",Font.BOLD,12));
		coreBar.add(jkMenu);

		check1 = new JRadioButton("Faltas por Alumno:");

		check2 = new JRadioButton("Incidencias por Alumno:");

		ButtonGroup buttonGroup= new ButtonGroup();
		buttonGroup.add(check1);
		buttonGroup.add(check2);
		check1.setSelected(true);

		checkx1 = new JRadioButton("Faltas por Grupo(1er Modo)");

		checkx2 = new JRadioButton("Faltas por Grupo(2do Modo)");
		ButtonGroup buttonGroup2= new ButtonGroup();
		buttonGroup2.add(checkx1);
		buttonGroup2.add(checkx2);

		JButton btnInformeDeAsistencias_1 = new JButton("Informe de Asistencias con Ticked");
		btnInformeDeAsistencias_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {


				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try{
							Date da1 = textFieldX1.getDate();
							if(da1==null){
								JOptionPane.showMessageDialog(principal, "Debe seleccionar una fecha de inicio!","Selecciona fechas",JOptionPane.WARNING_MESSAGE);
								return;
							}
							Date da2 = textFieldX2.getDate();
							if(da2==null){
								JOptionPane.showMessageDialog(principal, "Debe seleccionar una fecha de fin!","Selecciona fechas",JOptionPane.WARNING_MESSAGE);
								return;
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						progressBar.setIndeterminate(true);
						progressBar.setString("Procesando...");


						principal.getBaseDeDatos().getAsistenciaAlumnosTickeds(textFieldX1.getDate(), textFieldX2.getDate());



						progressBar.setIndeterminate(false);
						progressBar.setString("Completado.");

					}
				}).start();

			}
		});
		btnInformeDeAsistencias_1.setIcon(new ImageIcon(InformeYReportes.class.getResource("/resource/Pdf-icon1.png")));

		JButton btnInformeDeAsistencias_2 = new JButton("Informe de Asistencias por Grupo");
		btnInformeDeAsistencias_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(jkTable.getSelectedRow()!=-1){
					final String codigoGrupo=jkTable.getValueAt(jkTable.getSelectedRow(), 0).toString();
					new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							try{
								Date da1 = textFieldX1.getDate();
								if(da1==null){
									JOptionPane.showMessageDialog(principal, "Debe seleccionar una fecha de inicio!","Selecciona fechas",JOptionPane.WARNING_MESSAGE);
									return;
								}
								Date da2 = textFieldX2.getDate();
								if(da2==null){
									JOptionPane.showMessageDialog(principal, "Debe seleccionar una fecha de fin!","Selecciona fechas",JOptionPane.WARNING_MESSAGE);
									return;
								}
							}catch(Exception e){
								e.printStackTrace();
							}
							progressBar.setIndeterminate(true);
							progressBar.setString("Procesando...");


							principal.getBaseDeDatos().getAsistenciaAlumnos(textFieldX1.getDate(), textFieldX2.getDate(),codigoGrupo);



							progressBar.setIndeterminate(false);
							progressBar.setString("Completado.");

						}
					}).start();
				}else{
					JOptionPane.showMessageDialog(principal, "Debe seleccionar un Grupo","Seleccion",JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
		});
		btnInformeDeAsistencias_2.setIcon(new ImageIcon(InformeYReportes.class.getResource("/resource/Pdf-icon1.png")));
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
				gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_4.createSequentialGroup()
										.addComponent(btnInformeDeAsistencias_1, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnInformeDeAsistencias_2, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel_4.createSequentialGroup()
												.addGroup(gl_panel_4.createParallelGroup(Alignment.TRAILING, false)
														.addComponent(btnInformeDeAsistencias, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(btnInformeDeFaltas, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addGroup(gl_panel_4.createSequentialGroup()
																.addGroup(gl_panel_4.createParallelGroup(Alignment.TRAILING)
																		.addComponent(lblFechaInicio)
																		.addComponent(lblFechaFin, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING, false)
																				.addComponent(textFieldX2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																				.addComponent(textFieldX1, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))))
																				.addPreferredGap(ComponentPlacement.RELATED)
																				.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
																						.addComponent(comboBox, 0, 403, Short.MAX_VALUE)
																						.addComponent(coreBar, GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
																						.addGroup(gl_panel_4.createSequentialGroup()
																								.addComponent(check1)
																								.addPreferredGap(ComponentPlacement.RELATED)
																								.addComponent(check2, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE))
																								.addGroup(gl_panel_4.createSequentialGroup()
																										.addComponent(checkx1)
																										.addGap(18)
																										.addComponent(checkx2, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)))))
																										.addContainerGap())
				);
		gl_panel_4.setVerticalGroup(
				gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_4.createSequentialGroup()
										.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panel_4.createSequentialGroup()
														.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
																.addComponent(lblFechaInicio)
																.addComponent(textFieldX1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																.addPreferredGap(ComponentPlacement.RELATED)
																.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
																		.addComponent(lblFechaFin)
																		.addComponent(textFieldX2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
																		.addGroup(gl_panel_4.createSequentialGroup()
																				.addGap(25)
																				.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
																				.addPreferredGap(ComponentPlacement.RELATED)
																				.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
																						.addComponent(btnInformeDeFaltas)
																						.addComponent(check1)
																						.addComponent(check2))
																						.addPreferredGap(ComponentPlacement.RELATED)
																						.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
																								.addComponent(btnInformeDeAsistencias)
																								.addComponent(coreBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
																								.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
																										.addComponent(checkx1)
																										.addComponent(checkx2)))
																										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																										.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
																												.addComponent(btnInformeDeAsistencias_1)
																												.addComponent(btnInformeDeAsistencias_2))
																												.addContainerGap())
				);
		panel_4.setLayout(gl_panel_4);
		panel_1.setLayout(gl_panel_1);

		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));

		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		panel_3.add(progressBar);
		progressBar.setFont(new Font("arial",Font.BOLD,12));
		new Thread(this).start();
		getContentPane().setLayout(groupLayout);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String cursoActual=principal.getBaseDeDatos().getCursoActual();
		principal.getBaseDeDatos().getGroups(jkTable,new JLabel(),cursoActual);

		//		String cursoActual=principal.getBaseDeDatos().getCursoActual();
		ArrayList<Persona> xz = principal.getBaseDeDatos().getTodosLosAlumnos(cursoActual);
		for(int index=0;index<xz.size();index++){
			table.addRow(xz.get(index).getNombreCompleto(),xz.get(index).getNia());	
		}	


	}
}
