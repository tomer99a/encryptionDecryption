package encryptionDecryption.encryption;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static encryptionDecryption.utils.GeneralMethods.addSuffixToFileNameAtPath;

public class DoubleEncryption extends EncryptionAlgorithmAbstract{
    final private EncryptionAlgorithmInterface algo;

    public DoubleEncryption(EncryptionAlgorithmInterface algo) {
        super("Double");
        this.algo = algo;
        String className = algo.getClass().toString();
        className = className.substring(className.lastIndexOf("."), className.lastIndexOf("E"));
        this.encryptionMethod += className;
    }

    @Override
    public void act(String originalPath, String outputPath, String keyPath) throws IOException {
        String keyPath1 = addSuffixToFileNameAtPath(keyPath, "1");
        String keyPath2 = addSuffixToFileNameAtPath(keyPath, "2");

        // Create a temporary file
        String tmpPath = Files.createTempFile("firstOutputEncrypt", ".txt").toString();

        algo.act(originalPath, tmpPath, keyPath1);
        algo.act(tmpPath, outputPath, keyPath2);
        
        if (!(new File(tmpPath).delete()))
            System.err.println("The tmp file didn't auto delete");
    }

    //TODO delete or move up this function!!!
    @Override
    public char handleCher(char c, int key) {
        return 'T';
    }
}
