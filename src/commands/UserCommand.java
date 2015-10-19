package commands;

import java.util.ArrayList;
import java.util.List;

public class UserCommand extends SpecialForm {
	private String myCommandName;
	private int myNumParameters;
	private List<String> myParameterList;
	private Command myProcedure;
	
	public UserCommand(String name, int numParameters) {
		myCommandName = name;
		myNumParameters = numParameters;
	}

	public UserCommand(String name, int numParameters, Command parameterList, Command proc) {
		myCommandName = name;
		myNumParameters = numParameters;
		initializeVariables(parameterList);
		myProcedure = proc;
	}
	
	public String getCommandName() {
		return myCommandName;
	}
	
	@Override
	public int getNumParameters() {
		return myNumParameters;
	}

	@Override
	public double returnDoubleValue() {
		return myProcedure.returnDoubleValue();
	}

	@Override
	public void execute() {
		applyArgumentsToProcedure();
		myProcedure.execute();
		removeTemporaryVariables();
	}
	
	private void initializeVariables(Command parameterList) {
		myParameterList = new ArrayList<String>();
		for (Command c: parameterList.getParameters()) {
			myParameterList.add(c.getExpression());
		}
	}
	
	private void applyArgumentsToProcedure() {
		for (int i=0; i < getNumParameters(); i++) {
			addVariable(myParameterList.get(i), getParameterDoubleValue(i));
		}
	}
	
	private void removeTemporaryVariables() {
		for (String parameter: myParameterList) {
			removeVariable(parameter);
		}
	}

}
