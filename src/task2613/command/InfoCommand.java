package task2613.command;

import java.util.*;

import task2613.ConsoleHelper;
import task2613.CurrencyManipulator;
import task2613.CurrencyManipulatorFactory;

class InfoCommand implements Command {
    @Override
    public void execute() {
	int check=0;
	for(CurrencyManipulator cm:CurrencyManipulatorFactory.getAllCurrencyManipulators()) {
	    if(cm.hasMoney()) {
		ConsoleHelper.writeMessage(cm.getCurrencyCode()+" - "+Integer.toString(cm.getTotalAmount())); 
		check++;
	    }	    
	};
	if(check==0) {
	    ConsoleHelper.writeMessage("No money available.");
	}
    }
}