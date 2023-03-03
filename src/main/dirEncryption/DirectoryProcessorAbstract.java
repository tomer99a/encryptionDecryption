package dirEncryption;

import exceptions.invalidPathException;

import java.io.File;

public abstract class DirectoryProcessorAbstract<T> implements IDirectoryProcessor<T> {
    protected final String dirPath;

    public DirectoryProcessorAbstract(String dirPath) throws invalidPathException {
        File dirFile = new File(dirPath);
        if (!dirFile.isDirectory()) {
           throw new invalidPathException("The path givven isnt a directory");
        }
        this.dirPath = dirPath;
    }
}
