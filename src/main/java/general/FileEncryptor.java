package java.general;

import java.encryption.EncryptionAlgorithmInterface;

import java.io.IOException;

public class FileEncryptor {
    final private EncryptionAlgorithmInterface ENCRYPTION_ALGORITHM;

    public FileEncryptor(EncryptionAlgorithmInterface ENCRYPTION_ALGORITHM) {
        this.ENCRYPTION_ALGORITHM = ENCRYPTION_ALGORITHM;
    }

    public void encrypt(String originalPath, String outputPath, String keyPath) throws IOException {
        ENCRYPTION_ALGORITHM.encrypt(originalPath, outputPath, keyPath);
    }

    public void decrypt(String originalPath, String outputPath, String keyPath) throws IOException {
        ENCRYPTION_ALGORITHM.decrypt(originalPath, outputPath, keyPath);
    }
}
