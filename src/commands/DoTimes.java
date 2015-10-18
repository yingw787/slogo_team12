package commands;

import java.util.List;

import engine.Controller;
import model.TurtleStatus;

public class DoTimes extends SpecialForm {

	public DoTimes() {
		super();
	}
	
	public DoTimes(Controller controller, String expression, List<Command> parameters) {
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
		Double limit = arg1.getParameterDoubleValue(1);
		for (int i=1; i <= limit; i++) {
			addVariable(variableName, i*1.0);
			arg2.execute();
		}
		removeVariable(variableName);
	}

}
