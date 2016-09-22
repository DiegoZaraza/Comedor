package comedor;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;

import com.leyer.JKComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JProgressBar;

public class DialogoExportFotos extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private final JPanel contentPanel = new JPanel();

	
	private ComedorGUI principal;


	private JKComboBox comboBox;


	private ArrayList<String> arrayList;


	private JProgressBar progressBar;
	public DialogoExportFotos(ComedorGUI gui) {
		super(gui);
		setTitle("Exportar Fotos de Grupo");
		this.principal=gui;
		setSize(447, 183);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
//		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblGrupos = new JLabel("Grupos:");
		
	 comboBox = new JKComboBox();
	 arrayList=new ArrayList<>();
	 principal.getBaseDeDatos().getGroups(comboBox, arrayList);
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(progressBar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
						.addComponent(comboBox, Alignment.LEADING, 0, 407, Short.MAX_VALUE)
						.addComponent(lblGrupos, Alignment.LEADING))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblGrupos)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(8))
		);
		contentPanel.setLayout(gl_contentPanel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(DialogoExportFotos.class.getResource("/resource/close1.png")));
		panel.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Procesar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						progressBar.setIndeterminate(true);
						progressBar.setString("Exportando fotos...");
						// TODO Auto-generated method stub
						JFileChooser chooser=new JFileChooser();
						chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
						int c = chooser.showSaveDialog(principal);
						if(c==JFileChooser.APPROVE_OPTION){
							String gc=chooser.getSelectedFile().toString()+""+File.separator+""+comboBox.getSelectedItem().toString();
							String code=arrayList.get(comboBox.getSelectedIndex());
							principal.getBaseDeDatos().exportFotos(gc,code);
							progressBar.setIndeterminate(false);
							progressBar.setString("Finalizado.");
							JOptionPane.showMessageDialog(principal, "Exportacion completada!","Finalizado",JOptionPane.INFORMATION_MESSAGE);
							dispose();
						   }
					}
				}).start();
			}
		});
		btnNewButton.setIcon(new ImageIcon(DialogoExportFotos.class.getResource("/resource/n41.png")));
		panel.add(btnNewButton);
		setLocationRelativeTo(principal);
	}
}
