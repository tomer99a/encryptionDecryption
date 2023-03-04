package dirEncryption;

import encryption.charAlgo.XorEncryption;
import keys.NormalKey;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static utilsTest.helpers.compareTwoFiles;

class SyncDirectoryProcessorTest extends DirectoryProcessorAbstractTest{

    public SyncDirectoryProcessorTest() throws IOException {
    }

    @Test
    @DisplayName("check encryption of dir in a sync")
    void encryptDir() {
        SyncDirectoryProcessor<NormalKey> syncDirectoryProcessor;
        try {
            syncDirectoryProcessor = new SyncDirectoryProcessor<>(dataFile.getPath());
            syncDirectoryProcessor.encryptDir(new XorEncryption<>(), normalKey);
        } catch (IOException e) {
            String message = String.format("The folder encryption failed\nError message - %s", e.getMessage());
            fail(message);
        }
        for (int i = 1; i <= 10; i++) {
            String file1 = dataFile.getPath() + File.separator + "file" + i + ".txt";
            String file2 = encryptFile.getPath() + File.separator + "file" + i + ".txt";
            assertFalse(compareTwoFiles(file1, file2));
        }
    }

    @Test
    void decryptDir() {
    }
}