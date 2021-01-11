package task2613.command;

import java.util.*;

import task2613.Operation;
import task2613.exception.InterruptOperationException;

public class CommandExecutor {
    private static final Map<Operation, Command>allKnownCommandsMap=new HashMap<Operation, Command>();
    private CommandExecutor() {
    }
    static {
	allKnownCommandsMap.put(Operation.INFO, new InfoCommand());
	allKnownCommandsMap.put(Operation.DEPOSIT, new DepositCommand());
	allKnownCommandsMap.put(Operation.WITHDRAW, new WithdrawCommand());
	allKnownCommandsMap.put(Operation.EXIT, new ExitCommand());
	allKnownCommandsMap.put(Operation.LOGIN, new LoginCommand());
    }
    
    public static final void execute(Operation operation) throws InterruptOperationException {
	allKnownCommandsMap.get(operation).execute();
    }
}
