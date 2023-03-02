package logs;

public class LogEncrypt extends LogDecorator {
    public LogEncrypt(ILog logMessage) {
        super(logMessage);
    }

    @Override
    public void writeMessage(String message) {
        super.writeMessage(message + "encryption");
    }
}
