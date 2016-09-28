package comedor;

import itaca.Familiars;
import itaca.Grups;
import itaca.Imexalum;
import itaca.Pass;
import itaca.Profes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jdesktop.xswingx.PromptSupport;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.csvreader.CsvReader;
import com.leyer.JKList;
import javax.swing.JTextField;

public class ImporDataITACA extends JInternalFrame 
{
	private static final long serialVersionUID = 1L;
	private ComedorGUI principal;
	private JKList list;
	private JProgressBar progressBar;
	private JButton btnNewButton;
	private JLabel label_8;
	private JLabel label_9;
	private JLabel label_10;
	private JLabel label_11;
	private JLabel label_12;	
	private ArrayList<Familiars> arrayListFamiliars = new ArrayList<>();
	private ArrayList<Grups> arrayListGrups = new ArrayList<>();
	private ArrayList<Imexalum> arrayListImexalums = new ArrayList<>();
	private ArrayList<Pass> arrayListPas = new ArrayList<>();
	private ArrayList<Profes> arrayListProfes = new ArrayList<>();
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JProgressBar progressBarMain;
	private JTextField textField;
	private JCheckBox chckbxNewCheckBox_1;
	
	public String getCurso()
	{
		return textField.getText();
	}
	
	public void loadFileXML(File file)
	{
		File fXmlFile = file;
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		
		try 
		{
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			
			doc.getDocumentElement().normalize();		
			doc.getDocumentElement().getAttribute("codigo");
			doc.getDocumentElement().getAttribute("denominacion");
			doc.getDocumentElement().getAttribute("curso");
			
			String fechaExp="";
			
			fechaExp = doc.getDocumentElement().getAttribute("fechaExportacion");
			
			if(file.getName().indexOf("familiars.xml")!=-1)
			{
				label_8.setText(fechaExp);
				
				NodeList nList = doc.getElementsByTagName("familiar");
				
				for (int temp = 0; temp < nList.getLength(); temp++) 
				{
					Node nNode = nList.item(temp);
					Familiars familiars=new Familiars();
//					System.out.println("\nCurrent Element :" + nNode.getNodeName());
					
					Element eElement = (Element) nNode;
					familiars.setAlumno( eElement.getAttribute("alumno"));
					familiars.setParentesco(eElement.getAttribute("parentesco"));
					familiars.setNombre(eElement.getAttribute("nombre"));
					familiars.setApellido1(eElement.getAttribute("apellido1"));
					familiars.setApellido2(eElement.getAttribute("apellido2"));
					familiars.setTipo_doc(eElement.getAttribute("tipo_doc"));
					familiars.setEs_tutor(eElement.getAttribute("es_tutor"));
					familiars.setDocumento(eElement.getAttribute("documento"));
					arrayListFamiliars.add(familiars);
				}
				
			}
			else if(file.getName().indexOf("grups.xml")!=-1)
			{
				label_9.setText(fechaExp);
				
				NodeList nList = doc.getElementsByTagName("grupo");
				
				for (int temp = 0; temp < nList.getLength(); temp++) {
					Node nNode = nList.item(temp);
					Grups grups=new Grups();
					Element eElement = (Element) nNode;
					grups.setAula(eElement.getAttribute("aula"));
					grups.setCapacidad(eElement.getAttribute("capacaidad"));
					grups.setCodigo(eElement.getAttribute("codigo"));
					grups.setEnsenanza(eElement.getAttribute("ensenanza"));
					grups.setLinea(eElement.getAttribute("linea"));
					grups.setModalidad(eElement.getAttribute("modalidad"));
					grups.setNombre(eElement.getAttribute("nombre"));
					grups.setOficial(eElement.getAttribute("oficial"));
					grups.setTurno(eElement.getAttribute("turno"));
					grups.setTutor_ppal(eElement.getAttribute("tutor_ppal"));
					grups.setTutor_sec(eElement.getAttribute("tutor_sec"));
					
					arrayListGrups.add(grups);	
				}
				label_1.setText(""+arrayListGrups.size());
				
			}else if(file.getName().indexOf("imexalum.xml")!=-1)
			{
				label_10.setText(fechaExp);
				NodeList nList = doc.getElementsByTagName("alumno");
			
				for (int temp = 0; temp < nList.getLength(); temp++) {
					Imexalum alumno =new Imexalum();
					Node nNode = nList.item(temp);
					Element eElement = (Element) nNode;
					
					alumno.setAmpa(eElement.getAttribute("ampa"));
					alumno.setApellido1(eElement.getAttribute("apellido1"));
					alumno.setApellido2(eElement.getAttribute("apellido2"));
					alumno.setBanco(eElement.getAttribute("banco"));
					alumno.setCod_postal(eElement.getAttribute("cod_postal"));
					alumno.setCuenta(eElement.getAttribute("cuenta"));
					alumno.setCurso(eElement.getAttribute("curso"));
					alumno.setDictamen(eElement.getAttribute("dictamen"));
					alumno.setDigito_control(eElement.getAttribute("digito_control"));
					alumno.setDocumento(eElement.getAttribute("documento"));
					alumno.setDomicilio(eElement.getAttribute("domicilio"));
					alumno.setEmail1(eElement.getAttribute("email1"));
					alumno.setEmail2(eElement.getAttribute("email2"));
					alumno.setEnsenanza(eElement.getAttribute("ensenanza"));
					alumno.setEscalera(eElement.getAttribute("escalera"));
					alumno.setEstado_maricula(eElement.getAttribute("estado_matricula"));
					alumno.setExpediente(eElement.getAttribute("expediente"));
					alumno.setFecha_ingreso_centro(eElement.getAttribute("fecha_ingreso_centro"));
					alumno.setFecha_matricula(eElement.getAttribute("fecha_matricula"));
					alumno.setFecha_nac(eElement.getAttribute("fecha_nac"));
					alumno.setFecha_resolucion(eElement.getAttribute("fecha_resolucion"));
					alumno.setGrupo(eElement.getAttribute("grupo"));
					alumno.setIban(eElement.getAttribute("iban"));
					alumno.setInformado_posib(eElement.getAttribute("informado_posib"));
					alumno.setInforme_psicoped(eElement.getAttribute("informe_psicoded"));
					alumno.setLetra(eElement.getAttribute("letra"));
					alumno.setLibro_escolaridad(eElement.getAttribute("libro_escolaridad"));
					alumno.setLinea(eElement.getAttribute("linea"));
					alumno.setLocalidad(eElement.getAttribute("localidad"));
					alumno.setModalidad(eElement.getAttribute("modalidad"));
					alumno.setMunicipio(eElement.getAttribute("municipio"));
					alumno.setMunicipio_nac(eElement.getAttribute("municipio_nac"));
					alumno.setMunicipio_nac_ext(eElement.getAttribute("municipio_nac_ext"));
					alumno.setNacionalidad(eElement.getAttribute("nacionalidad"));
					alumno.setNIA(eElement.getAttribute("NIA"));
					alumno.setNombre(eElement.getAttribute("nombre"));
					alumno.setNum_repeticion(eElement.getAttribute("num_repeticion"));
					alumno.setNumero(eElement.getAttribute("numero"));
					alumno.setObservaciones(eElement.getAttribute("observaciones"));
					alumno.setPais_nac(eElement.getAttribute("pais_nac"));
					alumno.setPiso(eElement.getAttribute("piso"));
					alumno.setProvincia(eElement.getAttribute("provincia"));
					alumno.setProvincia_nac(eElement.getAttribute("provincia_nac"));
					alumno.setPuerta(eElement.getAttribute("puerta"));
					alumno.setRepite(eElement.getAttribute("repite"));
					alumno.setSeguro(eElement.getAttribute("seguro"));
					alumno.setSexo(eElement.getAttribute("sexo"));
					alumno.setSip(eElement.getAttribute("sip"));
					alumno.setSucursal(eElement.getAttribute("sucursal"));
					alumno.setTelefono1(eElement.getAttribute("telefono1"));
					alumno.setTelefono2(eElement.getAttribute("telefono2"));
					alumno.setTelefono3(eElement.getAttribute("telefono3"));
					alumno.setTipo_doc(eElement.getAttribute("tipo_doc"));
					alumno.setTipo_matricula(eElement.getAttribute("tipo_matricula"));
					alumno.setTipo_via(eElement.getAttribute("tipo_via"));
					alumno.setTurno(eElement.getAttribute("turno"));
					arrayListImexalums.add(alumno);
				}
			label_2.setText(""+arrayListImexalums.size());
			}else if(file.getName().indexOf("pas.xml")!=-1){
				label_11.setText(fechaExp);
				
				NodeList nList = doc.getElementsByTagName("no_docente");
				
				for (int temp = 0; temp < nList.getLength(); temp++) {
					Pass pass=new Pass();
					Node nNode = nList.item(temp);
					Element eElement = (Element) nNode;
					
					pass.setApellido1(eElement.getAttribute("apellido1"));
					pass.setApellido2(eElement.getAttribute("apellido2"));
					pass.setBanco(eElement.getAttribute("banco"));
					pass.setClaustro(eElement.getAttribute("claustro"));
					pass.setCod_postal(eElement.getAttribute("cod_postal"));
					pass.setCuenta(eElement.getAttribute("cuenta"));
					pass.setDigito_control(eElement.getAttribute("digito_control"));
					pass.setDocumento(eElement.getAttribute("documento"));
					pass.setDomicilio(eElement.getAttribute("domicilio"));
					pass.setEscalera(eElement.getAttribute("escalera"));
					pass.setFecha_ingreso(eElement.getAttribute("fecha_ingreso"));
					pass.setFecha_nac(eElement.getAttribute("fecha_nac"));
					pass.setFuncion(eElement.getAttribute("funcion"));
					pass.setHoras_dedicadas(eElement.getAttribute("horas_dedicadas"));
					pass.setHoras_puesto(eElement.getAttribute("horas_puesto"));
					pass.setIban(eElement.getAttribute("iban"));
					pass.setLetra(eElement.getAttribute("letra"));
					pass.setLocalidad(eElement.getAttribute("localidad"));
					pass.setMunicipio(eElement.getAttribute("municipio"));
					pass.setNombre(eElement.getAttribute("nombre"));
					pass.setNumero(eElement.getAttribute("numero"));
					pass.setOrganisco(eElement.getAttribute("organismo"));
					pass.setPiso(eElement.getAttribute("piso"));
					pass.setProvincia(eElement.getAttribute("provincia"));
					pass.setPuerta(eElement.getAttribute("puerta"));
					pass.setSexo(eElement.getAttribute("sexo"));
					pass.setSucursal(eElement.getAttribute("sucursal"));
					pass.setTelefono1(eElement.getAttribute("telefono1"));
					pass.setTelefono2(eElement.getAttribute("telefono2"));
					pass.setTelefono3(eElement.getAttribute("telefono3"));
					pass.setTipo_doc(eElement.getAttribute("tipo_doc"));
					pass.setTipo_trabajador(eElement.getAttribute("tipo_trabajador"));
					arrayListPas.add(pass);
				}
			
				label.setText(""+arrayListPas.size());
			}else if(file.getName().indexOf("profes.xml")!=-1){
				label_12.setText(fechaExp);
				
				
				NodeList nList = doc.getElementsByTagName("docente");
				
				for (int temp = 0; temp < nList.getLength(); temp++) {
				Profes prof=new Profes();
					Node nNode = nList.item(temp);
					Element eElement = (Element) nNode;
					
					prof.setApellido1( eElement.getAttribute("apellido1"));
					prof.setApellido2( eElement.getAttribute("apellido2"));
					prof.setBanco( eElement.getAttribute("banco"));
					prof.setCategoria( eElement.getAttribute("categoria"));
					prof.setClaustro( eElement.getAttribute("claustro"));
					prof.setCod_postal( eElement.getAttribute("cod_postal"));
					prof.setDigito_control( eElement.getAttribute("digito_control"));
					prof.setDocumento( eElement.getAttribute("documento"));
					prof.setDomicilio( eElement.getAttribute("domicilio"));
					prof.setEmail1( eElement.getAttribute("email1"));
					prof.setEmail2( eElement.getAttribute("email2"));
					prof.setEnsenanza( eElement.getAttribute("ensenanza"));
					prof.setEscalera( eElement.getAttribute("escalera"));
					prof.setFecha_antiguedad( eElement.getAttribute("fecha_antiguedad"));
					prof.setFecha_ingreso( eElement.getAttribute("fecha_ingreso"));
					prof.setFecha_nac( eElement.getAttribute("fecha_nac"));
					prof.setHoras_computo_mens( eElement.getAttribute("horas_computo_mens"));
					prof.setHoras_dedicadas( eElement.getAttribute("horas_dedicadas"));
					prof.setHoras_puesto( eElement.getAttribute("horas_puesto"));
					prof.setHoras_reparacion( eElement.getAttribute("horas_preparacion"));
					prof.setLenguaje( eElement.getAttribute("lenguaje"));
					prof.setLetra( eElement.getAttribute("letra"));
					prof.setIban(eElement.getAttribute("iban"));
					prof.setLocalidad( eElement.getAttribute("localidad"));
					prof.setMotivo( eElement.getAttribute("motivo"));
					prof.setMunicipio( eElement.getAttribute("municipio"));
					prof.setNivel_valenciano( eElement.getAttribute("nivel_valenciano"));
					prof.setNombre( eElement.getAttribute("nombre"));
					prof.setNumero( eElement.getAttribute("numero"));
					prof.setOrganismo( eElement.getAttribute("organismo"));
					prof.setPiso( eElement.getAttribute("piso"));
					prof.setProviencia( eElement.getAttribute("provincia"));
					prof.setPuerta( eElement.getAttribute("puerta"));
					prof.setSexo( eElement.getAttribute("sexo"));
					prof.setCuenta(eElement.getAttribute("cuenta"));
					prof.setSucursal( eElement.getAttribute("sucursal"));
					prof.setTelefono1( eElement.getAttribute("telefono1"));
					prof.setTelefono2( eElement.getAttribute("telefono2"));
					prof.setTelefono3( eElement.getAttribute("telefono3"));
					prof.setTipo( eElement.getAttribute("tipo"));
					prof.setTipo_doc( eElement.getAttribute("tipo_doc"));
					prof.setTipo_docente( eElement.getAttribute("tipo_docente"));
					prof.setTitular_sustituido( eElement.getAttribute("titular_sustituido"));
					arrayListProfes.add(prof);
					
				}
				label_3.setText(""+arrayListProfes.size());
			}

//			NodeList nList = doc.getElementsByTagName("centro");
			
			progressBar.setString("Cargado!");
			btnNewButton.setEnabled(true);
			progressBar.setIndeterminate(false);
			progressBar.setString("Esperando Archivos.");
			progressBar.setValue(0);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void loadCSV(){
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				  progressBarMain.setIndeterminate(true);
				  progressBarMain.setString("Guardando Información...");
					
				try {
					CsvReader csvReader=new CsvReader(new FileReader(fileCsv));
					try {
					
						csvReader.readHeaders();
						while (csvReader.readRecord())
						{
							String nia = csvReader.get("NIA");
							String alta = csvReader.get("F. Alta");
							String baja = csvReader.get("F. Baja");
							String pago = csvReader.get("Tipo pago");
							
							if(principal.getBaseDeDatos().verificarSiExiste(nia))
							{	
								principal.getBaseDeDatos().updateAlumnoAS(nia, "si");
								SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyy");
								
								try {
									principal.getBaseDeDatos().addFechas(new Date(dateFormat.parse(alta).getTime()),new Date(dateFormat.parse(baja).getTime()), nia,pago,"3","si","Si","Si","No");
//									principal.getBaseDeDatos().addPago(nia,pago);
									
//									principal.getBaseDeDatos().reme
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				progressBarMain.setIndeterminate(false);
				progressBarMain.setString("Completado!");
				try {
					Thread.sleep(2000);
					dispose();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				btnNewButton_1.setEnabled(false);
			}
		}).start();
	}
	private File fileCsv;
	private JTextField textFieldDias;
	private JTextField textFieldPrecioMenu;
	private JTextField textFieldComison;
	private JTextField textFieldJuevesComedor;
	public ImporDataITACA(final ComedorGUI principal) {
		super("", false, true, false, true);
		setTitle("Importar Datos ITACA");
		setBounds(10,20,817, 546);
		
		this.principal = principal;
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new TitledBorder(null, "Archivos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
//		panel.setOpaque(false);
//		panel_1.setOpaque(false);
//		panel_2.setOpaque(false);
//		panel_3.setOpaque(false);
//		panel_4.setOpaque(false);
		panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informacion General", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informacion Individual", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBackground(Color.WHITE);
		
		JLabel lblFamiliars = new JLabel("familiars - >");
		
		JLabel lblGrups = new JLabel("grups    - >");
		
		JLabel lblPas = new JLabel("pas - >");
		
		JLabel lblImexalum = new JLabel("imexalum - >");
		
		JLabel lblProfes = new JLabel("profes - >");
		
		JLabel lblNewLabel = new JLabel("Fecha de Exportacion:");
		
		JLabel lblFechaDeExportacion = new JLabel("Fecha de Exportacion:");
		
		JLabel lblFechaDeExportacion_1 = new JLabel("Fecha de Exportacion:");
		
		JLabel lblFechaDeExportacion_2 = new JLabel("Fecha de Exportacion:");
		
		JLabel lblFechaDeExportacion_3 = new JLabel("Fecha de Exportacion:");
		
	label_8 = new JLabel("00/00/000");
		label_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		label_9 = new JLabel("00/00/000");
		label_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		label_10 = new JLabel("00/00/000");
		label_10.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		label_11 = new JLabel("00/00/000");
		label_11.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		label_12 = new JLabel("00/00/000");
		label_12.setFont(new Font("Tahoma", Font.BOLD, 11));
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFamiliars)
						.addComponent(lblPas, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
						.addComponent(lblImexalum, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 107, Short.MAX_VALUE)
						.addComponent(lblGrups, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
						.addComponent(lblProfes, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblFechaDeExportacion_3, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblFechaDeExportacion, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblFechaDeExportacion_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblFechaDeExportacion_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(label_8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(label_9, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(label_12, Alignment.LEADING)
						.addComponent(label_10, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
						.addComponent(label_11, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_5.createSequentialGroup()
							.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(label_8))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblFechaDeExportacion)
								.addComponent(label_9))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblFechaDeExportacion_1)
								.addComponent(label_10))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblFechaDeExportacion_2)
								.addComponent(label_11))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblFechaDeExportacion_3)
								.addComponent(label_12)))
						.addGroup(gl_panel_5.createSequentialGroup()
							.addComponent(lblFamiliars)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblGrups)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblImexalum)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblPas)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblProfes)))
					.addContainerGap(38, Short.MAX_VALUE))
		);
		panel_5.setLayout(gl_panel_5);
		
		JPanel panel_7 = new JPanel();
		panel_7.setOpaque(false);
		panel_7.setBackground(Color.WHITE);
		panel_7.setBorder(new TitledBorder(null, "Datos de Curso", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblNotaParaActualizar = new JLabel("<html><body><b><font color='red'>Nota:</font></b>   Para actualizar colocar el curso a Actualizar</body></html>");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 782, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(10)
									.addComponent(lblNotaParaActualizar))
								.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
								.addComponent(panel_5, GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
								.addComponent(panel_7, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 381, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(panel_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNotaParaActualizar)
							.addGap(14)
							.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_5, 0, 0, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel_1 = new JLabel("Precio del Menu:");
		
		textFieldDias = new JTextField();
		textFieldDias.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textFieldDias.setColumns(10);
		
		textFieldPrecioMenu = new JTextField();
		textFieldPrecioMenu.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textFieldPrecioMenu.setColumns(10);
		
		JLabel lblDias = new JLabel("Dias:");
		
		JLabel lblComisionDeBanco = new JLabel("Comision de Banco:");
		
		textFieldComison = new JTextField();
		textFieldComison.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textFieldComison.setColumns(10);
		
		PromptSupport.setPrompt("Ejemplo: 245", textFieldDias);
		
		PromptSupport.setPrompt("Ejemplo: 3.55", textFieldPrecioMenu);
		PromptSupport.setPrompt("Ejemplo: 1", textFieldComison);
		
		JLabel lblCursoAC = new JLabel("Curso a Cargar:");
		
		textField = new JTextField();
		PromptSupport.setPrompt("Ejemplo: 2016", textField);
		textField.setBorder(BorderFactory.createTitledBorder(""));
		textField.setColumns(10);
		
		JLabel lblJuevesDe = new JLabel("Jueves de Comedor:");
		
		textFieldJuevesComedor = new JTextField();
		textFieldJuevesComedor.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textFieldJuevesComedor.setColumns(10);
		GroupLayout gl_panel_7 = new GroupLayout(panel_7);
		gl_panel_7.setHorizontalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_7.createSequentialGroup()
					.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_7.createSequentialGroup()
							.addGap(65)
							.addComponent(lblDias))
						.addGroup(gl_panel_7.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblCursoAC)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textFieldDias)
						.addComponent(textField, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_7.createSequentialGroup()
							.addComponent(lblComisionDeBanco)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldComison, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblJuevesDe)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldJuevesComedor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_7.createSequentialGroup()
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textFieldPrecioMenu, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_7.setVerticalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_7.createSequentialGroup()
					.addGap(14)
					.addGroup(gl_panel_7.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCursoAC)
						.addComponent(lblComisionDeBanco)
						.addComponent(textFieldComison, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblJuevesDe)
						.addComponent(textFieldJuevesComedor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_7.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(textFieldDias, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDias)
						.addComponent(textFieldPrecioMenu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_7.setLayout(gl_panel_7);
		
		JLabel lblNroDeNoDocentes = new JLabel("Nro. de No Docentes:");
		
		label = new JLabel("0");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblNroDeGrupos = new JLabel("Nro. de Grupos:");
		
		label_1 = new JLabel("0");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblNroDeAlumnos = new JLabel("Nro. de Alumnos:");
		
		label_2 = new JLabel("0");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblNroDeDocentes = new JLabel("Nro. de Docentes:");
		
		label_3 = new JLabel("0");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(lblNroDeAlumnos, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(lblNroDeNoDocentes)
							.addGap(23)))
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(label_2)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(22)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(lblNroDeDocentes)
							.addGap(18))
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(lblNroDeGrupos, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
							.addGap(3)))
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(label_1, GroupLayout.DEFAULT_SIZE, 10, Short.MAX_VALUE)
							.addGap(7))
						.addComponent(label_3, GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE))
					.addGap(22))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNroDeNoDocentes)
						.addComponent(label_1)
						.addComponent(lblNroDeGrupos)
						.addComponent(label))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNroDeAlumnos)
						.addComponent(label_2)
						.addComponent(lblNroDeDocentes)
						.addComponent(label_3))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		panel_4.setLayout(gl_panel_4);
		
		JButton btnNewButton_2 = new JButton("Cancelar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(ImporDataITACA.class.getResource("/resource/A1-close.png")));
		
		
		final JButton btnNewButton_1 = new JButton("Aceptar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(chckbxNewCheckBox_1.isSelected()){
					btnNewButton_1.setEnabled(false);
					  loadCSV();
//					  System.out.println("asa");
					
					
					
				}else{
				
				if(textField.getText().length()==0){
					JOptionPane.showMessageDialog(principal, "Debe agregar un Curso","Año Escolar",JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!principal.getBaseDeDatos().verificarCurso(getCurso())){
//					JOptionPane.showm
//				}
					if(textFieldDias.getText().length()==0){
						JOptionPane.showMessageDialog(principal, "Debe especificar los Dias del Curso","Dias",JOptionPane.WARNING_MESSAGE);
						return;
					}
					if(textFieldComison.getText().length()==0){
						JOptionPane.showMessageDialog(principal, "Debe especificar la Comision del Banco","Comision",JOptionPane.WARNING_MESSAGE);
						return;
					}
					if(textFieldPrecioMenu.getText().length()==0){
						JOptionPane.showMessageDialog(principal, "Debe especificar el Precio del Menu","Precio del Menu",JOptionPane.WARNING_MESSAGE);
						return;
					}
					if(textFieldJuevesComedor.getText().length()==0){
						JOptionPane.showMessageDialog(principal, "Debe especificar los Jueves de Comedor","Jueves Comedor",JOptionPane.WARNING_MESSAGE);
						return;
					}
				
				
					boolean n=principal.getBaseDeDatos().crearCurso(getCurso(),textFieldDias.getText(),textFieldComison.getText(),textFieldPrecioMenu.getText(),textFieldJuevesComedor.getText());
					if(n){
						principal.updateCursos();
					}
				}
//				if(principal.getBaseDeDatos().verificarCurso(getCurso())){
//					JOptionPane.showm
//				}
//				boolean n=principal.getBaseDeDatos().setCentro(codigo,denominacion,getCurso());
//				if(!n){
//					JOptionPane.showMessageDialog(principal, "Curso ya agregado.","Curso ya Cargado",JOptionPane.WARNING_MESSAGE);
//					return;
//				}
				principal.updateDatosCentro();
				new Thread(new Runnable() {
					@Override
					public void run() {
						btnNewButton_1.setEnabled(false);
						progressBarMain.setString("Guardando Información...");
						try{
							if(arrayListFamiliars.size()>0)
							for(int index=0;index<arrayListFamiliars.size();index++){
								Familiars a = arrayListFamiliars.get(index);
								principal.getBaseDeDatos().eliminarFamiliares(a.getAlumno());
							}
						  }catch(Exception e){
						}
						for(int index=0;index<arrayListFamiliars.size();index++){
							Familiars a = arrayListFamiliars.get(index);
							principal.getBaseDeDatos().registrarFamiliar(a);
						}
						progressBarMain.setValue(1);
						for(int index=0;index<arrayListGrups.size();index++){
							Grups a = arrayListGrups.get(index);
							principal.getBaseDeDatos().registrarGroup(a,getCurso());
						}
						progressBarMain.setValue(2);
						for(int index=0;index<arrayListImexalums.size();index++){
							Imexalum a = arrayListImexalums.get(index);
							principal.getBaseDeDatos().registrarAlumnos(a,getCurso());
						}
						progressBarMain.setValue(3);
						for(int index=0;index<arrayListPas.size();index++){
							Pass a = arrayListPas.get(index);
								
							principal.getBaseDeDatos().registrarNoDocente(a,getCurso());
						}
						progressBarMain.setValue(4);
						for(int index=0;index<arrayListProfes.size();index++){
							Profes a= arrayListProfes.get(index);
							principal.getBaseDeDatos().registrarProf(a,getCurso());
						}
						progressBarMain.setValue(5);
//						for(int index=0;index<arrayListImexalums.size();index++){
//							Imexalum a= arrayListImexalums.get(index);
//							principal.getBaseDeDatos().registrarAlumno(a);
//						}
						
						
						progressBarMain.setString("Completado!");
						
						try {
							Thread.sleep(2300);
							principal.repaintData();
							dispose();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
//						principal.getBaseDeDatos().setCentro(codigo,denominacion,getCurso());
//						principal.updateDatosCentro();
						
						principal.updateGroups();
						
					   }
				     }).start();
				}
				
			}
		});
//		setIcon(true);
		setFrameIcon(new ImageIcon(getClass().getResource("/resource/A1-restart.png")));
		btnNewButton_1.setIcon(new ImageIcon(ImporDataITACA.class.getResource("/resource/A1-init.png")));
		
		progressBarMain = new JProgressBar(0,5);
		progressBarMain.setStringPainted(true);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addComponent(progressBarMain, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_3.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnNewButton_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_panel_3.createParallelGroup(Alignment.BASELINE)
							.addComponent(progressBarMain, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
							.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)))
					.addContainerGap())
		);
		panel_3.setLayout(gl_panel_3);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		btnNewButton = new JButton("Buscar Archivos");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				
				JFileChooser chooser=new JFileChooser();
				int opt=chooser.showOpenDialog(principal);
				
				
				
				if(opt == JFileChooser.APPROVE_OPTION){
					
					File selected = chooser.getSelectedFile();
					for(int index=0;index<list.getModel().getSize();index++){
						if(list.getModel().getElementAt(index).toString().equalsIgnoreCase(selected.getName())){
							JOptionPane.showMessageDialog(principal, "<html><body>Archivo: <b>"+selected.getName()+"</b><br>Ya ha sido agregado.</body></html>","Agregado",JOptionPane.WARNING_MESSAGE);
							return;
						}
					}
					list.addItem(selected.getName());
					
					if(chckbxNewCheckBox_1.isSelected()){
						  fileCsv=selected;
						
					}else{
					progressBar.setString("Extrayendo Informacion...");
					progressBar.setIndeterminate(true);
					btnNewButton.setEnabled(false);
					
					loadFileXML(selected);
					}
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(ImporDataITACA.class.getResource("/resource/14.png")));
		panel_2.add(btnNewButton);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		panel_2.add(panel_6);
		panel_6.setLayout(new GridLayout(1, 0, 0, 0));
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Archivo XML");
		chckbxNewCheckBox.setSelected(true);
		chckbxNewCheckBox.setBackground(Color.WHITE);
		panel_6.add(chckbxNewCheckBox);
		
		chckbxNewCheckBox_1 = new JCheckBox("Archivo CSV");
		chckbxNewCheckBox_1.setBackground(Color.WHITE);
		PromptSupport.setPrompt("Ejemplo:20", textFieldJuevesComedor);
		panel_6.add(chckbxNewCheckBox_1);
		ButtonGroup buttonGroup=new ButtonGroup();
		buttonGroup.add(chckbxNewCheckBox);
		buttonGroup.add(chckbxNewCheckBox_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);
		
		list = new JKList();
		scrollPane.setViewportView(list);
		
		progressBar = new JProgressBar();
		progressBar.setString("Esperando archivos.");
		progressBar.setStringPainted(true);
		panel_1.add(progressBar, BorderLayout.SOUTH);
		panel.setLayout(gl_panel);		
	}
}