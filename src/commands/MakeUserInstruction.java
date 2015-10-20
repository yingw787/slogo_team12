package commands;

public class MakeUserInstruction extends SpecialForm {

	@Override
	public int getNumParameters() {
		return 3;
	}

	@Override
	public double returnDoubleValue() {
		// TODO fix this
		return 1;
	}

	@Override
	public void execute() {
		UserCommand command = getUserCommand(getParameterExpression(0));
		String commandName = command.getCommandName();
		int numParameters = command.getNumParameters();
		removeUserCommand(commandName);
		Command variablesList = getParameter(1);
		Command procedure = getParameter(2);
		addUserCommand(new UserCommand(commandName, numParameters, variablesList, procedure));
	}

}
