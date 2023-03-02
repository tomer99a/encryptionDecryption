package logs;

public class LogStart extends LogDecorator {
    public LogStart(ILog logMessage) {
        super(logMessage);
    }

    @Override
    public void writeMessage(String message) {
        super.writeMessage(message + "Start ");
    }

    public void writeMessage() {
        this.writeMessage("");
    }
}
