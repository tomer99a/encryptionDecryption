package handler;

import log.DecryptEventLogger;
import log.EncryptEventLogger;

public class EventHandler {
    Register encryption = new Register();
    Register decryption = new Register();

    public EventHandler(Class<?> clazz) {
        new EncryptEventLogger(clazz).subscribe(encryption);
        new DecryptEventLogger(clazz).subscribe(decryption);
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
