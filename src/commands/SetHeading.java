package commands;

import java.util.List;

import engine.IController;

public class SetHeading extends TurtleCommand {

	public SetHeading() {
		super();
	}
	
	public SetHeading(IController controller, String expression, List<Command> parameters) {
		super(controller, expression, parameters);
	}
	
	@Override
	public int getNumParameters() {
		return 1;
	}

	@Override
	public double returnDoubleValue() {
		double newDirection = getParameterDoubleValue(0);
		double degreesMoved = Math.abs(super.getController().getTurtleDirection() - newDirection);
		return degreesMoved;
	}

	@Override
	public void execute() {
		super.getController().setTurtleDirection(getParameterDoubleValue(0));
		addUpdatedTurtleStatus();
	}

}
