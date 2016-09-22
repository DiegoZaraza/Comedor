package comedor;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;

import com.leyer.JKComboBox;
import com.toedter.calendar.JDateChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class DialogoFechaAltaBaja extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JDateChooser textField;
	private JDateChooser textField_1;

	
	public Date getfechaBaja() {
		try {
			new Date(textField_1.getDateEditor().getDate().getTime()).toString();
		} catch (Exception c) {
			return new Date(new java.util.Date().getTime());
		}
		return new Date(textField_1.getDateEditor().getDate().getTime());	
	}
	public Date getfechaAlta() {
		try {
			new Date(textField.getDateEditor().getDate().getTime()).toString();
		} catch (Exception c) {
			return new Date(new java.util.Date().getTime());
		}
		return new Date(textField.getDateEditor().getDate().getTime());	
	}
	private JKComboBox comboBox;

	public void setFechBaja(String fechaAlta){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");
		try {
			textField_1.setDate(dateFormat.parse(fechaAlta));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void setFechAlta(String fechaAlta){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");
		try {
			textField.setDate(dateFormat.parse(fechaAlta));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public DialogoFechaAltaBaja(final ComedorGUI gui,final String nia) {
		super(gui,true);
		setSize(225, 160);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 197, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
//					boolean n=gui.getBaseDeDatos().addFechas(getfechaAlta(),getfechaBaja(),nia);
//					if(n){
//						JOptionPane.showMessageDialog(gui, "Se ha agregado correctamente!","Exito",JOptionPane.INFORMATION_MESSAGE);
//						dispose();
//					}else{
//						JOptionPane.showMessageDialog(gui, "No se ha podido guardar las fechas!","Fallido",JOptionPane.ERROR_MESSAGE);
//						dispose();
//					}
				
				
			}
		});
		panel_1.add(btnNewButton);
		
		JLabel lblFechaAlta = new JLabel("Fecha Alta:");
		lblFechaAlta.setIcon(new ImageIcon(DialogoFechaAltaBaja.class.getResource("/resource/calendar.png")));
		
		JLabel lblFechaBaja = new JLabel("Fecha Baja:");
		lblFechaBaja.setIcon(new ImageIcon(DialogoFechaAltaBaja.class.getResource("/resource/calendar.png")));
		
		textField = new JDateChooser();
		textField.getDateEditor().setEnabled(false);
		
		textField_1 = new JDateChooser();
		textField_1.getDateEditor().setEnabled(false);
//		textField_1.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblFechaAlta, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblFechaBaja)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)))
					.addGap(12))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblFechaAlta))
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(9)
							.addComponent(lblFechaBaja))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(6)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPanel.setLayout(gl_contentPanel);
		setLocationRelativeTo(gui);
	}
	/**
	 * @wbp.parser.constructor
	 */
	public DialogoFechaAltaBaja(final ComedorGUI principal) {
		super(principal,true);
	
		setTitle("Rango de Fechas");
		setSize(227, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
	comboBox = new JKComboBox();
	comboBox.addItem("Ordinario");
	comboBox.addItem("Extraordinario");
	comboBox.addItem("Ambos");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(2)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(comboBox, 0, 195, Short.MAX_VALUE)
							.addContainerGap())
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.setIcon(new ImageIcon(DialogoFechaAltaBaja.class.getResource("/resource/close1.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.setIcon(new ImageIcon(DialogoFechaAltaBaja.class.getResource("/resource/n41.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
//					boolean n=gui.getBaseDeDatos().addFechas(getfechaAlta(),getfechaBaja(),nia);
//					if(n){
//						JOptionPane.showMessageDialog(gui, "Se ha agregado correctamente!","Exito",JOptionPane.INFORMATION_MESSAGE);
//						dispose();
//					}else{d
//						JOptionPane.showMessageDialog(gui, "No se ha podido guardar las fechas!","Fallido",JOptionPane.ERROR_MESSAGE);
//						dispose();
//					}
				
				
				java.util.Date fecha1 = textField.getDate();
				java.util.Date fecha2 = textField_1.getDate();
				principal.getBaseDeDatos().getRecibosPorFecha(fecha1,fecha2,comboBox.getSelectedItem().toString());
				dispose();
			}
		});
		panel_1.add(btnNewButton);
		
		JLabel lblFechaAlta = new JLabel("Fecha Alta:");
		lblFechaAlta.setIcon(new ImageIcon(DialogoFechaAltaBaja.class.getResource("/resource/calendar.png")));
		
		JLabel lblFechaBaja = new JLabel("Fecha Baja:");
		lblFechaBaja.setIcon(new ImageIcon(DialogoFechaAltaBaja.class.getResource("/resource/calendar.png")));
		
		textField = new JDateChooser();
		textField.getDateEditor().setEnabled(false);
		
		textField_1 = new JDateChooser();
		textField_1.getDateEditor().setEnabled(false);
//		textField_1.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblFechaAlta, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblFechaBaja)
							.addGap(4)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblFechaAlta))
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblFechaBaja))
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPanel.setLayout(gl_contentPanel);
		setLocationRelativeTo(principal);
	}
	public DialogoFechaAltaBaja(final ComedorGUI gui,final String nia,final String id) {
		super(gui,true);
		setTitle("Actualizar Fechas");
		setSize(225, 170);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
					.addContainerGap(227, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(138, Short.MAX_VALUE))
		);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Actualizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean a=gui.getBaseDeDatos().updateFechas(getfechaAlta(), getfechaBaja(), id);
				if(a){
					JOptionPane.showMessageDialog(gui, "Se ha actualizado corractamente!","Exito",JOptionPane.INFORMATION_MESSAGE);
						dispose();
					return;
				}else{
					JOptionPane.showMessageDialog(gui, "No se ha podido actualizar!","Fallido",JOptionPane.ERROR_MESSAGE);
					dispose();
					return;
				}
				
			}
		});
		panel_1.add(btnNewButton);
		
		
		JLabel lblFechaAlta = new JLabel("Fecha Alta:");
		lblFechaAlta.setIcon(new ImageIcon(DialogoFechaAltaBaja.class.getResource("/resource/calendar.png")));
		
		JLabel lblFechaBaja = new JLabel("Fecha Baja:");
		lblFechaBaja.setIcon(new ImageIcon(DialogoFechaAltaBaja.class.getResource("/resource/Pdf-icon1.png")));
		
		textField = new JDateChooser();
		textField.getDateEditor().setEnabled(false);
		
		try{
			textField.setDate(gui.getBaseDeDatos().getFechaAlta(id));
		}catch(Exception e){
			
		}
		textField_1 = new JDateChooser();
		try{
			textField_1.setDate(gui.getBaseDeDatos().getFechaBaja(id));
		}catch
		(Exception e){
			e.printStackTrace();
		}
		textField_1.getDateEditor().setEnabled(false);
//		textField_1.setColumns(10);
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblFechaAlta, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblFechaBaja)
							.addGap(4)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblFechaAlta))
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblFechaBaja))
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPanel.setLayout(gl_contentPanel);
		setLocationRelativeTo(gui);
	}
}
