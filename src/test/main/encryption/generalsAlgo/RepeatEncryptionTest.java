package encryption.generalsAlgo;

import encryption.charAlgo.ShiftMultiplyEncryption;
import encryption.charAlgo.ShiftUpEncryption;
import encryption.charAlgo.XorEncryption;
import encryption.EncryptionAlgorithmAbstractTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RepeatEncryptionTest extends EncryptionAlgorithmAbstractTest {
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5})
    @DisplayName("encrypt and decrypt by using RepeatPlus")
    void encryptPlus(int repeatNum) {
        encryptTest(new RepeatEncryption(repeatNum, new ShiftUpEncryption()));
        decryptTest(new RepeatEncryption(repeatNum, new ShiftUpEncryption()));
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 5, 9, 15})
    @DisplayName("encrypt and decrypt by using RepeatMulti")
    void encryptMulti(int repeatNum) {
        encryptTest(new RepeatEncryption(repeatNum, new ShiftMultiplyEncryption()));
        decryptTest(new RepeatEncryption(repeatNum, new ShiftMultiplyEncryption()));
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 5, 9, 15})
    @DisplayName("encrypt and decrypt by using RepeatXor")
    void encryptXor(int repeatNum) {
        encryptTest(new RepeatEncryption(repeatNum, new XorEncryption()));
        decryptTest(new RepeatEncryption(repeatNum, new XorEncryption()));
    }
}