package Encryption.CharAlgo;

import Encryption.EncryptionAlgorithmAbstract;
import Exceptions.InvalidEncryptionKeyException;

import java.io.IOException;

import static Utils.IOMethods.*;

public abstract class CharEncryptionAlgorithmAbstract extends EncryptionAlgorithmAbstract {
    protected int key;
    protected int keyMaxRange;

    final static protected char SMALL_A = 'a';
    final static protected char SMALL_Z = 'z';
    final static protected char BIG_A = 'A';
    final static protected char BIG_Z = 'Z';
    final static protected int BOUND_RANDOM_NUMBER = 1000;

    public CharEncryptionAlgorithmAbstract(String encryptionMethod) {
        super(encryptionMethod);
        setKeyMaxRange();
        generateKey();
    }

    abstract public char encryptChar(char c, int key);
    abstract public char decryptChar(char c, int key);
    abstract protected void generateKey();
    /**
     * describe the maximal length (number of digits) of the key.
     * @return the maximal length of the key.
     */
    abstract public int getKeyStrength();
    abstract protected void setKeyMaxRange();

    public int getKeyMaxRange() {
        return keyMaxRange;
    }

    public void encrypt(String originalPath, String outputPath, String keyPath) throws IOException {
        createFile(keyPath);
        createFile(outputPath);

        scanAndSubmitFile(true, originalPath, outputPath, this, key);
        writeToFile(keyPath, Integer.toString(key));

        System.out.printf("Location of the files are -\nencrypted - %s\nkey - %s%s", outputPath, keyPath, System.lineSeparator());
    }

    public void decrypt(String originalPath, String outputPath, String keyPath) throws IOException, InvalidEncryptionKeyException {
        final int decryptKey;
        decryptKey = getKeyFromFile(keyPath);

        createFile(outputPath);
        scanAndSubmitFile(false, originalPath, outputPath, this, decryptKey);
        System.out.println("Location of the decrypted file is - " + outputPath);
    }

    /**
     * Extract key value from file
     * @param keyPath the path to the file key
     * @return key value
     */
    private int getKeyFromFile(String keyPath) throws IOException, InvalidEncryptionKeyException {
        String keyStr = readFile(keyPath);
        if(keyStr.indexOf('\n') != -1)
            keyStr = keyStr.substring(0, keyStr.indexOf(System.lineSeparator()));
        int myKey;
        try{
            myKey = Integer.parseInt(keyStr);
        } catch (NumberFormatException e) {
            throw new InvalidEncryptionKeyException("The key file doesn't contain number");
        }
        if (0 > myKey || myKey >= this.getKeyMaxRange())
            throw new InvalidEncryptionKeyException("The key not in the correct range that suppose to be");
        return myKey;
    }
}
