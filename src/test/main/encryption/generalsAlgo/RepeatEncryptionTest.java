package encryption.generalsAlgo;

import encryption.EncryptionAlgorithmAbstractTest;
import encryption.charAlgo.ShiftMultiplyEncryption;
import encryption.charAlgo.ShiftUpEncryption;
import encryption.charAlgo.XorEncryption;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;

class RepeatEncryptionTest extends EncryptionAlgorithmAbstractTest {
    public RepeatEncryptionTest() throws IOException {
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 14, 18})
    @DisplayName("encrypt and decrypt by using RepeatPlus")
    void encryptPlus(int repeatNum) {
        RepeatEncryption repeatEncryption = new RepeatEncryption(repeatNum, new ShiftUpEncryption());
        encryptTest(repeatEncryption);
        decryptTest(repeatEncryption);
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