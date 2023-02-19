package encryptionDecryption.encryption;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static encryptionDecryption.utils.IOMethods.copyFile;

public class RepeatEncryption extends EncryptionAlgorithmAbstract{
    final private EncryptionAlgorithmInterface algo;
    final private int repeatNum;

    public RepeatEncryption(int n, EncryptionAlgorithmInterface algo) {
        super("Repeat");
        this.repeatNum = n;
        this.algo = algo;
        String className = algo.getClass().toString();
        className = className.substring(className.lastIndexOf("."), className.lastIndexOf("E"));
        this.encryptionMethod += className;
    }

    @Override
    public void act(String originalPath, String outputPath, String keyPath) throws IOException {

        // Create a temporary file
        final String tmpPath = Files.createTempFile("EncryptTmp", ".txt").toString();

        algo.act(originalPath, tmpPath, keyPath);
        for(int i=1; i < repeatNum; i++){
            algo.act(tmpPath, outputPath, keyPath);
            copyFile(outputPath, tmpPath);
        }

        if (!(new File(tmpPath).delete()))
            System.err.println("The tmp file didn't auto delete");
    }
}
