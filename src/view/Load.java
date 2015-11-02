package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;


public class Load implements Clickable<Button> {
    private Button load;

    public Load (TextArea t) {
        load = new Button("Load");
        load.setOnAction(e -> this.loadMethod(t));
    }

    private void loadMethod (TextArea t) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Logo Program To Load");
        fileChooser.setInitialDirectory(new File("src/userPrograms/"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            t.setText(readFile(file));
        }
    }

    private String readFile (File file) {
        StringBuilder stringBuffer = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {

            bufferedReader = new BufferedReader(new FileReader(file));

            String text;
            while ((text = bufferedReader.readLine()) != null) {
                stringBuffer.append(text);
            }

        }
        catch (FileNotFoundException ex) {

        }
        catch (IOException ex) {

        }
        finally {
            try {
                bufferedReader.close();
            }
            catch (IOException ex) {
            }
        }

        return stringBuffer.toString();
    }

    @Override
    public Button getClickable () {
        return load;
    }
}
