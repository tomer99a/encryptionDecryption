package main.java.encryption;

import java.io.IOException;
import java.util.Random;

import static main.java.utils.GeneralMethods.getKeyFromFile;
import static main.java.utils.IOMethods.createFile;
import static main.java.utils.IOMethods.scanAndSubmitFile;
import static main.java.utils.IOMethods.writeToFile;

public abstract class EncryptionAlgorithmAbstract implements EncryptionAlgorithmInterface{
    protected String encryptionMethod;
    protected int key;

    public EncryptionAlgorithmAbstract() {
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
        key = new Random().nextInt(1000) + 2;
    }

    public char encryptChar(char c, int key) {
        return c;
    }

    public char decryptChar(char c, int key) {
        return c;
    }
}
