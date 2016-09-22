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

public class GUICarnets extends JFrame
{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//
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
//			JSplitPane jSplitPane=new JSplitPane();
//			jSplitPane.setDividerLocation(425);
//			Carnet carnetFrontal=new Carnet(1);
//			Carnet carnetPosterior=new Carnet(2);
//			carnetFrontal.setModo(1);
//			carnetPosterior.setModo(1);
//			
//			jSplitPane.setLeftComponent(carnetFrontal);
//			jSplitPane.setRightComponent(carnetPosterior);
//			tabbedPane.addTab("Edicion de Carnet Vertical",jSplitPane);
		}
		{
			JSplitPane jSplitPane=new JSplitPane();
		
			jSplitPane.setDividerLocation(425);
			
			Carnet carnetFrontal=new Carnet(1,null);
//			
//			Carnet carnetPosterior=new Carnet(2);
			carnetFrontal.setModo(2);
//			carnetPosterior.setModo(2);
			carnetFrontal.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 130));
////			carnetFrontal.setBackground(Color.BLACK);
////			carnetPosterior.setBackground(Color.black);
//			carnetPosterior.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 170));
//			JTabbedPane n =new JTabbedPane();
//			n.addTab("Carnet Frontal", carnetFrontal);
//			n.addTab("Carnet Posterior", carnetPosterior);
//			System.out.println(n.getComponentAt(0).getWidth()+"/"+n.getComponentAt(0).getHeight());
			
			tabbedPane.addTab("Edicion de Carnet Horizontal",carnetFrontal);
		}
//		System.out.println(car);
		
	
		JKStatusBar bar = new JKStatusBar(JKStatusBar.ON_LEFT,new java.awt.Font("tahoma",Font.BOLD,12));
		
		bar.addComponent("Estatus:", "Edicion Iniciada.",null, Color.blue);
		getContentPane().add(bar,BorderLayout.SOUTH);
	
		getContentPane().add(tabbedPane,BorderLayout.CENTER);
		setSize(870,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setLocationRelativeTo(null);
		
		
		
	}
	public static void main(String[] args) {
//		try {
//			
////			JOptionPane.showMessageDialog(null, JKDataBase.DB2);
////			System.out.println(JKDataBase.DB2);
//			new JKActiveSyntethica();
//
////			UIManager.setLookAndFeel(new SyntheticaBlackStarLookAndFeel());
//		} catch (UnsupportedLookAndFeelException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (ParseException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
////		}
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUICarnets frame = new GUICarnets();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
