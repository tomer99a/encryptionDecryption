package encryptionDecryption.encryption;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static encryptionDecryption.utils.GeneralMethods.repeatAct;
import static encryptionDecryption.utils.IOMethods.copyFile;

public class RepeatEncryption extends EncryptionAlgorithmAbstract {
    final private EncryptionAlgorithmInterface algo;
    final private int repeatNum;

    public RepeatEncryption(int n, EncryptionAlgorithmInterface algo) {
        super("Repeat");
        this.repeatNum = n;
        this.algo = algo;
        String className = algo.getClass().toString();
        className = className.substring(className.lastIndexOf("."), className.lastIndexOf("E"));
        this.encryptionMethod += className;
    }

    @Override
    public void act(String originalPath, String outputPath, String keyPath) throws IOException {
        repeatAct(repeatNum, originalPath, outputPath, keyPath, algo);
    }
}
