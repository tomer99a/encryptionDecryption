package logs;

public class LogEnd extends LogDecorator {
    public LogEnd(ILog logMessage) {
        super(logMessage);
    }

    @Override
    public void writeMessage(String message) {
        String messageToAdd = message.equals("") ? "E" : " e";
        messageToAdd += "nd";
        super.writeMessage(message + messageToAdd);
    }

    public void writeMessage() {
        this.writeMessage("");
    }
}
