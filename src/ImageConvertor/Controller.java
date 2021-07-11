package ImageConvertor;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Controller {

    private ImageLoader imageLoader;
    private BufferedImage bufferedImage;
    private BaseParser parser;
    private List<?> pointsList;
    private short chunkSize, imageWidth, imageHeight;
    private float stroke;
    private boolean isLoaded = false;
    private ParsedImagePreview parsedImage;
    private ImageIcon imageIcon;
    private String fileName;
    private String figure;
    private boolean isSingle;
    private boolean useRandom;
    private float minLum, maxLum;
    private float layers;

    public Controller() {
    }

    public void setMaxLum(float maxLum) {
	this.maxLum = maxLum;
    }

    public void setMinLum(float minLum) {
	this.minLum = minLum;
    }

    public float getMaxLum() {
	return maxLum;
    }

    public float getMinLum() {
	return minLum;
    }

    public float getLayers() {
	return layers;
    }

    public void setLayers(int layers) {
	this.layers = layers;
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

    public void setChunkSize(short chunkSize) {
	this.chunkSize = chunkSize;
    }

    public void setStroke(float stroke) {
	this.stroke = stroke;
    }

    public void loadImage() {
	imageLoader = new ImageLoader();
	bufferedImage = imageLoader.loadImage();
	if (bufferedImage == null)
	    return;
	fileName = imageLoader.getPath().getFileName().toString().substring(0,
		imageLoader.getPath().getFileName().toString().lastIndexOf("."));
	imageWidth = (short) bufferedImage.getWidth();
	imageHeight = (short) bufferedImage.getHeight();
	imageIcon = new ImageIcon(bufferedImage);
	isLoaded = true;
    }

    public ImageIcon getImageIcon() {
	return imageIcon;
    }

    public boolean isLoaded() {
	return isLoaded;
    }

    public List<?> getPointsList() {
	Runtime.getRuntime().gc();	
	/*
	 * if (isSingle) {
	 * parser = new SingleThreadParseImage(bufferedImage, chunkSize, minLum,
	 * maxLum);
	 * pointsList = (List<Points>) parser.getPointsList();
	 * } else {
	 * parser = new MultiThreadParseImage(bufferedImage, chunkSize, minLum, maxLum);
	 * JDialog jdl=new JDialog();
	 * jdl.setTitle("Working...");
	 * jdl.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	 * jdl.setLocationRelativeTo(null);
	 * jdl.setVisible(true);
	 * pointsList = (List<Future<Points>>) parser.getPointsList();
	 * jdl.setVisible(false);
	 * }
	 */
	// TEST SECTION
	float lumStep = 1f / layers;
	float start = 0f;
	List<Float> steps = new ArrayList<>();
	steps.add(start);
	while (start < 1f) {
	    start=start+lumStep;
	    steps.add(start);
	}
	List<List<Points>> results = new ArrayList<List<Points>>();
	//minLum = 0.2f;
	//maxLum = 0.4f;
	parser = new SingleThreadParseImage(this);
	for (int i = 0; i < steps.size() - 1; i++) {
	    parser.setMinLum(steps.get(i));
	    parser.setMaxLum(steps.get(i + 1));
	    //System.out.println(minLum+" "+maxLum);	    
	    pointsList = (List<Points>) parser.getPointsList();
	    results.add(List.copyOf((List<Points>) pointsList));
	   // 
	}	
	//System.out.println(steps);
	StringBuilder sb = new StringBuilder();
	/*int boundWidth = 0, boundHeight = 0;
	while (boundWidth < imageWidth) {
	    boundWidth += chunkSize;
	}
	while (boundHeight < imageHeight) {
	    boundHeight += chunkSize;
	}
	Points[][] matrix = new Points[boundHeight / chunkSize][boundWidth / chunkSize];
	int count = 0;
	for (int i = 0; i < matrix.length; i++) {
	    for (int j = 0; j < matrix[i].length; j++) {
		matrix[i][j] = ((Points) pointsList.get(count++));

		// count++;
	    }
	}*/
	/*
	 * for (int i = 0; i < pointsList.size(); i++) {
	 * if (((Points) pointsList.get(i)).direction == Direction.STUB) {
	 * sb.append("-");
	 * } else {
	 * sb.append("*");
	 * }
	 * if (i != 0 && i % boundWidth == 0) {
	 * sb.append("\n");
	 * }
	 * }
	 */
	/*for (int i = 0; i < matrix.length; i++) {
	    for (int j = 0; j < matrix[i].length; j++) {
		if (((Points) matrix[i][j]).direction == Direction.STUB) {
		    sb.append("-");
		} else {
		    sb.append("*");
		}
	    }
	    sb.append("\n");
	}
	try {
	    Files.deleteIfExists(Paths.get(View.HOME+"\\Desktop\\res.txt"));
	    Files.write(Paths.get(View.HOME+"\\Desktop\\res.txt"), sb.toString().getBytes());
	} catch (IOException e) {
	    e.printStackTrace();
	}*/

	return results;
    }

    public short getChunkSize() {
	return chunkSize;
    }

    public float getStroke() {
	return stroke;
    }

    public short getImageWidth() {
	return imageWidth;
    }

    public short getImageHeight() {
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

    public void setSingle(boolean b) {
	isSingle = b;
    }

    public boolean isSingle() {
	return isSingle;
    }

    public boolean isRandom() {
	return useRandom;
    }

    public void setRandom(boolean useRandom) {
	this.useRandom = useRandom;
    }

}
