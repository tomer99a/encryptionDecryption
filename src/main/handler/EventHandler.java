package handler;

import log.DecryptionLog4jLogger;
import log.EncryptionLog4jLogger;

public class EventHandler {
    Register encryption = new Register();
    Register decryption = new Register();

    public EventHandler(Class<?> clazz, String fileName) {
        new EncryptionLog4jLogger(clazz, fileName).subscribe(encryption);
        new DecryptionLog4jLogger(clazz, fileName).subscribe(decryption);
    }

    /**
     * build good message depend on the param
     *
     * @param single say if the event occur on single thing or all of them
     */
    public void encrypt(boolean single) {
        encryption.publish(single);
    }

    /**
     * build good message depend on the param
     *
     * @param single say if the event occur on single thing or all of them
     */
    public void decrypt(boolean single) {
        decryption.publish(single);
    }
}
