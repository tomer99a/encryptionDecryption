import encryption.IEncryptionAlgorithm;
import keys.AKey;
import logs.EncryptionLogEventArgs;
import logs.EncryptionLogger;

import java.io.IOException;

public class FileEncryptor<T extends AKey> {
    final private IEncryptionAlgorithm<AKey> encryptionAlgo;

    public FileEncryptor(IEncryptionAlgorithm<AKey> encryptionAlgo) {
        this.encryptionAlgo = encryptionAlgo;
    }

    public void encrypt(final String originalPath, final String outputPath, final T keyPath) {
        EncryptionLogger.writeToLog(EncryptionLogEventArgs.getMessage("start encryption"));
        try {
            encryptionAlgo.encrypt(originalPath, outputPath, keyPath);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        EncryptionLogger.writeToLog(EncryptionLogEventArgs.getMessage("end encryption"));

    }

    public void decrypt(final String originalPath, final String outputPath, final T keyPath){
        EncryptionLogger.writeToLog(EncryptionLogEventArgs.getMessage("start decryption"));
        try{
            encryptionAlgo.decrypt(originalPath, outputPath, keyPath);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        EncryptionLogger.writeToLog(EncryptionLogEventArgs.getMessage("end decryption"));
    }
}
