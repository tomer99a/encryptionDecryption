package encryptionDecryption.decryption;

import static encryptionDecryption.utils.GeneralMethods.getRange;

public class ShiftUpDecryption extends DecryptionAlgorithmAbstract{
    public ShiftUpDecryption() {
        super("ShiftUp");
    }

    /**
     * decrypt the char by key
     * @param c char to encrypt
     * @param key key to use for encrypt
     * @return the decryption char that you're looking for
     */
    public char handleCher(char c, int key){
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
