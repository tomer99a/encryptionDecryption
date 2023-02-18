package encryptionDecryption.encryption;

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
        int myPrimeNumber = 53;
        int[] range = getRange(c);
        if(c == 248)
            c = (char) ('A' + myPrimeNumber - 1);
        else if(range[0] == -1)
            return c;
        else if (range[0] == 'a')
            c -= 6;

        int encryptValue = (c * key) % myPrimeNumber;
        if(encryptValue < 26)
            encryptValue += 'A';
        else if(encryptValue != 52)
            encryptValue += 'a' - 26;
        else
            encryptValue = 248;

        return (char) encryptValue;
    }
}
