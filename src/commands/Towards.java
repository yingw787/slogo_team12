package commands;

import java.util.List;

import engine.Controller;

public class Towards extends TurtleCommand {
	
	public Towards() {
		super();
	}
	
	public Towards(Controller controller, String expression, List<Command> parameters) {
		super(controller, expression, parameters);
	}
	
	@Override
	public int getNumParameters() {
		return 2;
	}

	@Override
	public double returnDoubleValue() {
		double finalAngle = calculateNewAngle();
		double currentAngle = super.getController().getTurtleDirection();
		if (currentAngle < finalAngle) {
			return finalAngle - currentAngle;
		}
		return 360 - currentAngle + finalAngle;
	}

	@Override
	public void execute() {
		super.getController().setTurtleDirection(calculateNewAngle());
		addUpdatedTurtleStatus();
	}
	
	private double calculateNewAngle() {
		List<Command> params = super.getParameters();
		Command xValue = params.get(0);
		Command yValue = params.get(1);
		double radianAngle = Math.atan2(xValue.returnDoubleValue(), yValue.returnDoubleValue());
		double finalAngle = Math.toDegrees(radianAngle);
		return finalAngle;
	}

}
