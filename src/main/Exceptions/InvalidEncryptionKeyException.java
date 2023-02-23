package Exceptions;

public class InvalidEncryptionKeyException extends Exception{
    public InvalidEncryptionKeyException() {
        super("The key file doesn't contain number");
    }
}
