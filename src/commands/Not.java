package commands;

import java.util.List;

public class Not extends BooleanOperation {

	public Not() {
		super();
	}
	
	public Not(String expression, List<Command> parameters) {
		super(expression, parameters);
	}
	
	@Override
	public int getNumParameters() {
		return 1;
	}

	@Override
	public double returnDoubleValue() {
		return getParameterDoubleValue(0) == 1 ? 0 : 1;
	}
}
