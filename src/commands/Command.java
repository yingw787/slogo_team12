package commands;

import java.util.List;

public abstract class Command {
	private String value;
	private List<Command> parameters;
	
	public Command() {
		//do nothing
	}
	
	public Command(String expression, List<Command> params) {
		value = expression;
		parameters = params;
	}
	
	public String getValue() {
		return value;
	}
	
	public List<Command> getParameters() {
		return parameters;
	}
	
	public void setParameters(List<Command> commandList) {
		this.parameters = commandList;
	}
	
	public abstract int getNumParameters();
	
	public abstract int returnInt();
	
	public abstract void execute();
}
