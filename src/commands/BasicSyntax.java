package commands;

import java.util.List;

public abstract class BasicSyntax extends Command {

	public BasicSyntax() {
		//do nothing
	}

	public BasicSyntax(String expression, List<Command> commandList) {
		super(expression, commandList);
	}
	
	@Override
	public String getCommandType() {
		return "BasicSyntax";
	}

	@Override
	public abstract int getNumParameters();

	@Override
	public abstract double returnDoubleValue();

	@Override
	public abstract void execute();
}
