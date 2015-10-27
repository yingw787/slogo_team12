package commands;

import java.util.List;

import model.BackEndProperties;

public abstract class BasicSyntax extends Command {

	public BasicSyntax() {
		super();
	}

	public BasicSyntax(String expression, List<Command> commandList) {
		super(expression, commandList);
	}
	
	@Override
	public String getCommandType() {
		return BackEndProperties.BASIC_SYNTAX;
	}

	@Override
	public abstract int getNumParameters();

	@Override
	public abstract double returnDoubleValue();

	@Override
	public abstract void execute();
	
}
