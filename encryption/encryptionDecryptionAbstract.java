package encryptionDecryption.encryption;

import java.util.Scanner;

import static encryptionDecryption.utils.GeneralMethods.addSuffixFileName;
import static encryptionDecryption.utils.IOMethods.*;

public abstract class encryptionDecryptionAbstract implements EncryptionAlgorithmInterface{
    protected String originalPath;
    protected String encryptedPath;
    protected String keyPath;

    public void act(){
        getPaths();
        final int key = 1 + (int) (Math.random()*9); // get random number 1 to 10;
        creatFile(keyPath);
        creatFile(encryptedPath);
        scanAndSubmitFile(originalPath, encryptedPath, this, key);
        writeToFile(keyPath, Integer.toString(key));
        System.out.printf("Location of the files are -\nencrypted - %s\nkey - %s%n", encryptedPath, keyPath);
    }

    public void getPaths(){
        System.out.println("Please enter the path to the input source file");
        Scanner myScanner = new Scanner(System.in);
        originalPath = myScanner.nextLine();
//        originalPath = "src\\encryptionDecryption\\data\\input text.txt";

        encryptedPath = addSuffixFileName(originalPath, "encrypted");
        keyPath = originalPath.substring(0, originalPath.lastIndexOf("\\")+1) + "key.txt";
    }
}
