import encryption.EncryptionAlgorithmInterface;

import java.io.IOException;

public class FileEncryptor {
    final private EncryptionAlgorithmInterface encryptionAlgo;

    public FileEncryptor(EncryptionAlgorithmInterface encryptionAlgo) {
        this.encryptionAlgo = encryptionAlgo;
    }

    public void encrypt(String originalPath, String outputPath, String keyPath) throws IOException {
        encryptionAlgo.encrypt(originalPath, outputPath, keyPath);
    }

    public void decrypt(String originalPath, String outputPath, String keyPath) throws IOException {
        encryptionAlgo.decrypt(originalPath, outputPath, keyPath);
    }
}
