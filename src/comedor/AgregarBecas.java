package comedor;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

import javax.swing.JTextField;
import com.leyer.JKComboBox;

public class AgregarBecas extends JDialog
{
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPanel panel;
	private ComedorGUI principal;
	private JButton btnActualizar;
	private boolean actualizar = false;
	private JKComboBox comboPagoUnico;
	
	public AgregarBecas(ComedorGUI comedorGUI)
	{
		super(comedorGUI, true);
		setTitle("Periodo");

			setTitle("Periodo Comedor");

		setBounds(10, 10, 450, 193);
		this.principal = comedorGUI;

		getContentPane().setLayout(new BorderLayout());

		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			new ButtonGroup();
			new ButtonGroup();

			JLabel lblTipoBeca = new JLabel("Tipo Beca:");

			JLabel lblValorBeca = new JLabel("Valor Beca:");
			lblValorBeca.setHorizontalAlignment(SwingConstants.RIGHT);

			new ButtonGroup();

			txtTipoBeca = new JTextField();
			txtTipoBeca.setColumns(10);

			txtTipoBeca.addKeyListener(new KeyListener()
			{
				@Override
				public void keyTyped(KeyEvent arg0)	{ }

				@Override
				public void keyReleased(KeyEvent arg0)
				{
					principal.getBaseDeDatos().consultarBecas(getInstance(), getTipoBeca());

					btnActualizar.setEnabled(true);
				}

				@Override
				public void keyPressed(KeyEvent arg0){ }
			});


			txtValorBeca = new JTextField();
			txtValorBeca.setColumns(10);
			
			JLabel lblPagoUnico = new JLabel("Pago Unico:");
			lblPagoUnico.setHorizontalAlignment(SwingConstants.RIGHT);
			
			comboPagoUnico = new JKComboBox();
			comboPagoUnico.addItem("Si");
			comboPagoUnico.addItem("No");
			
			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_panel.createSequentialGroup()
						.addGap(105)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
							.addComponent(lblTipoBeca)
							.addComponent(lblValorBeca)
							.addComponent(lblPagoUnico, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(comboPagoUnico, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(txtTipoBeca, Alignment.LEADING)
							.addComponent(txtValorBeca, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
						.addGap(96))
			);
			gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblTipoBeca)
							.addComponent(txtTipoBeca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblValorBeca)
							.addComponent(txtValorBeca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(comboPagoUnico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblPagoUnico))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			);
			panel.setLayout(gl_panel);
		}

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addGap(5)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);

		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.setIcon(new ImageIcon(AgregarBecas.class.getResource("/resource/close1.png")));
		btnNewButton_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				dispose();
			}
		});
		panel_2.add(btnNewButton_1);

		JButton btnNewButton = new JButton("Aceptar");

		if (actualizar)
			btnNewButton.setEnabled(false);

		btnNewButton.setIcon(new ImageIcon(AgregarBecas.class.getResource("/resource/A1-init.png")));
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if (getTipoBeca().length() <= 0)
				{
					JOptionPane.showMessageDialog(principal, "Debe digitar el Tipo de Beca", "Tipo Beca", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (("" + getValorBeca()).length() <= 0)
				{
					JOptionPane.showMessageDialog(principal, "Debe digitar el Valor", "Valor Beca", JOptionPane.WARNING_MESSAGE);
					return;
				}

				int v = JOptionPane.showConfirmDialog(principal, "Esta seguro(a) de Agregar este Tipo de Beca?", "Conformacion",
						JOptionPane.INFORMATION_MESSAGE);

				if (v == JOptionPane.OK_OPTION)
				{
					boolean n = principal.getBaseDeDatos().addBecas(getTipoBeca(), getValorBeca(), getPagoUnico());

					if (n)
					{
						JOptionPane.showMessageDialog(principal, "Procesado Correctamente!", "Exito", JOptionPane.INFORMATION_MESSAGE);
						dispose();
					}
					else
						return;
				}
			}
		});

		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{

				if (getTipoBeca().length() <= 0)
				{
					JOptionPane.showMessageDialog(principal, "Debe digitar el Tipo de Beca", "Tipo Beca", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (("" + getValorBeca()).length() <= 0)
				{
					JOptionPane.showMessageDialog(principal, "Debe digitar el Valor", "Valor Beca", JOptionPane.WARNING_MESSAGE);
					return;
				}

				int v = JOptionPane.showConfirmDialog(principal, "Esta seguro(a) de Aplicar la Actualizacion esta Beca?", "Confirmacion",
						JOptionPane.INFORMATION_MESSAGE);

				if (v == JOptionPane.OK_OPTION)
				{

					boolean n = principal.getBaseDeDatos().updateBecas(getTipoBeca(), getValorBeca(), getPagoUnico());

					if (n)
					{
						JOptionPane.showMessageDialog(principal, "Procesado Correctamente!", "Exito", JOptionPane.INFORMATION_MESSAGE);
						dispose();
					}
					else
						return;
				}
			}
		});
		
		btnActualizar.setPreferredSize(new Dimension(120, 24));
		btnActualizar.setEnabled(false);
		panel_2.add(btnActualizar);
		panel_2.add(btnNewButton);

		btnNewButton_1.setPreferredSize(new Dimension(120, 24));
		btnNewButton.setPreferredSize(new Dimension(120, 24));
		contentPanel.setLayout(gl_contentPanel);
		setLocationRelativeTo(comedorGUI);
	}

	private JTextField txtTipoBeca;
	private JTextField txtValorBeca;

	public String getTipoBeca()
	{
		return txtTipoBeca.getText();
	}

	public float getValorBeca()
	{
		return Float.valueOf(txtValorBeca.getText());
	}

	public void setValorBeca(float valor)
	{
		txtValorBeca.setText("" + valor);
	}	
	
	public void setPagoUnico(String s)
	{
		if(s.equals("Si"))
			comboPagoUnico.setSelectedIndex(0);
		else
			comboPagoUnico.setSelectedIndex(1);
	}
	
	public String getPagoUnico()
	{
		return comboPagoUnico.getSelected();
	}
	
	private AgregarBecas getInstance()
	{
		return this;
	}
}