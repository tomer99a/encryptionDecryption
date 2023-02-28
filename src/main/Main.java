import encryption.charAlgo.ShiftUpEncryption;
import encryption.generalsAlgo.DoubleEncryption;
import keys.DoubleKey;
import keys.NormalKey;

import java.io.File;
import java.util.Scanner;

//import org.apache.log4j.Logger;
//import org.apache.log4j.LogManager;

public class Main {
    private static void menu() {
        String fileName = "input_text";
        String basePath = "src" + File.separator + "main" + File.separator + "data" + File.separator;
        String originalPath = basePath + fileName + ".txt";
        String encryptedPath = basePath + fileName + "_encrypted.txt";
        String decryptedPath = basePath + fileName + "_decrypted.txt";
        String keyPath = basePath + "key.txt";

        String invalidChoiceErrorMessage = "You should write 1, 2 or 3 only!!!";
        boolean doneLoop = false;
        Scanner myScanner = new Scanner(System.in);

        ShiftUpEncryption charEncryptionAlgorithm = new ShiftUpEncryption();

//        DoubleEncryption<DoubleKey> encryptionAlgorithm = new DoubleEncryption<DoubleKey>(charEncryptionAlgorithm);

        FileEncryptor<NormalKey> fileEncryptor = new FileEncryptor<NormalKey>(charEncryptionAlgorithm);
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
                    fileEncryptor.encrypt(originalPath, encryptedPath, new NormalKey(keyPath));
                    break;
                case 2:
                    fileEncryptor.decrypt(encryptedPath, decryptedPath, new NormalKey(keyPath));
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
//        Logger logger = LogManager.getLogger(Main.class);
//        logger.info("adsfasdfadsf");
//        logger.error("ERRORRR");
        menu();
        System.out.println("Done program");
    }
}
