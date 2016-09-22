package comedor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

import com.leyer.JKComboBox;
import com.leyer.JKTable;
import comedor.pdfs.Persona;
import comedor.pdfs.PDF_Listado;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.GridLayout;

public class InformeSeleccion extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void paintComponent(Graphics g) {
			setOpaque(false);
			g.drawImage(
					new ImageIcon(getClass().getResource(
							"/resource/gv.jpg"))
							.getImage(), 0, 0, getWidth(), getHeight(),
					null);

		   }
		};
	private JKComboBox comboBox;


	/**
	 * Create the dialog.
	 */
	private ComedorGUI principal;
	private ArrayList<String>     arrayList;
	private JTextField textField;
	private JKTable table1;
	private JKTable table2;
	private JLabel label;
	public InformeSeleccion(ComedorGUI gui) {
		setTitle("Seleccion");
		this.principal =gui;
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(943, 470);
		getContentPane().setLayout(new BorderLayout());
//		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblEstaOpcion = new JLabel("<html><body>Esta opcion permite seleccionar alumnos de cuarquier curso y grupo<br>que vayan a hacer alguna actividad extraescolar, desdoble,etc.</body></html>");
		lblEstaOpcion.setFont(new Font("Tahoma", Font.BOLD, 11));
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Listado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblElihe = new JLabel("Elige el Curso:");
		
		comboBox = new JKComboBox();
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try{
					String c=arrayList.get(comboBox.getSelectedIndex());
					String cursoActual=principal.getBaseDeDatos().getCursoActual();
					ArrayList<Persona> array = principal.getBaseDeDatos().getAlumnos(c,cursoActual);
				
					try{
						table1.clearTable();
					}catch(Exception e){
						
					}
					for(int index=0;index<array.size();index++){
						table1.addRow(array.get(index).getNia(),array.get(index).getApellidos()+", "+array.get(index).getNombres());
					}
					}catch(Exception e){
						e.printStackTrace();
					}
				
			}
		});
	    arrayList=new ArrayList<>();
	    comboBox.setFont(new Font("arial",Font.BOLD,12));
		principal.getBaseDeDatos().getGroups(comboBox,arrayList);
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Seleccionados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblEs = new JLabel("Escribe aqui el encabezado de la Orla, por ejemplo..: Viaje de Fin de Curso:");
		lblEs.setHorizontalAlignment(SwingConstants.CENTER);
		lblEs.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setText("Listado");
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 907, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 352, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblElihe)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(comboBox, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(lblEstaOpcion, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 402, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(18)
									.addComponent(textField, GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblEs, GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)))))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblEstaOpcion)
							.addGap(9))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblEs)
							.addGap(18)))
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblElihe)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(panel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
					.addContainerGap())
		);
			
		table2=new JKTable();
		table2.addColumn("NIA");
		table2.addColumn("Alumno");
		table2.getColumn("Alumno").setPreferredWidth(350);
		table2.getColumn("Alumno").setWidth(350);
		panel_1.setLayout(new BorderLayout(0, 0));
		table2.setFont(new Font("arial",Font.BOLD,12));
		JScrollPane scrollPane2 = new JScrollPane(table2);
		panel_1.add(scrollPane2);
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4, BorderLayout.SOUTH);
		
		JLabel lblNroDeSeleccionados = new JLabel("Nro. de Seleccionados:");
		panel_4.add(lblNroDeSeleccionados);
		
		label = new JLabel("0");
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_4.add(label);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		table1=new JKTable();
		table1.addColumn("NIA");
		table1.addColumn("Alumno");
		table1.getColumn("Alumno").setPreferredWidth(350);
		table1.getColumn("Alumno").setWidth(350);
		table2.getColumn("NIA").setPreferredWidth(130);
		table2.getColumn("NIA").setWidth(130);
		table1.getColumn("NIA").setPreferredWidth(130);
		table1.getColumn("NIA").setWidth(130);
		
		table1.setFont(new Font("arial",Font.BOLD,12));
		table1.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		JScrollPane scrollPane1 = new JScrollPane(table1);
		
		panel.add(scrollPane1);
		
		JButton btnNewButton_2 = new JButton("Imprirmir Orla de los Seleccionados");
		btnNewButton_2.setPreferredSize(new Dimension(240,26));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table2.getRowCount()==0){
					JOptionPane.showMessageDialog(principal, "No ha seleccionado ningun alumno!","Vacio",JOptionPane.WARNING_MESSAGE);
					return;
				}
				ArrayList<Persona> array=new ArrayList<>();
				for(int index=0;index<table2.getRowCount();index++){
					
					array.add(principal.getBaseDeDatos().getAlumno(table2.getValueAt(index, 0).toString()));
				}
				try {
					new PDF_Listado().crearPDF5(principal, principal.getBaseDeDatos().getCursoActual(), "", "", textField.getText(), array,true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
//					e1.printStackTrace();
					JOptionPane.showMessageDialog(principal, e1.getMessage(),e1.getLocalizedMessage(),JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		JButton btnNewButton_3 = new JButton("Cerrar");
		btnNewButton_3.setPreferredSize(new Dimension(140,26));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(InformeSeleccion.class.getResource("/resource/close1.png")));
		panel_3.add(btnNewButton_3);
		btnNewButton_2.setIcon(new ImageIcon(InformeSeleccion.class.getResource("/resource/Pdf-icon1.png")));
		panel_3.add(btnNewButton_2);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				table2.addRow(table1.getValueAt(table1.getSelectedRow(), 0),table1.getValueAt(table1.getSelectedRow(), 1));
				label.setText(""+table2.getRowCount());
			}
		});
		btnNewButton.setIcon(new ImageIcon(InformeSeleccion.class.getResource("/resource/Flecha.png")));
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table2.getSelectedRow()!=-1){
					table2.removeRow(table2.getSelectedRow());
					label.setText(""+table2.getRowCount());
				}
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(InformeSeleccion.class.getResource("/resource/Flecha1.png")));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
						.addComponent(btnNewButton_1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(5)
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_1)
					.addContainerGap(230, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		panel.setOpaque(false);
		panel_1.setOpaque(false);
		panel_3.setOpaque(false);
		panel_2.setOpaque(false);
//		panel_4.setOpaque(false);
		contentPanel.setLayout(gl_contentPanel);
		setLocationRelativeTo(gui);
	}
}
