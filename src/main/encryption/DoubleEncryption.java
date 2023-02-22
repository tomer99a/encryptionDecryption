package encryption;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static utils.GeneralMethods.addSuffixToFileNameAtPath;

public class DoubleEncryption extends EncryptionAlgorithmAbstract{
    final private CharEncryptionAlgorithmInterface algo;

    public DoubleEncryption(CharEncryptionAlgorithmInterface algo) {
        super("Double" + algo.getEncryptionMethod());
        this.algo = algo;
    }

    @Override
    public void encrypt(Path originalPath, Path outputPath, Path keyPath) throws IOException {
        final Path keyPath1 = Paths.get(addSuffixToFileNameAtPath(keyPath, "1"));
        final Path keyPath2 = Paths.get(addSuffixToFileNameAtPath(keyPath, "2"));

        // Create a temporary file
        final Path tmpPath = Paths.get(Files.createTempFile("firstOutputEncrypt", ".txt").toString());

        algo.encrypt(originalPath, tmpPath, keyPath1);
        algo.encrypt(tmpPath, outputPath, keyPath2);
        
        if(!(new File(String.valueOf(tmpPath)).delete()))
            System.err.println("The tmp file didn't auto delete");
    }

    @Override
    public void decrypt(Path originalPath, Path outputPath, Path keyPath) throws IOException {
        final Path keyPath1 = Paths.get(addSuffixToFileNameAtPath(keyPath, "1"));
        final Path keyPath2 = Paths.get(addSuffixToFileNameAtPath(keyPath, "2"));

        // Create a temporary file
        final Path tmpPath = Paths.get(Files.createTempFile("firstOutputDecrypt", ".txt").toString());

        algo.decrypt(originalPath, tmpPath, keyPath2);
        algo.decrypt(tmpPath, outputPath, keyPath1);

        if(!(new File(String.valueOf(tmpPath)).delete()))
            System.err.println("The tmp file didn't auto delete");
    }
}
