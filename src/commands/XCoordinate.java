package commands;

import java.util.List;

import engine.Controller;

public class XCoordinate extends TurtleQuery{

	public XCoordinate() {
		super();
	}
	
	public XCoordinate(Controller controller, String expression, List<Command> parameters) {
		super(controller, expression, parameters);
	}
	
	@Override
	public int getNumParameters() {
		return 0;
	}

	@Override
	public double returnDoubleValue() {
		double[] turtlePos = super.getController().getTurtlePosition();
		return turtlePos[0];
	}
}
