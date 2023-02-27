package exceptions;

import java.io.IOException;

public class invalidPathException extends IOException {
    public invalidPathException() {
        super("The path given is invalid");
    }
}
