package sample;

import javafx.scene.control.Alert;

public class WrongColumnsNamesException extends Exception {
    public WrongColumnsNamesException(){
        super();
    }

    public void invalidNameAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText("Nieodpowiednie nazwy kolumn");
        alert.setContentText("Wybrany plik nie zawiera kolumn o wpisanych nazwach - proszę wpisać poprawne");
        alert.showAndWait();
    }
}
