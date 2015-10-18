package commands;

import java.util.List;

import engine.Controller;

public class PenUp extends TurtleCommand {

	public PenUp() {
		super();
	}
	
	public PenUp(Controller controller, String expression, List<Command> parameters) {
		super(controller, expression, parameters);
	}
	
	@Override
	public int getNumParameters() {
		return 0;
	}

	@Override
	public double returnDoubleValue() {
		return 1;
	}

	@Override
	public void execute() {
		super.getController().setIsTurtlePenDown(false);
		addUpdatedTurtleStatus();
	}

}
