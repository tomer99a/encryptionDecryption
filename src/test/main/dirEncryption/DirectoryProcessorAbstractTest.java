package dirEncryption;

import encryption.IEncryptionAlgorithm;
import keys.NormalKey;
import log.ErrorLog4jLogger;
import org.junit.jupiter.api.AfterAll;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static utils.IOMethods.writeToFile;
import static utilsTest.Helpers.buildBigText;
import static utilsTest.Helpers.compareTwoFiles;

abstract class DirectoryProcessorAbstractTest {
    static protected File dataFile;
    final protected File encryptFile;
    final protected File decryptFile;
    final protected NormalKey normalKey;

    final protected int numberFiles = 25;

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
        deleteDirectory(dataFile);
    }

    protected void encryptDirTest(IDirectoryProcessor<NormalKey> directoryProcessor, IEncryptionAlgorithm<NormalKey> encryptionAlgorithm) {
        try {
            directoryProcessor.encryptDir(encryptionAlgorithm, normalKey);
        } catch (IOException | InterruptedException e) {
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
        } catch (IOException | InterruptedException e) {
            String message = String.format("The folder decryption failed\nAlgo - %s\nError message - %s", encryptionAlgorithm.getEncryptionMethod(),
                    e.getMessage());
            fail(message);
        }
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 1; i <= numberFiles; i++) {
            String file1 = dataFile.getPath() + File.separator + "file" + i + ".txt";
            String file2 = decryptFile.getPath() + File.separator + "file" + i + ".txt";
            Runnable myThread = () -> {
                Thread.currentThread().setName(new File(file1).getName());
                assertTrue(compareTwoFiles(file1, file2));
                System.out.println(new File(file1).getName() + " is good");
            };

            Thread run = new Thread(myThread);
            threads.add(run);
            run.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                ErrorLog4jLogger.writeErrorToLog(this.getClass(), e.getMessage());
            }
        }
    }
}