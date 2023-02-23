package encryption;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class EncryptionAlgorithmAbstractTest {
    final protected String originalPath;
    final protected String encryptedPath;
    final protected String decryptedPath;
    final protected String keyPath;

    EncryptionAlgorithmAbstractTest() {
        String fileName = "input_text";
        String basePath = "src" + File.separator + "main" + File.separator + "data" + File.separator;
        this.originalPath = basePath + fileName + ".txt";
        this.encryptedPath = basePath + fileName + "_encrypted.txt";
        this.decryptedPath = basePath + fileName + "_decrypted.txt";
        this.keyPath = basePath + "key.txt";
    }

    public static boolean compareTwoFiles(String path1, String path2){
        if(path1.equals(path2))
            return true;
        File file1 = new File(path1);
        File file2 = new File(path2);

        try (FileInputStream in1 = new FileInputStream(file1); FileInputStream in2 = new FileInputStream(file2)) {
            int tmpChar1, tmpChar2;

            while ((tmpChar1 = in1.read()) != -1) {
                tmpChar2 = in2.read();
                if(tmpChar2 == -1)
                    return false;
                if(tmpChar1 != tmpChar2)
                    return false;
            }
            tmpChar2 = in2.read();
            return tmpChar2 == -1;
        } catch (IOException e) {
            System.err.println("fail at compare two files");
            return false;
        }
    }

    protected void encryptTest(EncryptionAlgorithmInterface algo){
        try {
            algo.encrypt(originalPath, encryptedPath, keyPath);
        } catch (IOException e) {
            fail(String.format("The %s encrypt failed", algo.getEncryptionMethod()));
        }
        assertFalse(compareTwoFiles(originalPath, encryptedPath));
    }

    protected void decryptTest(EncryptionAlgorithmInterface algo) {
        try {
            algo.decrypt(encryptedPath, decryptedPath, keyPath);
        } catch (IOException e) {
            fail(String.format("The %s decrypt failed", algo.getEncryptionMethod()));
        }
        assertFalse(compareTwoFiles(encryptedPath, decryptedPath));
        assertTrue(compareTwoFiles(originalPath, decryptedPath));
    }
}