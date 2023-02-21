package java.encryption;

import java.io.IOException;
import java.security.SecureRandom;

import static java.utils.GeneralMethods.getKeyFromFile;
import static java.utils.IOMethods.createFile;
import static java.utils.IOMethods.scanAndSubmitFile;
import static java.utils.IOMethods.writeToFile;

public abstract class EncryptionAlgorithmAbstract implements EncryptionAlgorithmInterface{
    protected String encryptionMethod;
    protected int key;

    final protected char SMALL_A = 'a';
    final protected char SMALL_Z = 'z';
    final protected char BIG_A = 'A';
    final protected char BIG_Z = 'Z';

    public EncryptionAlgorithmAbstract(String encryptionMethod) {
        this.encryptionMethod = encryptionMethod;
        generateKey();
    }

    public String getEncryptionMethod() {
        return encryptionMethod;
    }

    public void encrypt(String originalPath, String outputPath, String keyPath) throws IOException {
        createFile(keyPath);
        createFile(outputPath);

        scanAndSubmitFile(true, originalPath, outputPath, this, key);
        writeToFile(keyPath, Integer.toString(key));

        System.out.printf("Location of the files are -\nencrypted - %s\nkey - %s\n", outputPath, keyPath);
    }

    public void decrypt(String originalPath, String outputPath, String keyPath) throws IOException {
        final int KEY = getKeyFromFile(keyPath);

        createFile(outputPath);
        scanAndSubmitFile(false, originalPath, outputPath, this, KEY);
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
