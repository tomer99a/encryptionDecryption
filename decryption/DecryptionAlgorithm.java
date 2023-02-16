package encryptionDecryption.decryption;

import encryptionDecryption.interfaces.encryptsDecrypt;
import encryptionDecryption.utils.GeneralMethods.*;

import java.io.File;
import java.util.Scanner;

import static encryptionDecryption.utils.GeneralMethods.*;
import static encryptionDecryption.utils.IOMethods.*;

public class DecryptionAlgorithm implements encryptsDecrypt {
    public void act(){
        String encryptionPath = pathFromUser("encryption", "_encrypted");
        String keyPath = pathFromUser("key", "");

        String originalPath = encryptionPath.substring(0, encryptionPath.indexOf("_")) + encryptionPath.substring(encryptionPath.indexOf("."));
        String decryptedPath = addSuffixFileName(new File(originalPath), "decrypted");

        int key = 0; //TODO: should I init the key to 0?
        try{
            String keyStr = readFile(keyPath);
            if(keyStr.charAt(keyStr.length()-1) == '\n')
                keyStr = keyStr.substring(0, keyStr.length()-1);
            key = Integer.parseInt(keyStr);
        } catch (NumberFormatException e) {
            System.err.println("The key file doesn't contain number");
        }
        createFile(decryptedPath);
        scanAndSubmitFile(encryptionPath, decryptedPath, this, key);
        System.out.println("Location of the decrypted file is - " + decryptedPath);
    }

    /**
     * decrypt the char by key
     * @param c char to encrypt
     * @param key key to use for encrypt
     * @param start start of the ASCII sequence
     * @param end end of the ASCII sequence
     * @return the decryption char that you're looking for
     */
    public char handleCher(char c, int key, int start, int end){
        if((int) c >= start && end >= (int) c)
            if((int) c - key < start)
                return (char) ((int) c - key + end - start);
            else
                return (char) ((int) c - key);
        return c;
    }
}
