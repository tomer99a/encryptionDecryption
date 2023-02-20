package main.java.decryption;

import java.io.IOException;

import static main.java.utils.GeneralMethods.getKeyFromFile;
import static main.java.utils.IOMethods.*;

public abstract class DecryptionAlgorithmAbstract implements DecryptionAlgorithmInterface{
    protected String decryptionMethod;

    public DecryptionAlgorithmAbstract(String decryptionMethod) {
        this.decryptionMethod = decryptionMethod;
    }

    public String getDecryptionMethod() {
        return decryptionMethod;
    }

    public void act(String originalPath, String outputPath, String keyPath) throws IOException {
        final int key = getKeyFromFile(keyPath);

        createFile(outputPath);
        scanAndSubmitFile(originalPath, outputPath, this, key);
        System.out.println("Location of the decrypted file is - " + outputPath);
    }

    @Override
    public char handleCher(char c, int key) {
        return c;
    }
}
