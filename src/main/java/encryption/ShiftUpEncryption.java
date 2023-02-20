package main.java.encryption;

import java.util.Random;

import static main.java.utils.GeneralMethods.getRange;

public class ShiftUpEncryption extends EncryptionAlgorithmAbstract {
    /**
     * Generate key that didn't reset the modulo action.
     */
    @Override
    public void generateKey() {
        key = new Random().nextInt(1000) + 1;

        while (key % ('Z'-'A') == 0){
            key = new Random().nextInt(1000) + 1;
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
        final char END_RANGE = START_RANGE == 'a' ? 'z' : 'Z';
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
        final char END_RANGE = START_RANGE == 'a' ? 'z' : 'Z';
        key = key % (END_RANGE - START_RANGE);
        if((int) c - key < START_RANGE)
            return (char) ((int) c - key + END_RANGE - START_RANGE + 1);
        else
            return (char) ((int) c - key);
    }
}
