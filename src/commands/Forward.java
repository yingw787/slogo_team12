package commands;

import java.util.List;

public class Forward extends Command {

	public Forward() {
		super();
	}
	
	public Forward(String expression, List<Command> parameters) {
		super(expression, parameters);
	}
	
	@Override
	public int getNumParameters() {
		return 1;
	}

	@Override
	public int returnInt() {
		Command argument = this.getParameters().get(0);
		return argument.returnInt();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

}
