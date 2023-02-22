package Encryption.CharAlgo;

import Encryption.EncryptionAlgorithmAbstractTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class XorEncryptionTest extends EncryptionAlgorithmAbstractTest {
    @Test
    @DisplayName("encrypt and decrypt by using xor")
    void encrypt(){
        encryptTest(new XorEncryption());
        decryptTest(new XorEncryption());
    }
}