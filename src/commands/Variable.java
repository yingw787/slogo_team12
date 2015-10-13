package commands;

import java.util.List;

public class Variable extends BasicSyntax {

	public Variable() {
		super();
	}
	
	public Variable(String expression, List<Command> parameters) {
		super(expression, parameters);
	}
	
	@Override
	public int getNumParameters() {
		return 0;
	}

	@Override
	public double returnDoubleValue() {
		String key = super.getExpression();
		return super.getVariables().get(key);
	}

	@Override
	public void execute() {
		//do nothing
	}

}
