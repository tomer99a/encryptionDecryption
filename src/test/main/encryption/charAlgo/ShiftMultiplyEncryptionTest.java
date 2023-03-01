package encryption.charAlgo;

import encryption.EncryptionAlgorithmAbstractTest;
import keys.NormalKey;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class ShiftMultiplyEncryptionTest extends EncryptionAlgorithmAbstractTest {

    public ShiftMultiplyEncryptionTest() throws IOException {
        super(false);
    }

    @Test
    @DisplayName("encrypt and decrypt by using multi")
    void encrypt() {
        encryptTest(new ShiftMultiplyEncryption());
        decryptTest(new ShiftMultiplyEncryption());
    }

    @Test
    @DisplayName("multi encrypt fail")
    void encryptWrongPathTest() {
        encryptWrongPath(new ShiftMultiplyEncryption<NormalKey>());
    }

    @Test
    @DisplayName("multi encrypt fail")
    void decryptFailTest() throws IOException {
        new ShiftMultiplyEncryption<NormalKey>().encrypt(originalPath, originalPath, (NormalKey) keyPath);
        decryptWrongPath(new ShiftMultiplyEncryption<NormalKey>());
    }
}