package main.java.encryption;

import static main.java.utils.GeneralMethods.getRange;

public class XorEncryption extends EncryptionAlgorithmAbstract {
    /**
     * Encrypt the char by key
     * @param c char to encrypt
     * @param key key to use for encrypt
     * @return the encryption char that you're looking for
     */
    @Override
    public char encryptChar(char c, int key){
        int[] range = getRange(c);
        if(range[0] == -1)
            return c;

        System.out.print((c ^ key) + ", ");
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
        if(c < 127)
            return c;

        return (char) ((c - 255) ^ key);
    }
}
