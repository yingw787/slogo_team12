package view;

import java.util.Optional;
import java.util.function.BiConsumer;
import engine.Controller;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;


public class VarValueListView extends Clickable<ListView> {
    private Controller myController;
    private ObservableList<String> myVariableValues;
    private ObservableList<String> myVariableNames;
    private ListView variableValues;
    private GUI gui;

    public VarValueListView (Controller myController,
                             ObservableList<String> myVariableValues, ObservableList<String> variableNames,
                             GUI gui) {
        this.myController = myController;
        this.myVariableValues = myVariableValues;
        this.myVariableNames = variableNames;
        this.gui = gui;
        variableValues = new ListView();
        variableValues.setItems(myVariableValues);
        variableValues
                .setOnMouseClicked(e -> launchVariablePopUp("Change variable value", variableValues,
                                                            (a, b) -> onVariableValueChange(a, b)));
    }

    private void launchVariablePopUp (String displayMessage,
                                      ListView variableBox,
                                      BiConsumer<String, String> changeVariableFunc) {
        int selectedIndex = variableBox.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            String originalValue = myVariableNames.get(selectedIndex);
            TextInputDialog variablePopup = new TextInputDialog();
            variablePopup.setTitle("Variable Definition");
            variablePopup.setHeaderText("Modify Variable");
            variablePopup.setContentText(displayMessage);
            Optional<String> input = variablePopup.showAndWait();
            changeVariableFunc.accept(originalValue, input.get() == null ? "" : input.get());
            gui.updateVariablesMap();
        }
    }

    private void onVariableValueChange (String key, String newValue) {
        if (!newValue.equals("")) {
            myController.changeVariableValue(key, newValue);
        }
    }

    @Override
    ListView getClickable () {
        return variableValues;
    }

}
