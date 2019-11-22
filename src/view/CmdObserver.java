package view;

/**
 * Part of a simplified observer design pattern.
 * Represents objects which are observers of the command pane, which the command pane can signal whenever its state is updated.
 *
 * @author Goh Khian Wei
 */
public interface CmdObserver {
    /**
     * Action that occurs whenever the command pane signals that its state has been meaningfully updated (i.e. A command has been completed by the user.)
     *
     * @param cmd Verbatim command that the user has input
     */
    void updateCmd(String cmd);
}
