package commands;

import java.util.List;

public abstract class BooleanOperation extends Command {

	public BooleanOperation() {
		//do nothing
	}

	public BooleanOperation(String expression, List<Command> commandList) {
		super(expression, commandList);
	}
	
	@Override
	public String getCommandType() {
		return "BooleanOperation";
	}

	@Override
	public abstract int getNumParameters();

	@Override
	public abstract double returnDoubleValue();

	@Override
	public abstract void execute();
}
