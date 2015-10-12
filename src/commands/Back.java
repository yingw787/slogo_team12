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
	public int returnInt() {
		Command argument = this.getParameters().get(0);
		return argument.returnInt();
	}

	@Override
	public void execute() {
		int increment = returnInt();
		int[] startPos = super.getController().getTurtlePosition();
		//TODO calculate how to go backwards by "increment"
	}

}
