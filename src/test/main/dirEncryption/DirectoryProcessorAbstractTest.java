package dirEncryption;

import keys.NormalKey;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.SecureRandom;

import static utils.IOMethods.writeToFile;

abstract class DirectoryProcessorAbstractTest {
    final protected File dataFile;
    final protected File encryptFile;
    final protected File decryptFile;
    final protected NormalKey normalKey;

    public DirectoryProcessorAbstractTest() throws IOException {
        boolean didCreate;
        dataFile = new File(Files.createTempDirectory("data").toString());
        for (int i = 1; i <= 10; i++) {
            File file = new File(dataFile, "file" + i + ".txt");
            didCreate = file.createNewFile();
            writeToFile(file.getPath(), buildBigText());
        }
        System.out.println(dataFile.getAbsolutePath());
        encryptFile = new File(dataFile, "encrypted");
        decryptFile = new File(dataFile, "decrypted");

        String keyPath = dataFile.getPath() + File.separator + "key.txt";
        normalKey = new NormalKey(keyPath);

        //TODO: add after all that delete all the tmp files.
    }

    private String buildBigText() {
        StringBuilder str = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < 3000000; i++) {
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

}