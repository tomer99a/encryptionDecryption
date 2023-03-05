package encryption.charAlgo;

import encryption.EncryptionAlgorithmAbstractTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class XorEncryptionTest extends EncryptionAlgorithmAbstractTest {
    public XorEncryptionTest() throws IOException {
    }

    @Test
    @DisplayName("encrypt and decrypt by using xor")
    void encrypt(){
        encryptTest(new XorEncryption());
        decryptTest(new XorEncryption());
    }

    @Test
    @DisplayName("xor encrypt fail")
    void encryptWrongPathTest() {
        encryptWrongPath(new XorEncryption());
    }

    @Test
    @DisplayName("up encrypt fail")
    void decryptFailTest() throws IOException {
        new XorEncryption().encrypt(originalPath, originalPath, keyPath);
        decryptWrongPath(new XorEncryption());
    }
}