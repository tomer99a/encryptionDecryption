package encryptionDecryption;

import encryptionDecryption.decryption.*;

import encryptionDecryption.encryption.*;
import encryptionDecryption.general.FileEncryptor;

import java.util.Scanner;

public class Main {
    private static void menu() throws Exception {
        String fileName = "alpha";
        String basePath = "src\\encryptionDecryption\\data\\";
        String originalPath = basePath + fileName + ".txt";
        String encryptedPath = basePath + fileName + "_encrypted.txt";
        String decryptedPath = basePath + fileName + "_decrypted.txt";
        String keyPath = basePath + "key.txt";

        String invalidChoiceErrorMessage = "You should write 1, 2 or 3 only!!!";
        boolean doneLoop = false;
        Scanner myScanner = new Scanner(System.in);

        EncryptionAlgorithmInterface encryptionAlgorithm;
        DecryptionAlgorithmInterface decryptionAlgorithm;
        String algoName = "multi";

        switch (algoName) {
            case "up" -> {
                encryptionAlgorithm = new ShiftUpEncryption();
                decryptionAlgorithm = new ShiftUpDecryption();
            }
            case "multi" -> {
                encryptionAlgorithm = new ShiftMultiplyEncryption();
                decryptionAlgorithm = new ShiftMultiplyDecryption();
            }
            default -> {
                encryptionAlgorithm = new XorEncryption();
                decryptionAlgorithm = new XorDecrypt();
            }

        }
        String algo2 = "repeat";
        switch (algo2) {
            case "repeat" -> {
                int repeatNum = 6;
                encryptionAlgorithm = new RepeatEncryption(repeatNum, encryptionAlgorithm);
                decryptionAlgorithm = new RepeatDecryption(repeatNum, decryptionAlgorithm);
            }

            case "double" -> {
                encryptionAlgorithm = new DoubleEncryption(encryptionAlgorithm);
                decryptionAlgorithm = new DoubleDecryption(decryptionAlgorithm);
            }
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
                case 1 -> fileEncryptor.encrypt(originalPath, encryptedPath, keyPath);
                case 2 -> fileEncryptor.decrypt(encryptedPath, decryptedPath, keyPath);
                case 3 -> doneLoop = true;
                default -> System.err.println(invalidChoiceErrorMessage);
            }
        }
    }

    public static void main(String[] args) throws Exception {
//        Integer[] a = {245, 246, 247, 240, 241, 242, 243, 252, 253, 254, 255, 248, 249, 250, 251, 228, 229, 230, 231, 224, 225, 226, 227, 236, 237, 238, 213, 214, 215, 208, 209, 210, 211, 220, 221, 222, 223, 216, 217, 218, 219, 196, 197, 198, 199, 192, 193, 194, 195, 204, 205, 206};
//        int min = Collections.min(Arrays.asList(a));
//
//        // using Collections.max()
//        // to find maximum element
//        // using only 1 line.
//        int max = Collections.max(Arrays.asList(a));
//
//        List<Integer> d = Arrays.asList(a);
//        Collections.sort(d);
//        System.out.println(d);
//
//        // printing minimum and maximum numbers
//        System.out.println("Minimum number of array is : "
//                + min);
//        System.out.println("Maximum number of array is : "
//                + max);
        menu();
    }
}
