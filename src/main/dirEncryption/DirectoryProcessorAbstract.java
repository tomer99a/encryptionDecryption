package dirEncryption;

import exceptions.invalidPathException;
import logs.*;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public abstract class DirectoryProcessorAbstract<T> implements IDirectoryProcessor<T> {
    protected final String dirPath;
    protected final File encryptDir;
    protected final File decryptDir;
    protected final LogStart encryptionStart;
    protected final LogEnd encryptionEnd;
    protected final LogStart decryptionStart;
    protected final LogEnd decryptionEnd;

    public DirectoryProcessorAbstract(String dirPath) throws IOException {
        // make sour that we get a dir path and save it
        File dirFile = new File(dirPath);
        if (!dirFile.isDirectory()) {
           throw new invalidPathException("The path given isn't a directory");
        }
        this.dirPath = dirPath;

        encryptDir = new File(dirPath, "encrypted");
        decryptDir = new File(dirPath, "decrypted");

        // save logs to help.
        LogBasic logBasic = new LogBasic(this.getClass());
        LogEncrypt logEncrypt = new LogEncrypt(logBasic);
        LogDecrypt logDecrypt = new LogDecrypt(logBasic);

        encryptionStart = new LogStart(logEncrypt);
        encryptionEnd = new LogEnd(logEncrypt);
        decryptionStart = new LogStart(logDecrypt);
        decryptionEnd = new LogEnd(logDecrypt);
    }

    protected void addDirSafe(File file) throws IOException {
        if (file.exists()) {
            for (String s: Objects.requireNonNull(file.list())) {
                boolean didDelete = new File(file.getPath(), s).delete();
            }
        } else if (!file.mkdir()) {
            throw new IOException();
        }
    }
}
