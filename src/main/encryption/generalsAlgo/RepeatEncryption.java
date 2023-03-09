package encryption.generalsAlgo;

import encryption.EncryptionAlgorithmAbstract;
import encryption.charAlgo.CharEncryptionAlgorithmAbstract;
import encryption.charAlgo.ShiftUpEncryption;
import keys.NormalKey;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static utils.IOMethods.copyFile;


public class RepeatEncryption extends EncryptionAlgorithmAbstract<NormalKey> {
    final private CharEncryptionAlgorithmAbstract algo;
    final private int repeatNum;

    public RepeatEncryption(int n, CharEncryptionAlgorithmAbstract algo) {
        super("Repeat" + algo.getEncryptionMethod());
        this.repeatNum = n;
        if (algo.getEncryptionMethod().equals("ShiftUp")) {
            int numberLetters = (int) 'Z' - 'A' + 1;
            while (((repeatNum * algo.getKey()) % numberLetters == 0))
                algo = new ShiftUpEncryption();
        }
        this.algo = algo;
    }

    @Override
    public void actualEncrypt(final String originalPath, final String outputPath, final NormalKey keyPath) throws IOException {
        // Create a temporary file
        final String tmpPath = Files.createTempFile("RepeatTmp", ".txt").toString();

        algo.encrypt(originalPath, tmpPath, keyPath);
        for (int i = 1; i < repeatNum; i++) {
            algo.encrypt(tmpPath, outputPath, keyPath);
            copyFile(outputPath, tmpPath);
        }

        if (!(new File(tmpPath)).delete())
            System.err.println("The tmp file didn't auto delete");
    }

    @Override
    public void actualDecrypt(final String originalPath, final String outputPath, final NormalKey keyPath) throws IOException {
        // Create a temporary file
        final String tmpPath = Files.createTempFile("RepeatTmp", ".txt").toString();

        algo.decrypt(originalPath, tmpPath, keyPath);
        for (int i = 1; i < repeatNum; i++) {
            algo.decrypt(tmpPath, outputPath, keyPath);
            copyFile(outputPath, tmpPath);
        }

        if (!(new File(tmpPath)).delete())
            System.err.println("The tmp file didn't auto delete");
    }
}
