package Encryption;

//import com.sun.org.apache.xpath.internal.operations.String;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.SecureRandom;

import static Utils.IOMethods.createFile;
import static Utils.IOMethods.writeToFile;
import static org.junit.jupiter.api.Assertions.*;

public class EncryptionAlgorithmAbstractTest {
    static protected String originalPath;
    static protected String encryptedPath;
    static protected String decryptedPath;
    static protected String keyPath;

    private void fuckThePath() {
        int randNum = new SecureRandom().nextInt(1000000000);
        encryptedPath = randNum + encryptedPath;
    }

    @BeforeAll
    static void createFiles() throws IOException {
        originalPath = Files.createTempFile("input_text", ".txt").toString();
        encryptedPath = Files.createTempFile("input_text_encrypted", ".txt").toString();
        decryptedPath = Files.createTempFile("input_text_decrypted", ".txt").toString();
        keyPath = Files.createTempFile("key", ".txt").toString();

        createFile(originalPath);
        String message = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + System.lineSeparator() +
                "abcdefghijklmnopqrstuvwxyz" + System.lineSeparator() +
                "Fuck AngularJS" + System.lineSeparator() +
                "$#@^%$&^*$#@!" + System.lineSeparator() +
                "the text to Encryption!!!" + System.lineSeparator() +
                "@#$$%^&^*&*^(*&(*&&^(%&)*)%&*)_+_-809-7087956845463";
        writeToFile(originalPath, message);
    }

    @AfterAll
    static void cleanFiles(){
        String[] allPath = new String[]{originalPath, encryptedPath, decryptedPath, keyPath};
        for (String s : allPath) {
            if (!(new File(s).delete()))
                System.err.println("the file " + s + " didn't deleted");
        }
    }

    protected void encryptTest(IEncryptionAlgorithm algo){
        try {
            algo.encrypt(originalPath, encryptedPath, keyPath);
        } catch (IOException e) {
            fail(String.format("The %s encrypt failed", algo.getEncryptionMethod()));
        }
        assertFalse(compareTwoFiles(originalPath, encryptedPath));

//        fuckThePath();
//
//        Throwable exception = assertThrows(IOException.class, () -> algo.encrypt(originalPath, encryptedPath, keyPath));
//        assertEquals("The system cannot find the path specified", exception.getMessage());
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
}