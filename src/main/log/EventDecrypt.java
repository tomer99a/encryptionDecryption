package log;

public class EventDecrypt extends EventAbstract {
    public EventDecrypt(Class<?> clazz) {
        super(clazz);
    }

    @Override
    void writeMessage(boolean startStatus, boolean single) {
        String message = startStatus ? "Start " : "End ";
        if (!single) {
            message = "All the files " + message.toLowerCase();
        }
        writeMessage(message + "decrypt");
    }
}
