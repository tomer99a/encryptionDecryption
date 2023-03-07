package log;

public class HandlerEvent {
    Register encryption = new Register();
    Register decryption = new Register();

    public HandlerEvent(Class<?> clazz) {
        new EventEncrypt(clazz).subscribe(encryption);
        new EventDecrypt(clazz).subscribe(decryption);
    }

    /**
     * build good message depend on the param
     * @param startStatus say if the event start (true) or end (false)
     */
    public void encrypt(boolean startStatus) {
        encryption.publish(startStatus);
    }

    /**
     * build good message depend on the param
     * @param startStatus say if the event start (true) or end (false)
     */
    public void decrypt(boolean startStatus) {
        decryption.publish(startStatus);
    }
}
