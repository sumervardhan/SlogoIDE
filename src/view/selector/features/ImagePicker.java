package view.selector.features;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * This class is responsible for holding all the possible turtle shapes. This class also translates indices inputted by the user into images so that the front end can change the
 * turtle's shape.
 *
 * @author Ben Lawrence
 */
public class ImagePicker {
    public static final String SHAPES_FILE_NAME = "shapes";
    private static final int DEFUALT_INDEX = 0;
    private static final String SHAPES_DIRECTORY = "/src/view/selector/features/resources/shapes/";

    private ResourceBundle resources;
    private Map<String, Image> shapes;

    /**
     * Constructor for the ImagePicker class. This constructor does not take in any arguments. All private variables are initialized and the turtle images are determined from an
     * internal properties file. In the event that a given index has a corrupt or absent image, the index is given a null value for the turtle shape.
     */
    public ImagePicker() {
        shapes = new HashMap<>();
        resources = ResourceBundle.getBundle(FeatureConstants.RESOURCE_PATH + SHAPES_FILE_NAME);
        resources.getKeys().asIterator().forEachRemaining(key -> {
            try {
                shapes.put(key, new Image(new FileInputStream(System.getProperty("user.dir") + SHAPES_DIRECTORY + resources.getString(key))));
            } catch (Exception e) {
                shapes.put(key, null);
            }
        });
    }

    /**
     * Takes in a turtle index and returns the image object associated with that index.
     *
     * @param index - Integer index of new shape for the turtle
     * @return - Returns the new shape of the turtle as an image.
     */
    public Image getImageFromIndex(int index) {
        try {
            return shapes.get("" + index);
        } catch (Exception e) {
            return shapes.get("" + DEFUALT_INDEX);
        }
    }
}


