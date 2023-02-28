package encryption.generalsAlgo;

import encryption.charAlgo.ShiftUpEncryption;
import encryption.EncryptionAlgorithmAbstract;
import encryption.charAlgo.CharEncryptionAlgorithmAbstract;
import keys.IKey;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static utils.IOMethods.copyFile;


public class RepeatEncryption<K extends IKey> extends EncryptionAlgorithmAbstract<K> {
    final private CharEncryptionAlgorithmAbstract<K> algo;
    final private int repeatNum;

    public RepeatEncryption(int n, CharEncryptionAlgorithmAbstract<K> algo) {
        super("Repeat" + algo.getEncryptionMethod());
        this.repeatNum = n;
        if (algo.getEncryptionMethod().equals("ShiftUp")) {
            int numberLetters = 26;
            while (((repeatNum * algo.getKey()) % numberLetters != 0))
                algo = new ShiftUpEncryption();
        }
        this.algo = algo;
    }

    @Override
    public void encrypt(final String originalPath, final String outputPath, final K keyPath) throws IOException {
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
    public void decrypt(final String originalPath, final String outputPath, final K keyPath) throws IOException {
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
