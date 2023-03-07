package log;

public class EventEncrypt extends EventAbstract {
    public EventEncrypt(Class<?> clazz) {
        super(clazz);
    }

    @Override
    void writeMessage(boolean startStatus) {
        String message = startStatus ? "Start " : "End ";
        writeMessage(message + "encrypt");
    }
}
