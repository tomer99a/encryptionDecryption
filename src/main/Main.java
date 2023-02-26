import Encryption.*;
import Encryption.CharAlgo.CharEncryptionAlgorithmAbstract;
import Encryption.CharAlgo.ShiftMultiplyEncryption;
import Encryption.CharAlgo.ShiftUpEncryption;
import Encryption.CharAlgo.XorEncryption;
import Encryption.GeneralsAlgo.DoubleEncryption;
import Encryption.GeneralsAlgo.RepeatEncryption;

import java.io.File;
import java.util.Scanner;

public class Main {
    private static void menu() {
        String fileName = "input_text";
        String basePath = "src" + File.separator + "main" + File.separator + "Data" + File.separator;
        String originalPath = basePath + fileName + ".txt";
        String encryptedPath = basePath + fileName + "_encrypted.txt";
        String decryptedPath = basePath + fileName + "_decrypted.txt";
        String keyPath = basePath + "kgdsfsdfsey.txt";

        String invalidChoiceErrorMessage = "You should write 1, 2 or 3 only!!!";
        boolean doneLoop = false;
        Scanner myScanner = new Scanner(System.in);

        CharEncryptionAlgorithmAbstract charEncryptionAlgorithm;
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
        IEncryptionAlgorithm encryptionAlgorithm = charEncryptionAlgorithm;
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
        FileEncryptor fileEncryptor = new FileEncryptor(encryptionAlgorithm);
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

    public static void main(String[] args) {
        System.out.println(Math.pow(2,3));
//        menu();
        System.out.println("Done program");
    }
}
