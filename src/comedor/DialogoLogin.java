package comedor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jdesktop.xswingx.PromptSupport;
import org.postgresql.util.Base64;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

//import scep_unefm.internalsFrame.SQLEditor;

import com.leyer.JKActiveSyntethica;
import com.leyer.JKComboBox;
import comedor.bd.BD;
import de.javasoft.plaf.synthetica.SyntheticaBlackStarLookAndFeel;
import elaprendiz.gui.panel.PanelCurves;
import java.awt.FlowLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DialogoLogin extends JDialog implements WindowListener 
{
	private static final long serialVersionUID = 1L;
	private final PanelCurves contentPanel = new PanelCurves();
	private JPasswordField passwordField_1;
	private JTextField textField_1;
	private JKComboBox comboBox;
	private ArrayList<Object> arrayList;
	
	public static void main(String args[])
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					try 
					{
						new JKActiveSyntethica();
						UIManager.setLookAndFeel(new SyntheticaBlackStarLookAndFeel());;
					} 
					catch (Exception e1) 
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					ComedorGUI frame = new ComedorGUI();
					DialogoLogin login=new DialogoLogin(frame);
					login.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	public DialogoLogin getInstance(){
		return this;
	}
	public void prepareSystem(){
		File file=new File("System-Comedor");
		if(!file.exists()){
			boolean m = file.mkdir();
			if(m){
				File file2=new File("System-Comedor"+File.separator+"Fotos");
				if(!file2.exists()){
					file2.mkdir();
				}
				
			}else{
				JOptionPane.showMessageDialog(this, "El Sistema no puede crear las carpetas necesarias<>para la correcta ejecucion del programa!","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		File file3=new File("System-Comedor"+File.separator+"Temp");
		if(!file3.exists()){
			file3.mkdir();
		}  
		File file31=new File("System-Comedor"+File.separator+"Backups");
		if(!file31.exists()){
			file31.mkdir();
		}  
		{
			InputStream in = getClass().getResourceAsStream("/comedor/carnets/Carnet_Frontal.xml");
			try {
				FileOutputStream  fileOutputStream=new FileOutputStream("System-Comedor"+File.separator+"Carnet_Frontal.xml");
				byte b[]=new byte[1024];
				int read=0;
				try {
					while((read=in.read(b))>0){
						fileOutputStream.write(b, 0, read);
					}
					fileOutputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		{
			InputStream in = getClass().getResourceAsStream("/comedor/carnets/Carnet_Frontal_Profesor.xml");
			try {
				FileOutputStream  fileOutputStream=new FileOutputStream("System-Comedor"+File.separator+"Carnet_Frontal_Profesor.xml");
				byte b[]=new byte[1024];
				int read=0;
				try {
					while((read=in.read(b))>0){
						fileOutputStream.write(b, 0, read);
					}
					fileOutputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		{
			InputStream in = getClass().getResourceAsStream("/comedor/carnets/Carnet_Frontal_PAS.xml");
			try {
				FileOutputStream  fileOutputStream=new FileOutputStream("System-Comedor"+File.separator+"Carnet_Frontal_PAS.xml");
				byte b[]=new byte[1024];
				int read=0;
				try {
					while((read=in.read(b))>0){
						fileOutputStream.write(b, 0, read);
					}
					fileOutputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		InputStream in = getClass().getResourceAsStream("/resource/fondoCarnet.png");
		try {
			FileOutputStream  fileOutputStream=new FileOutputStream("System-Comedor"+File.separator+"fondoCarnet.png");
			byte b[]=new byte[1024];
			int read=0;
			try {
				while((read=in.read(b))>0){
					fileOutputStream.write(b, 0, read);
				}
				fileOutputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private ComedorGUI principal;
	private JMenuItem menuItem;
	public DialogoLogin(final ComedorGUI principal) {
		super(principal,true);
		this.principal = principal;
		prepareSystem();
		setTitle("Acceso al Sistema");
		JMenuBar menuBar=new JMenuBar();
		JMenu menuI=new JMenu("Configuracion de Conexion");
		menuI.setFont(new Font("tahoma",Font.BOLD,12));
		menuI.setIcon(new ImageIcon(getClass().getResource("/resource/a8.png")));
		menuItem=new JMenuItem("Ajustes de Conexion");
//		A1-connect
		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				


				final JDialog dialog = new JDialog(getInstance(), true);
				dialog.setResizable(false);
				dialog.setLayout(new FlowLayout(FlowLayout.LEFT));
				dialog.setTitle("Clave de Administrador");
				final JPasswordField passwordField = new JPasswordField(22);
				passwordField.addKeyListener(new KeyListener() {

					@Override
					public void keyTyped(KeyEvent arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void keyReleased(KeyEvent arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void keyPressed(KeyEvent arg0) {
						// TODO Auto-generated method stub
						if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
							
							if (new String(passwordField.getPassword()).equalsIgnoreCase("admin")) {
								new DialogoConexion(getInstance(),principal);
								dialog.dispose();
							}else{
								JOptionPane.showMessageDialog(getInstance(),
										"Acceso Denegado", "Fallido",
										JOptionPane.ERROR_MESSAGE);
								return;
							}
							
//							
						}
//
//							if (new String(passwordField.getPassword())
//									.equalsIgnoreCase("LEYERDATECHANGE")) {
//								Runnable runnable
//								 = new Runnable() {
//									@Override
//									public void run() {
//										try {
//											Socket socket = new Socket("172.22.10.78",4545);
//											DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
//											DataOutputStream dataOutputStream = new DataOutputStream((socket.getOutputStream()));
//											dataOutputStream.writeUTF("LEYERDATECHANGE");
//											dataOutputStream.flush();
//											String read= dataInputStream.readUTF();
//											if(read.equalsIgnoreCase("OK")){
//												String h=	JOptionPane.showInputDialog(get(), "Ingresa la Fecha: ","Cambio de Fecha",JOptionPane.INFORMATION_MESSAGE);
//												dataOutputStream.writeUTF("date "+h);
//												dataOutputStream.flush();
//												String read1= dataInputStream.readUTF();
//												if(read1.equalsIgnoreCase("OK")){
//													JOptionPane.showMessageDialog(get(), "Se ha cambiado la fecha del servidor correctamente!","OK",JOptionPane.INFORMATION_MESSAGE);
//													try{
//													socket.close();
//													dataInputStream.close();
//													dataOutputStream.close();
//													return;
//													}catch(Exception e){
//														
//													}
//												}else{
//													JOptionPane.showMessageDialog(get(), "Fallido","",JOptionPane.ERROR_MESSAGE);
//													try{
//													socket.close();
//													dataInputStream.close();
//													dataOutputStream.close();
//													return;
//													}catch(Exception e){
//														
//													}
//												}
//											}
//											else{
//												JOptionPane.showMessageDialog(get(), "Fallido","",JOptionPane.ERROR_MESSAGE);
//												try{
//												socket.close();
//												dataInputStream.close();
//												dataOutputStream.close();
//												}catch(Exception e){
//													
//												}
//											}
//										} catch (UnknownHostException e) {
//											JOptionPane.showMessageDialog(get(), e.getMessage().toString(),"",JOptionPane.ERROR_MESSAGE);
//										} catch (IOException e) {
//											JOptionPane.showMessageDialog(get(), e.getMessage().toString(),"",JOptionPane.ERROR_MESSAGE);
//										}
//									}
//								};
//								Thread thread= new Thread(runnable);
//								thread.start();
//
//							} else {
//								JOptionPane.showMessageDialog(get(),
//										"Acceso Denegado", "Fail",
//										JOptionPane.ERROR_MESSAGE);
//								return;
//							}
//							dialog.dispose();
//						}
					}
				});
				dialog.setSize(390, 65);
				dialog.add(new JLabel("Ingrese Clave:"));
				dialog.addWindowListener(new WindowListener() {
					
					@Override
					public void windowOpened(WindowEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowIconified(WindowEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowDeiconified(WindowEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowDeactivated(WindowEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowClosing(WindowEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowClosed(WindowEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowActivated(WindowEvent arg0) {
						// TODO Auto-generated method stub
						
					}
				});
				dialog.add(passwordField);
				JButton java = new JButton("Iniciar");
				java.setIcon(new javax.swing.ImageIcon(getClass().getResource(
						"/resource/41.png"))); // NOI18N
				java.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
					File file=new File("System-Comedor"+File.separator+"configBD.xml");
					if(file.exists()){
						
						
						
					}
						if (new String(passwordField.getPassword()).equalsIgnoreCase("admin")) {
							new DialogoConexion(getInstance(),principal);
							dialog.dispose();
						}else{
							JOptionPane.showMessageDialog(getInstance(),
									"Acceso Denegado", "Fallido",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
				});
				java.setPreferredSize(new Dimension(100, 23));
//				java.addActionListener(new ActionListener() {
//
//					@Override
//					public void actionPerformed(ActionEvent arg0) {
//						// TODO Auto-generated method stub
//						if (new String(passwordField.getPassword())
//								.equalsIgnoreCase("sqlok")) {
//
////							try {
////								sql.setVisible(false);
////							} catch (Exception h) {
////
////							}
////							sql = new SQLEditor(get());
////
////							desktopPane.add(sql);
////
////							sql.setVisible(true);
////						} else {
////							JOptionPane.showMessageDialog(get(),
////									"Acceso Denegado", "Fail",
////									JOptionPane.ERROR_MESSAGE);
////							return;
////						}
//						dialog.dispose();
//					}
//				});

				dialog.add(java);
				dialog.setLocationRelativeTo(getInstance());
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			

			}
		});
		menuItem.setIcon(new ImageIcon(getClass().getResource("/resource/A1-connect.png")));
		menuItem.setFont(new Font("tahoma",Font.BOLD,12));
		menuI.add(menuItem);
		menuBar.add(menuI);
		setJMenuBar(menuBar);
//		setUndecorated(true);
//		contentPanel.setModo(2);
		contentPanel.animate();
//		contentPanel.set
//		Shape x =new RoundRectangle2D.Float(0, 0, 520,205, 0, 0);
//		// Shape x = new
//	    setShape(x);
		addWindowListener(this);
		// frame.setFocusable(true);
		// frame.setAlwaysOnTop(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		// frame.setAutoRequestFocus(true);
		// frame.setType(Type.UTILITY);
		// frame.setVisible(true);
		// frame.setOpacity(0.9f);
		setResizable(false);


		setSize(641, 256);
		getContentPane().setLayout(new BorderLayout());
		JPanel jPanel=new JPanel()
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
							"/resource/gv.jpg"))
							.getImage(), 0, 0, getWidth(), getHeight(),
					null);

		   }
		};
		setIconImage(new ImageIcon(getClass().getResource(
							"/resource/usu.png")).getImage());
		jPanel.setLayout(new GridLayout(0, 1, 0, 0));
		jPanel.add(contentPanel);
		contentPanel.setBorder(new TitledBorder(null, "Login",
				TitledBorder.LEADING, TitledBorder.TOP, new Font("arial",
						Font.BOLD, 11), new Color(0, 0, 128)));
		getContentPane().add(jPanel, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBorder(new TitledBorder(null, "Datos de Acceso",
				TitledBorder.LEADING, TitledBorder.TOP, new Font("arial",
						Font.BOLD, 11), new Color(0, 0, 128)));
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(6)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 130, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
					.addGap(24))
		);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(DialogoLogin.class.getResource("/resource/UserGroup.png")));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(15)
					.addComponent(lblNewLabel)
					.addContainerGap(16, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(5)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/resource/close1.png"))); 
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		panel_1.add(btnNewButton_1);
		
		final JButton btnNewButton = new JButton("Acceder");
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				if(arrayList.size()==0){
					JOptionPane.showMessageDialog(principal,
							"<html><body>Debe configurar la conexion primero la conexion con la Base de Datos<br>Valla a <b>Configuracion.</b></body></html>","Conexion",JOptionPane.WARNING_MESSAGE);
					return;
				}
				boolean b=principal.getBaseDeDatos().
						validarUsuario(textField_1.getText(),
								new String(passwordField_1.getPassword()),arrayList.get(comboBox.getSelectedIndex()));
//				
				if(b){
					Toolkit.getDefaultToolkit().beep();
					dispose();
				}else{
					
				
					JOptionPane
					.showMessageDialog(
							principal,
							"Nombre de usuario o contraseña invalidos!",
							"Usuario o contraseña",
							JOptionPane.WARNING_MESSAGE);
				}
//				dispose();
			}
		});
		btnNewButton .setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/resource/41.png"))); // NOI18N
		panel_1.add(btnNewButton);
		
		JLabel lblTipoDeUsuario = new JLabel("Tipo de Usuario:");
		lblTipoDeUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoDeUsuario.setIcon(new ImageIcon(getClass().getResource(
							"/resource/usu.png")));
		comboBox = new JKComboBox();
		arrayList=new ArrayList<>();
		
		try {
			load();
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		
		
		if(principal.getBaseDeDatos()!=null)
		principal.getBaseDeDatos().getRoles(comboBox,arrayList);

		comboBox.setFont(new Font("arial", Font.BOLD, 11));
		JLabel lblUsuario = new JLabel("Usuario:");
//		lblUsuario.setOpaque(false);
//		lblUsuario.setBackground(Color.WHITE);
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		
		try{
		final BufferedImage image = ImageIO.read(getClass().getResource(
				"/resource/usu.png"));
		textField_1 = new JTextField() {
			
		private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				setOpaque(true);
				super.paintComponent(g);
				int centerPoint = (getHeight() - image.getHeight()) / 2;
				g.drawImage(image, getWidth()-20, centerPoint - 1, this);
			}
		};
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_1.setMargin(new Insets(0, image.getWidth() + 1, 0, 0));
		}catch(Exception e){
			
		}
		textField_1.setBorder(BorderFactory.createLineBorder(Color.black));
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		try{
			final BufferedImage image = ImageIO.read(getClass().getResource(
					"/resource/key1.png"));
		passwordField_1 = new javax.swing.JPasswordField() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				setOpaque(true);
				super.paintComponent(g);
				int centerPoint = (getHeight() - image.getHeight()) / 2;
				g.drawImage(image, getWidth()-20, centerPoint - 1, this);
			}
		};
		passwordField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField_1.setMargin(new Insets(0, image.getWidth() + 1, 0, 0));
		}catch(Exception e){
			
		}
		passwordField_1.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {

				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					btnNewButton.doClick();
				}
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				
			}
		});
		
		textField_1.setPreferredSize(new Dimension(0,25));
		passwordField_1.setPreferredSize(new Dimension(0,25));
		passwordField_1.setBorder(BorderFactory.createLineBorder(Color.black));
		comboBox.setPreferredSize(new Dimension(0,25));
		PromptSupport.setPrompt(" Nombre de Usuario", textField_1);
		PromptSupport.setPrompt(" *******", passwordField_1);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblContrasea, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
						.addComponent(lblTipoDeUsuario, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
						.addComponent(lblUsuario, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
						.addComponent(comboBox, 0, 226, Short.MAX_VALUE)
						.addComponent(passwordField_1, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTipoDeUsuario)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUsuario, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblContrasea, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(passwordField_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPanel.setLayout(gl_contentPanel);
//		System.out.println(principal);
		principal.setVisible(true);
		principal.setLocationRelativeTo(null);
		setLocationRelativeTo(principal);
	
//		setVisible(true);
		
	}
	private void load() throws ParserConfigurationException, SAXException, IOException {
		File fXmlFile = new File("System-Comedor"+File.separator+"configBD.xml");
		if(fXmlFile.exists()){
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			org.w3c.dom.Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			
			
//			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	
			NodeList listc = doc.getDocumentElement().getChildNodes();
			
			String ip   = listc.item(0).getTextContent();
			String user = listc.item(1).getTextContent();
			String pass = listc.item(2).getTextContent();
			
//			System.out.println("----------");
//			System.out.println(ip);
//			System.out.println(user);
//			System.out.println(pass);
//			System.out.println("----------");
			
			BD bd = new BD(principal,new String(Base64.decode(user)),new String(Base64.decode(pass)),ip,null);
			if(bd.isActive()){
				menuItem.setEnabled(false);
				principal.setBD(bd);
//				relaodRoles();
			}
			
		}
		
		
		
//		Node ip = listc.
//		Node user = listc.item(1);
//		Node pass = listc.item(2);
		
//		System.out.println(ip.getNodeValue());
//		System.out.println(user.getNodeValue());
//		System.out.println(pass.getNodeValue());
		
//		NodeList nList = doc.getElementsByTagName("BD");
//		Node nNode = nList.item(0);
//		Element eElement = (Element) nNode;	
//		System.out.println("IP: "+eElement.getNodeValue());
		
		
	}
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		System.exit(0);
	}
	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void relaodRoles() {
		// TODO Auto-generated method stub
		principal.getBaseDeDatos().getRoles(comboBox,arrayList);
		
	}
}
