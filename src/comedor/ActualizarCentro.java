package comedor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

public class ActualizarCentro extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldCodigo;
	private JTextField textFieldDenominacion;
	private JTextField textFieldDomicilio;
	private JTextField textFieldTelefono;

	private JTextField textFieldFax;
	public ActualizarCentro(final ComedorGUI principal) {
		setTitle("Actualizar Datos del Centro");
		setBounds(20,20,585, 238);
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
			
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(null, "Datos del Centro",
				TitledBorder.LEADING, TitledBorder.TOP, new Font("arial",
						Font.BOLD, 11), new Color(0, 0, 128)));
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lbla = new JLabel("<html><body>Codigo del<br>Centro:</body></html>");
		
		textFieldCodigo = new JTextField();
		textFieldCodigo.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Denominacion:");
		
		textFieldDenominacion = new JTextField();
		textFieldDenominacion.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Domicilio:");
		
		textFieldDomicilio = new JTextField();
		textFieldDomicilio.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setColumns(10);
		
		JLabel lblFax = new JLabel("Fax:");
		
		textFieldFax = new JTextField();
		textFieldFax.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lbla, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblNewLabel_1)
							.addComponent(lblTelefono)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(textFieldCodigo, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldDomicilio, GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
						.addComponent(textFieldDenominacion, GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(textFieldTelefono, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblFax)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldFax, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(17)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(textFieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lbla)
							.addGap(1)))
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textFieldDenominacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(textFieldDomicilio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTelefono)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(textFieldTelefono, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblFax)
							.addComponent(textFieldFax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(19, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(ActualizarCentro.class.getResource("/resource/close1.png")));
		panel_1.add(btnNewButton_1);
		principal.getBaseDeDatos().getDatosCentro(this);
		JButton btnNewButton = new JButton("Actualizar Datos");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				boolean b=principal.getBaseDeDatos().actualizarDatosCentro(getInstance());
				if(b){
					JOptionPane.showMessageDialog(getInstance(), "Se han actualizado los datos correctamente!","Exito al Actualizar!",JOptionPane.INFORMATION_MESSAGE);
					principal.updateDatosCentro();
					dispose();
				}else{
					JOptionPane.showMessageDialog(getInstance(), "No se ha podido actualizar!","Error",JOptionPane.ERROR_MESSAGE);
					
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(ActualizarCentro.class.getResource("/resource/update.png")));
		panel_1.add(btnNewButton);
		
		btnNewButton.setPreferredSize(new Dimension(160,24));
		btnNewButton_1.setPreferredSize(new Dimension(160,24));
	}
	public ActualizarCentro getInstance(){
		return this;
	}
	public String getCodigo(){
		return textFieldCodigo.getText();
	}
	public String getDenomicacion(){
		return textFieldDenominacion.getText();
	}
	public String getFex(){
		return textFieldFax.getText();
	}
	public String getTelefono(){
		return textFieldTelefono.getText();
	}
	public String getDemicilio(){
		return textFieldDomicilio.getText();
	}
	public void setCodigo(String code){
		textFieldCodigo.setText(code);
	}
	public void setDomicilio(String domicilio){
		textFieldDomicilio.setText(domicilio);
	}
	public void setTelefono(String telf){
		textFieldTelefono.setText(telf);
	}
	public void setDenominacion(String d){
		textFieldDenominacion.setText(d);
	}
	public void setFax(String fax){
		textFieldFax.setText(fax);
	}
}
