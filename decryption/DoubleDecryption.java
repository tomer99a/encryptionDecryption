package encryptionDecryption.decryption;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static encryptionDecryption.utils.GeneralMethods.addSuffixToFileNameAtPath;

public class DoubleDecryption extends DecryptionAlgorithmAbstract{
    final private DecryptionAlgorithmInterface algo;

    public DoubleDecryption(DecryptionAlgorithmInterface algo) {
        super("Double");
        this.algo = algo;
        String className = algo.getClass().toString();
        className = className.substring(className.lastIndexOf("."), className.lastIndexOf("D"));
        this.decryptionMethod += className;
    }

    @Override
    public void act(String originalPath, String outputPath, String keyPath) throws IOException {
        final String keyPath1 = addSuffixToFileNameAtPath(keyPath, "1");
        final String keyPath2 = addSuffixToFileNameAtPath(keyPath, "2");

        // Create a temporary file
        final String tmpPath = Files.createTempFile("firstOutputDecrypt", ".txt").toString();

        algo.act(originalPath, tmpPath, keyPath2);
        algo.act(tmpPath, outputPath, keyPath1);

        if (!(new File(tmpPath).delete()))
            System.err.println("The tmp file didn't auto delete");
    }
}
