package encryptionDecryption.encryption;

import encryptionDecryption.general.encryptsDecrypt;

public interface EncryptionAlgorithmInterface extends encryptsDecrypt {
    String getEncryptionMethod();
    void generateKey();
}
