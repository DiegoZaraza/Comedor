package comedor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.leyer.JKCoreBar;
import com.leyer.JKMenu;
import com.leyer.JKTable;

import comedor.carnets.Carnet;
import comedor.pdfs.Persona;
import javax.swing.ImageIcon;

public class PanelCarnetsProfesores extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JKTable table;

	/**
	 * Create the panel.
	 */
	private ComedorGUI principal;

	private JTabbedPane jTabbedPane;

	private JKMenu jkMenu;

	private JProgressBar progressBar;

	private JPanel panel3;

	private JRadioButton b1;

	private JRadioButton b2;
	

	
	public PanelCarnetsProfesores(final ComedorGUI principal,final ImpresionCarnets impresionCarnets) {
		this.principal=principal;
		
		final JKCoreBar coreBar=new JKCoreBar(JKCoreBar.MODE_1);
		jkMenu=new JKMenu("Por Profesor", JKCoreBar.MODE_1);
		coreBar.add(jkMenu);
		
		JPanel jpanel=new JPanel(new BorderLayout());
		JPanel panel2=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel jLabel=new JLabel("Documento/Nombre: ");
		panel2.add(jLabel);
		final JTextField textField=new JTextField(35);
		panel2.add(textField);
		textField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				table.search(textField.getText().toUpperCase());
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		table=new JKTable();
		table.addColumn("Apellidos y Nombres");
		table.addColumn("Documento");
		
		
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getClickCount()==1){
//					jkMenu.setVisible(false);
					jkMenu.setPopupMenuVisible(false);
					int t=JOptionPane.showConfirmDialog(principal, "Imprirmir Carnet?","Confirmar",JOptionPane.INFORMATION_MESSAGE);
					if(t == JOptionPane.OK_OPTION){
						clearTabs();
						new Thread(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								progressBar.setString("Generando Carnets...");
								progressBar.setIndeterminate(true);
								
								Carnet carnetFrontal=new Carnet(1,PanelCarnetsProfesores.this.principal);
								String nia=table.getValueAt(table.getSelectedRow(), 1).toString();
								
								String g[]=xml.split(">");
								
								Hashtable<String, String> hashtable=new Hashtable<>();
								Persona s = principal.getBaseDeDatos().getProfesor(nia);
//								principal.getBaseDeDatos().getProfesores(nia);
								
//								hashtable.put("fechaNacimiento", ""+s.getFechaNacimiento());
								hashtable.put("profesor", "PROFESOR");
								hashtable.put("curso", principal.getBaseDeDatos().getCursoActual());
//								hashtable.put("nia", s.getNia());
								if(s.isFotoVerificada()){
									hashtable.put("foto", "System-Comedor"+File.separator+"Fotos"+File.separator+""+s.getDocumento()+".jpg");
									}else
									{
										JOptionPane.showMessageDialog(principal, "Este Profesor no posee Foto","Sin Foto",JOptionPane.WARNING_MESSAGE);
										progressBar.setIndeterminate(false);
						 	    		progressBar.setString("Fallido!");
										return;
									}
								hashtable.put("documento", s.getDocumento());
								hashtable.put("nombre", ""+s.getNombreCompleto());
								hashtable.put("codigo_barra", s.getDocumento());	
								
						 		String xmlFinal="";
						 		for(int index=0;index<g.length;index++){
						 			String ja=g[index]+">";
						 			if(ja.startsWith("<image background=")){
						 				String p1=ja.substring(0,ja.indexOf("=")+2);
						 				p1+=new File(".")
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
//						 		System.out.println(xmlFinal);
						 		}
//						 		System.out.println(xmlFinal);
						 		try{
							 		File file=new File("System-Comedor"+File.separator+"Temp"+File.separator+hashtable.get("documento")+".xml");
							 		
							 		Writer w = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
							 		w.write(xmlFinal);
							 		w.flush();
							 		w.close();
							 		
							 		carnetFrontal.setPreferredSize(new Dimension(844,455));
									carnetFrontal.setBorder(BorderFactory.createEmptyBorder(0, 0, 13, 85));
							 		carnetFrontal.setModo(2);
							 		carnetFrontal.setPathCarnet(new File(".")
					 				.getAbsolutePath().substring(0,
					 						new File(".").getAbsolutePath().length() - 2).toString()+""+File.separator+"System-Comedor"+File.separator+"Temp"+File.separator+""+s.getDocumento()+".pdf");
							 		jTabbedPane.addTab(""+s.getDocumento(), carnetFrontal);
							 		try{
//							 		    jTabbedPane.setSelectedIndex(jTabbedPane.getTabCount()-1);
							 		}catch(Exception e){
							 			
							 		}
							 		carnetFrontal.loadConfiguration(file);
							 		
							 	    while(true){
							 	    	if(carnetFrontal.isReady()){
//							 	    		System.out.println("Si");
							 	    		Thread.sleep(2000);
							 	    		if(!isVistaPrevias())
							 	    		progressBar.setString("Imprimiendo...");
//											progressBar.setValue(index+1);
							 	    		progressBar.setIndeterminate(false);
							 	    		progressBar.setString("Completado!");
							 	    		if(!isVistaPrevias())
							 	    		carnetFrontal.imprimir();
							 	    		break;
							 	    	}else{
//							 	    		System.out.println("No");
							 	    		Thread.sleep(2000);
							 	    	}
							 	    }
							 
									
						 		}catch(Exception e){
						 			e.printStackTrace();
						 		}
							}
						}).start();
						
						
				 		
						
					
							
					}
				}		
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		table.setFont(new Font("arial",Font.BOLD,12));
		JScrollPane scrollPane=new JScrollPane(table);
		
		jpanel.add(panel2, BorderLayout.NORTH);
		jpanel.add(scrollPane,BorderLayout.CENTER);
		jkMenu.add(jpanel);
		jkMenu.setFont(new Font("arial",Font.BOLD,12));
		coreBar.add(jkMenu);
		setLayout(new BorderLayout());
		JPanel x=new JPanel(new FlowLayout(FlowLayout.LEFT));
		x.add(coreBar);
		coreBar.setPreferredSize(new Dimension(130,24));
		add(x,BorderLayout.NORTH);
		panel3=new JPanel(new BorderLayout());
		jTabbedPane=new JTabbedPane();
		jTabbedPane.setTabPlacement(JTabbedPane.RIGHT);
		jTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		progressBar=new JProgressBar();
		progressBar.setStringPainted(true);
		
		ButtonGroup buttonGroup=new ButtonGroup();
		b1=new JRadioButton("Impresion Directa");
		b2=new JRadioButton("Solo Vistas Previas");
		b2.setSelected(true);
		buttonGroup.add(b1);
		buttonGroup.add(b2);
		
		JButton btnNewButton = new JButton("Impirmir Orla General");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

//				jkMenu2.setPopupMenuVisible(false);
				int t=JOptionPane.showConfirmDialog(principal, "Imprirmir Carnets?","Confirmar",JOptionPane.INFORMATION_MESSAGE);
				if(t == JOptionPane.OK_OPTION){
					progressBar.setIndeterminate(true);
					clearTabs();
					final Timer timer=new Timer();
					final TimerTask timerTask=new TimerTask() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
//							System.out.println("/"+count);
							if(max!=-1){
									if(max==count){
										timer.cancel();
										progressBar.setIndeterminate(false);
										if(!isVistaPrevias())
										progressBar.setString("Imprimiendo...");
										progressBar.setMaximum(max);
										if(!isVistaPrevias())
										for(int index=0;index<jTabbedPane.getTabCount();index++){
											Carnet carnet=(Carnet)jTabbedPane.getComponentAt(index);
											if(!isVistaPrevias())
											carnet.imprimir();
											progressBar.setString("Imprimiendo ("+(index+1)+")...");
											progressBar.setValue(index+1);
										}
										if(isVistaPrevias()){
											progressBar.setValue(1000000);
										}
										progressBar.setString("Completado!");
										max=-1;
										count=0;
									}
								
							}
						}
					};
					
					timer.schedule(timerTask, 0, 1000);
					progressBar.setString("Cargando Alumnos...");
//					getPro
					
					final ArrayList<Persona> array = principal.getBaseDeDatos().getProfesores(principal.getBaseDeDatos().getCursoActual());
					max=0;
					for(int index=0;index<array.size();index++){
						if(array.get(index).isFotoVerificada()){
							max++;
						}
					}
					System.out.println(array);
					
					progressBar.setString("Generando Carnets...");
//					System.out.println("Cantidad: "+array.size());
					
					for(int index=0;index<array.size();index++){
						final int pos=index;
//						System.out.println(pos);
						new Thread(new Runnable() {
							
							@Override
							public synchronized void run() {
								// TODO Auto-generated method stub
								Carnet carnetFrontal=new Carnet(1,principal);
								
								String g[]=xml.split(">");
								
								Hashtable<String, String> hashtable=new Hashtable<>();
								Persona s = array.get(pos);
								
//								
//								hashtable.put("fechaNacimiento", ""+s.getFechaNacimiento());
								hashtable.put("profesor", "PROFESOR");
								hashtable.put("curso", principal.getBaseDeDatos().getCursoActual());
//								hashtable.put("nia", s.getNia());
								if(s.isFotoVerificada()){
									hashtable.put("foto", "System-Comedor"+File.separator+"Fotos"+File.separator+""+s.getDocumento()+".jpg");
									System.out.println(s.getDocumento());
								}else{
//										JOptionPane.showMessageDialog(principal, "Este Profesor no posee Foto","Sin Foto",JOptionPane.WARNING_MESSAGE);
//										progressBar.setIndeterminate(false);
//						 	    		progressBar.setString("Fallido!");
										return;
								}
								hashtable.put("documento", s.getDocumento());
								hashtable.put("nombre", ""+s.getNombreCompleto());
								hashtable.put("codigo_barra", s.getDocumento());	
								
						 		String xmlFinal="";
						 		for(int index=0;index<g.length;index++){
						 			String ja=g[index]+">";
						 			if(ja.startsWith("<image background=")){
						 				String p1=ja.substring(0,ja.indexOf("=")+2);
						 				p1+=new File(".")
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
//						 		System.out.println(xmlFinal);
						 		}
//						 		System.out.println(xmlFinal);
						 		try{
							 		File file=new File("System-Comedor"+File.separator+"Temp"+File.separator+hashtable.get("documento")+".xml");
							 		
							 		Writer w = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
							 		w.write(xmlFinal);
							 		w.flush();
							 		w.close();
							 		
							 		carnetFrontal.setPreferredSize(new Dimension(844,455));
									carnetFrontal.setBorder(BorderFactory.createEmptyBorder(0, 0, 13, 85));
							 		carnetFrontal.setModo(2);
							 		carnetFrontal.setPathCarnet(new File(".")
					 				.getAbsolutePath().substring(0,
					 						new File(".").getAbsolutePath().length() - 2).toString()+""+File.separator+"System-Comedor"+File.separator+"Temp"+File.separator+""+s.getDocumento()+".pdf");
//							 		System.out.println(s.getDocumento());
							 		synchronized (this) {
							 			addTab(s.getDocumento(), carnetFrontal);	
									}
							 		
							 		try{
//							 		    jTabbedPane.setSelectedIndex(jTabbedPane.getTabCount()-1);
							 		}catch(Exception e){
							 			
							 		}
							 		carnetFrontal.loadConfiguration(file);
							 		
							 		
							 	    while(true){
							 	    	if(carnetFrontal.isReady()){
//							 	    		System.out.println("Si");
							 	    		Thread.sleep(1000);
							 	    	
							 	    		synchronized (this) {
							 	    			count();
							 	    		}
//							 	    		.imprimir();
							 	    		break;
							 	    	}else{
							 	    		Thread.sleep(2000);
//							 	    		System.out.println("No");
							 	    	}
							 	    }
							 
									
						 		}catch(Exception e){
						 			e.printStackTrace();
						 		}
							}
						}).start();
	
					}
					
				}
			
				
			}
		});
		x.add(btnNewButton);
		x.add(b1);
		x.add(b2);
		x.add(progressBar);
		progressBar.setPreferredSize(new Dimension(280,24));
		jTabbedPane.setBackground(Color.WHITE);
		panel3.add(jTabbedPane,BorderLayout.CENTER);
		add(panel3,BorderLayout.CENTER);
		progressBar.setForeground(Color.ORANGE);
		JPanel panel4=new JPanel(new GridLayout());
		JButton button=new JButton("Cerrar");
		button.setIcon(new ImageIcon(PanelCarnetsProfesores.class.getResource("/resource/close.png")));
		panel4.add(button);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				impresionCarnets.dispose();
			}
		});
		new Thread(this).start();
		add(panel4,BorderLayout.SOUTH);
		button.setPreferredSize(new Dimension(0,25));
		setBackground(Color.WHITE);
	}
	private String xml="";
	private int max=-1;
	private int count=0;
	public synchronized void count(){
//		System.out.println(count);
		count++;
	}
	private void clearTabs() {
		// TODO Auto-generated method stub
		try{
			panel3.remove(jTabbedPane);
			jTabbedPane=new JTabbedPane();
			jTabbedPane.setTabPlacement(JTabbedPane.RIGHT);
			jTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
			panel3.add(jTabbedPane,BorderLayout.CENTER);
			panel3.updateUI();
//			for(int index=0;index<jTabbedPane.getTabCount();index++){
//					jTabbedPane.removeTabAt(index);
//					
//			}
//			jTabbedPane.removeAll();
		}catch(Exception e){
			
		}
	}
	public synchronized void addTab(String documento,Carnet carnet){
		jTabbedPane.addTab(":"+documento, carnet);
		jTabbedPane.repaint();
	}
	public boolean isVistaPrevias(){
		if(b2.isSelected()){
			return true;
		}else
			return false;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String cursoActual=principal.getBaseDeDatos().getCursoActual();
		ArrayList<Persona> xz = principal.getBaseDeDatos().getProfesores(cursoActual);
		for(int index=0;index<xz.size();index++){
			table.addRow(xz.get(index).getNombreCompleto(),xz.get(index).getDocumento());	
		}	
		
//		principal.getBaseDeDatos().getGroups(table2,new JLabel(),cursoActual);
		
		String line=null;
		
		BufferedReader bufferedReader;
		try {
			bufferedReader = new BufferedReader(
					   new InputStreamReader(new FileInputStream("System-Comedor"+File.separator+"Carnet_Frontal_Profesor.xml"), "UTF-8"));
			try {
				while((line=bufferedReader.readLine())!=null){
					xml+=line;
				}
				System.out.println(xml);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		String line=null;
//		Hashtable<String, String> hashtable=new Hashtable<>();
//		hashtable.put("fechaNacimiento", "29/03/1991");
//		hashtable.put("grupo", "LEYER");
//		hashtable.put("curso", "2016");
//		hashtable.put("nia", "20213944");
//		hashtable.put("foto", "C:\\46022932\\FotosNIA\\10016322.jpg");
//		hashtable.put("documento", "20213944F");
//		hashtable.put("nombre", "PAUL JOSE, FERRER ROMERO");
//		hashtable.put("codigo_barra", "8992819");	
	
	
		
	}
}
