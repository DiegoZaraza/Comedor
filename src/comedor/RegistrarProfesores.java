package comedor;

import itaca.Profes;

import java.awt.Graphics;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFileChooser;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.leyer.JKComboBox;
import com.leyer.JKPanel;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.ImageIcon;

import org.jdesktop.xswingx.PromptSupport;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.SwingConstants;

public class RegistrarProfesores extends JInternalFrame 
{
	private static final long serialVersionUID = 1L;
	private ComedorGUI principal;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JKComboBox comboBoxTipoDoc;
	private JKComboBox comboBox_1;
	private JTextField textField_4;
	private Thread thread;
	private JTextField textField_5;
	private File fileFoto;

	public RegistrarProfesores getInstance()
	{
		return this;
	}

	private JKPanel jkPanel;
	private JTextArea textArea_1;

	public String getCuentaBancaria()
	{
		return textField_5.getText();
	}

	public void setCuentaBancaria(String n)
	{
		textField_5.setText(n);
	}

	public String getAlergias()
	{
		return textArea_1.getText();
	}

	public void setAlergias(String nombre)
	{
		textArea_1.setText(nombre);
	}

	public String getEmail()
	{
		return textField_4.getText();
	}

	public void setEmail(String n)
	{
		textField_4.setText(n);
	}

	private boolean actualizar = false;

	public String getSexo()
	{
		return comboBox_1.getSelectedItem().toString();
	}

	public void setSexo(String s)
	{
		if(s != null)
			if(s.equalsIgnoreCase("M"))
				comboBox_1.setSelectedIndex(0);
			else
				comboBox_1.setSelectedIndex(1);
	}

	public String getApellido2()
	{
		return textField_3.getText();
	}

	public void setApellido2(String a)
	{
		textField_3.setText(a);
	}

	public String getApellido1()
	{
		return textField_2.getText();
	}

	public void setApellido1(String a)
	{
		textField_2.setText(a);
	}

	public String getTipoDoc()
	{
		if(comboBoxTipoDoc.getSelectedItem().toString().equalsIgnoreCase("NIF"))
			return "N";
		else
			return "E";
	}

	public void setTipoDoc(String t)
	{
		if(t != null)
		{
			if(t.equalsIgnoreCase("N"))
				comboBoxTipoDoc.setSelectedIndex(0);
			else
				comboBoxTipoDoc.setSelectedIndex(1);
		}
	}

	private boolean found;

	public String getDocumento()
	{
		return textField.getText();
	}

	public void setDocumento(String documento)
	{
		textField.setText(documento);
	}

	public String getNombre()
	{
		return textField_1.getText();
	}

	public void setNombre(String nombre)
	{
		textField_1.setText(nombre);
	}

	private BufferedImage foto;
	private JButton btnNewButton_3;
	private JPanel panel_3;
	private JButton btnNewButton;

	public BufferedImage getFoto() 
	{
		return foto;
	}

	public void clear()
	{
		textArea_1.setText("");
		textField.setText("");
		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
		textField_4.setText("");
		textField_5.setText("");
		panel_3.removeAll();
		jkPanel = new JKPanel();
		panel_3.add(jkPanel);
		fileFoto = null;
		panel_3.repaint();
		jkPanel.repaint();
		jkPanel.validate();
		panel_3.updateUI();
	}

	public RegistrarProfesores(ComedorGUI pr) 
	{
		setTitle("Registrar o Actualizar Datos de Profesores");
		setBounds(10, 10, 760, 506);

		this.principal = pr;

		JPanel panel = new JPanel()
		{
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) 
			{
				setOpaque(false);
				g.drawImage(new ImageIcon(getClass().getResource("/resource/e2.jpg")).getImage(), 0, 0, getWidth(), getHeight(), null);
			}
		};

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setLayout(new GridLayout(0, 3, 0, 0));

		JButton btnNewButton_1 = new JButton("Cerrar");
		btnNewButton_1.setIcon(new ImageIcon(RegistrarProfesores.class.getResource("/resource/close1.png")));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.addActionListener(
				new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						dispose();
					}
				});

		btnNewButton_1.setIcon(new ImageIcon(RegistrarProfesores.class.getResource("/resource/close1.png")));
		panel_1.add(btnNewButton_1);

		btnNewButton_3 = new JButton("Actualizar");
		btnNewButton_3.addActionListener(
				new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						Profes a = new Profes();
						a.setDocumento(getDocumento());
						a.setEmail1(getEmail());
						a.setNombre(getNombre());
						a.setApellido1(getApellido1());
						a.setApellido2(getApellido2());
						a.setSexo(getSexo());
						a.setTipo_doc(getTipoDoc());
						a.setAlergias(getAlergias());
						a.setCuenta(getCuentaBancaria());
						a.setReferencia(getReferencia());

						String curso = principal.getBaseDeDatos().getCursoActual();
						boolean b = principal.getBaseDeDatos().registrarProf(a,curso);
						
						if(b)
						{
							principal.getBaseDeDatos().addFoto(getDocumento(),fileFoto);
							JOptionPane.showMessageDialog(principal, "Se ha actualizado correctamente!", "Exito al actualizar!",JOptionPane.INFORMATION_MESSAGE);
							dispose();
						}
						else
						{
							JOptionPane.showMessageDialog(principal, "No se ha podido actualizar el registro!","Error",JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
				});
		btnNewButton_3.setEnabled(false);
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_3.setIcon(new ImageIcon(RegistrarProfesores.class.getResource("/resource/update.png")));
		panel_1.add(btnNewButton_3);

		btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) 
					{

						if(textField.getText().length()==0)
						{
							JOptionPane.showMessageDialog(principal, "El compo de documento esta vacio", "Campo documento vacio", JOptionPane.WARNING_MESSAGE);
							return;
						}

						Profes a = new Profes();
						a.setDocumento(getDocumento().toUpperCase());
						a.setEmail1(getEmail());
						a.setApellido1(getApellido1());
						a.setApellido2(getApellido2());
						a.setSexo(getSexo());
						a.setTipo_doc(getTipoDoc());
						a.setAlergias(getAlergias());

						String curso = principal.getBaseDeDatos().getCursoActual();
						boolean b = principal.getBaseDeDatos().registrarProf(a, curso);

						System.out.println(b);
						
						if(b)
						{
							principal.getBaseDeDatos().addFoto(getDocumento(), fileFoto);
							JOptionPane.showMessageDialog(principal, "Se ha registrado correctamente!", "Exito al Registrar!", JOptionPane.INFORMATION_MESSAGE);
							dispose();
						}
						else
						{
							JOptionPane.showMessageDialog(principal, "No se ha podido procesar el registro!","Error",JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
				});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setIcon(new ImageIcon(RegistrarProfesores.class.getResource("/resource/41.png")));
		panel_1.add(btnNewButton);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
								.addComponent(panel, 0, 0, Short.MAX_VALUE))
								.addContainerGap())
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 423, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addGap(4))
				);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Otros Datos:", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		panel_3 = new JPanel();
		jkPanel = new JKPanel();
		panel_3.add(jkPanel);
		panel_3.setBorder(new TitledBorder(null, "Foto", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Datos Personales", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JButton btnNewButton_2 = new JButton("Tomar Foto");
		btnNewButton_2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new Camara(principal, getInstance());
			}

		});
		
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.setIcon(new ImageIcon(RegistrarProfesores.class.getResource("/resource/camara.png")));

		JButton btnBuscarFoto = new JButton("Buscar Foto");
		btnBuscarFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				javax.swing.filechooser.FileFilter filtro2 = new FileNameExtensionFilter(
						"Imagenes (jpg, jpeg, png)", "jpg", "png", "jpeg");
				chooser.setFileFilter(filtro2);
				chooser.addChoosableFileFilter(filtro2);
				chooser.setAcceptAllFileFilterUsed(false);
				int n = chooser.showOpenDialog(principal);
				if (n == JFileChooser.APPROVE_OPTION) {
					fileFoto = chooser.getSelectedFile();

					jkPanel.setBackground(new ImageIcon(chooser
							.getSelectedFile().getPath()));
					jkPanel.validate();
				}


			}
		});
		btnBuscarFoto.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuscarFoto.setIcon(new ImageIcon(RegistrarProfesores.class.getResource("/resource/2.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
								.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(btnNewButton_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(btnBuscarFoto, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
												.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE))
												.addContainerGap())
				);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
								.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
												.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(btnBuscarFoto, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
												.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE))
												.addContainerGap())
				);

		textField = new JTextField();
		textField.setColumns(10);
		textField.requestFocus();

		JLabel lblDocumento = new JLabel("Documento:");

		comboBoxTipoDoc = new JKComboBox();
		//		comboBoxTipoDoc.addItem("");
		comboBoxTipoDoc.addItem("NIF");
		comboBoxTipoDoc.addItem("NIE");

		textField_1 = new JTextField();
		textField_1.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre:");

		JLabel lblApellido = new JLabel("Apellido1:");

		JLabel lblApellido_1 = new JLabel("Apellido2:");

		JLabel lblSexo = new JLabel("Sexo:");

		comboBox_1 = new JKComboBox();
		comboBox_1.addItem("M");
		comboBox_1.addItem("H");
		JLabel lblNewLabel_1 = new JLabel("Email:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);

		textField_4 = new JTextField();
		textField_4.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setColumns(10);

		JLabel lblNewLabel = new JLabel("Tipo Doc:");
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
				gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_4.createSequentialGroup()
										.addComponent(lblApellido)
										.addGap(18)
										.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE))
										.addGroup(gl_panel_4.createSequentialGroup()
												.addComponent(lblApellido_1)
												.addGap(18)
												.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE))
												.addGroup(gl_panel_4.createSequentialGroup()
														.addGap(19)
														.addComponent(lblSexo)
														.addGap(18)
														.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(textField_4, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
														.addGroup(gl_panel_4.createSequentialGroup()
																.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
																		.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
																		.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
																		.addGap(16)
																		.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
																				.addGroup(gl_panel_4.createSequentialGroup()
																						.addComponent(comboBoxTipoDoc, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
																						.addPreferredGap(ComponentPlacement.RELATED)
																						.addComponent(lblDocumento)
																						.addPreferredGap(ComponentPlacement.RELATED)
																						.addComponent(textField, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
																						.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE))))
																						.addContainerGap())
				);
		gl_panel_4.setVerticalGroup(
				gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBoxTipoDoc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_4.createSequentialGroup()
										.addGap(3)
										.addComponent(lblDocumento))
										.addComponent(lblNewLabel)
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panel_4.createSequentialGroup()
														.addGap(14)
														.addComponent(lblNombre))
														.addGroup(gl_panel_4.createSequentialGroup()
																.addGap(11)
																.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
																.addPreferredGap(ComponentPlacement.RELATED)
																.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
																		.addGroup(gl_panel_4.createSequentialGroup()
																				.addGap(14)
																				.addComponent(lblApellido))
																				.addGroup(gl_panel_4.createSequentialGroup()
																						.addGap(11)
																						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
																						.addPreferredGap(ComponentPlacement.RELATED)
																						.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
																								.addGroup(gl_panel_4.createSequentialGroup()
																										.addGap(14)
																										.addComponent(lblApellido_1))
																										.addGroup(gl_panel_4.createSequentialGroup()
																												.addGap(11)
																												.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
																												.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
																														.addGroup(gl_panel_4.createSequentialGroup()
																																.addPreferredGap(ComponentPlacement.RELATED)
																																.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
																																		.addComponent(lblSexo)
																																		.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																																		.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
																																		.addGroup(gl_panel_4.createSequentialGroup()
																																				.addGap(9)
																																				.addComponent(lblNewLabel_1)))
																																				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		panel_4.setLayout(gl_panel_4);
		panel_3.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel lblNewLabel_2 = new JLabel("Cuenta Bancaria:");

		textField_5 = new JTextField();
		textField_5.setColumns(10);

		JLabel lblAlergias = new JLabel("Alergias:");

		JScrollPane scrollPane_1 = new JScrollPane();

		JLabel lblReferenciaDeMantado = new JLabel("Referencia de Mantado:");

		textField_6 = new JTextField();
		textField_6.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textField_6.setColumns(10);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
				gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_2.createSequentialGroup()
										.addComponent(lblReferenciaDeMantado)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel_2.createSequentialGroup()
												.addComponent(lblAlergias)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE))
												.addComponent(textField_5, GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
												.addComponent(lblNewLabel_2))
												.addContainerGap())
				);
		gl_panel_2.setVerticalGroup(
				gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblNewLabel_2)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblReferenciaDeMantado)
								.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(32)
								.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
										.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblAlergias))
										.addGap(41))
				);
		textField.addKeyListener(new KeyListener() {




			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if(actualizar){
					if(arg0.getKeyCode() == KeyEvent.VK_BACK_SPACE){
						clear();
						found=false;
						btnNewButton.setEnabled(true);
						btnNewButton_3.setEnabled(false);
						return;
					}
				}
				if(!found){
					arg0.consume();
					final String h=textField.getText();
					try{
						if(Character.isAlphabetic(h.charAt(h.length()-1))){

							found =principal.getBaseDeDatos().verificarSiExisteProfesor(h.toUpperCase());
							if(found){
								found=false;
								if(thread==null){
									Runnable runnable=new Runnable() {

										@Override
										public synchronized void run() {
											// TODO Auto-generated method stub
											//							System.out.println(h);



											//								lblNewLabel_7.setVisible(true);
											//								lblNewLabel_7.setBusy(true);
											//								if(!h.equalsIgnoreCase("0")){
											//									
											//									actualizar = true;
											////									btnNewButton.setText("Actualizar");
											////									btnNewButton.setIcon(new ImageIcon(getClass().getResource("/resource/update.png")));
											//									
											//									principal.getBaseDeDatos().getDatosAlumno(textField_2.getText(),getInstance());
											////									lblNewLabel_7.setBusy(false);
											////									lblNewLabel_7.setVisible(false);
											//									found=true;
											////									setVisible(true);
											//								}else{

											int y=JOptionPane.showConfirmDialog(principal, "<html><body>Ya registrado<br>Desea cargar sus Datos?</body></html>","Existente",JOptionPane.INFORMATION_MESSAGE);

											if(y == JOptionPane.OK_OPTION){
												actualizar = true;
												//										btnNewButton.setText("Actualizar");
												btnNewButton.setEnabled(false);
												btnNewButton_3.setEnabled(true);
												//										btnNewButton.setIcon(new ImageIcon(getClass().getResource("/resource/update.png")));
												synchronized (this) {
													principal.getBaseDeDatos().getDatosProfesor(h.toUpperCase(),getInstance());

													//											024338213G
												}
												//										principal.getBaseDeDatos().getDatosProfesor(h.toUpperCase(),getInstance());
												//										lblNewLabel_7.setBusy(false);
												//										lblNewLabel_7.setVisible(false);
												found=true;
											}else{

											}
											//								}



										}

									};
									thread=new Thread(runnable);
									thread.start();
								}
							}
						}

					}catch(Exception e){
						//						e.printStackTrace();
					}
				}
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		textArea_1 = new JTextArea();
		scrollPane_1.setViewportView(textArea_1);
		panel_2.setLayout(gl_panel_2);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

		textField.setBorder(BorderFactory.createTitledBorder(""));
		textField_1.setBorder(BorderFactory.createTitledBorder(""));
		textField_2.setBorder(BorderFactory.createTitledBorder(""));
		textField_3.setBorder(BorderFactory.createTitledBorder(""));
		textField_4.setBorder(BorderFactory.createTitledBorder(""));
		textField_5.setBorder(BorderFactory.createTitledBorder(""));
		//		text
		PromptSupport.setPrompt("Documento", textField);
		PromptSupport.setPrompt("Nombre", textField_1);
		PromptSupport.setPrompt("Primer Apellido", textField_2);
		PromptSupport.setPrompt("Segundo Apellido", textField_3);
		PromptSupport.setPrompt("Correo Electronico", textField_4);
		panel_1.setOpaque(false);
		panel_2.setOpaque(false);
		panel_4.setOpaque(false);
		panel_3.setOpaque(false);
		try{
			textField.setCaretPosition(1);
		}catch(Exception e){

		}
	}
	public void setReferencia(String r){
		textField_6.setText(r);
	}
	public String getReferencia(){
		return textField_6.getText();
	}
	public void setFoto(BufferedImage bufferedImage){
		jkPanel.setBackground(new ImageIcon(bufferedImage));
	}
	public void setFoto(byte[] bytes){
		if(bytes!=null){
			//			System.out.println("FOTO > "+bytes.length);
			jkPanel.setBackground(new ImageIcon(bytes));
			panel_3.updateUI();
			//			panel_3.validate();
			//			jkPanel.validate();
		}
	}
	public void setFoto(BufferedImage image, File fileFotox) {

		this.foto= image;
		this.fileFoto=fileFotox;
		jkPanel.setBackground(new ImageIcon(image));
		jkPanel.repaint();
	}
	private String id_actualizar=null;
	private JTextField textField_6;
	public String getID(){
		return id_actualizar;
	}
	public void setID(String id) {
		this.id_actualizar = id;
		// TODO Auto-generated method stub

	}
}