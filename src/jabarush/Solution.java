package jabarush;

import java.nio.CharBuffer;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Solution {
    public static void main(String[] args) {
	Color[][] colors = { { Color.RED, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED },
		{ Color.RED, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED },
		{ Color.RED, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED },
		{ Color.RED, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED },
		{ Color.RED, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED },
		{ Color.RED, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED },
		{ Color.RED, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED },
		{ Color.RED, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED } };
	new PhotoPaint().paintFill(colors, 5, 4, Color.GREEN);

    }
}

class PhotoPaint {
    public boolean paintFill(Color[][] image, int x, int y, Color desiredColor) {
	if (desiredColor == null || image == null || image.length <= y || image[y].length <= x || image[y][x] == null
		|| image[y][x] == desiredColor) {
	    return false;
	}
	System.out.println("Before:");
	for (int i = 0; i < image.length; i++) {
	    for (int j = 0; j < image.length; j++) {
		System.out.print(image[i][j] + "\t");
	    }
	    System.out.println();
	}

	if (image[y][x] != desiredColor) {
	    image[y][x] = desiredColor;
	    int _x = x;
	    int _y = y;
	    
	    while (_x > -1) {
		for (; _y > -1; _y--) {
		    image[_y][_x] = desiredColor;
		}
		_y = y;
		_x--;
	    }
	    _x = x;
	    _y = y;
	    System.out.println("After first stage:");
	    for (int i = 0; i < image.length; i++) {
		for (int j = 0; j < image.length; j++) {
		    System.out.print(image[i][j] + "\t");
		}
		System.out.println();
	    }
	    
	    while (_x < image[y].length) {
		for (; _y < image[y].length; _y++) {
		    image[_y][_x] = desiredColor;
		}
		_y = y;
		_x++;
	    }
	    _x = x;
	    _y = y;
	    
	    System.out.println("After second stage:");
	    for (int i = 0; i < image.length; i++) {
		for (int j = 0; j < image.length; j++) {
		    System.out.print(image[i][j] + "\t");
		}
		System.out.println();
	    }
	    
	    
	    while (_y > -1) {
		for (; _x < image.length; _x++) {
		    image[_y][_x] = desiredColor;
		}
		_x = x;
		_y--;
	    }
	    _y = y;
	    _x = x;

	    
	    System.out.println("After third stage:");
	    for (int i = 0; i < image.length; i++) {
		for (int j = 0; j < image.length; j++) {
		    System.out.print(image[i][j] + "\t");
		}
		System.out.println();
	    }
	    
	    while (_y < image[y].length) {
		for (; _x > -1; _x--) {
		    image[_y][_x] = desiredColor;
		}
		_x = x;
		_y++;
	    }	    
	    
	    System.out.println("After fourth stage:");
	    for (int i = 0; i < image.length; i++) {
		for (int j = 0; j < image.length; j++) {
		    System.out.print(image[i][j] + "\t");
		}
		System.out.println();
	    }
	}

	System.out.println("Result:");
	for (int i = 0; i < image.length; i++) {
	    for (int j = 0; j < image.length; j++) {
		System.out.print(image[i][j] + "\t");
	    }
	    System.out.println();
	}
	return true;
    }
}

enum Color {
    RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET
}
