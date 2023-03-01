package encryption.charAlgo;

import encryption.EncryptionAlgorithmAbstractTest;
import keys.NormalKey;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class XorEncryptionTest extends EncryptionAlgorithmAbstractTest {
    public XorEncryptionTest() throws IOException {
        super(false);
    }

    @Test
    @DisplayName("encrypt and decrypt by using xor")
    void encrypt(){
        encryptTest(new XorEncryption<NormalKey>());
        decryptTest(new XorEncryption<NormalKey>());
    }

    @Test
    @DisplayName("xor encrypt fail")
    void encryptWrongPathTest() {
        encryptWrongPath(new XorEncryption<NormalKey>());
    }

    @Test
    @DisplayName("up encrypt fail")
    void decryptFailTest() throws IOException {
        new XorEncryption<NormalKey>().encrypt(originalPath, originalPath, keyPath);
        decryptWrongPath(new XorEncryption<NormalKey>());
    }
}