package log;

public class EventDecrypt extends EventAbstract {
    public EventDecrypt(Class<?> clazz) {
        super(clazz);
    }

    @Override
    void writeMessage(boolean startStatus) {
        String message = startStatus ? "Start " : "End ";
        writeMessage(message + "decrypt");
    }
}
