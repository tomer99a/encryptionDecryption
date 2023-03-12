package dirEncryption;

import encryption.IEncryptionAlgorithm;
import handler.EventHandler;
import log.ErrorLog4jLogger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AsyncDirectoryProcessor<T> extends DirectoryProcessorAbstract<T> {
    // Maximum number of threads in thread pool
    static final int MAX_T = 5;

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
        ArrayList<Future<?>> tasks = new ArrayList<>();

        ExecutorService pool = Executors.newFixedThreadPool(MAX_T);
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
                Future<?> future = pool.submit(myThread);
                tasks.add(future);
            }
        }
        pool.shutdown();

        for (Future<?> future : tasks) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                ErrorLog4jLogger.writeErrorToLog(this.getClass(), e.getMessage());
            }
        }
        calculateTime(isEncrypt ? "encrypt" : "decrypt");
    }
}
