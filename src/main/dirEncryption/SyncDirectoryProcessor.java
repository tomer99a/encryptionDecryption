package dirEncryption;

import encryption.IEncryptionAlgorithm;

import java.io.File;
import java.io.IOException;

public class SyncDirectoryProcessor<T> extends DirectoryProcessorAbstract<T> {

    public SyncDirectoryProcessor(String dirPath) throws IOException {
        super(dirPath);
    }

    @Override
    public void encryptDir(IEncryptionAlgorithm<T> algo, T key) throws IOException {
        addDirSafe(encryptDir);
        File folder = new File(dirPath);
        File[] listOfFiles = folder.listFiles();

        assert listOfFiles != null;
        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                String fileName = file.getName();
                if (fileName.contains("key")) {
                    continue;
                }
                handelEncrypt(fileName, file, algo, key);
            }
        }
        encryptionEnd.writeMessage("All the files");
    }

    @Override
    public void decryptDir(IEncryptionAlgorithm<T> algo, T key) throws IOException {
        addDirSafe(decryptDir);
        File[] listOfFiles = encryptDir.listFiles();

        assert listOfFiles != null;
        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                String fileName = file.getName();
                if (fileName.contains("key")) {
                    continue;
                }
                String decryptPath = decryptDir.getPath() + File.separator + fileName;
                decryptionStart.writeMessage();
                try {
                    algo.decrypt(file.getPath(), decryptPath, key);
                } catch (IOException e) {
                    System.err.println(e.getMessage() + "\nThe file %s didnt decrypt"); //TODO: change %s
                }
                decryptionEnd.writeMessage();
            }
        }
        decryptionEnd.writeMessage("All the files");
    }
}
