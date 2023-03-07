package encryption.generalsAlgo;

import encryption.EncryptionAlgorithmAbstractTest;
import encryption.charAlgo.ShiftMultiplyEncryption;
import encryption.charAlgo.ShiftUpEncryption;
import encryption.charAlgo.XorEncryption;
import keys.DoubleKey;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

class DoubleEncryptionTest extends EncryptionAlgorithmAbstractTest {
    private final DoubleKey doubleKeyPath;

    public DoubleEncryptionTest() throws IOException {
        super();
        String keyPath1 = addSuffixToFileNameAtPath(keyPath.getKey(), "1");
        String keyPath2 = addSuffixToFileNameAtPath(keyPath.getKey(), "2");
        doubleKeyPath = new DoubleKey(keyPath1, keyPath2);
    }

    private void encryptDoubleTest(DoubleEncryption doubleEncryption) {
        try {
            doubleEncryption.encrypt(originalPath, encryptedPath, doubleKeyPath);
        } catch (IOException e) {
            String message = String.format("The %s encryption failed\nError message - %s", doubleEncryption.getEncryptionMethod(), e.getMessage());
            fail(message);
        }
        assertFalse(compareTwoFiles(originalPath, encryptedPath));
    }

    private void decryptDoubleTest(DoubleEncryption doubleEncryption) {
        try {
            doubleEncryption.decrypt(encryptedPath, decryptedPath, doubleKeyPath);
        } catch (IOException e) {
            String message = String.format("The %s decryption failed\nError message - %s", doubleEncryption.getEncryptionMethod(), e.getMessage());
            fail(message);
        }
        assertFalse(compareTwoFiles(originalPath, encryptedPath));
    }

    @Test
    @DisplayName("encrypt and decrypt by using DoublePlus")
    void encryptPlus() {
        encryptDoubleTest(new DoubleEncryption(new ShiftUpEncryption()));
        decryptDoubleTest(new DoubleEncryption(new ShiftUpEncryption()));
    }

    @Test
    @DisplayName("encrypt and decrypt by using DoubleMulti")
    void encryptMulti() {
        encryptDoubleTest(new DoubleEncryption(new ShiftMultiplyEncryption()));
        decryptDoubleTest(new DoubleEncryption(new ShiftMultiplyEncryption()));
    }

    @Test
    @DisplayName("encrypt and decrypt by using DoubleXor")
    void encryptXor() {
        encryptDoubleTest(new DoubleEncryption(new XorEncryption()));
        decryptDoubleTest(new DoubleEncryption(new XorEncryption()));
    }
}