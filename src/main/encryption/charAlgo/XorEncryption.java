package encryption.charAlgo;

import java.security.SecureRandom;

public class XorEncryption extends CharEncryptionAlgorithmAbstract {
    private static final int NUMBERS_NORMAL_ASCII_CHARS = 255;
    public XorEncryption() {
        super("Xor");

    }

    /**
     * Generate new key for the encryption.
     */
    protected void generateKey() {
        key = new SecureRandom().nextInt(keyMaxRange);
    }

    @Override
    protected void setKeyMaxRange() {
        keyMaxRange = BOUND_RANDOM_NUMBER * 10;
    }

    /**
     * Encrypt the char by key.
     * @param c char to encrypt
     * @param key key to use for encrypt
     * @return the encryption char that you're looking for
     */
    @Override
    public char encryptChar(final char c, final int key) {
        if (!Character.isLetter(c)) {
            return c;
        }

        return (char) ((c ^ key) + NUMBERS_NORMAL_ASCII_CHARS);
    }

    /**
     * Decrypt the char by key.
     * @param c char to encrypt
     * @param key key to use for encrypt
     * @return the decryption char that you're looking for
     */
    @Override
    public char decryptChar(final char c, final int key) {
        // the xor encrypt chars to ascii code bigger than 127
        // so if I get char smaller than 255 it should return as is.
        if (c < NUMBERS_NORMAL_ASCII_CHARS) {
            return c;
        }

        return (char) ((c - NUMBERS_NORMAL_ASCII_CHARS) ^ key);
    }
}
