package commands;

import java.util.List;

import engine.Controller;

public class Left extends TurtleCommand {

	public Left() {
		super();
	}
	
	public Left(Controller controller, String expression, List<Command> parameters) {
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
		double newDirection = returnDoubleValue();
		double difference = super.getController().getTurtleDirection() + newDirection;
		return difference > 360 ? difference - 360 : difference;
	}

}
