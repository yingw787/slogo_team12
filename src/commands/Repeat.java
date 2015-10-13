package commands;

import java.util.List;

import engine.Controller;

public class Repeat extends SpecialForm {
	private static final String REP_COUNT = ":repcount";

	public Repeat() {
		super();
	}
	
	public Repeat(Controller controller, String expression, List<Command> parameters) {
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
		double numRepeats = getParameterDoubleValue(0);
		for (int i=1; i <= numRepeats; i++) {
			addVariable(REP_COUNT, i*1.0);
			getParameter(1).execute();
		}
		removeVariable(REP_COUNT);
	}

}
