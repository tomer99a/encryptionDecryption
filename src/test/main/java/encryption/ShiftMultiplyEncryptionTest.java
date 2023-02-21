package java.encryption;

import org.junit.jupiter.api.Test;

class ShiftMultiplyEncryptionTest extends EncryptionAlgorithmAbstractTest {

    @Test
    void encryptChar() {
        encryptTest(new ShiftMultiplyEncryption());
    }

    @Test
    void decryptChar() {
        decryptTest(new ShiftMultiplyEncryption());
    }
}