package commands;

import java.util.List;

import engine.Controller;

public class PenDown extends TurtleCommand {

	public PenDown() {
		super();
	}
	
	public PenDown(Controller controller, String expression, List<Command> parameters) {
		super(controller, expression, parameters);
	}
	
	@Override
	public int getNumParameters() {
		return 0;
	}

	@Override
	public double returnDoubleValue() {
		return 0;
	}

	@Override
	public void execute() {
		super.getController().setIsTurtlePenDown(true);
		addUpdatedTurtleStatus();
	}

}
