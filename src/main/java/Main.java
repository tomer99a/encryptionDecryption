package main.java;

import main.java.decryption.*;
import main.java.encryption.*;
import main.java.general.FileEncryptor;

import java.util.Scanner;

public class Main {
    private static void menu() throws Exception {
        String fileName = "alpha";
        String basePath = "src\\main\\java\\data\\";
        String originalPath = basePath + fileName + ".txt";
        String encryptedPath = basePath + fileName + "_encrypted.txt";
        String decryptedPath = basePath + fileName + "_decrypted.txt";
        String keyPath = basePath + "key.txt";

        String invalidChoiceErrorMessage = "You should write 1, 2 or 3 only!!!";
        boolean doneLoop = false;
        Scanner myScanner = new Scanner(System.in);

        EncryptionAlgorithmInterface encryptionAlgorithm;
        DecryptionAlgorithmInterface decryptionAlgorithm;
        String algoName = "up";

        switch (algoName) {
            case "up":
                encryptionAlgorithm = new ShiftUpEncryption();
                decryptionAlgorithm = new ShiftUpDecryption();
                break;
            case "multi":
                encryptionAlgorithm = new ShiftMultiplyEncryption();
                decryptionAlgorithm = new ShiftMultiplyDecryption();
                break;
            default:
                encryptionAlgorithm = new XorEncryption();
                decryptionAlgorithm = new XorDecrypt();
                break;
        }
        String algo2 = "doublewskj";
        switch (algo2) {
            case "repeat":
                int repeatNum = 6;
                encryptionAlgorithm = new RepeatEncryption(repeatNum, encryptionAlgorithm);
                decryptionAlgorithm = new RepeatDecryption(repeatNum, decryptionAlgorithm);
                break;

            case "double":
                encryptionAlgorithm = new DoubleEncryption(encryptionAlgorithm);
                decryptionAlgorithm = new DoubleDecryption(decryptionAlgorithm);
                break;
        }
        FileEncryptor fileEncryptor;
        try {
            fileEncryptor = new FileEncryptor(encryptionAlgorithm, decryptionAlgorithm);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }
        while (!doneLoop) {
            int choice;

            System.out.println("Hello user! please choose number:\n1 - encryption\n2 - decryption\n3 - exit");

            try {
                choice = Integer.parseInt(myScanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println(invalidChoiceErrorMessage);
                continue;
            }
            switch (choice) {
                case 1:
                    fileEncryptor.encrypt(originalPath, encryptedPath, keyPath);
                    break;
                case 2:
                    fileEncryptor.decrypt(encryptedPath, decryptedPath, keyPath);
                    break;
                case 3:
                    doneLoop = true;
                    break;
                default:
                    System.err.println(invalidChoiceErrorMessage);
                    break;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        menu();
    }
}
