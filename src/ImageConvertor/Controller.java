package ImageConvertor;

import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Controller {

    private ImageLoader imageLoader;
    private BufferedImage bufferedImage;
    private BaseParser parser;
    private List<Points> pointsList;
    private int chunkSize, imageWidth, imageHeight;
    private float stroke;
    private float lumfactor;
    private boolean isLoaded = false;
    private ParsedImagePreview parsedImage;
    private ImageIcon imageIcon; 
    private String fileName;
    private String figure;

    public Controller() {
    }
    
    public void setFigure(String figure) {
	this.figure = figure;
    }
    
    public String getFigure() {
	return figure;
    }
    
    public String getFileName() {
	return fileName;
    }

    public void setChunkSize(int chunkSize) {
	this.chunkSize = chunkSize;
    }

    public void setStroke(float stroke) {
	this.stroke = stroke;
    }

    public void setLumfactor(float lumfactor) {
	this.lumfactor = lumfactor;
    }

    public void loadImage() {
	imageLoader = new ImageLoader();
	bufferedImage = imageLoader.loadImage();
	if(bufferedImage==null)return;
	fileName=imageLoader.getPath().getFileName().toString().substring(0, imageLoader.getPath().getFileName().toString().lastIndexOf("."));
	imageWidth = bufferedImage.getWidth();
	imageHeight = bufferedImage.getHeight();
	imageIcon=new ImageIcon(bufferedImage);	
	isLoaded = true;
    }
    
    public ImageIcon getImageIcon() {
	return imageIcon;
    }

    public boolean isLoaded() {
	return isLoaded;
    }

    public List<Points> getPointsList() {	
	parser = new ParseImage(bufferedImage, chunkSize, lumfactor);	
	pointsList = parser.getPointsList();	
	return pointsList;
    }

    public int getChunkSize() {
	return chunkSize;
    }

    public float getStroke() {
	return stroke;
    }

    public int getImageWidth() {
	return imageWidth;
    }

    public int getImageHeight() {
	return imageHeight;
    }

    public BufferedImage getBufferedImage() {
	return bufferedImage;
    }

    public void showImage() {
	parsedImage = new ParsedImagePreview(this);	
	parsedImage.showImage();
    }

    public void saveImage() {
	parsedImage.saveImage();
    }

}
