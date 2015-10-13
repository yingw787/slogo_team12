package commands;

import java.util.List;

import engine.Controller;

public class Right extends TurtleCommand {

	public Right() {
		super();
	}
	
	public Right(Controller controller, String expression, List<Command> parameters) {
		super(controller, expression, parameters);
	}
	
	@Override
	public int getNumParameters() {
		return 1;
	}

	@Override
	public double returnDoubleValue() {
		return returnParameterValue();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

}
