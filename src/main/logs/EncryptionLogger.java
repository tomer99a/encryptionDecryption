package logs;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

public class EncryptionLogger {
    private static final Logger logger = LogManager.getLogger(EncryptionLogger.class);

    public static void writeToLog(String message){
        logger.info(message);
    }
}
