package Exceptions;

public class InvalidEncryptionKeyException extends Exception{
    public InvalidEncryptionKeyException(String message) {
        super(message);
    }
}
