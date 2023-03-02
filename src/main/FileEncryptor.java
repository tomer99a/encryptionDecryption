import encryption.IEncryptionAlgorithm;

import logs.*;

import java.io.IOException;

public class FileEncryptor<T> {
    final private IEncryptionAlgorithm<T> encryptionAlgo;
    final private LogBasic logBasic;

    public FileEncryptor(IEncryptionAlgorithm<T> encryptionAlgo) {
        this.encryptionAlgo = encryptionAlgo;
        logBasic = new LogBasic(encryptionAlgo.getClass());
    }

    public void encrypt(final String originalPath, final String outputPath, final T keyPath) {
        LogEncrypt logEncrypt = new LogEncrypt(logBasic);
        new LogStart(logEncrypt).writeMessage("");
        try {
            encryptionAlgo.encrypt(originalPath, outputPath, keyPath);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        new LogEnd(logEncrypt).writeMessage("");

    }

    public void decrypt(final String originalPath, final String outputPath, final T keyPath){
        LogDecrypt logDecrypt = new LogDecrypt(logBasic);
        new LogStart(logDecrypt).writeMessage("");
        try{
            encryptionAlgo.decrypt(originalPath, outputPath, keyPath);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        new LogEnd(logDecrypt).writeMessage("");
    }
}
