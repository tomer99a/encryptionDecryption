package encryption.charAlgo;

import encryption.EncryptionAlgorithmAbstractTest;
import keys.NormalKey;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class ShiftUpEncryptionTest extends EncryptionAlgorithmAbstractTest {

    public ShiftUpEncryptionTest() throws IOException {
    }

    @Test
    @DisplayName("encrypt and decrypt by using plus")
    void encrypt() {
        encryptTest(new ShiftUpEncryption<>());
        decryptTest(new ShiftUpEncryption<>());
    }

    @Test
    @DisplayName("up encrypt fail")
    void encryptWrongPathTest() {
        encryptWrongPath(new ShiftUpEncryption<>());
    }

    @Test
    @DisplayName("up encrypt fail")
    void decryptFailTest() throws IOException {
        new ShiftUpEncryption<>().encrypt(originalPath, originalPath, keyPath);
        decryptWrongPath(new ShiftUpEncryption<>());
    }
}