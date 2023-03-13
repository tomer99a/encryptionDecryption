package log;

public class EncryptionLog4jLogger extends SubscriberLog4jLogger {
    public EncryptionLog4jLogger(Class<?> clazz, String fileName) {
        super(clazz, fileName);
    }

    /**
     * build good message depend on the param
     *
     * @param single say if the event occur on single thing or all of them
     */
    @Override
    public void eventAction(boolean single) {
        String message = single ? "The encryption " : "All the files encrypt ";
        if (data.getStartTime() == 0) {
            data.setStartTime(System.currentTimeMillis());
        }
        writeInfoToLog(message);
    }
}
