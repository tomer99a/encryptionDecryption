package encryptionDecryption;

import encryptionDecryption.decryption.ShiftUpDecryption;
import encryptionDecryption.encryption.ShiftUpEncryption;

import java.util.Scanner;

public class Main {
    public static void menu(){
        String errorMessage = "You should write 1, 2 or 3 only!!!";
        boolean doneLoop = false;
        Scanner myScanner = new Scanner(System.in);
        while (!doneLoop){
            System.out.println("""
                    Hello user! please choose number
                    1 - encryption
                    2 - decryption
                    3 - exit""");
            int ans;
            try{
                ans = Integer.parseInt(myScanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println(errorMessage);
                continue;
            }
            switch (ans) {
                case 1 -> new ShiftUpEncryption().act();
                case 2 -> new ShiftUpDecryption().act();
                case 3 -> doneLoop = true;
                default -> System.err.println(errorMessage);
            }
        }
    }

    public static void main(String[] args){
        menu();
    }
}
