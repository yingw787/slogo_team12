package commands;

public class SetPenColor extends DisplayCommand {

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
		super.getController().setPenColor((int)returnDoubleValue());
	}

}
