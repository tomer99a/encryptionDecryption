package logs;

public class LogEnd extends LogDecorator {
    public LogEnd(ILog logMessage) {
        super(logMessage);
    }

    @Override
    public void writeMessage(String message) {
        super.writeMessage(message + "End ");
    }

    public void writeMessage() {
        this.writeMessage("");
    }
}
