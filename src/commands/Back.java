package commands;

import java.util.List;

import engine.IController;

public class Back extends TurtleCommand {

	public Back() {
		super();
	}
	
	public Back(IController controller, String expression, List<Command> parameters) {
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
		moveTurtleForwardBackward(-1);
	}

}
