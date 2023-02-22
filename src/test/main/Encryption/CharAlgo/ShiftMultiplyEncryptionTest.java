package Encryption.CharAlgo;

import Encryption.EncryptionAlgorithmAbstractTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ShiftMultiplyEncryptionTest extends EncryptionAlgorithmAbstractTest {

    @Test
    @DisplayName("encrypt and decrypt by using multi")
    void encrypt() {
        encryptTest(new ShiftMultiplyEncryption());
        decryptTest(new ShiftMultiplyEncryption());
    }
}