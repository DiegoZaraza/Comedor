package comedor;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import org.jdesktop.xswingx.PromptSupport;

import com.leyer.JKComboBox;

import java.awt.GridLayout;

public class AsignarUsuario extends javax.swing.JDialog implements
		ActionListener {

	private static final long serialVersionUID = 1L;
	private char l1, l2;

	
	private JKComboBox comboBox;
	private int op = 0;
	private ComedorGUI principal;
	private ArrayList<Object> arrayList;


	private AdministrarUsuarios administrarUsuarios;
	public AsignarUsuario( ComedorGUI principal, int i,AdministrarUsuarios administrarUsuarios) {
		super(principal, true);
		this.principal = principal;

		this.administrarUsuarios = administrarUsuarios;
		this.op = i;
		initComponents();
		if (i == 2) {

			try {

//				String v = principal.getBaseDeDatos().getDatosUsuario(principal.getCedula(), this);
				// nombre_y_apellido, nombre_usuario, clave_usuarios,
				// privilegios
//				textField.setText(v.split(":")[0]);
//				comboBox.setSelectedItem((v.split(":")[3].equalsIgnoreCase("1")) ? "Administrador"
//						: "Profesor");
//				textField_1.setText(principal.getCedula());
//				jTextField1.setText(v.split(":")[1]);
//				textField_1.setEnabled(false);
//				jPasswordField1.setText(v.split(":")[2]);
//				jPasswordField2.setText(v.split(":")[2]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.setLocationRelativeTo(principal);
		setSize(657, 381);
		setVisible(true);

	}

//	/**
//	 * @wbp.parser.constructor
//	 */
//	public AsignarUsuario(Principal principal, int i) {
//		super(principal, true);
//		this.principal = principal;
//		// this.acceso=pantallaDeCarga;
//		this.op = i;
//		initComponents();
//		if (i == 2) {
//			try {
//				String v = principal.getBaseDeDatos().getDatosUsuario(
//						principal.getCedula(), this);
//				// nombre_y_apellido, nombre_usuario, clave_usuarios,
//				// privilegios
//				textField.setText(v.split(":")[0]);
//				comboBox.setSelectedItem((v.split(":")[3].equalsIgnoreCase("1")) ? "Administrador"
//						: "Profesor");
//				textField_1.setText(principal.getCedula());
//				jTextField1.setText(v.split(":")[1]);
//				textField_1.setEnabled(false);
//				jPasswordField1.setText(v.split(":")[2]);
//				jPasswordField2.setText(v.split(":")[2]);
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		this.setLocationRelativeTo(principal);
//		setSize(657, 387);
//		setVisible(true);
//
//	}

	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jPanel1.setBackground(UIManager.getColor("Button.disabledShadow"));
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();
		jPasswordField1 = new javax.swing.JPasswordField();
		jPasswordField2 = new javax.swing.JPasswordField();
		jRadioButton1 = new javax.swing.JRadioButton();
		if (op == 2) {
			this.setTitle("Cambio de Clave");
		} else if (op != 1)
			this.setTitle("Asignacion de Privilegios");
		else
			this.setTitle("Asignar Usuarios");
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		//
		// database= new database(igu);
		if (op == 2) {
			jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(
					null, "", 0, 0, new Font("arial", Font.BOLD, 11),
					new java.awt.Color(0, 0, 153)));

		} else if (op != 1)
			jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(
					null, "Asignar Usuarios", 0, 0, new Font("arial", Font.BOLD,
							11), new java.awt.Color(0, 0, 153)));
		else
			jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(
					null, "Asignar Usuarios", 0, 0, new Font("arial",
							Font.BOLD, 11), new java.awt.Color(0, 0, 153)));

		PromptSupport.setPrompt("Introduzca Usuario", jTextField1);

		PromptSupport.setPrompt("Introduzca Clave", jPasswordField1);

		PromptSupport.setPrompt("Introduzca Nueva Clave ", jPasswordField2);
		jRadioButton1.addActionListener(this);

		l1 = jPasswordField1.getEchoChar();

		l2 = jPasswordField1.getEchoChar();

		jLabel1.setText("Usuario:");

		jLabel2.setText("Clave:");

		jLabel3.setText("Confirmar  Clave:");

		jRadioButton1.setText("Mostrar Claves");

		textField = new JTextField();
		if (op == 2) {
			textField.setEnabled(false);
		}
		PromptSupport.setPrompt("Nombre y apellido", textField);
		textField.setColumns(10);

		JLabel lblNombreYApellido = new JLabel("Nombre y Apellido:");

		JLabel lblPrivilegio = new JLabel("Tipo de Usuario:");

		comboBox = new JKComboBox();
		arrayList=new ArrayList<>();
		principal.getBaseDeDatos().getRoles(comboBox,arrayList);

		comboBox.setFont(new Font("Tahoma", Font.BOLD, 11));
	
		
		
		label = new JLabel("      ");
		label.setIcon(new ImageIcon(getClass().getResource(
				"/resource/users2.png")));
		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1Layout.setHorizontalGroup(
			jPanel1Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
					.addContainerGap()
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup()
							.addGap(21)
							.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
								.addComponent(lblPrivilegio, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox, 0, 302, Short.MAX_VALUE)
								.addComponent(jLabel1)
								.addComponent(jTextField1, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
								.addGroup(jPanel1Layout.createSequentialGroup()
									.addComponent(jLabel2)
									.addGap(78)
									.addComponent(jLabel3))
								.addGroup(jPanel1Layout.createSequentialGroup()
									.addComponent(jPasswordField1, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(jPasswordField2, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
								.addComponent(jRadioButton1)))
						.addGroup(jPanel1Layout.createSequentialGroup()
							.addGap(18)
							.addComponent(lblNombreYApellido)))
					.addContainerGap())
		);
		jPanel1Layout.setVerticalGroup(
			jPanel1Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
					.addContainerGap(114, Short.MAX_VALUE)
					.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addGroup(jPanel1Layout.createSequentialGroup()
							.addComponent(lblNombreYApellido)
							.addPreferredGap(ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(9)
							.addComponent(lblPrivilegio)
							.addGap(5)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(jLabel1)
							.addGap(8)
							.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(jLabel2)
								.addComponent(jLabel3))
							.addGap(5)
							.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(jPasswordField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(jPasswordField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(14)
							.addComponent(jRadioButton1)
							.addGap(103)))
					.addGap(173))
		);
		jPanel1.setLayout(jPanel1Layout);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		jButton1 = new javax.swing.JButton();
		jButton1.setText("Aceptar");

		jButton1.setIcon(new ImageIcon(getClass().getResource(
				"/resource/41.png")));

		jButton1.addActionListener(this);

		if (op == 2) {
			jButton1.setText("Actualizar");
		} else
			jButton1.setText("Aceptar");
		jButton1.setFont(new Font("arial", Font.BOLD, 11));
		jButton2 = new javax.swing.JButton();
		jButton2.setIcon(new ImageIcon(getClass().getResource(
				"/resource/close.png")));
		jButton2.addActionListener(this);

		jButton2.setText("Cerrar");
		jButton2.setFont(new Font("arial", Font.BOLD, 11));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(jPanel1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 621, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(239, Short.MAX_VALUE))
		);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		panel.add(jButton2);
		panel.add(jButton1);
		getContentPane().setLayout(layout);
		jRadioButton1.setOpaque(false);
		textField.setBorder(BorderFactory.createEtchedBorder());
		jTextField1.setBorder(BorderFactory.createEtchedBorder());
		jPasswordField1.setBorder(BorderFactory.createEtchedBorder());
		jPasswordField2.setBorder(BorderFactory.createEtchedBorder());
		jButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		jButton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub


				System.out.println("REGISTER");
				if (textField.getText().equalsIgnoreCase("")) {
					JOptionPane.showMessageDialog(getInstance(),
							"No ha escrito ningun Nombre y Apellido",
							"¡El campo se encuentra vacio!",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
//				if (textField_1.getText().equalsIgnoreCase("")) {
	//
//					JOptionPane.showMessageDialog(this,
//							"No ha escrito ninguna Cedula!",
//							"¡El campo se encuentra vacio!",
//							JOptionPane.WARNING_MESSAGE);
//					return;
//				}
//				if (textField_1.getText().length() < 6) {
//					JOptionPane.showMessageDialog(this, "La cedula es invalida!",
//							"Error", JOptionPane.WARNING_MESSAGE);
//					return;
//				}
				if (jTextField1.getText().equalsIgnoreCase("")) {

					JOptionPane.showMessageDialog(getInstance(),
							"No ha escrito ningun usuario",
							"¡El campo se encuentra vacio!",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (new String(jPasswordField1.getPassword()).length() == 0) {

					JOptionPane.showMessageDialog(getInstance(), "No ha escrito una clave",
							"¡El campo se encuentra vacio!",
							JOptionPane.WARNING_MESSAGE);

					return;
				}

				if (new String(jPasswordField2.getPassword()).length() == 0) {
					JOptionPane.showMessageDialog(getInstance(),
							"No ha confirmado la clave",
							"¡El campo se encuentra vacio!",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				
				
				if (new String(jPasswordField1.getPassword())
						.equalsIgnoreCase(new String(jPasswordField2.getPassword()))) {
					try {
						boolean  n = principal.getBaseDeDatos().agregarUsuario(
								textField.getText(), jTextField1.getText(),
								new String(jPasswordField1.getPassword()),
								arrayList.get(comboBox.getSelectedIndex()),getInstance());
	
						if(n){
							JOptionPane.showMessageDialog(getInstance(), "Se ha registrado correctamente!","Registro Exitoso!",JOptionPane.INFORMATION_MESSAGE);
							administrarUsuarios.getUsuarios();
							dispose();
						}else{
							
						}
					} catch (HeadlessException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
	//
//					dispose();
				
	//
				} else {
	
					JOptionPane
							.showMessageDialog(getInstance(), "Las claves no son iguales",
									"Se ha encontrado un Error ",
									JOptionPane.ERROR_MESSAGE);
				}
			
					
			}
		});
		pack();
	}

	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPasswordField jPasswordField1;
	private javax.swing.JPasswordField jPasswordField2;
	private javax.swing.JRadioButton jRadioButton1;
	private javax.swing.JTextField jTextField1;
	private JTextField textField;
	private JPanel panel;
	private JLabel label;

	public AsignarUsuario getInstance(){
		return this;
	}
	// private database database;
	// private principal principal;
	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getSource() == jRadioButton1) {

			if (jRadioButton1.isSelected()) {

				jPasswordField1.setEchoChar((char) 0);
				jPasswordField2.setEchoChar((char) 0);
			} else {

				jPasswordField1.setEchoChar(l1);
				jPasswordField2.setEchoChar(l2);
			}

		}
		if (arg0.getSource() == jButton1
				&& jButton1.getText().equalsIgnoreCase("Actualizar")) {
			if (jTextField1.getText().equalsIgnoreCase("")) {

//				JOptionPane.showMessageDialog(this,
//						"No ha escrito ningun usuario",
//						"¡El campo se encuentra vacio!",
//						JOptionPane.WARNING_MESSAGE);
//				return;
//			}
//			if (new String(jPasswordField1.getPassword()).length() == 0) {
//
//				JOptionPane.showMessageDialog(this, "No ha escrito una clave",
//						"¡El campo se encuentra vacio!",
//						JOptionPane.WARNING_MESSAGE);
//
//				return;
//			}
//			if (new String(jPasswordField1.getPassword())
//					.equalsIgnoreCase(new String(jPasswordField2.getPassword()))) {
//				boolean b = principal.getBaseDeDatos().cambiarClaveUsuario(
//						principal.getCedula(), jTextField1.getText(),
//						new String(jPasswordField2.getPassword()));
//				if (b) {
//					JOptionPane.showMessageDialog(this,
//							"Se ha actualizado correctamente!", "Completado!",
//							JOptionPane.INFORMATION_MESSAGE);
//					dispose();
//				} else {
//					JOptionPane.showMessageDialog(this, "Ha ocurrido un error",
//							"Se ha encontrado un Error",
//							JOptionPane.ERROR_MESSAGE);
//					dispose();
//
//				}
//				dispose();
//				return;
//			} else {
//				JOptionPane
//						.showMessageDialog(this, "Las claves no son iguales",
//								"Se ha encontrado un Error ",
//								JOptionPane.ERROR_MESSAGE);
//				return;
//			}

		}


		if (arg0.getSource() == jButton2) {

			dispose();

			}
		}
	}

	}
