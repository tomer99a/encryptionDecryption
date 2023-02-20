package main.java.encryption;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static main.java.utils.GeneralMethods.addSuffixToFileNameAtPath;

public class DoubleEncryption extends EncryptionAlgorithmAbstract{
    final private EncryptionAlgorithmInterface algo;

    public DoubleEncryption(EncryptionAlgorithmInterface algo) {
        super("Double");
        this.algo = algo;
        String className = algo.getEncryptionMethod();
        this.encryptionMethod += className;
    }

    @Override
    public void encrypt(String originalPath, String outputPath, String keyPath) throws IOException {
        final String keyPath1 = addSuffixToFileNameAtPath(keyPath, "1");
        final String keyPath2 = addSuffixToFileNameAtPath(keyPath, "2");

        // Create a temporary file
        final String tmpPath = Files.createTempFile("firstOutputEncrypt", ".txt").toString();

        algo.encrypt(originalPath, tmpPath, keyPath1);
        algo.encrypt(tmpPath, outputPath, keyPath2);
        
        if(!(new File(tmpPath).delete()))
            System.err.println("The tmp file didn't auto delete");
    }

    @Override
    public void decrypt(String originalPath, String outputPath, String keyPath) throws IOException {
        final String keyPath1 = addSuffixToFileNameAtPath(keyPath, "1");
        final String keyPath2 = addSuffixToFileNameAtPath(keyPath, "2");

        // Create a temporary file
        final String tmpPath = Files.createTempFile("firstOutputDecrypt", ".txt").toString();

        algo.decrypt(originalPath, tmpPath, keyPath2);
        algo.decrypt(tmpPath, outputPath, keyPath1);

        if(!(new File(tmpPath).delete()))
            System.err.println("The tmp file didn't auto delete");
    }
}
