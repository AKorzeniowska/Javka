package sample;

import javafx.scene.control.Alert;

public class NoChosenFileException extends Exception {
    public NoChosenFileException (){
        super();
    }

    public void alert (){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText("Nie wybrano pliku");
        alert.setContentText("Proszę wybrać plik");
        alert.showAndWait();
    }
}
