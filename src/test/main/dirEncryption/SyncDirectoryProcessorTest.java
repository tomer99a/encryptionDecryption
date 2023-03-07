package dirEncryption;

import encryption.IEncryptionAlgorithm;
import encryption.charAlgo.ShiftMultiplyEncryption;
import encryption.charAlgo.ShiftUpEncryption;
import encryption.charAlgo.XorEncryption;
import keys.NormalKey;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

class SyncDirectoryProcessorTest extends DirectoryProcessorAbstractTest {
    public SyncDirectoryProcessorTest() throws IOException {
    }

    @Test
    @DisplayName("check encryption of dir in a sync with xor")
    void encryptDirXor() {
        try {
            SyncDirectoryProcessor<NormalKey> syncDirectoryProcessor = new SyncDirectoryProcessor<>(dataFile.getPath());
            encryptDirTest(syncDirectoryProcessor, new XorEncryption());
        } catch (IOException e) {
            String message = String.format("The folder encryption failed\nAlgo - Xor\nError message - %s", e.getMessage());
            fail(message);
        }
    }

    @Test
    @DisplayName("check encryption of dir in a sync with plus")
    void encryptDirPlus() {
        try {
            SyncDirectoryProcessor<NormalKey> syncDirectoryProcessor = new SyncDirectoryProcessor<>(dataFile.getPath());
            encryptDirTest(syncDirectoryProcessor, new ShiftUpEncryption());
        } catch (IOException e) {
            String message = String.format("The folder encryption failed\nAlgo - plus\nError message - %s", e.getMessage());
            fail(message);
        }
    }

    @Test
    @DisplayName("check encryption of dir in a sync with multi")
    void encryptDirMulti() {
        try {
            SyncDirectoryProcessor<NormalKey> syncDirectoryProcessor = new SyncDirectoryProcessor<>(dataFile.getPath());
            encryptDirTest(syncDirectoryProcessor, new ShiftMultiplyEncryption());
        } catch (IOException e) {
            String message = String.format("The folder encryption failed\nAlgo - multi\nError message - %s", e.getMessage());
            fail(message);
        }
    }

    @Test
    @DisplayName("check decryption of dir in a sync with xor")
    void decryptDirXor() {
        try {
            SyncDirectoryProcessor<NormalKey> syncDirectoryProcessor = new SyncDirectoryProcessor<>(dataFile.getPath());
            IEncryptionAlgorithm<NormalKey> encryptionAlgorithm = new XorEncryption();
            syncDirectoryProcessor.encryptDir(encryptionAlgorithm, normalKey);
            decryptDirTest(syncDirectoryProcessor, encryptionAlgorithm);
        } catch (IOException e) {
            String message = String.format("The folder decryption failed\nAlgo - Xor\nError message - %s", e.getMessage());
            fail(message);
        }
    }

    @Test
    @DisplayName("check decryption of dir in a sync with plus")
    void decryptDirPlus() {
        try {
            SyncDirectoryProcessor<NormalKey> syncDirectoryProcessor = new SyncDirectoryProcessor<>(dataFile.getPath());
            IEncryptionAlgorithm<NormalKey> encryptionAlgorithm = new ShiftUpEncryption();
            syncDirectoryProcessor.encryptDir(encryptionAlgorithm, normalKey);
            decryptDirTest(syncDirectoryProcessor, encryptionAlgorithm);
        } catch (IOException e) {
            String message = String.format("The folder decryption failed\nAlgo - Plus\nError message - %s", e.getMessage());
            fail(message);
        }
    }

    @Test
    @DisplayName("check decryption of dir in a sync with multi")
    void decryptDirMulti() {
        try {
            SyncDirectoryProcessor<NormalKey> syncDirectoryProcessor = new SyncDirectoryProcessor<>(dataFile.getPath());
            IEncryptionAlgorithm<NormalKey> encryptionAlgorithm = new ShiftMultiplyEncryption();
            syncDirectoryProcessor.encryptDir(encryptionAlgorithm, normalKey);
            decryptDirTest(syncDirectoryProcessor, encryptionAlgorithm);
        } catch (IOException e) {
            String message = String.format("The folder decryption failed\nAlgo - Multi\nError message - %s", e.getMessage());
            fail(message);
        }
    }
}