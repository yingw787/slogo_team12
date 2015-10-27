package commands;

public class ClearStamps extends DisplayCommand {

	@Override
	public int getNumParameters() {
		return 0;
	}

	@Override
	protected double returnDoubleValue() {
		return super.getController().getNumStamps();
	}

	@Override
	public void execute() {
		super.getController().clearStamps();
	}

}
