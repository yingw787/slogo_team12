package commands;

import java.util.List;

import engine.Controller;
import model.BackEndProperties;

public abstract class MultipleTurtleCommand extends Command {

	public MultipleTurtleCommand() {
		//do nothing
	}
	
	public MultipleTurtleCommand(Controller controller, String expression, List<Command> commandList) {
		super(controller, expression, commandList);
	}
	
	@Override
	public String getCommandType() {
		return BackEndProperties.MULTIPLE_TURTLE_COMMAND;
	}

	@Override
	public abstract int getNumParameters();

	@Override
	public abstract double returnDoubleValue();

	@Override
	public abstract void execute();

}
