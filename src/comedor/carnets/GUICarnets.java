package comedor.carnets;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.itextpdf.text.Font;
import com.leyer.JKStatusBar;
import comedor.bd.LogEjecucion;

public class GUICarnets extends JFrame
{
	private static final long serialVersionUID = 1L;
	static LogEjecucion log = new LogEjecucion();
	
	public GUICarnets()
	{
		setTitle("Edicion de Carnet");
		JMenuBar bar1 = new JMenuBar();
		JMenu jMenu = new JMenu("Archivo");
		JMenu jMenu2 = new JMenu("Herramientas");

		bar1.add(jMenu);
		bar1.add(jMenu2);
		setJMenuBar(bar1);
		setLayout(new BorderLayout());
		JTabbedPane tabbedPane=new JTabbedPane();
		{
			JSplitPane jSplitPane=new JSplitPane();

			jSplitPane.setDividerLocation(425);

			Carnet carnetFrontal=new Carnet(1, null);
			carnetFrontal.setModo(2);
			carnetFrontal.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 130));
			tabbedPane.addTab("Edicion de Carnet Horizontal",carnetFrontal);
		}
		JKStatusBar bar = new JKStatusBar(JKStatusBar.ON_LEFT,new java.awt.Font("tahoma", Font.BOLD, 12));

		bar.addComponent("Estatus:", "Edicion Iniciada.", null, Color.blue);
		getContentPane().add(bar, BorderLayout.SOUTH);

		getContentPane().add(tabbedPane,BorderLayout.CENTER);
		setSize(870,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) 
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			log.grabarArchivo(e.getMessage()+ "," + e.getLocalizedMessage());
		} 
		catch (InstantiationException e) 
		{
			e.printStackTrace();
			log.grabarArchivo(e.getMessage()+ "," + e.getLocalizedMessage());
		} 
		catch (IllegalAccessException e) 
		{
			e.printStackTrace();
			log.grabarArchivo(e.getMessage()+ "," + e.getLocalizedMessage());
		}
		catch (UnsupportedLookAndFeelException e) 
		{
			e.printStackTrace();
			log.grabarArchivo(e.getMessage()+ "," + e.getLocalizedMessage());
		}

		EventQueue.invokeLater(
				new Runnable() 
				{
					public void run() 
					{
						try 
						{
							GUICarnets frame = new GUICarnets();
							frame.setVisible(true);
						} 
						catch (Exception e) 
						{
							e.printStackTrace();
							log.grabarArchivo(e.getMessage()+ "," + e.getLocalizedMessage());
						}
					}
				});
	}
}