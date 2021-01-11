package task2613.command;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import task2613.CashMachine;
import task2613.ConsoleHelper;
import task2613.exception.InterruptOperationException;

public class LoginCommand implements Command {

    // private static String cardnumber = "123456789012", pin = "1234";
    private ResourceBundle validCreditCards = ResourceBundle
	    .getBundle(CashMachine.class.getPackage().getName() + ".resources.verifiedCards", new Locale("ru", "RU"));
    static {
	try {
	File f = new File(CashMachine.class.getPackage().getName());
	if (!f.exists())
	    Files.createDirectory(Paths.get(f.getAbsolutePath()+"/src/task2613/resources"));
	System.out.println(f.getAbsolutePath());
	Path q = Paths.get(f.getAbsolutePath().toString() + "/resources.verifiedCards");
	if (!Files.exists(Paths.get(f.getAbsolutePath().toString() + "/resources.verifiedCards"))) {
	    q = Files.createFile(Paths.get(f.getAbsolutePath().toString() + "/resources.verifiedCards"));
	}
	List<String> arr = Arrays.asList("123456789012=1234\n", "234567890123=2345\n", "345678901234=3456");
	FileWriter fw = new FileWriter(q.toFile());
	for (String s : arr) {
	    fw.write(s);
	}
	fw.close();
	System.out.println(arr);
	}catch (Exception e) {	    
	}
    }

    @Override
    public void execute() throws InterruptOperationException {
	while (true) {
	    ConsoleHelper.writeMessage("Введите номер карты (12 цифр) и пин-код(4 цифры):");
	    // String res=ConsoleHelper.readString();
	    try {
		String cn = ConsoleHelper.readString();
		String pn = ConsoleHelper.readString();
		// if(!cn.equals(cardnumber)||!pn.equals(pin)) {
		if (cn != null && pn != null && (cn = cn.trim()).length() == 12 && (pn = pn.trim()).length() == 4
			&& validCreditCards.containsKey(cn) && pn.equals(validCreditCards.getString(pn))) {
		    ConsoleHelper.writeMessage("Верификация прошла успешно.");
		    return;
		} else {
		    ConsoleHelper.writeMessage("Не верные данные. Попробуйте снова.");
		    continue;
		}
	    } catch (Exception e) {
		ConsoleHelper.writeMessage("Не верные данные. Попробуйте снова.");
		continue;
	    }
	}
    }
}
