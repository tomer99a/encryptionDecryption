package logs;

public class LogDecrypt extends LogDecorator  {
    public LogDecrypt(ILog logMessage) {
        super(logMessage);
    }

    @Override
    public void writeMessage(String message) {
        String messageToAdd = message.equals("") ? "D" : " d";
        messageToAdd += "ecryption";
        super.writeMessage(message + messageToAdd);
    }
}
