package encryptionDecryption;

import static encryptionDecryption.generalMethods.*;
import java.util.Scanner;

public class DecryptionAlgorithm {
    public DecryptionAlgorithm() {
    }

    public static char decryptChar(char c, int n, int start, int end){
        if((int) c >= start && end >= (int) c){
            if((int) c - n < start)
                return (char) ((int) c - n + 25);
            else
                return (char) ((int) c - n);
        }
        return c;
    }

    public static void decryption(){
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Please enter the path to the encryption source file");
//        String encryption_path = myScanner.nextLine();  // Read user input
        String encryption_path = "C:\\Users\\Tomer\\Desktop\\encryptionDecryption\\src\\encryptionDecryption\\input text_encrypted.txt";

        System.out.println("Please enter the path to the key file");
//        String key_path = myScanner.nextLine();  // Read user input
        String key_path = "C:\\Users\\Tomer\\Desktop\\encryptionDecryption\\src\\encryptionDecryption\\key.txt";

        String txt = readFile(encryption_path);
        String key = readFile(key_path).charAt(0)+"";
        int n = Integer.parseInt(key);

        StringBuilder str = new StringBuilder();
        for (int i=0; i<txt.length(); i++){
            char charToAdd = txt.charAt(i);
            charToAdd = decryptChar(charToAdd, n, 65, 90);
            charToAdd = decryptChar(charToAdd, n, 97, 122);
            str.append(charToAdd);
        }
        String originalPath = encryption_path.substring(0, encryption_path.indexOf("_")) + encryption_path.substring(encryption_path.indexOf("."));
        String decrypted_path = addSuffixFileName(originalPath, "decrypted");
        writeToFile(decrypted_path, str.toString());
        System.out.println("Location of the decrypted file is - " + decrypted_path);
    }
}
