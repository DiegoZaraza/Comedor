package comedor;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import com.itextpdf.text.DocumentException;
import com.leyer.JKComboBox;
import com.leyer.JKCoreBar;
import com.leyer.JKMenu;
import com.leyer.JKTable;

import comedor.pdfs.PDF_Carta_1;
import comedor.pdfs.PDF_Carta_2;
import comedor.pdfs.PDF_Carta_3;
import comedor.pdfs.Persona;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import javax.swing.JSeparator;
import javax.swing.UIManager;

public class ContabilidadAlumnos extends JInternalFrame implements Runnable
{
	private static final long serialVersionUID = 1L;
	private ComedorGUI principal;
	private JKTable table;
	private JKTable tableFechas;
	private ArrayList<String>    listFechas;
	private JKMenu jkMenu;
	private JKTable tableRemesas;
	private JPanel panel_5;
	private JKComboBox comboBox;
	private JTextField textField_1;
	private JPanel panel_6;
	private ArrayList<String> arrayList;
	private JKTable tableDevoluciones;
	private JKComboBox nRemesas;
	private ArrayList<String> arrayListIDGeneradas;
	private JTextField motivo;
	private JTextField observacion;
	private ArrayList<String> arrayListDevoluciones;
	private JKTable tableCartas;
	private ArrayList<String> arrayListIDRemesasDevoluciones;

	@Override
	public void paintComponent(Graphics g) {
		setOpaque(false);
		g.drawImage(
				new ImageIcon(getClass().getResource(
						"/resource/e2.jpg"))
						.getImage(), 0, 0, getWidth(), getHeight(),
				null);


}
	public ContabilidadAlumnos(ComedorGUI gui) {
		setTitle("Contabilidad de Usuarios");
		setOpaque(false);
		
	
		setBounds(10, 10, 865, 604);
		
		JPanel panel = new JPanel(){
			

				/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

				@Override
				public void paintComponent(Graphics g) {
					setOpaque(false);
					g.drawImage(
							new ImageIcon(getClass().getResource(
									"/resource/e2.jpg"))
									.getImage(), 0, 0, getWidth(), getHeight(),
							null);

				   }
			};
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 696, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 74, Short.MAX_VALUE)
		);
		panel.setLayout(gl_panel);
		this.principal=gui;
		
		final JKCoreBar coreBar=new JKCoreBar(JKCoreBar.MODE_1);
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
					tableFechas.clearTable();
					listFechas.clear();
				}catch(Exception e){
					
				}
				principal.getBaseDeDatos().getFechasPeriodo(table.getValueAt(table.getSelectedRow(), 1).toString(),tableFechas,listFechas);
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
		JScrollPane scrollPane=new JScrollPane(table);
		
		jpanel.add(panel2, BorderLayout.NORTH);
		jpanel.add(scrollPane,BorderLayout.CENTER);
		jkMenu.add(jpanel);
		jkMenu.setFont(new Font("arial",Font.BOLD,12));
		coreBar.add(jkMenu);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.add(coreBar);
		
		JPanel panel_8 = new JPanel(){
			

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				setOpaque(false);
				g.drawImage(
						new ImageIcon(getClass().getResource(
								"/resource/e2.jpg"))
								.getImage(), 0, 0, getWidth(), getHeight(),
						null);

			   }
		};
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_8, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 839, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 839, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_8, GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE))
		);
		
	arrayListIDRemesasDevoluciones = new ArrayList<>();
		JPanel panel_3 = new JPanel();
		
		 tableRemesas = new JKTable();
		 tableRemesas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 tableRemesas.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			
				if(tableRemesas.getSelectedRow()!= tableRemesas.getRowCount()-1){
					tableRemesas.clearSelection();
					tableRemesas.setSelectedRow(tableRemesas.getRowCount()-1);
//					JOptionPane.showMessageDialog(principal, "No valido!","",JOptionPane.WARNING_MESSAGE);
					
				}
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
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		 tableRemesas.addColumn("Recibo");
		 tableRemesas.addColumn("Remesa");
		 tableRemesas.addColumn("Estatus");
		 tableRemesas.getColumn("Recibo").setPreferredWidth(10);
		 tableRemesas.getColumn("Recibo").setWidth(10);
		 tableRemesas.getColumn("Estatus").setPreferredWidth(390);
		 tableRemesas.getColumn("Estatus").setWidth(390);
		 //		tableRemesas.setFont(new Font("arial",Font.BOLD,17));
		 		tableRemesas.setRowHeight(15);
		 		tableRemesas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		 		tableRemesas.activeIconsToRows(tableRemesas.getColumn("Estatus"));
		 		panel_3.setLayout(new BorderLayout(0, 0));
		 		JScrollPane scrollPane_1 = new JScrollPane(tableRemesas);
		 		panel_3.add(scrollPane_1,BorderLayout.CENTER);
		 		
		 			
		 			panel_5 = new JPanel();
		 			panel_5.setPreferredSize(new Dimension(230,0));
		 			panel_5.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Remesa Seleccionada", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		 			panel_3.add(panel_5, BorderLayout.EAST);
		 			
		 			JLabel lblEstado = new JLabel("Estado:");
		 			
		 			comboBox = new JKComboBox();
		 			comboBox.addItem("Seleccionar");
		 			comboBox.addItem("PAGADO");
		 			comboBox.addItem("PAGADO CON COMISION");
		 			comboBox.addActionListener(new ActionListener() {
		 				
		 				@Override
		 				public void actionPerformed(ActionEvent arg0) {
		 					// TODO Auto-generated method stub
		 					if(comboBox.getSelectedItem().toString().equalsIgnoreCase("PAGADO CON COMISION")){
		 						panel_6.setVisible(true);
		 					}else{
		 						panel_6.setVisible(false);
		 					   }
		 					}
		 			});
		 			
		 			JSeparator separator = new JSeparator();
		 			
		 			JButton btnNewButton = new JButton("Aceptar");
		 			btnNewButton.addActionListener(new ActionListener() {
		 				public void actionPerformed(ActionEvent arg0) {
		 					if(comboBox.getSelectedItem().toString().equalsIgnoreCase("PAGADO")){
		 						if(tableRemesas.getSelectedRow()!=-1){
		 							principal.getBaseDeDatos().updateRemesa(arrayList.get(tableRemesas.getSelectedRow()),"PAGADO");
		 							String id=listFechas.get(tableFechas.getSelectedRow());
//						principal.getBaseDeDatos().getRemesas(id,tableRemesas,arrayList);	
		 							principal.getBaseDeDatos().getRemesasGeneradas(id,tableRemesas,arrayList,arrayListIDGeneradas,nRemesas);
		 						}else{
		 							return;
		 						}
		 					}else if(comboBox.getSelectedItem().toString().equalsIgnoreCase("PAGADO CON COMISION")){
		 						String valor = textField_1.getText();
		 						float comision =0;
		 						try{
		 						comision=Float.parseFloat(valor);
		 						if(comision==0){
		 							JOptionPane.showMessageDialog(principal, "Valor no valido!","No valido",JOptionPane.WARNING_MESSAGE);
		 							return;
		 							}
		 						}catch(Exception e){
		 							JOptionPane.showMessageDialog(principal, "Valor no valido!","No valido",JOptionPane.WARNING_MESSAGE);
		 							return;
		 						}
		 						principal.getBaseDeDatos().updateRemesa(arrayList.get(tableRemesas.getSelectedRow()),Float.parseFloat(textField_1.getText()));
		 						String id=listFechas.get(tableFechas.getSelectedRow());
//					principal.getBaseDeDatos().getRemesas(id,tableRemesas,arrayList);	
		 						principal.getBaseDeDatos().getRemesasGeneradas(id,tableRemesas,arrayList,arrayListIDGeneradas,nRemesas);
		 						
		 					}else{
		 						
		 					}
		 				}
		 			});
		 			btnNewButton.setIcon(new ImageIcon(ContabilidadAlumnos.class.getResource("/resource/n41.png")));
		 			
		 			panel_6 = new JPanel();
		 			panel_6.setVisible(false);
		 			GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		 			gl_panel_5.setHorizontalGroup(
		 				gl_panel_5.createParallelGroup(Alignment.LEADING)
		 					.addGroup(gl_panel_5.createSequentialGroup()
		 						.addContainerGap()
		 						.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
		 							.addGroup(gl_panel_5.createSequentialGroup()
		 								.addComponent(panel_6, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
		 								.addContainerGap())
		 							.addGroup(gl_panel_5.createSequentialGroup()
		 								.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		 								.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
		 									.addGroup(gl_panel_5.createSequentialGroup()
		 										.addComponent(lblEstado)
		 										.addPreferredGap(ComponentPlacement.RELATED)
		 										.addComponent(comboBox, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
		 									.addGroup(gl_panel_5.createSequentialGroup()
		 										.addPreferredGap(ComponentPlacement.RELATED)
		 										.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
		 										.addContainerGap())))))
		 			);
		 			gl_panel_5.setVerticalGroup(
		 				gl_panel_5.createParallelGroup(Alignment.LEADING)
		 					.addGroup(gl_panel_5.createSequentialGroup()
		 						.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
		 							.addGroup(gl_panel_5.createSequentialGroup()
		 								.addGap(9)
		 								.addComponent(lblEstado))
		 							.addGroup(gl_panel_5.createSequentialGroup()
		 								.addGap(4)
		 								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
		 						.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
		 							.addGroup(gl_panel_5.createSequentialGroup()
		 								.addGap(65)
		 								.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		 							.addGroup(gl_panel_5.createSequentialGroup()
		 								.addPreferredGap(ComponentPlacement.UNRELATED)
		 								.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
		 								.addPreferredGap(ComponentPlacement.RELATED)
		 								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
		 						.addGap(33))
		 			);
		 			
		 			JLabel lblValorDeComision = new JLabel("Valor de Comision:");
		 			
		 			textField_1 = new JTextField();
		 			textField_1.setColumns(10);
		 			GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		 			gl_panel_6.setHorizontalGroup(
		 				gl_panel_6.createParallelGroup(Alignment.LEADING)
		 					.addGroup(gl_panel_6.createSequentialGroup()
		 						.addComponent(lblValorDeComision)
		 						.addPreferredGap(ComponentPlacement.RELATED)
		 						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
		 						.addContainerGap(57, Short.MAX_VALUE))
		 			);
		 			gl_panel_6.setVerticalGroup(
		 				gl_panel_6.createParallelGroup(Alignment.LEADING)
		 					.addGroup(gl_panel_6.createSequentialGroup()
		 						.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
		 							.addComponent(lblValorDeComision)
		 							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		 						.addContainerGap(37, Short.MAX_VALUE))
		 			);
		 			panel_6.setLayout(gl_panel_6);
		 			panel_5.setLayout(gl_panel_5);
		 			panel_3.setOpaque(false);
		 			panel_5.setOpaque(false);
		 			panel_6.setOpaque(false);
		
		JPanel panel_2 = new JPanel();
		
		tableFechas=new JKTable();
		tableFechas.setRowHeight(20);
		tableFechas.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				String nia=table.getValueAt(table.getSelectedRow(), 1).toString();
				String id=listFechas.get(tableFechas.getSelectedRow());
				principal.getBaseDeDatos().getRemesasGeneradas(id,tableRemesas,arrayList,arrayListIDGeneradas,nRemesas);
//				principal.getBaseDeDatos().getRemesasGeneradas(id,2);
				
				;
				principal.getBaseDeDatos().getDevoluciones(tableDevoluciones,arrayListDevoluciones,nia,id,arrayListIDRemesasDevoluciones);

				
			}	
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		tableFechas.addColumn("F. Alta");
		tableFechas.addColumn("F. Bajas");
		tableFechas.addColumn("Beca");
		tableFechas.addColumn("Tipo Usuario");
		JScrollPane scrollPane1 = new JScrollPane(tableFechas);
		
			tableFechas.getTableHeader().setReorderingAllowed(false);
			tableFechas.getTableHeader().setResizingAllowed(false);
			
			panel_2.setLayout(new BorderLayout());
			panel_2.add(scrollPane1,BorderLayout.CENTER);
			panel_2.setOpaque(false);
		
		JButton btnNewButton_1 = new JButton("Generar Remesa");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getRemesas();
			}
		});
		
		JButton btnMarcarTodoComo = new JButton("Marcar Todo como Pagado");
		btnMarcarTodoComo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String id=listFechas.get(tableFechas.getSelectedRow());

				for(int index=0; index<arrayList.size();index++){
					principal.getBaseDeDatos().updateRemesa(""+arrayList.get(index),"PAGADO");
				}
				
				principal.getBaseDeDatos().getRemesasGeneradas(id,tableRemesas,arrayList,arrayListIDGeneradas,nRemesas);
			}
		});
		
		JPanel panel_1 = new JPanel();
		
		tableDevoluciones =new JKTable();
		tableDevoluciones.addColumn("Motivo");
		tableDevoluciones.addColumn("Observaciones");
		tableDevoluciones.addColumn("Porcentaje %");
		tableDevoluciones.addColumn("Remesa");
		tableDevoluciones.getColumn("Observaciones").setPreferredWidth(240);
		tableDevoluciones.getColumn("Observaciones").setWidth(240);
		
		tableDevoluciones.getColumn("Remesa").setPreferredWidth(14);
		tableDevoluciones.getColumn("Remesa").setWidth(14);
		
		tableDevoluciones.getColumn("Porcentaje %").setPreferredWidth(24);
		tableDevoluciones.getColumn("Porcentaje %").setWidth(24);
		
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane  scrollPane2=new JScrollPane(tableDevoluciones);
		panel_1.add(scrollPane2);
		panel_1.setOpaque(false);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(null, "Nueva Devolucion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblPorcentaje = new JLabel("Porcentaje %:");
		
		final JKComboBox porcentaje = new JKComboBox();
		porcentaje.setFont(new Font("Tahoma", Font.BOLD, 12));
		porcentaje.addItem("25%");
		porcentaje.addItem("50%");
		porcentaje.addItem("75%");
		porcentaje.addItem("100%");
		
		JLabel lblRemesa = new JLabel("Remesa:");
		
		nRemesas = new JKComboBox();
		nRemesas.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		motivo = new JTextField();
		motivo.setColumns(10);
		
		JLabel lblMotivo = new JLabel("Motivo:");
		JLabel lblObservacion = new JLabel("Observacion:");
		
		observacion = new JTextField();
		observacion.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Procesar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				if(motivo.getText().length()==0){
					JOptionPane.showMessageDialog(principal, "Debe especificar un motivo!","Motivo",JOptionPane.WARNING_MESSAGE);
					return;
				}
				int c=JOptionPane.showConfirmDialog(principal, "Esta seguro(a) de aplicar esta Devolucion?","Confirmacion",JOptionPane.OK_OPTION);
				if(c== JOptionPane.OK_OPTION){

					String nia=table.getValueAt(table.getSelectedRow(), 1).toString();
					String id=listFechas.get(tableFechas.getSelectedRow());
					principal.getBaseDeDatos().addDevolucion(nia,nRemesas.getSelectedItem().toString(),arrayListIDGeneradas.get(nRemesas.getSelectedIndex()),motivo.getText(),observacion.getText(),porcentaje.getSelectedItem().toString(),id);
					principal.getBaseDeDatos().getDevoluciones(tableDevoluciones,arrayListDevoluciones,nia,id,arrayListIDRemesasDevoluciones);
//					String id=listFechas.get(tableFechas.getSelectedRow());
					principal.getBaseDeDatos().getRemesasGeneradas(id,tableRemesas,arrayList,arrayListIDGeneradas,nRemesas);
					
				}
			}
		});
		
		btnNewButton_2.setIcon(new ImageIcon(ContabilidadAlumnos.class.getResource("/resource/n41.png")));
		
//		comboBox_2.addActionListener( new  ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				
////				String n= arrayList.get(tableRemesas.getSelectedRow());
////				principal.getBaseDeDatos().getRemesas(comboBox_2);
//				
//				
//				
//				
//
//			}
//		});		
		
		GroupLayout gl_panel_7 = new GroupLayout(panel_7);
		gl_panel_7.setHorizontalGroup(
			gl_panel_7.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_7.createSequentialGroup()
					.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_7.createSequentialGroup()
							.addComponent(lblPorcentaje)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(porcentaje, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblRemesa)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(nRemesas, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_panel_7.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblMotivo)
							.addGap(10)
							.addComponent(motivo, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_panel_7.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
								.addComponent(lblObservacion)
								.addComponent(observacion, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_panel_7.setVerticalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_7.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_7.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPorcentaje)
						.addComponent(porcentaje, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRemesa)
						.addComponent(nRemesas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_7.createParallelGroup(Alignment.BASELINE)
						.addComponent(motivo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMotivo))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblObservacion)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(observacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
					.addGap(48))
		);
		panel_7.setLayout(gl_panel_7);
		panel_7.setOpaque(false);
		
		JPanel panel_4 = new JPanel();
		
		
		panel_4.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setIcon(new ImageIcon(ContabilidadAlumnos.class.getResource("/resource/close1.png")));
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		panel_4.add(btnCerrar);
		panel_4.setOpaque(false);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton btnNewButton_3 = new JButton("Carta de Cobro 1");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tableDevoluciones.getSelectedRow()!=-1){
					
					String id =arrayListIDRemesasDevoluciones.get(tableDevoluciones.getSelectedRow());
					int l=JOptionPane.showConfirmDialog(principal, "Esta seguro(a) de generar esta carta de Cobro para la Devolucion seleccionada?","Confirmacion",JOptionPane.INFORMATION_MESSAGE);
					if(l== JOptionPane.OK_OPTION){
						
						String nia=table.getValueAt(table.getSelectedRow(), 1).toString();
						principal.getBaseDeDatos().updateStatusCarta1(id,nia);

						String h=principal.getBaseDeDatos().getUltimaRemesa(nia);
						
						System.out.println(h);
						try {
							
							new PDF_Carta_1(principal, nia,h);
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (DocumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
//						String nia=table.getValueAt(table.getSelectedRow(), 1).toString();
						principal.getBaseDeDatos().getEstadoCartas(id,tableCartas,nia);
						
						
					}
					
				}else{
					JOptionPane.showMessageDialog(principal, "Debe seleccionar una devolucion","Seleccion vacia",JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
		});
		
		JButton btnCartaDeCobro_1 = new JButton("Carta de Cobro 2");
		btnCartaDeCobro_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tableDevoluciones.getSelectedRow()!=-1){
					
					String id =arrayListIDRemesasDevoluciones.get(tableDevoluciones.getSelectedRow());
					int l=JOptionPane.showConfirmDialog(principal, "Esta seguro(a) de generar esta carta de Cobro para la Devolucion seleccionada?","Confirmacion",JOptionPane.INFORMATION_MESSAGE);
					if(l== JOptionPane.OK_OPTION){
						
						String nia=table.getValueAt(table.getSelectedRow(), 1).toString();
						principal.getBaseDeDatos().updateStatusCarta2(id,nia);

						String h=principal.getBaseDeDatos().getUltimaRemesa(nia);
						
						System.out.println(h);
						try {
							
							new PDF_Carta_2(principal, nia,h);
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (DocumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
//						String nia=table.getValueAt(table.getSelectedRow(), 1).toString();
						principal.getBaseDeDatos().getEstadoCartas(id,tableCartas,nia);
						
						
					}
					
				}else{
					JOptionPane.showMessageDialog(principal, "Debe seleccionar una devolucion","Seleccion vacia",JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
		});
		
		JButton btnCartaDeCobro = new JButton("Carta de Cobro 3");
		btnCartaDeCobro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tableDevoluciones.getSelectedRow()!=-1){
					
					String id =arrayListIDRemesasDevoluciones.get(tableDevoluciones.getSelectedRow());
					int l=JOptionPane.showConfirmDialog(principal, "Esta seguro(a) de generar esta carta de Cobro para la Devolucion seleccionada?","Confirmacion",JOptionPane.INFORMATION_MESSAGE);
					if(l== JOptionPane.OK_OPTION){
						
						String nia=table.getValueAt(table.getSelectedRow(), 1).toString();
						principal.getBaseDeDatos().updateStatusCarta3(id,nia);

						try {
							
							new PDF_Carta_3(principal, nia);
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (DocumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
//						String nia=table.getValueAt(table.getSelectedRow(), 1).toString();
						principal.getBaseDeDatos().getEstadoCartas(id,tableCartas,nia);
						
						
					}
					
				}else{
					JOptionPane.showMessageDialog(principal, "Debe seleccionar una devolucion","Seleccion vacia",JOptionPane.WARNING_MESSAGE);
					return;
				}
			
				
			}
		});
//		new PDF_Carta_2(gui, nia, ultimno)
//		PDF_CartaCo		new PDF_Carta_3(gui, nia)
		GroupLayout gl_panel_8 = new GroupLayout(panel_8);
		gl_panel_8.setHorizontalGroup(
			gl_panel_8.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_8.createSequentialGroup()
					.addGroup(gl_panel_8.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_8.createSequentialGroup()
							.addGroup(gl_panel_8.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
								.addComponent(panel_9, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
								.addGroup(gl_panel_8.createSequentialGroup()
									.addContainerGap()
									.addComponent(btnNewButton_3)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnCartaDeCobro_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnCartaDeCobro)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_7, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 839, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_8.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnNewButton_1)
							.addGap(6)
							.addComponent(btnMarcarTodoComo))
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 839, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 839, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel_8.setVerticalGroup(
			gl_panel_8.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_8.createSequentialGroup()
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_8.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_1)
						.addComponent(btnMarcarTodoComo))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_8.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_7, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_8.createSequentialGroup()
							.addGap(12)
							.addGroup(gl_panel_8.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton_3)
								.addComponent(btnCartaDeCobro_1)
								.addComponent(btnCartaDeCobro))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_9, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel_9.setLayout(new GridLayout(0, 1, 0, 0));
		tableCartas= new JKTable();
		
		tableCartas.addColumn("Carta de Cobro #1");
		tableCartas.addColumn("Carta de Cobro #2");
		tableCartas.addColumn("Carta de Cobro #3");
		
		JScrollPane scrollPane_2 = new JScrollPane(tableCartas);
		
		tableDevoluciones.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
				if(tableDevoluciones.getSelectedRow()!= -1){
					String id= arrayListIDRemesasDevoluciones.get(tableDevoluciones.getSelectedRow());
					String nia=table.getValueAt(table.getSelectedRow(), 1).toString();
					principal.getBaseDeDatos().getEstadoCartas(id,tableCartas,nia);
					
				}
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		panel_9.add(scrollPane_2);
		panel_8.setLayout(gl_panel_8);
	    arrayListDevoluciones=new ArrayList<>();
		getContentPane().setLayout(groupLayout);
		arrayList = new ArrayList<>();
		arrayListIDGeneradas = new ArrayList<>();
	    listFechas = new ArrayList<>();
		
		panel.setOpaque(false);
		panel2.setOpaque(false);
		new Thread(this).start();
		
		
	}
	public void getRemesas(){
		if(tableFechas.getSelectedRow()!=-1){
			String id=listFechas.get(tableFechas.getSelectedRow());
			principal.getBaseDeDatos().getRemesas(id,tableRemesas,arrayList);	
			principal.getBaseDeDatos().getRemesasGeneradas(id,tableRemesas,arrayList,arrayListIDGeneradas,nRemesas);
		}
	}
	@Override
	public void run() {
		
		String cursoActual=principal.getBaseDeDatos().getCursoActual();
		ArrayList<Persona> xz = principal.getBaseDeDatos().getTodosLosAlumnos(cursoActual);
		for(int index=0;index<xz.size();index++){
			table.addRow(xz.get(index).getNombreCompleto(),xz.get(index).getNia());	
		}	
	}
}
