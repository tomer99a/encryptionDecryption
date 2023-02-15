package encryptionDecryption.decryption;

import java.util.Scanner;

import static encryptionDecryption.utils.GeneralMethods.addSuffixFileName;
import static encryptionDecryption.utils.IOMethods.*;

public abstract class DecryptionAlgorithmAbstract implements DecryptionAlgorithmInterface{
    protected String encryptionPath;
    protected String keyPath;
    protected String decryptedPath;

    public void act(){
        getPaths();
        final int key = Integer.parseInt(readFile(keyPath).charAt(0)+"");
        creatFile(decryptedPath);
        scanAndSubmitFile(encryptionPath, decryptedPath, this, key);
        System.out.println("Location of the decrypted file is - " + decryptedPath);
    }

    public void getPaths(){
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Please enter the path to the encryption source file");
        encryptionPath = myScanner.nextLine();  // Read user input
//        encryptionPath = "src\\encryptionDecryption\\data\\input text_encrypted.txt";

        System.out.println("Please enter the path to the key file");
        keyPath = myScanner.nextLine();  // Read user input
//        keyPath = "src\\encryptionDecryption\\data\\key.txt";

        String originalPath = encryptionPath.substring(0, encryptionPath.indexOf("_")) + encryptionPath.substring(encryptionPath.indexOf("."));
        decryptedPath = addSuffixFileName(originalPath, "decrypted");
    }
}
