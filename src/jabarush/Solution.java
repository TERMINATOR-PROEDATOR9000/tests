package jabarush;
/*                                                   
Биты были биты                                                  
*/
import java.nio.CharBuffer;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Solution {

    public static void main(String[] args) {
	System.out.println("1\t<--true-->\t" + new Solution().isOneEditAway("AAAASS", "AAAASAS"));
	System.out.println("2\t<--true-->\t" + new Solution().isOneEditAway("letter", "letter"));
	System.out.println("3\t<--false-->\t" + new Solution().isOneEditAway("leTTer", "letter"));
	System.out.println("4\t<--true-->\t" + new Solution().isOneEditAway("leter", "letter"));
	System.out.println("5\t<--true-->\t" + new Solution().isOneEditAway("letterA", "letterB"));
	System.out.println("6\t<--true-->\t" + new Solution().isOneEditAway("letterA", "letter"));
	System.out.println("7\t<--true-->\t" + new Solution().isOneEditAway("letter", "letterB"));
	System.out.println("8\t<--false-->\t" + new Solution().isOneEditAway("letter", "letterBB"));
	System.out.println("9\t<--false-->\t" + new Solution().isOneEditAway("leTter", "letterB"));
	System.out.println("10\t<--true-->\t" + new Solution().isOneEditAway("A", "B"));
	System.out.println("11\t<--true-->\t" + new Solution().isOneEditAway("A", ""));
	System.out.println("12\t<--false-->\t" + new Solution().isOneEditAway("", ""));
	System.out.println("13\t<--false-->\t" + new Solution().isOneEditAway("   ", "         "));
	System.out.println("14\t<--false-->\t" + new Solution().isOneEditAway(null, null));
	System.out.println("15\t<--false-->\t" + new Solution().isOneEditAway(" ", null));
	System.out.println("16\t<--false-->\t" + new Solution().isOneEditAway(null, " "));
    }

    public static boolean isOneEditAway(String first, String second) {
	if (first == null || second == null) {
	    return false;
	}
	if (Math.abs(first.length() - second.length()) > 1) {
	    return false;
	}
	if (first.equals("") && second.equals("")) {
	    return true;
	}
	if (first.equals(second)) {
	    return true;
	}
	/*
	 * int[] countFirst = new int[256]; int[] countSecond = new int[256]; int count
	 * = 0; for (int i = 0; i < first.length(); i++) { char c = first.charAt(i);
	 * countFirst[c]++; } for (int i = 0; i < second.length(); i++) { char c =
	 * second.charAt(i); countSecond[c]++; }
	 */
	List<Character> ch1 = new CopyOnWriteArrayList<Character>();
	CharBuffer.wrap(first.toCharArray()).chars().mapToObj(ch -> (char) ch).forEach(ch -> ch1.add(ch));
	List<Character> ch2 = new CopyOnWriteArrayList<Character>();
	CharBuffer.wrap(second.toCharArray()).chars().mapToObj(ch -> (char) ch).forEach(ch -> ch2.add(ch));
	
	int count = 0;
	
	List<Character> minOrEq = ch1.size() == ch2.size() ? ch1 : ch1.size() < ch2.size() ? ch1 : ch2;
	List<Character> maxOrEq = ch1.size() == ch2.size() ? ch2 : ch1.size() < ch2.size() ? ch2 : ch1;
	
	label: for (int i = 0; i < minOrEq.size(); i++) {	   
	    if (minOrEq.get(i) != maxOrEq.get(i)) {
		if (minOrEq.size() == maxOrEq.size()) {		    
		    minOrEq.set(i, maxOrEq.get(i));
		    count++;	
		    if(count>2)return false;
		    continue label;
		} else {
		    minOrEq.add(i, maxOrEq.get(i));
		    count++;
		    if(count>2)return false;
		    continue label;
		}

	    }
	}
	if (count > 1 || Math.abs(minOrEq.size() - maxOrEq.size()) > 1) {
	    return false;
	}
	// System.err.println(count);
	// System.out.println(count);
	// System.out.println(Arrays.toString(countFirst)+"\n"+Arrays.toString(countSecond));
	return true;
    }

}
