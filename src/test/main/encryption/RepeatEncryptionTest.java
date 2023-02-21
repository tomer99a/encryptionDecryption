package encryption;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepeatEncryptionTest extends EncryptionAlgorithmAbstractTest{
    final int repeatNum = 9;

    @Test
    @DisplayName("encrypt and decrypt by using RepeatPlus")
    void encryptPlus() {
        encryptTest(new RepeatEncryption(repeatNum, new ShiftUpEncryption()));
        decryptTest(new RepeatEncryption(repeatNum, new ShiftUpEncryption()));
    }

    @Test
    @DisplayName("encrypt and decrypt by using RepeatMulti")
    void encryptMulti() {
        encryptTest(new RepeatEncryption(repeatNum, new ShiftMultiplyEncryption()));
        decryptTest(new RepeatEncryption(repeatNum, new ShiftMultiplyEncryption()));
    }

    @Test
    @DisplayName("encrypt and decrypt by using RepeatXor")
    void encryptXor() {
        encryptTest(new RepeatEncryption(repeatNum, new XorEncryption()));
        decryptTest(new RepeatEncryption(repeatNum, new XorEncryption()));
    }
}