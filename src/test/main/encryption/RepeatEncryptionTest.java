package encryption;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepeatEncryptionTest extends EncryptionAlgorithmAbstractTest{
    final int REPEAT_NUM = 5;

    @Test
    @DisplayName("encrypt and decrypt by using RepeatPlus")
    void encryptPlus() {
        encryptTest(new RepeatEncryption(REPEAT_NUM, new ShiftUpEncryption()));
        decryptTest(new RepeatEncryption(REPEAT_NUM, new ShiftUpEncryption()));
    }

    @Test
    @DisplayName("encrypt and decrypt by using RepeatMulti")
    void encryptMulti() {
        encryptTest(new RepeatEncryption(REPEAT_NUM, new ShiftMultiplyEncryption()));
        decryptTest(new RepeatEncryption(REPEAT_NUM, new ShiftMultiplyEncryption()));
    }

    @Test
    @DisplayName("encrypt and decrypt by using RepeatXor")
    void encryptXor() {
        encryptTest(new RepeatEncryption(REPEAT_NUM, new XorEncryption()));
        decryptTest(new RepeatEncryption(REPEAT_NUM, new XorEncryption()));
    }
}