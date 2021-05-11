package ImageConvertor;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
class PreView extends JPanel {

    Controller controller;
    double correction, w, h, max;
    int widthDraw, heightDraw, widthCorrection, heightCorrection;

    public PreView(Controller controller) {
	this.controller = controller;
	//double width = controller.getImageWidth();
	//double height = controller.getImageHeight();
	w = Math.abs(controller.getImageWidth() / 300d);
	h = Math.abs(controller.getImageHeight() / 300d);
	max = Math.max(w, h);
	if (max == 0) {
	    widthDraw = controller.getImageWidth();
	    heightDraw = controller.getImageHeight();
	} else {
	    max++;
	    widthDraw = (int) Math.ceil((controller.getImageWidth() / max));
	    heightDraw = (int) Math.ceil((controller.getImageHeight() / max));
	   // System.err.println(widthDraw + " " + heightDraw);
	    double m = Math.max(widthDraw, heightDraw);
	    
	    if (m == widthDraw) {
		double corr = Math.abs(300 / m);			
		widthDraw = 300;
		heightDraw =(int) (300-(300-heightDraw * corr));		
	    } else {	
		double corr = Math.abs(300 / m);		
		heightDraw = 300;
		widthDraw = (int) (300-(300-widthDraw * corr));
	    }
	}
	widthCorrection =Math.abs((widthDraw-300)/2);	
	heightCorrection =Math.abs((heightDraw-300)/2);
	
	/*System.out.println(w + " w---h " + h + "\n" + width + " widht---height " + height + "\n" + widthDraw
		+ " widthDraw---heightDraw " + heightDraw + "\n" + max + "\n" + widthCorrection
		+ " widthCorrection---heightCorrection " + heightCorrection);*/
    }

    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	ImageIcon img = controller.getImageIcon();
	g.drawImage(img.getImage(), widthCorrection, heightCorrection, widthDraw, heightDraw, null);
	//System.out.println(widthDraw+" "+heightDraw);
    }

}
