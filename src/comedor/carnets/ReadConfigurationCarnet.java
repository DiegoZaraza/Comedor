package comedor.carnets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Hashtable;

public class ReadConfigurationCarnet {

	public static void main(String[] args) {
		try{
		BufferedReader bufferedReader = new BufferedReader(
				   new InputStreamReader(new FileInputStream("c:\\Carnet_Frontal.xml"), "UTF-8"));
		
		String line=null;
		String xml="";
		Hashtable<String, String> hashtable=new Hashtable<>();
		hashtable.put("fechaNacimiento", "29/03/1991");
		hashtable.put("grupo", "LEYER");
		hashtable.put("curso", "2016");
		hashtable.put("nia", "20213944");
		hashtable.put("foto", "C:\\46022932\\FotosNIA\\10016322.jpg");
		hashtable.put("documento", "20213944F");
		hashtable.put("nombre", "PAUL JOSE, FERRER ROMERO");
		hashtable.put("codigo_barra", "8992819");	
	
		
		
		System.out.println(new File(".")
		.getAbsolutePath().substring(0,
				new File(".").getAbsolutePath().length() - 2).toString()+""+File.separator+"System-Comedor"+File.separator+"fondoCarnet.png");
		
 		while((line=bufferedReader.readLine())!=null)xml=line;
	 		String g[]=xml.split(">");
	 		String xmlFinal="";
	 		for(int index=0;index<g.length;index++){
	 			String ja=g[index]+">";
//	 			System.out.println(ja);
	 			if(ja.startsWith("<image background=")){
	 				String p1=ja.substring(0,ja.indexOf("=")+2);
	 				p1=new File(".")
	 				.getAbsolutePath().substring(0,
	 						new File(".").getAbsolutePath().length() - 2).toString()+""+File.separator+"System-Comedor"+File.separator+"fondoCarnet.png";
	 				String p2=ja.substring(ja.indexOf("background-point")-2,ja.length());
	 				xmlFinal+=""+p1+p2+"\n";
	 				continue;
	 			}
	 			
	 			
	 			Enumeration<String> x = hashtable.keys();
	 	 		while(x.hasMoreElements()){
	 	 		     String m=x.nextElement();
	 	 		     if(ja.indexOf("$"+m)!=-1){
	 	 		    	 String primero=ja.substring(0,ja.indexOf("{")+1);
	 	 		    	 String segundo=ja.substring(ja.lastIndexOf("}"),ja.length());
	 	 		    	 ja=primero+hashtable.get(m)+segundo;
	 	 		     }
 	 		}	
 		xmlFinal+=""+ja+"\n";
 		}
 		File file=new File(""+hashtable.get("documento")+".xml");
 		FileOutputStream fileOutputStream=new FileOutputStream(file);
 		fileOutputStream.write(xmlFinal.getBytes());
 		fileOutputStream.flush();
 		fileOutputStream.close();
// 		Carnet carnetFrontal=new Carnet(1);
// 		carnetFrontal.setPreferredSize(new Dimension(844,455));
//		carnetFrontal.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 170));
// 		carnetFrontal.setModo(2);
// 		
//// 		JFrame frame=new JFrame();
//// 		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//// 		frame.setLayout(new GridLayout());
//// 		frame.add(carnetFrontal);
//// 		frame.setSize(890,500);
//// 		frame.setVisible(true);
// 		
//// 		carnetFrontal.loadConfiguration(file);
//// 	    while(true){
//// 	    	if(carnetFrontal.isReady()){
//// 	    		System.out.println("Si");
//// 	    		Thread.sleep(2000);
//// 	    		carnetFrontal.imprimir();
//// 	    		break;
//// 	    	}else{
//// 	    		System.out.println("No");
//// 	    	}
//// 	    }
// 
		bufferedReader.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
