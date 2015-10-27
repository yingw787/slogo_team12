package commands;

import java.util.List;

import exceptions.NotEnoughParametersException;

public class SlogoList extends BasicSyntax {

	public SlogoList() {
		super();
	}
	
	public SlogoList(String expression, List<Command> parameters) {
		super(expression, parameters);
	}
	
	@Override
	public int getNumParameters() {
		return 0;
	}

	@Override
	public double returnDoubleValue() {
		return getParameterDoubleValue(super.getParameters().size()-1);
	}

	@Override
	public void execute() {
		for (Command command: super.getParameters()) {
			try {
				command.executeCommandOverActiveTurtles();
			} catch (NotEnoughParametersException e) {
//				System.out.println("I am in SLogoList.java");
				// should not need to come here in order to handle the exception; should be handled within Translator.java 
//				e.printStackTrace();
			}
		}
	}
}
