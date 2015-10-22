package commands;

import java.util.List;

import model.BackEndProperties;

public abstract class BooleanOperation extends Command {

	public BooleanOperation() {
		//do nothing
	}

	public BooleanOperation(String expression, List<Command> commandList) {
		super(expression, commandList);
	}
	
	@Override
	public String getCommandType() {
		return BackEndProperties.BOOLEAN_OPERATION;
	}

	@Override
	public abstract int getNumParameters();

	@Override
	public abstract double returnDoubleValue();

	@Override
	public void execute() {
		// do nothing
	}
}
