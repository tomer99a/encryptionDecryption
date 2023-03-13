import dirEncryption.AsyncDirectoryProcessor;
import dirEncryption.SyncDirectoryProcessor;
import encryption.IEncryptionAlgorithm;
import encryption.charAlgo.ShiftMultiplyEncryption;
import encryption.charAlgo.ShiftUpEncryption;
import encryption.generalsAlgo.DoubleEncryption;
import keys.DoubleKey;
import keys.NormalKey;
import log.ErrorLog4jLogger;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static void menu() {
        String fileName = "input_text";
        String basePath = "src" + File.separator + "main" + File.separator + "data" + File.separator;
        String originalPath = basePath + fileName + ".txt";
        String encryptedPath = basePath + fileName + "_encrypted.txt";
        String decryptedPath = basePath + fileName + "_decrypted.txt";
        String keyPath1 = basePath + "key1.txt";
        String keyPath2 = basePath + "key2.txt";
        DoubleKey doubleKey = new DoubleKey(keyPath1, keyPath2);

        String invalidChoiceErrorMessage = "You should write 1, 2 or 3 only!!!";
        boolean doneLoop = false;
        Scanner myScanner = new Scanner(System.in);

        IEncryptionAlgorithm<DoubleKey> iEncryptionAlgorithm = new DoubleEncryption(new ShiftMultiplyEncryption());

        FileEncryptor<DoubleKey> fileEncryptor = new FileEncryptor<>(iEncryptionAlgorithm);
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
                    fileEncryptor.encrypt(originalPath, encryptedPath, doubleKey);
                    break;
                case 2:
                    fileEncryptor.decrypt(encryptedPath, decryptedPath, doubleKey);
                    break;
                case 3:
                    doneLoop = true;
                    break;
                default:
                    ErrorLog4jLogger.writeErrorToLog(iEncryptionAlgorithm.getClass(), invalidChoiceErrorMessage);
                    break;
            }
        }
    }

    private static void dirEncrypt() {
        String basePath = "src" + File.separator + "main" + File.separator + "data";
        String keyPath = basePath + File.separator + "key.txt";
        String keyPath1 = basePath + File.separator + "key1.txt";
        String keyPath2 = basePath + File.separator + "key2.txt";
        NormalKey normalKey = new NormalKey(keyPath);
        DoubleKey doubleKey = new DoubleKey(keyPath1, keyPath2);

        String invalidChoiceErrorMessage = "You should write only number between 1 to 5!!!";
        boolean doneLoop = false;
        Scanner myScanner = new Scanner(System.in);

        while (!doneLoop) {
            int choice;

            System.out.println("Hello user! please choose number:" +
                    "\n1 - Encryption folder async (with threads)" +
                    "\n2 - Decryption folder async (with threads)" +
                    "\n3 - Encryption folder sync (without threads)" +
                    "\n4 - Encryption folder sync (without threads)" +
                    "\n5 - Exit");

            try {
                choice = Integer.parseInt(myScanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println(invalidChoiceErrorMessage);
                continue;
            }
            try {
                switch (choice) {
                    case 1:
                        new AsyncDirectoryProcessor<NormalKey>(basePath).encryptDir(new ShiftUpEncryption(), normalKey);
                        break;
                    case 2:
                        new AsyncDirectoryProcessor<NormalKey>(basePath).decryptDir(new ShiftUpEncryption(), normalKey);
                        break;
                    case 3:
                        new SyncDirectoryProcessor<NormalKey>(basePath).encryptDir(new ShiftUpEncryption(), normalKey);
                        break;
                    case 4:
                        new SyncDirectoryProcessor<NormalKey>(basePath).decryptDir(new ShiftUpEncryption(), normalKey);
                        break;
                    case 5:
                        doneLoop = true;
                        break;
                    default:
                        System.err.println(invalidChoiceErrorMessage);
                        break;
                }
            } catch (IOException | InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        dirEncrypt();
        System.out.println("Done program");
    }
}
