package commands;

import java.util.Map;

public class CommandFactory {
	private Map<String,UserCommand> myUserCommands;

	public CommandFactory(Map<String,UserCommand> userCommands) {
		myUserCommands = userCommands;
	}
	
	public Command getCommand(String command, String expression) throws Exception {
		switch (command) {
		//BasicSyntax
		case ("Constant"):
			return new Constant();
		case ("ListStart"):
			return new SlogoList();
		case ("Variable"):
			return new Variable();
		//TurtleCommands
		case ("Forward"):
			return new Forward();
		case ("Backward"):
			return new Back();
		case ("Left"):
			return new Left();
		case ("Right"):
			return new Right();
		case ("SetHeading"):
			return new SetHeading();
		case ("SetTowards"):
			return new Towards();
		case ("SetPosition"):
			return new SetPosition();
		case ("PenUp"):
			return new PenUp();
		case ("PenDown"):
			return new PenDown();
		case ("ShowTurtle"):
			return new ShowTurtle();
		case ("HideTurtle"):
			return new HideTurtle();
		case ("Home"):
			return new Home();
		case ("ClearScreen"):
			return new ClearScreen();	
		//TurtleQueries
		case ("XCoordinate"):
			return new XCoordinate();
		case ("YCoordinate"):
			return new YCoordinate();
		case ("Heading"):
			return new Heading();
		case ("IsPenDown"):
			return new IsPenDown();
		case ("IsShowing"):
			return new IsShowing();
		//MathOperations
		case ("Sum"):
			return new Sum();
		case ("Difference"):
			return new Difference();
		case ("Product"):
			return new Product();
		case ("Quotient"):
			return new Quotient();
		case ("Remainder"):
			return new Remainder();
		case ("Minus"):
			return new Minus();
		case ("Random"):
			return new SlogoRandom();
		case ("Sine"):
			return new Sine();
		case ("Cosine"):
			return new Cosine();
		case ("Tangent"):
			return new Tangent();
		case ("ArcTangent"):
			return new ArcTangent();
		case ("NaturalLog"):
			return new NaturalLog();
		case ("Power"):
			return new Power();
		case ("Pi"):
			return new Pi();
		//BooleanOperations
		case ("LessThan"):
			return new LessThan();
		case ("GreaterThan"):
			return new GreaterThan();
		case ("Equal"):
			return new Equal();
		case ("NotEqual"):
			return new NotEqual();
		case ("And"):
			return new And();
		case ("Or"):
			return new Or();
		case ("Not"):
			return new Not();
		//Variables, Control Structures, and User-Defined Commands
		case ("MakeVariable"):
			return new MakeVariable();
		case ("Repeat"):
			return new Repeat();
		case ("DoTimes"):
			return new DoTimes();
		case ("For"):
			return new For();
		case ("If"):
			return new If();
		case ("IfElse"):
			return new IfElse();
		case ("MakeUserInstruction"):
			return new MakeUserInstruction();
		case ("Command"):
			return myUserCommands.get(expression);
		//Multiple Turtle Commands
		case ("ID"):
			return new ID();
		case ("Turtles"):
			return new Turtles();
		case ("Tell"):
			return new Tell();
		case ("Ask"):
			return new Ask();
		case ("AskWith"):
			return new AskWith();
		//Display Commands
		case ("SetBackground"):
			return new SetBackground();
		case ("SetPenColor"):
			return new SetPenColor();
		case ("SetPenSize"):
			return new SetPenSize();
		case ("SetShape"):
			return new SetShape();
		case ("SetPalette"):
			return new SetPalette();
		case ("GetPenColor"):
			return new GetPenColor();
		case ("GetShape"):
			return new GetShape();
		case ("Stamp"):
			return new Stamp();
		case ("ClearStamps"):
			return new ClearStamps();
		default:
			return null;
		}
	}
}
