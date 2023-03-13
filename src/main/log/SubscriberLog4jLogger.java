package log;

import dirEncryption.AsyncDirectoryProcessor;
import handler.Register;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.concurrent.locks.ReentrantLock;

public abstract class SubscriberLog4jLogger {

    private final Logger logger;
    protected EncryptionLogEventArgs data;

    public SubscriberLog4jLogger(final Class<?> clazz, String fileName) {
        logger = LogManager.getLogger(clazz);
        String algoName = clazz.getName().substring(clazz.getName().lastIndexOf(".") + 1);
        data = new EncryptionLogEventArgs(fileName, algoName);
    }

    /**
     * build good message depend on the param
     */
    public abstract void eventAction();

    /**
     * write the message to the logger.
     *
     * @param action decryption or encryption??
     */
    protected void writeInfoToLog(String action) {
        String structure = String.format("The %s with algorithm %s", action, data.getAlgo());

        if (data.getStartTime() != 0) {
            structure += " took " + data.getTimeTook() + " milliseconds";
        } else {
            data.setStartTime();
            structure += "is starting";
        }

        ReentrantLock lock = AsyncDirectoryProcessor.getLOCK();
        lock.lock();
        logger.info(structure);
        lock.unlock();
    }

    public void subscribe(Register register) {
        register.eventLogRegister(this);
    }
}
