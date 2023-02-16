package encryptionDecryption.encryption;

public class ShiftUpEncryption extends EncryptionAlgorithmAbstract {
    public ShiftUpEncryption() {
        super("ShiftUp");
    }

    public void generateKey(){
        //TODO: better random, until bit.
        this.key = 1 + (int) (Math.random()*24); // get random number 1 to 24;
    }

    /**
     * encrypt the char by key
     * @param c char to encrypt
     * @param key key to use for encrypt
     * @param start start of the ASCII sequence
     * @param end end of the ASCII sequence
     * @return the encryption char that you're looking for
     */
    @Override
    public char handleCher(char c, int key, int start, int end){
        if((int) c >= start && end >= (int) c){
            if((int) c + key > end)
                return (char) ((int) c + key - end + start - 1);
            else
                return (char) ((int) c + key);
        }
        return c;
    }
}
