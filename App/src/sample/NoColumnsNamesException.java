package sample;

import javafx.scene.control.Alert;

public class NoColumnsNamesException extends Exception {
    public NoColumnsNamesException(){
        super();
    }
    public void noInputAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText("Nie wpisano nazw kolumn");
        alert.setContentText("Proszę wpisać nazwy kolumn");
        alert.showAndWait();
    }
}
