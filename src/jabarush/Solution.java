package jabarush;

/*                                                   
Биты были биты                                                  
*/
import java.nio.CharBuffer;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Solution {
    public static void main(String[] args) {
	int[][]s= {{1,1,1,1,1,1,1,1,1,1},
		   {1,1,1,1,1,1,0,1,1,1},
		   {1,1,1,1,1,1,1,1,1,1},
		   {1,0,1,1,0,1,1,1,1,1},
		   {1,1,1,1,0,1,1,1,1,1},
		   {1,1,1,0,1,1,1,1,1,1},
		   {1,1,1,1,1,1,1,1,1,1},
		   {1,1,1,1,1,1,1,1,1,1},
		   {1,1,1,1,1,1,1,1,1,1},
		   {1,1,1,1,1,1,1,1,1,1}};	
	
	System.out.println(new Solution().maxSquare(s));//25
    }

    public static int maxSquare(int[][] matrix) {
	int result = 0;
	int[][] ch = Arrays.copyOf(matrix, matrix.length);
	for (int i = 0; i < ch.length; i++) {
	    for (int j = 0; j < ch[i].length; j++) {
		if (matrix[i][j] > 0 && !(i == 0 || j == 0)) {
		    ch[i][j] = 1 + Math.min(ch[i - 1][j], Math.min(ch[i - 1][j - 1], ch[i][j - 1]));
		}
		if (ch[i][j] > result) {
		    result = ch[i][j];
		}
	    }
	}
	for (int i = 0; i < ch.length; i++) {
	    for (int j = 0; j < ch.length; j++) {
		System.out.print(ch[i][j]);
	    }
	    System.out.println();
	}
	return result*result;
    }  
}
