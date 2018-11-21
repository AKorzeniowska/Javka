package pckg;

public class DividingByZeroException extends Exception {
    int rowNumber;

    public DividingByZeroException() { }

    public DividingByZeroException (String message){
        super(message);
    }
}
