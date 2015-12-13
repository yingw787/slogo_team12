package commands;

public class Window extends TurtleCommand {

	@Override
	public int getNumParameters() {
		return 0;
	}

	@Override
	public double returnDoubleValue() {
		return 2;
	}

	@Override
	public void execute() {
		super.getController().setFence(false);
	}

}
