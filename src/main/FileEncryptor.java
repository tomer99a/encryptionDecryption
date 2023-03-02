import encryption.IEncryptionAlgorithm;

import logs.*;

import java.io.IOException;

public class FileEncryptor<T> {
    final private IEncryptionAlgorithm<T> encryptionAlgo;
    final private LogStart encryptionStart;
    final private LogEnd encryptionEnd;
    final private LogStart decryptionStart;
    final private LogEnd decryptionEnd;

    public FileEncryptor(IEncryptionAlgorithm<T> encryptionAlgo) {
        this.encryptionAlgo = encryptionAlgo;
        LogBasic logBasic = new LogBasic(encryptionAlgo.getClass());
        LogEncrypt logEncrypt = new LogEncrypt(logBasic);
        LogDecrypt logDecrypt = new LogDecrypt(logBasic);

        encryptionStart = new LogStart(logEncrypt);
        encryptionEnd = new LogEnd(logEncrypt);
        decryptionStart = new LogStart(logDecrypt);
        decryptionEnd = new LogEnd(logDecrypt);
    }

    public void encrypt(final String originalPath, final String outputPath, final T keyPath) {
        encryptionStart.writeMessage();
        try {
            encryptionAlgo.encrypt(originalPath, outputPath, keyPath);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        encryptionEnd.writeMessage();
    }

    public void decrypt(final String originalPath, final String outputPath, final T keyPath){
        decryptionStart.writeMessage();
        try{
            encryptionAlgo.decrypt(originalPath, outputPath, keyPath);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        decryptionEnd.writeMessage();
    }
}
