package main.java.general;

import main.java.encryption.EncryptionAlgorithmInterface;

import java.io.IOException;

public class FileEncryptor {
    final private EncryptionAlgorithmInterface encryptionAlgorithm;

    public FileEncryptor(EncryptionAlgorithmInterface encryptionAlgorithm) {
        this.encryptionAlgorithm = encryptionAlgorithm;
    }

    public void encrypt(String originalPath, String outputPath, String keyPath) throws IOException {
        encryptionAlgorithm.encrypt(originalPath, outputPath, keyPath);
    }

    public void decrypt(String originalPath, String outputPath, String keyPath) throws IOException {
        encryptionAlgorithm.decrypt(originalPath, outputPath, keyPath);
    }
}
