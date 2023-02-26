package Encryption.GeneralsAlgo;

import Encryption.CharAlgo.ShiftMultiplyEncryption;
import Encryption.CharAlgo.ShiftUpEncryption;
import Encryption.CharAlgo.XorEncryption;
import Encryption.EncryptionAlgorithmAbstractTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DoubleEncryptionTest extends EncryptionAlgorithmAbstractTest {

    @Test
    @DisplayName("encrypt and decrypt by using DoublePlus")
    void encryptPlus() {
        encryptTest(new DoubleEncryption(new ShiftUpEncryption()));
        decryptTest(new DoubleEncryption(new ShiftUpEncryption()));
    }

    @Test
    @DisplayName("encrypt and decrypt by using DoubleMulti")
    void encryptMulti() {
        encryptTest(new DoubleEncryption(new ShiftMultiplyEncryption()));
        decryptTest(new DoubleEncryption(new ShiftMultiplyEncryption()));
    }

    @Test
    @DisplayName("encrypt and decrypt by using DoubleXor")
    void encryptXor() {
        encryptTest(new DoubleEncryption(new XorEncryption()));
        decryptTest(new DoubleEncryption(new XorEncryption()));
    }
}