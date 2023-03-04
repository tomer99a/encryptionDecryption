package logs;

public class LogEncrypt extends LogDecorator {
    public LogEncrypt(ILog logMessage) {
        super(logMessage);
    }

    @Override
    public void writeMessage(String message) {
        String messageToAdd = message.equals("") ? "E" : " e";
        messageToAdd += "ncryption";
        super.writeMessage(message + messageToAdd);
    }
}
