package encryption.generalsAlgo;

import encryption.charAlgo.ShiftMultiplyEncryption;
import encryption.charAlgo.ShiftUpEncryption;
import encryption.charAlgo.XorEncryption;
import encryption.EncryptionAlgorithmAbstractTest;

import keys.NormalKey;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class DoubleEncryptionTest extends EncryptionAlgorithmAbstractTest {
    public DoubleEncryptionTest() throws IOException {
        super(true);
    }

    @Test
    @DisplayName("encrypt and decrypt by using DoublePlus")
    void encryptPlus() {
        encryptTest(new DoubleEncryption(new ShiftUpEncryption<NormalKey>()));
        decryptTest(new DoubleEncryption(new ShiftUpEncryption<NormalKey>()));
    }

    @Test
    @DisplayName("encrypt and decrypt by using DoubleMulti")
    void encryptMulti() {
        encryptTest(new DoubleEncryption(new ShiftMultiplyEncryption<NormalKey>()));
        decryptTest(new DoubleEncryption(new ShiftMultiplyEncryption<NormalKey>()));
    }

    @Test
    @DisplayName("encrypt and decrypt by using DoubleXor")
    void encryptXor() {
        encryptTest(new DoubleEncryption(new XorEncryption<NormalKey>()));
        decryptTest(new DoubleEncryption(new XorEncryption<NormalKey>()));
    }
}