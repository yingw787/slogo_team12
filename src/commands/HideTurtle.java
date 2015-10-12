package commands;

import java.util.List;

import engine.Controller;

public class HideTurtle extends TurtleCommand {
	
	public HideTurtle() {
		super();
	}
	
	public HideTurtle(Controller controller, String expression, List<Command> parameters) {
		super(controller, expression, parameters);
	}
	
	@Override
	public int getNumParameters() {
		return 0;
	}

	@Override
	public int returnInt() {
		return 0;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

}
