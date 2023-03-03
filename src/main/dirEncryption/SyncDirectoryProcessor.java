package dirEncryption;

import encryption.IEncryptionAlgorithm;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class SyncDirectoryProcessor<T> extends DirectoryProcessorAbstract<T> {
    private final File encryptDir;

    public SyncDirectoryProcessor(String dirPath) throws IOException {
        super(dirPath);
        encryptDir = new File(dirPath + File.separator + "encrypted");
        if (encryptDir.exists()) {
            for(String s: Objects.requireNonNull(encryptDir.list())){
                boolean didDelete = new File(encryptDir.getPath(),s).delete();
            }
        } else {
            if (!encryptDir.mkdir()) {
                throw new IOException();
            }
        }
    }

    @Override
    public void encryptDir(IEncryptionAlgorithm<T> algo, T key) {
        File folder = new File(dirPath);
        File[] listOfFiles = folder.listFiles();

        assert listOfFiles != null;
        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                String fileName = file.getName();
                if (fileName.contains("key")) {
                    continue;
                }
                String encryptPath = encryptDir.getPath() + File.separator + fileName;
                try {
                    algo.encrypt(file.getPath(), encryptPath, key);
                } catch (IOException e) {
                    System.err.println(e.getMessage() + "\nThe file %s didnt encrypt"); //TODO: change %s
                }
            }
        }
    }
}
