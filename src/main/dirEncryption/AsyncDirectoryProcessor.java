package dirEncryption;

import encryption.IEncryptionAlgorithm;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AsyncDirectoryProcessor<T> extends DirectoryProcessorAbstract<T> {

    public AsyncDirectoryProcessor(String dirPath) throws IOException {
        super(dirPath);
    }

    @Override
    public void encryptDir(IEncryptionAlgorithm<T> algo, T key) throws IOException {
        addDirSafe(encryptDir);
        File folder = new File(dirPath);
        File[] listOfFiles = folder.listFiles();

        assert listOfFiles != null;
        ArrayList<Thread> threads = new ArrayList<>();
        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                String fileName = file.getName();
                if (fileName.contains("key")) {
                    continue;
                }
                Runnable myThread = () -> {
                    handelEncrypt(fileName, file, algo, key);
                    Thread.currentThread().setName(fileName);
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
    }

    @Override
    public void decryptDir(IEncryptionAlgorithm<T> algo, T key) throws IOException {

    }
}
