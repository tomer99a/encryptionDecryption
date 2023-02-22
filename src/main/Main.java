import encryption.*;
import encryption.charAlgo.CharEncryptionAlgorithmInterface;
import encryption.charAlgo.ShiftMultiplyEncryption;
import encryption.charAlgo.ShiftUpEncryption;
import encryption.charAlgo.XorEncryption;

import java.io.File;
import java.util.Scanner;

public class Main {
    private static void menu() throws Exception {
        String fileName = "input_text";
        String basePath = "src" + File.separator + "main" + File.separator + "data" + File.separator;
        String originalPath = basePath + fileName + ".txt";
        String encryptedPath = basePath + fileName + "_encrypted.txt";
        String decryptedPath = basePath + fileName + "_decrypted.txt";
        String keyPath = basePath + "key.txt";

        String invalidChoiceErrorMessage = "You should write 1, 2 or 3 only!!!";
        boolean doneLoop = false;
        Scanner myScanner = new Scanner(System.in);

        CharEncryptionAlgorithmInterface charEncryptionAlgorithm;
        String algoName = "up";

        switch (algoName) {
            case "up":
                charEncryptionAlgorithm = new ShiftUpEncryption();
                break;
            case "multi":
                charEncryptionAlgorithm = new ShiftMultiplyEncryption();
                break;
            default:
                charEncryptionAlgorithm = new XorEncryption();
                break;
        }
        EncryptionAlgorithmInterface encryptionAlgorithm = charEncryptionAlgorithm;
        String algo2 = "doublewskj";
        switch (algo2) {
            case "repeat":
                int repeatNum = 6;
                encryptionAlgorithm = new RepeatEncryption(repeatNum, charEncryptionAlgorithm);
                break;

            case "double":
                encryptionAlgorithm = new DoubleEncryption(charEncryptionAlgorithm);
                break;
        }
        FileEncryptor fileEncryptor;
        try {
            fileEncryptor = new FileEncryptor(encryptionAlgorithm);
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
