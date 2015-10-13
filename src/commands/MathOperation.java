package commands;

import java.util.List;

public abstract class MathOperation extends Command {

	public MathOperation() {
		//do nothing
	}

	public MathOperation(String expression, List<Command> commandList) {
		super(expression, commandList);
	}
	
	@Override
	public String getCommandType() {
		return "MathOperation";
	}

	@Override
	public abstract int getNumParameters();

	@Override
	public abstract double returnDoubleValue();

	@Override
	public abstract void execute();

}
