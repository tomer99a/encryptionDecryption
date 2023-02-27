package Logs;

public class EncryptionLogEventArgs {
    public static String getMessage(String message) {
        switch (message){
            case "start encryption":
                return "Start encryption";
            case "end encryption":
                return "End encryption";
            case "start decryption":
                return "Start decryption";
            case "end decryption":
                return "End decryption";
            default:
                return "";
        }
    }
}
