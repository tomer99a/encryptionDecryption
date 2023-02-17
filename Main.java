package encryptionDecryption;

import encryptionDecryption.decryption.DecryptionAlgorithmInterface;
import encryptionDecryption.decryption.ShiftMultiplyDecryption;
import encryptionDecryption.decryption.ShiftUpDecryption;
import encryptionDecryption.encryption.DoubleEncryption;
import encryptionDecryption.encryption.EncryptionAlgorithmInterface;
import encryptionDecryption.encryption.ShiftMultiplyEncryption;
import encryptionDecryption.encryption.ShiftUpEncryption;

import java.util.Scanner;

public class Main {
    private static void menu() {
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
                    3 - Double
                    """);
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
                    encryptionAlgorithm = new DoubleEncryption();
                    decryptionAlgorithm = new ShiftUpDecryption();
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
                case 1 -> encryptionAlgorithm.act();
                case 2 -> decryptionAlgorithm.act();
                case 3 -> doneLoop = true;
                default -> System.err.println(invalidChoiceErrorMessage);
            }
        }
    }

    public static void main(String[] args) {
        menu();
    }
}
