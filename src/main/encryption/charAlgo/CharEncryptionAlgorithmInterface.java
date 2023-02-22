package encryption.charAlgo;

import encryption.EncryptionAlgorithmInterface;

public interface CharEncryptionAlgorithmInterface extends EncryptionAlgorithmInterface {
    char encryptChar(char c, int key);
    char decryptChar(char c, int key);
    void generateKey();
}
