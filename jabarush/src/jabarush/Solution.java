package jabarush;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	System.out.println("Please enter your string: ");
	// String s = bufferedReader.readLine();
	String s = "a123bcbcqwe";

	System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));

	s = "ttttwt";

	System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));
    }

    public static int lengthOfLongestUniqueSubstring(String s) {
	if (s == null || s.equals(""))
	    return 0;
	List<String> cBuf;
	List<String>wtf=new ArrayList<String>();
	int i = 0;
	int max = 0;
	boolean check = true;
	l:
	while (check) {
	    cBuf = new CopyOnWriteArrayList<String>(Arrays.asList(s.split("")));	 
	    for (String str : cBuf) {		
		if (!wtf.contains(str)) {		    
		    wtf.add(str);
		    i++;		    
		    continue;
		} else {		  
		    if (max <= i) {
			max = i;
		    }
		    i = 0;
		    cBuf.clear();
		    wtf.clear();
		    s = s.substring(1);		    
		    if (s.equals("")) {
			check = false;
		    }	
		    continue l;
		}
	    }
	}
	return max;
    }
}