package main.java.encryption;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static main.java.utils.IOMethods.readFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

class XorEncryptionTest {

    final private String originalPath;
    final private String encryptedPath;
    final private String decryptedPath;
    final private String keyPath;

    XorEncryptionTest(String keyPath) {
        String fileName = "alpha";
        String basePath = "src\\main\\java\\data\\";
        this.originalPath = basePath + fileName + ".txt";
        this.encryptedPath = basePath + fileName + "_encrypted.txt";
        this.decryptedPath = basePath + fileName + "_decrypted.txt";
        this.keyPath = basePath + "key.txt";
    }

    @Test
    void encryptChar() throws IOException {
        String originalMessage = readFile(originalPath);
        XorEncryption xorEncryption = new XorEncryption();
        xorEncryption.encrypt(originalPath, encryptedPath, keyPath);
        assertEquals(4, 2+2);

    }

    @Test
    void decryptChar() {
        assertEquals(4, 2+2);
    }
}