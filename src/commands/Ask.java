package commands;

import java.util.List;

public class Ask extends MultipleTurtleCommand {

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
		List<Integer> activeTurtles = makeActiveTurtleList();
		for (Command c: getParameter(1).getParameters()) {
			c.executeCommandOverMultipleTurtles(activeTurtles);
		}
	}

}
