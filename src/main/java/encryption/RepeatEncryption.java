package main.java.encryption;

import java.io.IOException;

import static main.java.utils.GeneralMethods.repeatAct;

public class RepeatEncryption extends EncryptionAlgorithmAbstract {
    final private EncryptionAlgorithmInterface algo;
    final private int repeatNum;

    public RepeatEncryption(int n, EncryptionAlgorithmInterface algo) {
        super("Repeat");
        this.repeatNum = n;
        this.algo = algo;
        String className = algo.getEncryptionMethod();
        this.encryptionMethod += className;
    }

    @Override
    public void act(String originalPath, String outputPath, String keyPath) throws IOException {
        repeatAct(repeatNum, originalPath, outputPath, keyPath, algo);
    }
}
