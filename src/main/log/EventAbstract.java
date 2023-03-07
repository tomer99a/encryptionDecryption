package log;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public abstract class EventAbstract {

    private final Logger logger;

    public EventAbstract(final Class<?> clazz) {
        logger = LogManager.getLogger(clazz);
    }

    /**
     * build good message depend on the param
     * @param startStatus say if the event start (true) or end (false)
     */
    abstract void writeMessage(boolean startStatus);

    /**
     * write the message to the logger.
     * @param message message to write to the logger
     */
    void writeMessage(String message) {
        logger.info(message);
    }

    public void subscribe(Register register) {
        register.logRegister(this);
    }
}
