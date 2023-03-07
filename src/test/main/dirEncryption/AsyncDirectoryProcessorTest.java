package dirEncryption;

import encryption.IEncryptionAlgorithm;
import encryption.charAlgo.ShiftMultiplyEncryption;
import encryption.charAlgo.ShiftUpEncryption;
import encryption.charAlgo.XorEncryption;
import keys.NormalKey;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AsyncDirectoryProcessorTest extends DirectoryProcessorAbstractTest {

    public AsyncDirectoryProcessorTest() throws IOException {
    }

    @Test
    @DisplayName("check encryption of dir in a async with xor")
    void encryptDirXor() {
        try {
            AsyncDirectoryProcessor<NormalKey> asyncDirectoryProcessor = new AsyncDirectoryProcessor<>(dataFile.getPath());
            encryptDirTest(asyncDirectoryProcessor, new XorEncryption());
        } catch (IOException e) {
            String message = String.format("The async folder encryption failed\nAlgo - Xor\nError message - %s", e.getMessage());
            fail(message);
        }
    }

    @Test
    @DisplayName("check encryption of dir in a async with up")
    void encryptDirPlus() {
        try {
            AsyncDirectoryProcessor<NormalKey> asyncDirectoryProcessor = new AsyncDirectoryProcessor<>(dataFile.getPath());
            encryptDirTest(asyncDirectoryProcessor, new ShiftUpEncryption());
        } catch (IOException e) {
            String message = String.format("The async folder encryption failed\nAlgo - Up\nError message - %s", e.getMessage());
            fail(message);
        }
    }

    @Test
    @DisplayName("check encryption of dir in a async with multi")
    void encryptDirMulti() {
        try {
            AsyncDirectoryProcessor<NormalKey> asyncDirectoryProcessor = new AsyncDirectoryProcessor<>(dataFile.getPath());
            encryptDirTest(asyncDirectoryProcessor, new ShiftMultiplyEncryption());
        } catch (IOException e) {
            String message = String.format("The async folder encryption failed\nAlgo - Multi\nError message - %s", e.getMessage());
            fail(message);
        }
    }

    @Test
    @DisplayName("check decryption of dir in a async with xor")
    void decryptDirXor() {
        try {
            AsyncDirectoryProcessor<NormalKey> asyncDirectoryProcessor = new AsyncDirectoryProcessor<>(dataFile.getPath());
            IEncryptionAlgorithm<NormalKey> encryptionAlgorithm = new XorEncryption();
            asyncDirectoryProcessor.encryptDir(encryptionAlgorithm, normalKey);
            decryptDirTest(asyncDirectoryProcessor, encryptionAlgorithm);
        } catch (IOException e) {
            String message = String.format("The async folder decryption failed\nAlgo - Xor\nError message - %s", e.getMessage());
            fail(message);
        }
    }

    @Test
    @DisplayName("check decryption of dir in a async with plus")
    void decryptDirPlus() {
        try {
            AsyncDirectoryProcessor<NormalKey> asyncDirectoryProcessor = new AsyncDirectoryProcessor<>(dataFile.getPath());
            IEncryptionAlgorithm<NormalKey> encryptionAlgorithm = new ShiftUpEncryption();
            asyncDirectoryProcessor.encryptDir(encryptionAlgorithm, normalKey);
            decryptDirTest(asyncDirectoryProcessor, encryptionAlgorithm);
        } catch (IOException e) {
            String message = String.format("The async folder decryption failed\nAlgo - Plus\nError message - %s", e.getMessage());
            fail(message);
        }
    }

    @Test
    @DisplayName("check decryption of dir in a async with multi")
    void decryptDirMulti() {
        try {
            AsyncDirectoryProcessor<NormalKey> asyncDirectoryProcessor = new AsyncDirectoryProcessor<>(dataFile.getPath());
            IEncryptionAlgorithm<NormalKey> encryptionAlgorithm = new ShiftMultiplyEncryption();
            asyncDirectoryProcessor.encryptDir(encryptionAlgorithm, normalKey);
            decryptDirTest(asyncDirectoryProcessor, encryptionAlgorithm);
        } catch (IOException e) {
            String message = String.format("The async folder decryption failed\nAlgo - Multi\nError message - %s", e.getMessage());
            fail(message);
        }
    }
}