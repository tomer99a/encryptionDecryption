package main.java.encryption;

import java.security.SecureRandom;

import static main.java.utils.GeneralMethods.getRange;

public class ShiftUpEncryption extends EncryptionAlgorithmAbstract {
    /**
     * Generate key that didn't reset the modulo action.
     */
    @Override
    public void generateKey() {
        key = new SecureRandom().nextInt(1000);

        // if the random number is divided by The number of letters that
        // we encrypt the encryption won't do anything's
        while (key % (BIG_Z - BIG_A) == 0){
            key = new SecureRandom().nextInt(1000);
        }
    }

    /**
     * Encrypt the char by key
     * @param c char to encrypt
     * @param key key to use for encrypt
     * @return the encryption char that you're looking for
     */
    @Override
    public char encryptChar(char c, int key){
        final int START_RANGE = getRange(c);
        if(START_RANGE == -1)
            return c;
        final char END_RANGE = START_RANGE == SMALL_A ? SMALL_Z : BIG_Z;
        key = key % (END_RANGE - START_RANGE);
        if((int) c + key > END_RANGE)
            return (char) ((int) c + key - END_RANGE + START_RANGE - 1);
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
        final int START_RANGE = getRange(c);
        if(START_RANGE == -1)
            return c;
        final char END_RANGE = START_RANGE == SMALL_A ? SMALL_Z : BIG_Z;
        key = key % (END_RANGE - START_RANGE);
        if((int) c - key < START_RANGE)
            return (char) ((int) c - key + END_RANGE - START_RANGE + 1);
        else
            return (char) ((int) c - key);
    }
}
