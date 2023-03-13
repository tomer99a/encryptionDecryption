package log;

public class DecryptionLog4jLogger extends SubscriberLog4jLogger {
    public DecryptionLog4jLogger(Class<?> clazz, String fileName) {
        super(clazz, fileName);
    }

    @Override
    public void eventAction(boolean single) {
        String message = single ? "The encryption " : "All the files encrypt ";
        if (data.getStartTime() == 0) {
            data.setStartTime(System.currentTimeMillis());
        }
        writeInfoToLog(message);
    }
}
