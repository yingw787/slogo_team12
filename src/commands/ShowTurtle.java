package commands;

import java.util.List;

import engine.Controller;

public class ShowTurtle extends TurtleCommand {

	public ShowTurtle() {
		super();
	}
	
	public ShowTurtle(Controller controller, String expression, List<Command> parameters) {
		super(controller, expression, parameters);
	}
	
	@Override
	public int getNumParameters() {
		return 0;
	}

	@Override
	public double returnDoubleValue() {
		return 1;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

}
