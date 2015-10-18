package commands;

import java.util.List;

import engine.Controller;

public class IsShowing extends TurtleQuery {

	public IsShowing() {
		super();
	}
	
	public IsShowing(Controller controller, String expression, List<Command> parameters) {
		super(controller, expression, parameters);
	}
	
	@Override
	public int getNumParameters() {
		return 0;
	}

	@Override
	public double returnDoubleValue() {
		return super.getController().isTurtleShowing() ? 1 : 0;
	}
}
