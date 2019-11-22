package workspaces;

import javafx.scene.Node;
import view.View;

import java.util.Properties;

/**
 * @author Ben lawrence
 */
public interface WorkspaceSetting {

    void setProperty(View view);

    Node getNode();

    void saveProperties(Properties properties);

    void setSettingValue(String val);
}
