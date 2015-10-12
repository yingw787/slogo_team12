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
	public int returnInt() {
		double finalAngle = calculateNewAngle();
		double currentAngle = super.getController().getTurtleDirection();
		if (currentAngle < finalAngle) {
			return (int)(finalAngle - currentAngle);
		}
		return (int)(360 - currentAngle + finalAngle);
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	
	private double calculateNewAngle() {
		List<Command> params = super.getParameters();
		Command xValue = params.get(0);
		Command yValue = params.get(1);
		double radianAngle = Math.atan2(xValue.returnInt(), yValue.returnInt());
		int finalAngle = convertRadiansToDegrees(radianAngle);
		return 1.0*finalAngle;
	}

}
