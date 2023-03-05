package encryption.charAlgo;

import java.security.SecureRandom;

public class ShiftUpEncryption extends CharEncryptionAlgorithmAbstract {
    final private static int NUMBER_OF_LETTERS = 26;
    public ShiftUpEncryption() {
        super("ShiftUp");
    }

    @Override
    protected void setKeyMaxRange() {
        keyMaxRange = BOUND_RANDOM_NUMBER;
    }

    /**
     * Generate key that didn't reset the modulo action.
     */
    @Override
    protected void generateKey() {
        key = new SecureRandom().nextInt(BOUND_RANDOM_NUMBER);

        // if the random number is divided by The number of letters that
        // we encrypt the encryption won't do anything's
        while (key % (BIG_Z - BIG_A) == 0) {
            key = new SecureRandom().nextInt(BOUND_RANDOM_NUMBER);
        }
    }

    /**
     * Encrypt the char by key.
     * @param c char to encrypt
     * @param key key to use for encrypt
     * @return the encryption char that you're looking for
     */
    @Override
    public char encryptChar(final char c, final int key) {
        if (!Character.isLetter(c)) {
            return c;
        }
        final char endRange = Character.isLowerCase(c) ? SMALL_Z : BIG_Z;
        int currentKey = key % NUMBER_OF_LETTERS;
        if ((int) c + currentKey > endRange) {
            return (char) ((int) c + currentKey - NUMBER_OF_LETTERS);
        } else {
            return (char) ((int) c + currentKey);
        }
    }

    /**
     * Decrypt the char by key.
     * @param c char to encrypt
     * @param key key to use for encrypt
     * @return the decryption char that you're looking for
     */
    @Override
    public char decryptChar(final char c, final int key) {
        if (!Character.isLetter(c)) {
            return c;
        }
        int currentKey = key % NUMBER_OF_LETTERS;
        if ((int) c - currentKey < (Character.isUpperCase(c) ? 'A' : 'a')) {
            return (char) ((int) c - currentKey + NUMBER_OF_LETTERS);
        } else {
            return (char) ((int) c - currentKey);
        }
    }
}