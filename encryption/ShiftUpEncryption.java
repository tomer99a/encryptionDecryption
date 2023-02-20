package encryptionDecryption.encryption;

import java.util.Random;

import static encryptionDecryption.utils.GeneralMethods.getRange;

public class ShiftUpEncryption extends EncryptionAlgorithmAbstract {
    public ShiftUpEncryption() {
        super("ShiftUp");
    }

    /**
     * Encrypt the char by key
     * @param c char to encrypt
     * @param key key to use for encrypt
     * @return the encryption char that you're looking for
     */
    @Override
    public char handleCher(char c, int key){
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
     * Generate key that didn't reset the modulo action.
     */
    @Override
    public void generateKey() {
        key = new Random().nextInt(1000) + 1;
        while (key % ('Z'-'A') == 0){
            key = new Random().nextInt(1000) + 1;
        }
    }
}
