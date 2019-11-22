package controller;

import controller.exceptions.InvalidSyntaxException;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Pattern;


/**
 * Simple parser based on regular expressions that matches program strings to
 * kinds of language features.
 *
 * @author Robert C. Duvall
 */
public class ProgramParser {

    public static final String SYNTAX = "Syntax";
    // where to find resources specifically for this class
    public static final String RESOURCES_PACKAGE = "/resources/languages/";
    public static final String NUM_ARGUMENTS = "numArguments";
    // "types" and the regular expression patterns that recognize those types
    // note, it is a list because order matters (some patterns may be more generic)
    private List<Entry<String, Pattern>> mySymbols;

    public ProgramParser() {
        mySymbols = new ArrayList<>();
        addPatterns(SYNTAX);
    }

    public ProgramParser(String language) {
        mySymbols = new ArrayList<>();
        addPatterns(language);
        addPatterns(SYNTAX);
    }

    /**
     * Adds the given resource file to this language's recognized types
     */
    public void addPatterns(String syntax) {
        ResourceBundle resources = ResourceBundle.getBundle(RESOURCES_PACKAGE + syntax);
        for (String key : Collections.list(resources.getKeys())) {
            String regex = resources.getString(key);
            mySymbols.add(new SimpleEntry<>(key,
                    // THIS IS THE IMPORTANT LINE
                    Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
        }
    }

    /**
     * Returns language's type associated with the given text if one exists
     */
    public String getSymbol(String text) {
        final String ERROR = "NO MATCH";
        for (Entry<String, Pattern> e : mySymbols) {
            if (match(text, e.getValue())) {
                return e.getKey();
            }
        }
        throw new InvalidSyntaxException("Not in Command List");
    }

    /**
     * Returns true if the given text matches the given regular expression pattern
     */
    private boolean match(String text, Pattern regex) {
        // THIS IS THE IMPORTANT LINE
        return regex.matcher(text).matches();
    }
}
