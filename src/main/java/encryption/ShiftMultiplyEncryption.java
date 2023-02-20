package main.java.encryption;

import static main.java.utils.GeneralMethods.getRange;

public class ShiftMultiplyEncryption extends EncryptionAlgorithmAbstract {
    final protected int myPrimeNumber = 53;
    final protected int mySpecialChar = 248;

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
    public char encryptChar(char c, int key){
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

    /**
     * decrypt the char by key
     * @param c char to decrypt
     * @param key key to use for decrypt
     * @return the decryption char that you're looking for
     */
    @Override
    public char decryptChar(char c, int key){
        int numberOfLetter = 52;
        int rest;
        int[] range = getRange(c);

        if(c == mySpecialChar)
            rest = numberOfLetter;
        else if(range[0] == -1)
            return c;
        else
            rest = c - range[0];
        rest += range[0]=='a' ? numberOfLetter/2 : 0;


        for(int i='A'; i<='z'; i++){
            if((i*key) % myPrimeNumber == rest){
                //In the middle of capitals and lower case there are 6 chars, so I add 6 to get rid of the gap
                if(i > 'Z')
                    i += 6;
                if(i == '{')
                    return (char) mySpecialChar;
                return (char) i;
            }
        }
        return c;
    }
}
