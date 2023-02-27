package encryption;

import java.io.IOException;

public interface IEncryptionAlgorithm {
    void encrypt(final String originalPath, final String outputPath, final String keyPath) throws IOException;
    void decrypt(final String originalPath, final String outputPath, final String keyPath) throws IOException;

    String getEncryptionMethod();
}
