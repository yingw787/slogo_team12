package commands;

public class ID extends MultipleTurtleCommand {

	@Override
	public int getNumParameters() {
		return 0;
	}

	@Override
	public double returnDoubleValue() {
		return super.getController().getActiveTurtleID();
	}

	@Override
	public void execute() {
		// do nothing
	}

}
