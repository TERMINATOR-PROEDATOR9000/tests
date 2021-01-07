package jabarush;
/*                                                   
Биты были биты                                                  
*/

public class Solution {
    private static int n = 20;

    public static void main(String[] args) {
	System.out.println("The number of possible ascents for " + n + " steps is: " + numberOfPossibleAscents(n));
    }

    public static long numberOfPossibleAscents(int n) {
	// 3 //2 //1  //tribonaccchchchchchchhchcIIII
	if (n == 0)
	    return 1;
	if (n < 0)
	    return 0;
	long l1 = 1, l2 = 2, l3 = 4, l0 = 0;
	for (int i = 4; i < n + 1; i++) {
	    l0 = l1 + l2 + l3;
	    l1 = l2;
	    l2 = l3;
	    l3 = l0;
	}
	return l0;
    }
}
