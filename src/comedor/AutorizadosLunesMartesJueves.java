package comedor;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import com.leyer.JKTable;
import comedor.pdfs.Persona;

public class AutorizadosLunesMartesJueves extends JInternalFrame implements Runnable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ComedorGUI principal;
	private JKTable table;
	private JProgressBar progressBar;
	private JTextField textField;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	public AutorizadosLunesMartesJueves(ComedorGUI comedorGUI) {
		setTitle("Control de Autorizados al Comedor");
		setBounds(10, 10, 754, 566);
		this.principal=comedorGUI;
		JTabbedPane tabbedPane=new JTabbedPane();
		
		JPanel panel = new JPanel()
		{
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
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Listado General", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JButton btnNewButton = new JButton("Cerrar");
		btnNewButton.setIcon(new ImageIcon(AutorizadosLunesMartesJueves.class.getResource("/resource/close1.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
			}
		});
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(null, "-", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new TitledBorder(null, "Buscador", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(20)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(panel_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(panel_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(panel_8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_7, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(panel_8, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(panel_7, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
							.addGap(16)))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_4, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		JLabel lblNewLabel_2 = new JLabel("Termino a Buscar:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.addKeyListener(new KeyListener() {
			
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
		GroupLayout gl_panel_8 = new GroupLayout(panel_8);
		gl_panel_8.setHorizontalGroup(
			gl_panel_8.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_8.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_8.setVerticalGroup(
			gl_panel_8.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_8.createSequentialGroup()
					.addGap(3)
					.addGroup(gl_panel_8.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		);
		panel_8.setLayout(gl_panel_8);
		panel_7.setLayout(new GridLayout(0, 1, 0, 0));
		
		 progressBar = new JProgressBar();
		progressBar.setOrientation(SwingConstants.VERTICAL);
		progressBar.setStringPainted(true);
		panel_7.add(progressBar);
		panel_7.setOpaque(false);
		
		JLabel lblNoAutorizadosjueves = new JLabel("No. Autorizados (JUEVES):");
		panel_5.add(lblNoAutorizadosjueves);
		
		label_3 = new JLabel("0");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_5.add(label_3);
		
		JLabel lblNoAutorizados = new JLabel("No. Autorizados(LUNES, MARTES):\r\n");
		panel_6.add(lblNoAutorizados);
		
		label_2 = new JLabel("0");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_6.add(label_2);
		
		JLabel lblComedorJueves = new JLabel("Comedor: JUEVES:");
		panel_4.add(lblComedorJueves);
		
		label_1 = new JLabel("0");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_4.add(label_1);
		
		JLabel lblComedorLunesMartes = new JLabel("Comedor LUNES, MARTES:");
		panel_3.add(lblComedorLunesMartes);
		
		label = new JLabel("0");
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_3.add(label);
		
		JLabel lblNewLabel = new JLabel("Alumnos Matriculados:");
		panel_2.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(principal.getBaseDeDatos().getNroAlumnos());
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_2.add(lblNewLabel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		table = new JKTable();
		table.setFont(new Font("Tahoma", Font.BOLD, 11));
		table.addColumn("Apellidos y Nombres");
		table.addColumn("NIA");
		table.addColumn("LUNES, MARTES");
		table.addColumn("JUEVES");
		table.getColumn("Apellidos y Nombres").setPreferredWidth(300);
		table.getColumn("Apellidos y Nombres").setWidth(300);
	
		JPopupMenu jPopupMenu=new JPopupMenu();
		table.setComponentPopupMenu(jPopupMenu);
		JMenuItem item= new JMenuItem("Copiar NIA");
		jPopupMenu.add(item);
		item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String nia=table.getValueAt(table.getSelectedRow(), 1).toString();
				StringSelection selection= new StringSelection(nia);
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
			}
		});
		
		table.activeCheckBoxToColumn(table.getColumn("LUNES, MARTES"), new JCheckBox());
		table.activeCheckBoxToColumn(table.getColumn("JUEVES"), new JCheckBox());
		
//		table.getColumn("JUEVES").setCellEditor(new CheckBoxCellEditor(new JCheckBox()));
//		table.getColumn("JUEVES").setCellRenderer(new CheckBoxTableRenderer());
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
//				System.out.println(table.getSelectedColumn());
				if(table.getSelectedColumn()==2){
					if(!((Boolean)table.getValueAt(table.getSelectedRow(),2))){
						table.setValueAt(Boolean.TRUE, table.getSelectedRow(), 2);
						String nia=table.getValueAt(table.getSelectedRow(), 1).toString();
						principal.getBaseDeDatos().autorizadoComedorLunesMartes(nia,true);
						
					}else{
						table.setValueAt(Boolean.FALSE, table.getSelectedRow(), 2);
						String nia=table.getValueAt(table.getSelectedRow(), 1).toString();
						principal.getBaseDeDatos().autorizadoComedorLunesMartes(nia,false);
					}
				}
				
				if(table.getSelectedColumn()==3){
						boolean b=((Boolean)table.getValueAt(table.getSelectedRow(),3));
//						System.out.println(b);
						String nia=table.getValueAt(table.getSelectedRow(), 1).toString();
						principal.getBaseDeDatos().autorizadoComedorJueves(nia,b);
					
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
		JScrollPane scrollPane = new JScrollPane(table);
		
		panel_1.add(scrollPane);
		panel.setLayout(gl_panel);
		panel_8.setOpaque(false);
		panel_1.setOpaque(false);
		progressBar.setFont(new Font("arial",Font.BOLD,12));
		evaluator();
		tabbedPane.addTab("Usuarios Comedor", panel);
		new Thread(this).start();
		
	}
	
	public void evaluator(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true){
					try {
						Thread.sleep(1000);
						String g=principal.getBaseDeDatos().getComedorLunesMartes();
						label.setText(g);
						label_1.setText(principal.getBaseDeDatos().getComedorJueves());
						label_3.setText(principal.getBaseDeDatos().getNoAutorizadosJueves());
						label_2.setText(principal.getBaseDeDatos().getNoAutorizadosLunesMartes());
//						System.out.println("Evaluando");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();;
	}
	         
	@Override
	public void run() {
		// TODO Auto-generated method stub
		progressBar.setIndeterminate(true);
		progressBar.setString("0%");
		String cursoActual=principal.getBaseDeDatos().getCursoActual();
		ArrayList<Persona> a = principal.getBaseDeDatos().getUsuariosComedor(cursoActual);
		for(int index=0; index<a.size(); index++){
			
			Boolean n1=principal.getBaseDeDatos().getSelectedLunesMartes(a.get(index).getNia());
			Boolean n2=principal.getBaseDeDatos().getSelectedJueves(a.get(index).getNia());
			table.addRow(a.get(index).getNombreCompleto(),a.get(index).getNia(),n1,n2);
		
		}
		progressBar.setIndeterminate(false);
		progressBar.setString("100%");
		
	}
}
