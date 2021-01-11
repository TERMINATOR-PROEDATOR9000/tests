package task2613;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.*;

import task2613.exception.NotEnoughMoneyException;

public class CurrencyManipulator {

    private String currencyCode;
    private Map<Integer, Integer> denominations;

    public CurrencyManipulator(String currencyCode) {
	super();
	this.currencyCode = currencyCode.toUpperCase();
	this.denominations = new ConcurrentHashMap<Integer, Integer>();
    }

    public String getCurrencyCode() {
	return currencyCode;
    }

    public void addAmount(int denomination, int count) {
	if (denominations.containsKey(denomination)) {
	    denominations.put(denomination, denominations.get(denomination) + count);
	} else {
	    denominations.put(denomination, count);
	}
    }

    public int getTotalAmount() {
	int total = 0;
	for (Integer i : denominations.keySet()) {
	    total += denominations.get(i) * i;
	}
	return total;
    }

    public boolean hasMoney() {
	return !denominations.isEmpty();
    }

    public boolean isAmountAvailable(int amount) {
	return getTotalAmount() >= amount;
    }

    public Map<Integer, Integer> withdrawAmount(int amount) throws NotEnoughMoneyException {
	/*Map<Integer, Integer> result = new HashMap<Integer, Integer>();
	Map<Integer, Integer> copy = new HashMap<Integer, Integer>(denominations);
	List<Integer> moneys = denominations.keySet().stream().collect(Collectors.toList());
	Collections.sort(moneys);
	Collections.reverse(moneys);
	label:
	for (Integer i : moneys) {
	    while(amount > 0) {			    
		if (copy.containsKey(i)) {
		    if (copy.get(i)- amount < 0) {
			continue label;
		    }
		}
		if (result.containsKey(i)) {
		    int buf = result.get(i);
		    int forAdd = buf + 1;
		    result.put(i, forAdd);

		    amount = amount - i;

		    int b = copy.get(i);
		    b = b - 1;
		    copy.put(i, b);
		    System.err.println(amount + "\t" + b);
		} else {
		    result.put(i, 1);
		    int b = copy.get(i);
		    b = b - 1;
		    copy.put(i, b);
		    amount = amount - i;
		}
	    };
	}	
	/*result.entrySet().stream().forEach(e -> {
	    System.err.println("stream: "+e.getKey() + "\t" + e.getValue());
	});
	if (amount != 0) {
	    throw new NotEnoughMoneyException();
	}
	denominations = copy;
	return result;
    }*/
    int sum = amount;                                                  
    HashMap<Integer, Integer> copyDenominations = new HashMap<>(denominations);                                                  
                                              
    ArrayList<Integer> keys = new ArrayList<>(this.denominations.keySet());                                                  
                                              
    Collections.sort(keys);                                                  
    Collections.reverse(keys);                                                  
                                              
    TreeMap<Integer, Integer> resultMap = new TreeMap<>(new Comparator<Integer>() {                                                  
        @Override                                                  
        public int compare(Integer o1, Integer o2) {                                                  
            return o2.compareTo(o1);                                                  
        }                                                  
    });                                                  
                                              
    for (Integer denomination : keys) {                                                  
        final int key = denomination;                                                  
        int value = copyDenominations.get(key);                                                  
        while (true) {                                                  
            if (sum < key || value == 0) {                                                  
                copyDenominations.put(key, value);                                                  
                break;                                                  
            }                                                  
            sum -= key;                                                  
            value--;                                                  
                                              
            if (resultMap.containsKey(key))                                                  
                resultMap.put(key, resultMap.get(key) + 1);                                                  
            else                                                  
                resultMap.put(key, 1);                                                  
        }                                                  
    }                                                  

    if (sum > 0)
	throw new NotEnoughMoneyException();
    else {
	this.denominations.clear();
	this.denominations.putAll(copyDenominations);
    }
    return resultMap;
    }

}
/*
2
USD
500 2
2
USD
200 3
2
USD
100 1
2
USD
50 12

3
USD
600
*/

