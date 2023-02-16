package encryptionDecryption;

import encryptionDecryption.decryption.DecryptionAlgorithm;
import encryptionDecryption.encryption.EncryptionAlgorithm;

import java.util.Scanner;

public class Main {
    private static void menu(){
        String invalidChoiceErrorMessage = "You should write 1, 2 or 3 only!!!";
        boolean doneLoop = false;
        Scanner myScanner = new Scanner(System.in);
        while (!doneLoop){
            System.out.println("""
                    Hello user! please choose number
                    1 - encryption
                    2 - decryption
                    3 - exit""");
            int choice;
            try{
                choice = Integer.parseInt(myScanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println(invalidChoiceErrorMessage);
                continue;
            }
            switch (choice) {
                case 1 -> new EncryptionAlgorithm().act();
                case 2 -> new DecryptionAlgorithm().act();
                case 3 -> doneLoop = true;
                default -> System.err.println(invalidChoiceErrorMessage);
            }
        }
    }

    public static void main(String[] args){
        menu();
    }
}
