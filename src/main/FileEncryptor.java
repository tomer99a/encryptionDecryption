import encryption.IEncryptionAlgorithm;
import handler.EventHandler;

import java.io.IOException;

public class FileEncryptor<T> {
    final private IEncryptionAlgorithm<T> encryptionAlgo;
    final private EventHandler eventHandler;

    public FileEncryptor(final IEncryptionAlgorithm<T> encryptionAlgo) {
        this.encryptionAlgo = encryptionAlgo;
        eventHandler = new EventHandler(encryptionAlgo.getClass());
    }

    public final void encrypt(final String originalPath, final String outputPath, final T keyPath) {
        this.eventHandler.encrypt(true, true);
        try {
            encryptionAlgo.encrypt(originalPath, outputPath, keyPath);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        this.eventHandler.encrypt(false, true);

    }

    public final void decrypt(final String originalPath, final String outputPath, final T keyPath) {
        this.eventHandler.encrypt(true, true);
        try {
            encryptionAlgo.decrypt(originalPath, outputPath, keyPath);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        this.eventHandler.decrypt(false, true);
    }
}
