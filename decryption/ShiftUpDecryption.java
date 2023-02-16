package encryptionDecryption.decryption;

public class ShiftUpDecryption extends DecryptionAlgorithmAbstract{
    public ShiftUpDecryption() {
        super("ShiftUp");
    }

    /**
     * decrypt the char by key
     * @param c char to encrypt
     * @param key key to use for encrypt
     * @param start start of the ASCII sequence
     * @param end end of the ASCII sequence
     * @return the decryption char that you're looking for
     */
    public char handleCher(char c, int key, int start, int end){
        if((int) c >= start && end >= (int) c)
            if((int) c - key < start)
                return (char) ((int) c - key + end - start);
            else
                return (char) ((int) c - key);
        return c;
    }
}
