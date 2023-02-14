package com.company;

import java.util.Scanner;

public class Main {

    public static void menu(){
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Hello user!\nif you want to Encryption press 1 and if you want to chose Decryption press 2");
        String userName = myScanner.nextLine();  // Read user input
        if(userName.equals("1"))
            EncryptionAlgorithm.encryption();
        else if (userName.equals("2"))
            DecryptionAlgorithm.decryption();
        else
            System.out.println("You should write 1 or 2 only");
    }

    public static void main(String[] args) {
        EncryptionAlgorithm.encryption();
        System.out.println("---------------");
        DecryptionAlgorithm.decryption();
//        encryptionDecryption

    }
}
