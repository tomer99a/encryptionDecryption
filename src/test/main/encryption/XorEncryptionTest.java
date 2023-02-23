package encryption;

import encryption.charAlgo.XorEncryption;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class XorEncryptionTest extends EncryptionAlgorithmAbstractTest {
    @Test
    @DisplayName("encrypt by using xor")
    void encrypt(){
        encryptTest(new XorEncryption());
    }

    @Test
    @DisplayName("decrypt by using xor")
    void decrypt() {
        decryptTest(new XorEncryption());
    }
}