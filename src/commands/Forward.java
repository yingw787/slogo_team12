package commands;

import java.util.List;

import engine.Controller;

public class Forward extends TurtleCommand {

	public Forward() {
		super();
	}
	
	public Forward(Controller controller, String expression, List<Command> parameters) {
		super(controller, expression, parameters);
	}
	
	@Override
	public int getNumParameters() {
		return 1;
	}

	@Override
	public double returnDoubleValue() {
		return getParameterValue();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

}
