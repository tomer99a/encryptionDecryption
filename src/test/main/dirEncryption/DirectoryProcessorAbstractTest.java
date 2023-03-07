package dirEncryption;

import encryption.IEncryptionAlgorithm;
import keys.NormalKey;
import org.junit.jupiter.api.AfterAll;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.SecureRandom;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static utils.IOMethods.writeToFile;
import static utilsTest.helpers.compareTwoFiles;

abstract class DirectoryProcessorAbstractTest {
    static protected File dataFile;
    final protected File encryptFile;
    final protected File decryptFile;
    final protected NormalKey normalKey;

    final protected int numberFiles = 5;

    public DirectoryProcessorAbstractTest() throws IOException {
        dataFile = new File(Files.createTempDirectory("data").toString());
        for (int i = 1; i <= numberFiles; i++) {
            File file = new File(dataFile, "file" + i + ".txt");
            if (file.createNewFile()) {
                writeToFile(file.getPath(), buildBigText());
            }
        }
        System.out.println("make files:");
        System.out.println(dataFile.getAbsolutePath());
        encryptFile = new File(dataFile, "encrypted");
        decryptFile = new File(dataFile, "decrypted");

        String keyPath = dataFile.getPath() + File.separator + "key.txt";
        normalKey = new NormalKey(keyPath);

        //TODO: add after all that delete all the tmp files.
    }

    public static void deleteDirectory(File file) {
        for (File subFile : Objects.requireNonNull(file.listFiles())) {
            if (subFile.isDirectory()) {
                deleteDirectory(subFile);
            }
            boolean didDelete = subFile.delete();
        }
    }

    @AfterAll
    static void cleanFile() {
//        deleteDirectory(dataFile);
    }

    private String buildBigText() {
        StringBuilder str = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < 2000000; i++) {
            if (secureRandom.nextInt(6) == 3) {
                str.append(" ");
                continue;
            }
            if (secureRandom.nextInt(20) == 3) {
                str.append(System.lineSeparator());
                continue;
            }
            str.append((char) (secureRandom.nextInt(94) + 33));
        }
        return str.toString();
    }

    protected void encryptDirTest(IDirectoryProcessor<NormalKey> directoryProcessor, IEncryptionAlgorithm<NormalKey> encryptionAlgorithm) {
        try {
            directoryProcessor.encryptDir(encryptionAlgorithm, normalKey);
        } catch (IOException e) {
            String message = String.format("The folder encryption failed\nAlgo - %s\nError message - %s", encryptionAlgorithm.getEncryptionMethod(),
                    e.getMessage());
            fail(message);
        }
        for (int i = 1; i <= numberFiles; i++) {
            String file1 = dataFile.getPath() + File.separator + "file" + i + ".txt";
            String file2 = encryptFile.getPath() + File.separator + "file" + i + ".txt";
            assertFalse(compareTwoFiles(file1, file2));
        }
    }

    protected void decryptDirTest(IDirectoryProcessor<NormalKey> directoryProcessor, IEncryptionAlgorithm<NormalKey> encryptionAlgorithm) {
        try {
            directoryProcessor.decryptDir(encryptionAlgorithm, normalKey);
        } catch (IOException e) {
            String message = String.format("The folder decryption failed\nAlgo - %s\nError message - %s", encryptionAlgorithm.getEncryptionMethod(),
                    e.getMessage());
            fail(message);
        }
        for (int i = 1; i <= numberFiles; i++) {
            String file1 = dataFile.getPath() + File.separator + "file" + i + ".txt";
            String file2 = decryptFile.getPath() + File.separator + "file" + i + ".txt";
            assertTrue(compareTwoFiles(file1, file2));
            System.out.println(new File(file1).getName() + " is good");
        }
    }
}