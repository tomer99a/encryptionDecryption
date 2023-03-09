package encryption;

import handler.EventHandler;

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
        EventHandler eventHandler = new EventHandler(this.getClass());
        eventHandler.encrypt(true, true);
        this.actualEncrypt(originalPath, outputPath, keyPath);
        eventHandler.encrypt(false, true);
    }

    @Override
    public void decrypt(final String originalPath, final String outputPath, final T keyPath) throws IOException {
        EventHandler eventHandler = new EventHandler(this.getClass());
        eventHandler.decrypt(true, true);
        this.actualDecrypt(originalPath, outputPath, keyPath);
        eventHandler.decrypt(false, true);
    }

    protected abstract void actualEncrypt(final String originalPath, final String outputPath, final T keyPath) throws IOException;
    protected abstract void actualDecrypt(final String originalPath, final String outputPath, final T keyPath) throws IOException;
}
