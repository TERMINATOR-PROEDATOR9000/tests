package jabarush;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*                                                   
Биты были биты                                                  
*/

public class Solution {
    public static void main(String[] args) throws IOException {
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	System.out.println("Please enter a number: ");

	long l = Long.parseLong(reader.readLine());
	String result = isWeightEven(l) ? "even" : "odd";
	System.out.println("The entered number has " + result + "ones");

    }

    public static boolean isWeightEven(long number) {
	String temp = String.valueOf(Long.toBinaryString(number));
	int result = 0;
	for (int i = 0; i < temp.length(); i++) {
	    char c = temp.charAt(i);
	    if (c == '1') {
		result++;
	    }
	}
	/*
	number ^= number >>> 32;
	number ^= number >>> 16;
	number ^= number >>> 8;
	number ^= number >>> 4;
	number ^= number >>> 2;
	number ^= number >>> 1;
	return (number & 1) == 0;
	*/	
	return result % 2 == 0 ? true : false;
    }
}
