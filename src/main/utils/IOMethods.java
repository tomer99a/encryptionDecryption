package utils;

import encryption.charAlgo.CharEncryptionAlgorithmAbstract;
import exceptions.invalidPathException;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Scanner;

public class IOMethods {
    /**
     * Scan the input file line by line and put the changed lines into the output line
     *
     * @param inputPath       path to input file
     * @param outputPath      path to output file
     * @param encryptsDecrypt interface with the function to change the line
     * @param key             key to usr to encrypt/decrypt
     */
    public static void scanAndSubmitFile(boolean encrypt, String inputPath, String outputPath, CharEncryptionAlgorithmAbstract encryptsDecrypt, int key, Logger logger) throws IOException {
        Scanner sc = new Scanner(new FileInputStream(inputPath));
        int j = 0;
        int numLineToAddDebug = 100;
        while (sc.hasNextLine()) {
            StringBuilder lineToWrite = new StringBuilder();
            String line = sc.nextLine();
            for (int i = 0; i < line.length(); i++) {
                if (encrypt)
                    lineToWrite.append(encryptsDecrypt.encryptChar(line.charAt(i), key));
                else
                    lineToWrite.append(encryptsDecrypt.decryptChar(line.charAt(i), key));
            }
            if (sc.hasNextLine())
                lineToWrite.append(System.lineSeparator());
            j++;
            if (j % numLineToAddDebug == numLineToAddDebug-1)  {
                logger.debug("write 100 lines to file " + outputPath);
            }
            writeLine(outputPath, lineToWrite.toString());
        }

        // note that Scanner suppresses exceptions
        if (sc.ioException() != null)
            throw sc.ioException();

        sc.close();
    }

    /**
     * Open file and append to in message
     *
     * @param path path to file should be written.
     * @param line message to be written into file.
     */
    public static void writeLine(String path, String line) throws IOException {
        if (!new File(path).getParentFile().exists())
            throw new invalidPathException("The path given is invalid");
        Writer output = new BufferedWriter(new FileWriter(path, true));
        output.append(line);
        output.close();
    }

    /**
     * Creat file at the given path and delete the exists if was one.
     *
     * @param path file path and name
     */
    public static void createFile(String path, Logger logger) throws IOException {
        final File myObj = new File(path);
        if (!myObj.getParentFile().exists())
            throw new invalidPathException("The path given is invalid");
        if (!myObj.exists()) {
            if (myObj.createNewFile()) {
                logger.debug(String.format("The file %s created", myObj.getName()));
            } else {
                logger.error(String.format("File %s didn't created", myObj.getName()));
            }
        }
    }

    /**
     * delete file if was existed.
     *
     * @param path file path and name
     */
    public static void deleteFile(String path, Logger logger) throws IOException {
        final File myObj = new File(path);
        if (!myObj.getParentFile().exists())
            throw new invalidPathException("The path given is invalid");
        if (myObj.exists()) {
            if (myObj.delete()) {
                logger.debug(String.format("File %s deleted", myObj.getName()));
            } else {
                logger.error(String.format("File %s didn't delete", myObj.getName()));
            }
        }
    }

    /**
     * Write the given message to the file at the given path
     *
     * @param path    file path and name
     * @param message message to write into path
     */
    public static void writeToFile(String path, String message, Logger logger) throws IOException {
        File file = new File(path);
        if (!file.exists())
            throw new invalidPathException("The path given is invalid");
        FileWriter myWriter = new FileWriter(path);
        myWriter.write(message);
        myWriter.close();
        logger.debug(String.format("File %s hase been written", file.getName()));
    }

    /**
     * Read all file from the path given.
     *
     * @param path file path and name
     * @return string of the text to the given file combine and separated by \n char
     */
    public static String readFile(String path) throws FileNotFoundException, invalidPathException {
        if (!new File(path).getParentFile().exists())
            throw new invalidPathException("The path given is invalid");
        StringBuilder txt = new StringBuilder();
        Scanner myReader = new Scanner(new File(path));
        while (myReader.hasNextLine())
            txt.append(myReader.nextLine()).append(System.lineSeparator());
        myReader.close();
        return txt.toString();
    }

    /**
     * copy content of one file to another
     *
     * @param originalPath file path to copy from
     * @param newPath      file path to copy
     */
    public static void copyFile(String originalPath, String newPath) throws IOException {
        if (!new File(originalPath).getParentFile().exists())
            throw new invalidPathException("The path given is invalid");
        if (!new File(newPath).getParentFile().exists())
            throw new invalidPathException("The path given is invalid");
        File originalFile = new File(originalPath);
        File newFile = new File(newPath);

        FileInputStream in = new FileInputStream(originalFile);
        FileOutputStream out = new FileOutputStream(newFile);

        int tmpChar;
        while ((tmpChar = in.read()) != -1)
            out.write(tmpChar);

        in.close();
        out.close();
    }
}
