package view;

import java.util.Optional;
import java.util.function.BiConsumer;

import engine.IController;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;


public class VarNameListView extends Clickable<ListView> {
    private IController myController;
    private ObservableList<String> myVariableNames;
    private ListView variableNames;
    private GUI gui;

    public VarNameListView (IController myController,
                            ObservableList<String> myVariableNames,
                            GUI gui) {
        this.myController = myController;
        this.myVariableNames = myVariableNames;
        this.gui = gui;
        variableNames = new ListView();
        variableNames.setItems(myVariableNames);
        variableNames
                .setOnMouseClicked(e -> launchVariablePopUp("Change variable name", variableNames,
                                                            (a, b) -> onVariableNameChange(a, b)));
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

    private void onVariableNameChange (String oldName, String newName) {
        if (!newName.equals("")) {
            myController.changeVariableName(oldName, newName);
        }
    }

    @Override
    ListView getClickable () {
        return variableNames;
    }

}
