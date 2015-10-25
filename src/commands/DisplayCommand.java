package commands;

import model.BackEndProperties;

public abstract class DisplayCommand extends Command {

	@Override
	public String getCommandType() {
		return BackEndProperties.DISPLAY_COMMAND;
	}

	@Override
	public abstract int getNumParameters();

	@Override
	protected abstract double returnDoubleValue();

	@Override
	public abstract void execute();

}
