package encryption;

import keys.AKey;

import java.io.IOException;

public interface IEncryptionAlgorithm<T extends AKey> {
    void encrypt(final String originalPath, final String outputPath, final T keyPath) throws IOException;
    void decrypt(final String originalPath, final String outputPath, final T keyPath) throws IOException;

    String getEncryptionMethod();
}
