package exceptions;

import java.io.IOException;

public class invalidPathException extends IOException {
    public invalidPathException(String str) {
        super(str);
    }
}
