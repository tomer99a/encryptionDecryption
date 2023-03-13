package log;

public class EncryptionLog4jLogger extends SubscriberLog4jLogger {
    public EncryptionLog4jLogger(Class<?> clazz, String fileName) {
        super(clazz, fileName);
    }

    /**
     * build good message depend on the param
     */
    @Override
    public void eventAction() {
        writeInfoToLog("The encryption ");
    }
}
