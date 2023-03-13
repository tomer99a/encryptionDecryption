package log;

public class DecryptionLog4jLogger extends SubscriberLog4jLogger {
    public DecryptionLog4jLogger(Class<?> clazz, String fileName) {
        super(clazz, fileName);
    }

    @Override
    public void eventAction() {
        writeInfoToLog("The decryption ");
    }
}
