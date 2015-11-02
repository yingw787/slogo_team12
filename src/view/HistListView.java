package view;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;


public class HistListView implements Clickable<ListView> {
    private ListView histListView;

    public HistListView (TextArea t, ObservableList<String> myHistList) {
        histListView = new ListView();
        histListView.setItems(myHistList);
        histListView.setOnMouseClicked(e -> this.onClicked(t));
    }

    private void onClicked (TextArea t) {
        if (!(histListView.getSelectionModel().getSelectedItem() == null)) {
            t.setText((histListView.getSelectionModel().getSelectedItem().toString()));
        }
    }

    @Override
    public ListView getClickable () {
        return histListView;
    }
}
