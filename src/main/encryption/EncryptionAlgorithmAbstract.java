package encryption;

import handler.EventHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

public abstract class EncryptionAlgorithmAbstract<T> implements IEncryptionAlgorithm<T> {
    protected final Logger logger = LogManager.getLogger(this.getClass());
    protected String encryptionMethod;

    public EncryptionAlgorithmAbstract(String encryptionMethod) {
        this.encryptionMethod = encryptionMethod;
    }

    public String getEncryptionMethod() {
        return encryptionMethod;
    }

    @Override
    public String toString() {
        return this.getEncryptionMethod();
    }

    @Override
    public void encrypt(final String originalPath, final String outputPath, final T keyPath) throws IOException {
        File file = new File(originalPath);
        EventHandler eventHandler = new EventHandler(this.getClass(), file.getName());
        eventHandler.encrypt(true);
        encryption(originalPath, outputPath, keyPath);
        eventHandler.encrypt(true);
    }

    @Override
    public void decrypt(final String originalPath, final String outputPath, final T keyPath) throws IOException {
        File file = new File(originalPath);
        EventHandler eventHandler = new EventHandler(this.getClass(), file.getName());
        eventHandler.decrypt(true);
        decryption(originalPath, outputPath, keyPath);
        eventHandler.decrypt(true);
    }

    /**
     * encrypt the original path to the output path.
     *
     * @param originalPath file to encrypt
     * @param outputPath   file for encrypted text
     * @param keyPath      file to save the key that the encryption use.
     * @throws IOException IOException
     */
    protected abstract void encryption(final String originalPath, final String outputPath, final T keyPath) throws IOException;

    /**
     * decrypt the original path to the output path.
     *
     * @param originalPath encrypt file path
     * @param outputPath   file for decrypted text
     * @param keyPath      file with the key to use the decryption
     * @throws IOException IOException
     */
    protected abstract void decryption(final String originalPath, final String outputPath, final T keyPath) throws IOException;
}
