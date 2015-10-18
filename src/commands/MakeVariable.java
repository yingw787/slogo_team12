package commands;

import java.util.List;

import engine.Controller;

public class MakeVariable extends SpecialForm {

	public MakeVariable() {
		super();
	}
	
	public MakeVariable(Controller controller, String expression, List<Command> parameters) {
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
		String variableName = getParameterExpression(0);
		double variableValue = getParameterDoubleValue(1);
		addVariable(variableName, variableValue);
	}

}
