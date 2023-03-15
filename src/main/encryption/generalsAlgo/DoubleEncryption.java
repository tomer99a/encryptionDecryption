package encryption.generalsAlgo;

import encryption.EncryptionAlgorithmAbstract;
import encryption.charAlgo.CharEncryptionAlgorithmAbstract;
import encryption.charAlgo.ShiftUpEncryption;
import keys.DoubleKey;
import keys.NormalKey;

import java.io.IOException;
import java.nio.file.Files;

import static utils.IOMethods.deleteFile;

public class DoubleEncryption extends EncryptionAlgorithmAbstract<DoubleKey> {
    final private CharEncryptionAlgorithmAbstract algo;

    public DoubleEncryption(CharEncryptionAlgorithmAbstract algo) {
        super("Double" + algo.getEncryptionMethod());
        if (algo.getEncryptionMethod().equals("ShiftUp")) {
            int halfNumberLetters = ((int) 'Z' - 'A' + 1) / 2;
            while ((algo.getKey() % halfNumberLetters == 0))
                algo = new ShiftUpEncryption();
        }
        this.algo = algo;
    }

    @Override
    public void encryption(final String originalPath, final String outputPath, final DoubleKey keyPath) throws IOException {
        // Create a temporary file
        final String tmpPath = Files.createTempFile("firstOutputEncrypt", ".txt").toString();

        algo.encrypt(originalPath, tmpPath, new NormalKey(keyPath.getKey1()));
        algo.encrypt(tmpPath, outputPath, new NormalKey(keyPath.getKey2()));

        deleteFile(tmpPath, logger);
    }

    @Override
    public void decryption(final String originalPath, final String outputPath, final DoubleKey keyPath) throws IOException {
        // Create a temporary file
        final String tmpPath = Files.createTempFile("firstOutputDecrypt", ".txt").toString();

        algo.decrypt(originalPath, tmpPath, new NormalKey(keyPath.getKey2()));
        algo.decrypt(tmpPath, outputPath, new NormalKey(keyPath.getKey1()));

        deleteFile(tmpPath, logger);
    }
}
