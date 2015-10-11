package model;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RegExUtil {
	public static final String REG_EX_SYNTAX_FILE = "resources/languages/Syntax";
	
	private List<Entry<String, Pattern>> myPatterns;
	private List<Entry<String, Pattern>> myTurtleCommandPatterns;
	private List<Entry<String, Pattern>> myBasicSyntaxPatterns;
	
	public RegExUtil(String languageFile) {
		myTurtleCommandPatterns = makePatterns(languageFile);
		myPatterns = new ArrayList<Entry<String, Pattern>>();
		myPatterns.addAll(myTurtleCommandPatterns);
		myPatterns.addAll(makePatterns(REG_EX_SYNTAX_FILE));
	}
	
	public String matchPattern(String expression) {
		for (Entry<String, Pattern> p : myPatterns) {
            if (match(expression, p.getValue())) {
            	return p.getKey();
            }
        }
		return null;
	}
	
	public List<String> getTurtleCommandKeys() {
		return myTurtleCommandPatterns.stream()
				.map(entry -> entry.getKey())
				.collect(Collectors.toList());
	}
	
	//from Professor Duvall's code
	private boolean match(String input, Pattern regEx) {
		return regEx.matcher(input).matches();
	}
	
	//from Professor Duvall's code
    private List<Entry<String, Pattern>> makePatterns (String syntax) {
        ResourceBundle resources = ResourceBundle.getBundle(syntax);
        List<Entry<String, Pattern>> patterns = new ArrayList<>();
        Enumeration<String> iter = resources.getKeys();
        while (iter.hasMoreElements()) {
            String key = iter.nextElement();
            String regex = resources.getString(key);
            patterns.add(new SimpleEntry<String, Pattern>(key,
            		Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
        }
        return patterns;
    }
}
