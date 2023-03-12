package encryption;

import handler.EventHandler;

import java.io.File;
import java.io.IOException;

public abstract class EncryptionAlgorithmAbstract<T> implements IEncryptionAlgorithm<T> {
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
        this.actualEncrypt(originalPath, outputPath, keyPath);
        eventHandler.encrypt(true);
    }

    @Override
    public void decrypt(final String originalPath, final String outputPath, final T keyPath) throws IOException {
        File file = new File(originalPath);
        EventHandler eventHandler = new EventHandler(this.getClass(), file.getName());
        eventHandler.decrypt(true);
        this.actualDecrypt(originalPath, outputPath, keyPath);
        eventHandler.decrypt(true);
    }

    protected abstract void actualEncrypt(final String originalPath, final String outputPath, final T keyPath) throws IOException;
    protected abstract void actualDecrypt(final String originalPath, final String outputPath, final T keyPath) throws IOException;
}
