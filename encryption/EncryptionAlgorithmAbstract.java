package encryptionDecryption.encryption;

import java.io.File;
import java.util.Random;

import static encryptionDecryption.utils.GeneralMethods.addSuffixFileName;
import static encryptionDecryption.utils.GeneralMethods.pathFromUser;
import static encryptionDecryption.utils.IOMethods.*;

public abstract class EncryptionAlgorithmAbstract implements EncryptionAlgorithmInterface{
    protected String encryptionMethod;
    protected int key;

    public EncryptionAlgorithmAbstract(String encryptionMethod) {
        this.encryptionMethod = encryptionMethod;
        generateKey();
    }

    public String getEncryptionMethod() {
        return encryptionMethod;
    }

    public void generateKey(){
        this.key = new Random().nextInt(1000) + 1;
    }

    public void act(){
        String originalPath = pathFromUser("input", "");
        File file = new File(originalPath);

        String encryptedPath = addSuffixFileName(file, "encrypted");
        String keyPath = file.getParent() + "\\key.txt";

        createFile(keyPath);
        createFile(encryptedPath);

        scanAndSubmitFile(originalPath, encryptedPath, this, key);
        writeToFile(keyPath, Integer.toString(key));

        System.out.printf("Location of the files are -\nencrypted - %s\nkey - %s%n", encryptedPath, keyPath);
    }
}
