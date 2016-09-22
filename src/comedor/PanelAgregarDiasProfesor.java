package comedor;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.ImageIcon;

import com.leyer.JKTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PanelAgregarDiasProfesor extends JPanel  implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	private ComedorGUI principal;
	private JTabbedPane tabbedPane;
	public PanelAgregarDiasProfesor(ComedorGUI comedorGUI, ContabilidadProfesores contabilidadProfesores) {
		this.principal = comedorGUI;
		JButton btnNewButton = new JButton("Seleccionar Profesor(a)");
		btnNewButton.addActionListener(new ActionListener() {
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
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						if(table.getSelectedRow()!=-1){
							String documento= table.getValueAt(table.getSelectedRow(), 0).toString();
							PanelAsignarDiasProfesor panelAsignarDiasProfesor= new PanelAsignarDiasProfesor(principal,documento);
							tabbedPane.addTab(documento,panelAsignarDiasProfesor);
							dialog.dispose();
						}
					}
				});
				jPanel.add(button2);
//				JPanel panel1= new JPanel();
				table.setRowHeight(40);
				
				dialog.setTitle("Seleccion");
			
//				table.addColumn("Foto");
				table.addColumn("Documento");
				table.addColumn("Apellidos y Nombre");
		
		
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
		btnNewButton.setIcon(new ImageIcon(PanelAgregarDiasProfesor.class.getResource("/resource/newElement.png")));
		
		JPanel panel = new JPanel();
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setIcon(new ImageIcon(PanelAgregarDiasProfesor.class.getResource("/resource/search1.png")));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				String h=""+textField.getText();
//				for(int index=0; index<tabbedPane.getTabCount(); index++){
//				if(	tabbedPane.getTitleAt(index).equalsIgnoreCase(h)){
//						tabbedPane.setSelectedIndex(index);
//					}
//				}
				

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
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						if(table.getSelectedRow()!=-1){
							String documento= table.getValueAt(table.getSelectedRow(), 0).toString();
//							PanelAsignarDiasProfesor panelAsignarDiasProfesor= new PanelAsignarDiasProfesor(principal,documento);
							for(int index=0; index<tabbedPane.getTabCount(); index++){
//							tabbedPane.addTab(documento,panelAsignarDiasProfesor);
								if(tabbedPane.getTitleAt(index).equalsIgnoreCase(documento)){
									tabbedPane.setSelectedIndex(index);
								}
							}
							dialog.dispose();
						}
					}
				});
				jPanel.add(button2);
//				JPanel panel1= new JPanel();
				table.setRowHeight(40);
				
				dialog.setTitle("Buscador");
			
//				table.addColumn("Foto");
				table.addColumn("Documento");
				table.addColumn("Apellidos y Nombre");
		
		
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
		
		JButton btnListadoPorRecibos = new JButton("Listado Por Recibos");
		btnListadoPorRecibos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String bigList[] = { "RECIBO 1", "RECIBO 2",
						"RECIBO 3",
						"RECIBO 4","RECIBO 5","RECIBO 6","RECIBO 7","RECIBO 8","RECIBO 9","RECIBO 10"};
				String selected = JOptionPane.showInputDialog(principal, "Seleccionar",
						"Seleccionar", JOptionPane.QUESTION_MESSAGE,null, bigList, "Titan").toString();
				
				if(selected!=null){
					
					principal.getBaseDeDatos().getRecibos(selected);
					
				}
			}
		});
		btnListadoPorRecibos.setIcon(new ImageIcon(PanelAgregarDiasProfesor.class.getResource("/resource/Pdf-icon1.png")));
		
		JButton btnNewButton_1 = new JButton("Listado por Rango de Fechas");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				new DialogoFechaAltaBaja(principal).setVisible(true);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(PanelAgregarDiasProfesor.class.getResource("/resource/Pdf-icon1.png")));
		
		JButton btnNewButton_2 = new JButton("Listado Por Dias");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String bigList[] = { "Ordinario", "Extraordinario","Ambos"};
				String selected = JOptionPane.showInputDialog(principal, "Seleccionar",
						"Seleccionar", JOptionPane.QUESTION_MESSAGE,null, bigList, "Titan").toString();
				if(selected!=null){
//					principal.getBaseDeDatos().getRecibos(selected);
					if(!selected.equalsIgnoreCase("Ambos")){
						principal.getBaseDeDatos().getRecibosPorDias(selected);
					}else
					{
						principal.getBaseDeDatos().getRecibos();
					}
				}
				
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(PanelAgregarDiasProfesor.class.getResource("/resource/Pdf-icon1.png")));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 818, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnListadoPorRecibos)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_2)
					.addContainerGap(41, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBuscar)
						.addComponent(btnListadoPorRecibos, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE))
		);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		panel.add(tabbedPane);
		setLayout(groupLayout);
		btnListadoPorRecibos.setVisible(false);
		new Thread(this).start();

	}
	public PanelAgregarDiasProfesor getInstance(){
		return this;
	}
	public void addProfesor(String documento){
//		String documento= table.getValueAt(table.getSelectedRow(), 0).toString();
		PanelAsignarDiasProfesor panelAsignarDiasProfesor= new PanelAsignarDiasProfesor(principal,documento);
		tabbedPane.addTab(documento,panelAsignarDiasProfesor);
	
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		principal.getBaseDeDatos().getContabilidadProfesores(getInstance());
		
	}
}
