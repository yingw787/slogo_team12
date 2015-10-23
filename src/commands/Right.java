package commands;

import java.util.List;

import engine.Controller;

public class Right extends TurtleCommand {

	public Right() {
		super();
	}
	
	public Right(Controller controller, String expression, List<Command> parameters) {
		super(controller, expression, parameters);
	}
	
	@Override
	public int getNumParameters() {
		return 1;
	}

	@Override
	public double returnDoubleValue() {
		return getParameterDoubleValue(0);
	}

	@Override
	public void execute() {
		super.getController().setTurtleDirection(calculateNewDirection());
		addUpdatedTurtleStatus();
	}
	
	private double calculateNewDirection() {
		double degrees = returnDoubleValue();
		double newDirection = super.getController().getTurtleDirection() - degrees;
		return newDirection < 0 ? 360 + newDirection : newDirection;
	}

}
