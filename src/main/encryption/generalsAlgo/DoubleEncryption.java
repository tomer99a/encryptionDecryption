package encryption.generalsAlgo;

import encryption.EncryptionAlgorithmAbstract;
import encryption.charAlgo.CharEncryptionAlgorithmAbstract;
import encryption.charAlgo.ShiftUpEncryption;
import keys.DoubleKey;
import keys.NormalKey;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class DoubleEncryption<T extends DoubleKey> extends EncryptionAlgorithmAbstract<T> {
    final private CharEncryptionAlgorithmAbstract<NormalKey> algo;

    public DoubleEncryption(CharEncryptionAlgorithmAbstract<NormalKey> algo) {
        super("Double" + algo.getEncryptionMethod());
        if (algo.getEncryptionMethod().equals("ShiftUp")) {
            int halfNumberLetters = ((int) 'Z' - 'A' + 1) / 2;
            while ((algo.getKey() % halfNumberLetters == 0))
                algo = new ShiftUpEncryption<>();
        }
        this.algo = algo;
    }

    @Override
    public void encrypt(final String originalPath, final String outputPath, final T keyPath) throws IOException {
        // Create a temporary file
        final String tmpPath = Files.createTempFile("firstOutputEncrypt", ".txt").toString();

        algo.encrypt(originalPath, tmpPath, new NormalKey(keyPath.getKey1()));
        algo.encrypt(tmpPath, outputPath, new NormalKey(keyPath.getKey2()));
        
        if (!(new File(tmpPath).delete())) {
            System.err.println("The tmp file didn't auto delete");
        }
    }

    @Override
    public void decrypt(final String originalPath, final String outputPath, final T keyPath) throws IOException {
        // Create a temporary file
        final String tmpPath = Files.createTempFile("firstOutputDecrypt", ".txt").toString();

        algo.decrypt(originalPath, tmpPath, new NormalKey(keyPath.getKey2()));
        algo.decrypt(tmpPath, outputPath, new NormalKey(keyPath.getKey1()));

        if (!(new File(tmpPath).delete())) {
            System.err.println("The tmp file didn't auto delete");
        }
    }
}
