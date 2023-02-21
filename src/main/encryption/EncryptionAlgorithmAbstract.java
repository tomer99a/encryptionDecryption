package encryption;

import java.io.IOException;
import java.security.SecureRandom;
import java.nio.file.Path;

import static utils.GeneralMethods.getKeyFromFile;
import static utils.IOMethods.createFile;
import static utils.IOMethods.scanAndSubmitFile;
import static utils.IOMethods.writeToFile;

public abstract class EncryptionAlgorithmAbstract implements EncryptionAlgorithmInterface{
    protected String encryptionMethod;
    protected int key = 742;

    final static protected char SMALL_A = 'a';
    final static protected char SMALL_Z = 'z';
    final static protected char BIG_A = 'A';
    final static protected char BIG_Z = 'Z';

    public EncryptionAlgorithmAbstract(String encryptionMethod) {
        this.encryptionMethod = encryptionMethod;
        generateKey();
    }

    public String getEncryptionMethod() {
        return encryptionMethod;
    }

    public void encrypt(Path originalPath, Path outputPath, Path keyPath) throws IOException {
        createFile(keyPath);
        createFile(outputPath);

        scanAndSubmitFile(true, originalPath, outputPath, this, key);
        writeToFile(keyPath, Integer.toString(key));

        System.out.printf("Location of the files are -\nencrypted - %s\nkey - %s\n", outputPath, keyPath);
    }

    public void decrypt(Path originalPath, Path outputPath, Path keyPath) throws IOException {
        final int key = getKeyFromFile(keyPath);

        createFile(outputPath);
        scanAndSubmitFile(false, originalPath, outputPath, this, key);
        System.out.println("Location of the decrypted file is - " + outputPath);
    }

    public void generateKey() {
        // I don't want that the random number will be 0/1 because it will destroy the mul and add
        key = new SecureRandom().nextInt(1000) + 2;
    }

    public char encryptChar(char c, int key) {
        return c;
    }

    public char decryptChar(char c, int key) {
        return c;
    }
}
