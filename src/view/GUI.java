package view;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import com.sun.istack.internal.logging.Logger;
import engine.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;


public class GUI {

    private GUIfactory myFactory;

    public static final String DEFAULT_RESOURCE_PACKAGE = "resources.languages/";
    private ResourceBundle myResources;
    private Controller myController;
    private BorderPane root;
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private ObservableList<String> myHistList;
    private ObservableList<String> myColorsList;
    private Pane canvasBox;
    final FileChooser fileChooser;
    private Turtle turtle;
    // consider adding a public method called get myHistList, that returns immutable histList

    public GUI (Controller controller, String language) {
        turtle = new Turtle(SCREEN_WIDTH, SCREEN_HEIGHT);
        myHistList = FXCollections.observableArrayList();
        myColorsList = FXCollections.observableArrayList();
        // myHistList.add("History");
        fileChooser = initFileChooser();
        myController = controller;

        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
        myFactory = new GUIfactory(myResources, myController);

        root = myFactory.makeBorderPane();

        HBox optionsBox = myFactory.makeHBox();
        root.setTop(optionsBox);

        canvasBox = myFactory.makePane();

        /*
         * Background canvasBG = new Background(???);
         * canvasBox.setBackground(canvasBG);
         */

        // Make VARIABLE CHANGEABLE BY BACKEND
        // canvasBox.getChildren().add(new Rectangle(50,50));

        // not sure if this actually works
        canvasBox.setPrefSize(SCREEN_WIDTH * (3 / 4), SCREEN_HEIGHT * (3 / 4));
        root.setCenter(canvasBox);

        ComboBox paneColorSelect = myFactory.makeComboBox();
        initPaneColorSelect(paneColorSelect);
        optionsBox.getChildren().add(paneColorSelect);

        ComboBox penColorSelect = myFactory.makeComboBox();
        initPenColorSelect(penColorSelect);
        optionsBox.getChildren().add(penColorSelect);

        HBox commandAndVarBox = myFactory.makeHBox();
        commandAndVarBox.setMaxHeight(SCREEN_HEIGHT / 5);
        root.setBottom(commandAndVarBox);

        TextArea t = myFactory.makeTextArea();
        commandAndVarBox.getChildren().add(t);

        VBox historyBox = myFactory.makeVBox();
        historyBox.setMaxWidth(SCREEN_WIDTH / 4);
        historyBox.getChildren().add(new Text("History"));
        root.setRight(historyBox);

        ListView myHistListView = myFactory.makeClickableList(myHistList);
        historyBox.getChildren().add(myHistListView);
        historyBox.getChildren().add(myFactory.makeButton("reset", e -> myController.reset()));

        // this should be moved to a more appropriate place
        myHistListView.setOnMouseClicked(e -> {
            // lambda, checks if selected is not null, if its not, populate the command box with the
            // selected history
            if (!(myHistListView.getSelectionModel().getSelectedItem() == null)) {
                t.setText((myHistListView.getSelectionModel().getSelectedItem().toString()));
            }

        });

        // makeButton: setOnAction(e-> myController.submit(t.getText()));
        commandAndVarBox.getChildren()
                .add(myFactory.makeButton("Go", e -> myController.submit(t.getText())));

        VBox variablesBox = myFactory.makeVBox();
        variablesBox.getChildren().add(new Text("Variables"));

        ///////// The list of variables needs to come from backend. updated by backend.
        /// Rather than be a list of strings, list of some type of object (variables object?) that
        ///////// can
        // be changed on click. To change on click see lambda function for history command box.
        ObservableList<String> listOfVariables = FXCollections.observableArrayList();
        listOfVariables.add("Variable 1");

        variablesBox.getChildren().add(myFactory.makeClickableList(listOfVariables));
        commandAndVarBox.getChildren().add(variablesBox);

        optionsBox.getChildren()
                .add(myFactory.makeButton("pickImageButton", e -> this.pickImage()));
        // make a root, etc, layout everything with the GUIfactory
        Image image = new Image(getClass().getClassLoader().getResourceAsStream("turtle.gif"));
        turtle.setTurtleImage(image);
        canvasBox.getChildren().add(turtle.getTurtleImage());

    }

    private FileChooser initFileChooser () {
        FileChooser f = new FileChooser();
        f.setTitle("Open Image File");
        f.getExtensionFilters().add(new ExtensionFilter("Image File", "*.png", "*.jpg", "*.gif"));
        return f;
    }

    private void initPenColorSelect (ComboBox penColorSelect) {
        penColorSelect.setItems(myColorsList);
        penColorSelect.setOnAction(e -> this
                .changePenColor(penColorSelect.getSelectionModel().getSelectedItem().toString()));
    }

    private void initPaneColorSelect (ComboBox paneColorSelect) {
        initColors(myColorsList);
        paneColorSelect.setItems(myColorsList);
        paneColorSelect.setOnAction(e -> this
                .changePaneColor(paneColorSelect.getSelectionModel().getSelectedItem().toString()));
    }

    private void initColors (ObservableList<String> myColorsList) {
        myColorsList.add("blue");
        myColorsList.add("red");
        myColorsList.add("purple");
        myColorsList.add("orange");
        myColorsList.add("green");
    }

    private void changePenColor (String penColor) {
        try {
            System.out.println(penColor);
            turtle.setPenColor(Color.valueOf(penColor));
        }
        catch (Exception e) {
            // invalid color
        }
    }

    private void changePaneColor (String canvasColor) {
        try {
            canvasBox.setStyle("-fx-background-color: " + canvasColor + ";");
        }
        catch (Exception e) {
            // invalid color
        }
    }

    public void setAndShowScene (Stage primaryStage) {
        // WIDTH AND HEIGHT, MORE DETAILS FOR SCENE
        primaryStage.setScene(new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT));
        primaryStage.show();

    }

    public void addToHistory (String stringFromGUI) {

        if (stringFromGUI.trim().length() > 0) {
            // checks that its not just all whitespace
            myHistList.add(stringFromGUI);
        }
        // this will become private when we set up an observer relationship
        // currently controller directly calls this in submit

    }

    private void pickImage () {
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            Image image1 = new Image(file.toURI().toString());
            canvasBox.getChildren().remove(turtle.getTurtleImage());
            turtle.setTurtleImage(image1);
            canvasBox.getChildren().add(turtle.getTurtleImage());
        }
    }

    public void drawLine () {
        turtle.setCurrentXPos(turtle.getCurrentXPos() + 20);
        turtle.setCurrentYPos(turtle.getCurrentYPos() + 20);
        Line line = new Line();
        line.setStartX(turtle.getPastXPos());
        line.setStartY(turtle.getPastYPos());
        line.setEndX(turtle.getCurrentXPos());
        line.setEndY(turtle.getCurrentYPos());
        line.setStroke(turtle.getPenColor());
        canvasBox.getChildren().add(line);

    }

}
