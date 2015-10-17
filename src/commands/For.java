package commands;

import java.util.List;

import engine.Controller;

public class For extends SpecialForm {

	public For() {
		super();
	}
	
	public For(Controller controller, String expression, List<Command> parameters) {
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
		Command arg1 = getParameter(0);
		Command arg2 = getParameter(1);
		String variableName = arg1.getParameterExpression(0);
		double start = arg1.getParameterDoubleValue(1);
		double end = arg1.getParameterDoubleValue(2);
		double increment = arg1.getParameterDoubleValue(3);
		for (double i=start; i <= end; i += increment) {
			addVariable(variableName, i);
			arg2.execute();
		}
		removeVariable(variableName);
	}

}
