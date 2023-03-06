package logs;

public class LogStart extends LogDecorator {
    public LogStart(ILog logMessage) {
        super(logMessage);
    }

    @Override
    public void writeMessage(String message) {
        String messageToAdd = message.equals("") ? "S" : " s";
        messageToAdd += "tart";
        super.writeMessage(message + messageToAdd);
    }

    public void writeMessage() {
        this.writeMessage("");
    }
}