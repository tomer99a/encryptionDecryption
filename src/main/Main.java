import encryption.*;
import general.FileEncryptor;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    private static void menu() throws Exception {
        String fileName = "input_text";
        Path basePath = Paths.get("src\\main\\data\\");
        Path originalPath = Paths.get(String.valueOf(basePath), fileName + ".txt");
        Path encryptedPath = Paths.get(String.valueOf(basePath), fileName + "_encrypted.txt");
        Path decryptedPath = Paths.get(String.valueOf(basePath), fileName + "_decrypted.txt");
        Path keyPath = Paths.get(String.valueOf(basePath), "key.txt");

        String invalidChoiceErrorMessage = "You should write 1, 2 or 3 only!!!";
        boolean doneLoop = false;
        Scanner myScanner = new Scanner(System.in);

        EncryptionAlgorithmInterface encryptionAlgorithm;
        String algoName = "up";

        switch (algoName) {
            case "up":
                encryptionAlgorithm = new ShiftUpEncryption();
                break;
            case "multi":
                encryptionAlgorithm = new ShiftMultiplyEncryption();
                break;
            default:
                encryptionAlgorithm = new XorEncryption();
                break;
        }
        String algo2 = "doublewskj";
        switch (algo2) {
            case "repeat":
                int repeatNum = 6;
                encryptionAlgorithm = new RepeatEncryption(repeatNum, encryptionAlgorithm);
                break;

            case "double":
                encryptionAlgorithm = new DoubleEncryption(encryptionAlgorithm);
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
