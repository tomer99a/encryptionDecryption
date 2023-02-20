package encryptionDecryption.decryption;

import static encryptionDecryption.utils.GeneralMethods.getRange;

public class XorDecrypt extends DecryptionAlgorithmAbstract{
    public XorDecrypt() {
        super("Xor");
    }

    /**
     * decrypt the char by key
     * @param c char to encrypt
     * @param key key to use for encrypt
     * @return the decryption char that you're looking for
     */
    @Override
    public char handleCher(char c, int key){
        if(c < 127)
            return c;

        return (char) ((c - 255) ^ key);
    }
}
