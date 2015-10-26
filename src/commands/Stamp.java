package commands;

public class Stamp extends DisplayCommand {

	@Override
	public int getNumParameters() {
		return 0;
	}

	@Override
	protected double returnDoubleValue() {
		return super.getController().getActiveTurtleID();
	}

	@Override
	public void execute() {
		super.getController().makeStamp();
	}

}
