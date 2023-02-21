package general;

import encryption.EncryptionAlgorithmInterface;
import java.nio.file.Path;

import java.io.IOException;

public class FileEncryptor {
    final private EncryptionAlgorithmInterface ENCRYPTION_ALGORITHM;

    public FileEncryptor(EncryptionAlgorithmInterface ENCRYPTION_ALGORITHM) {
        this.ENCRYPTION_ALGORITHM = ENCRYPTION_ALGORITHM;
    }

    public void encrypt(Path originalPath, Path outputPath, Path keyPath) throws IOException {
        ENCRYPTION_ALGORITHM.encrypt(originalPath, outputPath, keyPath);
    }

    public void decrypt(Path originalPath, Path outputPath, Path keyPath) throws IOException {
        ENCRYPTION_ALGORITHM.decrypt(originalPath, outputPath, keyPath);
    }
}
