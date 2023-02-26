package Encryption;

public abstract class EncryptionAlgorithmAbstract implements IEncryptionAlgorithm {
    protected String encryptionMethod;

    public EncryptionAlgorithmAbstract(String encryptionMethod) {
        this.encryptionMethod = encryptionMethod;
    }

    public String getEncryptionMethod() {
        return encryptionMethod;
    }

    @Override
    public String toString() {
        return this.getEncryptionMethod();
    }
}
