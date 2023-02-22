import Encryption.InterfaceEncryptionAlgorithm;

import java.io.IOException;

public class FileEncryptor {
    final private InterfaceEncryptionAlgorithm encryptionAlgo;

    public FileEncryptor(InterfaceEncryptionAlgorithm encryptionAlgo) {
        this.encryptionAlgo = encryptionAlgo;
    }

    public void encrypt(String originalPath, String outputPath, String keyPath){
        try{
            encryptionAlgo.encrypt(originalPath, outputPath, keyPath);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

    public void decrypt(String originalPath, String outputPath, String keyPath){
        try{
            encryptionAlgo.decrypt(originalPath, outputPath, keyPath);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
