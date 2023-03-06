package encryption;

import exceptions.invalidPathException;
import keys.NormalKey;
import org.junit.jupiter.api.AfterAll;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.SecureRandom;

import static utils.IOMethods.createFile;
import static utils.IOMethods.writeToFile;
import static org.junit.jupiter.api.Assertions.*;
import static utilsTest.helpers.compareTwoFiles;

public abstract class EncryptionAlgorithmAbstractTest {
    static protected String originalPath;
    static protected String encryptedPath;
    static protected String decryptedPath;
    static protected NormalKey keyPath;

    public EncryptionAlgorithmAbstractTest() throws IOException {
        originalPath = Files.createTempFile("input_text", ".txt").toString();
        encryptedPath = Files.createTempFile("input_text_encrypted", ".txt").toString();
        decryptedPath = Files.createTempFile("input_text_decrypted", ".txt").toString();
        keyPath = new NormalKey(Files.createTempFile("key", ".txt").toString());

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
            if (!(new File(path).delete()))
                System.err.println("the file " + path + " didn't deleted!!!");
        }
    }

    protected void encryptTest(IEncryptionAlgorithm<NormalKey> algo){
        try {
            algo.encrypt(originalPath, encryptedPath, keyPath);
        } catch (IOException e) {
            String message = String.format("The %s encryption failed\nError message - %s", algo.getEncryptionMethod(), e.getMessage());
            fail(message);
        }
        assertFalse(compareTwoFiles(originalPath, encryptedPath));
    }

    protected void decryptTest(IEncryptionAlgorithm<NormalKey> algo) {
        try {
            algo.decrypt(encryptedPath, decryptedPath, keyPath);
        } catch (IOException e) {
            fail(String.format("The %s decryption failed\nError message - %s", algo.getEncryptionMethod(), e.getMessage()));
        }
        assertFalse(compareTwoFiles(encryptedPath, decryptedPath));
        assertTrue(compareTwoFiles(originalPath, decryptedPath));
    }

    protected void encryptWrongPath(IEncryptionAlgorithm<NormalKey> algo) {
        String savePath = fuckThePath();
        assertThrows(invalidPathException.class, () -> algo.encrypt(originalPath, encryptedPath, keyPath));
        keyPath = new NormalKey(savePath);
    }

    protected void decryptWrongPath(IEncryptionAlgorithm<NormalKey> algo) {
        String savePath = fuckThePath();
        assertThrows(invalidPathException.class, () -> algo.decrypt(encryptedPath, decryptedPath, keyPath));
        keyPath = new NormalKey(savePath);
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