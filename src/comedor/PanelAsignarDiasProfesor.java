package comedor;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.JTextField;
import com.leyer.JKComboBox;
import com.leyer.JKPanel;
import com.leyer.JKTable;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import javax.swing.SwingConstants;

import javax.swing.JSeparator;
import java.awt.Color;

public class PanelAsignarDiasProfesor extends JPanel implements Runnable
{
	private static final long serialVersionUID = 1L;
	private JDateChooser textFieldDia;
	private JKComboBox comboBox;
	private ComedorGUI principal;
	private String documento;
	private JKPanel jkPanel;
	private JKTable jkTable;
	private JKTable tableRemesas;
	private JKComboBox comboBox_2;
	private JLabel label_8;
	private JLabel label_9;
	private JKComboBox comboBox_1;
	private JTextField textFieldComision;

	@Override
	public void paintComponent(Graphics g)
	{
		setOpaque(false);
		g.drawImage(new ImageIcon(getClass().getResource("/resource/e2.jpg")).getImage(), 0, 0, getWidth(), getHeight(), null);
	}

	public PanelAsignarDiasProfesor getInstance()
	{
		return this;
	}

	public PanelAsignarDiasProfesor(final ComedorGUI principal, final String documento)
	{
		this.principal = principal;
		this.documento = documento;
		JPanel panel = new JPanel(new GridLayout());
		jkPanel = new JKPanel();
		panel.add(jkPanel);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Foto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setOpaque(false);
		JLabel lblDocumento = new JLabel("Documento:");
		JLabel labelDocumento = new JLabel("" + documento.toUpperCase());
		labelDocumento.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel labelNombre = new JLabel("" + principal.getBaseDeDatos().getNombreTutor(documento));
		try
		{
			byte b[] = principal.getBaseDeDatos().getFotoProfesor(documento);
			
			if (b != null)
			{
				if (b.length > 1)
				{
					jkPanel.setBackground(new ImageIcon(b));
					panel.updateUI();
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		labelNombre.setFont(new Font("Tahoma", Font.BOLD, 11));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Dias", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setOpaque(false);
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Nueva Remesa", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setOpaque(false);
		JSeparator separator = new JSeparator();

		JLabel lblApellidosYNombre = new JLabel("Apellidos y Nombre:");
		lblApellidosYNombre.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JLabel lblEstadoDeRemesa = new JLabel("Remesa Seleccionada:");

		comboBox_1 = new JKComboBox();
		comboBox_1.addItem("PAGADO");
		comboBox_1.addItem("PAGADO CON COMISION");
		comboBox_1.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if (comboBox_1.getSelectedItem().toString().equalsIgnoreCase("PAGADO CON COMISION"))
					textFieldComision.setEnabled(true);
				else
					textFieldComision.setEditable(false);
			}
		});
		
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		
		gl_panel_3.setHorizontalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING).addGroup(
		        gl_panel_3.createSequentialGroup().addContainerGap().addComponent(lblEstadoDeRemesa).addPreferredGap(ComponentPlacement.RELATED).addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_panel_3.setVerticalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING).addGroup(
		        gl_panel_3.createSequentialGroup().addContainerGap()
		                .addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE).addComponent(lblEstadoDeRemesa).addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(26)));
		panel_3.setLayout(gl_panel_3);

		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JButton btnNewButton = new JButton("Aceptar");
		
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if (tableRemesas.getSelectedRow() != -1)
				{
					String nro = tableRemesas.getValueAt(tableRemesas.getSelectedRow(), 0).toString();

					if (comboBox_1.getSelectedItem().toString().equalsIgnoreCase("PAGADO"))
						principal.getBaseDeDatos().updateRemesaProfesor(nro, documento, "PAGADO", 0);
					else
						principal.getBaseDeDatos().updateRemesaProfesor(nro, documento, "PAGADO CON COMISION", Float.parseFloat(textFieldComision.getText()));

					new Thread(getInstance()).start();
				}
				else
				{
					JOptionPane.showMessageDialog(principal, "Debe seleccionar una remesa!", "Seleccion", JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(
		        groupLayout
		                .createSequentialGroup()
		                .addContainerGap()
		                .addComponent(panel, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
		                .addPreferredGap(ComponentPlacement.RELATED)
		                .addGroup(
		                        groupLayout
		                                .createParallelGroup(Alignment.LEADING)
		                                .addGroup(
		                                        groupLayout.createSequentialGroup().addGap(4).addComponent(lblDocumento).addPreferredGap(ComponentPlacement.RELATED).addComponent(labelDocumento).addPreferredGap(ComponentPlacement.RELATED)
		                                                .addComponent(lblApellidosYNombre, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED)
		                                                .addComponent(separator, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED).addComponent(labelNombre))
		                                .addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE)
		                                .addGroup(
		                                        groupLayout
		                                                .createSequentialGroup()
		                                                .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
		                                                .addPreferredGap(ComponentPlacement.RELATED)
		                                                .addGroup(
		                                                        groupLayout
		                                                                .createParallelGroup(Alignment.LEADING)
		                                                                .addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
		                                                                .addComponent(panel_5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                                                                .addGroup(
		                                                                        groupLayout.createSequentialGroup().addComponent(panel_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                                                                                .addPreferredGap(ComponentPlacement.RELATED).addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
		                                                                                .addPreferredGap(ComponentPlacement.RELATED).addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))))).addContainerGap()));
		
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
		        .addGroup(
		                groupLayout
		                        .createSequentialGroup()
		                        .addContainerGap()
		                        .addGroup(
		                                groupLayout
		                                        .createParallelGroup(Alignment.LEADING)
		                                        .addGroup(
		                                                groupLayout
		                                                        .createSequentialGroup()
		                                                        .addGroup(
		                                                                groupLayout.createParallelGroup(Alignment.TRAILING).addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                                                                        .addComponent(labelNombre)).addGap(57).addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
		                                        .addGroup(
		                                                groupLayout
		                                                        .createParallelGroup(Alignment.BASELINE)
		                                                        .addComponent(panel, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
		                                                        .addGroup(
		                                                                groupLayout
		                                                                        .createSequentialGroup()
		                                                                        .addGroup(
		                                                                                groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblDocumento, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE).addComponent(labelDocumento)
		                                                                                        .addComponent(lblApellidosYNombre))
		                                                                        .addPreferredGap(ComponentPlacement.RELATED)
		                                                                        .addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
		                                                                        .addPreferredGap(ComponentPlacement.RELATED)
		                                                                        .addGroup(
		                                                                                groupLayout
		                                                                                        .createParallelGroup(Alignment.TRAILING)
		                                                                                        .addComponent(panel_1, 0, 0, Short.MAX_VALUE)
		                                                                                        .addGroup(
		                                                                                                groupLayout
		                                                                                                        .createSequentialGroup()
		                                                                                                        .addGroup(
		                                                                                                                groupLayout
		                                                                                                                        .createParallelGroup(Alignment.LEADING)
		                                                                                                                        .addGroup(
		                                                                                                                                groupLayout
		                                                                                                                                        .createSequentialGroup()
		                                                                                                                                        .addGap(74)
		                                                                                                                                        .addGroup(
		                                                                                                                                                groupLayout.createParallelGroup(Alignment.LEADING)
		                                                                                                                                                        .addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
		                                                                                                                                                        .addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)))
		                                                                                                                        .addGroup(groupLayout.createSequentialGroup().addGap(86).addComponent(btnNewButton)))
		                                                                                                        .addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
		                                                                                                        .addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)))))).addContainerGap()));

		JLabel lblMontoComision = new JLabel("Monto Comision:");

		textFieldComision = new JTextField();
		textFieldComision.setColumns(10);
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(gl_panel_6.createParallelGroup(Alignment.LEADING).addGroup(
		        gl_panel_6.createSequentialGroup().addContainerGap().addComponent(lblMontoComision).addPreferredGap(ComponentPlacement.RELATED)
		                .addComponent(textFieldComision, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addContainerGap(74, Short.MAX_VALUE)));
		
		gl_panel_6.setVerticalGroup(gl_panel_6.createParallelGroup(Alignment.LEADING).addGroup(
		        gl_panel_6.createSequentialGroup().addContainerGap()
		                .addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE).addComponent(lblMontoComision).addComponent(textFieldComision, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		                .addContainerGap(16, Short.MAX_VALUE)));
		panel_6.setLayout(gl_panel_6);

		JButton btnNewButton_2 = new JButton("Generar Remesa");
		btnNewButton_2.setIcon(new ImageIcon(PanelAsignarDiasProfesor.class.getResource("/resource/n41.png")));
		btnNewButton_2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{

				int t = JOptionPane.showConfirmDialog(principal, "Continuar?", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
				if (t == JOptionPane.OK_OPTION)
				{
					principal.getBaseDeDatos().addRemesaProf(documento);
					new Thread(getInstance()).start();
				}
			}
		});
		
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(gl_panel_5.createParallelGroup(Alignment.LEADING).addGroup(
				gl_panel_5.createSequentialGroup().addContainerGap().addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE).addGap(343)));
		
		gl_panel_5.setVerticalGroup(gl_panel_5.createParallelGroup(Alignment.LEADING).addGroup(
				gl_panel_5.createSequentialGroup().addContainerGap().addComponent(btnNewButton_2).addContainerGap()));
		
		panel_5.setLayout(gl_panel_5);
		panel_4.setLayout(new GridLayout(0, 1, 0, 0));

		tableRemesas = new JKTable();
		tableRemesas.addColumn("Recibo");
		tableRemesas.addColumn("Mes");
		tableRemesas.addColumn("Remesa");
		tableRemesas.addColumn("Estado");
		tableRemesas.getColumn("Recibo").setPreferredWidth(4);
		tableRemesas.getColumn("Recibo").setWidth(4);

		tableRemesas.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseReleased(MouseEvent arg0)
			{
				if (tableRemesas.getSelectedRow() != tableRemesas.getRowCount() - 1)
				{
					tableRemesas.clearSelection();
					tableRemesas.setSelectedRow(tableRemesas.getRowCount() - 1);
				}
			}

			@Override
			public void mousePressed(MouseEvent arg0) { }

			@Override
			public void mouseExited(MouseEvent arg0) { }

			@Override
			public void mouseEntered(MouseEvent arg0) { }

			@Override
			public void mouseClicked(MouseEvent arg0) { }
		});

		JScrollPane scrollPane_1 = new JScrollPane(tableRemesas);
		panel_4.add(scrollPane_1);

		JLabel lblSeleccionDeDia = new JLabel("Seleccion de Dia:");

		textFieldDia = new JDateChooser();

		JLabel lblSeleccion = new JLabel("Seleccion:");

		comboBox = new JKComboBox();
		comboBox.addItem("Ordinario");
		comboBox.addItem("Extraordinario");

		JButton btnNewButton_1 = new JButton("OK");
		btnNewButton_1.setIcon(new ImageIcon(PanelAsignarDiasProfesor.class.getResource("/resource/n41.png")));
		btnNewButton_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				Date d = textFieldDia.getDate();
				if (d == null)
				{
					JOptionPane.showMessageDialog(principal, "Debe seleccionar un dia!", "Seleccion", JOptionPane.WARNING_MESSAGE);
					return;
				}
				boolean h = principal.getBaseDeDatos().addProfesor(d, comboBox.getSelectedItem().toString(), PanelAsignarDiasProfesor.this.documento);

				String mes = comboBox_2.getSelectedItem().toString();
				if (mes.equalsIgnoreCase("TODOS"))
				{
					jkTable.search("");
					int c1 = 0, c2 = 0;
					for (int index = 0; index < jkTable.getRowCount(); index++)
					{
						String c = jkTable.getValueAt(index, 1).toString();
						
						if (c.equalsIgnoreCase("Ordinario"))
							c1 += 1;
						else
							c2 += 1;
					}
					label_8.setText("" + c1);
					label_9.setText("" + c2);
				}
				else
				{
					String x = mes.split("-")[0];

					jkTable.search("-" + x + "-");

					int c1 = 0, c2 = 0;
					for (int index = 0; index < jkTable.getRowCount(); index++)
					{
						String c = jkTable.getValueAt(index, 1).toString();
						if (c.equalsIgnoreCase("Ordinario"))
							c1 += 1;
						else
							c2 += 1;
					}
					label_8.setText("" + c1);
					label_9.setText("" + c2);
				}

				if (h)
					principal.getBaseDeDatos().getDiasProfesor(documento, jkTable);
			}
		});
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addGroup(
		        gl_panel_2.createSequentialGroup().addContainerGap().addComponent(lblSeleccionDeDia).addPreferredGap(ComponentPlacement.RELATED).addComponent(textFieldDia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                .addGap(18).addComponent(lblSeleccion).addPreferredGap(ComponentPlacement.RELATED).addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.UNRELATED)
		                .addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE).addContainerGap()));
		
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addGroup(
		        gl_panel_2
		                .createSequentialGroup()
		                .addContainerGap()
		                .addGroup(
		                        gl_panel_2
		                                .createParallelGroup(Alignment.TRAILING, false)
		                                .addComponent(lblSeleccionDeDia, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                                .addGroup(
		                                        Alignment.LEADING,
		                                        gl_panel_2.createParallelGroup(Alignment.BASELINE).addComponent(textFieldDia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(lblSeleccion)
		                                                .addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                                                .addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		
		panel_2.setLayout(gl_panel_2);

		textFieldDia.getDateEditor().setEnabled(false);
		jkTable = new JKTable();
		jkTable.addColumn("Dias");
		jkTable.addColumn("Seleccion");
		principal.getBaseDeDatos().getDiasProfesor(documento, jkTable);
		
		JScrollPane scrollPane = new JScrollPane(jkTable);

		JLabel lblFiltrarPor = new JLabel("Filtrar:");

		comboBox_2 = new JKComboBox();
		comboBox_2.setForeground(new Color(60, 179, 113));
		comboBox_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		comboBox_2.addItem("01-ENERO");
		comboBox_2.addItem("02-FEBRERO");
		comboBox_2.addItem("03-MARZO");
		comboBox_2.addItem("04-ABRIL");
		comboBox_2.addItem("05-MAYO");
		comboBox_2.addItem("06-JUNIO");
		comboBox_2.addItem("07-JULIO" );
		comboBox_2.addItem("08-AGOSTO");
		comboBox_2.addItem("09-SEPTIEMBRE");
		comboBox_2.addItem("10-OCTUBRE");
		comboBox_2.addItem("11-NOVIEMBRE");
		comboBox_2.addItem("12-DICIEMBRE");
		comboBox_2.addItem("TODOS");
		label_8 = new JLabel("0");
		label_9 = new JLabel("0");
		
		comboBox_2.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				String mes = comboBox_2.getSelectedItem().toString();
				
				if (mes.equalsIgnoreCase("TODOS"))
				{
					jkTable.search("");
					int c1 = 0, c2 = 0;
					
					for (int index = 0; index < jkTable.getRowCount(); index++)
					{
						String c = jkTable.getValueAt(index, 1).toString();
						if (c.equalsIgnoreCase("Ordinario"))
							c1 += 1;
						else
							c2 += 1;
					}
					label_8.setText("" + c1);
					label_9.setText("" + c2);
				}
				else
				{
					String x = mes.split("-")[0];
					jkTable.search("-" + x + "-");

					int c1 = 0, c2 = 0;
					for (int index = 0; index < jkTable.getRowCount(); index++)
					{
						String c = jkTable.getValueAt(index, 1).toString();
						if (c.equalsIgnoreCase("Ordinario"))
							c1 += 1;
						else
							c2 += 1;
					}
					label_8.setText("" + c1);
					label_9.setText("" + c2);
				}
			}
		});
		
		comboBox_2.setSelectedIndex(10);
		JLabel lblOedinarios = new JLabel("Ordinarios:");

		label_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblExtraordinarios = new JLabel("Extraordinarios:");

		label_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(
		        gl_panel_1
		                .createSequentialGroup()
		                .addGroup(
		                        gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1.createSequentialGroup().addComponent(lblFiltrarPor).addPreferredGap(ComponentPlacement.RELATED).addComponent(comboBox_2, 0, 133, Short.MAX_VALUE))
		                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
		                                .addGroup(gl_panel_1.createSequentialGroup().addComponent(lblOedinarios).addPreferredGap(ComponentPlacement.RELATED).addComponent(label_8))
		                                .addGroup(gl_panel_1.createSequentialGroup().addComponent(lblExtraordinarios).addPreferredGap(ComponentPlacement.RELATED).addComponent(label_9))).addContainerGap()));
		
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(
		        gl_panel_1.createSequentialGroup().addGap(7)
		                .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(lblFiltrarPor).addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		                .addPreferredGap(ComponentPlacement.UNRELATED).addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE).addPreferredGap(ComponentPlacement.RELATED)
		                .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(lblOedinarios).addComponent(label_8)).addGap(9)
		                .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(lblExtraordinarios).addComponent(label_9))));
		
		textFieldComision.setEnabled(false);
		panel_1.setLayout(gl_panel_1);
		setLayout(groupLayout);

		new Thread(this).start();
	}

	@Override
	public void run()
	{
		principal.getBaseDeDatos().getRemesas(tableRemesas, documento);
	}
}
