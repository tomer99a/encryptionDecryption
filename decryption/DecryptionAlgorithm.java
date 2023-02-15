package encryptionDecryption.decryption;

import static encryptionDecryption.utils.GeneralMethods.*;
import static encryptionDecryption.utils.IOMethods.*;

public class DecryptionAlgorithm {
    private String encryptionPath;
    private String keyPath;
    private String decryptedPath;

    public void decryption(){
        getPaths();
        decrypt();
    }

    private void getPaths(){
//        Scanner myScanner = new Scanner(System.in);
        System.out.println("Please enter the path to the encryption source file");
//        String encryptionPath = myScanner.nextLine();  // Read user input
        encryptionPath = "src\\encryptionDecryption\\data\\input text_encrypted.txt";

        System.out.println("Please enter the path to the key file");
//        String keyPath = myScanner.nextLine();  // Read user input
        keyPath = "src\\encryptionDecryption\\data\\key.txt";

        String originalPath = encryptionPath.substring(0, encryptionPath.indexOf("_")) + encryptionPath.substring(encryptionPath.indexOf("."));
        decryptedPath = addSuffixFileName(originalPath, "decrypted");
    }

    private void decrypt(){

        String txt = readFile(encryptionPath);
        int key = Integer.parseInt(readFile(keyPath).charAt(0)+"");

        StringBuilder str = new StringBuilder();
        for (int i=0; i<txt.length(); i++){
            char charToAdd = txt.charAt(i);
            charToAdd = decryptChar(charToAdd, key, 'A', 'Z');
            charToAdd = decryptChar(charToAdd, key,'a', 'z');
            str.append(charToAdd);
        }
        writeToFile(decryptedPath, str.toString());
        System.out.println("Location of the decrypted file is - " + decryptedPath);
    }

    /**
     *
     * @param c char to encrypt
     * @param key key to use for encrypt
     * @param start start of the ASCII sequence
     * @param end end of the ASCII sequence
     * @return the decryption char that you're looking for
     */
    private char decryptChar(char c, int key, int start, int end){
        if((int) c >= start && end >= (int) c)
            if((int) c - key < start)
                return (char) ((int) c - key + end - start);
            else
                return (char) ((int) c - key);
        return c;
    }
}
