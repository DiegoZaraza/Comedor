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
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.leyer.JKComboBox;
import com.toedter.calendar.JDateChooser;

import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NuevoPeriodoComedor extends JDialog
{
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JKComboBox comboBoxDias;
	private JPanel panel;
	private JDateChooser textFieldDateAlta;
	private JDateChooser textFieldDateBaja;
	private JRadioButton asiduoSi;
	private JRadioButton asiduoNo;
	private JRadioButton PuedeSalirNo;
	private JRadioButton PuedeSalirSi;
	private JRadioButton beca100;
	private JRadioButton beca70;
	private JRadioButton pagoHabitual;
	private ComedorGUI principal;
	private JButton btnActualizar;
	private String id = null;
	private JLabel label;

	public NuevoPeriodoComedor(ComedorGUI comedorGUI, final String nia, final boolean actualizar, final String id)
	{
		super(comedorGUI, true);
		setTitle("Periodo");

		if (!actualizar)
			setTitle("Periodo Comedor");
		else
			setTitle("Actualizar Periodo Comedor");

		setBounds(10, 10, 450, 234);
		this.id = id;
		this.principal = comedorGUI;

		getContentPane().setLayout(new BorderLayout());

		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

			JLabel lblUsuarioComedor = new JLabel("Usuario Comedor:");

			asiduoSi = new JRadioButton("Si");
			asiduoNo = new JRadioButton("No:");
			ButtonGroup buttonGroup = new ButtonGroup();
			buttonGroup.add(asiduoNo);
			buttonGroup.add(asiduoSi);

			JLabel lblTipoDeBeca = new JLabel("Tipo de Beca:");

			beca100 = new JRadioButton("100%");
			beca100.setSelected(true);
			beca70 = new JRadioButton("70%");
			ButtonGroup buttonGroup2 = new ButtonGroup();
			buttonGroup2.add(beca100);
			buttonGroup2.add(beca70);

			pagoHabitual = new JRadioButton("Pago Habitual");
			buttonGroup2.add(pagoHabitual);

			JLabel lblTipoDeUsuario = new JLabel("Tipo de Usuario:");

			comboBoxDias = new JKComboBox();
			comboBoxDias.addItem("2 Dias");
			comboBoxDias.addItem("3 Dias");

			JLabel lblFechaDeAlta = new JLabel("Fecha de Alta:");

			textFieldDateAlta = new JDateChooser();
			textFieldDateAlta.getDateEditor().setEnabled(false);

			JLabel lblFechaDeBaja = new JLabel("Fecha de Baja:");
			lblFechaDeBaja.setHorizontalAlignment(SwingConstants.RIGHT);

			textFieldDateBaja = new JDateChooser();
			textFieldDateBaja.getDateEditor().setEnabled(false);

			label = new JLabel("Puede Salir:");
			label.setVisible(false);
			PuedeSalirSi = new JRadioButton("Si");
			PuedeSalirNo = new JRadioButton("No");

			PuedeSalirSi.setVisible(false);
			PuedeSalirNo.setVisible(false);

			ButtonGroup buttonGroup3 = new ButtonGroup();
			buttonGroup3.add(PuedeSalirSi);
			buttonGroup3.add(PuedeSalirNo);

			asiduoNo.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseClicked(MouseEvent arg0)
				{
					label.setVisible(true);
					PuedeSalirSi.setVisible(true);
					PuedeSalirNo.setVisible(true);
				}
			});

			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup().addGroup(gl_panel
			        .createParallelGroup(
			                Alignment.LEADING)
			        .addGroup(gl_panel.createSequentialGroup().addGap(18)
			                .addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false).addComponent(lblFechaDeAlta)
			                        .addComponent(lblTipoDeUsuario, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
			                                Short.MAX_VALUE)
			                        .addComponent(lblFechaDeBaja, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
			                                Short.MAX_VALUE))
			                .addGap(4)
			                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
			                        .addComponent(textFieldDateAlta, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			                        .addComponent(textFieldDateBaja, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			                        .addComponent(comboBoxDias, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
			        .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
			                .addGroup(gl_panel.createSequentialGroup().addContainerGap().addComponent(lblUsuarioComedor).addGap(4)
			                        .addComponent(asiduoSi).addGap(2).addComponent(asiduoNo).addGap(18)
			                        .addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			                        .addPreferredGap(ComponentPlacement.UNRELATED)
			                        .addComponent(PuedeSalirSi, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
			                        .addPreferredGap(ComponentPlacement.UNRELATED)
			                        .addComponent(PuedeSalirNo, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
			                        .addPreferredGap(ComponentPlacement.RELATED))
			                .addGroup(gl_panel.createSequentialGroup().addGap(31).addComponent(lblTipoDeBeca).addGap(4).addComponent(beca100)
			                        .addComponent(beca70, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
			                        .addComponent(pagoHabitual, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))))
			        .addGap(43)));

			gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup().addGap(11)
			        .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
			                .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(label).addComponent(PuedeSalirSi)
			                        .addComponent(PuedeSalirNo))
			                .addGroup(gl_panel.createSequentialGroup().addGap(4).addComponent(lblUsuarioComedor)).addComponent(asiduoSi)
			                .addComponent(asiduoNo))
			        .addPreferredGap(ComponentPlacement.RELATED)
			        .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
			                .addGroup(gl_panel.createSequentialGroup().addGap(4).addComponent(lblTipoDeBeca)).addComponent(beca100)
			                .addComponent(beca70).addComponent(pagoHabitual))
			        .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
			                .addGroup(gl_panel.createSequentialGroup().addGap(5).addComponent(lblTipoDeUsuario))
			                .addGroup(gl_panel.createSequentialGroup().addGap(2).addComponent(comboBoxDias, GroupLayout.PREFERRED_SIZE,
			                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
			        .addPreferredGap(ComponentPlacement.UNRELATED)
			        .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblFechaDeAlta).addComponent(textFieldDateAlta,
			                GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
			        .addPreferredGap(ComponentPlacement.RELATED)
			        .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblFechaDeBaja).addComponent(textFieldDateBaja,
			                GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
			        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
			panel.setLayout(gl_panel);
		}

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
		        gl_contentPanel.createSequentialGroup().addContainerGap()
		                .addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
		                        .addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE).addComponent(panel,
		                                Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 414, Short.MAX_VALUE))
		                .addContainerGap()));

		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
		        .addGroup(gl_contentPanel.createSequentialGroup().addComponent(panel, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
		                .addPreferredGap(ComponentPlacement.RELATED).addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
		                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.setIcon(new ImageIcon(NuevoPeriodoComedor.class.getResource("/resource/close1.png")));
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

		btnNewButton.setIcon(new ImageIcon(NuevoPeriodoComedor.class.getResource("/resource/A1-init.png")));
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
					textFieldDateAlta.getDate();
				}
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(principal, "Debe seleccionar una Fecha de Alta", "Fecha Alta", JOptionPane.WARNING_MESSAGE);
					return;
				}

				try
				{
					textFieldDateBaja.getDate();
				}
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(principal, "Debe seleccionar una Fecha de Baja", "Fecha Baja", JOptionPane.WARNING_MESSAGE);
					return;
				}

				int v = JOptionPane.showConfirmDialog(principal, "Esta seguro(a) de Agregar este Periodo?", "Conformacion",
		                JOptionPane.INFORMATION_MESSAGE);

				if (v == JOptionPane.OK_OPTION)
				{
					String lunesMartes = "Si";
					String jueves = "";

					if (getTipoUsuario().startsWith("2"))
						jueves = "No";
					else
						jueves = "Si";

					boolean n = principal.getBaseDeDatos().addFechas(getfechaAlta(), getfechaBaja(), nia, getTipoBeca(), getTipoUsuario(), isAsiduo(),
		                    lunesMartes, jueves, isSalida());

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
				try
				{
					textFieldDateAlta.getDate();
				}
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(principal, "Debe seleccionar una Fecha de Alta", "Fecha Alta", JOptionPane.WARNING_MESSAGE);
					return;
				}
				try
				{
					textFieldDateBaja.getDate();
				}
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(principal, "Debe seleccionar una Fecha de Baja", "Fecha Baja", JOptionPane.WARNING_MESSAGE);
					return;
				}

				int v = JOptionPane.showConfirmDialog(principal, "Esta seguro(a) de Aplicar la Actualizacion este Periodo?", "Confirmacion",
		                JOptionPane.INFORMATION_MESSAGE);

				if (v == JOptionPane.OK_OPTION)
				{
					System.out.println(getAutJueves() + " - " + getAutLunesMar()); 
					
					principal.getBaseDeDatos().eliminarPeriodoComedor(id);
					boolean n = principal.getBaseDeDatos().addFechas(getfechaAlta(), getfechaBaja(), nia, getTipoBeca(), getTipoUsuario(), 
							isAsiduo(), getAutLunesMar(), getAutJueves(), isSalida());

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

		if (actualizar)
			btnActualizar.setEnabled(true);
		else
			btnActualizar.setEnabled(false);

		btnActualizar.setPreferredSize(new Dimension(120, 24));
		panel_2.add(btnActualizar);
		panel_2.add(btnNewButton);

		btnNewButton_1.setPreferredSize(new Dimension(120, 24));
		btnNewButton.setPreferredSize(new Dimension(120, 24));
		contentPanel.setLayout(gl_contentPanel);
		setLocationRelativeTo(comedorGUI);

		if (actualizar)
			getDatos();
	}

	String autLunesMar;

	public void setAutLunesMar(String autLunesMar)
	{
		this.autLunesMar = autLunesMar;
	}

	String autJueves;

	public String getAutJueves()
	{
		return autJueves;
	}

	public void setAutJueves(String autJueves)
	{
		this.autJueves = autJueves;
	}

	public String getAutLunesMar()
	{
		return autLunesMar;
	}

	private void getDatos()
	{
		principal.getBaseDeDatos().getDatosPeriodo(id, this);

		if (isAsiduo().equals("No"))
		{
			label.setVisible(true);
			PuedeSalirNo.setVisible(true);
			PuedeSalirSi.setVisible(true);
		}
	}

	public Date getfechaBaja()
	{
		try
		{
			new Date(textFieldDateBaja.getDateEditor().getDate().getTime()).toString();
		}
		catch (Exception c)
		{
			return new Date(new java.util.Date().getTime());
		}
		return new Date(textFieldDateBaja.getDateEditor().getDate().getTime());
	}

	public Date getfechaAlta()
	{
		try
		{
			new Date(textFieldDateAlta.getDateEditor().getDate().getTime()).toString();
		}
		catch (Exception c)
		{
			return new Date(new java.util.Date().getTime());
		}
		return new Date(textFieldDateAlta.getDateEditor().getDate().getTime());
	}

	public void setFechBaja(String fechaAlta)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");

		try
		{
			textFieldDateBaja.setDate(dateFormat.parse(fechaAlta));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void setFechAlta(String fechaAlta)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");

		try
		{
			textFieldDateAlta.setDate(dateFormat.parse(fechaAlta));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void setBeca(String t)
	{
		if (t.startsWith("100"))
			beca100.setSelected(true);
		else if (t.startsWith("70"))
			beca70.setSelected(true);
		else
			pagoHabitual.setSelected(true);
	}

	public String getTipoBeca()
	{
		if (beca100.isSelected())
			return "100";
		else if (beca70.isSelected())
			return "70";
		else
			return "0";
	}

	public void setAsiduo(String b)
	{
		if (b.equals("Si"))
			asiduoSi.setSelected(true);
		else
			asiduoNo.setSelected(true);
	}

	public String isAsiduo()
	{
		if (asiduoSi.isSelected())
			return "Si";
		else
			return "No";
	}

	public void setSalida(String b)
	{
		if (b.equals("Si"))
			PuedeSalirSi.setSelected(true);
		else
			PuedeSalirNo.setSelected(true);
	}

	public String isSalida()
	{
		if (PuedeSalirSi.isSelected())
			return "Si";
		else
			return "No";
	}

	public void setTipoUsuario(String tipo)
	{
		if (tipo.startsWith("2"))
			comboBoxDias.setSelectedIndex(0);
		else
			comboBoxDias.setSelectedIndex(1);
	}

	public String getTipoUsuario()
	{
		if (comboBoxDias.getSelectedItem().toString().startsWith("2"))
			return "2";
		else
			return "3";
	}
}