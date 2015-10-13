package commands;

import java.util.List;

import engine.Controller;

public class Home extends TurtleCommand {
	
	public Home() {
		super();
	}
	
	public Home(Controller controller, String expression, List<Command> parameters) {
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
		double[] newPos = { 0, 0 };
		super.getController().setTurtlePosition(newPos);
	}

}
