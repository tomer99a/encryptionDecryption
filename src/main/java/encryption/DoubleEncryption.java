package java.encryption;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static java.utils.GeneralMethods.addSuffixToFileNameAtPath;

public class DoubleEncryption extends EncryptionAlgorithmAbstract{
    final private EncryptionAlgorithmInterface ALGO;

    public DoubleEncryption(EncryptionAlgorithmInterface algo) {
        super("Double");
        this.ALGO = algo;
        this.encryptionMethod += algo.getEncryptionMethod();
    }

    @Override
    public void encrypt(String originalPath, String outputPath, String keyPath) throws IOException {
        final String KEY_PATH1 = addSuffixToFileNameAtPath(keyPath, "1");
        final String KEY_PATH2 = addSuffixToFileNameAtPath(keyPath, "2");

        // Create a temporary file
        final String TMP_PATH = Files.createTempFile("firstOutputEncrypt", ".txt").toString();

        ALGO.encrypt(originalPath, TMP_PATH, KEY_PATH1);
        ALGO.encrypt(TMP_PATH, outputPath, KEY_PATH2);
        
        if(!(new File(TMP_PATH).delete()))
            System.err.println("The tmp file didn't auto delete");
    }

    @Override
    public void decrypt(String originalPath, String outputPath, String keyPath) throws IOException {
        final String KEY_PATH1 = addSuffixToFileNameAtPath(keyPath, "1");
        final String KEY_PATH2 = addSuffixToFileNameAtPath(keyPath, "2");

        // Create a temporary file
        final String TMP_PATH = Files.createTempFile("firstOutputDecrypt", ".txt").toString();

        ALGO.decrypt(originalPath, TMP_PATH, KEY_PATH2);
        ALGO.decrypt(TMP_PATH, outputPath, KEY_PATH1);

        if(!(new File(TMP_PATH).delete()))
            System.err.println("The tmp file didn't auto delete");
    }
}
