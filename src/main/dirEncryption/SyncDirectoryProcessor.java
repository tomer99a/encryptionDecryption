package dirEncryption;

import encryption.IEncryptionAlgorithm;
import handler.EventHandler;

import java.io.File;
import java.io.IOException;

public class SyncDirectoryProcessor<T> extends DirectoryProcessorAbstract<T> {

    public SyncDirectoryProcessor(String dirPath) throws IOException {
        super(dirPath);
    }

    @Override
    public final void encryptDir(IEncryptionAlgorithm<T> algo, T key) throws IOException {
        addDirSafe(encryptDir);
        File folder = new File(dirPath);
        File[] listOfFiles = folder.listFiles();

        assert listOfFiles != null;
        startTimeMillis = System.currentTimeMillis();
        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                String fileName = file.getName();
                if (fileName.contains("key")) {
                    continue;
                }
                handleEncrypt(fileName, file, algo, key);
            }
        }

        new EventHandler(algo.getClass()).encrypt(false, false);
        calculateTime("encrypt");
    }

    @Override
    public final void decryptDir(IEncryptionAlgorithm<T> algo, T key) throws IOException {
        addDirSafe(decryptDir);
        File[] listOfFiles = encryptDir.listFiles();

        assert listOfFiles != null;
        startTimeMillis = System.currentTimeMillis();
        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                String fileName = file.getName();
                if (fileName.contains("key")) {
                    continue;
                }
                handleDecrypt(fileName, file, algo, key);
            }
        }

        new EventHandler(algo.getClass()).decrypt(false, false);
        calculateTime("encrypt");
    }
}
