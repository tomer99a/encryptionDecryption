package encryptionDecryption.encryption;

import static encryptionDecryption.utils.GeneralMethods.fixNotGoodChars;
import static encryptionDecryption.utils.GeneralMethods.getRange;

public class ShiftMultiplyEncryption extends EncryptionAlgorithmAbstract {
    public ShiftMultiplyEncryption() {
        super("ShiftMultiply");
    }

    /**
     * @param c char to encrypt
     * @param key key to use for encrypt
     * @return the encryption char that you're looking for
     */
    @Override
    public char handleCher(char c, int key){
        int[] range = getRange(c);
        int myPrimeNumber = 59;
        int startRange = 255 - myPrimeNumber;
        if(range[0] == -1 && !(startRange <= c && c <= 255))
            return c;
        if(c > startRange){
            startRange = 255 - 2 * myPrimeNumber;
            return (char) fixNotGoodChars((c * key) % myPrimeNumber + startRange);
        }
        return (char) ((c * key) % myPrimeNumber + startRange);
    }
}
