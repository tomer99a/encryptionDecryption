package Encryption.CharAlgo;

import Encryption.EncryptionAlgorithmAbstractTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ShiftUpEncryptionTest extends EncryptionAlgorithmAbstractTest {

    @Test
    @DisplayName("encrypt by using plus")
    void encrypt() {
        encryptTest(new ShiftUpEncryption());
    }

    @Test
    @DisplayName("decrypt by using plus")
    void decrypt() {
        decryptTest(new ShiftUpEncryption());
    }
}