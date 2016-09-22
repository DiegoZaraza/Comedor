package comedor;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class ImpresionCarnets extends JInternalFrame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the frame.
	 */
	public ImpresionCarnets(ComedorGUI gui ){
		setTitle("Impresion de Carnets");
		setBounds(10, 10, 884, 585);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Carnets de Alumnos",new ImageIcon(getClass().getResource("/resource/a8.png")),new PanelCarnetsAlumno(gui,this));
		tabbedPane.addTab("Carnets de Profesores",new ImageIcon(getClass().getResource("/resource/a8.png")),new PanelCarnetsProfesores(gui,this));
		tabbedPane.addTab("Carnets de Personal No Docente",new ImageIcon(getClass().getResource("/resource/a8.png")),new PanelCarnetsPAS(gui,this));
//		tabbedPane.addTab("Carnets de Profesores");
//		tabbedPane.addTab("Carnets de P.A.S");
		panel.add(tabbedPane);
	}
}