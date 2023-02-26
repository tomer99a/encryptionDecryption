package Exceptions;

public class invalidPathException extends IllegalArgumentException {
    public invalidPathException() {
        super("The path given is invalid");
    }
}
