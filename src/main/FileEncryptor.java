import encryption.IEncryptionAlgorithm;
import handler.EventHandler;
import log.ErrorLog4jLogger;

import java.io.File;
import java.io.IOException;

public class FileEncryptor<T> {
    final private IEncryptionAlgorithm<T> encryptionAlgo;


    public FileEncryptor(final IEncryptionAlgorithm<T> encryptionAlgo) {
        this.encryptionAlgo = encryptionAlgo;
    }

    public final void encrypt(final String originalPath, final String outputPath, final T keyPath) {
        File file = new File(originalPath);
        EventHandler eventHandler = new EventHandler(encryptionAlgo.getClass(), file.getName());
        eventHandler.encrypt(true);
        try {
            encryptionAlgo.encrypt(originalPath, outputPath, keyPath);
        } catch (IOException e) {
            ErrorLog4jLogger.writeErrorToLog(this.getClass(), "The tmp file didn't auto delete");
        }
        eventHandler.encrypt(true);

    }

    public final void decrypt(final String originalPath, final String outputPath, final T keyPath) {
        File file = new File(originalPath);
        EventHandler eventHandler = new EventHandler(encryptionAlgo.getClass(), file.getName());
        eventHandler.encrypt(true);
        try {
            encryptionAlgo.decrypt(originalPath, outputPath, keyPath);
        } catch (IOException e) {
            ErrorLog4jLogger.writeErrorToLog(this.getClass(), e.getMessage());
        }
        eventHandler.decrypt(true);
    }
}
