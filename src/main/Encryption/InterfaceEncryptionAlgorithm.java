package Encryption;

import java.io.IOException;

public interface InterfaceEncryptionAlgorithm {
    void encrypt(String originalPath, String outputPath, String keyPath) throws IOException;
    void decrypt(String originalPath, String outputPath, String keyPath) throws IOException;

    String getEncryptionMethod();
}