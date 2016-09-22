package comedor;

import itaca.Grups;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;

import org.jdesktop.xswingx.PromptSupport;

import com.leyer.JKComboBox;
import com.leyer.JKTable;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GestionDeGrupos extends JInternalFrame implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JKTable jkTable;
	private JKComboBox comboBox;

	/**
	 * Create the frame.
	 */
	private ComedorGUI principal;
	private ArrayList<String> arrayListProfesores;
	private JLabel label;
	private JButton btnActualizar;
	private JButton btnNewButton_1;
	
	public GestionDeGrupos getInstance(){
		return this;
	}
	
	public String getNombre(){
		return textField_1.getText();
	}
	public void setNombre(String n){
		textField_1.setText(n);
	}
	public void setCodigo(String n){
		textField.setText(n);
	}
	public String getCodigo(){
		return textField.getText();
	}
	public String getSelectedProf(){
		return arrayListProfesores.get(comboBox.getSelectedIndex());
	}
	public GestionDeGrupos(ComedorGUI comedorGUI) {
		setTitle("Gestion de Grupos");
		setBounds(10, 10, 899, 504);
		this.principal = comedorGUI;
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Nuevo Grupo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(BorderFactory.createTitledBorder("Listado"));
		JPanel panel_2 = new JPanel();
		
		JButton btnNewButton_2 = new JButton("Actualizar Seleccionado");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(jkTable.getSelectedRow()!=-1){
					String codigoGrupo=jkTable.getValueAt(jkTable.getSelectedRow(), 0).toString();
					String nombreGrupo=principal.getBaseDeDatos().getNombreGrupo(codigoGrupo);
					String documento=principal.getBaseDeDatos().getTutorDeGrupo(codigoGrupo);
					setCodigo(codigoGrupo);
					setNombre(nombreGrupo);
					for(int index=0;index<arrayListProfesores.size();index++){
						if(arrayListProfesores.get(index).equalsIgnoreCase(documento)){
							comboBox.setSelectedIndex(index);
							break;
						}
					}
					btnNewButton_1.setEnabled(false);
					btnActualizar.setEnabled(true);
				}
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(GestionDeGrupos.class.getResource("/resource/update.png")));
		
		JButton btnNewButton_3 = new JButton("Eliminar Seleccionado");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(jkTable.getSelectedRow()!=-1){
					int u=JOptionPane.showConfirmDialog(principal, "Esta seguro(a) que eliminar el grupo seleccionado?","Confirmacion",JOptionPane.INFORMATION_MESSAGE);
					if(u==JOptionPane.OK_OPTION){
						String codigoGrupo=jkTable.getValueAt(jkTable.getSelectedRow(), 0).toString();
						String cursoActual=principal.getBaseDeDatos().getCursoActual();
						boolean n=principal.getBaseDeDatos().eliminarGrupo(codigoGrupo,cursoActual);
						if(n){
							JOptionPane.showMessageDialog(principal, "Se ha eliminado correctamente!","Exito",JOptionPane.INFORMATION_MESSAGE);
							new Thread(getInstance()).start();
							return;
						}else{
							JOptionPane.showMessageDialog(principal, "No se ha podido eliminar!","Eliminacion",JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
				}
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(GestionDeGrupos.class.getResource("/resource/A1-close.png")));
		
		JButton btnNewButton_4 = new JButton("Ver Alumnos");
		btnNewButton_4.setIcon(new ImageIcon(GestionDeGrupos.class.getResource("/resource/usu.png")));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(jkTable.getSelectedRow()!=-1){
					String codigoGrupo=jkTable.getValueAt(jkTable.getSelectedRow(), 0).toString();
					String nombre= jkTable.getValueAt(jkTable.getSelectedRow(), 1).toString();
					JDialog dialog=new  JDialog(principal,true);
					dialog.setSize(700,400);
					dialog.getContentPane().setLayout(new BorderLayout());
					JPanel panel=new JPanel(new GridLayout());
				    JKTable table=new JKTable();
				    dialog.setTitle("Alumnos del Grupo: "+nombre);
					JScrollPane scrollPane=new JScrollPane(table);
					table.addColumn("NIA");
					table.addColumn("Documento");
					table.addColumn("Alumno");
					table.getColumn("NIA").setPreferredWidth(20);
					table.getColumn("NIA").setWidth(20);
					
					table.getColumn("Alumno").setPreferredWidth(220);
					table.getColumn("Alumno").setWidth(220);
					

					panel.add(scrollPane,BorderLayout.CENTER);
					JPanel panel2=new JPanel(new FlowLayout(FlowLayout.LEFT));
					JLabel label1=new JLabel("0");
					label1.setFont(new Font("arial",Font.BOLD,14));
					panel2.add(new JLabel("Nro. de Alumnos:"));
					principal.getBaseDeDatos().getAlumnos(codigoGrupo,table,label1);
					panel2.add(label1);
//					panel.add(panel2,BorderLayout.SOUTH);
					dialog.getContentPane().add(panel,BorderLayout.CENTER);
					dialog.getContentPane().add(panel2,BorderLayout.SOUTH);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				    dialog.setLocationRelativeTo(principal);
					dialog.setVisible(true);
				}
			}
		});
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(GestionDeGrupos.class.getResource("/resource/grupos.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 883, Short.MAX_VALUE)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 883, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton_4)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_3)
					.addContainerGap(450, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 769, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(11)
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_4, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_2)
						.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel_1.setLayout(new BorderLayout(0, 0));
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
//		jkTable.setBackground(JKTable.MODE_JK2, new ImageIcon(getClass().getResource("/resource/grupos.png")), scrollPane);
		panel_1.add(scrollPane);
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_1.add(panel_3, BorderLayout.SOUTH);
		
		JLabel lblNroDeGrupos = new JLabel("Nro. de Grupos:");
		panel_3.add(lblNroDeGrupos);
		
		label = new JLabel("0");
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_3.add(label);
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnNewButton = new JButton("Cerrar");
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		btnNewButton.setIcon(new ImageIcon(GestionDeGrupos.class.getResource("/resource/close.png")));
		panel_2.add(btnNewButton);
		
		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nombre de Grupo:");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Profesor(a):");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
	    comboBox = new JKComboBox();
        arrayListProfesores = new ArrayList<>();
		btnNewButton_1 = new JButton("Crear Grupo");
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(textField.getText().length()==0){
					JOptionPane.showMessageDialog(principal, "El campo de codigo esta vacio!","Campo vacio",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				if(textField_1.getText().length()==0){
					JOptionPane.showMessageDialog(principal, "El campo de Nombre del Grupo esta vacio!","Campo vacio",JOptionPane.INFORMATION_MESSAGE);

					return;
				}
				Grups a=new Grups();
				a.setNombre(getNombre().toUpperCase());
				a.setCodigo(getCodigo().toUpperCase());
				a.setTutor_ppal(getSelectedProf());
				String cursoActual=principal.getBaseDeDatos().getCursoActual();
				boolean n=principal.getBaseDeDatos().registrarGroup(a,cursoActual);
				if(n){
					JOptionPane.showMessageDialog(principal, "Se ha registrado correctamente!","Exito",JOptionPane.INFORMATION_MESSAGE);
					new Thread(getInstance()).start();
					return;
				}else{
					JOptionPane.showMessageDialog(principal, "No se ha podido registrar!","Registro fallido",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
			}
		});
		
		btnNewButton_1.setIcon(new ImageIcon(GestionDeGrupos.class.getResource("/resource/41.png")));
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setEnabled(false);
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textField.getText().length()==0){
					JOptionPane.showMessageDialog(principal, "El campo de codigo esta vacio!","Campo vacio",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				if(textField_1.getText().length()==0){
					JOptionPane.showMessageDialog(principal, "El campo de Nombre del Grupo esta vacio!","Campo vacio",JOptionPane.INFORMATION_MESSAGE);

					return;
				}
				String cursoActual=principal.getBaseDeDatos().getCursoActual();
				String codigoGrupo=jkTable.getValueAt(jkTable.getSelectedRow(), 0).toString();
				if(principal.getBaseDeDatos().eliminarGrupo(codigoGrupo,cursoActual)){
					Grups a=new Grups();
					a.setNombre(getNombre().toUpperCase());
					a.setCodigo(getCodigo().toUpperCase());
					a.setTutor_ppal(getSelectedProf());
					boolean n=principal.getBaseDeDatos().registrarGroup(a,cursoActual);
					if(n){
						JOptionPane.showMessageDialog(principal, "Se ha actualizado correctamente!","Exito al Actualizar",JOptionPane.INFORMATION_MESSAGE);
						setCodigo("");
						setNombre("");
						btnNewButton_1.setEnabled(true);
						btnActualizar.setEnabled(false);
						new Thread(getInstance()).start();
						return;
					}else{
						JOptionPane.showMessageDialog(principal, "No se ha podido actualizar!","Fallido",JOptionPane.ERROR_MESSAGE);
						return;
					}
					
				}
			}
		});
		btnActualizar.setIcon(new ImageIcon(GestionDeGrupos.class.getResource("/resource/update.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblNewLabel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblCodigo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnActualizar, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(80, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnActualizar, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup()
								.addGap(52)
								.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
							.addGroup(gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblCodigo)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(14)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblNewLabel)
									.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblNewLabel_1)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
		textField.setBorder(BorderFactory.createTitledBorder(""));
		textField_1.setBorder(BorderFactory.createTitledBorder(""));
//		t
		PromptSupport.setPrompt("0000", textField);
		PromptSupport.setPrompt("Nombre", textField_1);
	    principal.getBaseDeDatos().getProgfesors(comboBox,arrayListProfesores);

		new Thread(this).start();

	}
	@Override
	public void run() {
		
		String cursoActual=principal.getBaseDeDatos().getCursoActual();
		principal.getBaseDeDatos().getGroups(jkTable,label,cursoActual);
		
	}
}
