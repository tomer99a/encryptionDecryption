package encryptionDecryption.general;

import java.io.IOException;

public interface encryptsDecrypt {
    void act(String originalPath, String outputPath, String keyPath) throws IOException;
    char handleCher(char c, int key);
}
