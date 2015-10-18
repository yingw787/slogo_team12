package commands;

import java.util.List;

public class Constant extends BasicSyntax {
	
	public Constant() {
		super();
	}
	
	public Constant(String expression, List<Command> parameters) {
		super(expression, parameters);
	}

	@Override
	public int getNumParameters() {
		return 0;
	}

	@Override
	public double returnDoubleValue() {
		return Double.parseDouble(this.getExpression());
	}

	@Override
	public void execute() {
		addUpdatedTurtleStatus();
	}
}
