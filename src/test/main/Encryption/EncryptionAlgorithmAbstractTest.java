package Encryption;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.*;

public class EncryptionAlgorithmAbstractTest {
    protected String originalPath;
    protected String encryptedPath;
    protected String decryptedPath;
    protected String keyPath;

    public EncryptionAlgorithmAbstractTest() {
        generatePaths();
    }

    private void generatePaths() {
        String fileName = "input_text";
        String basePath = "src" + File.separator + "main" + File.separator + "Data" + File.separator;
        this.originalPath = basePath + fileName + ".txt";
        this.encryptedPath = basePath + fileName + "_encrypted.txt";
        this.decryptedPath = basePath + fileName + "_decrypted.txt";
        this.keyPath = basePath + "key.txt";
    }

    private void fuckThePath() {
        int randNum = new SecureRandom().nextInt(1000000000);
        this.encryptedPath = randNum + this.encryptedPath;
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

    protected void encryptTest(IEncryptionAlgorithm algo){
        try {
            algo.encrypt(originalPath, encryptedPath, keyPath);
        } catch (IOException e) {
            fail(String.format("The %s encrypt failed", algo.getEncryptionMethod()));
        }
        assertFalse(compareTwoFiles(originalPath, encryptedPath));

        fuckThePath();

        Throwable exception = assertThrows(IOException.class, () -> algo.encrypt(originalPath, encryptedPath, keyPath));
        assertEquals("The system cannot find the path specified", exception.getMessage());
    }

    protected void decryptTest(IEncryptionAlgorithm algo) {
        try {
            algo.decrypt(encryptedPath, decryptedPath, keyPath);
        } catch (IOException e) {
            fail(String.format("The %s decrypt failed", algo.getEncryptionMethod()));
        }
        assertFalse(compareTwoFiles(encryptedPath, decryptedPath));
        assertTrue(compareTwoFiles(originalPath, decryptedPath));
    }
}