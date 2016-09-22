package comedor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Contador {
	public Contador() {
		
	}
	public static float MathNumberDigit(float v) {
		String y = "" + v;
		String f =	 y.substring(y.lastIndexOf(".") + 1, y.length());
		String number = "";
		try {
			number = y.substring(0, y.indexOf("."));
		} catch (Exception k) {
			return v;
		}
		String i = "";

		if (f.length() > 2) {

			if (Integer.parseInt("" + f.charAt(2)) >= 5) {

				int valor = (Integer.parseInt("" + f.charAt(1)) + 1);
				i = f.charAt(0) + "" + valor;
			} else {
				i = f.substring(0, 2);
				System.out.println(number + "." + i);
				return Float.parseFloat(number + "." + i);
			}
		} else {
			System.out.println(y);
			return Float.parseFloat(y);
		}
		System.out.println(number + "." + i);
		return Float.parseFloat(number + "." + i);
	}

	public static void main(String[] args) {
		// float x =419.955f;
		// String g = ""+x;
		// System.out.println(MathNumberDigit(x));
		// if(g.indexOf(".")!=-1){
		// String b = ""+g.sub;
		//
		// }
//		try{
//		URL url=new URL("http://ewappsolutions.com.ve/index.php/home/disco/1/");
//		URLConnection http = url.openConnection();
//		BufferedReader bufferedReader1=new BufferedReader(new InputStreamReader(http.getInputStream()));
//		String line="";
//		while((line=bufferedReader1.readLine())!=null){
//			if(line.indexOf(" <a href=\"")!=-1){
//				String g=line.replaceAll("<a href=\"", "").trim();
//				g=g.substring(0,g.indexOf("\""));
//				System.out.println(g);
//				line=bufferedReader1.readLine().trim().replaceAll("\" alt=\"\">", "");
//				String urlImagen=line.substring(line.indexOf("\"")+1, line.length());
//				System.out.println(urlImagen);
//				System.out.println(urlImagen.split("/")[4]);
//			  break;
//			}
//		}
//		bufferedReader1.close();
//		}catch(Exception c){
//			
//		}
//		System.exit(0);
		File file = new File(".\\src\\comedor");
		int s = 0;
		File[] list = file.listFiles();
		for (File k : list) {
			if (k.isDirectory()) {
				File[] j = k.listFiles();
				for (File i : j) {
					if (i.getName().endsWith(".java")) {
						try {
							FileReader fileReader = new FileReader(i);

							BufferedReader bufferedReader = new BufferedReader(
									fileReader);
							while ((bufferedReader.readLine()) != null) {
								s++;
//								if (line.indexOf("System.out") != -1) {
//									System.out.println(line);
//								}
								// if (line.indexOf("../resource/") != -1) {
								//
								// System.out.println(line);
								// }

								// new
								// ImageIcon(getClass().getResource("/scep_unefm/resource/m.png")));
								// jComboBox2.addItem("Femenino",
								// new
								// ImageIcon(getClass().getResource("../resource/f.png")));
								// jLabel7.setText("Edo. Civil:");

								
							}
							bufferedReader.close();
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			} else {
				if (k.getName().endsWith(".java")) {
					try {

						FileReader fileReader = new FileReader(k);
						BufferedReader bufferedReader = new BufferedReader(
								fileReader);
						while (bufferedReader.readLine() != null) {
							s++;
						}
						bufferedReader.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		System.out.println("\n [ Lineas de Codigo: " + s + " ]");

		"030919302".substring(0,"030919302".length()-2);
		Integer.parseInt("030919302".substring("030919302".length()-2,"030919302".length()));
	}

}
