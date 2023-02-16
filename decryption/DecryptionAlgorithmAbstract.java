package encryptionDecryption.decryption;

import java.io.File;
import java.util.Scanner;

import static encryptionDecryption.utils.GeneralMethods.addSuffixFileName;
import static encryptionDecryption.utils.GeneralMethods.pathFromUser;
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

        String originalPath = encryptionPath.substring(0, encryptionPath.indexOf("_")) + encryptionPath.substring(encryptionPath.indexOf("."));
        String decryptedPath = addSuffixFileName(new File(originalPath), "decrypted");

        int key;
        try{
            String keyStr = readFile(keyPath);
            if(keyStr.indexOf('\n') != -1)
                keyStr = keyStr.substring(0, keyStr.indexOf('\n'));

            key = Integer.parseInt(keyStr);
        } catch (NumberFormatException e) {
            System.err.println("The key file doesn't contain number");
            return;
        }
        createFile(decryptedPath);
        scanAndSubmitFile(encryptionPath, decryptedPath, this, key);
        System.out.println("Location of the decrypted file is - " + decryptedPath);
    }
}
