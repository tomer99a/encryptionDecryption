package encryption;

import java.io.IOException;
import java.nio.file.Path;

public interface EncryptionAlgorithmInterface {
    void encrypt(Path originalPath, Path outputPath, Path keyPath) throws IOException;
    void decrypt(Path originalPath, Path outputPath, Path keyPath) throws IOException;

    String getEncryptionMethod();
}
