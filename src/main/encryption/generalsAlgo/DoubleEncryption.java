package encryption.generalsAlgo;

import encryption.EncryptionAlgorithmAbstract;
import encryption.charAlgo.CharEncryptionAlgorithmAbstract;
import encryption.charAlgo.ShiftUpEncryption;
import keys.DoubleKey;
import keys.NormalKey;
import log.ErrorLog4jLogger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

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
    public void actualEncrypt(final String originalPath, final String outputPath, final DoubleKey keyPath) throws IOException {
        // Create a temporary file
        final String tmpPath = Files.createTempFile("firstOutputEncrypt", ".txt").toString();

        algo.encrypt(originalPath, tmpPath, new NormalKey(keyPath.getKey1()));
        algo.encrypt(tmpPath, outputPath, new NormalKey(keyPath.getKey2()));

        if (!(new File(tmpPath).delete())) {
            ErrorLog4jLogger.writeErrorToLog(this.getClass(), "The tmp file didn't auto delete");
        }
    }

    @Override
    public void actualDecrypt(final String originalPath, final String outputPath, final DoubleKey keyPath) throws IOException {
        // Create a temporary file
        final String tmpPath = Files.createTempFile("firstOutputDecrypt", ".txt").toString();

        algo.decrypt(originalPath, tmpPath, new NormalKey(keyPath.getKey2()));
        algo.decrypt(tmpPath, outputPath, new NormalKey(keyPath.getKey1()));

        if (!(new File(tmpPath).delete())) {
            ErrorLog4jLogger.writeErrorToLog(this.getClass(), "The tmp file didn't auto delete");
        }
    }
}
