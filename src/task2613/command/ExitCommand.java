package task2613.command;

import task2613.ConsoleHelper;
import task2613.exception.InterruptOperationException;

class ExitCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
	ConsoleHelper.writeMessage("Вы действительно хотите выйти? Y/N");
	String res=ConsoleHelper.readString();
	if(res.equalsIgnoreCase("Y")){
	    ConsoleHelper.writeMessage("До свидания!");
	} else {
	    
	}
    }
}
