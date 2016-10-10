package comedor;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

public class About extends JDialog 
{
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	public About(ComedorGUI principal) 
	{
		super(principal,true);
		setTitle("Acerca de");
		setSize(308, 345);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

//		JLabel lblDesarrolladoPor = new JLabel("Desarrollado Por:");
//		lblDesarrolladoPor.setFont(new Font("Tahoma", Font.BOLD, 11));
//
//		JLabel lblJhonatanFerrer = new JLabel("Jhonatan Ferrer");
//
//		JLabel lblEmail = new JLabel("Email:");
//		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
//
//		JLabel lblLeyerelhackernet = new JLabel("Leyer@elhacker.net");
//
//		JLabel lblIde = new JLabel("IDE:");
//		lblIde.setFont(new Font("Tahoma", Font.BOLD, 11));
//
//		JLabel lblEclipse = new JLabel("Eclipse");

//		JPanel panel_1 = new JPanel();
//		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
//		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
//		gl_contentPanel.setHorizontalGroup(
//				gl_contentPanel.createParallelGroup(Alignment.LEADING)
//				.addGroup(gl_contentPanel.createSequentialGroup()
//						.addContainerGap()
//						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
//								.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//								.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
//								.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
//										.addComponent(lblDesarrolladoPor)
//										.addPreferredGap(ComponentPlacement.RELATED)
//										.addComponent(lblJhonatanFerrer))
//										.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
//												.addComponent(lblEmail)
//												.addPreferredGap(ComponentPlacement.RELATED)
//												.addComponent(lblLeyerelhackernet))
//												.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
//														.addComponent(lblIde)
//														.addPreferredGap(ComponentPlacement.RELATED)
//														.addComponent(lblEclipse)))
//														.addContainerGap())
//				);

//		gl_contentPanel.setVerticalGroup(
//				gl_contentPanel.createParallelGroup(Alignment.LEADING)
//				.addGroup(gl_contentPanel.createSequentialGroup()
//						.addContainerGap()
//						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
//						.addPreferredGap(ComponentPlacement.RELATED)
//						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
//								.addComponent(lblDesarrolladoPor)
//								.addComponent(lblJhonatanFerrer))
//								.addPreferredGap(ComponentPlacement.RELATED)
//								.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
//										.addComponent(lblEmail)
//										.addComponent(lblLeyerelhackernet))
//										.addPreferredGap(ComponentPlacement.RELATED)
//										.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
//												.addComponent(lblIde)
//												.addComponent(lblEclipse))
//												.addPreferredGap(ComponentPlacement.UNRELATED)
//												.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 37, Short.MAX_VALUE)
//												.addGap(17))
//				);

		JButton btnNewButton = new JButton("Cerrar");
		btnNewButton.addActionListener(
				new ActionListener() 
				{
					public void actionPerformed(ActionEvent arg0) 
					{
						dispose();
					}
				});

//		panel_1.add(btnNewButton);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(About.class.getResource("/resource/about1.png")));
		panel.add(label);

//		contentPanel.setLayout(gl_contentPanel);
		setLocationRelativeTo(principal);
	}
}
