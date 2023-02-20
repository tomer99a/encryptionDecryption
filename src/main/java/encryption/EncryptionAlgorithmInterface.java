package main.java.encryption;

import main.java.general.encryptsDecrypt;

public interface EncryptionAlgorithmInterface extends encryptsDecrypt {
    String getEncryptionMethod();
    void generateKey();
}
