package encryptionDecryption.decryption;

import static encryptionDecryption.utils.GeneralMethods.getRange;

public class ShiftMultiplyDecryption extends DecryptionAlgorithmAbstract {
    public ShiftMultiplyDecryption() {
        super("ShiftMultiply");
    }

    /**
     * decrypt the char by key
     * @param c char to decrypt
     * @param key key to use for decrypt
     * @return the decryption char that you're looking for
     */
    public char handleCher(char c, int key){
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
                //In the middle of capitals and lower case there are 6 chars so I add 6 to get rid of the gap
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
