package pckg;

public class DividingByZeroException extends Exception {
    public DividingByZeroException() { }

    public DividingByZeroException (String message){
        super(message);
    }
}
