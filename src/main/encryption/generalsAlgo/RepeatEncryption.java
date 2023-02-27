package encryption.generalsAlgo;

import encryption.charAlgo.ShiftUpEncryption;
import encryption.EncryptionAlgorithmAbstract;
import encryption.charAlgo.CharEncryptionAlgorithmAbstract;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static utils.IOMethods.copyFile;


public class RepeatEncryption extends EncryptionAlgorithmAbstract {
    final private CharEncryptionAlgorithmAbstract algo;
    final private int repeatNum;

    public RepeatEncryption(int n, CharEncryptionAlgorithmAbstract algo) {
        super("Repeat" + algo.getEncryptionMethod());
        this.repeatNum = n;
        this.algo = algo;
        if (algo.getEncryptionMethod().equals("ShiftUp")) {
            int numberLetters = 26;
            while (((repeatNum * algo.getKey()) % numberLetters != 0))
                algo = new ShiftUpEncryption();
        }
    }

    @Override
    public void encrypt(final String originalPath, final String outputPath, final String keyPath) throws IOException {
        // Create a temporary file
        final String tmpPath = Files.createTempFile("RepeatTmp", ".txt").toString();

        algo.encrypt(originalPath, tmpPath, keyPath);
        for(int i=1; i < repeatNum; i++){
            algo.encrypt(tmpPath, outputPath, keyPath);
            copyFile(outputPath, tmpPath);
        }

        if (!(new File(tmpPath)).delete())
            System.err.println("The tmp file didn't auto delete");
    }

    @Override
    public void decrypt(final String originalPath, final String outputPath, final String keyPath) throws IOException {
        // Create a temporary file
        final String tmpPath = Files.createTempFile("RepeatTmp", ".txt").toString();

        algo.decrypt(originalPath, tmpPath, keyPath);
        for(int i=1; i < repeatNum; i++){
            algo.decrypt(tmpPath, outputPath, keyPath);
            copyFile(outputPath, tmpPath);
        }

        if (!(new File(tmpPath)).delete())
            System.err.println("The tmp file didn't auto delete");
    }
}
