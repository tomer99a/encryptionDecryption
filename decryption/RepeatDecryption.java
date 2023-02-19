package encryptionDecryption.decryption;

import java.io.IOException;

import static encryptionDecryption.utils.GeneralMethods.repeatAct;

public class RepeatDecryption extends DecryptionAlgorithmAbstract{
    final private DecryptionAlgorithmInterface algo;
    final private int repeatNum;

    public RepeatDecryption(int n, DecryptionAlgorithmInterface algo) {
        super("Repeat");
        this.algo = algo;
        this.repeatNum = n;
        String className = algo.getClass().toString();
        className = className.substring(className.lastIndexOf("."), className.lastIndexOf("D"));
        this.decryptionMethod += className;
    }

    @Override
    public void act(String originalPath, String outputPath, String keyPath) throws IOException {
        repeatAct(repeatNum, originalPath, outputPath, keyPath, algo);
    }
}
