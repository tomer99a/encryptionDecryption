package encryptionDecryption.general;

import encryptionDecryption.decryption.DecryptionAlgorithmInterface;
import encryptionDecryption.encryption.EncryptionAlgorithmInterface;

import java.io.IOException;

public class FileEncryptor {
    private EncryptionAlgorithmInterface encryptionAlgorithm;
    private DecryptionAlgorithmInterface decryptionAlgorithm;

    public FileEncryptor(EncryptionAlgorithmInterface encryptionAlgorithm, DecryptionAlgorithmInterface decryptionAlgorithm) throws Exception {
        setEncryptionDecryption(encryptionAlgorithm, decryptionAlgorithm);
    }

    public void setEncryptionDecryption(EncryptionAlgorithmInterface encryptionAlgorithm, DecryptionAlgorithmInterface decryptionAlgorithm) throws Exception {
        if(encryptionAlgorithm.getEncryptionMethod().equals(decryptionAlgorithm.getDecryptionMethod())){
            this.encryptionAlgorithm = encryptionAlgorithm;
            this.decryptionAlgorithm = decryptionAlgorithm;
        } else
            throw new Error("The encryption and decryption should use the same algo");
    }

    public void encrypt(String originalPath, String outputPath, String keyPath) throws IOException {
        encryptionAlgorithm.act(originalPath, outputPath, keyPath);
    }

    public void decrypt(String originalPath, String outputPath, String keyPath) throws IOException {
        decryptionAlgorithm.act(originalPath, outputPath, keyPath);
    }
}
