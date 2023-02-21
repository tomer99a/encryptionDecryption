package utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.nio.file.Path;

import encryption.EncryptionAlgorithmInterface;
import static utils.GeneralMethods.scanLines;

public class IOMethods {
    /**
     * Scan the input file line by line and put the changed lines into the output line
     * @param inputPath path to input file
     * @param outputPath path to output file
     * @param encryptsDecrypt interface with the function to change the line
     * @param key key to usr to encrypt/decrypt
     */
    public static void scanAndSubmitFile(boolean encrypt, Path inputPath, Path outputPath, EncryptionAlgorithmInterface encryptsDecrypt, int key){
        try (Scanner sc = new Scanner(new FileInputStream(String.valueOf(inputPath)), String.valueOf(StandardCharsets.UTF_8))) {
            while (sc.hasNextLine()){
                String lineToWrite = scanLines(encrypt, sc.nextLine(), encryptsDecrypt, key);
                if(sc.hasNextLine())
                    lineToWrite += "\r\n";
                writeLine(outputPath, lineToWrite);
            }

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
    public static void writeLine(Path path, String line) {
        try (Writer output = new BufferedWriter(new FileWriter(String.valueOf(path), true))) {
            output.append(line);
        } catch (IOException e) {
            System.err.println("failed to write to file " + path);
        }
    }

    /**
     * Creat file at the given path and delete the exists if was one.
     * @param path file path and name
     */
    public static void createFile(Path path){
        try {
            final File MY_OBJ = new File(String.valueOf(path));
            if(MY_OBJ.exists())
                if(!MY_OBJ.delete())
                    throw new IOException("unable to delete existing file");
            if (!MY_OBJ.createNewFile())
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
    public static void writeToFile(Path path, String message){
        try (FileWriter myWriter = new FileWriter(String.valueOf(path))) {
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
    public static String readFile(Path path){
        StringBuilder txt = new StringBuilder();
        try (Scanner myReader = new Scanner(new File(String.valueOf(path)))) {
            while (myReader.hasNextLine()) {
                txt.append(myReader.nextLine()).append((char) 10);
            }
        } catch (FileNotFoundException e) {
            System.err.printf("failed to read %s file", path);
        }
        return txt.toString();
    }

    /**
     * copy content of one file to another
     * @param originalPath file path to copy from
     * @param newPath file path to copy
     */
    public static void copyFile(Path originalPath, Path newPath)
    {
        File originalFile = new File(String.valueOf(originalPath));
        File newFile = new File(String.valueOf(newPath));

        try (FileInputStream in = new FileInputStream(originalFile); FileOutputStream out = new FileOutputStream(newFile)) {
            int tmpChar;
            while ((tmpChar = in.read()) != -1) {
                out.write(tmpChar);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
