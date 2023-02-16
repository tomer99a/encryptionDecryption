package encryptionDecryption.encryption;

import encryptionDecryption.interfaces.encryptsDecrypt;

import java.io.File;
import java.util.Scanner;

import static encryptionDecryption.utils.GeneralMethods.*;
import static encryptionDecryption.utils.IOMethods.*;

public class EncryptionAlgorithm implements encryptsDecrypt {
    public void act(){
        String originalPath = pathFromUser("input", "");
        String encryptedPath = addSuffixFileName(originalPath, "encrypted");
        String keyPath = originalPath.substring(0, originalPath.lastIndexOf("\\") + 1) + "key.txt";

        final int key = 10 + (int) (Math.random()*100); // get random number 10 to 110;
        creatFile(keyPath);
        creatFile(encryptedPath);
        scanAndSubmitFile(originalPath, encryptedPath, this, key);
        writeToFile(keyPath, Integer.toString(key));
        System.out.printf("Location of the files are -\nencrypted - %s\nkey - %s%n", encryptedPath, keyPath);
    }

    /**
     * encrypt the char by key
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
