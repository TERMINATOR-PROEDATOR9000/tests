package ImageConvertor;

import java.awt.image.BufferedImage;

import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

abstract class BaseParser {

    protected BufferedImage img;
    protected int width;
    protected int height;
    protected StringBuilder sb;
    protected int chunkSize;
    protected float luminanceFactor;

    public BaseParser(BufferedImage img, int chunkSize, float luminanceFactor) {
	this.img = img;
	this.sb = new StringBuilder();
	this.width = img.getWidth();
	this.height = img.getHeight();
	this.chunkSize = chunkSize;
	this.luminanceFactor=luminanceFactor;
	sb.append("width: " + width + " height: " + height + "\n");
    }

    protected int[] getDirection(int w, int h, Direction direction) {
	int result[] = { -2, -2 };
	switch (direction) {
	case DOWN: {
	    result[0] = w;
	    result[1] = h + 1;
	    return result;
	}
	case UP: {
	    result[0] = w;
	    result[1] = h - 1;
	    return result;
	}
	case LEFT: {
	    result[0] = w - 1;
	    result[1] = h;
	    return result;
	}
	case RIGHT: {
	    result[0] = w + 1;
	    result[1] = h;
	    return result;
	}
	case LEFT_UP: {
	    result[0] = w - 1;
	    result[1] = h - 1;
	    return result;
	}
	case RIGHT_DOWN: {
	    result[0] = w + 1;
	    result[1] = h + 1;
	    return result;
	}
	case LEFT_DOWN: {
	    result[0] = w - 1;
	    result[1] = h + 1;
	    return result;
	}
	case RIGHT_UP: {
	    result[0] = w + 1;
	    result[1] = h - 1;
	    return result;
	}

	}
	return null;
    }

    protected boolean isBlackPixel(int x, int y) {

	if (x > width - 1 || y > height - 1 || x < 0 || y < 0) {
	    sb.append("Out of bounds when check. return...\n");
	    return false;
	}

	int color = img.getRGB(x, y);
	if((color>>>24)==0x00) {
	    return false;
	}
	int red = (color >>> 16) & 0xFF;
	int green = (color >>> 8) & 0xFF;
	int blue = (color >>> 0) & 0xFF;
	float luminance = (red * 0.2126f + green * 0.7152f + blue * 0.0722f) / 255;
	if (luminance >=this.luminanceFactor /*0.4f*/) {
	    return false;
	} else {
	    return true;
	}
    }

    protected abstract int getChunkSize();
    protected abstract List<?> getPointsList();   
}

/*
 * class ParseImage extends BaseParser {
 * 
 * 
 * 
 * public List<Points> getResults() {
 * return maxPoints;
 * }
 * 
 * protected Points searchMaxLine(int w, int h, Direction direction, Points
 * points) {
 * // System.out.println(isBlackPixel(w, h));
 * // System.out.println(points.chunkW+" "+points.chunkH);
 * // System.out.println("searchMaxLine: "+w + "---" + h);
 * int[] tempArr;// = getDirection(w, h, direction);
 * int check = 0;
 * while (isBlackPixel(w, h)) {
 * check++;
 * // System.out.println("searchMaxLine: "+w + "---" + h);
 * tempArr = getDirection(w, h, direction);
 * points.endW = w;
 * points.endH = h;
 * points.length++;
 *
 * w = w > 15 ? 15 : w;
 * h = h > 15 ? 15 : h;
 * w = w < 0 ? 0 : w;
 * h = h < 0 ? 0 : h;
 *
 * // System.out.println("searchMaxLine:
 * //
 * "+(Math.abs(w-15*points.chunkW-2))+"---"+(Math.abs(h-15*points.chunkH-2)));
 * try {
 * points.res[Math.abs(w - 15 * points.chunkW)][Math.abs(h - 15 *
 * points.chunkH)] = 1;
 * } catch (IndexOutOfBoundsException e) {
 * // TODO: handle exception
 * }
 * // points.res[CHUNK*points.chunkH][CHUNK*points.chunkW] = 1;
 * // System.out.println("searchMaxLine:
 * // "+(((h-CHUNK*points.chunkH))+"---"+((w-CHUNK*points.chunkW))));
 * w = tempArr[0];
 * h = tempArr[1];
 * if (check > 15) {
 * break;
 * }
 * }
 * sb.append(points);
 * return points;
 * }
 * 
 * protected void search(int w, int h, Direction direction, int chW, int chH) {
 * sb.append("called... \t" + w + " <--x\ty--> " + h + "\n");
 * List<Points> temp = new ArrayList<Points>();
 * // System.out.println("search: W "+w + "---H " + h);
 * for (int dW = w; dW < (w + 16); dW++) {
 * for (int dH = h; dH < (h + 16); dH++) {
 * // System.err.println("search: dw " + dW + " dh " + dH);
 * if (isBlackPixel(dW, dH)) {
 * Points p = new Points();
 * p.direction = direction;
 * p.startW = dW;
 * p.startH = dH;
 * p.chunkH = chH;
 * p.chunkW = chW;
 * temp.add(searchMaxLine(dW, dH, direction, p));
 * }
 * }
 * }
 * 
 * Points max;
 * try {
 * max = temp.stream().max((f, s) -> Integer.compare(f.length, s.length)).get();
 * } catch (NoSuchElementException e) {
 * max = new Points();
 * }
 * results.add(max);
 * // System.out.println("TEMP: "+temp.size());
 * temp = new ArrayList<Points>();
 * // System.out.println("search:\n"+max);
 * try {
 * Files.writeString(Paths.get("C:\\Users\\HOME\\Desktop\\result.txt"), sb);
 * } catch (IOException e1) {
 * e1.printStackTrace();
 * }
 * 
 * }
 * 
 * public void init() {
 * System.out.println("Image resolution: " + img.getWidth() + "x" +
 * img.getHeight());
 * int chW = 1, chH = 1;
 * for (int w = 0; w < width; w += 16) {
 * for (int h = 0; h < height; h += 16) {
 * search(w, h, Direction.RIGHT, chW, chH);
 * search(w, h, Direction.RIGHT_DOWN, chW, chH);
 * search(w, h, Direction.DOWN, chW, chH);
 * search(w, h, Direction.RIGHT_UP, chW, chH);
 * /*
 * search(w, h, currentPoint, Direction.LEFT);
 * search(w, h, currentPoint, Direction.LEFT_DOWN);
 * search(w, h, currentPoint, Direction.UP);
 * search(w, h, currentPoint, Direction.RIGHT_UP);
 *
 * 
 * // search(j, i, Direction.UP);
 * // search(j, i, Direction.LEFT);
 * // search(j, i, Direction.RIGHT, w, h, currentPoint);
 * // search(j, i, Direction.RIGHT_DOWN, w, h, currentPoint);
 * // search(j, i, Direction.RIGHT_UP, w, h, currentPoint);
 * // search(j, i, Direction.LEFT_DOWN);
 * // search(i, j, Direction.LEFT_UP);
 * // System.out.println("start...");
 * // System.out.println(String.format("w %d, h %d, i %d, j %d", w,h,i,j));
 * chH++;
 * // System.err.println("init: "+w+"---"+h);
 * Points max = results.stream().max((v1, v2) -> Integer.compare(v1.length,
 * v2.length)).get();
 * maxPoints.add(max);
 * results.clear();
 * }
 * // System.out.println(chW+"\ninit:\n"+results);
 * chW++;
 * chH = 1;
 * // System.out.println("init: start new chunk search... line: "+chW*16);
 * }
 * 
 * // System.out.println("init:\n" + maxPoints);
 * 
 * try {
 * Files.writeString(Paths.get("C:\\Users\\HOME\\Desktop\\arr.txt"),
 * sb.toString());
 * } catch (IOException e) {
 * e.printStackTrace();
 * }
 * /*
 * set.keySet().stream().forEach(e -> {
 * Points max = set.get(e).stream().max((f, s) -> Integer.compare(f.length,
 * s.length)).get();
 * // System.out.println(max);
 * });
 *
 * System.out.println("RESULTS arr size: " + results.size());
 * }
 * }
 */
