package encryptionDecryption.decryption;

import java.io.File;

import static encryptionDecryption.utils.GeneralMethods.addSuffixFileName;
import static encryptionDecryption.utils.GeneralMethods.pathFromUser;
import static encryptionDecryption.utils.GeneralMethods.getKeyFromFile;
import static encryptionDecryption.utils.IOMethods.*;

public abstract class DecryptionAlgorithmAbstract implements DecryptionAlgorithmInterface{
    protected String decryptionMethod;

    public DecryptionAlgorithmAbstract(String decryptionMethod) {
        this.decryptionMethod = decryptionMethod;
    }

    public String getDecryptionMethod() {
        return decryptionMethod;
    }

    public void act(){
        String encryptionPath = pathFromUser("encryption", "_encrypted");
        String keyPath = pathFromUser("key", "");

        String originalPath = encryptionPath.substring(0, encryptionPath.lastIndexOf("_")) + encryptionPath.substring(encryptionPath.indexOf("."));
        String decryptedPath = addSuffixFileName(new File(originalPath), "decrypted");

        final int key = getKeyFromFile(keyPath);

        createFile(decryptedPath);
        scanAndSubmitFile(encryptionPath, decryptedPath, this, key);
        System.out.println("Location of the decrypted file is - " + decryptedPath);
    }
}
