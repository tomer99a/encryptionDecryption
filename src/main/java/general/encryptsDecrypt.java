package main.java.general;

import java.io.IOException;

public interface encryptsDecrypt {
    int myPrimeNumber = 53;
    int mySpecialChar = 248;

    void act(String originalPath, String outputPath, String keyPath) throws IOException;
    char handleCher(char c, int key);
}
