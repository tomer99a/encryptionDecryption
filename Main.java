package encryptionDecryption;

import encryptionDecryption.decryption.DecryptionAlgorithmInterface;
import encryptionDecryption.decryption.ShiftUpDecryption;
import encryptionDecryption.decryption.ShiftMultiplyDecryption;
import encryptionDecryption.decryption.DoubleDecryption;

import encryptionDecryption.encryption.EncryptionAlgorithmInterface;
import encryptionDecryption.encryption.ShiftUpEncryption;
import encryptionDecryption.encryption.ShiftMultiplyEncryption;
import encryptionDecryption.encryption.DoubleEncryption;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static void menu() throws IOException {
        String fileName = "alpha";
        String basePath = "src\\encryptionDecryption\\data\\";
        String originalPath = basePath + fileName + ".txt";
        String encryptedPath = basePath + fileName + "_encrypted.txt";
        String decryptedPath = basePath + fileName + "_decrypted.txt";
        String keyPath = basePath + "key.txt";

        String invalidChoiceErrorMessage = "You should write 1, 2 or 3 only!!!";
        boolean doneLoop = false;
        Scanner myScanner = new Scanner(System.in);
        while (!doneLoop) {
            int choice;
            EncryptionAlgorithmInterface encryptionAlgorithm;
            DecryptionAlgorithmInterface decryptionAlgorithm;
            System.out.println("""
                    Please choose method:
                    1 - Shift up
                    2 - Multiply
                    3 - Double""");
            try {
                choice = Integer.parseInt(myScanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println(invalidChoiceErrorMessage);
                continue;
            }
            switch (choice) {
                case 1 -> {
                    encryptionAlgorithm = new ShiftUpEncryption();
                    decryptionAlgorithm = new ShiftUpDecryption();
                }
                case 2 -> {
                    encryptionAlgorithm = new ShiftMultiplyEncryption();
                    decryptionAlgorithm = new ShiftMultiplyDecryption();
                }
                case 3 -> {
                    if(new Random().nextInt(2) == 0){
                        encryptionAlgorithm = new ShiftUpEncryption();
                        decryptionAlgorithm = new ShiftUpDecryption();
                    } else {
                        encryptionAlgorithm = new ShiftMultiplyEncryption();
                        decryptionAlgorithm = new ShiftMultiplyDecryption();
                    }
                    encryptionAlgorithm = new DoubleEncryption(encryptionAlgorithm);
                    decryptionAlgorithm = new DoubleDecryption(decryptionAlgorithm);
                }
                default -> {
                    System.err.println(invalidChoiceErrorMessage);
                    continue;
                }
            }

            System.out.println("""
                    Hello user! please choose number:
                    1 - encryption
                    2 - decryption
                    3 - exit""");

            try {
                choice = Integer.parseInt(myScanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println(invalidChoiceErrorMessage);
                continue;
            }
            switch (choice) {
                case 1 -> encryptionAlgorithm.act(originalPath, encryptedPath, keyPath);
                case 2 -> decryptionAlgorithm.act(encryptedPath, decryptedPath, keyPath);
                case 3 -> doneLoop = true;
                default -> System.err.println(invalidChoiceErrorMessage);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        menu();
    }
}
