package task2613.command;

import task2613.ConsoleHelper;
import task2613.CurrencyManipulator;
import task2613.CurrencyManipulatorFactory;
import task2613.exception.InterruptOperationException;
import task2613.exception.NotEnoughMoneyException;

class WithdrawCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
	String code = ConsoleHelper.askCurrencyCode();
	CurrencyManipulator curr = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
	while (true) {
	    ConsoleHelper.writeMessage("Введите желаемую сумму деняг:");
	    try {
		int sum = Integer.valueOf(ConsoleHelper.readString());
		if(sum<0) {
		   throw new NumberFormatException();
		}
		if(!curr.isAmountAvailable(sum)) {
		    throw new NotEnoughMoneyException();
		}
		ConsoleHelper.writeMessage("\t"+code+" - "+curr.withdrawAmount(sum));
		break;
	    } catch (NumberFormatException e) {
		ConsoleHelper.writeMessage("Не верная сумма. Попробуйте снова.");
	    } catch (NotEnoughMoneyException e) {
		ConsoleHelper.writeMessage("Недостаточно денег на счету. Попробуйте снова.");
	    }
	}
    }
}
