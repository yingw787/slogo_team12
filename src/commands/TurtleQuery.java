package commands;

import java.util.List;

import engine.Controller;

public abstract class TurtleQuery extends Command {

	public TurtleQuery() {
		//do nothing
	}
	
	public TurtleQuery(Controller controller, String expression, List<Command> commandList) {
		super(controller, expression, commandList);
	}
	
	@Override
	public String getCommandType() {
		return "TurtleQuery";
	}

	@Override
	public abstract int getNumParameters();

	@Override
	public abstract double returnDoubleValue();

	@Override
	public abstract void execute();

}
