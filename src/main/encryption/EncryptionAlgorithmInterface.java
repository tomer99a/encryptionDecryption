package encryption;

import java.io.IOException;

public interface EncryptionAlgorithmInterface {
    void encrypt(String originalPath, String outputPath, String keyPath) throws IOException;
    void decrypt(String originalPath, String outputPath, String keyPath) throws IOException;

    String getEncryptionMethod();
}
