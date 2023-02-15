package encryptionDecryption.general;

import encryptionDecryption.decryption.DecryptionAlgorithmInterface;
import encryptionDecryption.encryption.EncryptionAlgorithmInterface;

public class FileEncryptor {
    private EncryptionAlgorithmInterface encryptionAlgorithmInterface;
    private DecryptionAlgorithmInterface decryptionAlgorithmInterface;

    public void setEncryptionDecryption(EncryptionAlgorithmInterface encryptionAlgorithm, DecryptionAlgorithmInterface decryptionAlgorithm) throws Exception {
        if(encryptionAlgorithm.getEncryptionMethod().equals(decryptionAlgorithm.getDecryptionMethod())){
            this.decryptionAlgorithmInterface = decryptionAlgorithm;
            this.encryptionAlgorithmInterface = encryptionAlgorithm;
        } else
            throw new Exception("The encryption and decryption should be the same kind");
    }

    public void encrypt(){
        encryptionAlgorithmInterface.act();
    }

    public void decrypt(){
        decryptionAlgorithmInterface.act();
    }
}
