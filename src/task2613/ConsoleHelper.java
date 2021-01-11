package task2613;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import task2613.exception.InterruptOperationException;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
	System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
	try {
	    String result=bis.readLine();
	    if(result.equalsIgnoreCase("exit")){
		throw new InterruptOperationException();
	    }
	    return result;
	} catch (IOException e) {
	    return null;
	} 
    }

    public static String askCurrencyCode() throws InterruptOperationException {
	writeMessage("Введите код валюты:");
	String temp = "";
	while (true) {
	    temp = readString();
	    if (temp.length() == 3) {
		return temp.toUpperCase();
	    } else {
		writeMessage("Данные не корректны. Повторите ввод:");
	    }
	}
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
	String[] result = new String[2];
	while (true) {
	    try {
		writeMessage("Введите номинал банкнот и колличество банкнот через пробел:");
		String temp = readString();
		int one = Integer.valueOf(temp.substring(0, temp.indexOf(" ")));
		int two = Integer.valueOf(temp.substring(temp.indexOf(" ") + 1, temp.length()));
		if (one < 0 || two < 0) {
		    throw new NumberFormatException();
		}
		result[0] = Integer.toString(one);
		result[1] = Integer.toString(two);
		return result;
	    } catch (NumberFormatException | IndexOutOfBoundsException e) {
		writeMessage("Данные не корректны. Повторите ввод:");
	    }
	}
    }

    public static Operation askOperation() throws InterruptOperationException {
	writeMessage("Список доступных операций:\n\t" + Arrays.toString(Operation.values()) + "\nВыберите операцию:");
	while (true) {
	    try {
		int val = Integer.valueOf(readString());
		if (val < 1 || val > Operation.values().length) {
		    throw new IllegalArgumentException();
		}
		return Operation.getAllowableOperationByOrdinal(val);
	    } catch (IllegalArgumentException e) {
		writeMessage("Данные не корректны. Повторите ввод:");
	    }
	}
    }
}
