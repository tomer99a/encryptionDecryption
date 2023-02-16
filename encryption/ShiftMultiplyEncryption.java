package encryptionDecryption.encryption;

import encryptionDecryption.utils.PrimeNumberGenerator;

public class ShiftMultiplyEncryption extends EncryptionAlgorithmAbstract {
    public ShiftMultiplyEncryption() {
        super("ShiftMultiply");
    }

    public void generateKey(){
        this.key = PrimeNumberGenerator.generatPrimeNumber();
    }

    /**
     * @param c char to encrypt
     * @param key key to use for encrypt
     * @param start start of the ASCII sequence
     * @param end end of the ASCII sequence
     * @return the encryption char that you're looking for
     */
    @Override
    public char handleCher(char c, int key, int start, int end){
        if((int) c >= start && end >= (int) c){
            int ascii = (int) c - start + 1;
            ascii *= key;
            ascii = ascii % (end - start);
            ascii += start + 1;
            return (char) ascii;
        }
        return c;
    }
}
