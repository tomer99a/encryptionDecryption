import encryption.IEncryptionAlgorithm;
import log.HandlerEvent;

import java.io.IOException;

public class FileEncryptor<T> {
    final private IEncryptionAlgorithm<T> encryptionAlgo;
    final private HandlerEvent handlerEvent;

    public FileEncryptor(final IEncryptionAlgorithm<T> encryptionAlgo) {
        this.encryptionAlgo = encryptionAlgo;
        handlerEvent = new HandlerEvent(encryptionAlgo.getClass());
    }

    public final void encrypt(final String originalPath, final String outputPath, final T keyPath) {
        this.handlerEvent.encrypt(true, true);
        try {
            encryptionAlgo.encrypt(originalPath, outputPath, keyPath);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        this.handlerEvent.encrypt(false, true);

    }

    public final void decrypt(final String originalPath, final String outputPath, final T keyPath) {
        this.handlerEvent.encrypt(true, true);
        try {
            encryptionAlgo.decrypt(originalPath, outputPath, keyPath);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        this.handlerEvent.decrypt(false, true);
    }
}
