package commands;

import java.util.List;

public class SlogoList extends BasicSyntax {

	public SlogoList() {
		super();
	}
	
	public SlogoList(String expression, List<Command> parameters) {
		super(expression, parameters);
	}
	
	@Override
	public int getNumParameters() {
		return 0;
	}

	@Override
	public double returnDoubleValue() {
		return getParameterDoubleValue(super.getParameters().size()-1);
	}

	@Override
	public void execute() {
		for (Command c: super.getParameters()) {
			executeNestedCommands(c);
		}
	}

}
