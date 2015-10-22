package commands;

import java.util.ArrayList;
import java.util.List;

public class AskWith extends MultipleTurtleCommand {

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
		List<Integer> turtleIDs = makeTurtleIDList();
		getParameter(1).executeCommandOverMultipleTurtles(turtleIDs);
	}

	private List<Integer> makeTurtleIDList() {
		int startID = super.getController().getActiveTurtleID();
		List<Integer> turtleIDs = new ArrayList<Integer>();
		for (int i=1; i <= super.getController().getNumTurtles(); i++) {
			super.getController().setActiveTurtleID(i);
			if (bitToBoolean((int)getParameter(0).returnDoubleValue())) {
				turtleIDs.add(i);
			}
		}
		super.getController().setActiveTurtleID(startID);
		return turtleIDs;
	}
}
