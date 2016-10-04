package comedor.bd;

import itaca.Familiars;
import itaca.Grups;
import itaca.Imexalum;
import itaca.Pass;
import itaca.Profes;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Properties;

import javax.imageio.ImageIO;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

import org.postgresql.util.Base64;

import com.leyer.JKComboBox;
import com.leyer.JKDataBase;
import com.leyer.JKTable;

import comedor.ActualizarCentro;
import comedor.ActualizarCurso;
import comedor.AsignarUsuario;
import comedor.ComedorGUI;
import comedor.ConfigurarEmail;
import comedor.DialogoIncidencias;
import comedor.DialogoConexion;
import comedor.NuevoPeriodoComedor;
import comedor.PanelAgregarDiasProfesor;
import comedor.PanelFamiliar;
import comedor.RegistrarAlumno;
import comedor.RegistrarNoDocentes;
import comedor.RegistrarProfesores;
import comedor.pdfs.PDF_Asistentes;
import comedor.pdfs.PDF_Asistentes_Ticked;
import comedor.pdfs.PDF_BalancesEconomicos;
import comedor.pdfs.PDF_Faltas;
import comedor.pdfs.PDF_Faltas_Alumno;
import comedor.pdfs.PDF_Incidencias_Alumno;
import comedor.pdfs.PDF_Listado_AmbosDias;
import comedor.pdfs.PDF_Listado_Dias;
import comedor.pdfs.PDF_Listado_RangoDias;
import comedor.pdfs.PDF_Recibos1_Profesores;
import comedor.pdfs.PDF_RecibosPendientes;
import comedor.pdfs.Persona;

public class BD extends JKDataBase
{
	private ComedorGUI principal;
	private boolean active = false;

	public boolean isActive() 
	{
		return active;
	}

	public void setActive(boolean active) 
	{
		this.active = active;
	}

	public BD(ComedorGUI principal,String user,String password, String ip, DialogoConexion conexion) 
	{
		super(JKDataBase.POSTGRESQL, "//" + ip + "/comedorbd", user, password);

		try 
		{
			this.principal = principal;

			if(openConnection())
				active = true;
			else
			{
				if(conexion != null)
					conexion.fail();

				JOptionPane.showMessageDialog(principal, "Conexion Fallida!", "Mensaje", JOptionPane.ERROR_MESSAGE);
				active = false;
			}
		} 
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(principal, e.getMessage(),"Mensaje",JOptionPane.ERROR_MESSAGE);
			active=false;
		}
	}

	public void error(Exception e)
	{
		e.printStackTrace();
		JOptionPane.showMessageDialog(principal, e.getMessage(),e.getLocalizedMessage(),JOptionPane.ERROR_MESSAGE);
	}

	public boolean setCentro(String codigo, String denominacion, String curso)
	{
		try
		{
			ResultSet x= executeQuery("SELECT * FROM centro WHERE codigo_centro = '" + codigo + "' AND curso = '" + curso + "'");

			while(x.next())
				return false;

			boolean b=executeUpdate("INSERT INTO centro(codigo_centro, denominacion, curso) VALUES('" + codigo + "', '" + denominacion + "', '" + curso + "');");
			return b;

		}
		catch(Exception e)
		{
			error(e);
		}
		return false;
	}

	public String getDenominacion()
	{
		try
		{
			ResultSet x = executeQuery("SELECT codigo_centro, denominacion, telefono, fax, domicilio,id_centro FROM centro;");

			while(x.next())
				return x.getString(2);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "";
	}

	public void getDatosCentro(JLabel labelDatosCentro) 
	{
		try
		{
			String codigoCentro = "00000";
			String denominacion = "Sin Definir";
			String domicilio = "Sin Definir";
			String telefono = "Sin Definir";
			String fax = "Sin Definir";

			ResultSet x = executeQuery("SELECT codigo_centro, denominacion, telefono, fax, domicilio,id_centro FROM centro;");

			while(x.next())
			{
				codigoCentro = x.getString(1);
				denominacion = x.getString(2);
				domicilio = x.getString(5);
				telefono=x.getString(3);
				fax = x.getString(4);
			}

			labelDatosCentro.setText("<html>Codigo de Centro: <font color='blue'>" +
					"<b>" + codigoCentro + "</b></font><br>Denomincación:<b>" + denominacion + "</b>" +
					"<BR>Domicilio: <b>" + domicilio + "</b><br>Telefono: <b>" + telefono + "</b>" +
					"<br>Fax:  <b>" + fax + "</b></html>");
		}
		catch(Exception e)
		{
			error(e);
		}
	}

	public boolean registrarGroup(Grups a, String curso) 
	{
		try 
		{
			ResultSet x = executeQuery("SELECT *  FROM grups WHERE codigo = '" + a.getCodigo() + "' AND curso = '" + curso + "'");

			while(x.next())
				executeUpdate("DELETE FROM grups WHERE codigo = '" + a.getCodigo() + "' and curso = '" + curso + "'");

			return executeUpdate("INSERT INTO grups(codigo, nombre, ensenanza, linea, turno, modalidad,  aula, capacidad, tutor_ppal, tutor_sec, oficial,curso)" 
					+ "VALUES ('" + a.getCodigo() + "', '" + a.getNombre() + "', '" + a.getEnsenanza() + "', '" + a.getLinea() + "', '" + a.getTurno() + "',"
					+ " '" + a.getModalidad() + "', '" + a.getAula() + "', '" + a.getCapacidad() + "', '" + a.getTutor_ppal() + "'," 
					+ " '" + a.getTutor_sec() + "', '" + a.getOficial() + "','" + curso + "');");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public void eliminarFamiliares(String c)
	{
		try
		{
			String curso=principal.getBaseDeDatos().getCursoActual();
			executeUpdate("DELETE FROM familiars WHERE alumno='" + c + "' AND curso='" + curso + "'");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void registrarFamiliar(Familiars a) 
	{
		try
		{
			String curso = principal.getBaseDeDatos().getCursoActual();
			executeUpdate("INSERT INTO familiars(alumno, nombre, apellido1, tipo_doc, documento, es_tutor,telefono,direccion,parentesco,apellido2,curso) " 
					+ "VALUES ('"+a.getAlumno()+"', '"+a.getNombre()+"', '" + a.getApellido1() + "', '" + a.getTipo_doc() + "', '" + a.getDocumento() 
					+ "', '" + a.getEs_tutor() + "','sin informacion','sin informacion','" + a.getParentesco() + "','" + a.getApellido2() + "','" + curso + "');");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public boolean registrarNoDocente(Pass a, String curso) 
	{
		try
		{
			ResultSet x = executeQuery("SELECT * FROM pas WHERE documento = '" + a.getDocumento() + "' AND curso = '" + curso + "'");

			while(x.next())
			{
				String sql="UPDATE pas " 
						+ "SET nombre = '" + a.getNombre().toUpperCase() + "', apellido1 = '" + a.getApellido1().toUpperCase() + "', tipo_doc = '" + a.getTipo_doc() 
						+ "', sexo = '" + a.getSexo() + "', fecha_ingreso = '" + a.getFecha_ingreso() + "', domicilio = '" + a.getDomicilio() + "', numero='" + a.getNumero() 
						+ "', puerta = '" + a.getPuerta() + "', escalera = '" + a.getEscalera() + "', " + "letra = '" + a.getLetra() + "', piso = '" + a.getPiso() 
						+ "', provincia = '" + a.getProvincia() + "', municipio = '" + a.getMunicipio() + "', localidad = '" + a.getLocalidad() + "', cod_postal = '" + a.getCod_postal()
						+ "', telefono1 = '" + a.getTelefono1() + "', telefono2 = '" + a.getTelefono2() + "', telefono3 = '" + a.getTelefono3() + "', horas_puesto = '" + a.getHoras_puesto()
						+ "', horas_dedicadas = '" + a.getHoras_dedicadas() + "', funcion = '" + a.getFuncion() + "', tipo_trabajador = '" + a.getTipo_trabajador() + "', fecha_nac = '" + a.getFecha_nac() 
						+ "', organismo='" + a.getOrganisco() + "', banco='" + a.getBanco() + "', sucursal = '" + a.getSucursal() + "', digito_control = '" + a.getDigito_control()
						+ "', cuenta = '" + a.getCuenta() + "', claustro='" + a.getClaustro() + "', iban='" + a.getIban() + "', apellido2 = '" + a.getApellido2().toUpperCase() + "' "
						+ "WHERE documento='" + a.getDocumento() + "' and curso='" + curso + "'";

				return 	executeUpdate(sql);
			}

			return executeUpdate("INSERT INTO pas(nombre, apellido1, tipo_doc, documento, sexo, fecha_ingreso, domicilio, numero, puerta, escalera, letra, piso, "
					+ "provincia, municipio, localidad, cod_postal, telefono1, telefono2, telefono3, horas_puesto, horas_dedicadas, funcion, tipo_trabajador, "
					+ "fecha_nac, organismo, banco, sucursal, digito_control, cuenta, claustro, iban,apellido2,curso)"
					+ "VALUES ('" + a.getNombre().toUpperCase() + "', '" + a.getApellido1().toUpperCase() + "', '" + a.getTipo_doc() + "', '" + a.getDocumento().toUpperCase()
					+ "', '" + a.getSexo() + "', '" + a.getFecha_ingreso()+"', '" + a.getDomicilio() + "', '" + a.getNumero() + "', '" + a.getPuerta() + "', '" + a.getEscalera()
					+ "', '" + a.getLetra() + "', '" + a.getPiso() + "', '" + a.getProvincia() + "', '" + a.getMunicipio() + "', '" + a.getLocalidad() + "', '" + a.getCod_postal()
					+ "', '" + a.getTelefono1() + "', '" + a.getTelefono2() + "', '" + a.getTelefono3() + "', '" + a.getHoras_puesto() + "', '" + a.getHoras_dedicadas() 
					+ "', '" + a.getFuncion() + "', '" + a.getTipo_trabajador() + "','" + a.getFecha_nac() + "','" + a.getOrganisco() + "','" + a.getBanco() 
					+ "','" + a.getSucursal() + "','" + a.getDigito_control() + "', '" + a.getCuenta() + "', '" + a.getClaustro() + "', '" + a.getIban() 
					+ "', '" + a.getApellido2().toUpperCase() + "','" + curso + "')");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public boolean registrarProf(Profes a, String curso) 
	{
		try
		{
			String red = "";

			if(a.getDocumento().length() == 10)
				red = "0" + a.getDocumento();
			else
				red = "00" + a.getDocumento();

			ResultSet x = executeQuery("SELECT * FROM profes WHERE documento = '" + a.getDocumento() + "' AND curso = '" + curso + "'");

			while(x.next())
			{
				String sql = "UPDATE profes " +
						"SET  nombre='" + a.getNombre().toUpperCase()+"', apellido1='"+a.getApellido1().toUpperCase()+"',apellido2='"+a.getApellido2().toUpperCase()+"', tipo_doc='"+a.getTipo_doc()+"', documento='"+a.getDocumento()+"', sexo='"+a.getSexo()+"', "+
						"fecha_ingreso='"+a.getFecha_ingreso()+"', domicilio='"+a.getDomicilio()+"', numero='"+a.getNumero()+"', puerta='"+a.getPuerta()+"', escalera='"+a.getEscalera()+"', "+
						"letra='"+a.getLetra()+"', piso='"+a.getPiso()+"', provincia='"+a.getProviencia()+"', municipio='"+a.getMunicipio()+"', localidad='"+a.getLocalidad()+"', cod_postal='"+a.getCod_postal()+"', "+
						"telefono1='"+a.getTelefono1()+"', telefono2='"+a.getTelefono2()+"', telefono3='"+a.getTelefono3()+"', horas_puesto='"+a.getHoras_puesto()+"', horas_dedicadas='"+a.getHoras_dedicadas()+"', "+
						"tipo='"+a.getTipo()+"', categoria='"+a.getCategoria()+"', email1='"+a.getEmail1()+"', email2='"+a.getEmail2()+"', fecha_nac='"+a.getFecha_nac()+"', lenguaje='"+a.getLenguaje()+"', "+
						"nivel_valenciano='"+a.getNivel_valenciano()+"', ensenanza='"+a.getEnsenanza()+"', organismo='"+a.getOrganismo()+"', banco='"+a.getBanco()+"', sucursal='"+a.getSucursal()+"', "+
						"digito_control='"+a.getDigito_control()+"', cuenta='"+a.getCuenta()+"', titular_surtituido='"+a.getTitular_surtituido()+"', motivo='"+a.getMotivo()+"', horas_preparacion='"+a.getHoras_reparacion()+"', "+
						"horas_computo_mens='"+a.getHoras_computo_mens()+"', fecha_antiguedad='"+a.getFecha_antiguedad()+"', claustro='"+a.getClaustro()+"', tipo_docente='"+a.getTipo_docente()+"', "+
						"iban='"+a.getIban()+"',alergias='"+a.getAlergias()+"', referencia_mandato='"+a.getReferencia()+"' WHERE documento='"+a.getDocumento().toUpperCase()+"' and curso='"+curso+"'";

				return executeUpdate(sql);
			}

			return executeUpdate("INSERT INTO profes(nombre, apellido1, tipo_doc, documento, sexo, fecha_ingreso, domicilio, numero, puerta, escalera, letra, "
					+ "piso, provincia, municipio, localidad, cod_postal, telefono1, telefono2, telefono3, horas_puesto, horas_dedicadas, tipo, categoria, "
					+ "email1, email2, fecha_nac, lenguaje, nivel_valenciano, ensenanza, organismo, banco, sucursal, digito_control, cuenta, titular_surtituido, "
					+ "motivo, horas_preparacion, horas_computo_mens, fecha_antiguedad, claustro, tipo_docente, iban,alergias,apellido2,curso,referencia_mandato)"
					+ " VALUES ('" + a.getNombre().toUpperCase() + "', '" + a.getApellido1().toUpperCase() + "', '" + a.getTipo_doc() + "', '" + a.getDocumento().toUpperCase()+"', '"
					+ a.getSexo()+"', " + "'" + a.getFecha_ingreso() + "', '" + a.getDomicilio() + "', '"+a.getNumero()+"', '"+a.getPuerta()+"', '"
					+ a .getEscalera()+"'," +
					" '"+a.getLetra()+"','"+a.getPiso()+"', '"+a.getProviencia()+"', '"+a.getMunicipio()+"'," +
					" '"+a.getLocalidad()+"', '"+a.getCod_postal()+"', '"+a.getTelefono1()+"', " +
					"'"+a.getTelefono2()+"', '"+a.getTelefono3()+"', '"+a.getHoras_puesto()+"', '"+a.getHoras_dedicadas()+"', '"+a.getTipo()+"', '"+a.getCategoria()+"'" +
					",'"+a.getEmail1()+"', '"+a.getEmail2()+"', '"+a.getFecha_nac()+"', '"+a.getLenguaje()+"', '"+a.getNivel_valenciano()+"', '"+a.getEnsenanza()+"'" +
					",'"+a.getOrganismo()+"', '"+a.getBanco()+"', '"+a.getSucursal()+"', '"+a.getDigito_control()+"', '"+a.getCuenta()+"', '"+a.getTitular_surtituido()+"'" +
					",'"+a.getMotivo()+"', '"+a.getHoras_reparacion()+"','"+a.getHoras_computo_mens()+"', '"+a.getFecha_antiguedad()+"'" +
					",'"+a.getClaustro()+"','"+a.getTipo_docente()+"', '"+a.getIban()+"','"+a.getAlergias()+"','"+a.getApellido2().toUpperCase()+"','"+curso+"','"+red+"');");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public void getDatosCentro(ActualizarCentro actualizarCentro) 
	{
		try
		{
			ResultSet x = executeQuery("SELECT codigo_centro, denominacion, telefono, fax, domicilio, id_centro FROM centro;");

			while(x.next())
			{
				actualizarCentro.setCodigo(x.getString(1));
				actualizarCentro.setDenominacion(x.getString(2));
				actualizarCentro.setTelefono(x.getString(3));
				actualizarCentro.setFax(x.getString(4));
				actualizarCentro.setDomicilio(x.getString(4));
			}
		}
		catch(Exception e)
		{
			error(e);
		}
	}

	public boolean actualizarDatosCentro(ActualizarCentro actualizarCentro) 
	{
		try{
			ResultSet x=executeQuery("select * from centro where codigo_centro='"+actualizarCentro.getCodigo()+"'");
			while(x.next()){

				return executeUpdate("UPDATE centro SET codigo_centro='"+actualizarCentro.getCodigo()+"', denominacion='"+actualizarCentro.getDenomicacion()+"'," +
						" telefono='"+actualizarCentro.getTelefono()+"', fax='"+actualizarCentro.getFex()+"', domicilio='"+actualizarCentro.getDemicilio()+"' ");
			}
			return executeUpdate("INSERT INTO centro(codigo_centro, denominacion, telefono, fax, domicilio) VALUES ('"+actualizarCentro.getCodigo()+"', '"+actualizarCentro.getDenomicacion()+"', '"+actualizarCentro.getTelefono()+"', '"+actualizarCentro.getFex()+"','"+actualizarCentro.getDemicilio()+"');");
		}catch(Exception e){
			error(e);
			return false;
		}
	}

	public void getGroups(JKComboBox comboBox) {
		// TODO Auto-generated method stub
		try{
			String curso=principal.getBaseDeDatos().getCursoActual();
			comboBox.removeAllItems();
			ResultSet x =executeQuery("select nombre from grups where curso='"+curso+"'");
			while(x.next()){
				comboBox.addItem(x.getString(1));
			}
		}catch(Exception e){
			error(e);
		}
	}
	public Object[] getGroups() {
		ArrayList<String> arrayList = new ArrayList<>();
		try{
			String curso=principal.getBaseDeDatos().getCursoActual();
			ResultSet x = executeQuery("select nombre from grups where curso='"+curso+"'");
			while(x.next()){
				arrayList.add(x.getString(1));


			}

		}catch(Exception e){
			error(e);
		}
		return arrayList.toArray();
	}
	public void getGroups(JKComboBox comboBox,ArrayList<String> arrayList) {
		// TODO Auto-generated method stub
		try{
			comboBox.removeAllItems();
			String curso=principal.getBaseDeDatos().getCursoActual();
			arrayList.clear();
			ResultSet x =executeQuery("select nombre,codigo from grups where curso='"+curso+"'");
			while(x.next()){
				comboBox.addItem(x.getString(1));
				arrayList.add(x.getString(2));
			}
		}catch(Exception e){
			//			error(e);
		}
	}
	public void getCodigosGroups(JKComboBox comboBox,ArrayList<String> arrayList) {
		// TODO Auto-generated method stub
		try{
			comboBox.removeAllItems();
			String curso=principal.getBaseDeDatos().getCursoActual();
			arrayList.clear();
			ResultSet x =executeQuery("select nombre,codigo from grups where curso='"+curso+"'");
			while(x.next()){
				comboBox.addItem(x.getString(2));
				arrayList.add(x.getString(2));
			}
		}catch(Exception e){
			//			error(e);
			e.printStackTrace();
		}
	}
	public void getRoles(JKComboBox comboBox, ArrayList<Object> arrayList) {
		// TODO Auto-generated method stub
		if(active){
			try{
				ResultSet x =executeQuery( "select * from roles");
				while(x.next()){
					comboBox.addItem(x.getString(2),new ImageIcon(getClass().getResource("/resource/a8.png")));
					arrayList.add(x.getInt(1));
				}
			}catch(Exception e){
				error(e);
			}
		}
	}
	public boolean  validarUsuario(String text, String password,Object id_rol) {

		try{
			ResultSet x=executeQuery("select usuario,clave FROM USUARIOS where usuario='"+text+"' " +
					"and clave='"+Base64.encodeBytes(password.getBytes())+"' and id_rol='"+id_rol+"'");

			while(x.next()){
				Toolkit.getDefaultToolkit().beep();
				if(id_rol.toString().equalsIgnoreCase("6")){
					principal.permiso(6);
				}else if(id_rol.toString().equalsIgnoreCase("5")){
					principal.permiso(5);
				}else{
					// :) admin
					principal.permiso(4);
				}
				return true;
			}
		}catch(Exception e){
			error(e);
		}
		return false;
	}
	public String getNroAlumnos(){
		try{
			String curso=principal.getBaseDeDatos().getCursoActual();
			ResultSet x =executeQuery("SELECT count (id_alumno) FROM alumnos where curso='"+curso+"'");
			while(x.next()){

				return ""+x.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "0";
	}

	public boolean newAlumno(String nia, String nombres, String apellidos,BufferedImage foto, String grupo, File fileFoto, String sex, String email, String telefono,
			//			,getTelefono1(),getTelefono2(),getfechaNacimiento(),getDocumento(),getSIP(),getExpediente(),getApellido2());

			String telefono2, String fechaNacimiento, String documento, String sip, String expediente, String apellido2, String tipo_doc, String curso,String referenciaMandato) {

		try{

			PreparedStatement p = preparedStatement("INSERT INTO alumnos( " +
					"" +
					"nombres, apellido1, nia, grupo, foto, codigo_barra,sexo,email,telefono1,apellido2," +
					"expediente,sip,telefono2,documento,fecha_nacimiento,tipo_doc,curso,referencia_mandato)  " +
					"VALUES (?,?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?);");

			p.setString(1, nombres.toUpperCase());
			p.setString(2, apellidos.toUpperCase());
			p.setString(3, nia);
			p.setString(4, grupo);

			if (fileFoto != null) {
				FileInputStream fileInputStream = new FileInputStream(fileFoto);
				p.setBinaryStream(5, fileInputStream, (int) fileFoto.length());
			} else {
				p.setBinaryStream(5, null, 0);
			}
			p.setString(6, nia);
			p.setString(7, sex);
			p.setString(8, email);
			p.setString(9,telefono);
			p.setString(10,apellido2.toUpperCase());
			p.setString(11,expediente);
			p.setString(12,sip);
			p.setString(13,telefono2);
			p.setString(14,documento.toUpperCase());
			p.setString(15,fechaNacimiento);
			p.setString(16,tipo_doc);
			p.setString(17,curso);
			p.setString(18,referenciaMandato);
			int k = p.executeUpdate();

			if(k==1){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;

	}

	public boolean actualizarDatosAlumno(String nia, String nombres, String apellidos,BufferedImage foto, String grupo, File fileFoto,String sex,String email, String telefono, String telefono2, String fechaNacimiento, String documento, String sip, String expediente, String apellido2, String tipo_doc, String curso, String personaDeContacto, String cta, String informe_medico, String medicamentos, String referenciaMandato) {
		int k=0;
		try{

			if(fileFoto!=null){
				String sql="";
				if(referenciaMandato!=null){
					sql="UPDATE alumnos SET nombres=?, apellido1=?, nia=?, grupo=?, " +
							"foto=?,  codigo_barra=?,sexo=?,email=? ,telefono1=?, apellido2=?, expediente=?, sip=?, fecha_nacimiento=?," +
							" telefono2=?,documento=?,tipo_doc=?,curso=?,persona_de_contacto=?,cta_bancaria=?,informe_medico=?,medicamentos=?,referencia_mandato=? WHERE nia='"+nia+"' and curso='"+curso+"'";
				}else{
					sql="UPDATE alumnos SET nombres=?, apellido1=?, nia=?, grupo=?, " +
							"foto=?,  codigo_barra=?,sexo=?,email=? ,telefono1=?, apellido2=?, expediente=?, sip=?, fecha_nacimiento=?," +
							" telefono2=?,documento=?,tipo_doc=?,curso=?,persona_de_contacto=?,cta_bancaria=?,informe_medico=?,medicamentos=? WHERE nia='"+nia+"' and curso='"+curso+"'";
				}
				PreparedStatement p = preparedStatement(sql);

				principal.getBaseDeDatos().insertFotos2(fileFoto,nia);

				p.setString(1, nombres.toUpperCase());
				p.setString(2, apellidos.toUpperCase());
				p.setString(3, nia);
				p.setString(4, grupo);

				//				if (fileFoto != null) {
				FileInputStream fileInputStream = new FileInputStream(fileFoto);
				p.setBinaryStream(5, fileInputStream, (int) fileFoto.length());
				//				
				p.setString(6, nia);
				p.setString(7, sex);
				p.setString(8, email);
				p.setString(9, telefono);
				p.setString(10, apellido2.toUpperCase());
				p.setString(11, expediente);
				p.setString(12, sip);
				p.setString(13, fechaNacimiento);
				p.setString(14, telefono2);
				p.setString(15, documento);
				p.setString(16, tipo_doc);
				p.setString(17, curso);
				p.setString(18, personaDeContacto);
				p.setString(19, cta);
				p.setString(20, informe_medico);
				p.setString(21,medicamentos);
				if(referenciaMandato!=null)
					p.setString(22,referenciaMandato);

				k = p.executeUpdate();
			}else{
				String sql="";
				if(referenciaMandato!=null){
					sql="UPDATE alumnos SET nombres=?, apellido1=?, nia=?, grupo=?, " +
							"codigo_barra=?,sexo=?,email=?, telefono1=?,apellido2=?, expediente=?, sip=?, fecha_nacimiento=?, telefono2=? ," +
							"documento=?, tipo_doc=? ,persona_de_contacto=?,cta_bancaria=?,informe_medico=?,medicamentos=?,referencia_mandato=? WHERE nia='"+nia+"' and curso='"+curso+"'";
				}else{
					sql="UPDATE alumnos SET nombres=?, apellido1=?, nia=?, grupo=?, " +
							"codigo_barra=?,sexo=?,email=?, telefono1=?,apellido2=?, expediente=?, sip=?, fecha_nacimiento=?, telefono2=? ," +
							"documento=?, tipo_doc=? ,persona_de_contacto=?,cta_bancaria=?,informe_medico=?,medicamentos=? WHERE nia='"+nia+"' " +
							"and curso='"+curso+"'";
				}
				PreparedStatement p = preparedStatement(sql);

				p.setString(1, nombres.toUpperCase());
				p.setString(2, apellidos.toUpperCase());
				p.setString(3, nia);
				p.setString(4, grupo);
				p.setString(5, nia);
				p.setString(6, sex);
				p.setString(7, email);
				p.setString(8, telefono);
				p.setString(9, apellido2.toUpperCase());
				p.setString(10, expediente);
				p.setString(11, sip);
				p.setString(12, fechaNacimiento);
				p.setString(13, telefono2);
				p.setString(14, documento);
				p.setString(15, tipo_doc);
				p.setString(16, personaDeContacto);
				p.setString(17, cta);
				p.setString(18, informe_medico);
				p.setString(19,medicamentos);
				if(referenciaMandato!=null){
					p.setString(20,referenciaMandato);
				}
				k=p.executeUpdate();
			}

			if(k==1){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;

	}
	//	public void newFamiliars(String nombrePadre, String apellidoPadre,
	//			String telefonoPadre, String direccionPadre, String nombreMadre,
	//			String apellidoMadre, String telefonoMadre2, String direccionMadre, String nia) {
	//		
	//		try {
	//			PreparedStatement s = preparedStatement("INSERT INTO" +
	//					" familiars( alumno, nombre, apellido, tipo_doc, " +
	//					"documento, es_tutor, telefono, direccion) VALUES ( ?, ?, ?, ?, ?,  ?, ?, ?);");
	//			s.setString(1, nia);
	//			s.setString(2, nombrePadre);
	//			s.setString(3, apellidoPadre);
	//			s.setString(4, "1");
	//			s.setString(5, "sin informacion");
	//			s.setString(6, "sin informacion");
	//			s.setString(7, telefonoPadre);
	//			s.setString(8, direccionPadre);
	//			int c=s.executeUpdate();
	//			
	//			if(c==1){
	//				
	//				PreparedStatement s1 = preparedStatement("INSERT INTO" +
	//						" familiars( alumno, nombre, apellido, tipo_doc, " +
	//						"documento, es_tutor, telefono, direccion) VALUES ( ?, ?, ?, ?, ?,  ?, ?, ?);");
	//				s1.setString(1, nia);
	//				s1.setString(2, nombreMadre);
	//				s1.setString(3, apellidoMadre);
	//				s1.setString(4, "1");
	//				s1.setString(5, "sin informacion");
	//				s1.setString(6, "sin informacion");
	//				s1.setString(7, telefonoMadre2);
	//				s1.setString(8, direccionMadre);
	//				s1.executeUpdate();
	//				
	//			}
	//			
	//		} catch (SQLException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//		
	//	
	//	}
	//	public void actualizarFamiliars(String nombrePadre, String apellidoPadre,
	//			String telefonoPadre, String direccionPadre, String nombreMadre,
	//			String apellidoMadre, String telefonoMadre2, String direccionMadre, String nia, String idPadre, String idMadre,) {
	//		
	//		try {
	//			{
	//				
	//				String sql ="UPDATE familiars "+
	//				   "SET alumno=?, nombre=?, apellido1=?, tipo_doc=?, documento=?, "+
	//				       "es_tutor=?, telefono=?, direccion=?,parentesco=?, apellido2=? "+
	//				 "WHERE alumno='"+nia+"' and id_familiars='"+idPadre+"';";
	//				
	//				
	//				PreparedStatement s = preparedStatement(sql);
	//				s.setString(1, nia);
	//				s.setString(2, nombrePadre);
	//				s.setString(3, apellidoPadre);
	//				s.setString(4, "1");
	//				s.setString(5, "sin informacion");
	//				s.setString(6, "sin informacion");
	//				s.setString(7, telefonoPadre);
	//				s.setString(8, direccionPadre);
	//				s.setString(9, parentesco);
	//				s.setString(10, apellido2);
	//				int c=s.executeUpdate();
	//				
	//			}
	//
	//			{
	//				String sql ="UPDATE familiars "+
	//				   "SET alumno=?, nombre=?, apellido=?, tipo_doc=?, documento=?, "+
	//				       "es_tutor=?, telefono=?, direccion=? "+
	//				 "WHERE alumno='"+nia+"' and id_familiars='"+idMadre+"';";
	//				
	//				PreparedStatement s1 = preparedStatement(sql);
	//				s1.setString(1, nia);
	//				s1.setString(2, nombreMadre);
	//				s1.setString(3, apellidoMadre);
	//				s1.setString(4, "1");
	//				s1.setString(5, "sin informacion");
	//				s1.setString(6, "sin informacion");
	//				s1.setString(7, telefonoMadre2);
	//				s1.setString(8, direccionMadre);
	//				s1.executeUpdate();
	//			}
	//			
	//			
	////			if(c==1){
	////				
	////				PreparedStatement s1 = preparedStatement(sql);
	////				s1.setString(1, nia);
	////				s1.setString(2, nombreMadre);
	////				s1.setString(3, apellidoMadre);
	////				s1.setString(4, "1");
	////				s1.setString(5, "sin informacion");
	////				s1.setString(6, "sin informacion");
	////				s1.setString(7, telefonoMadre2);
	////				s1.setString(8, direccionMadre);
	////				s.executeUpdate();
	////				
	////			}
	//			
	//		} catch (SQLException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//		
	//	
	//	}

	public void newStatusAlumno(String nia,String persona_contacto,String usuario_comedor,String tipo_usuario,String informe_medico,String medicamentos,
			String beca, String string) {
		// TODO Auto-generated method stub
		try{
			String sql="INSERT INTO estatus_alumno("+
					"nia, pesona_de_contacto, usuario_comedor, tipo_usuario, informe_medico, "+
					"medicamentos,beca,cta_bancaria,curso)"+
					" VALUES (?, ?, ?, ?, ?, "+
					"      ?, ?, ?,?)";

			PreparedStatement m =preparedStatement(sql);

			//			m.setString(0,"");

			String curso=principal.getBaseDeDatos().getCursoActual();
			m.setString(1, nia);
			m.setString(2, persona_contacto);
			m.setString(3, usuario_comedor);
			m.setString(4, tipo_usuario);
			m.setString(5, informe_medico);
			m.setString(6, medicamentos);
			m.setString(7, beca);
			m.setString(8, string);
			m.setString(9, curso);

			m.executeUpdate();	


		}catch(Exception e){
			e.printStackTrace();
		}
	}


	public void actualizarStatusAlumno(String nia,String persona_contacto,String usuario_comedor,String tipo_usuario,String informe_medico,String medicamentos,String beca,String cta) {
		// TODO Auto-generated method stub
		try{
			String curso=principal.getBaseDeDatos().getCursoActual();
			String sql ="UPDATE estatus_alumno"+
					" SET nia=?, pesona_de_contacto=?, usuario_comedor=?, tipo_usuario=?, "+
					"informe_medico=?, medicamentos=?, "+
					"beca=?,cta_bancaria=?, curso=? "+
					" WHERE nia='"+nia+"' and curso='"+curso+"';";


			ResultSet x = executeQuery("select * from estatus_alumno where nia='"+nia+"' and curso='"+curso+"'");

			while(x.next()){

				PreparedStatement m =preparedStatement(sql);
				m.setString(1, nia);
				m.setString(2, persona_contacto);
				m.setString(3, usuario_comedor);
				m.setString(4, tipo_usuario);
				m.setString(5, informe_medico);
				m.setString(6, medicamentos);
				m.setString(7, beca);
				m.setString(8, cta);
				m.setString(9, curso);
				m.executeUpdate();	
				return;

			}

			newStatusAlumno(nia,persona_contacto,usuario_comedor,tipo_usuario,informe_medico,medicamentos,beca,cta);



		}catch(Exception e){
			e.printStackTrace();
		}
	}


	public  boolean  agregarUsuario(String nombre, String username, String password,Object id_rol, AsignarUsuario asignarUsuario) {
		try{
			ResultSet x = executeQuery("select * from usuarios where usuario ='"+username+"'");
			boolean exits=false;
			while(x.next()){
				exits = true;
				JOptionPane.showMessageDialog(asignarUsuario, "Nombre de usuario ya existente!","Usuario usado!",JOptionPane.WARNING_MESSAGE);
				return false;
			}
			if(exits){
				return false;
			}
			String sql="INSERT INTO usuarios(usuario, clave, id_rol,nombre_completo) VALUES ('"+username+"', '"+Base64.encodeBytes(password.getBytes())+"', '"+id_rol+"','"+nombre.toUpperCase()+"');";
			boolean c = executeUpdate(sql);
			return c;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;

	}

	public String getRol(String id){
		try{
			ResultSet x = executeQuery("select  rol from roles where id_rol='"+id+"'");
			while(x.next()){
				return x.getString(1);
			}


		}catch(Exception e){
			e.printStackTrace();
		}
		return "sin definir";
	}
	public void getUsuarios(JKTable jkTable,ArrayList<String> arrayListIDs) {

		try{
			arrayListIDs.clear();
			try{
				jkTable.clearTable();
			}catch(Exception e){
				e.printStackTrace();
			}
			ResultSet x = executeQuery("SELECT  nombre_completo,id_rol,id_usuarios from usuarios");
			while(x.next()){

				jkTable.addRow(x.getString(1),getRol(x.getString(2)),x.getString(3));
				arrayListIDs.add(x.getString(3));


			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	public boolean actualizarTipoUsuario(String h, String g) {
		// TODO Auto-generated method stub
		try{
			return executeUpdate("UPDATE usuarios SET  id_rol='"+h+"' WHERE id_usuarios='"+g+"'");

		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	/** AJUSTE CONSULTA PARA BUSQUEDA POR APELLIDO */
	public synchronized boolean verificarSiExiste(String nia) 
	{
		try
		{
			String sql = "";
			String curso = principal.getBaseDeDatos().getCursoActual();
			
			if(Character.isAlphabetic(nia.charAt(nia.length()-1)))
				sql = "SELECT * FROM alumnos WHERE (documento = '" + nia + "' OR apellido1 = '" + nia + "') AND curso = '" + curso + "'";
			else
				sql = "SELECT * FROM alumnos WHERE (nia = '" + nia + "' OR apellido1 = '" + nia + "') AND curso = '" + curso + "'";

			ResultSet x = executeQuery(sql);
			while(x.next())
				return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	/** AJUSTE CONSULTA PARA BUSQUEDA POR APELLIDO */
	public synchronized boolean verificarSiExisteProfesor(String doc) 
	{
		try
		{
			String sql = "";
			String curso = principal.getBaseDeDatos().getCursoActual();
			sql = "SELECT * FROM profes WHERE documento = '" + doc + "' OR apellido1 = '" + doc + "')  AND curso = '" + curso + "'";

			ResultSet x = executeQuery(sql);
			while(x.next())
				return true;	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	/** AJUSTE CONSULTA PARA BUSQUEDA POR APELLIDO */
	public synchronized boolean verificarSiExisteNoProfesor(String doc) 
	{
		try
		{
			String sql = "";
			String curso = principal.getBaseDeDatos().getCursoActual();
			sql="SELECT * FROM pas WHERE documento = '" + doc + "' OR apellido1 = '" + doc + "') AND curso = '" + curso + "'";

			ResultSet x = executeQuery(sql);
			while(x.next())
				return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public String getTutorDeGrupo(String codigoGrupo){
		try{
			String curso=principal.getBaseDeDatos().getCursoActual();
			ResultSet x =executeQuery("select tutor_ppal from grups where codigo='"+codigoGrupo+"' and curso='"+curso+"'");
			while(x.next()){
				return x.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "sin definir";

	}
	public String getNombreTutorDeGrupo(String codigoGrupo){
		try{
			String curso=principal.getBaseDeDatos().getCursoActual();
			ResultSet x =executeQuery("select nombre,apellido1,apellido2 from profes where documento='"+getTutorDeGrupo(codigoGrupo)+"' and curso='"+curso+"'");
			while(x.next()){
				String b=x.getString(2)+" "+x.getString(3)+", "+x.getString(1);
				b=b.replaceAll("null", "");
				return b;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "sin definir";

	}
	public synchronized void getDatosProfesor(String doc, RegistrarProfesores registrarProfesores) {
		// TODO Auto-generated method stub

		try {
			String curso=principal.getBaseDeDatos().getCursoActual();
			ResultSet x = executeQuery("SELECT id_profes, nombre, apellido1, tipo_doc, documento, sexo, " +
					"fecha_ingreso, "+
					"domicilio, numero, puerta, escalera, letra, piso, provincia, "+
					"municipio, localidad, cod_postal, telefono1, telefono2, telefono3, "+
					"horas_puesto, horas_dedicadas, tipo, categoria, email1, email2, "+
					"fecha_nac, lenguaje, nivel_valenciano, ensenanza, organismo, "+
					"banco, sucursal, digito_control, cuenta, titular_surtituido, "+
					"motivo, horas_preparacion, horas_computo_mens, fecha_antiguedad, "+
					"claustro, tipo_docente, iban, foto, alergias,apellido2, referencia_mandato "+
					" FROM profes where documento='"+doc+"' and curso='"+curso+"'");
			while(x.next()){

				registrarProfesores.setNombre(x.getString(2));
				registrarProfesores.setApellido1(x.getString(3));
				registrarProfesores.setApellido2(x.getString(46));
				registrarProfesores.setReferencia(x.getString(47));
				registrarProfesores.setEmail(x.getString(25));
				registrarProfesores.setDocumento(x.getString(5));
				registrarProfesores.setTipoDoc(x.getString(4));
				registrarProfesores.setSexo(x.getString(6));
				registrarProfesores.setFoto(x.getBytes(44));

				registrarProfesores.setCuentaBancaria(x.getString(35));
				registrarProfesores.setAlergias(x.getString(45));
				registrarProfesores.setID(x.getString(1));


				//				registrarProfesores.setApellido1(x.getString(2));
				//				registrarProfesores.setGrupo(x.getString(4));
				//				registrarProfesores.setTutor(getTutorDeGrupo(x.getString(4)));
				//				try{
				//					registrarProfesores.setFoto(x.getBytes(5));
				//				}catch(Exception e){
				//					
				//				}
				//				registrarProfesores.setSexo(x.getString(7));
				//				registrarProfesores.setEmail(x.getString(8));
				//				registrarProfesores.setTelefono1(x.getString(9));
				//				registrarProfesores.setApellido2(x.getString(10));
				//				registrarProfesores.setExpediente(x.getString(11));
				//				registrarProfesores.setSIP(x.getString(12));
				//				registrarProfesores.setFechaNacimiento(x.getString(13));
				//				registrarProfesores.setTelefono2(x.getString(14));
				//				registrarProfesores.setDocumento(x.getString(15));
				//				registrarProfesores.setTipoDoc(x.getString(16));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void getDatosNoProfesor(String doc, RegistrarNoDocentes registrarProfesores) {
		// TODO Auto-generated method stub

		try {
			String curso=principal.getBaseDeDatos().getCursoActual();
			ResultSet x = executeQuery("SELECT id_pas, nombre, apellido1, tipo_doc, documento, sexo, fecha_ingreso,"+ 
					"domicilio, numero, puerta, escalera, letra, piso, provincia, "+
					"municipio, localidad, cod_postal, telefono1, telefono2, telefono3,"+ 
					"horas_puesto, horas_dedicadas, funcion, tipo_trabajador, fecha_nac, "+
					"organismo, banco, sucursal, digito_control, cuenta, claustro, "+
					"iban,foto,apellido2 FROM pas where documento='"+doc+"' and curso='"+curso+"'");
			while(x.next()){
				registrarProfesores.setNombre(x.getString(2));
				registrarProfesores.setApellido1(x.getString(3));
				registrarProfesores.setApellido2(x.getString(34));
				//				registrarProfesores.setEmail(x.getString(25));
				registrarProfesores.setDocumento(x.getString(5));
				registrarProfesores.setTipoDoc(x.getString(4));
				//				registrarProfesores.setSexo(x.getString(6));
				registrarProfesores.setFoto(x.getBytes(33));			
				//				registrarProfesores.setCuentaBancaria(x.getString(35));
				//				registrarProfesores.setAlergias(x.getString(45));
				registrarProfesores.setID(x.getString(1));

				//				registrarProfesores.setApellido1(x.getString(2));
				//				registrarProfesores.setGrupo(x.getString(4));
				//				registrarProfesores.setTutor(getTutorDeGrupo(x.getString(4)));
				//				try{
				//					registrarProfesores.setFoto(x.getBytes(5));
				//				}catch(Exception e){}
				//				registrarProfesores.setSexo(x.getString(7));
				//				registrarProfesores.setEmail(x.getString(8));
				//				registrarProfesores.setTelefono1(x.getString(9));
				//				registrarProfesores.setApellido2(x.getString(10));
				//				registrarProfesores.setExpediente(x.getString(11));
				//				registrarProfesores.setSIP(x.getString(12));
				//				registrarProfesores.setFechaNacimiento(x.getString(13));
				//				registrarProfesores.setTelefono2(x.getString(14));
				//				registrarProfesores.setDocumento(x.getString(15));
				//				registrarProfesores.setTipoDoc(x.getString(16));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public synchronized void getDatosAlumno(String nia, RegistrarAlumno instance) {
		// TODO Auto-generated method stub

		try {
			String curso=principal.getBaseDeDatos().getCursoActual();
			ResultSet x = executeQuery("select nombres, apellido1, nia, grupo, foto, codigo_barra,sexo,email," +
					"telefono1, apellido2, expediente, sip, fecha_nacimiento, telefono2,documento,tipo_doc,curso,persona_de_contacto,cta_bancaria," +
					"informe_medico,medicamentos, referencia_mandato " +
					" from alumnos where (nia='"+nia+"' OR apellido1='"+nia+"') and curso='"+curso+"'");
			//			System.out.println("select nombres, apellidos, nia, grupo, foto, codigo_barra from alumnos where nia='"+nia+"'");
			while(x.next()){
				instance.setNia(x.getString(3));
				instance.setNombres(x.getString(1));
				instance.setApellido1(x.getString(2));
				instance.setGrupo(x.getString(4));
				instance.setTutor(getTutorDeGrupo(x.getString(4)));
				try{
					instance.setFoto(x.getBytes(5));
				}catch(Exception e){

				}
				instance.setSexo(x.getString(7));
				instance.setEmail(x.getString(8));
				instance.setTelefono1(x.getString(9));
				instance.setApellido2(x.getString(10));
				instance.setExpediente(x.getString(11));
				instance.setSIP(x.getString(12));
				instance.setFechaNacimiento(x.getString(13));
				instance.setTelefono2(x.getString(14));
				instance.setPersonaDeContacto(x.getString(18));
				instance.setCuentaBancaria(x.getString(19));
				instance.setInformeMedico(x.getString(20));
				instance.setMedicamentos(x.getString(21));
				instance.setDocumento(x.getString(15));
				instance.setTipoDoc(x.getString(16));
				instance.setCurso(x.getString(17));
				instance.setReferenciaMandato(x.getString(22));
			}

			ResultSet a = executeQuery("SELECT id_familiars, alumno, nombre, apellido1, tipo_doc, documento, " +
					"es_tutor, telefono, direccion,parentesco,apellido2 FROM familiars where alumno='"+nia+"' and curso='"+curso+"'");

			while(a.next()){
				//				System.out.println("AAA");

				instance.addFamiliar(a.getString(10), a.getString(3), a.getString(4), a.getString(11), a.getString(6), a.getString(5), a.getString(2), a.getString(1),a.getString(7));	

				//			ResultSet z =executeQuery("SE<q	<

			}

		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void registrarAlumnos(Imexalum a, String curso) {
		try{
			ResultSet x1 = executeQuery("select * from alumnos where nia='"+a.getNIA()+"' and curso='"+curso+"'");
			while(x1.next()){

				actualizarDatosAlumno(a.getNIA(),a.getNombre(),a.getApellido1(), null, a.getGrupo(), null, a.getSexo(), a.getEmail1(),a.getTelefono1(),a.getTelefono2(),a.getFecha_nac(),a.getDocumento(),a.getSip(),a.getExpediente(),a.getApellido2(),a.getTipo_doc(),curso,"","","","",null);
				//				getTelefono2(),getfechaNacimiento(),getDocumento(),getSIP(),getExpediente(),getApellido2());
				//				actualizarStatusAlumno(a.getNIA(), a.get, usuario_comedor, tipo_usuario, informe_medico, medicamentos, fecha_alta, fecha_baja, beca)
				return;
			}


			String red="";
			if(a.getDocumento().length()==10)
				red="0"+a.getDocumento();
			else{
				red="00"+a.getDocumento();
			}
			newAlumno(a.getNIA(),a.getNombre(),a.getApellido1(), null, a.getGrupo(), null, a.getSexo(), a.getEmail1(),a.getTelefono1(),a.getTelefono2(),a.getFecha_nac(),a.getDocumento(),a.getSip(),a.getExpediente(),a.getApellido2(),a.getTipo_doc(),curso,red);


			//			System.out.println(h);
			//			newStatusAlumno(a.getNIA(), "", "no", "2", "", "", "", curso);

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public float getComisionBanco(String curso){
		try{
			ResultSet x = executeQuery("select comision_banco from cursos where curso='"+curso+"' ");
			while(x.next()){
				return x.getFloat(1);
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		return 1;
	}

	public int getDias(String curso){
		try{
			ResultSet x = executeQuery("select dias from cursos where curso='"+curso+"' ");
			while(x.next()){
				return x.getInt(1);
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		return 1;
	}
	public float getPrecioMenu(String curso){
		try{
			ResultSet x = executeQuery("select precio_menu from cursos where curso='"+curso+"' ");
			while(x.next()){
				return x.getFloat(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return 1;
	}
	public int getJuevesComedor(String curso){
		try{
			ResultSet x = executeQuery("select jueves_comedor from cursos where curso='"+curso+"' ");
			while(x.next()){
				return x.getInt(1);
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		return 1;
	}
	public int getDiasDescontados(String curso){
		try{
			ResultSet x = executeQuery("select dias_descontados from cursos where curso='"+curso+"' ");
			while(x.next()){
				return x.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	public void generarRemesasUsuarios(String nia, String curso, String id) {

		try{
			float precioMenu=getPrecioMenu(curso);
			int   dias=getDias(curso);
			float comisionBanco=getComisionBanco(curso);
			//			
			int diasDescontado=getDiasDescontados(curso);
			float total=((dias*precioMenu+comisionBanco/8)/8);// Result: 59.034374

			double redondeado=Math.ceil(total); 				//Result: 60  // valor de cada remesaa

			double x= redondeado*7; 							//Result: 420

			double ultimaRemesa=0;
			ultimaRemesa=(dias*precioMenu+comisionBanco/8)-x;
			if(diasDescontado>0){
				//Result:52.274993896484375
				ultimaRemesa=ultimaRemesa-(diasDescontado*precioMenu);
			}

			//			int value=1;
			for(int index=1;index<=7;index++){
				String sql="INSERT INTO remesas("+
						"nia, curso, nro_remesa, valor_remesa,id_fechas)"+
						" VALUES ('"+nia+"', '"+getCursoActual()+"', '"+index+"', '"+redondeado+"','"+id+"');";;
						executeUpdate(sql);

						ResultSet a= executeQuery("select id_remesa from remesas where nia='"+nia+"' and curso='"+getCursoActual()+"' order by id_remesa desc limit 1");
						String m="";
						while(a.next()){
							m=a.getString(1);
							break;
						}
						String sql1="INSERT INTO cartas_de_cobro("+
								" nia, curso, estado, fecha, id_remesa,estado2,estado3) "+
								" VALUES ('"+nia+"', '"+getCursoActual()+"', 'No Generada', " +
								"'"+new Date(new java.util.Date().getTime())+"', '"+m+"','No Generada','No Generada');";

						executeUpdate(sql1);

			}



			String sql="INSERT INTO remesas("+
					"nia, curso, nro_remesa, valor_remesa,id_fechas)"+
					" VALUES ('"+nia+"', '"+getCursoActual()+"', '8', '"+ultimaRemesa+"','"+id+"');";
			executeUpdate(sql);

			//			System.out.println(a);

			//			double devolucion=(3.55f*0.25);
			//			
			//			System.out.println(a-devolucion);
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	public void generarRemesas2Dias(String nia, String curso,String id) {

		try{
			float precioMenu=getPrecioMenu(curso);
			int   dias=getDias(curso);
			float comisionBanco=getComisionBanco(curso);
			int juevesComedor=getJuevesComedor(curso);

			//			System.out.println(juevesComedor);


			//			System.out.println(dias);
			float total=(((dias-juevesComedor)*precioMenu+comisionBanco/8)/8);// Result: 59.034374

			double redondeado=Math.ceil(total); 				//Result: 60  // valor de cada remesaa
			int diasDescontado=getDiasDescontados(curso);

			double x= redondeado*7; 							//Result: 420

			double ultimaRemesa=0;
			ultimaRemesa=(dias*precioMenu+comisionBanco/8)-x;
			if(diasDescontado>0){
				//Result:52.274993896484375
				ultimaRemesa=ultimaRemesa-(diasDescontado*precioMenu);
			}

			//			int value=1;
			for(int index=1;index<=7;index++){
				String sql="INSERT INTO remesas("+
						"nia, curso, nro_remesa, valor_remesa,id_fechas)"+
						" VALUES ('"+nia+"', '"+getCursoActual()+"', '"+index+"', '"+redondeado+"','"+id+"');";
				executeUpdate(sql);	


				ResultSet a= executeQuery("select id_remesa from remesas where nia='"+nia+"' and curso='"+getCursoActual()+"' order by id_remesa desc limit 1");
				String m="";
				while(a.next()){
					m=a.getString(1);
					break;
				}
				String sql1="INSERT INTO cartas_de_cobro("+
						" nia, curso, estado, fecha, id_remesa,estado2,estado3) "+
						" VALUES ('"+nia+"', '"+getCursoActual()+"', 'No Generada', " +
						"'"+new Date(new java.util.Date().getTime())+"', '"+m+"','No Generada','No Generada');";

				executeUpdate(sql1);



			}

			String sql="INSERT INTO remesas("+
					"nia, curso, nro_remesa, valor_remesa,id_fechas)"+
					" VALUES ('"+nia+"', '"+getCursoActual()+"', '8', '"+ultimaRemesa+"','"+id+"');";
			executeUpdate(sql);

			//			System.out.println(a);

			//			double devolucion=(3.55f*0.25);
			//			
			//			System.out.println(a-devolucion);
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	public void getTutores(JKComboBox comboBoxTutor,ArrayList<String> arrayListTutor) {

		try{

			arrayListTutor.clear();
			String curso=principal.getBaseDeDatos().getCursoActual();
			ResultSet x = executeQuery("select documento,nombre,apellido from profes where curso='"+curso+"' order by apellido");
			while(x.next()){
				arrayListTutor.add(x.getString(1));
				comboBoxTutor.addItem(x.getString(3)+" ,"+x.getString(2));

			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	public boolean verificarCurso(String curso) {
		// TODO Auto-generated method stub
		try{
			ResultSet x =executeQuery("select * from cursos where curso='"+curso+"'");
			while(x.next()){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	public boolean crearCurso(String curso, String dias, String comision, String precioMenu, String juevesComedor) {
		// TODO Auto-generated method stub
		try{
			return executeUpdate("INSERT INTO cursos(curso,estado,precio_menu, comision_banco, dias,jueves_comedor)VALUES ('"+curso+"','Activo'," +
					"'"+Float.parseFloat(precioMenu)+"','"+Float.parseFloat(comision)+"','"+Integer.parseInt(dias)+"','"+juevesComedor+"');");
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	public void getIncidencia(DialogoIncidencias dialogIncidencias, String nia) {
		// TODO Auto-generated method stub
		try{
			String curso=principal.getBaseDeDatos().getCursoActual();
			ResultSet x =executeQuery("SELECT id_incidencias, nia, incidencia, fecha  FROM incidencias where nia='"+nia+"' and curso='"+curso+"'");
			while(x.next()){

				dialogIncidencias.addIncidencia(x.getString(4), x.getString(3),x.getString(1));
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	public boolean registrarIncidencia(String nia, java.sql.Date s, String in) {
		// TODO Auto-generated method stub
		try{
			String curso=principal.getBaseDeDatos().getCursoActual();
			return executeUpdate("INSERT INTO incidencias(nia, incidencia, fecha,curso) VALUES ('"+nia+"', '"+in+"', '"+s+"','"+curso+"');");
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	public String getGrupo(String codigo){
		try{
			String curso=principal.getBaseDeDatos().getCursoActual();
			ResultSet x =executeQuery("select nombre from grups where codigo='"+codigo+"' and curso='"+curso+"'");
			while(x.next()){
				return x.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "No Definido";
	}
	public void buscarAlumnos(String text,JKTable table,JLabel jLabel) {
		// TODO Auto-generated method stub
		try{
			table.clearTable();
		}catch(Exception e){

		}
		try{
			String curso=principal.getBaseDeDatos().getCursoActual();
			String sql="SELECT id_alumno, nombres, apellido1, nia, grupo, foto, codigo_barra, " +
					" curso, sexo, email,telefono1,apellido2 FROM alumnos where  nombres like '"+text+"%	' or apellido1 like '"+text+"%' or nia like '"+text+"%' OR apellido2 like '"+text+"%'" +
					" and curso='"+curso+"' order by apellido1";

			if(text.length()==0){
				sql="SELECT id_alumno, nombres, apellido1, nia, grupo, foto, codigo_barra, " +
						" curso, sexo, email,telefono1,apellido2 FROM alumnos where curso='"+curso+"' order by apellido1";
			}
			ResultSet x = executeQuery(sql);
			while(x.next()){
				//				JLabel label = new JLabel();
				//				try {
				//					if(x.getBytes(6)!=null){
				//						if(x.getBytes(6).length>1){
				//							Image xz = Toolkit.getDefaultToolkit().createImage(x.getBytes(6)).getScaledInstance(85, 60, 70);
				//							label.setIcon(new ImageIcon(xz));
				//						   }else{
				//
				//								ImageIcon g = new ImageIcon(getClass().getResource(
				//										"/resource/default.gif"));
				//								Image m = Toolkit.getDefaultToolkit()
				//										.createImage(g.getImage().getSource())
				//										.getScaledInstance(70, 60, 20);
				//								label.setIcon(new ImageIcon(m));
				//						   }
				//						}else{
				//
				//							ImageIcon g = new ImageIcon(getClass().getResource(
				//									"/resource/default.gif"));
				//							Image m = Toolkit.getDefaultToolkit()
				//									.createImage(g.getImage().getSource())
				//									.getScaledInstance(70, 60, 20);
				//							label.setIcon(new ImageIcon(m));
				//						}
				//					} catch (Exception e) {
				////						Image xz = Toolkit.getDefaultToolkit().createImage(.getScaledInstance(85, 60, 70);
				//						
				//						ImageIcon g = new ImageIcon(getClass().getResource(
				//								"/resource/default.gif"));
				//						Image m = Toolkit.getDefaultToolkit()
				//								.createImage(g.getImage().getSource())
				//								.getScaledInstance(70, 60, 20);
				//						label.setIcon(new ImageIcon(m));
				//				}
				table.addRow(x.getString(4),x.getString(2),x.getString(3),x.getString(12),x.getString(11),
						x.getString(10),getGrupo(x.getString(5)));
				table.repaint();
				jLabel.setText("<html><body>Nro. de Resultados: <b><font color='blue'>"+table.getRowCount()+"</font></b></body></html>");

			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void buscarProfesores(String text,JKTable table,JLabel jLabel) {
		// TODO Auto-generated method stub
		try{
			table.clearTable();
		}catch(Exception e){
			//			jkTable.addColumn("Foto");
			//			jkTable.addColumn("Documento");
			//			jkTable.addColumn("Nombres");
			//			jkTable.addColumn("Apellido 1");
			//			jkTable.addColumn("Apellido 2");
			//			jkTable.addColumn("Sexo");
			//			jkTable.addColumn("Email");
		}
		try{
			String sql="";
			String curso=principal.getBaseDeDatos().getCursoActual();
			if(text.length()==0){
				sql="SELECT id_profes, foto,documento,nombre, apellido1,apellido2,sexo,email1 FROM profes where curso='"+curso+"' order by apellido1";
			}
			ResultSet x = executeQuery(sql);
			while(x.next()){
				JLabel label = new JLabel();
				try {
					if(x.getBytes(2)!=null){
						if(x.getBytes(2).length>1){
							Image xz = Toolkit.getDefaultToolkit().createImage(x.getBytes(2)).getScaledInstance(70, 60, 70);
							label.setIcon(new ImageIcon(xz));
						}else{
							ImageIcon g = new ImageIcon(getClass().getResource(
									"/resource/default.gif"));
							Image m = Toolkit.getDefaultToolkit()
									.createImage(g.getImage().getSource())
									.getScaledInstance(70, 60, 20);
							label.setIcon(new ImageIcon(m));
						}
					}else{

						ImageIcon g = new ImageIcon(getClass().getResource(
								"/resource/default.gif"));
						Image m = Toolkit.getDefaultToolkit()
								.createImage(g.getImage().getSource())
								.getScaledInstance(70, 60, 20);
						label.setIcon(new ImageIcon(m));
					}
				} catch (Exception e) {
					//						Image xz = Toolkit.getDefaultToolkit().createImage(.getScaledInstance(85, 60, 70);

					ImageIcon g = new ImageIcon(getClass().getResource(
							"/resource/default.gif"));
					Image m = Toolkit.getDefaultToolkit()
							.createImage(g.getImage().getSource())
							.getScaledInstance(70, 60, 20);
					label.setIcon(new ImageIcon(m));
				}
				table.addRow(label,x.getString(3),x.getString(4),x.getString(5),x.getString(6),x.getString(7),x.getString(8));
				jLabel.setText("<html><body>Nro. de Resultados: <b><font color='blue'>"+table.getRowCount()+"</font></b></body></html>");

			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void buscarProfesores(JKTable table) {
		// TODO Auto-generated method stub
		try{
			table.clearTable();
		}catch(Exception e){

		}
		try{
			String sql="";
			String curso=principal.getBaseDeDatos().getCursoActual();
			//			if(text.length()==0){
			sql="SELECT id_profes, foto,documento,nombre, apellido1,apellido2,sexo,email1 FROM profes where curso='"+curso+"' order by apellido1";
			//			}
			ResultSet x = executeQuery(sql);
			//			System.out.println("m");
			while(x.next()){

				table.addRow(x.getString(3),x.getString(5)+" "+x.getString(6)+", "+x.getString(4),Boolean.FALSE);
				//				jLabel.setText("<html><body>Nro. de Resultados: <b><font color='blue'>"+table.getRowCount()+"</font></b></body></html>");

			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void buscarNoDocentes(String text,JKTable table,JLabel jLabel) {
		// TODO Auto-generated method stub
		try{
			table.clearTable();
		}catch(Exception e){
			//			jkTable.addColumn("Foto");
			//			jkTable.addColumn("Documento");
			//			jkTable.addColumn("Nombres");
			//			jkTable.addColumn("Apellido 1");
			//			jkTable.addColumn("Apellido 2");
			//			jkTable.addColumn("Sexo");
			//			jkTable.addColumn("Email");
		}
		try{
			String sql="";
			String curso=principal.getBaseDeDatos().getCursoActual();
			if(text.length()==0){
				sql="SELECT id_pas, foto,documento,nombre, apellido1,apellido2 FROM pas where curso='"+curso+"' order by apellido1";
			}
			ResultSet x = executeQuery(sql);
			while(x.next()){
				JLabel label = new JLabel();
				try {
					if(x.getBytes(2)!=null){
						if(x.getBytes(2).length>1){
							Image xz = Toolkit.getDefaultToolkit().createImage(x.getBytes(2)).getScaledInstance(70, 60, 70);
							label.setIcon(new ImageIcon(xz));
						}else{
							ImageIcon g = new ImageIcon(getClass().getResource(
									"/resource/default.gif"));
							Image m = Toolkit.getDefaultToolkit()
									.createImage(g.getImage().getSource())
									.getScaledInstance(70, 60, 20);
							label.setIcon(new ImageIcon(m));
						}
					}else{

						ImageIcon g = new ImageIcon(getClass().getResource(
								"/resource/default.gif"));
						Image m = Toolkit.getDefaultToolkit()
								.createImage(g.getImage().getSource())
								.getScaledInstance(70, 60, 20);
						label.setIcon(new ImageIcon(m));
					}
				} catch (Exception e) {
					//						Image xz = Toolkit.getDefaultToolkit().createImage(.getScaledInstance(85, 60, 70);

					ImageIcon g = new ImageIcon(getClass().getResource(
							"/resource/default.gif"));
					Image m = Toolkit.getDefaultToolkit()
							.createImage(g.getImage().getSource())
							.getScaledInstance(70, 60, 20);
					label.setIcon(new ImageIcon(m));
				}
				table.addRow(label,x.getString(3),x.getString(4),x.getString(5),x.getString(6));
				jLabel.setText("<html><body>Nro. de Resultados: <b><font color='blue'>"+table.getRowCount()+"</font></b></body></html>");

			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void actualizarFamiliar(PanelFamiliar panelFamiliar) {
		try{
			if(panelFamiliar.getID()!=null){
				String sql="UPDATE familiars"+
						" SET nombre='"+panelFamiliar.getNombre()+"', apellido1='"+panelFamiliar.getApellido1()+"', tipo_doc='"+panelFamiliar.getTipoDoc()+"',"+ 
						" documento='"+panelFamiliar.getDocumento()+"', es_tutor='"+panelFamiliar.getTutor()+"', parentesco='"+panelFamiliar.getParentesco()+"',"+ 
						" apellido2='"+panelFamiliar.getApellido2()+"'"+
						"WHERE id_familiars='"+panelFamiliar.getID()+"';";
				executeUpdate(sql);
			}else{
				agregarFamiliar(panelFamiliar);
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	public void agregarFamiliar(PanelFamiliar a) {
		try{
			//			String sql="UPDATE familiars"+
			//		  " SET nombre='"+panelFamiliar.getNombre()+"', apellido1='"+panelFamiliar.getApellido1()+"', tipo_doc='"+panelFamiliar.getTipoDoc()+"',"+ 
			//		      " documento='"+panelFamiliar.getDocumento()+"', es_tutor='"+panelFamiliar.getTutor()+"', parentesco='"+panelFamiliar.getParentesco()+"',"+ 
			//		      " apellido2='"+panelFamiliar.getApellido2()+"'"+
			String curso=principal.getBaseDeDatos().getCursoActual();
			//		  "WHERE id_familiars='"+panelFamiliar.getID()+"';";
			String sql="INSERT INTO familiars("+
					"alumno, nombre, apellido1, tipo_doc, documento,"+ 
					"es_tutor, telefono, direccion, parentesco, apellido2)"+
					"VALUES ('"+a.getAlumno()+"','"+a.getNombre()+"', '"+a.getApellido1()+"','"+a.getTipoDoc()+"', '"+a.getDocumento()+"', '"+a.getTutor()+"',"+ 
					"'Sin informacion', 'Sin informacion', '"+a.getParentesco()+"', '"+a.getApellido2()+"','"+curso+"');";

			executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	public String getNia(String g) {
		// TODO Auto-generated method stub
		try{
			String curso=principal.getBaseDeDatos().getCursoActual();
			ResultSet x =executeQuery("select nia  from alumnos where documento='"+g+"' and curso='"+curso+"'");
			while(x.next()){
				return x.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "No encontrado";
	}

	public void getProgfesors(JKComboBox comboBox,
			ArrayList<String> arrayListProfesores) {
		// TODO Auto-generated method stub
		try{
			String curso=principal.getBaseDeDatos().getCursoActual();
			ResultSet x = executeQuery("SELECT documento, nombre, apellido1,apellido2 from profes where curso='"+curso+"' order by nombre"); 
			//       "domicilio, numero, puerta, escalera, letra, piso, provincia,"+
			//       "municipio, localidad, cod_postal, telefono1, telefono2, telefono3,"+ ".replaceAll("null", "");
			//       "horas_puesto, horas_dedicadas, tipo, categoria, email1, email2, "+
			//       "fecha_nac, lenguaje, nivel_valenciano, ensenanza, organismo, "+
			//       "banco, sucursal, digito_control, cuenta, titular_surtituido, "+
			//       "motivo, horas_preparacion, horas_computo_mens, fecha_antiguedad,"+ 
			//       "claustro, tipo_docente, iban "+
			//  "FROM profes;");
			arrayListProfesores.clear();
			comboBox.removeAllItems();
			while(x.next()){
				String h=""+x.getString(2)+" "+x.getString(3)+" "+x.getString(4);
				h = h.replaceAll("null", "");
				comboBox.addItem(h);
				arrayListProfesores.add(x.getString(1));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void addFoto(String documento, File fileFoto) {

		try{
			String curso=principal.getBaseDeDatos().getCursoActual();
			PreparedStatement p = preparedStatement("UPDATE profes SET foto=? WHERE documento='"+documento+"' and curso='"+curso+"'");
			if (fileFoto != null) {
				//				System.out.println("foto agregada: "+fileFoto);
				FileInputStream fileInputStream = new FileInputStream(fileFoto);
				p.setBinaryStream(1, fileInputStream, (int) fileFoto.length());
			}
			p.executeUpdate();
		}catch(Exception e){
			//			e.printStackTrace();
		}

	}
	public void addFotoNoDocentes(String documento, File fileFoto) {
		try{
			String curso=principal.getBaseDeDatos().getCursoActual();
			PreparedStatement p = preparedStatement("UPDATE pas SET foto=? WHERE documento='"+documento+"' and curso='"+curso+"'");
			if (fileFoto != null) {
				//				System.out.println("foto agregada: "+fileFoto);
				FileInputStream fileInputStream = new FileInputStream(fileFoto);
				p.setBinaryStream(1, fileInputStream, (int) fileFoto.length());
			}
			p.executeUpdate();
		}catch(Exception e){
			//			e.printStackTrace();
		}

	}
	public boolean eliminarProfesor(String id_actualizar) {
		// TODO Auto-generated method stub
		try{
			String curso=principal.getBaseDeDatos().getCursoActual();
			return executeUpdate("DELETE FROM profes WHERE id_profes='"+id_actualizar+"' and curso='"+curso+"'");

		}catch(Exception e){
			e.printStackTrace();
		}

		return false;
	}
	public boolean eliminarNoProfesor(String id_actualizar) {
		// TODO Auto-generated method stub
		try{
			return executeUpdate("DELETE FROM pas WHERE id_pas='"+id_actualizar+"'");

		}catch(Exception e){
			e.printStackTrace();
		}

		return false;
	}
	public String getNombreTutor(String doc){
		try{
			String curso=principal.getBaseDeDatos().getCursoActual();
			ResultSet x =executeQuery("select nombre,apellido1,apellido2 from profes where documento='"+doc+"' and curso='"+curso+"' order by apellido1");
			while(x.next()){
				String h=x.getString(1)+" "+x.getString(2)+", "+x.getString(3);
				h = h .replaceAll("null", "");
				return h;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "No definido";
	}
	public void getGroups(JKTable jkTable, JLabel label, String cursoActual) {
		// TODO Auto-generated method stub
		try{
			try{
				jkTable.clearTable();
			}catch(Exception e){

			}
			//			jkTable.addColumn("Codigo");
			//			jkTable.addColumn("Nombre de Grupo");
			//			jkTable.addColumn("Codigo");
			//			jkTable.addColumn("Tutor");
			ResultSet x =executeQuery("select codigo,nombre,tutor_ppal from grups where curso='"+cursoActual+"'");
			while(x.next()){
				jkTable.addRow(x.getString(1),x.getString(2),getNombreTutor(x.getString(3)));
				label.setText(""+jkTable.getRowCount());
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	public String getTuTorGrupo(String codigoGrupo){
		try{
			String curso=principal.getBaseDeDatos().getCursoActual();
			ResultSet x=executeQuery("select tutor_ppal from grups where codigo='"+codigoGrupo+"' and curso='"+curso+"' ");
			while(x.next()){
				return x.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "No definido";
	}
	public String getNombreGrupo(String codigoGrupo) {
		// TODO Auto-generated method stub
		try{
			String curso=principal.getBaseDeDatos().getCursoActual();
			ResultSet x =executeQuery("select nombre from grups where codigo='"+codigoGrupo+"' and curso='"+curso+"'");
			while(x.next()){
				return x.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	public boolean eliminarGrupo(String codigoGrupo, String cursoActual) {
		// TODO Auto-generated method stub
		try{
			return executeUpdate("DELETE FROM grups WHERE codigo='"+codigoGrupo+"' and curso='"+cursoActual+"'");

		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	public void getAlumnos(String codigoGrupo,JKTable table,JLabel label) {
		// TODO Auto-generated method stub
		try{
			try{
				table.clearTable();
			}catch(Exception e){

			}
			String curso=principal.getBaseDeDatos().getCursoActual();
			ResultSet x = executeQuery("select nia,documento,apellido1,apellido2,nombres from alumnos where grupo='"+codigoGrupo+"' and curso='"+curso+"' order by apellido1");
			while(x.next()){
				table.addRow(x.getString(1),x.getString(2),x.getString(3)+" "+x.getString(4)+", "+x.getString(5));
				label.setText(""+table.getRowCount());
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	public ArrayList<Persona> getAlumnos(String codigoGrupo,String cursoActual) {
		// TODO Auto-generated method stub
		ArrayList<Persona> alumnos=new ArrayList<>();
		try{

			ResultSet x = executeQuery("select nia,documento,apellido1,apellido2,nombres,foto,fecha_nacimiento,telefono1,grupo,documento from alumnos where grupo='"+codigoGrupo+"' and curso='"+cursoActual+"' order by apellido1");
			
//			System.out.println("select nia,documento,apellido1,apellido2,nombres,foto,fecha_nacimiento,telefono1,grupo,documento from alumnos where grupo='"+codigoGrupo+"' and curso='"+cursoActual+"' order by apellido1");
			
			while(x.next()){
				Persona alumno=new Persona();
				alumno.setFoto(x.getBytes(6));
				alumno.setNia(x.getString(1));
				alumno.setGrupo(x.getString(9));
				alumno.setNombres(x.getString(5));
				alumno.setApellidos(x.getString(3)+" "+x.getString(4));
				alumno.setFechaNacimiento(x.getString(7));
				alumno.setTelefono(x.getString(8));
				alumno.setDocumento(x.getString(10));
				String curso=principal.getBaseDeDatos().getCursoActual();

				if(new File(new File(".")
				.getAbsolutePath().substring(0,
						new File(".").getAbsolutePath().length() - 2).toString()+""+File.separator+"System-Comedor"
						+File.separator+"Fotos"+File.separator+"" + x.getString(1) + ".jpg").exists()){
					alumno.setFotoVerificada(true);
				}else{
					alumno.setFotoVerificada(false);
				}
				ResultSet a =executeQuery("select nombre,apellido1,apellido2 from familiars where parentesco='1' and alumno='"+x.getString(1)+"' and curso='"+curso+"'");
				String madre="";
				while(a.next()){
					madre=a.getString(2)+" "+a.getString(3)+", "+a.getString(1); 
				}
				ResultSet b =executeQuery("select nombre,apellido1,apellido2 from familiars where parentesco='2' and alumno='"+x.getString(1)+"' and curso='"+curso+"'");
				String padre="";
				while(b.next()){
					padre=b.getString(2)+" "+b.getString(3)+", "+b.getString(1); 
				}
				alumno.setMadre(madre);
				alumno.setPadre(padre);

				alumnos.add(alumno);
				//				table.addRow(x.getString(1),x.getString(2),x.getString(3)+" "+x.getString(4)+", "+x.getString(5));
				//				label.setText(""+table.getRowCount());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return alumnos;

	}
	public ArrayList<ArrayList<String>> getFaltasGrupo(String codigoGrupo,java.util.Date date1,java.util.Date date2) {
		// TODO Auto-generated method stub
		ArrayList<ArrayList<String>> arrayListG=new ArrayList<>(); 
		try{

			ResultSet x = executeQuery("select nia,documento,apellido1,apellido2,nombres,foto,fecha_nacimiento,telefono1,grupo,documento from alumnos where grupo='"+codigoGrupo+"' and curso='"+getCursoActual()+"' order by apellido1");
			while(x.next()){


				try{
					ResultSet xc =executeQuery("SELECT id_faltas, nia, curso, fecha, fecha_text,observacion " +
							"FROM faltas where (fecha >= '"+new Date(date1.getTime())+"' AND fecha <='"+new Date(date2.getTime())+"' ) " +
							"AND curso='"+getCursoActual()+"' and nia='"+x.getString(1)+"'");
					//					Hashtable<String, Integer> hashtable=new Hashtable<>();
					//					System.out.println();
					ArrayList<String> arrayList= new ArrayList<>();
					while(xc.next()){
						//						String nia=x.getString(2);

						arrayList.add(xc.getString(2)+"@1"+getFechaHumana(xc.getDate(4))+"@1"+xc.getString(5)+"@1"+xc.getString(6));
					}
					arrayListG.add(arrayList);
					//					Collections.sort(arrayList);
					//					new PDF_Faltas_Alumno(arrayList, date, date2, principal, niaAlumno);

				}catch(Exception e){
					e.printStackTrace();
				}






				//				table.addRow(x.getString(1),x.getString(2),x.getString(3)+" "+x.getString(4)+", "+x.getString(5));
				//				label.setText(""+table.getRowCount());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		//		return alumnos;

		return arrayListG;
	}




	public ArrayList<Persona> getTodosLosAlumnos(String curso) 
	{
		// TODO Auto-generated method stub
		ArrayList<Persona> alumnos=new ArrayList<>();
		
		try
		{
			ResultSet x = executeQuery("SELECT nia, documento, apellido1, apellido2, nombres, foto, fecha_nacimiento, telefono1 " 
					+ "FROM alumnos WHERE curso = '" + curso + "' ORDER BY apellido1");
			
			while(x.next())
			{
				Persona alumno = new Persona();
				alumno.setFoto(x.getBytes("foto"));
				alumno.setNia(x.getString("nia"));
				alumno.setNombres(x.getString("nombres"));
				alumno.setApellidos(x.getString("apellido1") + " " + x.getString("apellido2"));
				alumno.setFechaNacimiento(x.getString("fecha_nacimiento"));
				alumno.setTelefono(x.getString("telefono1"));
				
				ResultSet a = executeQuery("SELECT nombre, apellido1, apellido2 " 
						+ "FROM familiars WHERE parentesco = '1' AND alumno = '" + x.getString("nia") + "' AND curso = '" + curso + "'");
				
				String madre = "";
				
				while(a.next())
					madre = a.getString("apellido1") + " " + a.getString("apellido2") + ", " + a.getString("nombre"); 
				
				ResultSet b = executeQuery("SELECT nombre, apellido1, apellido2 " 
						+ "FROM familiars WHERE parentesco = '2' AND alumno = '" + x.getString("nia") + "' AND curso = '" + curso + "'");
				
				String padre = "";
				
				while(b.next())
					padre = b.getString("apellido1") + " " + b.getString("apellido2") + ", " + b.getString("nombre"); 
				
				alumno.setMadre(madre);
				alumno.setPadre(padre);

				alumnos.add(alumno);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return alumnos;
	}
	
	public ArrayList<Persona> getUsuariosComedor(String curso, String usuario, String dias) {
		// TODO Auto-generated method stub
		ArrayList<Persona> alumnos=new ArrayList<>();
		try{
			ResultSet x = executeQuery("select nia,documento,apellido1,apellido2,nombres,foto,fecha_nacimiento," +
					"telefono1,email from alumnos where curso='"+curso+"' order by apellido1");
			while(x.next()){
				//				System.out.println(y);

				ResultSet a1 = executeQuery("SELECT nia, fecha_alta, fecha_baja, id_fechas, curso, beca, tipo_usuario, "+
						"asiduo, autorizados_lunes_martes, autorizados_jueves"+
						" FROM fechas_altas_bajas where nia='"+x.getString(1)+"'and curso='"+curso+"' and tipo_usuario='"+dias+"'");

				String user="";
				if(usuario.equalsIgnoreCase("Usuarios Comedor")){
					user="Si";
				}else{
					user="No";
				}
				boolean n = false;
				while(a1.next()){
					if(a1.getString(8).equalsIgnoreCase(user)){
						n = true;
						break;
					}else{
						break;
					}
				}
				if(n){
					Persona alumno=new Persona();
					alumno.setFoto(x.getBytes(6));
					alumno.setNia(x.getString(1));
					alumno.setEmail(x.getString(9));
					alumno.setNombres(x.getString(5));
					alumno.setApellidos(x.getString(3)+" "+x.getString(4));
					alumno.setFechaNacimiento(x.getString(7));
					alumno.setTelefono(x.getString(8));
					ResultSet a =executeQuery("select nombre,apellido1,apellido2 from familiars where parentesco='1' " +
							"and alumno='"+x.getString(1)+"' and curso='"+curso+"'");
					String madre="";
					while(a.next()){
						madre=a.getString(2)+" "+a.getString(3)+", "+a.getString(1); 
					}
					ResultSet b =executeQuery("select nombre,apellido1,apellido2 from familiars where parentesco='2' " +
							"and alumno='"+x.getString(1)+"' and curso='"+curso+"'");
					String padre="";
					while(b.next()){
						padre=b.getString(2)+" "+b.getString(3)+", "+b.getString(1); 
					}
					alumno.setMadre(madre);
					alumno.setPadre(padre);
					alumnos.add(alumno);

				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return alumnos;

	}
	public ArrayList<Persona> getUsuariosComedor(String curso) {
		// TODO Auto-generated method stub
		ArrayList<Persona> alumnos=new ArrayList<>();
		try{
			ResultSet x = executeQuery("select nia,documento,apellido1,apellido2,nombres,foto,fecha_nacimiento," +
					"telefono1,email from alumnos where curso='"+curso+"' order by apellido1");
			while(x.next()){
				//				System.out.println(y);

				ResultSet a1 = executeQuery("SELECT nia, fecha_alta, fecha_baja, id_fechas, curso, beca, tipo_usuario, "+
						"asiduo, autorizados_lunes_martes, autorizados_jueves"+
						" FROM fechas_altas_bajas where nia='"+x.getString(1)+"'and curso='"+curso+"'");


				boolean n = false;
				while(a1.next()){
					if(a1.getString(8).equalsIgnoreCase("Si")){
						n = true;
						break;
					}else
					{
						break;
					}
				}
				if(n){

					Persona alumno=new Persona();
					alumno.setFoto(x.getBytes(6));
					alumno.setNia(x.getString(1));
					alumno.setEmail(x.getString(9));
					alumno.setNombres(x.getString(5));
					alumno.setApellidos(x.getString(3)+" "+x.getString(4));
					alumno.setFechaNacimiento(x.getString(7));
					alumno.setTelefono(x.getString(8));
					ResultSet a =executeQuery("select nombre,apellido1,apellido2 from familiars where parentesco='1' " +
							"and alumno='"+x.getString(1)+"' and curso='"+curso+"'");
					String madre="";
					while(a.next()){
						madre=a.getString(2)+" "+a.getString(3)+", "+a.getString(1); 
					}
					ResultSet b =executeQuery("select nombre,apellido1,apellido2 from familiars where parentesco='2' " +
							"and alumno='"+x.getString(1)+"' and curso='"+curso+"'");
					String padre="";
					while(b.next()){
						padre=b.getString(2)+" "+b.getString(3)+", "+b.getString(1); 
					}
					alumno.setMadre(madre);
					alumno.setPadre(padre);
					alumnos.add(alumno);

				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return alumnos;

	}
	public ArrayList<Persona> getUsuariosComedor3Dias(String curso,String dia) {
		// TODO Auto-generated method stub
		ArrayList<Persona> alumnos=new ArrayList<>();
		try{
			ResultSet x = executeQuery("select nia,documento,apellido1,apellido2,nombres,foto,fecha_nacimiento," +
					"telefono1,email from alumnos where curso='"+curso+"' order by apellido1");
			while(x.next()){
				//				System.out.println(y);
				ResultSet a1 = executeQuery("SELECT nia, fecha_alta, fecha_baja, id_fechas, curso, beca, tipo_usuario, "+
						"asiduo, autorizados_lunes_martes, autorizados_jueves"+
						" FROM fechas_altas_bajas where nia='"+x.getString(1)+"'and curso='"+curso+"' and tipo_usuario='"+dia+"'");

				boolean n = false;
				while(a1.next()){
					if(a1.getString(8).equalsIgnoreCase("Si")){
						n = true;
						break;
					}else
					{
						break;
					}
				}
				if(n){

					Persona alumno=new Persona();
					alumno.setFoto(x.getBytes(6));
					alumno.setNia(x.getString(1));
					alumno.setEmail(x.getString(9));
					alumno.setNombres(x.getString(5));
					alumno.setApellidos(x.getString(3)+" "+x.getString(4));
					alumno.setFechaNacimiento(x.getString(7));
					alumno.setTelefono(x.getString(8));
					ResultSet a =executeQuery("select nombre,apellido1,apellido2 from familiars where parentesco='1' " +
							"and alumno='"+x.getString(1)+"' and curso='"+curso+"'");
					String madre="";
					while(a.next()){
						madre=a.getString(2)+" "+a.getString(3)+", "+a.getString(1); 
					}
					ResultSet b =executeQuery("select nombre,apellido1,apellido2 from familiars where parentesco='2' " +
							"and alumno='"+x.getString(1)+"' and curso='"+curso+"'");
					String padre="";
					while(b.next()){
						padre=b.getString(2)+" "+b.getString(3)+", "+b.getString(1); 
					}
					alumno.setMadre(madre);
					alumno.setPadre(padre);
					alumnos.add(alumno);

				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return alumnos;

	}

	public ArrayList<Persona> getNoUsuariosComedor2(String curso, String nMes) {
		// TODO Auto-generated method stub
		ArrayList<Persona> alumnos=new ArrayList<>();
		try{
			ResultSet x = executeQuery("select nia,documento,apellido1,apellido2,nombres,foto,fecha_nacimiento," +
					"telefono1,email,grupo from alumnos where curso='"+curso+"' order by apellido1");
			while(x.next()){
				//				System.out.println(y);

				ResultSet a1 = executeQuery("SELECT nia, fecha_alta, fecha_baja, id_fechas, curso, beca, tipo_usuario, "+
						"asiduo, autorizados_lunes_martes, autorizados_jueves, permiso_salida"+
						" FROM fechas_altas_bajas where nia='"+x.getString(1)+"'and curso='"+curso+"' and ");

				boolean n = false;
				while(a1.next()){
					String fecha=a1.getString(2).split("-")[1];
					if(fecha.equalsIgnoreCase(nMes)&&a1.getString(8).equalsIgnoreCase("No")&&a1.getString(11).equalsIgnoreCase("Si")){
						n = true;
						break;
					}else
					{
						break;
					}
				}
				if(n){
					if(x.getString(10).indexOf("ESO")!=-1|| x.getString(10).indexOf("F")!=-1){
						Persona alumno=new Persona();
						alumno.setFoto(x.getBytes(6));
						alumno.setGrupo(x.getString(10));


						alumno.setNia(x.getString(1));
						alumno.setEmail(x.getString(9));
						alumno.setNombres(x.getString(5));
						alumno.setApellidos(x.getString(3)+" "+x.getString(4));
						alumno.setFechaNacimiento(x.getString(7));
						alumno.setTelefono(x.getString(8));
						ResultSet a =executeQuery("select nombre,apellido1,apellido2 from familiars where parentesco='1' " +
								"and alumno='"+x.getString(1)+"' and curso='"+curso+"'");
						String madre="";
						while(a.next()){
							madre=a.getString(2)+" "+a.getString(3)+", "+a.getString(1); 
						}
						ResultSet b =executeQuery("select nombre,apellido1,apellido2 from familiars where parentesco='2' " +
								"and alumno='"+x.getString(1)+"' and curso='"+curso+"'");
						String padre="";
						while(b.next()){
							padre=b.getString(2)+" "+b.getString(3)+", "+b.getString(1); 
						}
						alumno.setMadre(madre);
						alumno.setPadre(padre);
						alumnos.add(alumno);
					}

				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return alumnos;

	}

	public ArrayList<Persona> getNoUsuariosComedor(String curso) {
		// TODO Auto-generated method stub
		ArrayList<Persona> alumnos=new ArrayList<>();
		try{
			ResultSet x = executeQuery("select nia,documento,apellido1,apellido2,nombres,foto,fecha_nacimiento," +
					"telefono1,email,grupo from alumnos where curso='"+curso+"' order by apellido1");
			while(x.next()){
				//				System.out.println(y);

				ResultSet a1 = executeQuery("SELECT nia, fecha_alta, fecha_baja, id_fechas, curso, beca, tipo_usuario, "+
						"asiduo, autorizados_lunes_martes, autorizados_jueves"+
						" FROM fechas_altas_bajas where nia='"+x.getString(1)+"'and curso='"+curso+"'");


				boolean n = false;
				while(a1.next()){
					if(a1.getString(8).equalsIgnoreCase("No")){
						n = true;
						break;
					}else
					{
						break;
					}
				}
				if(n){

					Persona alumno=new Persona();
					alumno.setFoto(x.getBytes(6));
					alumno.setNia(x.getString(1));
					alumno.setEmail(x.getString(9));
					alumno.setNombres(x.getString(5));
					alumno.setApellidos(x.getString(3)+" "+x.getString(4));
					alumno.setFechaNacimiento(x.getString(7));
					alumno.setTelefono(x.getString(8));
					ResultSet a =executeQuery("select nombre,apellido1,apellido2 from familiars where parentesco='1' " +
							"and alumno='"+x.getString(1)+"' and curso='"+curso+"'");
					String madre="";
					while(a.next()){
						madre=a.getString(2)+" "+a.getString(3)+", "+a.getString(1); 
					}
					ResultSet b =executeQuery("select nombre,apellido1,apellido2 from familiars where parentesco='2' " +
							"and alumno='"+x.getString(1)+"' and curso='"+curso+"'");
					String padre="";
					while(b.next()){
						padre=b.getString(2)+" "+b.getString(3)+", "+b.getString(1); 
					}
					alumno.setMadre(madre);
					alumno.setPadre(padre);
					alumnos.add(alumno);

				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return alumnos;

	}
	public ArrayList<Persona> getProfesores(String curso) {
		// TODO Auto-generated method stub
		ArrayList<Persona> alumnos=new ArrayList<>();
		try{

			ResultSet x = executeQuery("select documento,apellido1,apellido2,nombre,foto from profes where curso='"+curso+"' order by apellido1");
			while(x.next()){
				Persona alumno=new Persona();
				alumno.setFoto(x.getBytes(5));
				//				alumno.setNia(x.getString(1));
				alumno.setDocumento(x.getString(1));
				alumno.setNombres(x.getString(4));
				alumno.setApellidos(x.getString(2)+" "+x.getString(3));
				if(new File(new File(".")
				.getAbsolutePath().substring(0,
						new File(".").getAbsolutePath().length() - 2).toString()+""+File.separator+"System-Comedor"
						+File.separator+"Fotos"+File.separator+""+x.getString(1)+".jpg").exists()){
					alumno.setFotoVerificada(true);
				}else{
					alumno.setFotoVerificada(false);
				}

				alumnos.add(alumno);
				//				table.addRow(x.getString(1),x.getString(2),x.getString(3)+" "+x.getString(4)+", "+x.getString(5));
				//				label.setText(""+table.getRowCount());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return alumnos;

	}
	
	public Persona getProfesor(String documento) 
	{
		try
		{
			ResultSet x = executeQuery("SELECT documento, apellido1, apellido2, nombre, foto FROM profes "
					+ "WHERE curso = '" + getCursoActual() + "' AND documento = '" + documento + "' ORDER BY apellido1");
			
			while(x.next())
			{
				Persona alumno = new Persona();
				alumno.setFoto(x.getBytes("foto"));
				alumno.setDocumento(x.getString("documento"));
				alumno.setNombres(x.getString("nombre"));
				alumno.setApellidos(x.getString("apellido1") + " " + x.getString("apellido2"));
				
				if(new File(new File(".").getAbsolutePath().substring(0, new File(".").getAbsolutePath().length() - 2).toString() + "" + File.separator + "System-Comedor" + File.separator + "Fotos" + File.separator + "" + x.getString("documento") + ".jpg").exists())
					alumno.setFotoVerificada(true);
				else
					alumno.setFotoVerificada(false);
				
				return alumno;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Persona> getPas(String curso) {
		// TODO Auto-generated method stub
		ArrayList<Persona> alumnos=new ArrayList<>();
		try{

			ResultSet x = executeQuery("select documento,apellido1,apellido2,nombre,foto from pas where curso='"+curso+"' order by apellido1");
			while(x.next()){
				Persona alumno=new Persona();
				alumno.setFoto(x.getBytes(5));
				//				alumno.setNia(x.getString(1));
				alumno.setDocumento(x.getString(1));
				alumno.setNombres(x.getString(4));
				alumno.setApellidos(x.getString(2)+" "+x.getString(3));
				alumnos.add(alumno);
				//				table.addRow(x.getString(1),x.getString(2),x.getString(3)+" "+x.getString(4)+", "+x.getString(5));
				//				label.setText(""+table.getRowCount());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return alumnos;

	}

	public Persona getPasS(String documento) 
	{
		try
		{
			ResultSet x = executeQuery("SELECT documento, apellido1, apellido2, nombre, foto FROM pas " 
					+ "WHERE curso = '" + getCursoActual() + "' AND documento = '" + documento + "' ORDER BY apellido1");
			
			while(x.next())
			{
				Persona alumno = new Persona();
				alumno.setFoto(x.getBytes(5));

				if(new File(new File(".").getAbsolutePath().substring(0, new File(".").getAbsolutePath().length() - 2).toString() + "" + File.separator + "System-Comedor" + File.separator + "Fotos" + File.separator + "" + x.getString("documento") + ".jpg").exists())
					alumno.setFotoVerificada(true);
				else
					alumno.setFotoVerificada(false);
				
				alumno.setDocumento(x.getString("documento"));
				alumno.setNombres(x.getString("nombre"));
				alumno.setApellidos(x.getString("apellido1") + " " + x.getString("apellido2"));
				return alumno;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public String getCentro() {
		// TODO Auto-generated method stub
		try{
			ResultSet x = executeQuery("select denominacion from centro");
			while(x.next()){
				return x.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "Sin Definir";
	}
	public String getCodigoCentro() {
		// TODO Auto-generated method stub
		try{
			ResultSet x = executeQuery("select codigo_centro from centro");
			while(x.next()){
				return x.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "Sin Definir";
	}
	
	public String getCursoActual() 
	{
		try
		{
			ResultSet x = executeQuery("SELECT * FROM cursos WHERE estado = 'Activo'");
			String g = "";
			
			while(x.next())
				g = x.getString(2);
			
			return g;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return "Sin Definir";
	}
	public String getIDCursoActual() {
		try{
			ResultSet x =executeQuery("select id_curso from cursos where estado='Activo'");
			String g="";
			while(x.next()){
				g=x.getString(1);
			}
			return g;
		}catch(Exception e){
			e.printStackTrace();
		}
		return "Sin Definir";
	}

	public void insertFotos(String path, JTextArea area, final JDialog dialog,JProgressBar progressBar) {
		File directory = new File(path);
		File[] arrayFiles = directory.listFiles();
		int y = 0;
		int y1=0;
		//		System.out.println(directory);
		for (int index = 0; index < arrayFiles.length; index++) {
			File file = arrayFiles[index];
			String fileName = file.getName();
			if (fileName.indexOf("jpg") != -1 || fileName.indexOf("png") != -1
					|| fileName.indexOf("JPG") != -1
					|| fileName.indexOf("PNG") != -1) {
				FileInputStream fileInputStream = null;
				y1++;
				area.append("Foto Insertada: " + fileName + "->"
						+ file.getPath() + "\n");
				area.repaint();dialog.repaint();
				File file2=null;
				try {
					FileInputStream fileInputStream1 = new FileInputStream(file);
					//						System.out.println();
//					resize(fileInputStream1, new FileOutputStream(file.getParentFile()+File.separator+"T"+file.getName()), 480, 640,file);
					fileInputStream1.close();
					file2=new File(file.getParentFile()+File.separator+"T"+file.getName());
					fileInputStream = new FileInputStream(file2);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					if (fileInputStream != null) {

						ResultSet x1=executeQuery("select * from alumnos WHERE nia='"
								+ fileName.substring(0,
										fileName.lastIndexOf(".")) + "' or documento= '"+ fileName.substring(0,
												fileName.lastIndexOf(".")) +"';");
						boolean n= false;
						while(x1.next()){
							n = true;
						}

						ResultSet x12=executeQuery("select * from profes WHERE documento= '"+ fileName.substring(0,
								fileName.lastIndexOf(".")) +"';");

						//						System.out.println("select * from profes WHERE documento= '"+ fileName.substring(0,
						//												fileName.lastIndexOf(".")) +"';");
						boolean n12= false;
						while(x12.next()){
							n12 = true;
						}

						int x=0;
						if(n){
							PreparedStatement preparedStatement = preparedStatement("UPDATE alumnos SET foto=? WHERE nia='"
									+ fileName.substring(0,
											fileName.lastIndexOf(".")) + "' or documento= '"+ fileName.substring(0,
													fileName.lastIndexOf(".")) +"';");
							final int FILE_SIZE = (int) file2.length();

							preparedStatement.setBinaryStream(1, fileInputStream,
									FILE_SIZE);


							x=preparedStatement.executeUpdate();
						}else{

							if(n12){
								PreparedStatement preparedStatement = preparedStatement("UPDATE profes SET foto=? WHERE documento= '"+ fileName.substring(0,
										fileName.lastIndexOf(".")) +"';");
								//								System.out.println("UPDATE profes SET foto=? WHERE documento= '"+ fileName.substring(0,
								//														fileName.lastIndexOf(".")) +"';");
								final int FILE_SIZE = (int) file2.length();

								preparedStatement.setBinaryStream(1, fileInputStream,
										FILE_SIZE);


								x=preparedStatement.executeUpdate();
								//								System.out.println(x);
							}

						}

						if (x >=1) {
							area.append("Foto Insertada: " + fileName + "->"
									+ file.getPath() + "\n");
							area.repaint();dialog.repaint();
							try {
								fileInputStream.close();
								file2.delete();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							y++;
						} else {
							area.append("---------->Foto No Insertada: "
									+ fileName + "\n");
							area.repaint();dialog.repaint();
							try {
								fileInputStream.close();
								file2.delete();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
					area.append("---------->Foto No Insertada: " + fileName
							+ "\n");
					area.repaint();
					dialog.repaint();
				}
				dialog.repaint();
			}

		}

		area.append("\n[ FINALIZADO ] \n\n");
		area.append("[ Total de Fotos ] -> " + y1 + "\n");
		area.append("[ Cantidad de Fotos Guardadas ] -> " + y + "\n");
		area.repaint();
		progressBar.setIndeterminate(false);
		progressBar.setString("Finalizado");
	}
	
//	public void resize(FileInputStream fileInputStream, FileOutputStream fileOutputStream, int width, int height, File file) throws Exception 
//	{
//		System.out.println("Entre");
//		BufferedImage src = ImageIO.read(fileInputStream);
////		BufferedImage dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//		
//		src = src.getSubimage((src.getWidth() / 2) - 320, (src.getHeight() / 2) - 240, 640, 480);
//		
//		
////		Graphics2D g = dest.createGraphics();
////		AffineTransform at = AffineTransform.getScaleInstance((double)width / src.getWidth(), (double)height / src.getHeight());
////		g.drawRenderedImage(src, at);
//		ImageIO.write(src, "JPG", fileOutputStream);
//
//		FileOutputStream outputStream=new FileOutputStream(new File("System-Comedor"+File.separator+"Fotos"+File.separator+file.getName()));
//		ImageIO.write(src, "JPG", outputStream);
//		outputStream.flush();
//		fileOutputStream.close();
//
//	}
	public void resize2(FileInputStream fileInputStream, 
			FileOutputStream fileOutputStream, int width, int height, File file,String n) throws Exception {
		BufferedImage src = ImageIO.read(fileInputStream);
		BufferedImage dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = dest.createGraphics();
		AffineTransform at = AffineTransform.getScaleInstance((double)width / src.getWidth(), (double)height / src.getHeight());
		g.drawRenderedImage(src, at);
		ImageIO.write(dest, "JPG", fileOutputStream);

		FileOutputStream outputStream=new FileOutputStream(new File("System-Comedor"+File.separator+"Fotos"+File.separator+n));
		ImageIO.write(dest, "JPG", outputStream);
		outputStream.flush();
		fileOutputStream.close();

	}
	
	public void insertFotos(File file) 
	{
		String fileName = file.getName();
	
		if (fileName.indexOf("jpg") != -1 || fileName.indexOf("png") != -1 || fileName.indexOf("JPG") != -1 || fileName.indexOf("PNG") != -1) 
		{
			File file2 = null;
			
			try 
			{
				FileInputStream fileInputStream1 = new FileInputStream(file);

//				resize(fileInputStream1, new FileOutputStream(file.getParentFile() + File.separator + "T" + file.getName()), 480, 640, file);
				fileInputStream1.close();
				file2 = new File(file.getParentFile() + File.separator + "T" + file.getName());
				file2.delete();
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public void insertFotos2(File file,String nia) {

		String fileName = nia+".jpg";
		if (fileName.indexOf("jpg") != -1 || fileName.indexOf("png") != -1
				|| fileName.indexOf("JPG") != -1
				|| fileName.indexOf("PNG") != -1) {
			//			FileInputStream fileInputStream = null;
			File file2=null;
			try {
				FileInputStream fileInputStream1 = new FileInputStream(file);
				//					System.out.println();
				resize2(fileInputStream1, new FileOutputStream(file.getParentFile()+File.separator+"T"+fileName), 480, 640,file,fileName);
				fileInputStream1.close();
				file2=new File(file.getParentFile()+File.separator+"T"+fileName);
				//					fileInputStream = new FileInputStream(file2);
				file2.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	public Persona getAlumno(String nia) {
		// TODO Auto-generated method stub

		try{
			String curso=principal.getBaseDeDatos().getCursoActual();
			ResultSet x = executeQuery("select nia,documento,apellido1,apellido2,nombres,foto,grupo,fecha_nacimiento from alumnos where nia='"+nia+"' and curso='"+curso+"'");
			while(x.next()){
				Persona alumno=new Persona();
				alumno.setFoto(x.getBytes(6));
				if(new File(new File(".")
				.getAbsolutePath().substring(0,
						new File(".").getAbsolutePath().length() - 2).toString()+""+File.separator+"System-Comedor"
						+File.separator+"Fotos"+File.separator+""+x.getString(1)+".jpg").exists()){
					alumno.setFotoVerificada(true);
				}else{
					alumno.setFotoVerificada(false);
				}
				alumno.setNia(x.getString(1));
				alumno.setNombres(x.getString(5));
				alumno.setGrupo(x.getString(7));
				alumno.setFechaNacimiento(x.getString(8));
				alumno.setDocumento(x.getString(2));
				alumno.setApellidos(x.getString(3)+" "+x.getString(4));
				return alumno;
			}
		}catch
		(Exception e){

		}
		return null;
	}
	public void exportFotos(String path, String code) {
		// TODO Auto-generated method stub
		try{
			File file=new File(path);
			if(!file.exists())
				file.mkdir();
			String curso=principal.getBaseDeDatos().getCursoActual();
			ResultSet x =executeQuery("select foto,nia from alumnos where grupo='"+code+"' and curso='"+curso+"'");
			while(x.next()){
				if(x.getBytes(1)!=null){
					FileOutputStream fileOutputStream=new FileOutputStream(path+File.separator+""+x.getString(2)+".jpg");
					fileOutputStream.write(x.getBytes(1));
					fileOutputStream.flush();
					fileOutputStream.close();
				}

			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public boolean addFechas(Date getfechaAlta, Date getfechaBaja, String nia, String beca, String dias, String asiduo, String marteslunes, String jueves, String salida) {
		// TODO Auto-generated method stub
		try{
			
			System.out.println("SALIDA: " + salida);
			
			String curso=principal.getBaseDeDatos().getCursoActual();
			ResultSet x = executeQuery("SELECT nia, fecha_alta, fecha_baja, id_fechas, permiso_salida FROM fechas_altas_bajas where nia ='"+nia+"' " +
					"and fecha_alta='"+getfechaAlta+"' and fecha_baja='"+getfechaBaja+"' and curso='"+curso+"'");
			while(x.next()){
				return true;
			}
			String pagox="";
			if(beca.indexOf("100")!=-1){
				pagox="100";
			}else
				if(beca.indexOf("70")!=-1){
					pagox="70";
				}else{
					pagox="0";
				}
						System.out.println(asiduo);
			PreparedStatement p = preparedStatement("INSERT INTO fechas_altas_bajas(nia, fecha_alta, " +
					"fecha_baja,curso,beca,tipo_usuario,asiduo,autorizados_lunes_martes,autorizados_jueves,documento,permiso_salida)VALUES (?, ?, ?,?,?,?,?,?,?,?,?);");
			p.setString(1, nia);
			p.setDate(2, getfechaAlta);
			p.setDate(3, getfechaBaja);
			p.setString(4, curso);
			p.setString(5, pagox);
			p.setString(6, dias);
			p.setString(7, (asiduo.equalsIgnoreCase("si")?"Si":"No"));
			p.setString(8, marteslunes);
			p.setString(9, jueves);
			p.setString(10, getDocumento(nia));
			p.setString(11, salida);


			if(p.executeUpdate()==1){
				ResultSet m = executeQuery("SELECT id_fechas FROM fechas_altas_bajas order by id_fechas desc limit 1");
				String id="";
				while(m.next()){
					id=m.getString(1);
				}
				if(dias.startsWith("3")){
					generarRemesasUsuarios(nia, curso,id);
				}else{
					generarRemesas2Dias(nia, curso,id);
				}
				return true;
			}


		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	public void getFechasPeriodo(String nia, JKTable tableFechas,ArrayList<String> list) {
		// TODO Auto-generated method stub
		try{
			try{
				tableFechas.clearTable();
				list.clear();
			}catch(Exception e){
				e.printStackTrace();
			}
			String curso=principal.getBaseDeDatos().getCursoActual();
			ResultSet x=executeQuery("SELECT nia, fecha_alta, fecha_baja, id_fechas,beca,tipo_usuario FROM fechas_altas_bajas where nia='"+nia+"' and curso='"+curso+"'");
			while(x.next()){

				tableFechas.addRow(getFechaHumana(x.getDate(2)),getFechaHumana(x.getDate(3)),x.getString(5)+"%",x.getString(6)+" Dias");
				list.add(x.getString(4));
			}
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	public java.sql.Date getFechaAlta(String id) {
		// TODO Auto-generated method stub
		try{
			ResultSet x=executeQuery("SELECT fecha_alta  FROM fechas_altas_bajas where id_fechas='"+id+"'");
			while(x.next()){
				return x.getDate(1);
			}
		}catch(Exception e){	
			e.printStackTrace();
		}
		return null;
	}
	public boolean updateFechas(Date alta,Date baja,String id){
		try{
			PreparedStatement p=preparedStatement("UPDATE fechas_altas_bajas  SET fecha_alta=?, fecha_baja=?  WHERE id_fechas='"+id+"'");
			p.setDate(1, alta);
			p.setDate(2, baja);
			int t=p.executeUpdate();
			if(t==1){
				return true;
			}else return false;

		}catch
		(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	public java.sql.Date getFechaBaja(String id) {
		// TODO Auto-generated method stub
		try{
			ResultSet x=executeQuery("SELECT fecha_baja  FROM fechas_altas_bajas where id_fechas='"+id+"'");
			while(x.next()){
				return x.getDate(1);
			}
		}catch(Exception e){	
			e.printStackTrace();
		}
		return null;
	}
	public void addPago(String nia, String pago) {
		try{
			String curso=principal.getBaseDeDatos().getCursoActual();
			if(pago.indexOf("100")!=-1){
				executeUpdate("UPDATE estatus_alumno SET beca='100' WHERE nia='"+nia+"' and curso='"+curso+"'");
			}else
				if(pago.indexOf("70")!=-1){
					executeUpdate("UPDATE estatus_alumno SET beca='70' WHERE nia='"+nia+"' and curso='"+curso+"'");
				}else{
					executeUpdate("UPDATE estatus_alumno SET beca='0' WHERE nia='"+nia+"' and curso='"+curso+"'");
				}

		}catch(Exception e){
			e.printStackTrace();
		}

	}

	public void updateAlumnoAS(String nia, String string) 
	{
		try
		{	
			String curso = principal.getBaseDeDatos().getCursoActual();
			System.out.println("UPDATE estatus_alumno SET usuario_comedor='" + string + "' WHERE nia = '" + nia + "' and curso='" + curso + "'");
			executeUpdate("UPDATE estatus_alumno SET usuario_comedor='" + string + "' WHERE nia = '" + nia + "' and curso='" + curso + "'");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public Boolean getSelectedLunesMartes(String nia) {
		try{
			String curso=principal.getBaseDeDatos().getCursoActual();
			ResultSet x = executeQuery("select autorizados_lunes_martes from fechas_altas_bajas where nia='"+nia+"' and curso='"+curso+"'");
			while(x.next()){

				String n=x.getString(1);
				if(n.startsWith("No")){
					return Boolean.FALSE;
				}else{
					return Boolean.TRUE;
				}
			}
		}catch(Exception e){
			e.printStackTrace();

		}
		return Boolean.FALSE;
	}
	public Boolean getSelectedJueves(String nia) {
		try{
			String curso=principal.getBaseDeDatos().getCursoActual();
			ResultSet x = executeQuery("select autorizados_jueves from fechas_altas_bajas where  nia='"+nia+"' and curso='"+curso+"'");
			while(x.next()){

				String n=x.getString(1);
				if(n.startsWith("No")){
					return Boolean.FALSE;
				}else{
					return Boolean.TRUE;
				}
			}
		}catch(Exception e){
			e.printStackTrace();

		}
		return Boolean.FALSE;
	}
	public String getComedorLunesMartes() {
		// TODO Auto-generated method stub
		try{
			String curso=principal.getBaseDeDatos().getCursoActual();
			ResultSet x = executeQuery("SELECT count(*) FROM fechas_altas_bajas where autorizados_lunes_martes='Si' and curso='"+curso+"';");
			while(x.next()){
				return x.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "0";
	}
	public String getComedorJueves() {
		try{
			String curso=principal.getBaseDeDatos().getCursoActual();
			ResultSet x = executeQuery("SELECT count(*) FROM fechas_altas_bajas where autorizados_jueves='Si' and curso='"+curso+"';");
			while(x.next()){
				return x.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "0";
	}
	public void autorizadoComedorLunesMartes(String nia, boolean b) {
		// TODO Auto-generated method stub
		try{
			String curso=principal.getBaseDeDatos().getCursoActual();
			executeUpdate("UPDATE fechas_altas_bajas SET  autorizados_lunes_martes='"+((b)?"Si":"No")+"' WHERE nia='"+nia+"' and curso='"+curso+"'");
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	public void autorizadoComedorJueves(String nia, boolean b) {
		// TODO Auto-generated method stub
		try{
			String curso=principal.getBaseDeDatos().getCursoActual();
			executeUpdate("UPDATE fechas_altas_bajas SET  autorizados_jueves='"+((b)?"Si":"No")+"' WHERE nia='"+nia+"' and curso='"+curso+"'");
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	public String getNoAutorizadosJueves() {
		try{
			String curso=principal.getBaseDeDatos().getCursoActual();
			ResultSet x = executeQuery("SELECT count(*) FROM fechas_altas_bajas where autorizados_jueves='No' and curso='"+curso+"'");
			while(x.next()){
				return x.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "0";
	}

	public String getNoAutorizadosLunesMartes() {
		try{
			String curso=principal.getBaseDeDatos().getCursoActual();
			ResultSet x = executeQuery("SELECT count(*) FROM fechas_altas_bajas where autorizados_lunes_martes='No' and curso='"+curso+"'");
			while(x.next()){
				return x.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "0";
	}

	public synchronized boolean verificarSiEstaAutorizadoLunesMartes(String g) 
	{
		try
		{
			String curso = principal.getBaseDeDatos().getCursoActual();

			/** AJUSTE A QUERY PARA TAMBIEN TRAER LOS ESTUDIANTES AUTORIZADOS LOS DIAS MARTES**/
			ResultSet x = executeQuery("SELECT autorizados_lunes_martes FROM fechas_altas_bajas WHERE (nia = '" + g + "' OR documento = '" + g + "') AND curso='" + curso + "'");

			while(x.next())
			{
				String j1= x.getString("autorizados_lunes_martes");

				if(j1.equalsIgnoreCase("Si"))
					return true;
				else
					return false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public synchronized boolean verificarSiEstaAutorizadoJueves(String g)
	{
		try
		{
			String curso=principal.getBaseDeDatos().getCursoActual();

			/** AJUSTE A QUERY PARA TAMBIEN TRAER LOS ESTUDIANTES AUTORIZADOS LOS DIAS MARTES**/
			ResultSet x =executeQuery("SELECT autorizados_jueves FROM fechas_altas_bajas WHERE (nia = '" + g + "' or documento = '" + g + "') AND curso = '" + curso + "'");

			while(x.next())
			{
				String j1 = x.getString("autorizados_jueves");

				if(j1.equalsIgnoreCase("Si"))
					return true;
				else
					return false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	/** SE AGREGA FUNCION PARA VERIFICAR QUE EL USUARIO NO HA PASADO ANTES EL MISMO DIA **/
	public synchronized boolean verificarAsistencia(String g) 
	{
		try
		{
			String curso = principal.getBaseDeDatos().getCursoActual();

			ResultSet x = executeQuery("SELECT count(*) FROM asistencias " 
					+ "WHERE (nia='" + g + "' or documento='" + g + "') AND curso='" + curso + "' AND fecha = '" + new Date(new java.util.Date().getTime()) + "';");

			System.out.println("SELECT count(*) FROM asistencias " 
					+ "WHERE (nia='" + g + "' or documento='" + g + "') AND curso='" + curso + "' AND fecha = '" + new Date(new java.util.Date().getTime()) + "';");

			while(x.next())
				return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return false;
	}
	public synchronized boolean verificarSiEsUsuarioComedor(String g) {
		try{
			String curso=principal.getBaseDeDatos().getCursoActual();
			ResultSet x = executeQuery("SELECT asiduo FROM fechas_altas_bajas WHERE nia = '" + g + "' AND curso = '" + curso 
					+ "' AND nia NOT IN(SELECT nia FROM asistencias where nia = '" + g + "' and fecha = '" + new Date(new java.util.Date().getTime()) + "');");

			//select asiduo from fechas_altas_bajas where nia='"+g+"' and curso='"+curso+"'");
			while(x.next()){
				if(x.getString(1).equalsIgnoreCase("si"))
					return true;
				else
					return false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return false;
	}
	public String getHora(){
		Calendar calendario = new GregorianCalendar();
		int hora = calendario.get(Calendar.HOUR_OF_DAY);
		int minutos = calendario.get(Calendar.MINUTE);
		int segundos = calendario.get(Calendar.SECOND);
		String stringHora=(hora + ":" + minutos + ":" + segundos);
		return stringHora;
	}
	public void addAsistencia(String g,String ticked) {
		// TODO Auto-generated method stub
		try{
			SimpleDateFormat dateFormat=new SimpleDateFormat("EEEEE");
			String dia="";
			//			System.out.println(getBaseDeDatos());
			try {
				dia=dateFormat.format(new java.util.Date());
			}catch(Exception e){
				e.printStackTrace();
			}
			executeUpdate("INSERT INTO asistencias(nia, documento, fecha, hora, curso,dia,ticked) VALUES ('"+g+"','"+principal.getBaseDeDatos().getDocumento(g)+"'," +
					" '"+new Date(new java.util.Date().getTime())+"', '"+getHora()+"','"+getCursoActual()+"','"+dia+"','"+ticked+"');");
		}catch
		(Exception e){
			e.printStackTrace();
		}

	}
	public String getDocumento(String nia) {
		try{
			ResultSet x = executeQuery("select documento  from alumnos where nia='"+nia+"' and curso='"+getCursoActual()+"'");
			while(x.next()){
				return x.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	public String getnAsistenciasDia() {
		// TODO Auto-generated method stub
		try{
			ResultSet x= executeQuery("select count(*) from asistencias  where curso='"+getCursoActual()+"' and fecha='"+new Date(new java.util.Date().getTime())+"'");
			while(x.next()){
				return x.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "0";
	}
	
	public String getImpresoraCarnet()
	{
		try
		{
			ResultSet x =executeQuery("SELECT impresora FROM impresoras WHERE id_impresora = '2'");
			
			while(x.next())
				return x.getString("impresora");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "";
	}
	
	public String getImpresoraEstandar()
	{
		try
		{
			ResultSet x = executeQuery("SELECT impresora FROM impresoras WHERE id_impresora = '1'");
	
			while(x.next())
				return x.getString("impresora");
	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "";
	}
	
	public void setImpresoraCarnet(String name) {
		// TODO Auto-generated method stub
		try{
			executeUpdate("UPDATE impresoras  SET impresora='"+name+"' WHERE id_impresora='2'");
		}catch(Exception e ){
			e.printStackTrace();
		}
	}
	public void setImpresoraEstandar(String name) {
		try{
			executeUpdate("UPDATE impresoras SET impresora='"+name+"' WHERE id_impresora='1'");
		}catch(Exception e ){
			e.printStackTrace();
		}

	}
	public boolean isImpresoraCarnetConfigurada() {
		// TODO Auto-generated method stub
		try{
			ResultSet x =executeQuery("select impresora from impresoras where id_impresora='2'");
			while(x.next()){
				if(x.getString(1).equalsIgnoreCase("sin definir")){
					return false;
				}else{
					return true;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	private boolean l=false;
	public void getCursos(JMenu menu) {
		// TODO Auto-generated method stub

		try{
			ButtonGroup buttonGroup=new ButtonGroup();
			final ResultSet x = executeQuery("select curso,estado from cursos");

			while(x.next()){
				if(x.getString(2).equalsIgnoreCase("Activo")){
					JRadioButton button=new JRadioButton("Curso Actual > "+x.getString(1));
					button.setSelected(true);
					buttonGroup.add(button);
					menu.add(button);
					l=true;
					final String j=x.getString(1);
					button.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							// TODO Auto-generated method stub
							try {
								String g=j;
								executeUpdate("UPDATE cursos SET estado='Cerrado'");
								executeUpdate("UPDATE cursos set estado='Activo' where curso='"+g+"'");

							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
				}else{
					JRadioButton button=new JRadioButton("Curso > "+x.getString(1));
					button.setSelected(false);
					buttonGroup.add(button);
					menu.add(button);

					final String j=x.getString(1);
					button.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							// TODO Auto-generated method stub
							try {
								String g=j;

								executeUpdate("UPDATE cursos  SET estado='Cerrado'");
								executeUpdate("UPDATE cursos set estado='Activo' where curso='"+g+"'");

							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});


				}
			}
			//			System.out.println(l);
			if(l){
				JMenuItem item=new JMenuItem("Configuracion del Curso Actual");
				//				item.setFont(new Font("tahoma",Font.BOLD,12));
				item.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						ActualizarCurso actualizarCurso=new ActualizarCurso(principal, getCursoActual());
						principal.getDesktop().add(actualizarCurso);
						actualizarCurso.setVisible(true);
					}
				});
				menu.add(new JSeparator());
				menu.add(item);

			}
			//			menu.repaint();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void eliminarPeriodoComedor(String id) {
		try{
			executeUpdate("DELETE FROM fechas_altas_bajas WHERE id_fechas='"+id+"'");
		}catch(Exception e){
			e.printStackTrace();
		}
		// TODO Auto-generated method stub

	}
	public void getDatosPeriodo(String id, NuevoPeriodoComedor nuevoPeridoComedor) {
		// TODO Auto-generated method stub
		try{
			ResultSet x = executeQuery("SELECT nia, fecha_alta, fecha_baja, id_fechas, curso, beca, tipo_usuario,  asiduo, permiso_salida FROM fechas_altas_bajas where id_fechas='"+id+"'");
			while(x.next()){
				nuevoPeridoComedor.setFechAlta(x.getString(2));
				nuevoPeridoComedor.setFechBaja(x.getString(3));
				//				System.out.println("MSAKMDS");
				nuevoPeridoComedor.setBeca(x.getString(6));
				nuevoPeridoComedor.setAsiduo(x.getString(8));
				nuevoPeridoComedor.setTipoUsuario(x.getString(7));
				nuevoPeridoComedor.setSalida(x.getString(9));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public String getDatosConfiguracionEmail(){
		try{
			ResultSet x = executeQuery("SELECT id_admin, email, password FROM email_administrador;");
			while(x.next()){
				return x.getString(2)+"@1"+x.getString(3);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	public  void sendEmail(final String subject, final String correo,final String EMAIL,final String PASSWORD,final String msg,final JProgressBar progressBar, final ConfigurarEmail configurarEmail) {
		new Thread(new Runnable() {
			@Override
			public void run() {


				Properties props = new Properties();
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.socketFactory.port", "465");
				props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.port", "465");
				Session session = Session.getDefaultInstance(props);

				//				PrintStream out = null;
				//				try {
				//					out = new PrintStream(new File("Temporal/logEmail.txt"));
				//				} catch (FileNotFoundException e) {
				//					e.printStackTrace();
				//				}
				session.setDebugOut(System.out);
				session.setDebug(true);
				MimeMessage message = new MimeMessage(session);

				BodyPart messageBodyPart = new MimeBodyPart();


				try {
					//					message.setText(msg, "utf-8", "html");
					message.setSubject(subject);
					// message.setText(msg);
					Address address = new InternetAddress("Software_de_Gestion_y_Administracion_de_Comedor");
					message.setFrom(address);
					Address address2 = new InternetAddress(correo, false);
					message.addRecipient(Message.RecipientType.TO, address2);
					messageBodyPart.setText(msg);


					Multipart multipart = new MimeMultipart();


					multipart.addBodyPart(messageBodyPart);


					//			         messageBodyPart = new MimeBodyPart();
					//			         String filename = "G:\\129.rar";
					//			         DataSource source = new FileDataSource(filename);
					//			         messageBodyPart.setDataHandler(new DataHandler(source));
					//			         messageBodyPart.setFileName(filename);
					//			         multipart.addBodyPart(messageBodyPart);


					message.setContent(multipart);

					progressBar.setString("Probando Conexion...");

					Transport t = session.getTransport("smtp");


					t.connect(EMAIL,PASSWORD);
					t.sendMessage(message, message.getAllRecipients());
					progressBar.setString("Enviando...");
					t.close();

					//					rpta = true;
					JOptionPane.showMessageDialog(principal, "Proceso Completado verifique su bandeja de Entrada.","Configuracion",JOptionPane.INFORMATION_MESSAGE);
					progressBar.setIndeterminate(false);
					progressBar.setString("Completado");
					Thread.sleep(1000);
					configurarEmail.dispose();

					executeUpdate("DELETE FROM email_administrador");
					executeUpdate("INSERT INTO email_administrador(email, password) VALUES ('"+EMAIL+"', '"+PASSWORD+"');");

				} catch (MessagingException ex) {
					// return rpta;
					System.out.println("fail");
					JOptionPane.showMessageDialog(principal, ex.getMessage(),ex.getLocalizedMessage(),JOptionPane.ERROR_MESSAGE);
					progressBar.setString("fallido");
					progressBar.setIndeterminate(false);
					configurarEmail.setEnabledButtonAceptar(true);
					ex.printStackTrace();
				} catch (Exception ex) {
					System.out.println("fail");
					JOptionPane.showMessageDialog(principal, ex.getMessage(),ex.getLocalizedMessage(),JOptionPane.ERROR_MESSAGE);
					progressBar.setString("fallido");
					configurarEmail.setEnabledButtonAceptar(true);
					progressBar.setIndeterminate(false);
				}
			}
		}).start();
	}
	public void updateRemesas(String curso, String diasDescontados) {
		// TODO Auto-generated method stub
		try{
			String sql="UPDATE cursos SET dias_descontados='"+diasDescontados+"' WHERE curso='"+curso+"'";
			if(executeUpdate(sql)){
				int dias=Integer.parseInt(diasDescontados);
				if(dias>1){
					ResultSet x =executeQuery("select valor_remesa,id_remesa from remesas where curso='"+curso+"' and nro_remesa='8' and estado='PENDIENTE'");
					while(x.next()){
						float valor=x.getFloat(1)-(dias*getPrecioMenu(curso));
						executeUpdate("UPDATE remesas SET valor_remesa='"+valor+"' WHERE id_remesa='"+x.getString(2)+"'");
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public String getTipoDeUsuario(String g) {
		// TODO Auto-generated method stub
		try{
			String curso=principal.getBaseDeDatos().getCursoActual();
			ResultSet x = executeQuery("select tipo_usuario from fechas_altas_bajas where nia='"+g+"' and curso='"+curso+"'");
			while(x.next()){
				return x.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;


	}


	public boolean verificarUsuario(String g) {
		// TODO Auto-generated method stub
		try{
			String curso=principal.getBaseDeDatos().getCursoActual();
			ResultSet x = executeQuery("select tipo_usuario from fechas_altas_bajas where nia='"+g+"' and curso='"+curso+"'");
			while(x.next()){
				if(x.getString(1).equalsIgnoreCase("2")){
					return true;
				}
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	public boolean verificarFechas(String g) 
	{
		try
		{
			String curso = principal.getBaseDeDatos().getCursoActual();

			/** CONSULTA ANIDADA PARA QUE LOS USUARIOS NO PASEN VARIAS VECES EL MISMO DIA **/
			ResultSet x = executeQuery("SELECT fecha_alta, fecha_baja FROM fechas_altas_bajas WHERE nia = '" + g + "' AND curso = '" + curso
					+ "' AND nia NOT IN(SELECT nia FROM asistencias WHERE nia = '" + g + "' AND fecha = '" + new Date(new java.util.Date().getTime()) + "');");

			while(x.next())
			{
				Date date1 = x.getDate("fecha_alta");
				Date date2 = x.getDate("fecha_baja");

				java.util.Date _date = new java.util.Date();
				Date date = new Date(_date.getTime());

				if ((date.after(date1) || date.toString().equalsIgnoreCase(date1.toString())) & ((!date.after(date2) || date.toString().equalsIgnoreCase(date2.toString())))) 
					return true;
				else
					return false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public void getRemesas(String id, JKTable tableRemesas,ArrayList<String> arrayList) {
		try{
			try{
				arrayList.clear();
				tableRemesas.clearTable();
			}catch(Exception e){

			}
			ResultSet x = executeQuery("SELECT id_remesa, nia, curso, nro_remesa, valor_remesa" +
					", estado, id_fechas,generada,valor_comision FROM remesas where id_fechas = '"+id+"'  order by nro_remesa;");
			while(x.next()){
				arrayList.add(x.getString(1));
				String estado = x.getString(6);
				JLabel jLabel = new JLabel();
				if(estado.equalsIgnoreCase("PENDIENTE")){
					jLabel.setIcon(new ImageIcon(getClass()
							.getResource("/resource/1xc.png")));
					jLabel.setText("Pendiente");
				}else{
					if(estado.equalsIgnoreCase("PAGADO")){
						jLabel.setIcon(new ImageIcon(getClass()
								.getResource("/resource/n41.png")));
						jLabel.setText("Pagado");
					}else{
						jLabel.setIcon(new ImageIcon(getClass()
								.getResource("/resource/n41.png")));
						jLabel.setText("<html><body>Pagado con Comision ("+x.getString(9)+")</body></html>");
					}
				}


				if(x.getString(8).equalsIgnoreCase("No")){
					new DecimalFormat("###.##");
					//				    arrayList.add(""+df1.format(Float.parseFloat(x.getString(5)))+"");
					ResultSet i= executeQuery("select id_remesa,valor_remesa from remesas where id_fechas='"+id+"' and nro_remesa='"+(x.getInt(4)-1)+"' and estado='PENDIENTE'");
					boolean h= false;
					float t=0;
					while(i.next()){
						String sql="UPDATE remesas "+
								" SET deuda='"+i.getFloat(2)+"' "+
								" WHERE id_remesa='"+i.getString(1)+"'";
						executeUpdate(sql);
						t=i.getFloat(2);
						h=true;
					}
					if(h){
						try{
							String sql="UPDATE remesas "+
									" SET generada='Si', valor_remesa='"+(t+x.getFloat(5))+"'"+
									" WHERE id_remesa='"+x.getString(1)+"'";
							executeUpdate(sql);
						}catch(Exception e){	
							e.printStackTrace();
						}
					}else{
						try{
							String sql="UPDATE remesas "+
									" SET generada='Si'  "+
									" WHERE id_remesa='"+x.getString(1)+"'";
							executeUpdate(sql);
						}catch(Exception e){	
							e.printStackTrace();
						}
					}
					//					tableRemesas.addRow(x.getString(4),""+df1.format( (Float.parseFloat(x.getString(5))))+"",jLabel);	
					break;
				}else{

					//					float b=0;
					if(!x.getString(4).equalsIgnoreCase("1")&&estado.equalsIgnoreCase("PENDIENTE")){
						x.getFloat(5);
					}
					DecimalFormat df1 = new DecimalFormat("###.##");
					tableRemesas.addRow(x.getString(4),""+df1.format(Float.parseFloat(x.getString(5)))+"",jLabel);	


				}
			}
		}catch(Exception e ){
			e.printStackTrace();
		}
	}
	public float getPendientes(int xc,String id){
		//		
		float a=0;
		try{
			ResultSet x = executeQuery("SELECT deuda FROM remesas " +
					"where id_fechas='"+id+"' and nro_remesa='"+(xc-1)+"' and estado='PENDIENTE';");

			//			System.out.println("SELECT id_remesa, nia, curso, nro_remesa, valor_remesa, estado, id_fechas,generada,valor_comision FROM remesas " +
			//					"where id_remesa='"+id+"' and nro_remesa = '"+(xc-1)+"';");
			while(x.next()){
				a=x.getFloat(1);
				break;

			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return a;
	}
	public void getRemesasGeneradas(String id, JKTable tableRemesas,ArrayList<String> arrayList, ArrayList<String> arrayListIDGeneradas, JKComboBox comboBox_2) {
		try{
			try{
				arrayList.clear();
				tableRemesas.clearTable();
				comboBox_2.removeAllItems();
			}catch(Exception e){

			}
			float n=0;
			ResultSet x = executeQuery("SELECT id_remesa, nia, curso, nro_remesa, valor_remesa" +
					", estado, id_fechas,generada,valor_comision FROM remesas where id_fechas = '"+id+"'  order by nro_remesa;");
			while(x.next()){
				arrayList.add(x.getString(1));
				String estado = x.getString(6);
				JLabel jLabel = new JLabel();
				if(estado.equalsIgnoreCase("PENDIENTE")){
					jLabel.setIcon(new ImageIcon(getClass()
							.getResource("/resource/1xc.png")));
					jLabel.setText("Pendiente");	
				}else{
					if(estado.equalsIgnoreCase("PAGADO")){
						jLabel.setIcon(new ImageIcon(getClass()
								.getResource("/resource/n41.png")));
						jLabel.setText("Pagado");
					}else{
						jLabel.setIcon(new ImageIcon(getClass()
								.getResource("/resource/n41.png")));
						DecimalFormat df1 = new DecimalFormat("###.##");
						jLabel.setText("<html><body>Pagado con Comision ("+df1.format(Float.parseFloat(x.getString(9)))+") > Total:"+df1.format((x.getFloat(5)+x.getFloat(9)))+" </body></html>");
					}
				}
				if(x.getString(8).equalsIgnoreCase("Si")){
					//					n+=getPendientes(Integer.parseInt(x.getString(4)), x.getString(7));
					//					System.out.println(n);
					comboBox_2.addItem(x.getString(4));
					float t=Float.parseFloat(x.getString(5));
					float devoluciones =0;
					if(x.getString(4).equalsIgnoreCase("8")){
						devoluciones=getDevolucionesTotales(x.getString(7));
					}
					arrayListIDGeneradas.add(x.getString(1));
					DecimalFormat df1 = new DecimalFormat("###.##");
					tableRemesas.addRow(x.getString(4),""+(  df1.format( (t+n) + (devoluciones) ) )+"",jLabel);
					tableRemesas.setSelectedRow(tableRemesas.getRowCount()-1);
				}
			}
		}catch(Exception e ){
			e.printStackTrace();
		}
	}
	public float getDevolucionesTotales(String id_fechas){
		float total=0;
		try{
			ResultSet x = executeQuery("select porcentaje,id_remesa from devoluciones where id_fechas='"+id_fechas+"'");

			while(x.next()){
				float porcentaje=x.getFloat(1);

				ResultSet a = executeQuery("select valor_remesa from remesas where id_remesa='"+x.getString(2)+"'");
				float valor=0;
				while(a.next()){
					valor=a.getFloat(1);
				}
				total+=(valor*porcentaje);

			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return total;
	}

	public boolean updateRemesa(String id, float t) {

		try{
			String sql="UPDATE remesas "+
					" SET estado='PAGADO CON COMISION' , valor_comision='"+t+"'"+
					" WHERE id_remesa='"+id+"'";
			return executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;

	}
	public boolean updateRemesa(String id, String estado) {

		try{
			String sql="UPDATE remesas "+
					" SET estado='"+estado+"' "+
					" WHERE id_remesa='"+id+"'";
			return executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;

	}
	public boolean updateRemesaProfesor(String nro, String documento,String estado, float m) {

		try{
			String sql="UPDATE remesas_profesor "+
					" SET estado='"+estado+"',pagado_con_comision='"+m+"' "+
					" WHERE nro_remesa='"+nro+"' and documento='"+documento+"' and curso='"+getCursoActual()+"'";
			return executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;

	}

	public boolean verificarAsistrencia(String nia, String cursoActual, java.util.Date dat, String dia) {
		// TODO Auto-generated method stub
		try{
			ResultSet x = executeQuery("SELECT id_asistencia, nia, documento, " +
					"fecha, hora, curso, dia, ticked FROM asistencias where nia='"+nia+"' and curso='"+cursoActual+"' and fecha='"+new Date(dat.getTime())+"' and dia='"+dia+"'");
			while(x.next()){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;

	}
	public void agregarFalta(String nia, String cursoActual,
			java.sql.Date date, java.util.Date dat, String anterior) {
		try{
			String sql="INSERT INTO faltas("+
					" nia, curso, fecha, fecha_text)"+
					" VALUES ('"+nia+"', '"+cursoActual+"', '"+new Date(dat.getTime())+"', '"+anterior+"');";
			executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	public String getApellidosYNombres(String nia){
		try{
			ResultSet x = executeQuery("select apellido1,apellido2,nombres from alumnos where nia='"+nia+"' and curso='"+getCursoActual()+"'");
			while(x.next()){
				return ""+x.getString(1)+" "+x.getString(2)+", "+x.getString(3);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";	
	}
	public String getApellidosYNombresSplit(String nia){
		try{
			ResultSet x = executeQuery("select apellido1,apellido2,nombres from alumnos where nia='"+nia+"' and curso='"+getCursoActual()+"'");
			while(x.next()){
				return ""+x.getString(1)+"@1"+x.getString(2)+"@1"+x.getString(3);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";	
	}
	public String getCodigoGrupo(String nia){
		try{
			ResultSet x= executeQuery("select grupo from alumnos where nia='"+nia+"' and curso='"+getCursoActual()+"'");
			while(x.next()){
				return x.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	public String getFechaHumana(java.util.Date date){
		String fechaInicio = new java.sql.Date(date.getTime()).toString();
		String ano1 = fechaInicio.toString().split("-")[0];
		String mes1 = fechaInicio.toString().split("-")[1];
		String dia1 = fechaInicio.toString().split("-")[2];
		String fecha1 = dia1 + "/" + mes1 + "/" + ano1;
		return fecha1;
	}

	public String getFechaHumana(String date){
		String fechaInicio = date;
		String ano1 = fechaInicio.toString().split("-")[0];
		String mes1 = fechaInicio.toString().split("-")[1];
		String dia1 = fechaInicio.toString().split("-")[2];
		String fecha1 = dia1 + "/" + mes1 + "/" + ano1;
		return fecha1;
	}

	public void getAsistenciaAlumnos(java.util.Date date, java.util.Date date2,String codigoGrupo) {
		try{
			ResultSet x =executeQuery("SELECT id_asistencia, nia, curso, fecha " +
					"FROM asistencias where (fecha >= '"+new Date(date.getTime())+"' AND fecha <='"+new Date(date2.getTime())+"' ) AND curso='"+getCursoActual()+"'");
			Hashtable<String, Integer> hashtable=new Hashtable<>();
			while(x.next()){
				String nia=x.getString(2);
				if(hashtable.containsKey(nia)){
					int falta=hashtable.get(nia);
					hashtable.put(nia,(falta+1));
				}else{
					hashtable.put(nia, 1);
				}
			}
			Enumeration<String> enu = hashtable.keys();
			ArrayList<Persona> arrayList=new ArrayList<>();
			while(enu.hasMoreElements()){
				String nia = enu.nextElement();
				if(getCodigoGrupo(nia).equalsIgnoreCase(codigoGrupo)){
					Persona persona=new Persona();
					persona.setNia(nia);
					persona.setApellido1(getApellidosYNombresSplit(nia).split("@1")[0]);
					persona.setApellido2(getApellidosYNombresSplit(nia).split("@1")[1]);
					persona.setNombres(getApellidosYNombresSplit(nia).split("@1")[2]);
					persona.setGrupo(getCodigoGrupo(nia));
					persona.setFaltas(hashtable.get(nia));
					arrayList.add(persona);
				}
			}
			Collections.sort(arrayList);
			new PDF_Asistentes(arrayList,date,date2,principal);

		}catch(Exception e){
			e.printStackTrace();
		}

	}
	public void getAsistenciaAlumnos(java.util.Date date, java.util.Date date2) {
		try{
			ResultSet x =executeQuery("SELECT id_asistencia, nia, curso, fecha " +
					"FROM asistencias where (fecha >= '"+new Date(date.getTime())+"' AND fecha <='"+new Date(date2.getTime())+"' ) AND curso='"+getCursoActual()+"'");
			Hashtable<String, Integer> hashtable=new Hashtable<>();
			while(x.next()){
				String nia=x.getString(2);
				if(hashtable.containsKey(nia)){
					int falta=hashtable.get(nia);
					hashtable.put(nia,(falta+1));
				}else{
					hashtable.put(nia, 1);
				}
			}
			Enumeration<String> enu = hashtable.keys();
			ArrayList<Persona> arrayList=new ArrayList<>();
			while(enu.hasMoreElements()){
				String nia = enu.nextElement();
				Persona persona=new Persona();
				persona.setNia(nia);
				persona.setApellido1(getApellidosYNombresSplit(nia).split("@1")[0]);
				persona.setApellido2(getApellidosYNombresSplit(nia).split("@1")[1]);
				persona.setNombres(getApellidosYNombresSplit(nia).split("@1")[2]);
				persona.setGrupo(getCodigoGrupo(nia));
				persona.setFaltas(hashtable.get(nia));
				arrayList.add(persona);
			}
			Collections.sort(arrayList);
			new PDF_Asistentes(arrayList,date,date2,principal);

		}catch(Exception e){
			e.printStackTrace();
		}

	}
	public void getAsistenciaAlumnosTickeds(java.util.Date date, java.util.Date date2) {
		try{
			ResultSet x =executeQuery("SELECT id_asistencia, nia, curso, fecha " +
					"FROM asistencias where (fecha >= '"+new Date(date.getTime())+"' AND fecha <='"+new Date(date2.getTime())+"' ) and ticked='Si' AND curso='"+getCursoActual()+"'");
			ArrayList<Persona> arrayList=new ArrayList<>();
			while(x.next()){
				String nia = x.getString(2);
				Persona persona=new Persona();
				persona.setNia(nia);
				persona.setApellido1(getApellidosYNombresSplit(nia).split("@1")[0]);
				persona.setApellido2(getApellidosYNombresSplit(nia).split("@1")[1]);
				persona.setNombres(getApellidosYNombresSplit(nia).split("@1")[2]);
				persona.setGrupo(getCodigoGrupo(nia));
				persona.setFecha(x.getString(4));
				//				persona.setFaltas(hashtable.get(nia));
				arrayList.add(persona);
			}
			Collections.sort(arrayList);
			new PDF_Asistentes_Ticked(arrayList,date,date2,principal);

		}catch(Exception e){
			e.printStackTrace();
		}

	}
	public void getFaltasDeGrupo(java.util.Date date, java.util.Date date2,String grupo) {
		try{
			ResultSet x =executeQuery("SELECT id_faltas, nia, curso, fecha, fecha_text " +
					"FROM faltas where (fecha >= '"+new Date(date.getTime())+"' AND fecha <='"+new Date(date2.getTime())+"' ) AND curso='"+getCursoActual()+"'");
			Hashtable<String, Integer> hashtable=new Hashtable<>();
			while(x.next()){
				String nia=x.getString(2);
				if(hashtable.containsKey(nia)){
					int falta=hashtable.get(nia);
					hashtable.put(nia,(falta+1));
				}else{
					hashtable.put(nia, 1);
				}
			}
			Enumeration<String> enu = hashtable.keys();
			ArrayList<Persona> arrayList=new ArrayList<>();
			while(enu.hasMoreElements()){
				String nia = enu.nextElement();
				if(getCodigoGrupo(nia).equalsIgnoreCase(grupo)){
					Persona persona=new Persona();

					persona.setNia(nia);
					persona.setApellido1(getApellidosYNombresSplit(nia).split("@1")[0]);
					persona.setApellido2(getApellidosYNombresSplit(nia).split("@1")[1]);
					persona.setNombres(getApellidosYNombresSplit(nia).split("@1")[2]);
					persona.setGrupo(getCodigoGrupo(nia));
					persona.setFaltas(hashtable.get(nia));
					arrayList.add(persona);
				}
			}
			Collections.sort(arrayList);
			new PDF_Faltas(arrayList,date,date2,principal);

		}catch(Exception e){
			e.printStackTrace();
		}

	}
	public void getFaltasPorAlumno(java.util.Date date, java.util.Date date2,String niaAlumno) {
		try{
			ResultSet x =executeQuery("SELECT id_faltas, nia, curso, fecha, fecha_text,observacion " +
					"FROM faltas where (fecha >= '"+new Date(date.getTime())+"' AND fecha <='"+new Date(date2.getTime())+"' ) " +
					"AND curso='"+getCursoActual()+"' and nia='"+niaAlumno+"'");
			//			Hashtable<String, Integer> hashtable=new Hashtable<>();
			//			System.out.println();
			ArrayList<String> arrayList= new ArrayList<>();
			while(x.next()){
				//				String nia=x.getString(2);

				arrayList.add(getFechaHumana(x.getDate(4))+"@1"+x.getString(5)+"@1"+x.getString(6));
			}
			//			Collections.sort(arrayList);
			new PDF_Faltas_Alumno(arrayList, date, date2, principal, niaAlumno);

		}catch(Exception e){
			e.printStackTrace();
		}

	}
	public void getIncidenciasPorAlumno(java.util.Date date, java.util.Date date2,String niaAlumno) {
		try{
			ResultSet x =executeQuery("SELECT id_incidencias,fecha,incidencia " +
					"FROM incidencias where (fecha >= '"+new Date(date.getTime())+"' AND fecha <='"+new Date(date2.getTime())+"' ) " +
					"AND curso='"+getCursoActual()+"' and nia='"+niaAlumno+"'");
			//			Hashtable<String, Integer> hashtable=new Hashtable<>();
			//			System.out.println();
			ArrayList<String> arrayList= new ArrayList<>();
			while(x.next()){
				//				String nia=x.getString(2);

				arrayList.add(getFechaHumana(x.getDate(2))+"@1"+x.getString(3));
			}
			//			Collections.sort(arrayList);
			new PDF_Incidencias_Alumno(arrayList, date, date2, principal, niaAlumno);

		}catch(Exception e){
			e.printStackTrace();
		}

	}

	public void getFaltasPorAlumno(String niaAlumno, JKTable jkTable,ArrayList<String> arrayList) {
		try{
			ResultSet x =executeQuery("SELECT id_faltas, nia, curso, fecha, fecha_text, observacion " +
					"FROM faltas where  " +
					" curso='"+getCursoActual()+"' and nia='"+niaAlumno+"'");
			//			Hashtable<String, Integer> hashtable=new Hashtable<>();
			try{
				jkTable.clearTable();
				arrayList.clear();
			}catch(Exception e){

			}
			while(x.next()){
				//				String nia=x.getString(2);

				//				String nia = enu.nextElement();
				jkTable.addRow(getFechaHumana(x.getDate(4)),x.getString(5),x.getString(6));
				arrayList.add(x.getString(1));
				//				Persona persona=new Persona();
				//				persona.setNia(nia);
				//				persona.setApellido1(getApellidosYNombresSplit(nia).split("@1")[0]);
				//				persona.setApellido2(getApellidosYNombresSplit(nia).split("@1")[1]);
				//				persona.setNombres(getApellidosYNombresSplit(nia).split("@1")[2]);
				//				persona.setGrupo(getCodigoGrupo(nia));
				//				persona.setFaltas(hashtable.get(nia));
				//				arrayList.add(persona);
			}
			//			Collections.sort(arrayList);
			//			new PDF_Faltas(arrayList,date,date2,principal);

		}catch(Exception e){
			e.printStackTrace();
		}

	}

	public void getAlumnosConFaltas(java.util.Date date, java.util.Date date2) {
		try{
			ResultSet x =executeQuery("SELECT id_faltas, nia, curso, fecha, fecha_text " +
					"FROM faltas where (fecha >= '"+new Date(date.getTime())+"' AND fecha <='"+new Date(date2.getTime())+"' ) AND curso='"+getCursoActual()+"'");
			Hashtable<String, Integer> hashtable=new Hashtable<>();
			while(x.next()){
				String nia=x.getString(2);
				if(hashtable.containsKey(nia)){
					int falta=hashtable.get(nia);
					hashtable.put(nia,(falta+1));
				}else{
					hashtable.put(nia, 1);
				}
			}
			Enumeration<String> enu = hashtable.keys();
			ArrayList<Persona> arrayList=new ArrayList<>();
			while(enu.hasMoreElements()){
				String nia = enu.nextElement();
				Persona persona=new Persona();
				persona.setNia(nia);
				persona.setApellido1(getApellidosYNombresSplit(nia).split("@1")[0]);
				persona.setApellido2(getApellidosYNombresSplit(nia).split("@1")[1]);
				persona.setNombres(getApellidosYNombresSplit(nia).split("@1")[2]);
				persona.setGrupo(getCodigoGrupo(nia));
				persona.setFaltas(hashtable.get(nia));
				arrayList.add(persona);
			}
			Collections.sort(arrayList);
			new PDF_Faltas(arrayList,date,date2,principal);

		}catch(Exception e){
			e.printStackTrace();
		}


	}
	public void addDevolucion(String nia, String nro_remesa, String id_remesa,
			String motivo, String observacion, String porcentaje, String id_fechas) {
		// TODO Auto-generated method stub
		//		principal.getBaseDeDatos().addDevolucion(nia,nRemesas.getSelected(),
		//		arrayListIDGeneradas.get(nRemesas.getSelectedIndex()),motivo.getText(),observacion.getText(),porcentaje.getSelectedItem().toString());
		float p=0;
		if(porcentaje.startsWith("25")){
			p=0.25f;
		}else if(porcentaje.startsWith("50")){
			p=0.50f;
		}else if(porcentaje.startsWith("75")){
			p=0.75f;
		}else if(porcentaje.startsWith("100")){
			p=1;
		}
		try{
			String SQL="INSERT INTO devoluciones("+
					"nia, curso, porcentaje, nro_remesa, motivo, observacion, "+
					" fecha, id_remesa,id_fechas) "+
					"VALUES ('"+nia+"', '"+getCursoActual()+"','"+p+"', " +
					"'"+nro_remesa+"', '"+motivo+"', '"+observacion+"', '"+new Date(new java.util.Date().getTime())+"'"+ 
					",'"+id_remesa+"','"+id_fechas+"');";
			executeUpdate(SQL);
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	public void getDevoluciones(JKTable tableDevoluciones,
			ArrayList<String> arrayListDevoluciones,String nia, String id_fechas,ArrayList<String> arrayListIDRemesasDevoluciones) {
		// TODO Auto-generated method stub
		try{
			arrayListDevoluciones.clear();
			tableDevoluciones.clearTable();
			arrayListIDRemesasDevoluciones.clear();
		}catch(Exception e){

		}
		//		tableDevoluciones.addColumn("Motivo");
		//		tableDevoluciones.addColumn("Observacion");
		//		tableDevoluciones.addColumn("Porcentaje %");
		//		tableDevoluciones.addColumn("Remesa");
		try{
			ResultSet x = executeQuery("SELECT id_devolucion, nia, curso, porcentaje, " +
					"nro_remesa, motivo, observacion,  fecha, id_remesa FROM devoluciones " +
					"where nia='"+nia+"' and curso='"+getCursoActual()+"' and id_fechas='"+id_fechas+"' order by nro_remesa desc");
			while(x.next()){ 

				float v= x.getFloat(4);
				String m="";
				if(v==0.25f){
					m="25%";
				}else if(v==0.50f){
					m="50%";
				}else if(v==0.75){
					m="75%";
				}else if(v==1){
					m="100%";
				}
				arrayListDevoluciones.add(x.getString(1));
				arrayListIDRemesasDevoluciones.add(x.getString(9));
				tableDevoluciones.addRow(x.getString(6),x.getString(7),m,x.getString(5));

			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	public void pagadoTodasRemesasGeneradas(String id) {
		// TODO Auto-generated method stub
		try{
			String SQL="UPDATE remesas  SET  estado='PAGADO' WHERE generada='Si' and id_fechas='"+id+"' and estado!='PAGADO CON COMISION'";
			executeUpdate(SQL);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public byte[] getFotoProfesor(String documento) {
		try{
			ResultSet x = executeQuery("SELECT foto FROM profes where documento='"+documento+"' and curso='"+getCursoActual()+"'");
			while(x.next()){
				System.out.println("// "+x.getBytes(1).length);
				return x.getBytes(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	public boolean addProfesor(java.util.Date d, String seleccion,String documento) {
		// TODO Auto-generated method stub

		try{
			return	executeUpdate("INSERT INTO contabilidad_dias_profesores(  " +
					"documento, curso, dia, seleccion)  VALUES ('"+documento+"', '"+getCursoActual()+"', '"+new Date(d.getTime())+"', '"+seleccion+"');");
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	public void getDiasProfesor(String documento, JKTable jkTable) {
		// TODO Auto-generated method stub
		try{
			jkTable.clearTable();
		}catch(Exception e){

		}
		try{
			ResultSet x = executeQuery("SELECT id_contabilidad, " +
					"documento, curso, dia, seleccion FROM contabilidad_dias_profesores where documento='"+documento+"' and curso='"+getCursoActual()+"'");
			while(x.next()){
				//				System.out.println("///");
				//				System.out.println(x.getString(4));
				//				System.out.println(x.getString(5));
				//				System.out.println("///");
				jkTable.addRow(""+x.getString(4),x.getString(5));
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	public void getContabilidadProfesores(PanelAgregarDiasProfesor instance) {
		// TODO Auto-generated method stub
		try{
			ResultSet x = executeQuery("SELECT id_contabilidad, " +
					"documento, curso, dia, seleccion FROM contabilidad_dias_profesores where curso='"+getCursoActual()+"'");
			ArrayList<String> arrayList = new ArrayList<>();
			while(x.next()){
				if(!arrayList.contains(x.getString(2))){
					arrayList.add(x.getString(2));
				}
			}
			for(int index=0; index< arrayList.size(); index++){
				instance.addProfesor(arrayList.get(index));
			}
		}catch(Exception e ){
			e.printStackTrace();
		}

	}
	public ArrayList<Persona> getProfesores(ArrayList<String> arrayList) {

		// TODO Auto-generated method stub
		ArrayList<Persona> alumnos=new ArrayList<>();
		try{

			ResultSet x = executeQuery("select documento,apellido1,apellido2,nombre,foto from profes where curso='"+getCursoActual()+"' order by apellido1");
			while(x.next()){
				if(arrayList.contains(x.getString(1))){
					Persona alumno=new Persona();
					alumno.setFoto(x.getBytes(5));
					//						alumno.setNia(x.getString(1));
					alumno.setDocumento(x.getString(1));
					alumno.setNombres(x.getString(4));
					alumno.setApellido1(x.getString(2));
					alumno.setApellido2(x.getString(3));
					alumno.setApellidos(x.getString(2)+" "+x.getString(3));
					alumnos.add(alumno);
					//						table.addRow(x.getString(1),x.getString(2),x.getString(3)+" "+x.getString(4)+", "+x.getString(5));
					//						label.setText(""+table.getRowCount());
				}
			}
			Collections.sort(alumnos);
		}catch(Exception e){
			e.printStackTrace();
		}
		return alumnos;


	}
	public boolean getImpresionDirecta(){
		try{
			ResultSet x = executeQuery("select impresion_directa from impresoras where id_impresora='1'");
			while(x.next()){
				if(x.getString(1).equalsIgnoreCase("SI")){
					return true;
				}else
					return false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	public void setImpresionDirecta(boolean b) {
		// TODO Auto-generated method stub
		try{
			executeUpdate("UPDATE impresoras SET impresion_directa='"+ ( (b)?"SI":"NO")+ "' ");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void addObservacionFalta(String id, String observacion) {
		// TODO Auto-generated method stub
		try{
			executeUpdate("UPDATE faltas  SET observacion='"+observacion+"' WHERE id_faltas='"+id+"'");
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	public String getDiasProfesor(String documento,String mes) {
		try{
			int ordinarios=0;
			int extraordinarios=0;
			{
				ResultSet x = executeQuery("select dia from contabilidad_dias_profesores where " +
						"seleccion='Ordinario' and documento='"+documento+"' " +
						"and curso='"+getCursoActual()+"' ");
				while(x.next()){

					String h= x.getString(1).split("-")[1];
					if(h.equalsIgnoreCase(mes)){
						ordinarios++;
					}
				}
			}
			{
				ResultSet x = executeQuery("select dia from contabilidad_dias_profesores where " +
						"seleccion='Extraordinario' and documento='"+documento+"' " +
						"and curso='"+getCursoActual()+"' ");
				while(x.next()){

					String h= x.getString(1).split("-")[1];
					if(h.equalsIgnoreCase(mes)){
						extraordinarios++;
					}
				}
			}
			return ""+ordinarios+"@1"+extraordinarios;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	public boolean addRemesaProfesor(String documento, String mes, float total,float deudaTotal, float extra, 
			float pagado, String estado, float saldoAUsar, float pagadoTotal) {
		try{
			int c=0;
			ResultSet x = executeQuery("select count(id_remesa_profesor) from remesas_profesor where documento='"+documento+"' " +
					"and curso='"+getCursoActual()+"' ");

			while(x.next()){
				c=x.getInt(1);
			}
			boolean n=executeUpdate("INSERT INTO remesas_profesor( "+
					"documento, curso, mes, pagado, pago_extra, "+
					"monto_a_pagar, deuda,estado,saldo_usado,pagado_total,nro_recibo,fecha)"+
					" VALUES ('"+documento+"', '"+getCursoActual()+"', '"+mes+"', '"+pagado+"', '"+extra+"', '"+total+"', "+
					"       '"+deudaTotal+"','"+estado+"','"+saldoAUsar+"','"+pagadoTotal+"','"+(c+1)+"','"+new Date(new java.util.Date().getTime())+"');");
			return n;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	public boolean abonar(String documento, float r) {
		// TODO Auto-generated method stub
		try{
			executeQuery("");
			return executeUpdate("INSERT INTO abonos(documento, curso, fecha, monto) VALUES " +
					"('"+documento+"', '"+getCursoActual()+"', '"+new Date(new java.util.Date().getTime())+"','"+r+"');");
		}catch(Exception e){

			e.printStackTrace();
		}
		return false;
	}
	public float getAbono(String documento) {
		// TODO Auto-generated method stub
		float a =0;
		float usado=0;
		try{
			{
				ResultSet x = executeQuery("select pago_extra from remesas_profesor where documento='"+documento+"' and curso='"+getCursoActual()+"'");
				while(x.next()){
					a+=x.getFloat(1);
					System.out.println("wtf "+a);
				}
			}
			{
				ResultSet x = executeQuery("select monto from abonos where documento='"+documento+"' and curso='"+getCursoActual()+"'");
				while(x.next()){
					a+=x.getFloat(1);
				}
			}
			{
				System.out.println("WTF>"+a);
				ResultSet x = executeQuery("select saldo_usado from remesas_profesor where documento='"+documento+"' and curso='"+getCursoActual()+"'");
				while(x.next()){
					usado+=x.getFloat(1);
				}
			}
			System.out.println(a);
			System.out.println(usado);
			a=a-usado;
		}catch(Exception e){
			e.printStackTrace();
		}
		return a;
	}
	public String getMes(String mes){
		int i=Integer.parseInt(mes);
		switch (i) {
		case 1:
			return "Enero";
		case 2:
			return "Febrero";
		case 3:
			return "Marzo";
		case 4:
			return "Abril";
		case 5:
			return "Mayo";
		case 6:
			return "Junio";
		case 7:
			return "Julio";
		case 8:
			return "Agosto";
		case 9:
			return "Septiembre";
		case 10:
			return "Octubre";
		case 11:
			return "Noviembre";
		case 12:
			return "Diciembre";
		}
		return "";
	}
	public void getRemesas(JKTable tableRemesas,String documento) {
		try{
			tableRemesas.clearTable();
		}catch(Exception e){

		}
		try{

			ResultSet x = executeQuery("SELECT valor_remesa, estado, nro_remesa, " +
					"pagado_con_comision FROM remesas_profesor where documento='"+documento+"' and curso='"+getCursoActual()+"' order by nro_remesa");
			while(x.next()){
				DecimalFormat df1 = new DecimalFormat("###.##");
				float comision =x.getFloat(4);
				if(comision!=0){
					tableRemesas.addRow(x.getString(3),getMes(getMesRemesa(Integer.parseInt(x.getString(3)))),""+df1.format(x.getFloat(1))+" + "+df1.format(comision)+" > Total [ "+df1.format(x.getFloat(1)+comision)+" ]",
							x.getString(2));
				}else{
					tableRemesas.addRow(x.getString(3),getMes(getMesRemesa(Integer.parseInt(x.getString(3)))),""+df1.format(x.getFloat(1))+"",
							x.getString(2));
				}

				//				i++;
			}
			try{
				tableRemesas.setSelectedRow(tableRemesas.getRowCount()-1);
			}catch(Exception e){
				tableRemesas.setSelectedRow(0);
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	public float getDeudaGeneral(String documento) {
		float deuda=0;
		// TODO Auto-generated method stub
		try{
			ResultSet x = executeQuery("select deuda from remesas_profesor where" +
					" documento='"+documento+"' and curso='"+getCursoActual()+"' and bloqueado='NO'");
			while(x.next()){
				deuda+=x.getFloat(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return deuda;
	}
	public void bloquearDeudasAnteriores(String documento) {

		try{
			executeUpdate("UPDATE  remesas_profesor set bloqueado='SI' WHERE documento='"+documento+"' and curso='"+getCursoActual()+"' ");
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	public void getRecibos(String selected) {
		try{
			//			System.out.println(selected);
			ArrayList<String> arrayList = new ArrayList<>();
			String recibo = selected.split(" ")[1];
			ResultSet x = executeQuery("select mes,documento,deuda from remesas_profesor" +" where curso='"+getCursoActual()+"' and nro_recibo='"+recibo+"'");
			while(x.next()){

				String mes=x.getString(1);
				String documento=""+x.getString(2);


				String nombreProfesor=getNombreTutor(documento);
				String dias= getDiasProfesor(documento, mes);


				float deudaPendiente = x.getFloat(3);

				arrayList.add(nombreProfesor+"@2"+dias+"@2"+deudaPendiente);


			}
			try{
				new PDF_Recibos1_Profesores(arrayList, principal);
			}catch(Exception  e ){
				e.printStackTrace();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void getRecibosPorFecha(java.util.Date fecha1, java.util.Date fecha2,String modo) {
		// TODO Auto-generated method stub
		try{
			String sql="";
			if(!modo.equalsIgnoreCase("Ambos")){
				sql="select dia from contabilidad_dias_profesores" +
						" where ( dia >='"+new Date(fecha1.getTime())+"'" +
						" and dia <='"+new Date(fecha2.getTime())+"') and seleccion='"+modo+"' order by dia";
			}else{
				sql="select dia from contabilidad_dias_profesores" +
						" where ( dia >='"+new Date(fecha1.getTime())+"'" +
						" and dia <='"+new Date(fecha2.getTime())+"')  order by dia";
			}
			ResultSet x= executeQuery(sql);
			ArrayList<String> arrayList=new ArrayList<>();
			Hashtable<String, Integer> hashtable
			=new Hashtable<>();
			while(x.next()){
				Date da = x.getDate(1);
				Date fecha= new Date(da.getTime());
				if(!arrayList.contains(fecha.toString()))
					arrayList.add(fecha.toString());
				if(hashtable.containsKey(fecha.toString())){
					hashtable.put(fecha.toString(), (hashtable.get(fecha.toString())+1));
				}else{
					hashtable.put(fecha.toString(), 1);
				}
			}
			new PDF_Listado_RangoDias(fecha1,fecha2, principal, arrayList, hashtable,modo);
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	public void getRecibos() {
		try{
			ResultSet x= executeQuery("select dia from contabilidad_dias_profesores order by dia");
			ArrayList<String> arrayList=new ArrayList<>();
			Hashtable<String, Integer> hashtable
			=new Hashtable<>();
			while(x.next()){
				Date da = x.getDate(1);
				Date fecha= new Date(da.getTime());
				if(!arrayList.contains(fecha.toString()))
					arrayList.add(fecha.toString());
				if(hashtable.containsKey(fecha.toString())){
					hashtable.put(fecha.toString(), (hashtable.get(fecha.toString())+1));
				}else{
					hashtable.put(fecha.toString(), 1);
				}
			}
			new PDF_Listado_AmbosDias("", principal, arrayList, hashtable);
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	public void getRecibosPorDias(String selected) {
		try{
			ResultSet x= executeQuery("select dia from contabilidad_dias_profesores where seleccion='"+selected+"' order by dia");
			ArrayList<String> arrayList=new ArrayList<>();
			Hashtable<String, Integer> hashtable
			=new Hashtable<>();
			while(x.next()){
				Date da = x.getDate(1);
				Date fecha= new Date(da.getTime());
				if(!arrayList.contains(fecha.toString()))
					arrayList.add(fecha.toString());
				if(hashtable.containsKey(fecha.toString())){
					hashtable.put(fecha.toString(), (hashtable.get(fecha.toString())+1));
				}else{
					hashtable.put(fecha.toString(), 1);
				}
			}
			new PDF_Listado_Dias(selected.toUpperCase(), principal, arrayList, hashtable);
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	public void getEstadoCartas(String id, JKTable tableCartas, String nia) {
		try{
			try{
				tableCartas.clearTable();
			}catch(Exception e){

			}
			ResultSet x = executeQuery("SELECT id_cartas, nia, curso, estado, fecha, id_remesa, estado2, estado3 FROM cartas_de_cobro where nia='"+nia+"' and curso='"+getCursoActual()+"' and id_remesa='"+id+"'");
			while(x.next()){
				tableCartas.addRow(x.getString(4),x.getString(7),x.getString(8));
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	public void updateStatusCarta1(String id, String nia) {	
		try{
			executeUpdate("UPDATE cartas_de_cobro SET estado='Generada' WHERE nia='"+nia+"' and curso='"+getCursoActual()+"' and id_remesa='"+id+"'");
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	public void updateStatusCarta2(String id, String nia) {	
		try{
			executeUpdate("UPDATE cartas_de_cobro SET estado2='Generada' WHERE nia='"+nia+"' and curso='"+getCursoActual()+"' and id_remesa='"+id+"'");
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	public void updateStatusCarta3(String id, String nia) {	
		try{
			executeUpdate("UPDATE cartas_de_cobro SET estado3='Generada' WHERE nia='"+nia+"' and curso='"+getCursoActual()+"' and id_remesa='"+id+"'");
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	public String getUltimaRemesa(String nia){
		try{
			ResultSet x = executeQuery("SELECT id_remesa, nia, curso, nro_remesa, " +
					"valor_remesa, id_fechas, estado,  valor_comision, generada, deuda" +
					" FROM remesas where generada='Si' and nia='"+nia+"' and curso='"+getCursoActual()+"' and estado='PENDIENTE' order by nro_remesa desc limit 1");


			System.out.println("SELECT id_remesa, nia, curso, nro_remesa, " +
					"valor_remesa, id_fechas, estado,  valor_comision, generada, deuda" +
					" FROM remesas where generada='Si' and nia='"+nia+"' and curso='"+getCursoActual()+"' and estado='PENDIENTE' order by nro_remesa desc limit 1");
			while(x.next()){
				return x.getString(4)+"@1"+x.getFloat(5);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	public String getUltimaRemesaProfesor(String doc){
		try{
			ResultSet x = executeQuery("SELECT nro_remesa, " +
					"valor_remesa " +
					" FROM remesas_profesor where  documento='"+doc+"' and curso='"+getCursoActual()+"' and estado='PENDIENTE' order by nro_remesa desc limit 1");


			while(x.next()){
				return x.getString(1)+"@1"+x.getFloat(2);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	public String getRemesasEmitidas(java.util.Date date, java.util.Date date2) {

		try{

		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	public String getMesRemesa(int t){
		if(t==1){
			return "09";
		}else if(t==2){
			return "10";
		}else if(t==3){
			return "11";
		}else if(t==4){
			return "12";
		}else if(t ==5){
			return "01";
		}else if(t==6){
			return "02";
		}else if(t==7){
			return "03";
		}else if(t==8){
			return "04";
		}else if(t==9){
			return "05";
		}else if(t==10){
			return "06";
		}
		return "00";
	}
	public void addRemesaProf(String documento) {
		// TODO Auto-generated method stub

		try{
			ResultSet x = executeQuery("select count(*) from remesas_profesor where documento='"+documento+"' and curso='"+getCursoActual()+"'");
			int t=0;
			while(x.next()){
				t=x.getInt(1);
			}

			ResultSet l= executeQuery("select valor_remesa,estado from remesas_profesor " +
					"where documento='"+documento+"' and curso='"+getCursoActual()+"' and nro_remesa ='"+t+"'");
			float deuda=0;
			while(l.next()){
				if(l.getString(2).equalsIgnoreCase("PENDIENTE")){
					deuda=l.getFloat(1);
					break;
				}
			}

			t++;
			String mes = getMesRemesa(t);

			ResultSet x1 = executeQuery("SELECT id_contabilidad, " +
					"documento, curso, dia, seleccion FROM contabilidad_dias_profesores where documento='"+documento+"' and curso='"+getCursoActual()+"' ");
			int sumDias=0;
			//			System.out.println(mes);
			while(x1.next()){
				String dia=x1.getString(4);
				//				System.out.println(dia);
				if(dia.split("-")[1].equalsIgnoreCase(mes)){
					sumDias++;
				}
			}

			float totalRemesa= (sumDias * principal.getBaseDeDatos().getPrecioMenu(principal.getBaseDeDatos().getCursoActual()))+deuda+1;

			executeUpdate("INSERT INTO remesas_profesor(documento, curso,valor_remesa, estado,  nro_remesa,pagado_con_comision) VALUES ('"+documento+"', '"+getCursoActual()+"','"+totalRemesa+"','PENDIENTE','"+t+"','0');");

		}catch(Exception e){
			e.printStackTrace();
		}

	}
	public void getBalancesEconomicosProfesores(String selected) {
		// TODO Auto-generated method stub
		try{

			ResultSet x = executeQuery("select nro_remesa,documento,valor_remesa,valor_comision from remesas_profesor where curso='"+getCursoActual()+"'");
			int total_remesas_emitidas=0;
			float valor_total_remesas=0;
			float total_generado=0;
			float valor_total_remesas_comision=0;
			while(x.next()){

				String g= getMesRemesa(x.getInt(1)).toUpperCase();
				//				System.out.println(g+" // "+selected.split("-")[0]);

				if(g.equalsIgnoreCase(selected.split("-")[0])){

					//					String doc=""+x.getString(2);

					valor_total_remesas+=x.getFloat(3); 
					total_remesas_emitidas++;

					valor_total_remesas_comision+=(x.getFloat(3)+x.getFloat(4));




				}

			}
			new PDF_BalancesEconomicos(principal,total_generado,valor_total_remesas,valor_total_remesas_comision, total_remesas_emitidas);


		}catch(Exception e){
			e.printStackTrace();
		}

	}
	public void getBalancesEconomicos(String selected) {
		// TODO Auto-generated method stub
		try{

			ResultSet x = executeQuery("SELECT fecha_alta, nia, id_fechas FROM fechas_altas_bajas where curso='"+getCursoActual()+"'");
			int total_remesas_emitidas=0;
			float valor_total_remesas=0;
			float total_generado=0;
			float valor_total_remesas_comision=0;
			while(x.next()){
				String g= x.getString(1).split("-")[1];

				//				System.out.println(g+" // "+selected.split("-")[0]);
				if(g.equalsIgnoreCase(selected.split("-")[0])){
					//					g.split(arg0)

					String nia=""+x.getString(2);
					{
						ResultSet v = executeQuery("select valor_remesa from remesas where nia='"+nia+"' and curso='"+getCursoActual()+"' and generada='Si'");
						while(v.next()){
							valor_total_remesas+=v.getFloat(1); 
							total_remesas_emitidas++;
						}
					}
					{
						ResultSet v = executeQuery("select valor_remesa from remesas where nia='"+nia+"' and curso='"+getCursoActual()+"'");
						while(v.next()){
							total_generado+=v.getFloat(1);
						}
					}
					{
						ResultSet v = executeQuery("select valor_remesa,valor_comision from remesas where nia='"+nia+"' and curso='"+getCursoActual()+"' and generada='Si'");
						while(v.next()){
							valor_total_remesas_comision+=(v.getFloat(1)+v.getFloat(2));
						}
					} 
				}
			}
			new PDF_BalancesEconomicos(principal,total_generado,valor_total_remesas,valor_total_remesas_comision, total_remesas_emitidas);

		}catch(Exception e){
			e.printStackTrace();
		}

	}
	public void getPendientesCobroProfesores(String selected) {
		// TODO Auto-generated method stub
		try{
			ResultSet x = executeQuery("select nro_remesa,documento from remesas_profesor where curso='"+getCursoActual()+"'");
			ArrayList<Persona> array=new ArrayList<>();
			while(x.next()){
				String g= getMesRemesa(x.getInt(1)).toUpperCase();
				//				System.out.println(g+" // "+selected.split("-")[0]);
				if(g.equalsIgnoreCase(selected.split("-")[0])){


					String doc=""+x.getString(2);
					{
						String y=getUltimaRemesaProfesor(doc);
						Persona p = getProfesor(doc);

						float n= 0;
						try{
							n=Float.parseFloat(y.split("@1")[1]);
						}catch(Exception e){
							continue;
						}
						p.setDeuda(n);
						array.add(p);
					}
				}
			}
			new PDF_RecibosPendientes(array,principal);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void getPendientesCobro(String selected) {
		// TODO Auto-generated method stub
		try{
			ResultSet x = executeQuery("select fecha_alta,nia from fechas_altas_bajas where curso='"+getCursoActual()+"'");
			ArrayList<Persona> array=new ArrayList<>();
			while(x.next()){
				String g= x.getString(1).split("-")[1];

				//				System.out.println(g+" // "+selected.split("-")[0]);
				if(g.equalsIgnoreCase(selected.split("-")[0])){

					String nia=""+x.getString(2);
					{
						String y=getUltimaRemesa(nia);

						Persona p = getAlumno(nia);
						float n= 0;
						try{
							n=Float.parseFloat(y.split("@1")[1]);
						}catch(Exception e){
							continue;
						}
						p.setDeuda(n);
						array.add(p);
					}
				}
			}
			new PDF_RecibosPendientes(array,principal);
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	public void updateJuevesComedor(String text) {
		// TODO Auto-generated method stub
		try{

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String verificarAlergias(String g)
	{
		try
		{
			String sql = "";
			String retorno = "";
			String curso = principal.getBaseDeDatos().getCursoActual();
			sql = "SELECT informe_medico FROM alumnos WHERE nia = '" + g + "' and curso='" + curso + "'";

			ResultSet x = executeQuery(sql);
			
			while(x.next())
				retorno = x.getString("informe_medico");
			
			return retorno;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}