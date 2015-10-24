package commands;

import java.util.List;

import engine.Controller;

public class ClearScreen extends TurtleCommand {
	private static final double[] END_POSITION = { 0, 0 };
	
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
		double[] endPos = END_POSITION;
		return calculateDistance(startPos, endPos);
	}

	@Override
	public void execute() {
		super.getController().setTurtlePosition(END_POSITION);
		super.getController().clear();
		addUpdatedTurtleStatus();
	}

}
