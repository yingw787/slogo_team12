package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import commands.UserCommand;
import engine.Controller;

public class BackEndController {
	private static final String PATH = "src/userPrograms/";
	private static final String EXTENSION = ".logo";
	private ParseModel myParser;
	private Controller myController;
	
	public BackEndController(Controller controller) {
		myController = controller;
	}
	
	public void saveProgram(String input, String programTitle) {
		String filePath = PATH + programTitle + EXTENSION;
		try {
			FileWriter writer = new FileWriter(new File(filePath));
			writer.write(input);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Queue<TurtleStatus> generateTurtleCommands(String input, String language) {
		myParser = new ParseModel(input, "resources/languages/" + language);
		List<ExpressionNode> parseModel = myParser.createParseModel();
		Map<String,UserCommand> userCommands = myParser.getUserCommands();
		myParser.printParseModel();
		Translator translator = new Translator(parseModel, userCommands, myController);
		return translator.executeCommands();
	}

}
