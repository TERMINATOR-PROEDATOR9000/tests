package ImageConvertor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

class SingleThreadParseImage extends BaseParser {

    List<Points> results = new CopyOnWriteArrayList<Points>();

    public SingleThreadParseImage(Controller c) {
	super(c);
	// System.out.println("CREATED SINGLE PARSER");
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

    protected List<Points> search() {

	List<Points> maxPoints = new ArrayList<Points>();
	for (short h = 0; h < height; h += chunkSize) {

	    for (short w = 0; w < width; w += chunkSize) {

		List<Points> pList = new ArrayList<Points>();

		for (short w1 = w; w1 < (w + chunkSize); w1++) {
		    for (short h1 = h; h1 < (h + chunkSize); h1++) {

			pList.add(searchMaxLine(w1, h1, Direction.RIGHT, new Points(), w, h));
			pList.add(searchMaxLine(w1, h1, Direction.DOWN, new Points(), w, h));
			pList.add(searchMaxLine(w1, h1, Direction.RIGHT_DOWN, new Points(), w, h));
			pList.add(searchMaxLine(w1, h1, Direction.RIGHT_UP, new Points(), w, h));
		    }

		}
		Points max = pList.stream().max((i1, i2) -> Integer.compare(i1.getLength(), i2.getLength())).get();

		if (c.isRandom()) {
		    max.startW = (short) ThreadLocalRandom.current().nextInt(max.startW - chunkSize,
			    max.startW + chunkSize);
		    max.startH = (short) ThreadLocalRandom.current().nextInt(max.startH - chunkSize,
			    max.startH + chunkSize);
		    max.endW = (short) ThreadLocalRandom.current().nextInt(max.endW - chunkSize, max.endW + chunkSize);
		    max.endH = (short) ThreadLocalRandom.current().nextInt(max.endH - chunkSize, max.endH + chunkSize);
		}
		// if (max.direction != Direction.STUB) {
		// System.out.println(max);
		maxPoints.add(max);
		// }
		// pList=null;
	    }
	}
	// System.out.println("end");
	//System.out.println("SngleThread line 70. Size: " + maxPoints.size());
	return maxPoints;
    }

    @Override
    public short getChunkSize() {
	return chunkSize;
    }

    public List<Points> getPointsList() {
	return search();
    }

}
