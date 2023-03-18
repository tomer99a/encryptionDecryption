package schema;

import encryption.IEncryptionAlgorithm;
import encryption.charAlgo.ShiftMultiplyEncryption;
import encryption.charAlgo.ShiftUpEncryption;
import encryption.charAlgo.XorEncryption;
import keys.NormalKey;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class XMLAlgoAdapter extends XmlAdapter<String, IEncryptionAlgorithm<NormalKey>> {
    @Override
    public IEncryptionAlgorithm<NormalKey> unmarshal(String algoName) throws IllegalArgumentException {
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

    @Override
    public String marshal(IEncryptionAlgorithm<NormalKey> algo) throws IllegalArgumentException {
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
