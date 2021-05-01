package ImageConvertor;

import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings(value = "serial")
class ParsedImagePreview extends JPanel {

    Controller controller;
    List<Points> forDraw;
    int s;
    String figure;

    public ParsedImagePreview(Controller controller) {
	this.controller = controller;
	forDraw = controller.getPointsList();
	s = controller.getChunkSize();
	figure = controller.getFigure();
	/*
	 * JDialog jDialog=new JDialog();
	 * jDialog.add(this);
	 * jDialog.setSize(controller.getImageWidth(), controller.getImageHeight());
	 * jDialog.setVisible(true);
	 */
	JFrame j = new JFrame();
	j.setSize(controller.getImageWidth()+10, controller.getImageHeight()+50);
	j.add(this);
	j.setLocationRelativeTo(null);
	j.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D) g;
	g2.setStroke(new BasicStroke(controller.getStroke()));

	for (Points pp : forDraw) {
	    if (pp.direction == Direction.STUB || pp.getLength() < s) {
		continue;
	    }
	    int x1;// = pp.startW;
	    int y1;// = pp.startH;
	    int x2;// = pp.endW;
	    int y2;// = pp.endH;

	    if (pp.getLength() >= s-1) {
		x1 = ThreadLocalRandom.current().nextInt(pp.startW - s, pp.startW + s);
		y1 = ThreadLocalRandom.current().nextInt(pp.startH - s, pp.startH + s);
		x2 = ThreadLocalRandom.current().nextInt(pp.endW - s, pp.endW + s);
		y2 = ThreadLocalRandom.current().nextInt(pp.endH - s, pp.endH + s);
	    }else {
		x1=pp.startW;
		y1=pp.startH;
		x2=pp.endW;
		y2=pp.endH;
	    }
	    switch (figure) {
	    case "Circle": {
		int rnd=pp.getLength()+ThreadLocalRandom.current().nextInt(s);
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
	    /*
	     * } else {
	     * //g2.setColor(Color.BLACK);
	     * x1 = pp.startW;
	     * y1 = pp.startH;
	     * x2 = pp.endW;
	     * y2 = pp.endH;
	     * g2.drawLine(x1, y1, x2, y2);
	     * }
	     */

	    // g2.drawRoundRect(x1, y1, 4, 4, 10, 10);
	}
	// g2.drawLine(120, 120, 100, 100);
	// g2.drawLine(15, 0, 1, 15);
    }

    public void showImage() {
	BufferedImage img = new BufferedImage(controller.getImageWidth(), controller.getImageHeight(),
		BufferedImage.TYPE_INT_RGB);
	Graphics2D g2 = img.createGraphics();
	printAll(g2);
	g2.dispose();
    }

    public void saveImage() {
	BufferedImage img = new BufferedImage(controller.getImageWidth(), controller.getImageHeight(),
		BufferedImage.TYPE_INT_RGB);
	Graphics2D g2 = img.createGraphics();
	setSize(controller.getImageWidth(), controller.getImageHeight());
	printAll(g2);
	g2.dispose();
	try {
	    String resFileName = controller.getFileName() + "_";
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss", Locale.ENGLISH);
	    resFileName += sdf.format(new Date()).toString();
	    Path s = Paths.get(View.HOME.toString() + "\\" + resFileName + ".jpg");
	    // System.out.println(s);

	    Files.deleteIfExists(s);
	    Files.createFile(s);

	    ImageIO.write(img, "jpg", s.toFile());
	    // System.out.println("writed");
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
