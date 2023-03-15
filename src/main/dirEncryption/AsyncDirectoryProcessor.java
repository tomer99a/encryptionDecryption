package dirEncryption;

import encryption.IEncryptionAlgorithm;
import handler.EventHandler;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class AsyncDirectoryProcessor<T> extends DirectoryProcessorAbstract<T> {
    // Maximum number of threads in thread pool is the number of CPU at the computer.
    static final int MAX_T = Runtime.getRuntime().availableProcessors();
    static final private ReentrantLock LOCK = new ReentrantLock();

    public AsyncDirectoryProcessor(String dirPath) throws IOException {
        super(dirPath);
    }

    public static ReentrantLock getLOCK() {
        return LOCK;
    }

    @Override
    public void encryptDir(IEncryptionAlgorithm<T> algo, T key) throws IOException, InterruptedException {
        encryptDecryptBody(algo, key, new File(dirPath), encryptDir, true);
    }

    @Override
    public void decryptDir(IEncryptionAlgorithm<T> algo, T key) throws IOException, InterruptedException {
        encryptDecryptBody(algo, key, encryptDir, decryptDir, false);
    }

    private void encryptDecryptBody(IEncryptionAlgorithm<T> algo, T key, File inputFolder, File outputFolder, boolean isEncrypt) throws IOException,
            InterruptedException {
        addDirSafe(outputFolder);
        File[] listOfFiles = inputFolder.listFiles();
        assert listOfFiles != null;

        ExecutorService pool = Executors.newFixedThreadPool(MAX_T);
        startTimeMillis = System.currentTimeMillis();

        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                String fileName = file.getName();
                if (fileName.contains("key")) {
                    continue;
                }
                pool.submit(() -> {
                    Thread.currentThread().setName(fileName);
                    useAlgo(algo, key, fileName, outputFolder, file, isEncrypt);
                });
            }
        }

        pool.shutdown();
        if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
            throw new RuntimeException();
        }
        calculateTime(isEncrypt ? "encrypt" : "decrypt");
    }
}
