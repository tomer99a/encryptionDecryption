package encryptionDecryption.general;

import encryptionDecryption.decryption.DecryptionAlgorithmInterface;
import encryptionDecryption.encryption.EncryptionAlgorithmInterface;

import java.io.IOException;

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

    public void encrypt(String originalPath, String outputPath, String keyPath) throws IOException {
        encryptionAlgorithmInterface.act(originalPath, outputPath, keyPath);
    }

    public void decrypt(String originalPath, String outputPath, String keyPath) throws IOException {
        decryptionAlgorithmInterface.act(originalPath, outputPath, keyPath);
    }
}
