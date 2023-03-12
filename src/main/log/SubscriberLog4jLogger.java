package log;

import handler.Register;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public abstract class SubscriberLog4jLogger {

    private final Logger logger;

    public SubscriberLog4jLogger(final Class<?> clazz) {
        logger = LogManager.getLogger(clazz);
    }

    /**
     * build good message depend on the param
     * @param startStatus say if the event start (true) or end (false)
     * @param single say if the event occur on single thing or all of them
     */
    public abstract void eventAction(boolean startStatus, boolean single);

    /**
     * write the message to the logger.
     * @param message message to write to the logger
     */
    protected void writeInfoToLog(String message) {
        logger.info(message);
    }

    public void subscribe(Register register) {
        register.eventLogRegister(this);
    }
}
