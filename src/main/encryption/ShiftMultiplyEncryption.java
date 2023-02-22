package encryption;

import java.security.SecureRandom;

import static utils.GeneralMethods.getRange;

public class ShiftMultiplyEncryption extends CharEncryptionAlgorithmAbstract {
    final static private int GAP_BETWEEN_UPPER_AND_LOWER_LETTERS = 6;
    final static private int MY_PRIME_NUMBER = 53;
    final static private int MY_SPECIAL_CHAR = 248;

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
        int range = getRange(c);
        if(c == MY_SPECIAL_CHAR)
            c = (char) (BIG_A + MY_PRIME_NUMBER - 1);
        else if(range == -1)
            return c;
        else if (range == SMALL_A)
            //In the middle of capitals and lower case there are 6 chars, so I bring down 6 to get rid of the gap
            c -= GAP_BETWEEN_UPPER_AND_LOWER_LETTERS;

        int encryptValue = (c * key) % MY_PRIME_NUMBER;
        if(encryptValue < numberOfLetter/2)
            encryptValue += BIG_A;
        else if(encryptValue != numberOfLetter)
            encryptValue += SMALL_A - numberOfLetter/2;
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
        else {
            rest = c - range;
            rest += range == SMALL_A ? numberOfLetter / 2 : 0;
        }


        for(int i=BIG_A; i<=SMALL_Z; i++){
            if((i*key) % MY_PRIME_NUMBER == rest){
                //In the middle of capitals and lower case there are 6 chars, so I add 6 to get rid of the gap
                if(i > BIG_Z)
                    i += GAP_BETWEEN_UPPER_AND_LOWER_LETTERS;
                if(i == '{')
                    return (char) MY_SPECIAL_CHAR;
                return (char) i;
            }
        }
        return c;
    }

    /**
     * Generate key that didn't reset the modulo action.
     */
    @Override
    public void generateKey() {
        key = new SecureRandom().nextInt(1000);

        // If the random number is divided by my prime number
        // the encryption will tern everything to the letter A
        while (key % MY_PRIME_NUMBER == 0){
            key = new SecureRandom().nextInt(1000);
        }
    }
}
