package jabarush;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*                                                   
Биты были биты                                                  
*/

public class Solution {
    public static void main(String[] args) throws IOException {
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	System.out.println("Please enter a number: ");

	long number = Long.parseLong(reader.readLine());
	System.out.println("Please enter the first index: ");
	int i = Integer.parseInt(reader.readLine());
	System.out.println("Please enter the second index: ");
	int j = Integer.parseInt(reader.readLine());
	

	System.out.println("The result of swapping bits is " + swapBits(number, i, j));
    }

    public static long swapBits(long number, int i, int j) {
	List<String> tem = new ArrayList<String>(Arrays.asList(Long.toBinaryString(number).split("")));
	System.err.println(tem);
	String buf = tem.get(i-1);
	String buf2 = tem.get(j-1);
	Collections.reverse(tem);
	tem.set(i-1, buf2);
	tem.set(j-1, buf);
	String resultString = "";
	Collections.reverse(tem);
	for (String s : tem) {
	    resultString += s;
	}
	Long resuLong = Long.parseLong(resultString, 2);
	return resuLong;
	/*if (((number >>> i) & 1) != ((number >>> j) & 1)) {                                                  
            long bitMask = (1L << i) | (1L << j);                                                  
            number ^= bitMask;                                                  
        }                                                  
        return number; */
	
    }
}
