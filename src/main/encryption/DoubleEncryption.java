package encryption;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class DoubleEncryption extends EncryptionAlgorithmAbstract{
    final private CharEncryptionAlgorithmInterface algo;

    public DoubleEncryption(CharEncryptionAlgorithmInterface algo) {
        super("Double" + algo.getEncryptionMethod());
        this.algo = algo;
    }
    /**
     * Add suffix only to the file name from the full path
     * @param path original path
     * @param suffix thing to add at the end of the file name
     * @return path with changed name
     */
    private String addSuffixToFileNameAtPath(String path, String suffix){
        File file = new File(path);
        String fileName = file.getName();
        return file.getParent() + File.separator + fileName.substring(0, fileName.lastIndexOf(".")) + suffix + fileName.substring(fileName.lastIndexOf("."));
    }

    @Override
    public void encrypt(String originalPath, String outputPath, String keyPath) throws IOException {
        final String keyPath1 = addSuffixToFileNameAtPath(keyPath, "1");
        final String keyPath2 = addSuffixToFileNameAtPath(keyPath, "2");

        // Create a temporary file
        final String tmpPath = Files.createTempFile("firstOutputEncrypt", ".txt").toString();

        algo.encrypt(originalPath, tmpPath, keyPath1);
        algo.encrypt(tmpPath, outputPath, keyPath2);
        
        if(!(new File(tmpPath).delete()))
            System.err.println("The tmp file didn't auto delete");
    }

    @Override
    public void decrypt(String originalPath, String outputPath, String keyPath) throws IOException {
        final String keyPath1 = addSuffixToFileNameAtPath(keyPath, "1");
        final String keyPath2 = addSuffixToFileNameAtPath(keyPath, "2");

        // Create a temporary file
        final String tmpPath = Files.createTempFile("firstOutputDecrypt", ".txt").toString();

        algo.decrypt(originalPath, tmpPath, keyPath2);
        algo.decrypt(tmpPath, outputPath, keyPath1);

        if(!(new File(tmpPath).delete()))
            System.err.println("The tmp file didn't auto delete");
    }
}
