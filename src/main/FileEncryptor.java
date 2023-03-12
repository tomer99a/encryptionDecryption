import encryption.IEncryptionAlgorithm;
import handler.EventHandler;
import log.ErrorLog4jLogger;

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
            ErrorLog4jLogger.writeErrorToLog(this.getClass(), "The tmp file didn't auto delete");
        }
        this.eventHandler.encrypt(false, true);

    }

    public final void decrypt(final String originalPath, final String outputPath, final T keyPath) {
        this.eventHandler.encrypt(true, true);
        try {
            encryptionAlgo.decrypt(originalPath, outputPath, keyPath);
        } catch (IOException e) {
            ErrorLog4jLogger.writeErrorToLog(this.getClass(), e.getMessage());
        }
        this.eventHandler.decrypt(false, true);
    }
}
