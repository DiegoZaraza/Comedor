package comedor;

import javax.print.PrintService;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;

import com.leyer.JKComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JCheckBox;

public class ConfigurarImpresoras extends JInternalFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ComedorGUI principal;
	private JKComboBox comboBox;
	private JKComboBox comboBox_1;
	private Impresora impresora;
	private JCheckBox checkBox;
	public ConfigurarImpresoras(ComedorGUI comedorGUI) {
		setTitle("Configuracion de Impresoras");
		impresora=new Impresora(comedorGUI);
		this.principal=comedorGUI;
		setBounds(10, 10, 763, 349);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lblImpresoraDeCarnets = new JLabel("<html></body>Impresora de Carnets:</body></html>");
		lblImpresoraDeCarnets.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblImpresoraDeCarnets.setIcon(new ImageIcon(ConfigurarImpresoras.class.getResource("/resource/impresoraCarnet.png")));
		
		JLabel lblImpresoraEstandar = new JLabel("<html><body>Impresora Estandar:</body></html>");
		lblImpresoraEstandar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblImpresoraEstandar.setIcon(new ImageIcon(ConfigurarImpresoras.class.getResource("/resource/impresoraEstandar.png")));
		
		JSeparator separator = new JSeparator();
		
		comboBox = new JKComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		comboBox_1 = new JKComboBox();
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		List<PrintService> x = impresora.getImpresoras();
		for(int index=0;index<x.size();index++){
			comboBox.addItem(x.get(index).getName());
			comboBox_1.addItem(x.get(index).getName());
		}
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		checkBox = new JCheckBox("Impresion Directa");
		boolean n=principal.getBaseDeDatos().getImpresionDirecta();
		if(n){
			checkBox.setSelected(true);
		}else
			checkBox.setSelected(false);
		checkBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(checkBox.isSelected()){
					principal.getBaseDeDatos().setImpresionDirecta(true);
				}else{
					principal.getBaseDeDatos().setImpresionDirecta(false);
				}
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblImpresoraDeCarnets, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox, 0, 521, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblImpresoraEstandar, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(checkBox)
								.addComponent(comboBox_1, GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE))))
					.addContainerGap())
				.addComponent(separator, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblImpresoraDeCarnets))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(24)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblImpresoraEstandar)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(checkBox)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnNewButton_1 = new JButton("Cerrar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(ConfigurarImpresoras.class.getResource("/resource/close.png")));
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				principal.getBaseDeDatos().setImpresoraCarnet(comboBox.getSelectedItem().toString());
				principal.getBaseDeDatos().setImpresoraEstandar(comboBox_1.getSelectedItem().toString());
				dispose();
			}
		});
		btnNewButton.setIcon(new ImageIcon(ConfigurarImpresoras.class.getResource("/resource/41.png")));
		panel_1.add(btnNewButton);
		panel.setLayout(gl_panel);
		
//		System.out.println(principal.getBaseDeDatos().getImpresoraCarnet());
//		System.out.println(principal.getBaseDeDatos().getImpresoraEstandar());
//		System.out.println();
		for(int index=0;index<comboBox.getItemCount();index++){
			System.out.println(comboBox.getItemAt(index));
			if(comboBox.getItemAt(index).toString().equalsIgnoreCase(
					principal.getBaseDeDatos().getImpresoraCarnet())){
				comboBox.setSelectedIndex(index);
			}
		}
		for(int index=0;index<comboBox_1.getItemCount();index++){
			if(comboBox_1.getItemAt(index).toString().equalsIgnoreCase(
					principal.getBaseDeDatos().getImpresoraEstandar())){
				comboBox_1.setSelectedIndex(index);
			}
		}
		

	}
}
