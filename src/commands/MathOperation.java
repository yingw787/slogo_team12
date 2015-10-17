package commands;

import java.util.List;

import model.BackEndProperties;

public abstract class MathOperation extends Command {

	public MathOperation() {
		//do nothing
	}

	public MathOperation(String expression, List<Command> commandList) {
		super(expression, commandList);
	}
	
	@Override
	public String getCommandType() {
		return BackEndProperties.MATH_OPERATION;
	}

	@Override
	public abstract int getNumParameters();

	@Override
	public abstract double returnDoubleValue();

	@Override
	public abstract void execute();

}
