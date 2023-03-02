package dirEncryption;

import encryption.IEncryptionAlgorithm;

public interface IDirectoryProcessor<T> {
    void encryptDir(String dirPath, IEncryptionAlgorithm<T> algo, T key);
}
