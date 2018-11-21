package pckg;

public class WrongValueTypeException extends Exception {
    String columnName;
    int rowNumber;

    public WrongValueTypeException() {}
    public WrongValueTypeException (String a) {super(a);}

}
