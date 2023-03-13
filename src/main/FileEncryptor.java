import encryption.IEncryptionAlgorithm;
import handler.EventHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class FileEncryptor<T> {
    protected final Logger logger = LogManager.getLogger(this.getClass());
    final private IEncryptionAlgorithm<T> encryptionAlgo;


    public FileEncryptor(final IEncryptionAlgorithm<T> encryptionAlgo) {
        this.encryptionAlgo = encryptionAlgo;
    }

    public final void encrypt(final String originalPath, final String outputPath, final T keyPath) {
        File file = new File(originalPath);
        try {
            encryptionAlgo.encrypt(originalPath, outputPath, keyPath);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public final void decrypt(final String originalPath, final String outputPath, final T keyPath) {
        File file = new File(originalPath);
        try {
            encryptionAlgo.decrypt(originalPath, outputPath, keyPath);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
