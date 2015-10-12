package commands;

import java.util.List;

import engine.Controller;

public class IsPenDown extends TurtleQuery {

	public IsPenDown() {
		super();
	}
	
	public IsPenDown(Controller controller, String expression, List<Command> parameters) {
		super(controller, expression, parameters);
	}
	
	@Override
	public int getNumParameters() {
		return 0;
	}

	@Override
	public double returnDoubleValue() {
		return super.getController().isTurtlePenDown() ? 1 : 0;
	}

	@Override
	public void execute() {
		//do nothing
	}

}
