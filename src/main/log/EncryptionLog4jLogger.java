package log;

public class EncryptionLog4jLogger extends SubscriberLog4jLogger {
    public EncryptionLog4jLogger(Class<?> clazz) {
        super(clazz);
    }

    /**
     * build good message depend on the param
     * @param startStatus say if the event start (true) or end (false)
     * @param single say if the event occur on single thing or all of them
     */
    @Override
    public void eventAction(boolean startStatus, boolean single) {
        String message = startStatus ? "Start " : "End ";
        if (!single) {
            message = "All the files " + message.toLowerCase();
        }
        writeInfoToLog(message + "encrypt");
    }
}
