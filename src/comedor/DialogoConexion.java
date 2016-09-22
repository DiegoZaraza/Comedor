package comedor;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import org.jdesktop.swingx.JXBusyLabel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import comedor.bd.BD;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class DialogoConexion extends JDialog 
{
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtPostgres;
	private JPasswordField textField_1;
	private JXBusyLabel lblNewLabel;
	private JPanel buttonPane;
	private BD bd;	
	private ComedorGUI principal;
	private JTextField txtLocalhost;
	private JButton btnNewButton_2;
	private JLabel lblNewLabel_1;
	private JButton btnNewButton;

	public DialogoConexion getInstance()
	{
		return this;
	}

	public DialogoConexion(final DialogoLogin dialogoLogin, ComedorGUI comedorGUI) 
	{
		super(dialogoLogin,true);
		this.principal = comedorGUI;
		setTitle("Ajustes de Conexion");
		setBounds(100, 100, 496, 204);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Conexion a la Base de Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JLabel lblUser = new JLabel("User:");
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);

		txtPostgres = new JTextField();
		txtPostgres.setText("postgres");
		txtPostgres.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");
		textField_1 = new JPasswordField();
		textField_1.setColumns(10);

		btnNewButton_2 = new JButton("Probar Conexion");
		btnNewButton_2.addActionListener(new ActionListener() 
		{	
			public void actionPerformed(ActionEvent arg0) 
			{	
				new Thread(new Runnable()
				{
					public void run() 
					{
						try 
						{
							btnNewButton_2.setEnabled(false);
							lblNewLabel.setBusy(true);
							Thread.sleep(2000);
							bd = new BD(principal, txtPostgres.getText(), new String(textField_1.getPassword()), txtLocalhost.getText(), getInstance());

							if(bd.isActive())
							{
								lblNewLabel_1.setIcon(new ImageIcon(DialogoConexion.class.getResource("/resource/conectbase.png")));
								lblNewLabel_1.repaint();
								lblNewLabel.setBusy(false);
								btnNewButton_2.setEnabled(false);
								btnNewButton.setEnabled(true);

								JOptionPane.showMessageDialog(principal, "Se ha realizado la conexion correctamente!", "Estado de Conexion", JOptionPane.INFORMATION_MESSAGE);
								principal.setBD(bd);

								if(dialogoLogin != null)
									dialogoLogin.relaodRoles();

								boolean x= false;

								File file = new File("System-Comedor" + File.separator + "configBD.xml");

								if(file.exists())
								{
									int j = JOptionPane.showConfirmDialog(getInstance(), "<html><body>Ya hay una configuracion de conexion guardada" +
											" anteriormente.<br><b>Desea sobreescribir los datos?</b></body></html>");

									if(j == JOptionPane.OK_OPTION)
									{
										file.delete();
										x=true;
									}
									else
									{
										dispose();
										return;
									}
								}

								DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();

								try 
								{
									DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
									Document doc = docBuilder.newDocument();

									Element rootElement = doc.createElement("BD");
									doc.appendChild(rootElement);

									Element locationx = doc.createElement("IP");
									locationx.appendChild(doc.createTextNode(""+txtLocalhost.getText()));
									rootElement.appendChild(locationx);

									locationx = doc.createElement("user");
									locationx.appendChild(doc.createTextNode(""+org.postgresql.util.Base64.encodeBytes(txtPostgres.getText().getBytes())));
									rootElement.appendChild(locationx);

									locationx = doc.createElement("password");
									locationx.appendChild(doc.createTextNode(""+org.postgresql.util.Base64.encodeBytes(new String(textField_1.getPassword()).getBytes())));
									rootElement.appendChild(locationx);

									TransformerFactory transformerFactory = TransformerFactory.newInstance();
									Transformer transformer = transformerFactory.newTransformer();
									DOMSource source = new DOMSource(doc);
									StreamResult result = new StreamResult(file);

									transformer.transform(source, result);

									if(x)
									{
										JOptionPane.showMessageDialog(principal, "El sistema se cerrara...", "Nueva Conexion", JOptionPane.INFORMATION_MESSAGE);
										System.exit(0);
									}
									dispose();
								}
								catch(Exception e)
								{
									e.printStackTrace();
								}
							}
							else
							{
								lblNewLabel.setBusy(false);
								btnNewButton_2.setEnabled(true);
							}

						} 
						catch (InterruptedException e) 
						{
							e.printStackTrace();
						}
					}
				}).start();
			}
		});

		lblNewLabel = new JXBusyLabel();
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(DialogoConexion.class.getResource("/resource/conectbase_false1.png")));

		JLabel lblNewLabel_2 = new JLabel("IP:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);

		txtLocalhost = new JTextField();
		txtLocalhost.setText("localhost");
		txtLocalhost.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblNewLabel_1)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblPassword, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblUser, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtLocalhost, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
								.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
								.addComponent(txtPostgres, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
								.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
				);

		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
										.addContainerGap()
										.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblNewLabel_2)
												.addComponent(txtLocalhost, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnNewButton_2))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panel.createSequentialGroup()
														.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
																.addComponent(txtPostgres, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																.addComponent(lblUser))
														.addPreferredGap(ComponentPlacement.RELATED)
														.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
																.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																.addComponent(lblPassword)))
												.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)))
								.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
						.addContainerGap())
				);

		panel.setLayout(gl_panel);
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new GridLayout(0, 2, 0, 0));

			JButton btnNewButton_1 = new JButton("Cancelar");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			btnNewButton_1.setIcon(new ImageIcon(DialogoConexion.class.getResource("/resource/A1-close.png")));
			buttonPane.add(btnNewButton_1);

			btnNewButton = new JButton("Aceptar");
			btnNewButton.setEnabled(false);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			btnNewButton.setIcon(new ImageIcon(DialogoConexion.class.getResource("/resource/41.png")));
			buttonPane.add(btnNewButton);
		}

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);

		gl_contentPanel.setHorizontalGroup(
				gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(buttonPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
								.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE))
						.addContainerGap())
				);
		gl_contentPanel.setVerticalGroup(
				gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
				);
		contentPanel.setLayout(gl_contentPanel);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(81, Short.MAX_VALUE))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		getContentPane().setLayout(groupLayout);
		setLocationRelativeTo(dialogoLogin);
		setResizable(false);
		setVisible(true);
	}

	public DialogoConexion(final ComedorGUI comedorGUI) 
	{
		super(comedorGUI,true);
		this.principal=comedorGUI;
		setTitle("Ajustes de Conexion");
		setBounds(100, 100, 496, 204);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Conexion a la Base de Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JLabel lblUser = new JLabel("User:");
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);

		txtPostgres = new JTextField();
		txtPostgres.setText("postgres");
		txtPostgres.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");

		textField_1 = new JPasswordField();
		textField_1.setColumns(10);

		btnNewButton_2 = new JButton("Probar Conexion");
		btnNewButton_2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				new Thread(new Runnable() {
					public void run() {
						try {
							btnNewButton_2.setEnabled(false);
							lblNewLabel.setBusy(true);
							Thread.sleep(2000);
							bd=new BD(principal,txtPostgres.getText(),new String(textField_1.getPassword()),txtLocalhost.getText() ,getInstance());
							if(bd.isActive()){
								lblNewLabel_1.setIcon(new ImageIcon(DialogoConexion.class.getResource("/resource/conectbase.png")));
								lblNewLabel_1.repaint();
								lblNewLabel.setBusy(false);
								btnNewButton_2.setEnabled(false);
								btnNewButton.setEnabled(true);

								JOptionPane.showMessageDialog(principal, "Se ha realizado la conexion correctamente!","Estado de Conexion",JOptionPane.INFORMATION_MESSAGE);
								principal.setBD(bd);

								{
									File file=new File("System-Comedor" + File.separator + "configBD.xml");

									if(file.exists())
									{
										int j = JOptionPane.showConfirmDialog(getInstance(), "Ya hay una configuracion de conexion guardada" +
												" anteriormente.<br><b>Desea sobreescribir los datos?</b></body></html>");

										if(j == JOptionPane.OK_OPTION)
											file.delete();
										else
										{
											dispose();
											return;
										}
									}

									DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
									DocumentBuilder docBuilder;

									try 
									{
										docBuilder = docFactory.newDocumentBuilder();

										Document doc = docBuilder.newDocument();
										Element rootElement = doc.createElement("BD");
										doc.appendChild(rootElement);

										Element locationx = doc.createElement("IP");
										locationx.appendChild(doc.createTextNode(""+txtLocalhost.getText()));
										rootElement.appendChild(locationx);

										locationx = doc.createElement("user");
										locationx.appendChild(doc.createTextNode(""+org.postgresql.util.Base64.encodeBytes(txtPostgres.getText().getBytes())));
										rootElement.appendChild(locationx);

										locationx = doc.createElement("password");
										locationx.appendChild(doc.createTextNode(""+org.postgresql.util.Base64.encodeBytes(new String(textField_1.getPassword()).getBytes())));
										rootElement.appendChild(locationx);

										TransformerFactory transformerFactory = TransformerFactory.newInstance();
										Transformer transformer = transformerFactory.newTransformer();
										DOMSource source = new DOMSource(doc);
										StreamResult result = new StreamResult(file);

										transformer.transform(source, result);
										dispose();
									}
									catch(Exception e)
									{
										e.printStackTrace();
									}
								}
							}
							else
							{
								lblNewLabel.setBusy(false);
								btnNewButton_2.setEnabled(true);
							}
						} 
						catch (InterruptedException e) 
						{
							e.printStackTrace();
						}
					}
				}).start();
			}
		});

		lblNewLabel = new JXBusyLabel();
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(DialogoConexion.class.getResource("/resource/conectbase_false1.png")));

		JLabel lblNewLabel_2 = new JLabel("IP:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);

		txtLocalhost = new JTextField();
		txtLocalhost.setText("localhost");
		txtLocalhost.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblNewLabel_1)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblPassword, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblUser, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtLocalhost, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
								.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
								.addComponent(txtPostgres, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
								.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
				);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
										.addContainerGap()
										.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblNewLabel_2)
												.addComponent(txtLocalhost, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnNewButton_2))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panel.createSequentialGroup()
														.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
																.addComponent(txtPostgres, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																.addComponent(lblUser))
														.addPreferredGap(ComponentPlacement.RELATED)
														.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
																.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																.addComponent(lblPassword)))
												.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)))
								.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
						.addContainerGap())
				);
		panel.setLayout(gl_panel);
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new GridLayout(0, 2, 0, 0));

			JButton btnNewButton_1 = new JButton("Cancelar");
			btnNewButton_1.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					dispose();
				}
			});
			
			btnNewButton_1.setIcon(new ImageIcon(DialogoConexion.class.getResource("/resource/A1-close.png")));
			buttonPane.add(btnNewButton_1);

			btnNewButton = new JButton("Aceptar");
			btnNewButton.setEnabled(false);
			btnNewButton.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) {	}
			});
			
			btnNewButton.setIcon(new ImageIcon(DialogoConexion.class.getResource("/resource/41.png")));
			buttonPane.add(btnNewButton);
		}
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
				gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(buttonPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
								.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE))
						.addContainerGap())
				);
		gl_contentPanel.setVerticalGroup(
				gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
				);
		
		contentPanel.setLayout(gl_contentPanel);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(81, Short.MAX_VALUE))
				);
		
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		
		getContentPane().setLayout(groupLayout);
		setLocationRelativeTo(comedorGUI);
		setResizable(false);
		setVisible(true);
	}
	
	public void fail() 
	{
		lblNewLabel_1.setIcon(new ImageIcon(DialogoConexion.class.getResource("/resource/conectbase_false1.png")));
		lblNewLabel_1.repaint();
	}
}