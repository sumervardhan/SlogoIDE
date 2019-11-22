package view.selector.features;

import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * This class allows for the choosing of a specific type of file to read or write.
 *
 * @author Ben Lawrence
 */
public class FileSelector {
    private FileChooser fileChooser;
    private Button selectionButton;
    private Stage fileStage;

    /**
     * Constructor for the FileSelector class. The selector holds its own stage for choosing files and instantiates all variables.
     *
     * @param fileDescription - Description of the file type that the user is available to choose.
     * @param extension - File type extension that the user is allowed to choose. Must contain the star character (i.e., *.txt, *.slogo, etc.)
     */
    public FileSelector(String fileDescription, String extension) {
        fileStage = new Stage();

        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(fileDescription, extension));

        selectionButton = new Button("Choose file");
        selectionButton.setOnAction(event -> chooseFile());
    }

    /**
     * Returns the selected file as a raw file type.
     *
     * @return - File object representing the file chosen by the user.
     */
    public File getFile() {
        return chooseFile();
    }

    /**
     * Before returning to the user, converts the contents of the file to a string for easy manipulation.
     *
     * @return - Returns string value with contents of file.
     */
    public String getFileAsString() {
        return fileToString(chooseFile());
    }

    /**
     * Takes in a string value and saves that string value as a file.
     *
     * @param text - String text representing file content. A file name does not need to be passed in as the user types this into the dialog box that pops up.
     */
    public void saveStringAsFile(String text) {
        File file = fileChooser.showSaveDialog(fileStage);
        if (file != null) {
            saveFile(text, file);
        }
    }

    private File chooseFile() {
        return fileChooser.showOpenDialog(fileStage);
    }

    private String fileToString(File file) {
        String command = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            while ((line = br.readLine()) != null) {
                command += (line + "\n");
            }

            return command;
        } catch (Exception e) {
            return "";
        }
    }

    // credit: http://java-buddy.blogspot.com/2012/05/save-file-with-javafx-filechooser.html
    private void saveFile(String text, File file) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(text);
            fileWriter.close();
        } catch (Exception e) {

        }
    }
}
