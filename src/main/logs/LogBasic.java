package logs;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class LogBasic implements ILog {
    private final Logger logger;

    public LogBasic(final Class<?> clazz) {
        logger = LogManager.getLogger(clazz);
    }

    @Override
    public void writeMessage(String message) {
        logger.info(message);
    }
}
