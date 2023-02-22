package Encryption;

public abstract class EncryptionAlgorithmAbstract implements InterfaceEncryptionAlgorithm {
    protected String encryptionMethod;

    public EncryptionAlgorithmAbstract(String encryptionMethod) {
        this.encryptionMethod = encryptionMethod;
    }

    public String getEncryptionMethod() {
        return encryptionMethod;
    }
}
