package comedor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.leyer.JKComboBox;

public class Camara extends JDialog implements Runnable,WindowListener
{
	private static final long serialVersionUID = 1L;
	private File fileFotox;
	private Webcam webcam;
	@SuppressWarnings("unused")
	private ComedorGUI principal;
	private WebcamPanel panelWebCam;
	private List<Webcam> list;
	private JKComboBox comboBox;
	private JButton button;
	private JButton button2;
	private BufferedImage image = null;

	public void resize(FileInputStream fileInputStream, FileOutputStream fileOutputStream, int width, int height) throws Exception 
	{
		BufferedImage src = ImageIO.read(fileInputStream);
		BufferedImage dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = dest.createGraphics();
		AffineTransform at = AffineTransform.getScaleInstance((double)width / src.getWidth(), (double)height / src.getHeight());
		g.drawRenderedImage(src, at);
		ImageIO.write(dest, "JPG", fileOutputStream);
		fileOutputStream.close();
	}

	public Camara(final ComedorGUI principal, final RegistrarProfesores registrarProfesor) 
	{
		super(principal,true);
		this.principal=principal;
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		list = Webcam.getWebcams();

		comboBox=new JKComboBox();
		comboBox.addActionListener(
				new ActionListener() 
				{	
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						new Thread(new Runnable() 
						{	
							@Override
							public void run() 
							{
								if(panelWebCam!=null)
								{
									remove(panelWebCam);

									try
									{
										Webcam.shutdown();
										Thread.sleep(3000);
									}
									catch(Exception e){};

									webcam = list.get(comboBox.getSelectedIndex());
									webcam.setViewSize(WebcamResolution.VGA.getSize());

									panelWebCam = new WebcamPanel(webcam);

									panelWebCam.setFPSDisplayed(true);
									panelWebCam.setDisplayDebugInfo(true);
									panelWebCam.setImageSizeDisplayed(true);
									panelWebCam.setMirrored(true);;
									add(panelWebCam,BorderLayout.CENTER);
									panelWebCam.updateUI();
								}
							}
						}).start();
					}
				});

		Iterator<Webcam> iter = list.iterator();

		while(iter.hasNext())
		{
			Webcam f = iter.next();
			comboBox.addItem(f.getName());
		}

		setLayout(new BorderLayout());
		add(comboBox,BorderLayout.NORTH);
		JPanel panelx = new JPanel(new GridLayout());
		button = new JButton("Tomar Foto");
		button.setIcon(new ImageIcon(DialogoLogin.class.getResource("/resource/camara.png")));

		button.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				image = webcam.getImage();

				try 
				{				
					fileFotox = new File("System-Comedor" + File.separator + "Fotos" + File.separator + "foto-" + registrarProfesor.getDocumento()+".jpg");
					ImageIO.setUseCache(true);
					/** AJUSTE IMAGEN DE IMPRERIOS */
					image = image.getSubimage((image.getWidth() / 2) - 240, (image.getHeight() / 2) - 320, 480, 640);
					
					ImageIO.write(image, "PNG", fileFotox);
					principal.getBaseDeDatos().insertFotos(fileFotox);

					ImageIO.write(image, "PNG", new File("System-Comedor" + File.separator + "Fotos" + File.separator + "foto-" + registrarProfesor.getDocumento() + ".jpg"));

					registrarProfesor.setFoto(image, fileFotox);

					try
					{
						Thread.sleep(1000);
					}catch(Exception e){};
					dispose();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});

		button2=new JButton("Cancelar");
		button2.setIcon(new ImageIcon(DialogoLogin.class.getResource("/resource/close1.png")));

		button2.addActionListener(
				new ActionListener() 
				{
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						try
						{
							if(webcam.isOpen())
								webcam.close();
							dispose();
						}catch(Exception e){ }
					}
				});

		panelx.add(button2);
		panelx.add(button);
		panelx.setPreferredSize(new Dimension(0, 40));
		add(panelx, BorderLayout.SOUTH);
		webcam = Webcam.getDefault();

		if(webcam==null)
		{
			JOptionPane.showMessageDialog(principal, "No se ha encontrado ningun dispositivo!","",JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(!webcam.isOpen())
			webcam.setViewSize(WebcamResolution.VGA.getSize());
		else
		{
			try
			{
				webcam.close();
				webcam = Webcam.getDefault();
				webcam.setViewSize(WebcamResolution.VGA.getSize());
			}
			catch(Exception e){}
		}

		panelWebCam = new WebcamPanel(webcam);

		panelWebCam.setFPSDisplayed(true);
		panelWebCam.setDisplayDebugInfo(true);
		panelWebCam.setImageSizeDisplayed(true);
		panelWebCam.setMirrored(true);

		add(panelWebCam,BorderLayout.CENTER);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		setSize(300,400);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(principal);
		setTitle("[ Tomar Foto ]");
		setVisible(true);

		new Thread(this).start();
	}

	public Camara(final ComedorGUI principal, final RegistrarNoDocentes registrarProfesor) 
	{
		super(principal, true);
		this.principal = principal;
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		list = Webcam.getWebcams();

		comboBox=new JKComboBox();
		comboBox.addActionListener(
				new ActionListener() 
				{
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						new Thread(
								new Runnable() 
								{
									@Override
									public void run() 
									{
										if(panelWebCam!=null)
										{
											remove(panelWebCam);

											try
											{
												Webcam.shutdown();
												Thread.sleep(3000);
											}
											catch(Exception e){};

											webcam = list.get(comboBox.getSelectedIndex());
											webcam.setViewSize(WebcamResolution.VGA.getSize());

											panelWebCam = new WebcamPanel(webcam);

											panelWebCam.setFPSDisplayed(true);
											panelWebCam.setDisplayDebugInfo(true);
											panelWebCam.setImageSizeDisplayed(true);
											panelWebCam.setMirrored(true);;
											add(panelWebCam,BorderLayout.CENTER);
											panelWebCam.updateUI();
										}
									}
								}).start();
					}
				});

		Iterator<Webcam> iter = list.iterator();

		while(iter.hasNext())
		{
			Webcam f = iter.next();
			comboBox.addItem(f.getName());
		}

		setLayout(new BorderLayout());
		add(comboBox, BorderLayout.NORTH);

		JPanel panelx = new JPanel(new GridLayout());
		button = new JButton("Tomar Foto");
		button.setIcon(new ImageIcon(DialogoLogin.class.getResource("/resource/camara.png")));
		button.addActionListener(
				new ActionListener() 
				{
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						image = webcam.getImage();

						try 
						{				
							fileFotox = new File("System-Comedor" + File.separator + "Fotos" + File.separator + "" + registrarProfesor.getDocumento() + ".jpg");
							ImageIO.setUseCache(true);

							ImageIO.write(image, "PNG", fileFotox);
							principal.getBaseDeDatos().insertFotos(fileFotox);

							registrarProfesor.setFoto(image,new File("System-Comedor" + File.separator + "Fotos" + File.separator + "" + registrarProfesor.getDocumento() + ".jpg"));

							try
							{
								Thread.sleep(1000);
							}
							catch(Exception e){};

							dispose();
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
				});

		button2=new JButton("Cancelar");
		button2.setIcon(new ImageIcon(DialogoLogin.class.getResource("/resource/close1.png")));

		button2.addActionListener(
				new ActionListener() 
				{
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						try
						{
							if(webcam.isOpen())
								webcam.close();

							dispose();
						}
						catch(Exception e){ }
					}
				});

		panelx.add(button2);
		panelx.add(button);
		panelx.setPreferredSize(new Dimension(0, 40));
		add(panelx, BorderLayout.SOUTH);
		webcam = Webcam.getDefault();

		if(!webcam.isOpen())
			webcam.setViewSize(WebcamResolution.VGA.getSize());
		else
		{
			try
			{
				webcam.close();
				webcam = Webcam.getDefault();
				webcam.setViewSize(WebcamResolution.VGA.getSize());
			}
			catch(Exception e){}
		}

		panelWebCam = new WebcamPanel(webcam);
		panelWebCam.setFPSDisplayed(true);
		panelWebCam.setDisplayDebugInfo(true);
		panelWebCam.setImageSizeDisplayed(true);
		panelWebCam.setMirrored(true);

		add(panelWebCam, BorderLayout.CENTER);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(300, 400);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(principal);
		setTitle("[ Tomar Foto ]");
		setVisible(true);
		new Thread(this).start();
	}

	public Camara(final ComedorGUI principal, final RegistrarAlumno internalAlumno) 
	{
		super(principal, true);
		this.principal  =principal;
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		list = Webcam.getWebcams();

		comboBox = new JKComboBox();

		comboBox.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				new Thread(
						new Runnable() 
						{
							@Override
							public void run() 
							{
								if(panelWebCam != null)
								{
									remove(panelWebCam);

									try
									{
										Webcam.shutdown();
										Thread.sleep(3000);
									}
									catch(Exception e){};

									webcam = list.get(comboBox.getSelectedIndex());
									webcam.setViewSize(WebcamResolution.VGA.getSize());

									panelWebCam = new WebcamPanel(webcam);

									panelWebCam.setFPSDisplayed(true);
									panelWebCam.setDisplayDebugInfo(true);
									panelWebCam.setImageSizeDisplayed(true);
									panelWebCam.setMirrored(true);;
									add(panelWebCam,BorderLayout.CENTER);
									panelWebCam.updateUI();
								}
							}
						}).start();
			}
		});

		Iterator<Webcam> iter = list.iterator();

		while(iter.hasNext())
		{
			Webcam f = iter.next();
			comboBox.addItem(f.getName());
		}

		setLayout(new BorderLayout());
		add(comboBox, BorderLayout.NORTH);

		JPanel panelx = new JPanel(new GridLayout());
		button=new JButton("Tomar Foto");
		button.setIcon(new ImageIcon(DialogoLogin.class.getResource("/resource/camara.png")));

		button.addActionListener(
				new ActionListener() 
				{
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						image = webcam.getImage();

						try 
						{				
							fileFotox = new File("System-Comedor" + File.separator + "Fotos" + File.separator + "" + internalAlumno.getNia() + ".jpg");
							ImageIO.setUseCache(true);
							ImageIO.write(image, "PNG", fileFotox);
							principal.getBaseDeDatos().insertFotos(fileFotox);
							internalAlumno.setFoto(image, new File("System-Comedor" + File.separator + "Fotos" + File.separator + "" + internalAlumno.getNia() + ".jpg"));

							try
							{
								Thread.sleep(1000);
							}
							catch(Exception e){};
							dispose();
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
				});

		button2 = new JButton("Cancelar");
		button2.setIcon(new ImageIcon(DialogoLogin.class.getResource("/resource/close1.png")));

		button2.addActionListener(
				new ActionListener() 
				{
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						try
						{
							if(webcam.isOpen())
								webcam.close();

							dispose();
						}
						catch(Exception e){ }
					}
				});

		panelx.add(button2);
		panelx.add(button);
		panelx.setPreferredSize(new Dimension(0,40));
		add(panelx,BorderLayout.SOUTH);
		webcam = Webcam.getDefault();

		if(!webcam.isOpen())
			webcam.setViewSize(WebcamResolution.VGA.getSize());
		else
			try
		{
				webcam.close();
				webcam = Webcam.getDefault();
				webcam.setViewSize(WebcamResolution.VGA.getSize());
		}
		catch(Exception e){}

		panelWebCam = new WebcamPanel(webcam);

		panelWebCam.setFPSDisplayed(true);
		panelWebCam.setDisplayDebugInfo(true);
		panelWebCam.setImageSizeDisplayed(true);
		panelWebCam.setMirrored(true);

		add(panelWebCam,BorderLayout.CENTER);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(300,400);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(principal);
		setTitle("[ Tomar Foto ]");
		setVisible(true);

		new Thread(this).start();
	}

	public BufferedImage getImage() 
	{
		return image;
	}

	public void setImage(BufferedImage image) 
	{
		this.image = image;
	}

	@Override
	public void run() {}

	@Override
	public void windowActivated(WindowEvent arg0) { }

	@Override
	public void windowClosed(WindowEvent arg0) { }

	@Override
	public void windowClosing(WindowEvent arg0) 
	{
		try
		{
			if(webcam.isOpen())
				webcam.close();

			dispose();
		}
		catch(Exception e){ }
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) { }

	@Override
	public void windowDeiconified(WindowEvent arg0) { }

	@Override
	public void windowIconified(WindowEvent arg0) { }

	@Override
	public void windowOpened(WindowEvent arg0) { }
}