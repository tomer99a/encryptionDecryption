package encryption.charAlgo;

import encryption.EncryptionAlgorithmAbstractTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class ShiftMultiplyEncryptionTest extends EncryptionAlgorithmAbstractTest {

    @Test
    @DisplayName("encrypt and decrypt by using multi")
    void encrypt() {
        encryptTest(new ShiftMultiplyEncryption());
        decryptTest(new ShiftMultiplyEncryption());
    }

    @Test
    @DisplayName("multi encrypt fail")
    void encryptWrongPathTest() {
        encryptWrongPath(new ShiftMultiplyEncryption());
    }

    @Test
    @DisplayName("multi encrypt fail")
    void decryptFailTest() throws IOException {
        new ShiftMultiplyEncryption().encrypt(originalPath, originalPath, keyPath);
        decryptWrongPath(new ShiftMultiplyEncryption());
    }
}