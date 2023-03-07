package log;

public class HandlerEvent {
    Register encryption = new Register();
    Register decryption = new Register();

    public HandlerEvent(Class<?> clazz) {
        new EventEncrypt(clazz).subscribe(encryption);
        new EventDecrypt(clazz).subscribe(decryption);
    }

    public void encrypt(boolean startStatus) {
        encryption.publish(startStatus);
    }

    public void decrypt(boolean startStatus) {
        decryption.publish(startStatus);
    }
}
