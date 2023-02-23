package encryption;

public abstract class EncryptionAlgorithmAbstract implements EncryptionAlgorithmInterface {
    protected String encryptionMethod;

    public EncryptionAlgorithmAbstract(String encryptionMethod) {
        this.encryptionMethod = encryptionMethod;
    }

    public String getEncryptionMethod() {
        return encryptionMethod;
    }
}
