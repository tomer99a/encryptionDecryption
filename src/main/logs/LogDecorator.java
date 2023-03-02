package logs;

public abstract class LogDecorator implements ILog {
    private final ILog logMessage;

    public LogDecorator(ILog logMessage) {this.logMessage = logMessage;}

    @Override
    public void writeMessage(String message) {
        logMessage.writeMessage(message);
    }
}
