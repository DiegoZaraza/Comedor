package comedor.carnets;


import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

import jbarcodebean.JBarcodeBean;

import net.sourceforge.jbarcodebean.model.Code128;
import org.jdesktop.swingx.JXImageView;
import org.jdesktop.swingx.JXLabel;


@SuppressWarnings({ "deprecation" })
public class PanelCodigoBarra extends JXLabel implements KeyListener,Serializable{


	private static final long serialVersionUID = 1L;
	static ResizableBorder m=new ResizableBorder(8);
	private JXImageView imageView;
	private String identificador="";
	private Carnet carnet;
	private JBarcodeBean barcode;
	private BufferedImage bufferedImage=null;
	
	private String text="01010101";
	
	public String getText() {
		return "$"+identificador+"{"+text+"}";
	}
	public void setText(String text) {
		this.text = text;
	}
	public PanelCodigoBarra(final JXImageView comp,JXImageView im, Object s,Carnet carnets,String identificador, String texto) {
        this(comp,m);
        s.toString();
        setOpaque(false);
        this.carnet = carnets;
        this.identificador=identificador;
        setBounds(20,20,200,100);
        this.imageView = im;
        this.text = texto;
//        setFont(new Font("ärial",Font.BOLD,11));
		setHorizontalAlignment(SwingConstants.CENTER);
//        setText("Imagen");
        
        comp.setLayout(null);
      
        
	    barcode = new JBarcodeBean();

    
        barcode.setCodeType(new Code128() );
//        barcode.setCodeType(new Code39());
 
        barcode.setCode(text);
        barcode.setCheckDigit(true);
//        setText("sa");
        barcode.setShowText(false);
//        BufferedImage bufferedImage = barcode.draw(new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB));
//        setIcon(new ImageIcon(bufferedImage));
       
//        resize();
    }
	@Override
	public void paintComponent(Graphics g) {
		setOpaque(false);
		if(bufferedImage==null)
	    bufferedImage = barcode.draw(new BufferedImage(200, 100, BufferedImage.TYPE_INT_RGB));
//		System.out.println(getWidth()+" - "+getHeight());
		g.drawImage(new ImageIcon(bufferedImage).getImage(), 0, 0, getWidth(), getHeight(),null);
		repaint();
		
	   }
    public ResizableBorder get(){
    	return m;
    }

    public PanelCodigoBarra(JPanel comp, ResizableBorder border) {
        comp.setLayout(new FlowLayout());
        addMouseListener(resizeListener);
        addMouseMotionListener(resizeListener);
        addKeyListener(this);
        setBorder(border);
 
      
    }

    private void resize() {
    	{
    		repaint();
    		
    	}
        if (getParent() != null) {
            ((JComponent) getParent()).revalidate();
            
        }
        
//        BufferedImage bufferedImage = barcode.draw(new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB));
//        setIcon(new ImageIcon(bufferedImage));
    }

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


        private int cursor;
        private Point startPos = null;

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

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == KeyEvent.VK_DELETE){
			imageView.remove(this);
			imageView.repaint();
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
        repaint();
	}
	
}