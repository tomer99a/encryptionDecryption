package main.java.encryption;

import java.util.Random;

import static main.java.utils.GeneralMethods.getRange;

public class ShiftUpEncryption extends EncryptionAlgorithmAbstract {
    public ShiftUpEncryption() {
        super("ShiftUp");
    }

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
        int[] range = getRange(c);
        if(range[0] == -1)
            return c;
        key = key % (range[1] - range[0]);
        if((int) c + key > range[1])
            return (char) ((int) c + key - range[1] + range[0] - 1);
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
        int[] range = getRange(c);
        if(range[0] == -1)
            return c;
        key = key % (range[1] - range[0]);
        if((int) c - key < range[0])
            return (char) ((int) c - key + range[1] - range[0] + 1);
        else
            return (char) ((int) c - key);
    }
}
