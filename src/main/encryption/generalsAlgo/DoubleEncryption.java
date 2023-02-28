package encryption.generalsAlgo;

import encryption.EncryptionAlgorithmAbstract;
import encryption.charAlgo.CharEncryptionAlgorithmAbstract;
import keys.IKey;
import keys.NormalKey;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class DoubleEncryption<K extends IKey> extends EncryptionAlgorithmAbstract<K> {
    final private CharEncryptionAlgorithmAbstract<IKey> algo;

    public DoubleEncryption(CharEncryptionAlgorithmAbstract<IKey> algo) {
        super("Double" + algo.getEncryptionMethod());
        this.algo = algo;
    }

    /**
     * Add suffix only to the file name from the full path
     * @param path original path
     * @param suffix thing to add at the end of the file name
     * @return path with changed name
     */
    private String addSuffixToFileNameAtPath(final String path, final String suffix) {
        File file = new File(path);
        String fileName = file.getName();
        return file.getParent() + File.separator + fileName.substring(0, fileName.lastIndexOf(".")) + suffix + fileName.substring(fileName.lastIndexOf("."));
    }

    @Override
    public void encrypt(final String originalPath, final String outputPath, final K keyPath) throws IOException {

        final String keyPath1 = addSuffixToFileNameAtPath(keyPath.getKey(), "1");
        final String keyPath2 = addSuffixToFileNameAtPath(keyPath.getKey(), "2");

        // Create a temporary file
        final String tmpPath = Files.createTempFile("firstOutputEncrypt", ".txt").toString();

        algo.encrypt(originalPath, tmpPath, new NormalKey(keyPath1));
        algo.encrypt(tmpPath, outputPath, new NormalKey(keyPath2));
        
        if (!(new File(tmpPath).delete())) {
            System.err.println("The tmp file didn't auto delete");
        }
    }

    @Override
    public void decrypt(final String originalPath, final String outputPath, final K keyPath) throws IOException {
        final String keyPath1 = addSuffixToFileNameAtPath(keyPath.toString(), "1");
        final String keyPath2 = addSuffixToFileNameAtPath(keyPath.toString(), "2");

        // Create a temporary file
        final String tmpPath = Files.createTempFile("firstOutputDecrypt", ".txt").toString();

        algo.decrypt(originalPath, tmpPath, new NormalKey(keyPath2));
        algo.decrypt(tmpPath, outputPath, new NormalKey(keyPath1));

        if (!(new File(tmpPath).delete())) {
            System.err.println("The tmp file didn't auto delete");
        }
    }
}
