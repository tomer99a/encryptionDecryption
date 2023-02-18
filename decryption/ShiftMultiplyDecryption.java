package encryptionDecryption.decryption;

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
        int myPrimeNumber = 59;
        int startRange = 255 - myPrimeNumber;
        int rest = c - startRange;

        for(int i='A'; i<='z'; i++){
            if((i*key) % myPrimeNumber == rest){
                return (char) i;
            }
        }
        return c;
    }
}
