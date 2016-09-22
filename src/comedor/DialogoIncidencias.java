package comedor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;

import javax.swing.ImageIcon;

import com.toedter.calendar.JDateChooser;

public class DialogoIncidencias extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();

	private ComedorGUI principal;

	public DialogoIncidencias getInstance(){
		return this;
	}
	private JPanel panelx;
	public void addIncidencia(String fecha,String incidencia, String id){
		JPanel panel_1 = new JPanel(new GridLayout());
		panel_1.setBorder(BorderFactory.createTitledBorder("Fecha de la Incidencia: "+fecha));
		JTextArea area=new JTextArea();
		
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		area.setEditable(false);
		area.setText(incidencia);
		JScrollPane jScrollPane=new JScrollPane(area);
		panel_1.add(jScrollPane);
		panel_1.setOpaque(false);
		panel_1.setBackground(Color.WHITE);
		panelx.add(panel_1);
	}
	public DialogoIncidencias(RegistrarAlumno alumno,ComedorGUI comedorGUI,final  String nia) {
		super(comedorGUI,true);
		setTitle("Incidencias del Alumno");
		this.principal=comedorGUI;
		setSize(450, 300);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		getContentPane().setLayout(new BorderLayout());
//		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(Color.white);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.SOUTH);
			{
				JButton btnNewButton = new JButton("Cerrar");
				btnNewButton.setIcon(new ImageIcon(DialogoIncidencias.class.getResource("/resource/A1-close.png")));
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				panel.setLayout(new GridLayout(0, 2, 0, 0));
				panel.add(btnNewButton);
			}
			{
				JButton btnNewButton_1 = new JButton("Agregar");
				btnNewButton_1.setIcon(new ImageIcon(DialogoIncidencias.class.getResource("/resource/add1.png")));
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						final JDialog dialog=new JDialog(getInstance(),true);
						
						JPanel panel1=new JPanel(new FlowLayout(FlowLayout.LEFT));
						panel1.add(new JLabel("Fecha: "));
						final JDateChooser chooser=new JDateChooser();
						chooser.setDate(new Date());
						chooser.getDateEditor().setEnabled(false);
						panel1.add(chooser);
						dialog.setLayout(new BorderLayout());
						final JTextArea area=new JTextArea();
						area.setLineWrap(true);
						area.setWrapStyleWord(true);
						JScrollPane scrollPane=new JScrollPane(area);
						dialog.add(scrollPane,BorderLayout.CENTER);
						JPanel panel2=new JPanel();
						JButton button1=new JButton("Cancelar");
						button1.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent arg0) {
								// TODO Auto-generated method stub
								dialog.dispose();
							}
						});
						JButton button2=new JButton("Aceptar");
						button2.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent arg0) {
								// TODO Auto-generated method stub
								if(area.getText().length()==0){
									JOptionPane.showMessageDialog(dialog, "Debe agregan la incidencia...","Vacio",JOptionPane.WARNING_MESSAGE);
									return;
								}
								java.sql.Date s=new java.sql.Date(chooser.getDate().getTime());
								String in=area.getText();
								boolean j=principal.getBaseDeDatos().registrarIncidencia(nia,s,in);
							
								if(j){
									panelx.removeAll();
									principal.getBaseDeDatos().getIncidencia(getInstance(),nia);
									dialog.dispose();
									panelx.repaint();
								}else{
									JOptionPane.showMessageDialog(dialog, "No se ha podido registrar la incidencia!","",JOptionPane.ERROR_MESSAGE);
								}
							}
						});
						panel2.add(button1);
						panel2.add(button2);
						dialog.add(panel2,BorderLayout.SOUTH);
						dialog.setSize(260,160);
						dialog.setLocationRelativeTo(getInstance());
						dialog.setTitle("Nueva Incidencia");
						dialog.setVisible(true);
					}
				});
				panel.add(btnNewButton_1);
			}
		}
		{
			panelx = new JPanel();
			panelx.setBackground(Color.WHITE);
			panelx.setBorder(BorderFactory.createTitledBorder(""));
			panelx.setPreferredSize(new Dimension(0,1800));
			JScrollPane scrollPane=new JScrollPane(panelx);
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			panelx.setLayout(new GridLayout(20, 0, 0, 0));
			{
				
			}
		}
		setLocationRelativeTo(comedorGUI);
		principal.getBaseDeDatos().getIncidencia(this,nia);
	}

}
