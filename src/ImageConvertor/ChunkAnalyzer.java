package ImageConvertor;

import java.awt.image.BufferedImage;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class ChunkAnalyzer implements Callable<Points> {
    short w1, h1, w, h, chunkSize;
    BufferedImage img;
    float minLum, maxLum;
    List<Points> maxPoints;
    static AtomicInteger thread=new AtomicInteger();

    public ChunkAnalyzer(short w, short h, short w1, short h1, short chunkSize, BufferedImage img, float minLum, float maxlum,
	    List<Points> arr) {
	super();
	this.w1 = w1;
	this.h1 = h1;
	this.w = w;
	this.h = h;
	this.chunkSize = chunkSize;
	this.img = img;
	this.minLum = minLum;
	this.maxLum = maxlum;
	maxPoints = arr;
    }

    @Override
    public Points call() throws Exception {
	List<Points> temp = new ArrayList<Points>();
	//System.out.println("w1 "+w1+" chunkSize "+chunkSize+" sum "+(w1+chunkSize)+" thread "+Thread.currentThread());
	for (; w < (w1 + chunkSize); w++) {
	    for (; h < (h1 + chunkSize); h++) {
		temp.add(searchMaxLine(w, h, Direction.RIGHT, new Points(), w1, h1));
		temp.add(searchMaxLine(w, h, Direction.DOWN, new Points(), w1, h1));
		temp.add(searchMaxLine(w, h, Direction.RIGHT_DOWN, new Points(), w1, h1));
		temp.add(searchMaxLine(w, h, Direction.RIGHT_UP, new Points(), w1, h1));
	    }
	}
	Points max = temp.stream().max((i1, i2) -> Integer.compare(i1.getLength(), i2.getLength())).get();
	if (max.direction != Direction.STUB) {
	    // System.out.println(max);
	    maxPoints.add(max);
	}
	temp.clear();
	// System.err.println(maxPoints.size());
	return max;
    }

    protected Points searchMaxLine(short w, short h, Direction direction, Points points, short startW, short startH) {
	short[] arr = getDirection(w, h, direction);
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
		if (w < startW + 1 || w > startW + 1 + chunkSize || h < startH + 1 || h > startH + 1 + chunkSize) {
		    break;
		}
		// System.out.println("\t\tsearchMaxline:"+points.endH+"---"+points.endW);
		// points.length++;
		points.direction = direction;
		arr = getDirection(w, h, direction);
	    }
	}

	return points;
    }

    protected short[] getDirection(short w, short h, Direction direction) {
	short result[] = { -2, -2 };
	switch (direction) {
	case DOWN: {
	    result[0] = w;
	    result[1] = (short) (h + 1);
	    return result;
	}
	case UP: {
	    result[0] = w;
	    result[1] = (short) (h - 1);
	    return result;
	}
	case LEFT: {
	    result[0] = (short) (w - 1);
	    result[1] = h;
	    return result;
	}
	case RIGHT: {
	    result[0] = (short) (w + 1);
	    result[1] = h;
	    return result;
	}
	case LEFT_UP: {
	    result[0] = (short) (w - 1);
	    result[1] = (short) (h - 1);
	    return result;
	}
	case RIGHT_DOWN: {
	    result[0] = (short) (w + 1);
	    result[1] = (short) (h + 1);
	    return result;
	}
	case LEFT_DOWN: {
	    result[0] = (short) (w - 1);
	    result[1] = (short) (h + 1);
	    return result;
	}
	case RIGHT_UP: {
	    result[0] = (short) (w + 1);
	    result[1] = (short) (h - 1);
	    return result;
	}

	}
	return null;
    }

    protected boolean isBlackPixel(short x, short y) {

	if (x > img.getWidth() - 1 || y > img.getHeight() - 1 || x < 0 || y < 0) {
	    // sb.append("Out of bounds when check. return...\n");
	    return false;
	}

	int color = img.getRGB(x, y);
	if ((color >>> 24) == 0x00) {
	    return false;
	}
	int red = (color >>> 16) & 0xFF;
	int green = (color >>> 8) & 0xFF;
	int blue = (color >>> 0) & 0xFF;
	float luminance = (red * 0.2126f + green * 0.7152f + blue * 0.0722f) / 255;
	/*
	 * if (isDark) {
	 * if (luminance >= this.luminanceFactor ) {
	 * return false;
	 * } else {
	 * return true;
	 * }
	 * } else {
	 * if (luminance <= this.luminanceFactor ) {
	 * return false;
	 * } else {
	 * return true;
	 * }
	 * }
	 */
	if (luminance >= minLum && luminance <= maxLum) {

	    return true;
	} else {
	    return false;
	}
    }

}
