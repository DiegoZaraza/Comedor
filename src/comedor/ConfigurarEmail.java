package comedor;

import java.awt.Desktop;
import java.awt.Toolkit;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JProgressBar;
import javax.swing.ImageIcon;

import org.jdesktop.xswingx.PromptSupport;

import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.net.URL;

public class ConfigurarEmail extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JPasswordField passwordField;
	private JProgressBar progressBar;

	private ComedorGUI principal;
	private JButton btnNewButton;
	public ConfigurarEmail getInstance(){
		return this;
	}
	public void setEnabledButtonAceptar(boolean n){
		btnNewButton.setEnabled(n);
	}
	public ConfigurarEmail(ComedorGUI p) {
		setTitle("Configurar Email");
		this.principal = p;
		setBounds(10, 10, 421, 311);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblEmail = new JLabel("Email:");
		
		textField = new JTextField();
		textField.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textField.setColumns(10);
		
		JLabel lblClave = new JLabel("Clave:");
		
		passwordField = new JPasswordField();
		passwordField.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblEmail)
							.addGap(21)
							.addComponent(textField, GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblClave)
							.addGap(18)
							.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblClave)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		
		JLabel label = new JLabel("    ");
		label.setIcon(new ImageIcon(ConfigurarEmail.class.getResource("/resource/email.png")));
		
		JLabel lblNewLabel = new JLabel("Importante:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblAntesDeConfigurar = new JLabel("<html><body>Antes de configurar el Email. loguese en su cuenta <br>Gmail y presione la opcion 'Activar' en el siguiente enlace:</body></html>");
		
		JButton btnEnlace = new JButton("Enlace");
//		URL
		btnEnlace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					try {
				        Desktop.getDesktop().browse(new URL("https://www.google.com/settings/security/lesssecureapps").toURI());
				        
					} catch (Exception eX) {
						JOptionPane.showMessageDialog(principal,eX.getMessage(),eX.getLocalizedMessage(),JOptionPane.ERROR_MESSAGE);
						StringSelection selection=new StringSelection("https://www.google.com/settings/security/lesssecureapps");
						Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection,null);
						JOptionPane.showMessageDialog(principal, "Se ha copiado en enlace al portapapeles!","Copiado",JOptionPane.INFORMATION_MESSAGE);
					}
					
//							new URI("www.google.com/settings/security/lesssecureapps"));
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(principal,e1.getMessage(),e1.getLocalizedMessage(),JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
						.addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(13)
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
								.addComponent(lblAntesDeConfigurar)
								.addComponent(btnEnlace, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(label)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblAntesDeConfigurar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEnlace)
					.addPreferredGap(ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setIcon(new ImageIcon(ConfigurarEmail.class.getResource("/resource/close1.png")));
		panel_1.add(btnCerrar);
		
		 btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				progressBar.setIndeterminate(true);
				String passString=new String(passwordField.getPassword());
				String email = textField.getText();
				if(email.length()==0){
					JOptionPane.showMessageDialog(principal, "Debe ingresar un Email!","Email vacio",JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(passString.length()==0){
					JOptionPane.showMessageDialog(principal, "Debe ingresar la clave!","Clave",JOptionPane.WARNING_MESSAGE);
					return;
				}
				btnNewButton.setEnabled(false);
				principal.getBaseDeDatos().sendEmail("Software de Gestion y Administracion de Comedor", textField.getText(),
						textField.getText(),passString,"Email Configurado Correctamente! Ya puede usar el Servicio.",progressBar,getInstance());
//				if(b){
//					
//				}else{
//					
//				}
			}
		});
		
		PromptSupport.setPrompt("***********",passwordField);
		PromptSupport.setPrompt("Correo Gmail", textField);
		btnNewButton.setIcon(new ImageIcon(ConfigurarEmail.class.getResource("/resource/n41.png")));
		panel_1.add(btnNewButton);
		getContentPane().setLayout(groupLayout);
		try{
		textField.setText(principal.getBaseDeDatos().getDatosConfiguracionEmail().split("@1")[0]);
		passwordField.setText(principal.getBaseDeDatos().getDatosConfiguracionEmail().split("@1")[1]);
		}catch(Exception e){
			
		}

	}
}
