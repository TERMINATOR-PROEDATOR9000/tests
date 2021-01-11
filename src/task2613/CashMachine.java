package task2613;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import task2613.command.CommandExecutor;
import task2613.exception.InterruptOperationException;

/** С МОБИЛЬНОГО ЗАДАЧА НЕ РЕШАЕМА! **/

public class CashMachine {
    public static void main(String... args){
	/*
	 * Locale.setDefault(Locale.ENGLISH); Operation op; try {
	 * CommandExecutor.execute(Operation.LOGIN); do { op =
	 * ConsoleHelper.askOperation();
	 * ConsoleHelper.writeMessage("Вы выбрали операцию "+op.toString());
	 * CommandExecutor.execute(op); } while (op != Operation.EXIT); } catch
	 * (InterruptOperationException e) { ConsoleHelper.writeMessage("Прощайте!"); }
	 */

	File f = new File(CashMachine.class.getPackage().getName());
	System.out.println(f.getAbsolutePath().toString());
	Path dir = Paths.get(f.getAbsolutePath() + "\\resources");
	System.out.println(dir);	
	File dd=new File(dir.toString());
	dd.mkdir();
	try {
	Path q=Files.createFile(Paths.get(dd + "/resources.verifiedCards"));
	List<String> arr = Arrays.asList("123456789012=1234\n", "234567890123=2345\n", "345678901234=3456");
	FileWriter fw = new FileWriter(q.toFile());
	for (String s : arr) {
	    fw.write(s);
	}
	fw.close();
	}catch (Exception e) {	   
	}
    }
}
