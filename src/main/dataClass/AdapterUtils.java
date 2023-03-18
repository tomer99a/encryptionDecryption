package dataClass;

import encryption.IEncryptionAlgorithm;
import encryption.charAlgo.ShiftMultiplyEncryption;
import encryption.charAlgo.ShiftUpEncryption;
import encryption.charAlgo.XorEncryption;
import keys.NormalKey;

public class AdapterUtils {
    public static IEncryptionAlgorithm<NormalKey> stringToEncrypt(String algoName) {
        switch (algoName) {
            case "ShiftMultiply":
                return new ShiftMultiplyEncryption();
            case "ShiftUp":
                return new ShiftUpEncryption();
            case "Xor":
                return new XorEncryption();
        }
        throw new IllegalArgumentException("The data value at XML algo is incorrect");
    }
}