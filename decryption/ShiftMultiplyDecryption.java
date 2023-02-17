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
        int myPrimeNumber = 67;
        int startRange = 188;
        int rest = c - startRange;

        for(int i='A'; i<='z'; i++){
            if((i*key)%myPrimeNumber == rest){
                return (char) i;
            }
        }
        return c;
    }
}
