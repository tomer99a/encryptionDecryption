package java.encryption;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static java.utils.IOMethods.copyFile;

public class RepeatEncryption extends EncryptionAlgorithmAbstract {
    final private EncryptionAlgorithmInterface ALGO;
    final private int REPEAT_NUM;

    public RepeatEncryption(int n, EncryptionAlgorithmInterface algo) {
        super("Repeat");
        this.REPEAT_NUM = n;
        this.ALGO = algo;
        this.encryptionMethod += algo.getEncryptionMethod();
    }

    @Override
    public void encrypt(String originalPath, String outputPath, String keyPath) throws IOException {
        // Create a temporary file
        final String TMP_PATH = Files.createTempFile("RepeatTmp", ".txt").toString();

        ALGO.encrypt(originalPath, TMP_PATH, keyPath);
        for(int i=1; i < REPEAT_NUM; i++){
            ALGO.encrypt(TMP_PATH, outputPath, keyPath);
            copyFile(outputPath, TMP_PATH);
        }

        if (!(new File(TMP_PATH).delete()))
            System.err.println("The tmp file didn't auto delete");
    }

    @Override
    public void decrypt(String originalPath, String outputPath, String keyPath) throws IOException {
        // Create a temporary file
        final String TMP_PATH = Files.createTempFile("RepeatTmp", ".txt").toString();

        ALGO.decrypt(originalPath, TMP_PATH, keyPath);
        for(int i=1; i < REPEAT_NUM; i++){
            ALGO.decrypt(TMP_PATH, outputPath, keyPath);
            copyFile(outputPath, TMP_PATH);
        }

        if (!(new File(TMP_PATH).delete()))
            System.err.println("The tmp file didn't auto delete");
    }
}
