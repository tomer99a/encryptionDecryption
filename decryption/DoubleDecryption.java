package encryptionDecryption.decryption;

public class DoubleDecryption extends DecryptionAlgorithmAbstract{
    public DoubleDecryption() {
        super("Double");
    }

    @Override
    public char handleCher(char c, int key) {
        return 0;
    }
}
