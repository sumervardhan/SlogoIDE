package view.mainpane;

/**
 * Interface for a Selector Pane observer, that should be updated whenever the selector pane changes state.
 * Part of a simplified observer design pattern.
 *
 * @author Goh Khian Wei
 */
public interface SelectorPaneObserver {

    /**
     * Updates observers whenever a pane is marked as 'hidden' in the selector pane
     *
     * @param paneName name of pane that has been hidden
     */
    void updateHidePane(String paneName);

    /**
     * Updates observers whenever a pane is marked as 'shown' in the selector pane
     *
     * @param paneName name of pane that has been shown
     */
    void updateShowPane(String paneName);
}
