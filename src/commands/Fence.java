package commands;

public class Fence extends TurtleCommand {

	@Override
	public int getNumParameters() {
		return 0;
	}

	@Override
	public double returnDoubleValue() {
		return 3;
	}

	@Override
	public void execute() {
		super.getController().setFence(true);
	}

}
