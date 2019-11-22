package view.commandline;

/**
 * Contains all constants required by the command line pane and all subclasses.
 * Private constructor as no objects of this class should ever have to be instantiated.
 *
 * @author Goh Khian Wei
 */
public class CmdLineConstants {
    public static final String CMD_CSS_FILE = "/resources/stylesheets/cmdlineStylesheet.css";
    public static final String CSS_TAG_CMD_MAIN = "cmd-main";
    public static final String CSS_TAG_CMD_TEXT_AREA = "cmd-text-area";
    public static final String CSS_TAG_CMD_BUTTON_AREA = "cmd-button-area";
    public static final String CSS_TAG_CMD_BUTTON = "cmd-button";
    public static final String RESOURCE_PATH = "/view/commandline/buttons";

    public static final String ERROR_BUTTON_HANDLER = "ERROR EXECUTING BUTTON HANDLER METHOD";

    private CmdLineConstants() {
        //Do nothing
    }
}
