package commands;

public class SetPenSize extends DisplayCommand {

	@Override
	public int getNumParameters() {
		return 1;
	}

	@Override
	protected double returnDoubleValue() {
		return getParameterDoubleValue(0);
	}

	@Override
	public void execute() {
		super.getController().setPenSize(returnDoubleValue());
	}

}
