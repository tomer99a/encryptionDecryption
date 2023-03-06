package dirEncryption;

import encryption.IEncryptionAlgorithm;

import java.io.File;
import java.io.IOException;

public class AsyncDirectoryProcessor<T> extends DirectoryProcessorAbstract<T> {

    public AsyncDirectoryProcessor(String dirPath) throws IOException {
        super(dirPath);
    }

    @Override
    public void encryptDir(IEncryptionAlgorithm<T> algo, T key) throws IOException {
        addDirSafe(encryptDir);
        File folder = new File(dirPath);
        File[] listOfFiles = folder.listFiles();

        assert listOfFiles != null;
        for (File file : listOfFiles) {

        }
    }

    @Override
    public void decryptDir(IEncryptionAlgorithm<T> algo, T key) throws IOException {

    }
}
