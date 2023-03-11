package handler;

import log.DecryptionLog4jLogger;
import log.EncryptionLog4jLogger;

public class EventHandler {
    Register encryption = new Register();
    Register decryption = new Register();

    public EventHandler(Class<?> clazz) {
        new EncryptionLog4jLogger(clazz).subscribe(encryption);
        new DecryptionLog4jLogger(clazz).subscribe(decryption);
    }

    /**
     * build good message depend on the param
     * @param startStatus say if the event start (true) or end (false)
     * @param single say if the event occur on single thing or all of them
     */
    public void encrypt(boolean startStatus, boolean single) {
        encryption.publish(startStatus, single);
    }

    /**
     * build good message depend on the param
     * @param startStatus say if the event start (true) or end (false)
     * @param single say if the event occur on single thing or all of them
     */
    public void decrypt(boolean startStatus, boolean single) {
        decryption.publish(startStatus, single);
    }
}
