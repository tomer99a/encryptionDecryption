package dirEncryption;

import exceptions.invalidPathException;
import logs.*;

import java.io.File;

public abstract class DirectoryProcessorAbstract<T> implements IDirectoryProcessor<T> {
    protected final String dirPath;
    protected final LogStart encryptionStart;
    protected final LogEnd encryptionEnd;
    protected final LogStart decryptionStart;
    protected final LogEnd decryptionEnd;

    public DirectoryProcessorAbstract(String dirPath) throws invalidPathException {
        File dirFile = new File(dirPath);
        if (!dirFile.isDirectory()) {
           throw new invalidPathException("The path givven isnt a directory");
        }
        this.dirPath = dirPath;

        LogBasic logBasic = new LogBasic(this.getClass());
        LogEncrypt logEncrypt = new LogEncrypt(logBasic);
        LogDecrypt logDecrypt = new LogDecrypt(logBasic);

        encryptionStart = new LogStart(logEncrypt);
        encryptionEnd = new LogEnd(logEncrypt);
        decryptionStart = new LogStart(logDecrypt);
        decryptionEnd = new LogEnd(logDecrypt);
    }
}
