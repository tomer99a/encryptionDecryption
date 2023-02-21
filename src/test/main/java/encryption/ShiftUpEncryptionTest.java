package java.encryption;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ShiftUpEncryptionTest extends EncryptionAlgorithmAbstractTest {

    @Test
    @DisplayName("encrypt by using plus")
    void encryptChar() {
        encryptTest(new ShiftUpEncryption());
    }

    @Test
    @DisplayName("decrypt by using plus")
    void decryptChar() {
        decryptTest(new ShiftUpEncryption());
    }
}