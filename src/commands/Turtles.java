package commands;

public class Turtles extends MultipleTurtleCommand {

	@Override
	public int getNumParameters() {
		return 0;
	}

	@Override
	public double returnDoubleValue() {
		return super.getController().getNumTurtles();
	}

	@Override
	public void execute() {
		//do nothing
	}

}
