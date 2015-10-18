package commands;

// CommandName is supposed to be the name of a user-defined command, but it could also be an exception. 
// Check for an exception if the CommandFactory ever returns a CommandName object, because that is the only type of object that could possibly be an exception. 
// If is exception, do exception handling. Otherwise, continue to execute the command as defined in the user-command map. 
public class CommandName extends BasicSyntax {

	
	
	@Override
	public int getNumParameters() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double returnDoubleValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

}
