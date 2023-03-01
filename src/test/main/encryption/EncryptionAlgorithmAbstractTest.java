package encryption;

import exceptions.invalidPathException;
import keys.AKey;
import keys.DoubleKey;
import keys.NormalKey;
import org.junit.jupiter.api.AfterAll;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.SecureRandom;

import static utils.IOMethods.createFile;
import static utils.IOMethods.writeToFile;
import static org.junit.jupiter.api.Assertions.*;

public class EncryptionAlgorithmAbstractTest {
    static protected String originalPath;
    static protected String encryptedPath;
    static protected String decryptedPath;
    static protected AKey keyPath;

    public EncryptionAlgorithmAbstractTest(boolean isDoubleKey) throws IOException {
        originalPath = Files.createTempFile("input_text", ".txt").toString();
        encryptedPath = Files.createTempFile("input_text_encrypted", ".txt").toString();
        decryptedPath = Files.createTempFile("input_text_decrypted", ".txt").toString();
        keyPath = new NormalKey(Files.createTempFile("key", ".txt").toString());
        if (isDoubleKey) {
            String keyPath1 = addSuffixToFileNameAtPath(keyPath.getKey(), "1");
            String keyPath2 = addSuffixToFileNameAtPath(keyPath.getKey(), "2");
            keyPath = new DoubleKey(keyPath1, keyPath2);
        }
        createFile(originalPath);
        String message = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + System.lineSeparator() +
                "abcdefghijklmnopqrstuvwxyz" + System.lineSeparator() +
                "Fuck AngularJS" + System.lineSeparator() +
                "$#@^%$&^*$#@!" + System.lineSeparator() +
                "the text to Encryption!!!" + System.lineSeparator() +
                "@#$$%^&^*&*^(*&(*&&^(%&)*)%&*)_+_-809-7087956845463";
        writeToFile(originalPath, message);
    }

    private String fuckThePath() {
        String savePath = keyPath.getKey();
        int randNum = new SecureRandom().nextInt(1000000000);
        keyPath = new NormalKey(randNum+savePath);
        return savePath;
    }

    @AfterAll
    static void cleanFiles(){
        String[] allPathToDelete = new String[]{originalPath, encryptedPath, decryptedPath, keyPath.getKey()};
        for (String path : allPathToDelete) {
            System.out.println(path);
//            if (!(new File(path).delete()))
//                System.err.println("the file " + path + " didn't deleted!!!");
        }
    }

    protected void encryptTest(IEncryptionAlgorithm<AKey> algo){
        try {
            algo.encrypt(originalPath, encryptedPath, keyPath);
        } catch (IOException e) {
            String message = String.format("The %s encryption failed\nError message - %s", algo.getEncryptionMethod(), e.getMessage());
            fail(message);
        }
        assertFalse(compareTwoFiles(originalPath, encryptedPath));
    }

    protected void decryptTest(IEncryptionAlgorithm<AKey> algo) {
        try {
            algo.decrypt(encryptedPath, decryptedPath, keyPath);
        } catch (IOException e) {
            fail(String.format("The %s decryption failed", algo.getEncryptionMethod()));
        }
        assertFalse(compareTwoFiles(encryptedPath, decryptedPath));
        assertTrue(compareTwoFiles(originalPath, decryptedPath));
    }

    protected void encryptWrongPath(IEncryptionAlgorithm<AKey> algo) {
        String savePath = fuckThePath();
        assertThrows(invalidPathException.class, () -> algo.encrypt(originalPath, encryptedPath, keyPath));
        keyPath = new NormalKey(savePath);
    }

    protected void decryptWrongPath(IEncryptionAlgorithm<AKey> algo) {
        String savePath = fuckThePath();
        assertThrows(invalidPathException.class, () -> algo.decrypt(encryptedPath, decryptedPath, keyPath));
        keyPath = new NormalKey(savePath);
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

    /**
     * Add suffix only to the file name from the full path
     * @param path original path
     * @param suffix thing to add at the end of the file name
     * @return path with changed name
     */
    public static String addSuffixToFileNameAtPath(final String path, final String suffix) {
        File file = new File(path);
        String fileName = file.getName();
        return file.getParent() + File.separator + fileName.substring(0, fileName.lastIndexOf(".")) + suffix + fileName.substring(fileName.lastIndexOf("."));
    }
}