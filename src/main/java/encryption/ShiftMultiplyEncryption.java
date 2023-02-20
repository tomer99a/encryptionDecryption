package main.java.encryption;

import static main.java.utils.GeneralMethods.getRange;

public class ShiftMultiplyEncryption extends EncryptionAlgorithmAbstract {
    public ShiftMultiplyEncryption() {
        super("ShiftMultiply");
    }

    /**
     * Encrypt the char by key
     * @param c char to encrypt
     * @param key key to use for encrypt
     * @return the encryption char that you're looking for
     */
    @Override
    public char handleCher(char c, int key){
        int numberOfLetter = 52;
        int[] range = getRange(c);
        if(c == mySpecialChar)
            c = (char) ('A' + myPrimeNumber - 1);
        else if(range[0] == -1)
            return c;
        else if (range[0] == 'a')
            //In the middle of capitals and lower case there are 6 chars, so I bring down 6 to get rid of the gap
            c -= 6;

        int encryptValue = (c * key) % myPrimeNumber;
        if(encryptValue < numberOfLetter/2)
            encryptValue += 'A';
        else if(encryptValue != numberOfLetter)
            encryptValue += 'a' - numberOfLetter/2;
        else
            encryptValue = mySpecialChar;

        return (char) encryptValue;
    }
}
