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

    public static String encryptToString(IEncryptionAlgorithm<NormalKey> algo) throws IllegalArgumentException {
        if (algo instanceof ShiftMultiplyEncryption) {
            return "ShiftMultiply";
        } else if (algo instanceof ShiftUpEncryption) {
            return "ShiftUp";
        } else if (algo instanceof XorEncryption) {
            return "Xor";
        }
        throw new IllegalArgumentException("The data value at XML algo can't get " + algo.getClass());
    }
}