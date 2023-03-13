package log;

import handler.Register;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public abstract class SubscriberLog4jLogger {

    private final Logger logger;
    protected EncryptionLogEventArgs data;

    public SubscriberLog4jLogger(final Class<?> clazz, String fileName) {
        logger = LogManager.getLogger(clazz);
        String algoName = clazz.getName().substring(clazz.getName().lastIndexOf(".")+1);
        data = new EncryptionLogEventArgs(fileName, algoName);
    }

    /**
     * build good message depend on the param
     *
     * @param single      say if the event occur on single thing or all of them
     */
    public abstract void eventAction(boolean single);

    /**
     * write the message to the logger.
     *
     * @param startMessage start of the message to write to the logger
     */
    protected void writeInfoToLog(String startMessage) {
        String structure = String.format("%sXXXXwith algorithm %s", startMessage, data.getAlgo());

        String name = data.getFileName().equals("") ? "" : ("for file " + data.getFileName() + " ");
        structure = structure.replace("XXXX", name);

        if (data.getStartTime() > 0) {
            structure += " took " + data.getTimeTook() + " milliseconds";
        } else {
            structure += "is starting";
        }

        logger.info(structure);
    }

    public void subscribe(Register register) {
        register.eventLogRegister(this);
    }
}
