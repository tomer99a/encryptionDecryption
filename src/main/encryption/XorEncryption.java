package encryption;

import static utils.GeneralMethods.getRange;

public class XorEncryption extends CharEncryptionAlgorithmAbstract {
    public XorEncryption() {
        super("Xor");
    }

    /**
     * Encrypt the char by key
     * @param c char to encrypt
     * @param key key to use for encrypt
     * @return the encryption char that you're looking for
     */
    @Override
    public char encryptChar(char c, int key){
        if(getRange(c) == -1)
            return c;

        return (char) ((c ^ key) + 255);
    }

    /**
     * decrypt the char by key
     * @param c char to encrypt
     * @param key key to use for encrypt
     * @return the decryption char that you're looking for
     */
    @Override
    public char decryptChar(char c, int key){
        // the xor encrypt chars to ascii code bigger than 127
        // so if I get char smaller than 255 it should return as is.
        if(c < 255)
            return c;

        return (char) ((c - 255) ^ key);
    }
}
