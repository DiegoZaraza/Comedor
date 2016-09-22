package comedor.carnets;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

import org.jdesktop.swingx.JXImageView;

public class PanelTexto extends JLabel implements KeyListener,Serializable{
	private static final long serialVersionUID = 1L;
	static ResizableBorder m=new ResizableBorder(8);
	private JXImageView imageView;
	private File fileImageSelected = null;
	private Carnet carnet;
	private String text ="<HTML><BODY>";
	private String identificador;

	public float getTransparencia(){
		return  transparencia;
	}
	
	private String formatText="";
	
	public String getTextFormat(){
		return formatText;
	}
	
	private float transparencia=0;
	
	@SuppressWarnings("unchecked")
	public PanelTexto(final JPanel jPanel,final JXImageView im, Object s,final Carnet carnet,final  String identificador)
	{
		this(jPanel,m);
		s.toString();
		setOpaque(false);
		this.identificador = identificador;
		this.carnet=carnet;
		//        setSize(300, 100);
		setBounds(10, 10,200,100);
		this.imageView = im;
		//        setOpaque(false);
		jPanel.setLayout(null);
		setFont(new Font("Arial",Font.BOLD,11));
		setHorizontalAlignment(SwingConstants.CENTER);
		setText("Texto");
		JSlider slider_1 = new JSlider(0,10);
		//		slider_1.setValue(1);
		slider_1.setMinorTickSpacing(0);
		slider_1.setPaintTicks(true);
		slider_1.setMajorTickSpacing(10);
		slider_1.setPaintLabels(true);
		slider_1.setBorder(BorderFactory.createTitledBorder("Transparencia"));
		@SuppressWarnings("rawtypes")
		Hashtable labelTable = new Hashtable();
		labelTable.put( new Integer( 0 ), new JLabel("0.0") );
		labelTable.put( new Integer( 5 ), new JLabel("0.5") );
		labelTable.put( new Integer( 10 ), new JLabel("1.0") );
		slider_1.setLabelTable( labelTable );
		
		//	    
		slider_1.addChangeListener(new ChangeListener() 
		{
			@Override
			public void stateChanged(ChangeEvent e)
			{
				int value=((JSlider)e.getSource()).getValue();
				double b=0.1;
				float c=(float)b*value;
				//					System.out.println(c);
				setBackground(new Color(0,0,0,c));
				transparencia=c;
				//					setScale(b*value);
				//					imageView.requestFocus();
			}
		});

		JPopupMenu popupMenu=new JPopupMenu();
		JMenuItem item=new JMenuItem("Modificar Texto");
		item.setIcon(new ImageIcon(getClass().getResource("/resource/text.png")));
		JMenuItem item2=new JMenuItem("Selector de Fuente");
		item2.setIcon(new ImageIcon(getClass().getResource("/resource/iconTexto.png")));
		JMenuItem item3=new JMenuItem("Selector de Color");
		item3.setIcon(new ImageIcon(getClass().getResource("/resource/color.png")));

		item3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new JColorChooser();
				Color s=JColorChooser.showDialog(carnet, "Color de Texto", Color.black);
				setForeground(s);
			}
		});
		item2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				Font font = null;
				font = FontChooser.showDialog(null, null, null,PanelTexto.this.carnet);


				setFont(font);
			}

		});
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				final JDialog dialog=new JDialog(new JFrame(),true);
				final JTextArea are=new JTextArea();
				are.setLineWrap(true);
				are.setWrapStyleWord(true);
				JScrollPane scrollPane=new JScrollPane(are);
				dialog.setLayout(new BorderLayout());
				dialog.add(scrollPane,BorderLayout.CENTER);
				JButton button=new JButton("Aceptar");
				JButton button2=new JButton("Cancelar");
				JPanel panel=new JPanel(new GridLayout());
				panel.add(button2);
				panel.add(button);
				//				String h=text.replaceAll("</BODY></HTML>", "");
				text=text.replaceAll("</BODY></HTML>", "");
				text=text.replaceAll("<HTML><BODY>", "");
				text=text.replaceAll("<center>", "");
				text=text.replaceAll("</center>", "");
				text=text.replaceAll("<div style='text-align:justify'>", "");
				text=text.replaceAll("</div>", "");
				final JToggleButton b1=new JToggleButton("Centrado");
				final JToggleButton b2=new JToggleButton("Justificado");
				are.setText(""+text);
				dialog.add(panel,BorderLayout.SOUTH);
				button.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						String x1="";
						String x2="";
						if(b1.isSelected()){
							x1="<center>";
							x2="</center>";
						}
						if(b2.isSelected()){
							x1="<div style='text-align:justify'>"; x2="</div>";
						}

						text="<HTML><BODY>"+x1+are.getText()+x2+"</BODY></HTML>";
						//						"$"+identificador+"{"+formatText+"}";
						formatText="<HTML><BODY>"+x1+"$"+identificador+"{"+are.getText()+"}"+x2+"</BODY></HTML>";
						//						System.out.println(text);

						setText(text);
						dialog.dispose();
					}
				});
				button2.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						dialog.dispose();
					}
				});
				JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));

				b1.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						// TODO Auto-generated method stub
						if(b2.isSelected()){
							b2.setSelected(false);
						}
					}
				});

				b2.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if(b1.isSelected()){
							b1.setSelected(false);
						}
					}
				});
				panel2.add(b1);
				panel2.add(b2);
				b1.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub

					}
				});
				dialog.add(panel2,BorderLayout.NORTH);
				dialog.setSize(300,200);
				dialog.setLocationRelativeTo(carnet);
				dialog.setTitle("Texto");
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		popupMenu.add(item);
		popupMenu.add(new JSeparator());
		popupMenu.add(item2);
		popupMenu.add(new JSeparator());
		popupMenu.add(item3);
		popupMenu.add(new JSeparator());
		popupMenu.add(new JSeparator());
		popupMenu.add(slider_1);
		setComponentPopupMenu(popupMenu);
	}
	public ResizableBorder get(){
		return m;
	}

	public PanelTexto(JPanel comp, ResizableBorder border) {
		comp.setLayout(new FlowLayout());
		addMouseListener(resizeListener);
		addMouseMotionListener(resizeListener);
		addKeyListener(this);
		setBorder(border);

	}

	private void resize() {
		{
			if(fileImageSelected!=null){
				ImageIcon fot = new ImageIcon(fileImageSelected.toString());
				Icon icono = new ImageIcon(fot.getImage().getScaledInstance(getWidth(),getHeight(), Image.SCALE_DEFAULT));
				setIcon(icono);
				repaint();
			}
		}
		if (getParent() != null) {
			((JComponent) getParent()).revalidate();
		}
	}

	private int cursor;
	private Point startPos = null;

	MouseInputListener resizeListener = new MouseInputAdapter() {

		@Override
		public void mouseMoved(MouseEvent me) {
			if (hasFocus()) {
				ResizableBorder border = (ResizableBorder) getBorder();
				setCursor(Cursor.getPredefinedCursor(border.getCursor(me)));
			}
		}

		@Override
		public void mouseExited(MouseEvent mouseEvent) {
			setCursor(Cursor.getDefaultCursor());
			setFocusable(false);
			repaint();
		}


		@Override
		public void mouseEntered(MouseEvent arg0) {
			setFocusable(true);
			repaint();
		}

		@Override
		public void mousePressed(MouseEvent me) {

			ResizableBorder border = (ResizableBorder) getBorder();
			cursor = border.getCursor(me);
			startPos = me.getPoint();
			requestFocus();

			repaint();
		}

		@Override
		public void mouseDragged(MouseEvent me) {

			if (startPos != null) {

				int x = getX();
				int y = getY();
				int w = getWidth();
				int h = getHeight();

				int dx = me.getX() - startPos.x;
				int dy = me.getY() - startPos.y;

				switch (cursor) {
				case Cursor.N_RESIZE_CURSOR:
					if (!(h - dy < 50)) {
						setBounds(x, y + dy, w, h - dy);
						resize();
					}
					break;

				case Cursor.S_RESIZE_CURSOR:
					if (!(h + dy < 50)) {
						setBounds(x, y, w, h + dy);
						startPos = me.getPoint();
						resize();
					}
					break;

				case Cursor.W_RESIZE_CURSOR:
					if (!(w - dx < 50)) {
						setBounds(x + dx, y, w - dx, h);
						resize();
					}
					break;

				case Cursor.E_RESIZE_CURSOR:
					if (!(w + dx < 50)) {
						setBounds(x, y, w + dx, h);
						startPos = me.getPoint();
						resize();
					}
					break;

					//----------------
				case Cursor.NW_RESIZE_CURSOR:


					if (!(w - dx < 50) && !(h - dy < 50)) {
						setBounds(x + dx, y + dy, w - dx, h - dy);
						resize();
					}


					break;

				case Cursor.NE_RESIZE_CURSOR:
					if (!(w + dx < 50) && !(h - dy < 50)) {
						setBounds(x, y + dy, w + dx, h - dy);
						startPos = new Point(me.getX(), startPos.y);
						resize();
					}
					break;

				case Cursor.SW_RESIZE_CURSOR:
					if (!(w - dx < 50) && !(h + dy < 50)) {
						setBounds(x + dx, y, w - dx, h + dy);
						startPos = new Point(startPos.x, me.getY());
						resize();
					}
					break;

				case Cursor.SE_RESIZE_CURSOR:
					if (!(w + dx < 50) && !(h + dy < 50)) {
						setBounds(x, y, w + dx, h + dy);
						startPos = me.getPoint();
						resize();
					}
					break;




				case Cursor.MOVE_CURSOR:
					Rectangle bounds = getBounds();
					bounds.translate(dx, dy);
					setBounds(bounds);
					resize();
				}

				setCursor(Cursor.getPredefinedCursor(cursor));
			}
		}

		@Override
		public void mouseReleased(MouseEvent mouseEvent) {
			startPos = null;
		}
	};
	int angle = 0;
	double x = getWidth()/2.0;
	double y = getHeight()/2.0;
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;




		double x = getWidth()/2.0;
		double y = getHeight()/2.0;
		g2.rotate(Math.toRadians(angle), x, y);


		//		
		g2.setColor(getBackground());
		g2.fillRect(0, 0, getWidth(), getHeight());

		super.paintComponent(g);
	}
	// 
	//   
	//
	//    @Override
	//    protected void paintComponent(Graphics g) {
	//        Graphics2D g2d = (Graphics2D) g.create();
	//        g2d.setColor(getBackground());
	//        g2d.fillRect(0, 0, getWidth(), getHeight());
	//        super.paintComponent(g2d);
	//        g2d.dispose();
	//    }

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == KeyEvent.VK_DELETE){
			imageView.remove(this);
			carnet.removeElement(this,identificador);
			imageView.repaint();
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	public void selected() {
		setFocusable(true);

		requestFocus();

		//         System.out.println(sta);

		repaint();

	}
	public void setBackgroundImage(String background) {
		try {
			imageView.setImage(new File(background));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void setBackgroundScale(double parseDouble) {
		imageView.setScale(parseDouble);
	}
	public void setTransparencia(float t) {
		setBackground(new Color(0,0,0,t));
		transparencia=t;

	}
	public void setFormatText(String texto) {
		// TODO Auto-generated method stub
		formatText=text;

	}
}