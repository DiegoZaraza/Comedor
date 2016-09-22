package comedor;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import com.leyer.JKTextUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JProgressBar;

public class ActualizarCurso extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldDiasDescontedos;
	private JTextField textFieldComisionBanco;

	private ComedorGUI principal;
	private JTextField textField;
	public ActualizarCurso(ComedorGUI comedorGUI,final String curso) {
//		super(comedorGUI,true);
		setTitle("Actualizar Datos del Curso Actual");
		setBounds(10, 10, 450, 269);
		this.principal = comedorGUI;
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		JKTextUtil textUtil=new JKTextUtil();
		JLabel lblDiasDelCurso = new JLabel("Dias del Curso:");
		
		final JLabel labelDiasCurso = new JLabel("0");
		labelDiasCurso.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblDiasDescontados = new JLabel("Dias Descontados:");
		
		textFieldDiasDescontedos = new JTextField();
		textFieldDiasDescontedos.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Comision del Banco:");
		
		textFieldComisionBanco = new JTextField();
		textFieldComisionBanco.setEnabled(false);
		textFieldComisionBanco.setColumns(10);
		
		final JLabel lblTotal = new JLabel("Total:");
		
		final JLabel labelTotalCurso = new JLabel("0");
		labelTotalCurso.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		final JProgressBar progressBar = new JProgressBar();
		
		JLabel lblJuevesComedor = new JLabel("Jueves Comedor:");
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setText(""+principal.getBaseDeDatos().getJuevesComedor(principal.getBaseDeDatos().getCursoActual()));	
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblDiasDelCurso)
									.addGap(6)
									.addComponent(labelDiasCurso))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
									.addGap(4)
									.addComponent(textFieldComisionBanco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblDiasDescontados, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
									.addGap(4)
									.addComponent(textFieldDiasDescontedos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblTotal)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(labelTotalCurso))))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblJuevesComedor)
							.addGap(18)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDiasDelCurso)
						.addComponent(labelDiasCurso))
					.addGap(11)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblDiasDescontados))
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(textFieldDiasDescontedos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblTotal)
							.addComponent(labelTotalCurso)))
					.addGap(11)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel))
						.addComponent(textFieldComisionBanco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblJuevesComedor)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(ActualizarCurso.class.getResource("/resource/close1.png")));
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Actualizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				progressBar.setIndeterminate(true);
				principal.getBaseDeDatos().updateRemesas(curso,textFieldDiasDescontedos.getText());
				principal.getBaseDeDatos().updateJuevesComedor(textField.getText());
				progressBar.setIndeterminate(false);
				JOptionPane.showMessageDialog(principal, "Actualizacion Finalizada!","Exito",JOptionPane.INFORMATION_MESSAGE);
				dispose();
				
			}
		});
		btnNewButton.setIcon(new ImageIcon(ActualizarCurso.class.getResource("/resource/update.png")));
		panel_1.add(btnNewButton);
		getContentPane().setLayout(groupLayout);
		
		textUtil.validate(textFieldDiasDescontedos, JKTextUtil.MODE_INT);
		textFieldComisionBanco.setText(""+principal.getBaseDeDatos().getComisionBanco(curso));
		labelDiasCurso.setText(""+principal.getBaseDeDatos().getDias(curso));
		textFieldDiasDescontedos.setText(""+principal.getBaseDeDatos().getDiasDescontados(curso));
		
		try{
			int y= Integer.parseInt(labelDiasCurso.getText());
			int h= Integer.parseInt(textFieldDiasDescontedos.getText());
//			System.out.println(h);
			labelTotalCurso.setText(""+(y-h));
		}catch(Exception e){
			
		}
		textFieldDiasDescontedos.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
//				prin
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				try{
					int y= Integer.parseInt(labelDiasCurso.getText());
					int h= Integer.parseInt(textFieldDiasDescontedos.getText());
//					System.out.println(h);
					labelTotalCurso.setText(""+(y-h));
				}catch(Exception e){
					
				}
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});

	}
}
