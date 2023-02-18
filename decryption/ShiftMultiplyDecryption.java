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
        int myPrimeNumber = 53;
        int rest;
        int[] range = getRange(c);

        if(c == 248)
            rest = 52;
        else if(range[0] == -1)
            return c;
        else
            rest = c - range[0];
        rest += range[0]=='a' ? 26 : 0;

        for(int i='A'; i<='z'; i++){
            if((i*key) % myPrimeNumber == rest){
                if(i > 'Z')
                    i += 6;
                if(i == '{')
                    return (char) 248;
                return (char) i;
            }
        }
        return c;
    }
}
