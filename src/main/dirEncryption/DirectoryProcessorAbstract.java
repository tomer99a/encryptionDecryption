package dirEncryption;

import encryption.IEncryptionAlgorithm;
import exceptions.invalidPathException;
import log.HandlerEvent;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public abstract class DirectoryProcessorAbstract<T> implements IDirectoryProcessor<T> {
    protected final String dirPath;
    protected final File encryptDir;
    protected final File decryptDir;
    protected long startTimeMillis;

    public DirectoryProcessorAbstract(String dirPath) throws IOException {
        // make sour that we get a dir path and save it
        File dirFile = new File(dirPath);
        if (!dirFile.isDirectory()) {
            throw new invalidPathException("The path given isn't a directory");
        }
        this.dirPath = dirPath;

        encryptDir = new File(dirPath, "encrypted");
        decryptDir = new File(dirPath, "decrypted");
    }

    protected void calculateTime(String action) {
        long end = System.currentTimeMillis();
        float msec = end - startTimeMillis;
        // converting it into seconds
        float sec= msec/1000F;
        // converting it into minutes
        float minutes=sec/60F;
        System.out.printf("The %s action took %.3f minutes (%.0f seconds)%n", action, minutes, sec);
        System.out.println();

    }

    protected void addDirSafe(File file) throws IOException {
        if (file.exists()) {
            for (String s : Objects.requireNonNull(file.list())) {
                boolean didDelete = new File(file.getPath(), s).delete();
            }
        } else if (!file.mkdir()) {
            throw new IOException();
        }
    }

    protected void handelEncrypt(String fileName, File file, IEncryptionAlgorithm<T> algo, T key) {
        HandlerEvent handlerEvent = new HandlerEvent(algo.getClass());
        handlerEvent.encrypt(true, true);
        String encryptPath = encryptDir.getPath() + File.separator + fileName;
        try {
            algo.encrypt(file.getPath(), encryptPath, key);
        } catch (IOException e) {
            System.err.println(e.getMessage() + "\nThe file " + fileName + " didn't encrypt");
        }
        handlerEvent.encrypt(false, true);
    }

    protected void handelDecrypt(String fileName, File file, IEncryptionAlgorithm<T> algo, T key) {
        HandlerEvent handlerEvent = new HandlerEvent(algo.getClass());
        handlerEvent.decrypt(true, true);
        String decryptPath = decryptDir.getPath() + File.separator + fileName;
        try {
            algo.decrypt(file.getPath(), decryptPath, key);
        } catch (IOException e) {
            System.err.println(e.getMessage() + "\nThe file " + fileName + " didn't decrypt");
        }
        handlerEvent.decrypt(false, true);
    }
}
