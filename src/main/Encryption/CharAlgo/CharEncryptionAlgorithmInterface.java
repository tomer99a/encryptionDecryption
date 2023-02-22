package Encryption.CharAlgo;

import Encryption.EncryptionAlgorithmInterface;

public interface CharEncryptionAlgorithmInterface extends EncryptionAlgorithmInterface {
    char encryptChar(char c, int key);
    char decryptChar(char c, int key);
    void generateKey();
    int getKeyStrength();
}
