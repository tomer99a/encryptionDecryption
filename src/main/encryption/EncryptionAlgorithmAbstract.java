package encryption;

import keys.IKey;

public abstract class EncryptionAlgorithmAbstract<K extends IKey> implements IEncryptionAlgorithm<K> {
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
