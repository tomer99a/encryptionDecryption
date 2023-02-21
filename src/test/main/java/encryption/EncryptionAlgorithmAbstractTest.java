package java.encryption;

import java.io.IOException;

import static java.utils.IOMethods.compareTwoFiles;
import static org.junit.jupiter.api.Assertions.*;

class EncryptionAlgorithmAbstractTest {
    final protected String ORIGINAL_PATH;
    final protected String ENCRYPT_PATH;
    final protected String DECRYPT_PATH;
    final protected String KEY_PATH;

    EncryptionAlgorithmAbstractTest() {
        String fileName = "input_text";
        String basePath = "src\\main\\java\\data\\";
        this.ORIGINAL_PATH = basePath + fileName + ".txt";
        this.ENCRYPT_PATH = basePath + fileName + "_encrypted.txt";
        this.DECRYPT_PATH = basePath + fileName + "_decrypted.txt";
        this.KEY_PATH = basePath + "key.txt";
    }

    protected void encryptTest(EncryptionAlgorithmInterface algo){
        try {
            algo.encrypt(ORIGINAL_PATH, ENCRYPT_PATH, KEY_PATH);
        } catch (IOException e) {
            fail(String.format("The %s encrypt failed", algo.getEncryptionMethod()));
        }
        assertFalse(compareTwoFiles(ORIGINAL_PATH, ENCRYPT_PATH));
    }

    protected void decryptTest(EncryptionAlgorithmInterface algo) {
        try {
            algo.decrypt(ORIGINAL_PATH, ENCRYPT_PATH, KEY_PATH);
        } catch (IOException e) {
            fail(String.format("The %s decrypt failed", algo.getEncryptionMethod()));
        }
        assertTrue(compareTwoFiles(ORIGINAL_PATH, DECRYPT_PATH));
        assertFalse(compareTwoFiles(ENCRYPT_PATH, DECRYPT_PATH));
    }
}