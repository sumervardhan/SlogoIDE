package view.turtlearena;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the TurtleArena, contains the scene and the turtles within that the user sees, allows for visual properties to change through defined methods.
 *
 * @author Goh Khian Wei
 */
public class BasicTurtleArena implements TurtleArena {
    private Image defaultImage;
    private Color defaultPenColor;
    private double defaultPenWidth;

    private Map<Integer, Double> turtleCurX;
    private Map<Integer, Double> turtleCurY;
    private Map<Integer, Turtle> turtleControl;
    private Map<Node, Integer> lines;
    private Pane arena;
    private double xOffset;
    private double yOffset;

    public BasicTurtleArena(Image defaultImage, Color defaultPenColor, double defaultPenWidth) {
        this.defaultImage = defaultImage;
        this.defaultPenColor = defaultPenColor;
        this.defaultPenWidth = defaultPenWidth;
        lines = new HashMap<>();
        turtleCurX = new HashMap<>();
        turtleCurY = new HashMap<>();
        turtleControl = new HashMap<>();
        initArena();
    }

    @Override
    public Node getTurtleArena() {
        return arena;
    }

    @Override
    public void changeBackgroundColor(Color color) {
        arena.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    @Override
    public void changeTurtleImage(int turtleIndex, Image image) {
        turtleControl.get(turtleIndex).setTurtleImage(image);
        moveTurtle(turtleIndex, turtleCurX.get(turtleIndex), turtleCurY.get(turtleIndex));
    }

    @Override
    public void changePenColor(int turtleIndex, Color color) {
        turtleControl.get(turtleIndex).setPenColor(color);
    }

    @Override
    public void changePenThickness(int turtleIndex, double thickness) {
        turtleControl.get(turtleIndex).setPenThickness(thickness);
    }

    @Override
    public void addTurtle(int turtleIndex) {
        turtleControl.put(turtleIndex, new BasicTurtle(defaultImage, defaultPenColor, defaultPenWidth));
        arena.getChildren().add(turtleControl.get(turtleIndex).getTurtle());
        moveTurtle(turtleIndex, 0, 0);
    }

    @Override
    public void hideTurtle(int turtleIndex) {
        turtleControl.get(turtleIndex).hide();
    }

    @Override
    public void showTurtle(int turtleIndex) {
        turtleControl.get(turtleIndex).show();
    }

    @Override
    public void addLine(int turtleIndex, double xPos, double yPos) {
        turtleCurX.put(turtleIndex, xPos);
        turtleCurY.put(turtleIndex, yPos);

        Node newLine = turtleControl.get(turtleIndex).addLine(xPos + xOffset, yPos + yOffset);
        lines.put(newLine, turtleIndex);
        arena.getChildren().add(newLine);
        turtleControl.get(turtleIndex).getTurtle().toFront();
    }

    @Override
    public void moveTurtle(int turtleIndex, double xPos, double yPos) {
        turtleCurX.put(turtleIndex, xPos);
        turtleCurY.put(turtleIndex, yPos);

        turtleControl.get(turtleIndex).move(xOffset + xPos, yOffset + yPos);
    }

    @Override
    public void rotateTurtle(int turtleIndex, double angle) {
        turtleControl.get(turtleIndex).rotate(angle);
    }

    @Override
    public void clearScreen() {
        arena.getChildren().clear();
        lines.clear();

        turtleControl.entrySet().forEach(controller -> {
            arena.getChildren().add(controller.getValue().getTurtle());
            moveTurtle(controller.getKey(), 0, 0);
        });
    }

    public void setDefaultImage(Image image) {
        defaultImage = image;
    }

    public void setDefaultPenColor(Color color) {
        defaultPenColor = color;
    }

    public void setDefaultPenWidth(double width) {
        defaultPenWidth = width;
    }

    private void initArena() {
        arena = new Pane();
        arena.getStylesheets().add(getClass().getResource(ArenaConstants.ARENA_CSS_FILE).toExternalForm());
        arena.getStyleClass().add(ArenaConstants.CSS_TAG_ARENA);
        turtleControl.values().forEach(control -> arena.getChildren().add(control.getTurtle()));

        initArenaDynamicSizeChange();
        initArenaClipping();
    }

    private void initArenaDynamicSizeChange() {
        arena.heightProperty().addListener(e -> sizeChangeHandler());
        arena.widthProperty().addListener(e -> sizeChangeHandler());
    }

    private void initArenaClipping() {
        Rectangle clip = new Rectangle(0, 0, 0, 0);
        clip.widthProperty().bind(arena.widthProperty());
        clip.heightProperty().bind(arena.heightProperty());
        arena.setClip(clip);
    }

    private void sizeChangeHandler() {
        double newXOffset = arena.getBoundsInLocal().getCenterX();
        double newYOffset = arena.getBoundsInLocal().getCenterY();
        turtleControl.values().forEach(control -> control.moveOffset(newXOffset - xOffset, newYOffset - yOffset));
        lines.keySet().forEach(line -> {
            line.relocate(line.getBoundsInParent().getMinX() + newXOffset - xOffset, line.getBoundsInParent().getMinY() + newYOffset - yOffset);
        });
        xOffset = newXOffset;
        yOffset = newYOffset;
    }
}
