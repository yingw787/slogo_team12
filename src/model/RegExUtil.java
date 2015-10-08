package model;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class RegExUtil {
	public static final String REG_EX_SYNTAX_FILE = "resources/languages/Syntax";
	
	private List<Entry<String, Pattern>> myPatterns;
	
	public RegExUtil() {
		myPatterns = makePatterns(REG_EX_SYNTAX_FILE);
	}
	
	public String matchPattern(String expression) {
		for (Entry<String, Pattern> p : myPatterns) {
            if (match(expression, p.getValue())) {
            	return p.getKey();
            }
        }
		return null;
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
