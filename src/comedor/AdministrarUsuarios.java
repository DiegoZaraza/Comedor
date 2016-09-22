package comedor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.jdesktop.xswingx.PromptSupport;

import com.leyer.JKComboBox;
import com.leyer.JKTable;

public class AdministrarUsuarios extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AdministrarUsuarios getInstance(){
		return this;
	}
	private ComedorGUI principal;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private ArrayList<Object> arrayList;
	private ArrayList<String>arrayListIDs ;
	private JKTable jkTable;

	public AdministrarUsuarios(final ComedorGUI principal) {
		super("", false, true, false, true);
		getContentPane().setBackground(Color.WHITE);
		this.principal = principal;
		setBounds(10, 10, 865, 408);
		arrayListIDs = new ArrayList<>();
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,
				null, null), "Lista de Usuarios", TitledBorder.LEADING,
				TitledBorder.TOP, new Font("arial", Font.BOLD, 11),
				new java.awt.Color(0, 0, 153)));
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(getClass().getResource(
				"/resource/679.png")));

		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.TRAILING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addGap(12)
						.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE,
								273, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								groupLayout
										.createParallelGroup(Alignment.LEADING)
										.addComponent(panel_1,
												Alignment.TRAILING, 0, 0,
												Short.MAX_VALUE)
										.addComponent(panel,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(panel_2,
												GroupLayout.DEFAULT_SIZE, 550,
												Short.MAX_VALUE))
						.addContainerGap()));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				panel_1,
																				GroupLayout.PREFERRED_SIZE,
																				52,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				panel,
																				GroupLayout.PREFERRED_SIZE,
																				237,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(18)
																		.addComponent(
																				panel_2,
																				GroupLayout.PREFERRED_SIZE,
																				51,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(42)
																		.addComponent(
																				lblNewLabel_1,
																				GroupLayout.PREFERRED_SIZE,
																				272,
																				GroupLayout.PREFERRED_SIZE)))
										.addContainerGap(
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		// jkTable.setBackground(JKTable.MODE_JK2,new
		// ImageIcon(getClass().getResource("/scep_unefm/resource/nb.jpg")),scrollPane);
		JButton btnNewButton = new JButton("Cerrar");
		btnNewButton.setIcon(new ImageIcon(getClass().getResource(
				"/resource/close.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
	jkTable = new JKTable();
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel lblCantidadDeUsuarios = new JLabel("Cantidad de Usuarios:");

		lblNewLabel = new JLabel("0");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setText("" + jkTable.getRowCount());
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCantidadDeUsuarios)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(lblCantidadDeUsuarios)
						.addComponent(lblNewLabel))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);

//		JPopupMenu jPopupMenu = new JPopupMenu();
//		JMenuItem item = new JMenuItem("Ver Datos de Acceso");
//		JMenuItem item2 = new JMenuItem("Cambiar Clave");
//		item2.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				try {
//					try {
//						cambio.setVisible(false);
//					} catch (Exception h) {
//
//					}
//					String a = ""
//							+ jkTable.getValueAt(jkTable.getSelectedRow(), 3);
//					cambio = new CambioDeClave(principal, a);
//
//					principal.addCa(cambio);
//
//					cambio.setVisible(true);
//				} catch (Exception e) {
//				}
//			}
//		});
//		JMenuItem item3 = new JMenuItem("Cambiar Usuario");
//		item3.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				// // TODO Auto-generated method stub
//				String f = principal.getIdUser();
//
//				String c = JOptionPane.showInputDialog(get(),
//						"Ingrese su Clave Actual:", "Validar",
//						JOptionPane.INFORMATION_MESSAGE);
//				boolean b = principal.getBaseDeDatos().validarClave(f, c);
//				if (b) {
//					String g = JOptionPane.showInputDialog(get(),
//							"Ingrese el nuevo nombre de usuario: ",
//							"Nuevo Nombre", JOptionPane.INFORMATION_MESSAGE);
//
//					String a = ""
//							+ jkTable.getValueAt(jkTable.getSelectedRow(), 3);
//					principal.getBaseDeDatos().cambiarUsuario(a, g);
//				} else {
//					JOptionPane.showMessageDialog(get(), "Clave Invalida!",
//							"Invalido", JOptionPane.WARNING_MESSAGE);
//
//				}
//			}
//		});
//		item.setFont(new Font("arial", Font.BOLD, 11));
//		item2.setFont(new Font("arial", Font.BOLD, 11));
//		item3.setFont(new Font("arial", Font.BOLD, 11));
//		item.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO Auto-generated method stub
//				if (jkTable.getSelectedRow() != -1) {
//					String f = principal.getIdUser();
//
//					String c = JOptionPane.showInputDialog(get(),
//							"Ingrese su Clave Actual:", "Validar",
//							JOptionPane.INFORMATION_MESSAGE);
//					boolean b = principal.getBaseDeDatos().validarClave(f, c);
//					if (b) {
//						String a = ""
//								+ jkTable.getValueAt(jkTable.getSelectedRow(),
//										3);
//
//						principal.getBaseDeDatos().verDatosDeAcceso(a);
//					}
//				}
//			}
//		});
//		jPopupMenu.add(item);
//		jPopupMenu.add(new JSeparator());
//		jPopupMenu.add(item2);
//		jPopupMenu.add(item3);
//		jkTable.setComponentPopupMenu(jPopupMenu);

		JScrollPane scrollPane = new JScrollPane(jkTable);
		setTitle("Administrar Usuarios");

		JButton btnAgregar = new JButton("Agregar Usuario");
		btnAgregar.setIcon(new ImageIcon(getClass().getResource(
				"/resource/add1.png")));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
//				new asis
				new AsignarUsuario(principal, 1,getInstance());
				
//				jkTable.clearTable();
//				principal.getBaseDeDatos().getUsuarios(jkTable);
			}
		});
		btnAgregar.setFont(new Font("Tahoma", Font.BOLD, 11));

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

//				int y = JOptionPane.showConfirmDialog(principal,
//						"Esta seguro(a) de aplicar esta accion?", "No",
//						JOptionPane.INFORMATION_MESSAGE);
//
//				if (y == JOptionPane.OK_OPTION) {
//
//					if (jkTable.getSelectedRow() != -1) {
//						if (principal.getBaseDeDatos()
//								.eliminarUsuario(
//										Integer.parseInt(jkTable.getValueAt(
//												jkTable.getSelectedRow(), 3)
//												.toString()))) {
//							JOptionPane.showMessageDialog(principal,
//									"Se ha eliminado correctamente!",
//									"Correcto!",
//									JOptionPane.INFORMATION_MESSAGE);
//							jkTable.clearTable();
//							principal.getBaseDeDatos().getUsuarios(jkTable);
//							lblNewLabel.setText("" + jkTable.getRowCount());
//						}
//					} else
//						JOptionPane.showMessageDialog(principal,
//								"No ha seleccionado un usuario!",
//								"Ninguna seleccion",
//								JOptionPane.WARNING_MESSAGE);
//				}
			}
		});
		btnEliminar.setIcon(new ImageIcon(getClass().getResource(
				"/resource/close.png")));
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 11));

		JButton btnCambiarDeGrupo = new JButton("Cambiar Tipo");
		 btnCambiarDeGrupo.setIcon(new ImageIcon(getClass().getResource(
				"/resource/update.png")));
		btnCambiarDeGrupo.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				
				final JDialog dialog= new JDialog(principal,true);
				dialog.setTitle("Selecionar Tipo");
				dialog.setLayout(new BorderLayout());
				final JKComboBox box = new JKComboBox();
				box.setFont(new Font("arial",Font.BOLD,12));
				final ArrayList<Object> arrayList = new ArrayList<>();
				principal.getBaseDeDatos().getRoles(box, arrayList);
				dialog.add(box,BorderLayout.CENTER);
				dialog.setSize(320,120);
				JButton button = new JButton("Aceptar");
				
				button.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						
						if(jkTable.getSelectedRow() !=-1){
							String h = arrayList.get(box.getSelectedIndex()).toString();
							
							String g = jkTable.getValueAt(jkTable.getSelectedRow(), 2).toString();
							
							
						 boolean j=principal.getBaseDeDatos().actualizarTipoUsuario(h,g);
						 if(j){
							 JOptionPane.showMessageDialog(principal, "Se ha cambiado el privilegio correctamente!", "Exito!", JOptionPane.INFORMATION_MESSAGE);
							
							 getUsuarios();
							 dialog.dispose();
							 return;
							 }
						}else{
							
							JOptionPane.showMessageDialog(principal, "No se ha podido cambiar el privilegio!","Error",JOptionPane.ERROR_MESSAGE);
								return;
						}
						
					}
				});
				JButton button2 = new JButton("Cancelar");
				button2.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						
						dialog.dispose();
					}
				});
				JPanel jPanel = new JPanel();
				jPanel.setLayout(new GridLayout());
				jPanel.setBorder(BorderFactory.createTitledBorder(""));
				jPanel.add(button2);
				jPanel.add(button);
				dialog.add(jPanel,BorderLayout.SOUTH);
				
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setLocationRelativeTo(principal);
				dialog.setVisible(true);
				
				
//				if(jkTable.getSelectedRow()!=-1){
//				String bigList[] = { "ADMINISTRADOR", "DESE", "DIRECTOR",
//						"JEFES", "PROFESOR", "ALUMNO", "ADMINISTRACION",
//						"PASANTE"};
//				String s = (String) JOptionPane.showInputDialog(principal,
//						"Seleccionar", "Seleccionar",
//						JOptionPane.QUESTION_MESSAGE, null, bigList, "Titan");
//				if (s != null) {
//				
//					Integer c = Integer.parseInt(jkTable.getValueAt(
//							jkTable.getSelectedRow(), 3).toString());
//					boolean n = principal.getBaseDeDatos().cambiarDeGrupo(c, s);
//					if (n) {
//						JOptionPane.showMessageDialog(principal,
//								"Se ha cambiado correctamente!", "OK",
//								JOptionPane.INFORMATION_MESSAGE);
//						jkTable.clearTable();
//						principal.getBaseDeDatos().getUsuarios(jkTable);
//						lblNewLabel.setText("" + jkTable.getRowCount());
//
//					} else {
//						JOptionPane.showMessageDialog(principal,
//								"No se ha podido realizar la accion!", "error",
//								JOptionPane.ERROR_MESSAGE);
//
//					}
//			       } 
//				}else{
//					JOptionPane.showMessageDialog(principal,
//							"Debe seleccionar un usuario!", "Seleccion Vacia",
//							JOptionPane.ERROR_MESSAGE);
//				}
			}
		});
		btnCambiarDeGrupo.setFont(new Font("Tahoma", Font.BOLD, 11));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(
				Alignment.TRAILING).addGroup(
				gl_panel_1
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(btnAgregar, GroupLayout.DEFAULT_SIZE,
								186, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnCambiarDeGrupo,
								GroupLayout.PREFERRED_SIZE, 198,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE,
								135, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		gl_panel_1
				.setVerticalGroup(gl_panel_1
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_panel_1
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_panel_1
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																btnAgregar)
														.addComponent(
																btnEliminar)
														.addComponent(
																btnCambiarDeGrupo,
																GroupLayout.PREFERRED_SIZE,
																27,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap(
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		panel_1.setLayout(gl_panel_1);

		jkTable.getTableHeader().setEnabled(false);
		jkTable.setFont(new Font("arial", Font.BOLD, 11));
		jkTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		jkTable.addColumn("Nombres y Apellidos");

		jkTable.addColumn("Tipo Usuario");
		jkTable.addColumn("ID");
		jkTable.getColumn("Nombres y Apellidos").setPreferredWidth(200);
		jkTable.getColumn("Nombres y Apellidos").setWidth(200);
		jkTable.getColumn("ID").setPreferredWidth(10);
		jkTable.getColumn("ID").setWidth(10);

		
		principal.getBaseDeDatos().getUsuarios(jkTable,arrayListIDs);
		lblNewLabel.setText("" + jkTable.getRowCount());
		textField = new JTextField();
		textField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				jkTable.search(textField.getText());
				lblNewLabel.setText("" + jkTable.getRowCount());
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		PromptSupport.setPrompt("Buscador...", textField);
		textField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Filtrar Por:");

		arrayList = new ArrayList<>();
		
		final JKComboBox comboBox = new JKComboBox();
		
		principal.getBaseDeDatos().getRoles(comboBox,arrayList);

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jkTable.search(comboBox.getSelectedItem().toString());
				lblNewLabel.setText("" + jkTable.getRowCount());
			}
		});
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 11));

		

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					jkTable.clearTable();
				}catch(Exception e){
					e.printStackTrace();
				}
//				principal.getBaseDeDatos().getUsuarios(jkTable,arrayListIDs);
				getUsuarios();
				lblNewLabel.setText("" + jkTable.getRowCount());

			}
		});
		btnNewButton_1.setIcon(new ImageIcon(getClass().getResource(
				"/resource/update.png")));
		
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		// textField.
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		textField,
																		GroupLayout.PREFERRED_SIZE,
																		252,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		lblNewLabel_2)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		comboBox,
																		GroupLayout.PREFERRED_SIZE,
																		213,
																		GroupLayout.PREFERRED_SIZE))
												.addComponent(
														btnNewButton_1,
														GroupLayout.PREFERRED_SIZE,
														69,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														scrollPane,
														GroupLayout.PREFERRED_SIZE,
														526,
														GroupLayout.PREFERRED_SIZE))
								.addContainerGap(12, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(
														textField,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNewLabel_2)
												.addComponent(
														comboBox,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(scrollPane,
										GroupLayout.PREFERRED_SIZE, 147,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED,
										27, Short.MAX_VALUE)
								.addComponent(btnNewButton_1)));
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
		textField.setBorder(BorderFactory.createEtchedBorder());

	}

	public void getUsuarios(){
		principal.getBaseDeDatos().getUsuarios(jkTable,arrayListIDs);
	}
	public AdministrarUsuarios get() {
		return this;
	}

	private JTextField textField;
}
