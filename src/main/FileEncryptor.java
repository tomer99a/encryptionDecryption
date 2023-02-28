import encryption.IEncryptionAlgorithm;
import keys.IKey;
import logs.EncryptionLogEventArgs;
import logs.EncryptionLogger;

import java.io.IOException;

public class FileEncryptor<K extends IKey> {
    final private IEncryptionAlgorithm<IKey> encryptionAlgo;

    public FileEncryptor(IEncryptionAlgorithm<IKey> encryptionAlgo) {
        this.encryptionAlgo = encryptionAlgo;
    }

    public void encrypt(final String originalPath, final String outputPath, final K keyPath) {
//        EncryptionLogger.writeToLog(EncryptionLogEventArgs.getMessage("start encryption"));
        try {
            encryptionAlgo.encrypt(originalPath, outputPath, keyPath);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
//        EncryptionLogger.writeToLog(EncryptionLogEventArgs.getMessage("end encryption"));

    }

    public void decrypt(final String originalPath, final String outputPath, final K keyPath){
//        EncryptionLogger.writeToLog(EncryptionLogEventArgs.getMessage("start decryption"));
        try{
            encryptionAlgo.decrypt(originalPath, outputPath, keyPath);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
//        EncryptionLogger.writeToLog(EncryptionLogEventArgs.getMessage("end decryption"));
    }
}
