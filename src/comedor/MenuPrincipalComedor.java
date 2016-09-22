package comedor;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class MenuPrincipalComedor extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel label2;
	private JLabel label3;
	private JLabel lblconsultasYListados;
	private JLabel label1;
	private JLabel label4;
	private JLabel label5;
	private JLabel label6;
	private JLabel label7;
	private JLabel label61;
	private JLabel label3x;
	private JLabel label4a;
	private JPanel panel_1;
	@Override
	public void paintComponent(Graphics g) {
			setOpaque(false);
			g.drawImage(
					new ImageIcon(getClass().getResource(
							"/resource/loginFondo.jpg"))
							.getImage(), 0, 0, getWidth(), getHeight(),
			null);
    }
	public MenuPrincipalComedor(final ComedorGUI principal) {
		setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		JPanel panel = new JPanel()
		{
	
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				setOpaque(false);
				g.drawImage(
						new ImageIcon(getClass().getResource(
								"/resource/loginFondo.jpg"))
								.getImage(), 0, 0, getWidth(), getHeight(),
						null);

			   }
		};
		
		panel_1 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
					.addContainerGap())
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_1.setLayout(new GridLayout(136, 16, 0, 0));
		panel.setLayout(new GridLayout(0, 1, 0, 0));
	
		
		JLabel lblNewLabel = new JLabel("  Menu Principal");
		
		JPanel jPanel=new JPanel(new GridLayout());
		jPanel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel.setIcon(new ImageIcon(MenuPrincipalComedor.class.getResource("/resource/a23.png")));
		jPanel.setBorder(BorderFactory.createTitledBorder(""));
		panel.add(jPanel);
		setLayout(groupLayout
				);
		lblconsultasYListados = new JLabel();
		lblconsultasYListados.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		lblconsultasYListados.setText("<html><font color='LIGHT_GRAY'><a href=>Consultas y Listados</a> </font></html>");
		lblconsultasYListados.setFont(new Font("arial", Font.BOLD, 11));
		lblconsultasYListados.setIcon(new ImageIcon(getClass().getResource("/resource/search1.png")));
		lblconsultasYListados.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		lblconsultasYListados.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				ConsultaYListados consultaListados=new ConsultaYListados(principal);
				principal.getDesktop().add(consultaListados);
				consultaListados.setVisible(true);
				
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
		
		
		
		
		
		label1 = new JLabel();
		label1.addMouseListener(new MouseListener(){
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
//				ActualizarCentro centro = new ActualizarCentro(getInstance());
//				desktop.add(centro);
//				centro.setVisible(true);
				
	
				System.out.println("OK");
				
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
		label1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		label1.setText("<html><font color='LIGHT_GRAY'><a href=>Actualizar Centro</a></font></html>");
		label1.setFont(new Font("arial", Font.BOLD, 11));
		
		

	
		label1.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				ActualizarCentro centro = new ActualizarCentro(principal);
				principal.getDesktop().add(centro);
				centro.setVisible(true);
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
		
		label1.setIcon(new ImageIcon(getClass().getResource("/resource/update.png")));
		
		label2 = new JLabel();
		label2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		label2.setText("<html><font color='LIGHT_GRAY'><a href=>  Carnets</a><></html>");
		label2.setFont(new Font("arial", Font.BOLD, 11));
		label2.setIcon(new ImageIcon(getClass().getResource("/resource/A1-signal.png")));
		
		label2.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				ImpresionCarnets carnets=new ImpresionCarnets(principal);
				principal.getDesktop().add(carnets);
				carnets.setVisible(true);
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
		label2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		label3 = new JLabel();
		label3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		label3.setText("<html><font color='LIGHT_GRAY'><a href=>Control de Acceso</a></font></html>");
		label3.setFont(new Font("arial", Font.BOLD, 11));
		label3.setIcon(new ImageIcon(getClass().getResource("/resource/acceso.png")));
		
		
		label3.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
				AdministrarUsuarios administrarUsuarios = new AdministrarUsuarios(principal);
				principal.getDesktop().add(administrarUsuarios);
				administrarUsuarios.setVisible(true);
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
		
		label3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		
		
		
		label4 = new JLabel();
		label4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		label4.setText("<html><font color='LIGHT_GRAY'><a href=>Contabilidad Alumnos</a></font></html>");
		label4.setFont(new Font("arial", Font.BOLD, 11));
		label4.setIcon(new ImageIcon(getClass().getResource("/resource/tab3.png")));
		
		label4.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				ContabilidadAlumnos contabilidad=new ContabilidadAlumnos(principal);
				principal.getDesktop().add(contabilidad);
				contabilidad.setVisible(true);
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
		label4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		
		
		label5 = new JLabel();
		label5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		label5.setText("<html><font color='LIGHT_GRAY'><a href=>Gestions de Grupos</a></font></html>");
		label5.setFont(new Font("arial", Font.BOLD, 11));
		label5.setIcon(new ImageIcon(getClass().getResource("/resource/usu.png")));
		label5.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				
				GestionDeGrupos gestionGrupos= new GestionDeGrupos(principal);
				principal.getDesktop().add(gestionGrupos);
				gestionGrupos.setVisible(true);
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
		
		label5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		
		
		
		label6 = new JLabel();
		label6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		label6.setText("<html><font color='LIGHT_GRAY'><a href=>Informe y Reportes</a></font></html>");
		label6.setFont(new Font("arial", Font.BOLD, 11));
		label6.setIcon(new ImageIcon(getClass().getResource("/resource/Pdf-icon1.png")));
		label6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		label6.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				InformeYReportes reportes=new InformeYReportes(principal);
				
				principal.getDesktop().add(reportes);
			reportes.setVisible(true);
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

		panel_1.setOpaque(false);
		
		label7 = new JLabel();
		label7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		label7.setText("<html><a href=>Alumno, Profesor, P.A.S</html>");
		label7.setFont(new Font("arial", Font.BOLD, 11));
		label7.setIcon(new ImageIcon(getClass().getResource("/resource/newElement.png")));
		label7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		label7.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
				new DialogoSeleccion(principal).setVisible(true);
//				principal.getDesktop().add(c);
//				c.setVisible(true);
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
		
		label61 = new JLabel();
		label61.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		label61.setText("<html><font color='LIGHT_GRAY'><a href=>Autorizados Lunes,Martes,Jueves</a></font></html>");
		label61.setFont(new Font("arial", Font.BOLD, 11));
		label61.setIcon(new ImageIcon(getClass().getResource("/resource/usu.png")));
		label61.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		label61.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
//				InformeYReportes reportes=new InformeYReportes(principal);
//				
//				principal.getDesktop().add(reportes);
//				reportes.setVisible(true);
				AutorizadosLunesMartesJueves autorizadosLunesMartesJueves=new AutorizadosLunesMartesJueves(principal);
				principal.getDesktop().add(autorizadosLunesMartesJueves);
				autorizadosLunesMartesJueves.setVisible(true);
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

		
		panel_1.add(label7);
		panel_1.add(lblconsultasYListados);
//		taskPane.add(new JSeparator());
		panel_1.add(label1);
		panel_1.add(label2);
		panel_1.add(label3);
		panel_1.add(label4);
		
		
		
		label4a = new JLabel();
		panel_1.add(label4a);
		label4a.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		label4a.setText("<html><font color='LIGHT_GRAY'><a href=>Contabilidad Profesores</a></font></html>");
		label4a.setFont(new Font("arial", Font.BOLD, 11));
		label4a.setIcon(new ImageIcon(getClass().getResource("/resource/tab3.png")));
		
		label4a.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				ContabilidadProfesores contabilidad=new ContabilidadProfesores(principal);
				principal.getDesktop().add(contabilidad);
				contabilidad.setVisible(true);
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
		label4a.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		
		panel_1.add(label5);
		panel_1.add(label6);
		panel_1.add(label61);
		
		label3x = new JLabel();
		label3x.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		label3x.setText("<html><font color='LIGHT_GRAY'><a href=>Configurar Impresoras</a></font></html>");
		label3x.setFont(new Font("arial", Font.BOLD, 11));
//		Color.LIGHT_LIGHT_GRAY
		label3x.setIcon(new ImageIcon(getClass().getResource("/resource/impresora.png")));
		
		
		label3x.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
//				AdministrarUsuarios administrarUsuarios = new AdministrarUsuarios(principal);
//				principal.getDesktop().add(administrarUsuarios);
//				administrarUsuarios.setVisible(true);
				ConfigurarImpresoras configurarImpresoras=new ConfigurarImpresoras(principal);
				principal.getDesktop().add(configurarImpresoras);
				configurarImpresoras.setVisible(true);
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
		
		label3x.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		
		
		panel_1.add(label3x);
		
		

		
	}
	public void contabilidad() {
		// TODO Auto-generated method stub
//		\	arg0)
		
		panel_1.remove(label7);
				panel_1.remove(lblconsultasYListados);
				panel_1.remove(label1);
				panel_1.remove(label2);
				panel_1.remove(label3);
				panel_1.remove(label5);
				panel_1.remove(label6);
				panel_1.remove(label61);
				panel_1.remove(label3x);
						
//		label7.setVisible(false);
//		lblconsultasYListados.setVisible(false);
//		label1.setVisible(false);
//		label2.setVisible(false);
//		label3.setVisible(false);
//		label5.setVisible(false);
//		label6.setVisible(false);
//		label61.setVisible(false);
//		label3x.setVisible(false);
		
	}
	public void contabilidadOff() {
		// TODO Auto-generated method stub
		panel_1.remove(label4);
		panel_1.remove(label4a);
		
	}
}
