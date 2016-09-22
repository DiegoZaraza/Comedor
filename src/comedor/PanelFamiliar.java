package comedor;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import com.leyer.JKComboBox;
import javax.swing.SwingConstants;

public class PanelFamiliar extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JKComboBox comboBox;
	private JTextField textField_3;
	private JKComboBox comboBox_1;

	/**
	 * Create the panel.
	 * @param id 
	 * @param alumno 
	 * @param tipo_doc 
	 * @param documento 
	 * @param apellido2 
	 * @param apellido1 
	 * @param nombre 
	 * @param parentesco 
	 */
	
	public String getDocumento(){
		return textField_3.getText();
	}
	public void setDocumento(String nombre){
		textField_3.setText(nombre);
	}
	
	public String getNombre(){
		return textField.getText();
	}
	public void setNombre(String nombre){
		textField.setText(nombre);
	}
	public String getApellido1(){
		return textField_1.getText();
	}
	public void setApellido1(String apellido){
		textField_1.setText(apellido);
	}
	public String getApellido2(){
		return textField_2.getText();
	}
	public void setApellido2(String apellido){
		textField_2.setText(apellido);
	}
	public String getTipoDoc(){
		String g=comboBox.getSelectedItem().toString();
		if(g.equalsIgnoreCase("NIF")){
			return "1";
		}else if(g.equalsIgnoreCase("NIE")){
			return "2";
		}else{
			return "3";
		}
		
	}
	public void setTipoDoc(String tipo){
		if(tipo != null){
			if(tipo.equalsIgnoreCase("1")){
				comboBox.setSelectedIndex(0);
			}else if(tipo.equalsIgnoreCase("2")){
				comboBox.setSelectedIndex(1);
			}else{
				comboBox.setSelectedIndex(2);
			}
		}
//		for(int index=0;index<comboBox.getItemCount();index++){
//			if(comboBox.getSelectedItem().toString().equalsIgnoreCase(tipo)){
//				comboBox.setSelectedIndex(index);
//			}
//		}
	}
	public String getTutor(){
		if(comboBox_1.getSelectedItem().toString().equalsIgnoreCase("Si")){
			return "S";
		}else
		    return "N";
	}
	public void setTutor(String t){
		if(t.equalsIgnoreCase("S")){
			comboBox_1.setSelectedIndex(0);
		}else{
			comboBox_1.setSelectedIndex(1);
		}
	}
	public String getID(){
		return id;
	}
	public String getParentesco(){
		return parentesco;
	}
	public String getAlumno(){
		return alumno;
	}
   private String id,alumno ,parentesco;
	public PanelFamiliar(String parentesco, String nombre, String apellido1, String apellido2, String documento, String tipo_doc, String alumno, String id,String es_tutor) {
		this.parentesco=parentesco;
		this.alumno=alumno;
		this.id=id;
		
	
		JLabel lblNewLabel = new JLabel("Nombre:");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido1:");
		lblApellido.setHorizontalAlignment(SwingConstants.CENTER);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblApellido_1 = new JLabel("Apellido2:");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Tipo doc:");
		
		comboBox = new JKComboBox();
		comboBox.addItem("NIF");
		comboBox.addItem("NIE");
		comboBox.addItem("Pasaporte");
		
		JLabel lblDocumebto = new JLabel("Documento:");
		lblDocumebto.setHorizontalAlignment(SwingConstants.CENTER);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		JLabel lblEsTutor = new JLabel("Es Tutor:");
		
	   comboBox_1 = new JKComboBox();
	   comboBox_1.addItem("Si");
	   comboBox_1.addItem("No");
//	   comboBox_1.addItem("");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblApellido, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField)
								.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboBox, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblEsTutor, Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblApellido_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
								.addComponent(lblDocumebto, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
								.addComponent(textField_3)
								.addComponent(comboBox_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(167)))
					.addGap(325))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblApellido_1)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblApellido)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblDocumebto)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEsTutor)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(217, Short.MAX_VALUE))
		);
		setNombre(nombre);
		setApellido1(apellido1);
		setApellido2(apellido2);
		setTipoDoc(tipo_doc);
		setDocumento(documento);
		setTutor(es_tutor);
		setLayout(groupLayout);

	}
}
