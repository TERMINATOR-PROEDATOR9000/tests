package task2613;

import java.util.*;

public class CurrencyManipulatorFactory {
    private static Map<String, CurrencyManipulator> map = new HashMap<String, CurrencyManipulator>();

    private CurrencyManipulatorFactory() {
    }

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
	if (currencyCode == null) {
	    throw new IllegalArgumentException();
	}
	currencyCode = currencyCode.toUpperCase();
	if (map.containsKey(currencyCode)) {
	    return map.get(currencyCode);
	} else {
	    CurrencyManipulator temp = new CurrencyManipulator(currencyCode);
	    map.put(currencyCode, temp);
	    return map.get(currencyCode);
	}
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators() {
	return map.values();
    }
    
}
