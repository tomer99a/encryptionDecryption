package log;

public class EventEncrypt extends EventAbstract {
    public EventEncrypt(Class<?> clazz) {
        super(clazz);
    }

    /**
     * build good message depend on the param
     * @param startStatus say if the event start (true) or end (false)
     * @param single say if the event occur on single thing or all of them
     */
    @Override
    void writeMessage(boolean startStatus, boolean single) {
        String message = startStatus ? "Start " : "End ";
        if (!single) {
            message = "All the files " + message.toLowerCase();
        }
        writeMessage(message + "encrypt");
    }
}
