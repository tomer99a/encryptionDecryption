package com.company;

import java.util.Scanner;
import static com.company.generalMethods.*;

public class DecryptionAlgorithm {
    public DecryptionAlgorithm() {
    }

    public static void decryption(){
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Please enter the path to the encryption source file");
//        String encryption_path = myScanner.nextLine();  // Read user input
        String encryption_path = "C:\\Users\\Tomer\\Desktop\\encryptor\\src\\com\\company\\input text_encrypted.txt";

        System.out.println("Please enter the path to the key file");
//        String key_path = myScanner.nextLine();  // Read user input
        String key_path = "C:\\Users\\Tomer\\Desktop\\encryptor\\src\\com\\company\\key.txt";

        String txt = readFile(encryption_path);
        String key = readFile(key_path).charAt(0)+"";

        StringBuilder str = new StringBuilder();
        for (int i=0; i<txt.length(); i++){
            char c = txt.charAt(i);
            str.append(c);
            if(Character.isLetter(c) && i+1<txt.length() && key.equals(txt.charAt(i+1)+"")){
                i++;
            }
        }
        String originalPath = encryption_path.substring(0, encryption_path.indexOf("_")) + encryption_path.substring(encryption_path.indexOf("."));
        String decrypted_path = addSuffixFileName(originalPath, "decrypted");
        writeToFile(decrypted_path, str.toString());
        System.out.println("Location of the decrypted file is - " + decrypted_path);
    }
}
