package commands;

import java.util.List;

import engine.Controller;

public class If extends SpecialForm {

	public If() {
		super();
	}
	
	public If(Controller controller, String expression, List<Command> parameters) {
		super(controller, expression, parameters);
	}
	
	@Override
	public int getNumParameters() {
		return 2;
	}

	@Override
	public double returnDoubleValue() {
		return getParameterDoubleValue(1);
	}

	@Override
	public void execute() {
		if (bitToBoolean((int)getParameterDoubleValue(0))) {
			getParameter(1).execute();
		}
	}

}
