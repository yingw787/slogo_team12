package commands;

import java.util.List;

public class Tell extends MultipleTurtleCommand {

	@Override
	public int getNumParameters() {
		return 1;
	}

	@Override
	public double returnDoubleValue() {
		return getParameterDoubleValue(0);
	}

	@Override
	public void execute() {
		List<Integer> newActiveTurtles = makeActiveTurtleList();
		System.out.println("active turtles: " + newActiveTurtles.toString());
		repopulateActiveTurtles(newActiveTurtles);
	}

}
