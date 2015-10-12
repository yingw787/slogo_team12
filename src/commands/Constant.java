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
	public int returnInt() {
		return Integer.parseInt(this.getValue());
	}

	@Override
	public void execute() {
		//do nothing
	}

}
