package dirEncryption;

import encryption.IEncryptionAlgorithm;

import java.io.IOException;

public interface IDirectoryProcessor<T> {
    void encryptDir(IEncryptionAlgorithm<T> algo, T key) throws IOException;
    void decryptDir(IEncryptionAlgorithm<T> algo, T key) throws IOException;
}
