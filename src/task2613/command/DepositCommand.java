package task2613.command;

import task2613.ConsoleHelper;
import task2613.CurrencyManipulator;
import task2613.CurrencyManipulatorFactory;
import task2613.exception.InterruptOperationException;

class DepositCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException{
	CurrencyManipulator c = CurrencyManipulatorFactory
		.getManipulatorByCurrencyCode(ConsoleHelper.askCurrencyCode());
	String[] t = ConsoleHelper.getValidTwoDigits(c.getCurrencyCode());
	c.addAmount(Integer.valueOf(t[0]), Integer.valueOf(t[1]));
	ConsoleHelper.writeMessage(Integer.toString(c.getTotalAmount())+" "+c.getCurrencyCode()+" добавлены на баланс.");
    }
}