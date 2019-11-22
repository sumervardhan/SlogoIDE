package view;

/**
 * Part of a simplified observer design pattern.
 * Represents objects which should be signalled for further updating whenever a language change is executed.
 *
 * @author Goh Khian Wei
 */
public interface LanguageChangeObserver {

    /**
     * Method that executes whenever a language change occurs, for object to handle implications of a change in system language.
     *
     * @param newLanguage New language that has been changed to
     * @param oldLanguage Old/ current language that was in effect before the change
     */
    void updateLanguage(String newLanguage, String oldLanguage);

}
