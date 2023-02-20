package main.java.encryption;

import java.io.IOException;

public interface EncryptionAlgorithmInterface {
    void encrypt(String originalPath, String outputPath, String keyPath) throws IOException;
    void decrypt(String originalPath, String outputPath, String keyPath) throws IOException;

    char encryptChar(char c, int key);
    char decryptChar(char c, int key);

    void generateKey();
}
