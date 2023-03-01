package encryption.charAlgo;

import encryption.EncryptionAlgorithmAbstract;
import exceptions.InvalidEncryptionKeyException;

import keys.NormalKey;

import java.io.IOException;

import static utils.IOMethods.scanAndSubmitFile;
import static utils.IOMethods.writeToFile;
import static utils.IOMethods.createFile;
import static utils.IOMethods.readFile;

public abstract class CharEncryptionAlgorithmAbstract<T extends NormalKey> extends EncryptionAlgorithmAbstract<T> {
    protected int key;
    protected int keyMaxRange;

    final static protected char SMALL_A = 'a';
    final static protected char SMALL_Z = 'z';
    final static protected char BIG_A = 'A';
    final static protected char BIG_Z = 'Z';
    final static protected int BOUND_RANDOM_NUMBER = 1000;

    public CharEncryptionAlgorithmAbstract(final String encryptionMethod) {
        super(encryptionMethod);
        setKeyMaxRange();
        generateKey();
    }

    public int getKey() {
        return key;
    }

    abstract public char encryptChar(char c, int key);
    abstract public char decryptChar(char c, int key);
    abstract protected void generateKey();
    abstract protected void setKeyMaxRange();

    /**
     * Get the key strength - maximal length (number of digits) of the encryption methods.
     * @return key strength
     */
    public int getKeyStrength() {
        return Integer.toString(keyMaxRange).length() - 1;
    }

    public int getKeyMaxRange() {
        return keyMaxRange;
    }

    public void encrypt(final String originalPath, final String outputPath, final T keyPath) throws IOException {
        String keyPathStr = keyPath.getKey();
        createFile(keyPathStr);
        createFile(outputPath);

        scanAndSubmitFile(true, originalPath, outputPath, this, key);
        writeToFile(keyPathStr, Integer.toString(key));

        System.out.printf("Location of the files are -\nencrypted - %s\nkey - %s%s", outputPath, keyPathStr, System.lineSeparator());
    }

    public void decrypt(final String originalPath, final String outputPath, final T keyPath) throws IOException, InvalidEncryptionKeyException {
        final int decryptKey;
        decryptKey = getKeyFromFile(keyPath.getKey());

        createFile(outputPath);
        scanAndSubmitFile(false, originalPath, outputPath, this, decryptKey);
        System.out.println("Location of the decrypted file is - " + outputPath);
    }

    /**
     * Extract key value from file
     * @param keyPath the path to the file key
     * @return key value
     */
    private int getKeyFromFile(final String keyPath) throws IOException, InvalidEncryptionKeyException {
        String keyStr = readFile(keyPath);
        if (keyStr.indexOf('\n') != -1) {
            keyStr = keyStr.substring(0, keyStr.indexOf(System.lineSeparator()));
        }
        int myKey;
        try {
            myKey = Integer.parseInt(keyStr);
        } catch (NumberFormatException e) {
            throw new InvalidEncryptionKeyException("The key file doesn't contain number");
        }
        if (0 >= myKey || myKey >= this.getKeyMaxRange()) {
            throw new InvalidEncryptionKeyException("The key not in the correct range that suppose to be");
        }
        return myKey;
    }
}