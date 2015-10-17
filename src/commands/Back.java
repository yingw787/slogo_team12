package commands;

import java.util.List;

import engine.Controller;

public class Back extends TurtleCommand {

	public Back() {
		super();
	}
	
	public Back(Controller controller, String expression, List<Command> parameters) {
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
		double increment = returnDoubleValue();
		double[] startPos = super.getController().getTurtlePosition();
		//TODO calculate how to go backwards by "increment"
	}

}
