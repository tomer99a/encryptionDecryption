package encryption.generalsAlgo;

import encryption.EncryptionAlgorithmAbstract;
import encryption.charAlgo.CharEncryptionAlgorithmAbstract;
import keys.DoubleKey;
import keys.NormalKey;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class DoubleEncryption<T extends DoubleKey> extends EncryptionAlgorithmAbstract<T> {
    final private CharEncryptionAlgorithmAbstract<NormalKey> algo;
    final private static String ERROR_MESSAGE_KEYS = "You should send two path of key seperated by new line in the double encryption!";

    public DoubleEncryption(CharEncryptionAlgorithmAbstract<NormalKey> algo) {
        super("Double" + algo.getEncryptionMethod());
        this.algo = algo;
    }

    @Override
    public void encrypt(final String originalPath, final String outputPath, final T keyPath) throws IOException {
        String[] keys = keyPath.getKey().split("\n");
        if (keys.length != 2) {
            System.err.println(ERROR_MESSAGE_KEYS);
            return;
        }

        final String keyPath1 = keys[0];
        final String keyPath2 = keys[1];

        // Create a temporary file
        final String tmpPath = Files.createTempFile("firstOutputEncrypt", ".txt").toString();

        algo.encrypt(originalPath, tmpPath, new NormalKey(keyPath1));
        algo.encrypt(tmpPath, outputPath, new NormalKey(keyPath2));
        
        if (!(new File(tmpPath).delete())) {
            System.err.println("The tmp file didn't auto delete");
        }
    }

    @Override
    public void decrypt(final String originalPath, final String outputPath, final T keyPath) throws IOException {
        String[] keys = keyPath.getKey().split("\n");
        if (keys.length != 2) {
            System.err.println(ERROR_MESSAGE_KEYS);
            return;
        }

        final String keyPath1 = keys[0];
        final String keyPath2 = keys[1];

        // Create a temporary file
        final String tmpPath = Files.createTempFile("firstOutputDecrypt", ".txt").toString();

        algo.decrypt(originalPath, tmpPath, new NormalKey(keyPath2));
        algo.decrypt(tmpPath, outputPath, new NormalKey(keyPath1));

        if (!(new File(tmpPath).delete())) {
            System.err.println("The tmp file didn't auto delete");
        }
    }
}
