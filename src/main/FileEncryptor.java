import Encryption.IEncryptionAlgorithm;
import Logs.EncryptionLogEventArgs;
import Logs.EncryptionLogger;

import java.io.IOException;

public class FileEncryptor {
    final private IEncryptionAlgorithm encryptionAlgo;

    public FileEncryptor(IEncryptionAlgorithm encryptionAlgo) {
        this.encryptionAlgo = encryptionAlgo;
    }

    public void encrypt(String originalPath, String outputPath, String keyPath){
        EncryptionLogger.writeToLog(EncryptionLogEventArgs.getMessage("start encryption"));
        try{
            encryptionAlgo.encrypt(originalPath, outputPath, keyPath);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        EncryptionLogger.writeToLog(EncryptionLogEventArgs.getMessage("end encryption"));

    }

    public void decrypt(String originalPath, String outputPath, String keyPath){
        EncryptionLogger.writeToLog(EncryptionLogEventArgs.getMessage("start decryption"));
        try{
            encryptionAlgo.decrypt(originalPath, outputPath, keyPath);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        EncryptionLogger.writeToLog(EncryptionLogEventArgs.getMessage("end decryption"));
    }
}
