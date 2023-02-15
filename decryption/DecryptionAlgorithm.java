package decryption;

import static utils.generalMethods.*;
import java.util.Scanner;

public class DecryptionAlgorithm {

    /**
     *
     * @param c char to encrypt
     * @param key key to use for encrypt
     * @param start start of the ASCII sequence
     * @param end end of the ASCII sequence
     * @return the decryption char that you're looking for
     */
    public static char decryptChar(char c, int key, int start, int end){
        if((int) c >= start && end >= (int) c){
            if((int) c - key < start)
                return (char) ((int) c - key + 25);
            else
                return (char) ((int) c - key);
        }
        return c;
    }
//    public static char decryptChar(char c, int key, int start, int end){
//        int newAscii = (int) c - key;
//        return start <= (int) c && (int) c <= end ? (newAscii <= start ? (char) (newAscii+25) : (char) newAscii) : c;
//    }

    public static void decryption(){
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Please enter the path to the encryption source file");
//        String encryptionPath = myScanner.nextLine();  // Read user input
        String encryptionPath = "src\\data\\input text_encrypted.txt";

        System.out.println("Please enter the path to the key file");
//        String keyPath = myScanner.nextLine();  // Read user input
        String keyPath = "src\\data\\key.txt";

        String txt = readFile(encryptionPath);
        int key = Integer.parseInt(readFile(keyPath).charAt(0)+"");

        StringBuilder str = new StringBuilder();
        for (int i=0; i<txt.length(); i++){
            char charToAdd = txt.charAt(i);
            charToAdd = decryptChar(charToAdd, key, 65, 90);
            charToAdd = decryptChar(charToAdd, key, 97, 122);
            str.append(charToAdd);
        }
        String originalPath = encryptionPath.substring(0, encryptionPath.indexOf("_")) + encryptionPath.substring(encryptionPath.indexOf("."));
        String decryptedPath = addSuffixFileName(originalPath, "decrypted");
        writeToFile(decryptedPath, str.toString());
        System.out.println("Location of the decrypted file is - " + decryptedPath);
    }
}
