package comedor;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.jdesktop.xswingx.PromptSupport;

import com.leyer.JKTable;

public class PanelListadoProfesores extends JPanel implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField field;
	private JKTable jkTable;
	private JLabel jLabel;
	private ComedorGUI principal;

	public PanelListadoProfesores(final ComedorGUI principal){
		setLayout(new BorderLayout());
		this.principal = principal;
		jkTable = new JKTable();
		jkTable.addColumn("Foto");
		jkTable.addColumn("Documento");
		jkTable.addColumn("Nombres");
		jkTable.addColumn("Apellido 1");
		jkTable.addColumn("Apellido 2");
		jkTable.addColumn("Sexo");
		jkTable.addColumn("Email");
//		jkTable.addColumn("Grupo");
//		jkTable.addColumn("Email");
		JScrollPane jScrollPane = new JScrollPane(jkTable);

		jkTable.activeIconsToRows(jkTable.getColumn("Foto"));
		add(jScrollPane,BorderLayout.CENTER);
		
		JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jPanel.add(new JLabel("NIA/Nombres o Apellidos:"));
		field = new JTextField(35);
		PromptSupport.setPrompt("Termino a Buscar...",field);
		field.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				jkTable.search(field.getText().toUpperCase());
				jLabel.setText("<html><body>Nro. de Resultados: <b><font color='blue'>"+jkTable.getRowCount()+"</font></b></body></html>");
			}	
			@Override
			public void keyPressed(KeyEvent arg0) {
			
				
			}
		});
		field.setBorder(BorderFactory.createTitledBorder(""));
//		jkTable.setFont(new Font("arial",Font.PLAIN,13));
		jPanel.add(field);
		jPanel.setBorder(BorderFactory.createTitledBorder("Buscador"));
		add(jPanel,BorderLayout.NORTH);
		
//		jkTable.getColumn("Grupo").setPreferredWidth(158);
//		jkTable.getColumn("Grupo").setWidth(158);
		
		jkTable.setRowHeight(60);
		
		jkTable.getColumn("Foto").setPreferredWidth(0);
		jkTable.getColumn("Foto").setWidth(0);
	
		jkTable.getColumn("Documento").setPreferredWidth(43);
		jkTable.getColumn("Documento").setWidth(43);
		
		jkTable.getColumn("Nombres").setPreferredWidth(100);
		jkTable.getColumn("Nombres").setWidth(100);
		
		jkTable.getColumn("Email").setPreferredWidth(170);
		jkTable.getColumn("Email").setWidth(170);
		
		

//		jkTable.getColumn("Telefono").setPreferredWidth(8);
//		jkTable.getColumn("Telefono").setWidth(8);
		
		
		JPopupMenu jPopupMenu = new JPopupMenu();
		JMenuItem itemcOPY = new JMenuItem("Copiar Documento");
		itemcOPY.setIcon(new ImageIcon(getClass().getResource("/resource/copy.png")));
		jPopupMenu.add(itemcOPY);
		itemcOPY.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(jkTable.getSelectedRow()!=-1){
					String nia=jkTable.getValueAt(jkTable.getSelectedRow(), 1).toString();
					StringSelection arg1 = new StringSelection(nia);
					Toolkit.getDefaultToolkit().getSystemClipboard().setContents(arg1,null);
				}
				
			}
		});
		jkTable.setComponentPopupMenu(jPopupMenu);
		
		jkTable.setSelectionBackground(Color.LIGHT_GRAY);
		jLabel = new JLabel("<html><body>Nro. de Resultados: <b><font color='blue'>0</font></b></body></html>");
		
		jkTable.setBackground(JKTable.MODE_JK2, new ImageIcon(getClass().getResource("/resource/e2.jpg")), jScrollPane);
		JPanel jPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jPanel2.add(jLabel);
		jkTable.setSelectionForeground(Color.blue);
		jLabel.setFont(new Font("arial",Font.BOLD,12));
		jkTable.setFont(new Font("tahoma",Font.BOLD,11));
		add(jLabel,BorderLayout.SOUTH);
		new Thread(this).start();
	}

	@Override
	public void run() {
		principal.getBaseDeDatos().buscarProfesores("",jkTable,jLabel);

		
	}
}