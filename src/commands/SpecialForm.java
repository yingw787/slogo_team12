package commands;

import java.util.List;

import engine.Controller;
import model.BackEndProperties;
import model.TurtleStatus;

public abstract class SpecialForm extends Command {

	public SpecialForm() {
		//do nothing
	}
	
	public SpecialForm(Controller controller, String expression, List<Command> commandList) {
		super(controller, expression, commandList);
	}
	
	@Override
	public String getCommandType() {
		return BackEndProperties.SPECIAL_FORM;
	}
	
	@Override
	public abstract int getNumParameters();

	@Override
	public abstract double returnDoubleValue();

	@Override
	public abstract void execute();

}
