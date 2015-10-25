package commands;

public class GetPenColor extends DisplayCommand {

	@Override
	public int getNumParameters() {
		return 0;
	}

	@Override
	protected double returnDoubleValue() {
		return super.getController().getPenColor();
	}

	@Override
	public void execute() {
		//do nothing
	}

}
