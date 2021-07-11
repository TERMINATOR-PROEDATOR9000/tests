package ImageConvertor;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings(value = "serial")
class ParsedImagePreview extends JPanel implements MouseMotionListener, MouseListener, MouseWheelListener {

    Controller controller;
    List<?> forDraw;
    Short s;
    String figure;
    JFrame j;
    JScrollPane jsp;
    Point p;
    JFrame layers;
    List<JCheckBox> boxes;
    int width;
    int height;
    int count = 1;
    int layerCount = -1;
    boolean resized = false;

    public ParsedImagePreview(Controller controllerr) {
	controller = controllerr;
	forDraw = controller.getPointsList();
	s = controller.getChunkSize();
	figure = controller.getFigure();
	width = controller.getImageWidth();
	height = controller.getImageHeight();
	j = new JFrame();
	j.setTitle("Preview image");
	j.setSize(width, height);
	j.setLocationRelativeTo(null);

	boxes = new ArrayList<JCheckBox>();

	layers = new JFrame("Layer chooser");
	layers.setLocation(j.getLocation().x - 210, j.getLocation().y);
	// layers.setLocationRelativeTo(null);
	// layers.setLayout(new GridLayout(forDraw.size(), 1));
	layers.setLayout(new GridLayout(10, 3));
	layers.setSize(225, 300);// (forDraw.size()+1)*40);
	for (int i = 0; i < forDraw.size(); i++) {
	    JCheckBox temp = new JCheckBox("Layer " + (i + 1));
	    boxes.add(temp);
	    layers.add(temp);
	}
	int select = boxes.size() / 4;
	boxes.get(select).setSelected(true);
	layers.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	layerCount = -1;
	setSize(width, height);
	Graphics2D g2 = (Graphics2D) g;
	g2.setColor(Color.WHITE);
	g2.fillRect(0, 0, width, height);
	g2.setColor(Color.BLACK);
	g2.setStroke(new BasicStroke(controller.getStroke()));
	if (forDraw == null) {
	    return;
	}
	for (Object ppp : forDraw) {
	    layerCount++;
	    repaint();
	    if (!boxes.get(layerCount).isSelected()) {
		boxes.get(layerCount).setBackground(Color.DARK_GRAY);
		continue;
	    }
	    boxes.get(layerCount).setBackground(Color.GRAY);
	    /*
	     * if(skip>0) {
	     * skip--;
	     * continue;
	     * }else if(skip<0) {
	     * skip++;
	     * continue;
	     * }
	     */
	    List<Points> arr = (List<Points>) ppp;
	    Points pp = null;
	    for (Points pas : arr) {
		if (controller.isSingle()) {
		    pp = (Points) pas;
		} else {
		    try {
			pp = ((Future<Points>) ppp).get();
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    } catch (ExecutionException e) {
			e.printStackTrace();
		    }
		}
		if (pp.direction == Direction.STUB || pp.getLength() < s / 2) {
		    continue;
		}
		int x1;// = pp.startW;
		int y1;// = pp.startH;
		int x2;// = pp.endW;
		int y2;// = pp.endH;

		/*
		 * if (controller.isRandom() && pp.getLength() >= s - 1) {
		 * x1 = ThreadLocalRandom.current().nextInt(pp.startW - s, pp.startW + s) /
		 * count;
		 * y1 = ThreadLocalRandom.current().nextInt(pp.startH - s, pp.startH + s) /
		 * count;
		 * x2 = ThreadLocalRandom.current().nextInt(pp.endW - s, pp.endW + s) / count;
		 * y2 = ThreadLocalRandom.current().nextInt(pp.endH - s, pp.endH + s) / count;
		 * } else {
		 */

		x1 = pp.startW / count;
		y1 = pp.startH / count;
		x2 = pp.endW / count;
		y2 = pp.endH / count;

		// }
		switch (figure) {
		case "Circle": {
		    int rnd = pp.getLength() + ThreadLocalRandom.current().nextInt(s);
		    g2.drawArc(x1, y1, rnd, rnd, 0, 360);
		    break;
		}
		case "Line": {
		    g2.drawLine(x1, y1, x2, y2);
		    break;
		}
		case "X": {
		    g2.drawLine(x1, y1, x2, y2);
		    g2.drawLine(x1, y2, x2, y1);
		    break;
		}
		default:
		    break;
		}
	    }
	}
    }

    public void showImage() {
	/*
	 * BufferedImage img = new BufferedImage(controller.getImageWidth(),
	 * controller.getImageHeight(),
	 * BufferedImage.TYPE_INT_ARGB);
	 * Graphics2D g2 = img.createGraphics();
	 * setSize(controller.getImageWidth(), controller.getImageHeight());
	 * printAll(g2);
	 * g2.dispose();
	 * new LayeredPaneExample(img);
	 */
	j.addMouseListener(this);
	j.addMouseMotionListener(this);
	j.addMouseWheelListener(this);
	j.add(this);
	j.setVisible(true);
    }

    public void saveImage() {
	width = controller.getImageWidth();
	height = controller.getImageHeight();
	count = 1;
	BufferedImage img = new BufferedImage(controller.getImageWidth(), controller.getImageHeight(),
		BufferedImage.TYPE_INT_ARGB);
	Graphics2D g2 = img.createGraphics();
	setSize(controller.getImageWidth(), controller.getImageHeight());
	printAll(g2);
	g2.dispose();
	try {
	    String resFileName = controller.getFileName() + "_";
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss", Locale.ENGLISH);
	    resFileName += sdf.format(new Date()).toString();
	    Path s = Paths.get(View.HOME.toString() + "\\" + resFileName + ".png");

	    Files.deleteIfExists(s);
	    Files.createFile(s);

	    ImageIO.write(img, "png", s.toFile());
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @Override
    public void mouseDragged(MouseEvent e) {
	int thisX = getLocation().x;
	int thisY = getLocation().y;

	int xMoved = (thisX + e.getX()) - (thisX + p.x);
	int yMoved = (thisY + e.getY()) - (thisY + p.y);

	int X = thisX + xMoved / 10;
	int Y = thisY + yMoved / 10;

	setLocation(X, Y);
	// repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
	p = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
	if (e.getWheelRotation() > 0) {
	    count++;
	    width = controller.getImageWidth() / count;
	    height = controller.getImageHeight() / count;
	} else if (e.getWheelRotation() < 0) {
	    count = 1;
	    width = controller.getImageWidth();
	    height = controller.getImageHeight();
	}
	j.setSize(width, height);
	repaint();
    }
}

class LayeredPaneExample extends JFrame {

    BufferedImage im;

    public LayeredPaneExample(BufferedImage im) {
	super("Preview Image");
	this.im = im;
	setSize(600, 600);
	JLayeredPane pane = getLayeredPane();
	JLayeredPane formaPanel = new JLayeredPane();
	formaPanel.setBounds(0, 0, 600, 600);
	formaPanel.setLayout(new BoxLayout(formaPanel, BoxLayout.Y_AXIS));
	formaPanel.setLayout(null);
	BufferedImage img;
	JLabel label;
	JScrollPane scroll;
	try {
	    img = im;
	    ImageIcon icon = new ImageIcon(img);
	    label = new JLabel(icon);

	    scroll = new JScrollPane(label, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    formaPanel.add(scroll);
	    // without THIS DON'T WORK!!!!
	    scroll.setBounds(1, 30, 585, 562); // !!!!!!!!!!!!!!!!!!<-------------------------

	    pane.add(formaPanel);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	setLocationRelativeTo(null);
	setResizable(false);
	setVisible(true);
    }
}
