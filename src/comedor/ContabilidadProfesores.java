package comedor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;

import com.itextpdf.text.DocumentException;
import com.leyer.JKTable;

import comedor.pdfs.PDF_Formato_Apuntarse;
import comedor.pdfs.Persona;
import java.awt.Color;

public class ContabilidadProfesores extends JInternalFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ComedorGUI principal;
	public ContabilidadProfesores(ComedorGUI gui) {
		setTitle("Contabilidad Profesores");
		setBounds(10, 10, 1049, 582);
		this.principal = gui;
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		PanelAgregarDiasProfesor agregarDiasProfesor =  new PanelAgregarDiasProfesor(principal,this);
		tabbedPane.addTab("Contabilidad",new ImageIcon(getClass().getResource("/resource/a8.png")),agregarDiasProfesor);
//		GenerarRemesasProfesores remesasProfesores = new GenerarRemesasProfesores(this);
//		tabbedPane.addTab("Generacion de Remesas",new ImageIcon(getClass().getResource("/resource/a8.png")),remesasProfesores);
		
		
		JPanel panel_1 = new JPanel();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 807, Short.MAX_VALUE)
					.addContainerGap())
				.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addGap(5)
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JButton btnNewButton = new JButton("Cerrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		panel_1.setLayout(new GridLayout(0, 3, 0, 0));
		
		JButton btnNewButton_1 = new JButton("<html><body>Imprimir Formato para<br\r\n>Apuntarse General</body></html>");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String bigList[] = { "01-ENERO", "02-FEBRERO","04-MARZO","04-ABRIL","05-MAYO","06-JUNIO","07-JULIO","08-AGOSTO","09-SEPTIEMBRE","10-OCTUBRE","10-NIOVIEMBRE","02-DICIEMBRE"};
				String selected = JOptionPane.showInputDialog(principal, "Seleccionar",
						"Seleccionar", JOptionPane.QUESTION_MESSAGE,null, bigList, "Titan").toString();
				
				if(selected!=null){
				ArrayList<Persona> arrayList=principal.getBaseDeDatos().getProfesores(principal.getBaseDeDatos().getCursoActual());
				
				String nombre="formatoApuntarseGeneral";
				
				
//				ArrayList<Persona> arrayList2=new ArrayList<>();
				
					try {
						new PDF_Formato_Apuntarse(principal,arrayList,selected,nombre);
					} catch (DocumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(ContabilidadProfesores.class.getResource("/resource/Pdf-icon1.png")));
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("<html><body>Imprimir Formato para Apuntarse<br>Con seleccionados</body></html>");
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				final JDialog dialog = new JDialog(principal,true);
				dialog.getContentPane().setLayout(new BorderLayout());
				JPanel jPanel = new JPanel(new GridLayout());
				JButton button = new JButton("Cerrar");
				final JKTable table = new JKTable();

				button.setIcon(new ImageIcon(ContabilidadProfesores.class.getResource("/resource/close1.png")));
				button.addActionListener( new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						dialog.dispose();
					}
				});
				jPanel.add(button);
				JButton button2=new JButton("Aceptar");
				button2.setIcon(new ImageIcon(ContabilidadProfesores.class.getResource("/resource/n41.png")));
				button2.addActionListener(new ActionListener() {
					
					private ArrayList<Persona> arrayList1;

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
//						if(table.getSelectedRow()!=-1){
//							String documento= table.getValueAt(table.getSelectedRow(), 0).toString();
//							PanelAsignarDiasProfesor panelAsignarDiasProfesor= new PanelAsignarDiasProfesor(principal,documento);
//							tabbedPane.addTab(documento,panelAsignarDiasProfesor);
//						}
						ArrayList<String> arrayList = new ArrayList<>();
						for(int index=0;index<table.getRowCount(); index++){
							Boolean c = (Boolean)table.getValueAt(index, 2);
							if(c){
								arrayList.add(""+table.getValueAt(index, 0));
							}
						}
						String bigList[] = { "01-ENERO", "02-FEBRERO","04-MARZO","04-ABRIL","05-MAYO","06-JUNIO","07-JULIO","08-AGOSTO","09-SEPTIEMBRE","10-OCTUBRE","10-NIOVIEMBRE","02-DICIEMBRE"};
						String selected = JOptionPane.showInputDialog(principal, "Seleccionar",
								"Seleccionar", JOptionPane.QUESTION_MESSAGE,null, bigList, "Titan").toString();
						
						if(selected!=null){
						
						
						arrayList1=principal.getBaseDeDatos().getProfesores(arrayList);
						
						String nombre="formatoApuntarseSeleccionados";
						
						
//						ArrayList<Persona> arrayList2=new ArrayList<>();
						
							try {
								new PDF_Formato_Apuntarse(principal,arrayList1,selected,nombre);
							} catch (DocumentException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						
						}
						dialog.dispose();
					}
				});
				jPanel.add(button2);
//				JPanel panel1= new JPanel();
				table.setRowHeight(40);
				
				dialog.setTitle("Seleccion");
			
//				table.addColumn("Foto");
				table.addColumn("Documento");
				table.addColumn("Apellidos y Nombre");
				table.addColumn("Seleccion");
		
				table.activeCheckBoxToColumn(table.getColumn("Seleccion"), new JCheckBox());
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				JScrollPane jScrollPane = new JScrollPane(table);
				
				
				JPanel panel3= new JPanel(new FlowLayout(FlowLayout.LEFT));
				panel3.add(new JLabel("Buscador:"));
				final JTextField field=new JTextField(25);
				field.addKeyListener(new KeyListener() {
					@Override
					public void keyTyped(KeyEvent arg0) {
						// TODO Auto-generated method stub
						
					}
		
					@Override
					public void keyReleased(KeyEvent arg0) {
						// TODO Auto-generated method stub
						table.search(field.getText().toUpperCase());
					}
					
					@Override
					public void keyPressed(KeyEvent arg0) {
						// TODO Auto-generated method stub
						
					}
				});
				panel3.add(field);
				table.getColumn("Documento").setPreferredWidth(10);
				table.getColumn("Documento").setWidth(10);
				table.getColumn("Apellidos y Nombre").setPreferredWidth(210);
				table.getColumn("Apellidos y Nombre").setWidth(210);
				dialog.getContentPane().add(jScrollPane,BorderLayout.CENTER);
				dialog.getContentPane().add(panel3,BorderLayout.NORTH);
				
				jPanel.setPreferredSize(new Dimension(0,40));
				dialog.getContentPane().add(jPanel,BorderLayout.SOUTH);
				dialog.setSize(500,600);
				dialog.setLocationRelativeTo(principal);
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						principal.getBaseDeDatos().buscarProfesores(table);
					}
				}).start();
			
				dialog.setVisible(true);
				
				
				
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(ContabilidadProfesores.class.getResource("/resource/Pdf-icon1.png")));
		panel_1.add(btnNewButton_2);
		btnNewButton.setIcon(new ImageIcon(ContabilidadProfesores.class.getResource("/resource/close1.png")));
		panel_1.add(btnNewButton);
		panel.setLayout(gl_panel);
		
//		new Thread(this).start();
	}
}
