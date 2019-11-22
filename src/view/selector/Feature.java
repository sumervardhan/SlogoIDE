package view.selector;

import javafx.scene.Node;

public interface Feature {

    Node getNode();

    void update(Object... args);
}
