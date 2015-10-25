package commands;

public class GetShape extends DisplayCommand {

	@Override
	public int getNumParameters() {
		return 0;
	}

	@Override
	protected double returnDoubleValue() {
		return super.getController().getShape();
	}

	@Override
	public void execute() {
		//do nothing
	}

}
