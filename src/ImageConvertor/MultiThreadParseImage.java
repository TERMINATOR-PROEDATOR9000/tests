package ImageConvertor;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultiThreadParseImage extends BaseParser {
    
    static ExecutorService exec= //Executors.newCachedThreadPool();
	    Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);
    List<ChunkAnalyzer> runList = new ArrayList<ChunkAnalyzer>();
    List<Points> results = new ArrayList<Points>();
    BufferedImage loadedImage;

    public MultiThreadParseImage(Controller c) {
	super(c);
	this.loadedImage = loadedImage;	
	//System.out.println("CREATED MULTI PARSER");
    }

    protected Points searchMaxLine(short w, short h, Direction direction, Points points, short startW, short startH) {
	short[] arr = getDirection(w, h, direction);
	if (isBlackPixel(w, h)) {
	    points.startH = h;
	    points.startW = w;
	    while (isBlackPixel(w, h)) {		
		w = arr[0];
		h = arr[1];
		points.endW = w;
		points.endH = h;		
		points.direction = direction;
		arr = getDirection(w, h, direction);
		if (w < startW || w > startW + 1 + chunkSize || h < startH || h > startH + 1 + chunkSize) {
		    break;
		}
	    }
	}

	return points;
    }

    protected List<Future<Points>> search() {	
	for (short w = 0; w < width; w += chunkSize) {
	    for (short h = 0; h < height; h += chunkSize) {
		runList.add(new ChunkAnalyzer(w, h, w, h, chunkSize, loadedImage, minLum, maxLum, results));
		//runList.add(new ChunkAnalyzer(h, chunkSize, loadedImage, minLum, maxLum, results));
	    }
	}
	List<Future<Points>> secRes=null;
	try {	    
	    secRes = exec.invokeAll(runList);       
	    /*for (Future<Points> f : secRes) {
		try {
		    results.add(f.get());		    
		} catch (InterruptedException e) {
		    e.printStackTrace();
		} catch (ExecutionException e) {
		    e.printStackTrace();
		}
	    }*/
	} catch (InterruptedException e1) {
	    e1.printStackTrace();
	}	
	//exec.shutdownNow();	
	System.gc();	
	//System.out.println("MULTI end");	
	//System.gc();	
	return secRes;
    }

    @Override
    public short getChunkSize() {
	return chunkSize;
    }

    public List<Future<Points>> getPointsList() {	
	return search();
    }

}