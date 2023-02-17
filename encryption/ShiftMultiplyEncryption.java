package encryptionDecryption.encryption;

import encryptionDecryption.utils.PrimeNumberGenerator;

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
        int myPrimeNumber = 67;
        int startRange = 188;
        return (char) ((c * key) % myPrimeNumber + startRange);
    }
}
