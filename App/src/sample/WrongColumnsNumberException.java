package sample;

import javafx.scene.control.Alert;

public class WrongColumnsNumberException extends Exception {
    public WrongColumnsNumberException (){
        super();
    }
    public void invalidNumberAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText("Nieodpowiednia ilość podanych kolumn");
        alert.setContentText("Proszę wpisać poprawną ilość kolumn");
        alert.showAndWait();
    }

}
