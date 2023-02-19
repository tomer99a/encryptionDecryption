package encryptionDecryption.utils;

import encryptionDecryption.general.encryptsDecrypt;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static encryptionDecryption.utils.GeneralMethods.scanLines;

public class IOMethods {
    /**
     * Scan the input file line by line and put the changed lines into the output line
     * @param inputPath path to input file
     * @param outputPath path to output file
     * @param encryptsDecrypt interface with the function to change the line
     * @param key key to usr to encrypt/decrypt
     */
    public static void scanAndSubmitFile(String inputPath, String outputPath, encryptsDecrypt encryptsDecrypt, int key){
        try (Scanner sc = new Scanner(new FileInputStream(inputPath), StandardCharsets.UTF_8)) {
            while (sc.hasNextLine())
                writeLine(outputPath, scanLines(sc.nextLine(), encryptsDecrypt, key));

//             note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } catch (IOException e) {
            System.err.println("failed to scan file");
        }
    }

    /**
     * Open file and append to in message
     * @param path path to file should be written.
     * @param line message to be written into file.
     */
    public static void writeLine(String path, String line) {
        try (Writer output = new BufferedWriter(new FileWriter(path, true))) {
            output.append(line);
        } catch (IOException e) {
            System.err.println("failed to write to file " + path);
        }
    }

    /**
     * Creat file at the given path and delete the exists if was one.
     * @param path file path and name
     */
    public static void createFile(String path){
        try {
            final File myObj = new File(path);
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
     * Write the given message to the file at the given path
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
     * Read all file from the path given.
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
