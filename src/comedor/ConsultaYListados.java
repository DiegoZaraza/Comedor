package comedor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class ConsultaYListados extends JInternalFrame {



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ComedorGUI principal;
	private JTabbedPane tabbedPane;
	private PanelListadoAlumnos componentAlumnos;
	private PanelListadoProfesores panelListadoProfesores;
	private PanelListadoNoDocentes panelListadoNoDocentes;
	public PanelListadoAlumnos getPanelAlumnos(){
		return componentAlumnos;
	}
	public ConsultaYListados(ComedorGUI gui) {
		setBounds(10, 10, 1000, 571);
		
		setTitle("Consulta y Listados");
		this.principal=gui;
		
		JPanel panel_2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(panel_2, BorderLayout.SOUTH);
		panel_2.setBorder(BorderFactory.createTitledBorder(""));
		
		JButton btnNewButton = new JButton("Cerrar");
		btnNewButton.setPreferredSize(new Dimension(250,24));
		btnNewButton.setIcon(new ImageIcon(getClass().getResource("/resource/close1.png")));
		btnNewButton.setPreferredSize(new Dimension(150,24));
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				principal.setConsultaNull();
				dispose();
			}
		});
		panel_2.add(btnNewButton);
		
		tabbedPane = new JTabbedPane(1);
		
		componentAlumnos = new PanelListadoAlumnos(gui);
		panelListadoProfesores=new PanelListadoProfesores(principal);
		panelListadoNoDocentes=new PanelListadoNoDocentes(principal);
		tabbedPane.addTab("  Alumnos  ", new ImageIcon(getClass().getResource("/resource/alumnos.png")),componentAlumnos);
		
		tabbedPane.addTab("  Profesores ", new ImageIcon(getClass().getResource("/resource/teacher.png")),panelListadoProfesores);
		
		tabbedPane.addTab("  No Docentes ", new ImageIcon(getClass().getResource("/resource/nodocente.png")),panelListadoNoDocentes);
		
		tabbedPane.setFont(new Font("arial",Font.BOLD,12));
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		
		

	}

}
