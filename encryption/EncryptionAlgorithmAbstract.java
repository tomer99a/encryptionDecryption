package encryptionDecryption.encryption;

import java.io.IOException;
import java.util.Random;

import static encryptionDecryption.utils.IOMethods.createFile;
import static encryptionDecryption.utils.IOMethods.scanAndSubmitFile;
import static encryptionDecryption.utils.IOMethods.writeToFile;

public abstract class EncryptionAlgorithmAbstract implements EncryptionAlgorithmInterface{
    protected String encryptionMethod;

    public EncryptionAlgorithmAbstract(String encryptionMethod) {
        this.encryptionMethod = encryptionMethod;
    }

    public String getEncryptionMethod() {
        return encryptionMethod;
    }

    @Override
    public void act(String originalPath, String outputPath, String keyPath) throws IOException {
        final int key = this.generateKey();

        createFile(keyPath);
        createFile(outputPath);

        scanAndSubmitFile(originalPath, outputPath, this, key);
        writeToFile(keyPath, Integer.toString(key));

        System.out.printf("Location of the files are -\nencrypted - %s\nkey - %s\n", outputPath, keyPath);
    }

    @Override
    public int generateKey() {
        return new Random().nextInt(1000) + 3;
    }

    @Override
    public char handleCher(char c, int key) {
        return c;
    }
}
