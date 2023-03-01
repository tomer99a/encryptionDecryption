package encryption;

import keys.AKey;

public abstract class EncryptionAlgorithmAbstract<T extends AKey> implements IEncryptionAlgorithm<T> {
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
