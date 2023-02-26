package Encryption.CharAlgo;

import java.security.SecureRandom;

public class ShiftUpEncryption extends CharEncryptionAlgorithmAbstract {
    final static private int NUMBER_OF_LETTERS = 26;
    
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
        while (key % (BIG_Z - BIG_A) == 0){
            key = new SecureRandom().nextInt(BOUND_RANDOM_NUMBER);
        }
    }

    @Override
    public int getKeyStrength() {
        return 3;
    }

    /**
     * Encrypt the char by key
     * @param c char to encrypt
     * @param key key to use for encrypt
     * @return the encryption char that you're looking for
     */
    @Override
    public char encryptChar(char c, int key){
        if(!Character.isLetter(c))
            return c;
        final char endRange = Character.isLowerCase(c) ? SMALL_Z : BIG_Z;
        key = key % NUMBER_OF_LETTERS;
        if((int) c + key > endRange)
            return (char) ((int) c + key - NUMBER_OF_LETTERS);
        else
            return (char) ((int) c + key);
    }

    /**
     * decrypt the char by key
     * @param c char to encrypt
     * @param key key to use for encrypt
     * @return the decryption char that you're looking for
     */
    @Override
    public char decryptChar(char c, int key){
        if(!Character.isLetter(c))
            return c;
        key = key % NUMBER_OF_LETTERS;
        if((int) c - key < (Character.isUpperCase(c) ? 'A' : 'a'))
            return (char) ((int) c - key + NUMBER_OF_LETTERS);
        else
            return (char) ((int) c - key);
    }
}