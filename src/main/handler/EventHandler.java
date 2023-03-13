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
     */
    public void encrypt() {
        encryption.publish();
    }

    /**
     * build good message depend on the param
     */
    public void decrypt() {
        decryption.publish();
    }
}
