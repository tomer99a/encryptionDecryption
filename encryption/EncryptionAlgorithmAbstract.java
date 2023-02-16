package encryptionDecryption.encryption;

import java.util.Scanner;

import static encryptionDecryption.utils.GeneralMethods.addSuffixFileName;
import static encryptionDecryption.utils.IOMethods.*;

public abstract class EncryptionAlgorithmAbstract implements EncryptionAlgorithmInterface{
    protected String encryptionMethod;
    protected int key;

    protected String originalPath;
    protected String encryptedPath;
    protected String keyPath;

    public EncryptionAlgorithmAbstract(String encryptionMethod) {
        this.encryptionMethod = encryptionMethod;
        generateKey();
    }

    public String getEncryptionMethod() {
        return encryptionMethod;
    }

    public void act(){
        setPath();
        creatFile(keyPath);
        creatFile(encryptedPath);
        scanAndSubmitFile(originalPath, encryptedPath, this, key);
        writeToFile(keyPath, Integer.toString(key));
        System.out.printf("Location of the files are -\nencrypted - %s\nkey - %s%n", encryptedPath, keyPath);
    }

    public void setPath(){
        System.out.println("Please enter the path to the input source file");
        Scanner myScanner = new Scanner(System.in);
//        originalPath = myScanner.nextLine();
        originalPath = "src\\encryptionDecryption\\data\\input text.txt";

        encryptedPath = addSuffixFileName(originalPath, "encrypted");
        keyPath = originalPath.substring(0, originalPath.lastIndexOf("\\")+1) + "key.txt";
    }
}
