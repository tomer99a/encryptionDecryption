package encryptionDecryption.utils;

import encryptionDecryption.general.encryptsDecrypt;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static encryptionDecryption.utils.GeneralMethods.scanLines;

public class IOMethods {
    public static void scanAndSubmitFile(String inputPath, String outputPath, encryptsDecrypt encryptsDecrypt, int key){
        try (Scanner sc = new Scanner(new FileInputStream(inputPath), StandardCharsets.UTF_8)) {
            while (sc.hasNextLine()) {
                writeLine(outputPath, scanLines(sc.nextLine(), encryptsDecrypt, key));
            }
//             note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } catch (IOException e) {
            System.err.println("failed to scan file");
            e.printStackTrace();
        }
    }

    public static void writeLine(String path, String line) throws IOException {
        Writer output = null;
        try{
            output = new BufferedWriter(new FileWriter(path, true));
            output.append(line);
        } catch (IOException e) {
            System.err.println("failed to write to file " + path);
        } finally {
            assert output != null;
            output.close();
        }
    }

    /**
     * creat file at the given path
     * @param path file path and name
     */
    public static void creatFile(String path){
        try {
            File myObj = new File(path);
            if(myObj.exists())
                if(!myObj.delete())
                    throw new IOException("unable to delete existing file");
            if (!myObj.createNewFile())
                throw new IOException("failed to creat %s file " + path);

        } catch (IOException e) {
            System.err.print(e.getMessage());
        }
    }

    /**
     * write the given message to the file at the given path
     * @param path file path and name
     * @param message message to write into path
     */
    public static void writeToFile(String path, String message){
        try (FileWriter myWriter = new FileWriter(path)) {
            myWriter.write(message);
        } catch (IOException e) {
            System.err.printf("failed to write to %s file", path);
        }
    }

    /**
     *
     * @param path file path and name
     * @return string of the text to the given file combine and separated by \n char
     */
    public static String readFile(String path){
        StringBuilder txt = new StringBuilder();
        try (Scanner myReader = new Scanner(new File(path))) {
            while (myReader.hasNextLine()) {
                txt.append(myReader.nextLine()).append("\n");
            }
        } catch (FileNotFoundException e) {
            System.err.printf("failed to read %s file", path);
        }
        return txt.toString();
    }
}
