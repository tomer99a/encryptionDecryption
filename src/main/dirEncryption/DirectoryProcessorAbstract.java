package dirEncryption;

import encryption.IEncryptionAlgorithm;
import exceptions.invalidPathException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public abstract class DirectoryProcessorAbstract<T> implements IDirectoryProcessor<T> {
    protected final Logger logger = LogManager.getLogger(this.getClass());
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
        float sec = msec / 1000F;
        // converting it into minutes
        float minutes = sec / 60F;
        logger.info(String.format("The %s action took %.3f minutes (%.0f seconds)%n", action, minutes, sec));
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

    protected void useAlgo(IEncryptionAlgorithm<T> algo, T key, String fileName, File outputFolder, File file, boolean isEncrypt) {
        String outputPath = outputFolder.getPath() + File.separator + fileName;
        try {
            if (outputFolder.getPath().contains("encrypted")) {
                algo.encrypt(file.getPath(), outputPath, key);
            } else {
                algo.decrypt(file.getPath(), outputPath, key);
            }
        } catch (IOException e) {
            String action = isEncrypt ? "encryption" : "decryption";
            logger.error(e.getMessage() + "\nThe " + action + " on " + fileName + "didn't work");
        }
    }
}
