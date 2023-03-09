package log;

public class DecryptEventLogger extends EventLoggerAbstract {
    public DecryptEventLogger(Class<?> clazz) {
        super(clazz);
    }

    @Override
    public void writeMessage(boolean startStatus, boolean single) {
        String message = startStatus ? "Start " : "End ";
        if (!single) {
            message = "All the files " + message.toLowerCase();
        }
        writeMessageToLog(message + "decrypt");
    }
}
