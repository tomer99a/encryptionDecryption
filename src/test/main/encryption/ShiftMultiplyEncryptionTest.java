package encryption;

import encryption.charAlgo.ShiftMultiplyEncryption;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ShiftMultiplyEncryptionTest extends EncryptionAlgorithmAbstractTest {

    @Test
    @DisplayName("encrypt by using multi")
    void encrypt() {
        encryptTest(new ShiftMultiplyEncryption());
    }

    @Test
    @DisplayName("decrypt by using multi")
    void decrypt() {
        decryptTest(new ShiftMultiplyEncryption());
    }
}