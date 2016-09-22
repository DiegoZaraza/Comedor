package comedor.carnets;


import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.Serializable;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

import org.jdesktop.swingx.JXImageView;


public class PanelImage extends JLabel implements KeyListener,Serializable{


	private static final long serialVersionUID = 1L;
	static ResizableBorder m=new ResizableBorder(8);
	private JXImageView imageView;
	private File fileImageSelected = null;
	private String identificador="";
	private Carnet carnet;
	
    public File getFileImageSelected() {
		return fileImageSelected;
	}
	public void setFileImageSelected(File fileImageSelected) {
		this.fileImageSelected = fileImageSelected;
	}
	public PanelImage(final JXImageView comp,JXImageView im, Object s,Carnet carnets,String identificador) {
        this(comp,m);
        s.toString();
        setOpaque(false);
        this.carnet = carnets;
        this.identificador=identificador;
        setBounds(20,20,200,100);
        this.imageView = im;
        setFont(new Font("ärial",Font.BOLD,11));
		setHorizontalAlignment(SwingConstants.CENTER);
        setText("Imagen");
        
        comp.setLayout(null);
        JPopupMenu popupMenu = new JPopupMenu();
        
        JMenuItem jMenu = new JMenuItem("Agregar Imagen...");

	        jMenu.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					JFileChooser chooser = new JFileChooser();
					int y = chooser.showOpenDialog(null);
					if(y == JFileChooser.APPROVE_OPTION){
						File b = chooser.getSelectedFile();
						try {
							fileImageSelected = b;
//							setImage(b);
							
							ImageIcon fot = new ImageIcon(b.toString());
							Icon icono = new ImageIcon(fot.getImage().getScaledInstance(getWidth(),getHeight(), Image.SCALE_DEFAULT));
							setIcon(icono);
							repaint();
						
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			});
	 
	        
//        }
	        popupMenu.add(jMenu);
        setComponentPopupMenu(popupMenu);
//     
    
    }
    public ResizableBorder get(){
    	return m;
    }

    public PanelImage(JPanel comp, ResizableBorder border) {
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
	public void setImagen(File file) {
		ImageIcon fot = new ImageIcon(file.toString());
		Icon icono = new ImageIcon(fot.getImage().getScaledInstance(getWidth(),getHeight(), Image.SCALE_DEFAULT));
		setIcon(icono);
		setText("");
		repaint();
		fileImageSelected = file;
	}
}