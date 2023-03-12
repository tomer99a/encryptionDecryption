package log;

public class DecryptionLog4jLogger extends SubscriberLog4jLogger {
    public DecryptionLog4jLogger(Class<?> clazz) {
        super(clazz);
    }

    @Override
    public void eventAction(boolean startStatus, boolean single) {
        String message = startStatus ? "Start " : "End ";
        if (!single) {
            message = "All the files " + message.toLowerCase();
        }
        writeInfoToLog(message + "decrypt");
    }
}
