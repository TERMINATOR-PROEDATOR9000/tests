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

class SingleThreadParseImage extends BaseParser {

    List<Points> results = new CopyOnWriteArrayList<Points>();
    BufferedImage loadedImage;

    public SingleThreadParseImage(BufferedImage loadedImage, int chunkSize, float luminanceFactor) {
	super(loadedImage, chunkSize, luminanceFactor);
	this.loadedImage = loadedImage;
	//System.out.println("CREATED SINGLE PARSER");
    }

    protected Points searchMaxLine(int w, int h, Direction direction, Points points, int startW, int startH) {
	int[] arr = getDirection(w, h, direction);
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

    protected List<Points> search() {

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

		if (max.direction != Direction.STUB) {
		    //System.out.println(max);
		    maxPoints.add(max);
		}
		pList=null;
		//System.gc();
	    }
	}
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
