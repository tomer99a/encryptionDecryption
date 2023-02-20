package main.java.decryption;

import java.io.IOException;

import static main.java.utils.GeneralMethods.repeatAct;

public class RepeatDecryption extends DecryptionAlgorithmAbstract{
    final private DecryptionAlgorithmInterface algo;
    final private int repeatNum;

    public RepeatDecryption(int n, DecryptionAlgorithmInterface algo) {
        super("Repeat");
        this.algo = algo;
        this.repeatNum = n;
        String className = algo.getDecryptionMethod();
        this.decryptionMethod += className;
    }

    @Override
    public void act(String originalPath, String outputPath, String keyPath) throws IOException {
        repeatAct(repeatNum, originalPath, outputPath, keyPath, algo);
    }
}