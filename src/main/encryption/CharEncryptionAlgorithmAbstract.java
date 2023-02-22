package encryption;

import java.io.IOException;

import static utils.GeneralMethods.getKeyFromFile;
import static utils.IOMethods.*;

public abstract class CharEncryptionAlgorithmAbstract extends EncryptionAlgorithmAbstract implements CharEncryptionAlgorithmInterface {
    protected int key;

    final static protected char SMALL_A = 'a';
    final static protected char SMALL_Z = 'z';
    final static protected char BIG_A = 'A';
    final static protected char BIG_Z = 'Z';

    public CharEncryptionAlgorithmAbstract(String encryptionMethod) {
        super(encryptionMethod);
        generateKey();
    }

    public void encrypt(String originalPath, String outputPath, String keyPath) throws IOException {
        createFile(keyPath);
        createFile(outputPath);

        scanAndSubmitFile(true, originalPath, outputPath, this, key);
        writeToFile(keyPath, Integer.toString(key));

        System.out.printf("Location of the files are -\nencrypted - %s\nkey - %s%s", outputPath, keyPath, System.lineSeparator());
    }

    public void decrypt(String originalPath, String outputPath, String keyPath) throws IOException {
        final int key = getKeyFromFile(keyPath);

        createFile(outputPath);
        scanAndSubmitFile(false, originalPath, outputPath, this, key);
        System.out.println("Location of the decrypted file is - " + outputPath);
    }
}
