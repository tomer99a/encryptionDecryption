package encryptionDecryption.encryption;

import encryptionDecryption.interfaces.encryptsDecrypt;

import static encryptionDecryption.utils.GeneralMethods.*;
import static encryptionDecryption.utils.IOMethods.*;

public class EncryptionAlgorithm implements encryptsDecrypt {
    private String originalPath;
    private String encryptedPath;
    private String keyPath;

    public void act(){
        getPaths();
        mainAct();
    }

    public void getPaths(){
        System.out.println("Please enter the path to the input source file");
        originalPath = "src\\encryptionDecryption\\data\\input text.txt";

        encryptedPath = addSuffixFileName(originalPath, "encrypted");
        keyPath = originalPath.substring(0, originalPath.lastIndexOf("\\")+1) + "key.txt";
    }

    public void mainAct(){
        final int key = 1 + (int) (Math.random()*9); // get random number 1 to 10;
//        String txt = readFile(originalPath);
//        StringBuilder str = new StringBuilder();
//        for (int i = 0; i < txt.length(); i++) {
//            char charToAdd = txt.charAt(i);
//            charToAdd = handleCher(charToAdd, key,'A', 'Z');
//            charToAdd = handleCher(charToAdd, key,'a', 'z');
//            str.append(charToAdd);
//        }

        creatFile(keyPath);
        creatFile(encryptedPath);

        writeToFile(keyPath, Integer.toString(key));
        scanAndSubmitFile(originalPath, encryptedPath, this, key);
//        writeToFile(encryptedPath, str.toString());

        System.out.printf("Location of the files are -\nencrypted - %s\nkey - %s%n", encryptedPath, keyPath);
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
