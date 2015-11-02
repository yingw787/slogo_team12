package commands;

import java.util.List;

import engine.IController;
import model.BackEndProperties;

public abstract class TurtleQuery extends Command {

	public TurtleQuery() {
		//do nothing
	}
	
	public TurtleQuery(IController controller, String expression, List<Command> commandList) {
		super(controller, expression, commandList);
	}
	
	@Override
	public String getCommandType() {
		return BackEndProperties.TURTLE_QUERY;
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
