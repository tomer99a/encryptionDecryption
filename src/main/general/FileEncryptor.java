package general;

import encryption.EncryptionAlgorithmInterface;
import java.nio.file.Path;

import java.io.IOException;

public class FileEncryptor {
    final private EncryptionAlgorithmInterface encryptionAlgo;

    public FileEncryptor(EncryptionAlgorithmInterface encryptionAlgo) {
        this.encryptionAlgo = encryptionAlgo;
    }

    public void encrypt(Path originalPath, Path outputPath, Path keyPath) throws IOException {
        encryptionAlgo.encrypt(originalPath, outputPath, keyPath);
    }

    public void decrypt(Path originalPath, Path outputPath, Path keyPath) throws IOException {
        encryptionAlgo.decrypt(originalPath, outputPath, keyPath);
    }
}
