package commands;

import java.util.List;

import engine.IController;

public class Heading extends TurtleQuery {

	public Heading() {
		super();
	}
	
	public Heading(IController controller, String expression, List<Command> parameters) {
		super(controller, expression, parameters);
	}
	
	@Override
	public int getNumParameters() {
		return 0;
	}

	@Override
	public double returnDoubleValue() {
		return super.getController().getTurtleDirection();
	}
}
