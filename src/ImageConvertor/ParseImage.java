package ImageConvertor;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class ParseImage extends BaseParser{

    //ExecutorService exec = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()+1);
    //List<ChunkAnalyzer> runList=new ArrayList<ChunkAnalyzer>();
    List<Points>results=new CopyOnWriteArrayList<Points>();
    BufferedImage loadedImage;

    public ParseImage(BufferedImage loadedImage, int chunkSize, float luminanceFactor) {
	super(loadedImage, chunkSize, luminanceFactor);
	this.loadedImage=loadedImage;
    }

    protected Points searchMaxLine(int w, int h, Direction direction, Points points, int startW, int startH) {
	int[] arr = getDirection(w, h, direction);
	if (isBlackPixel(w, h)) {
	    points.startH = h;
	    points.startW = w;	   
	    while (isBlackPixel(w, h)) {
		// System.out.println("searchMaxLine: "+startH+"--"+startW);
		// System.out.println("\tsearchMaxLine: "+h+"---"+w);
		w = arr[0];
		h = arr[1];
		points.endW = w;
		points.endH = h;		
		// System.out.println("\t\tsearchMaxline:"+points.endH+"---"+points.endW);
		// points.length++;
		points.direction = direction;
		arr = getDirection(w, h, direction);
		if (w < startW || w > startW + chunkSize || h < startH || h > startH + chunkSize) {
		    break;
		}
	    }
	}

	return points;
    }

    protected List<Points> search(){
	
	List<Points> maxPoints = new ArrayList<Points>();

	for (int w = 0; w < width; w += chunkSize) {
	    for (int h = 0; h < height; h += chunkSize) {

		List<Points> pList = new ArrayList<Points>();

		for (int w1 = w; w1 < (w + chunkSize); w1++) {
		    for (int h1 = h; h1 < (h + chunkSize); h1++) {

			pList.add(searchMaxLine(w1, h1, Direction.RIGHT, new Points(), w, h));
			pList.add(searchMaxLine(w1, h1, Direction.DOWN, new Points(), w, h));
			pList.add(searchMaxLine(w1, h1, Direction.RIGHT_DOWN, new Points(), w, h));
			pList.add(searchMaxLine(w1, h1, Direction.RIGHT_UP, new Points(), w, h));
		    }
		    
		}
		Points max = pList.stream().max((i1, i2) -> Integer.compare(i1.getLength(), i2.getLength())).get();		
		sb.append(pList);		
		maxPoints.add(max);
		/*runList.add(new ChunkAnalyzer(w,h,w,h,chunkSize,loadedImage,luminanceFactor));*/
	    }
	}
	//System.out.println("start");
	/*System.out.println(runList.size());
	List<Future<Points>> secRes;
	try {
	    secRes = exec.invokeAll(runList);
	    for(Future<Points> f:secRes) {
		//System.out.println(secRes.size());
		
		    try {
			results.add(f.get());
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    } catch (ExecutionException e) {
			e.printStackTrace();
		    }
		}
	} catch (InterruptedException e1) {
	    e1.printStackTrace();
	}	
	System.out.println("end");
	try {
	    Files.writeString(Paths.get("C:\\Users\\HOME\\Desktop\\arr.txt"), sb);
	} catch (IOException e) {
	    e.printStackTrace();
	}*/
	//System.out.println("end");
	return maxPoints;
    }

    @Override
    public int getChunkSize() {
	return chunkSize;
    }

    public List<Points> getPointsList() {
	return search();
    }  

}
