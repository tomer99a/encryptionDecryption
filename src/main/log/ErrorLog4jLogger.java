package log;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ErrorLog4jLogger {
    public static void writeErrorToLog(Class<?> clazz, String message) {
        Logger logger = LogManager.getLogger(clazz);
        logger.error(message);
    }
}
