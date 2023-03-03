package dirEncryption;

import encryption.IEncryptionAlgorithm;

public interface IDirectoryProcessor<T> {
    void encryptDir(IEncryptionAlgorithm<T> algo, T key);
}
