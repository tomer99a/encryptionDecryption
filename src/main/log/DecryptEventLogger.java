package log;

public class DecryptEventLogger extends Subscriber {
    public DecryptEventLogger(Class<?> clazz) {
        super(clazz);
    }

    @Override
    public void eventAction(boolean startStatus, boolean single) {
        String message = startStatus ? "Start " : "End ";
        if (!single) {
            message = "All the files " + message.toLowerCase();
        }
        writeMessageToLog(message + "decrypt");
    }
}
