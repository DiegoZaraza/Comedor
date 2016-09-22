package comedor;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogoSeleccion extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel(){

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
	private JLabel lblRegistrarProfesores;

	

	/**
	 * Create the dialog.
	 * @param principal 
	 */
	public DialogoSeleccion(final ComedorGUI principal) {
		super(principal,true);
		
		setTitle("¿Que desea Hacer?");
		setSize(876, 195);
		setLocationRelativeTo(principal.getDesktop());
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 3, 7, 0));
		{
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			panel.setBorder(new LineBorder(new Color(0, 0, 0)));
			contentPanel.add(panel);
			panel.setLayout(new GridLayout(0, 1, 0, 0));
			JLabel lblNewLabel = new JLabel("<html><body>Registrar, Actualizar y Ver<br>Datos de Alumno</body></html>");
			lblNewLabel.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					dispose();
					RegistrarAlumno alumno=new RegistrarAlumno(principal,"0",false);
					principal.getDesktop().add(alumno);
					alumno.setVisible(true);	
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
			lblNewLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel.setIcon(new ImageIcon(DialogoSeleccion.class.getResource("/resource/alumnos.png")));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(lblNewLabel);
		}
		{
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			panel.setBorder(new LineBorder(new Color(0, 0, 0)));
			contentPanel.add(panel);
			{
				lblRegistrarProfesores = new JLabel("<html><body>Registrar, Actualizar y Ver<br>Datos de Profesor</body></html>");
				lblRegistrarProfesores.setIcon(new ImageIcon(DialogoSeleccion.class.getResource("/resource/teacher.png")));
				lblRegistrarProfesores.setCursor(new Cursor(Cursor.HAND_CURSOR));
				lblRegistrarProfesores.addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent arg0) {
						// TODO Auto-generated method stub
						RegistrarProfesores profesores = new RegistrarProfesores(principal);
						principal.getDesktop().add(profesores);
						profesores.setVisible(true);
						dispose();
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
				lblRegistrarProfesores.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblRegistrarProfesores.setHorizontalAlignment(SwingConstants.CENTER);
			}
			panel.setLayout(new GridLayout(0, 1, 0, 0));
			panel.add(lblRegistrarProfesores);
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0)));
			contentPanel.add(panel);
			panel.setOpaque(false);
			panel.setLayout(new GridLayout(0, 1, 0, 0));
			{
				JLabel lblNewLabel_1 = new JLabel("<html><body>Registrar, Actualizar y ver Datos de <>Personal No Docente</body></html>");
				lblNewLabel_1.setIcon(new ImageIcon(DialogoSeleccion.class.getResource("/resource/nodocente.png")));
				lblNewLabel_1.addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent arg0) {
						// TODO Auto-generated method stub
						dispose();	
						RegistrarNoDocentes noDocentes=new RegistrarNoDocentes(principal);
						principal.getDesktop().add(noDocentes);
						noDocentes.setVisible(true);
						
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
				lblNewLabel_1.setCursor(new Cursor(Cursor.HAND_CURSOR));
				lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblNewLabel_1);
			}
		}

		lblRegistrarProfesores.setCursor(new Cursor(Cursor.HAND_CURSOR));
		{
			JPanel panel = new JPanel();
			panel.setPreferredSize(new Dimension(0,30));
			getContentPane().add(panel, BorderLayout.SOUTH);
			panel.setLayout(new GridLayout(0, 1, 0, 0));
			{
				JButton btnNewButton = new JButton("Cancelar");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnNewButton.setIcon(new ImageIcon(DialogoSeleccion.class.getResource("/resource/A1-close.png")));
				panel.add(btnNewButton);
			}
		}
	}

}
