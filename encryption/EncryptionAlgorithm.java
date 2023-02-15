package encryptionDecryption.encryption;

import encryptionDecryption.interfaces.encryptsDecrypt;

import java.io.File;
import java.util.Scanner;

import static encryptionDecryption.utils.GeneralMethods.*;
import static encryptionDecryption.utils.IOMethods.*;

public class EncryptionAlgorithm implements encryptsDecrypt {
    private String originalPath;
    private String encryptedPath;
    private String keyPath;

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
        while(!new File(originalPath).exists()) {
            System.err.println("The given path is incorrect");
            System.out.println("Please enter the path to the input source file");
            originalPath = myScanner.nextLine();
        }

        encryptedPath = addSuffixFileName(originalPath, "encrypted");
        keyPath = originalPath.substring(0, originalPath.lastIndexOf("\\")+1) + "key.txt";
    }

    /**
     * @param c char to encrypt
     * @param key key to use for encrypt
     * @param start start of the ASCII sequence
     * @param end end of the ASCII sequence
     * @return the encryption char that you're looking for
     */
    public char handleCher(char c, int key, int start, int end){
        if((int) c >= start && end >= (int) c){
            if((int) c + key >= end)
                return (char) ((int) c + key - end + start);
            else
                return (char) ((int) c + key);
        }
        return c;
    }
}
