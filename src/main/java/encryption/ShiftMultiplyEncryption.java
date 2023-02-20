package main.java.encryption;

import static main.java.utils.GeneralMethods.getRange;

public class ShiftMultiplyEncryption extends EncryptionAlgorithmAbstract {
    final protected int MY_PRIME_NUMBER = 53;
    final protected int MY_SPECIAL_CHAR = 248;

    /**
     * Encrypt the char by key
     * @param c char to encrypt
     * @param key key to use for encrypt
     * @return the encryption char that you're looking for
     */
    @Override
    public char encryptChar(char c, int key){
        int numberOfLetter = 52;
        int range = getRange(c);
        if(c == MY_SPECIAL_CHAR)
            c = (char) ('A' + MY_PRIME_NUMBER - 1);
        else if(range == -1)
            return c;
        else if (range == 'a')
            //In the middle of capitals and lower case there are 6 chars, so I bring down 6 to get rid of the gap
            c -= 6;

        int encryptValue = (c * key) % MY_PRIME_NUMBER;
        if(encryptValue < numberOfLetter/2)
            encryptValue += 'A';
        else if(encryptValue != numberOfLetter)
            encryptValue += 'a' - numberOfLetter/2;
        else
            encryptValue = MY_SPECIAL_CHAR;

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
        int range = getRange(c);

        if(c == MY_SPECIAL_CHAR)
            rest = numberOfLetter;
        else if(range == -1)
            return c;
        else
            rest = c - range;
        rest += range=='a' ? numberOfLetter/2 : 0;


        for(int i='A'; i<='z'; i++){
            if((i*key) % MY_PRIME_NUMBER == rest){
                //In the middle of capitals and lower case there are 6 chars, so I add 6 to get rid of the gap
                if(i > 'Z')
                    i += 6;
                if(i == '{')
                    return (char) MY_SPECIAL_CHAR;
                return (char) i;
            }
        }
        return c;
    }
}
