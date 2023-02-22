package Encryption.CharAlgo;

import Encryption.EncryptionAlgorithmAbstract;
import Exceptions.InvalidEncryptionKeyException;

import java.io.IOException;

import static Utils.IOMethods.*;

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
        final int decryptKey;
        try {
            decryptKey = getKeyFromFile(keyPath);
        } catch (InvalidEncryptionKeyException e) {
            System.err.println(e.getMessage());
            return;
        }

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
        try{
            String keyStr = readFile(keyPath);
            if(keyStr.indexOf('\n') != -1)
                keyStr = keyStr.substring(0, keyStr.indexOf('\n'));
            return Integer.parseInt(keyStr);
        } catch (NumberFormatException e) {
            throw new InvalidEncryptionKeyException("The key file doesn't contain number");
        }
    }

    /**
     * describe the maximal length (number of digits) of the key.
     * @return the maximal length of the key.
     */
    public int getKeyStrength() {
        return 3;
    }
}
