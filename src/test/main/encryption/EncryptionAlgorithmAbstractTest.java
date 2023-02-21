package encryption;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class EncryptionAlgorithmAbstractTest {
    final protected Path ORIGINAL_PATH;
    final protected Path ENCRYPT_PATH;
    final protected Path DECRYPT_PATH;
    final protected Path KEY_PATH;

    EncryptionAlgorithmAbstractTest() {
        String fileName = "input_text";
        Path basePath = Paths.get("src\\main\\data\\");
        this.ORIGINAL_PATH = Paths.get(String.valueOf(basePath), fileName + ".txt");
        this.ENCRYPT_PATH = Paths.get(String.valueOf(basePath), fileName + "_encrypted.txt");
        this.DECRYPT_PATH = Paths.get(String.valueOf(basePath), fileName + "_decrypted.txt");
        this.KEY_PATH = Paths.get(String.valueOf(basePath), "key.txt");
    }

    public static boolean compareTwoFiles(Path path1, Path path2){
        if(path1.equals(path2))
            return true;
        File file1 = new File(String.valueOf(path1));
        File file2 = new File(String.valueOf(path2));

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
            algo.encrypt(ORIGINAL_PATH, ENCRYPT_PATH, KEY_PATH);
        } catch (IOException e) {
            fail(String.format("The %s encrypt failed", algo.getEncryptionMethod()));
        }
        assertFalse(compareTwoFiles(ORIGINAL_PATH, ENCRYPT_PATH));
    }

    protected void decryptTest(EncryptionAlgorithmInterface algo) {
        try {
            algo.decrypt(ENCRYPT_PATH, DECRYPT_PATH, KEY_PATH);
        } catch (IOException e) {
            fail(String.format("The %s decrypt failed", algo.getEncryptionMethod()));
        }
        assertFalse(compareTwoFiles(ENCRYPT_PATH, DECRYPT_PATH));
        assertTrue(compareTwoFiles(ORIGINAL_PATH, DECRYPT_PATH));
    }
}