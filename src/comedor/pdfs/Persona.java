package comedor.pdfs;

import java.io.File;

public class Persona  implements Comparable<Persona>{

	private String nia=null;
	private String documento;
	
	private File   foto;
	private String nombres;
	private String apellidos;
	private byte byteFoto[];
	private String fechaNacimiento;
	private String padre;
	private String madre;
	private String apellido1;
	private String apellido2;
	private String email;
	private String fecha;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	private String grupo;
	private boolean fotoVerificada=false;
	
	
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	private String telefono;
	public String getNombreCompleto(){
		return getApellidos()+", "+getNombres();
	}
	public String getPadre() {
		if(padre!=null&&padre.length()>=2)
		return padre;
		else return " ";
	}
	public void setPadre(String padre) {
		this.padre = padre;
	}
	public String getMadre() {
		if(madre!=null &&madre.length()>=2)
		return madre;
		else return " ";
	}
	public void setMadre(String madre) {
		this.madre = madre;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getNia() {
		if(nia!=null)
		return nia;
		else return documento;
	}
	public void setNia(String nia) {
		this.nia = nia;
	}
	public File getFoto() {
		return foto;
	}
	public void setFoto(File foto) {
		this.foto = foto;
	}
	public String getNombres() {
		try{
		return nombres.replaceAll("null", " ");
		}catch(Exception e){
			return " ";
		}
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		try{
		return apellidos.replaceAll("null", " ");
		}catch(Exception e){
			return " ";
		}
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public void setFoto(byte[] bytes) {
		byteFoto=bytes;
		// TODO Auto-generated method stub
		
	}
	public byte[] getBytesFoto(){
		if(byteFoto!=null&& byteFoto.length>2)
		return byteFoto;
		else return null;
	}
	public String getFechaNacimiento(){
		return this.fechaNacimiento;
	}
	public void setFechaNacimiento(String string) {
		// TODO Auto-generated method stub
		this.fechaNacimiento=string;
		
	}
	public String getTelefono(){
		return telefono;
	}
	public void setTelefono(String t) {
		// TODO Auto-generated method stub
		this.telefono=t;
	}
	public boolean isFotoVerificada(){
		return fotoVerificada;
	}
	public void setFotoVerificada(boolean b) {
		// TODO Auto-generated method stub
		this.fotoVerificada=b;
	}	
	public int getFaltas() {
		return faltas;
	}
	public void setFaltas(int faltas) {
		this.faltas = faltas;
	}
	@Override
	public int compareTo(Persona arg0) {
			if (apellido1.toUpperCase().compareToIgnoreCase(arg0.apellido1.toUpperCase()) == 0) {

				return 0;
			} else if (apellido1.toUpperCase()
					.compareToIgnoreCase(arg0.apellido1.toUpperCase()) < 0) {
				return 1;
			}
			return -1;
	}
	private int faltas=0;
	public String getFecha(){
		return this.fecha;
	}
	public void setFecha(String fecha) {
		// TODO Auto-generated method stub
		this.fecha=fecha;
	}
	private float deuda=0;
	public void setDeuda(float n) {
		// TODO Auto-generated method stub
		this.deuda=n;
	}
	public float getDeuda(){
		return deuda
				;
	}
	
}
