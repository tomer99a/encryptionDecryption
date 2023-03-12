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
        help(algo, key, new File(dirPath), encryptDir, true);

        new EventHandler(algo.getClass(), "").encrypt(false);
    }

    @Override
    public final void decryptDir(IEncryptionAlgorithm<T> algo, T key) throws IOException {
        help(algo, key, encryptDir, decryptDir, false);

        new EventHandler(algo.getClass(), "").decrypt(false);
    }

    private void help(IEncryptionAlgorithm<T> algo, T key, File inputFolder, File outputFolder, boolean isEncrypt) throws IOException {
        addDirSafe(outputFolder);
        File[] listOfFiles = inputFolder.listFiles();
        assert listOfFiles != null;
        startTimeMillis = System.currentTimeMillis();

        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                String fileName = file.getName();
                if (fileName.contains("key")) {
                    continue;
                }
                useAlgo(algo, key, fileName, outputFolder, file, isEncrypt);
            }
        }
        calculateTime(isEncrypt ? "encrypt" : "decrypt");
    }
}
