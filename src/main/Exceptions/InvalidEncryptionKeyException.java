package Exceptions;

public class InvalidEncryptionKeyException extends NumberFormatException{
    public InvalidEncryptionKeyException(String s) {
        super(s);
    }
}
