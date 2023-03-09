package dirEncryption;

import encryption.IEncryptionAlgorithm;
import handler.EventHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AsyncDirectoryProcessor<T> extends DirectoryProcessorAbstract<T> {

    public AsyncDirectoryProcessor(String dirPath) throws IOException {
        super(dirPath);
    }

    @Override
    public void encryptDir(IEncryptionAlgorithm<T> algo, T key) throws IOException {
        help(algo, key, new File(dirPath), encryptDir, true);
        new EventHandler(algo.getClass()).encrypt(false, false);
    }

    @Override
    public void decryptDir(IEncryptionAlgorithm<T> algo, T key) throws IOException {
        help(algo, key, encryptDir, decryptDir, false);
        new EventHandler(algo.getClass()).decrypt(false, false);
    }

    private void help(IEncryptionAlgorithm<T> algo, T key, File inputFolder, File outputFolder, boolean isEncrypt) throws IOException {
        addDirSafe(outputFolder);
        File[] listOfFiles = inputFolder.listFiles();
        assert listOfFiles != null;
        ArrayList<Thread> threads = new ArrayList<>();
        startTimeMillis = System.currentTimeMillis();

        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                String fileName = file.getName();
                if (fileName.contains("key")) {
                    continue;
                }
                Runnable myThread = () -> {
                    Thread.currentThread().setName(fileName);
                    useAlgo(algo, key, fileName, outputFolder, file, isEncrypt);
                };
                Thread run = new Thread(myThread);
                threads.add(run);
                run.start();
            }
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
        calculateTime(isEncrypt ? "encrypt" : "decrypt");
    }
}
