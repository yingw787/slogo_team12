package commands;

import java.util.List;

import engine.Controller;

public class ClearScreen extends TurtleCommand {
	
	public ClearScreen() {
		super();
	}
	
	public ClearScreen(Controller controller, String expression, List<Command> parameters) {
		super(controller, expression, parameters);
	}

	@Override
	public int getNumParameters() {
		return 0;
	}

	@Override
	public double returnDoubleValue() {
		double[] startPos = super.getController().getTurtlePosition();
		double[] endPos = { 0, 0 };
		return calculateDistance(startPos, endPos);
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

}
