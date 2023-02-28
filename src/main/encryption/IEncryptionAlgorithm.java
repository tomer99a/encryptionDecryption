package encryption;

import keys.IKey;

import java.io.IOException;

public interface IEncryptionAlgorithm<K extends IKey> {
    void encrypt(final String originalPath, final String outputPath, final K keyPath) throws IOException;
    void decrypt(final String originalPath, final String outputPath, final K keyPath) throws IOException;

    String getEncryptionMethod();
}
