package commands;

import java.util.List;

import engine.IController;

public class IfElse extends SpecialForm {

	public IfElse() {
		super();
	}
	
	public IfElse(IController controller, String expression, List<Command> parameters) {
		super(controller, expression, parameters);
	}
	
	@Override
	public int getNumParameters() {
		return 3;
	}

	@Override
	public double returnDoubleValue() {
		if (bitToBoolean((int)getParameterDoubleValue(0))) {
			return getParameterDoubleValue(1);
		}
		return getParameterDoubleValue(2);
	}

	@Override
	public void execute() {
		if (bitToBoolean((int)getParameterDoubleValue(0))) {
			getParameter(1).execute();
		} else {
			getParameter(2).execute();
		}

	}

}
