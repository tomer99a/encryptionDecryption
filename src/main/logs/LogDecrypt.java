package logs;

public class LogDecrypt extends LogDecorator {
    public LogDecrypt(ILog logMessage) {
        super(logMessage);
    }

    @Override
    public void writeMessage(String message) {
        super.writeMessage(message + "decryption");
    }
}
