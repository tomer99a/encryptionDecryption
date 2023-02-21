package encryption;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static utils.GeneralMethods.addSuffixToFileNameAtPath;

public class DoubleEncryption extends EncryptionAlgorithmAbstract{
    final private EncryptionAlgorithmInterface ALGO;

    public DoubleEncryption(EncryptionAlgorithmInterface algo) {
        super("Double");
        this.ALGO = algo;
        this.encryptionMethod += algo.getEncryptionMethod();
    }

    @Override
    public void encrypt(Path originalPath, Path outputPath, Path keyPath) throws IOException {
        final Path KEY_PATH1 = Paths.get(addSuffixToFileNameAtPath(keyPath, "1"));
        final Path KEY_PATH2 = Paths.get(addSuffixToFileNameAtPath(keyPath, "2"));

        // Create a temporary file
        final Path TMP_PATH = Paths.get(Files.createTempFile("firstOutputEncrypt", ".txt").toString());

        ALGO.encrypt(originalPath, TMP_PATH, KEY_PATH1);
        ALGO.encrypt(TMP_PATH, outputPath, KEY_PATH2);
        
        if(!(new File(String.valueOf(TMP_PATH)).delete()))
            System.err.println("The tmp file didn't auto delete");
    }

    @Override
    public void decrypt(Path originalPath, Path outputPath, Path keyPath) throws IOException {
        final Path KEY_PATH1 = Paths.get(addSuffixToFileNameAtPath(keyPath, "1"));
        final Path KEY_PATH2 = Paths.get(addSuffixToFileNameAtPath(keyPath, "2"));

        // Create a temporary file
        final Path TMP_PATH = Paths.get(Files.createTempFile("firstOutputDecrypt", ".txt").toString());

        ALGO.decrypt(originalPath, TMP_PATH, KEY_PATH2);
        ALGO.decrypt(TMP_PATH, outputPath, KEY_PATH1);

        if(!(new File(String.valueOf(TMP_PATH)).delete()))
            System.err.println("The tmp file didn't auto delete");
    }
}
