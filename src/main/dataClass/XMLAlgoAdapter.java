package dataClass;

import encryption.IEncryptionAlgorithm;
import encryption.charAlgo.ShiftMultiplyEncryption;
import encryption.charAlgo.ShiftUpEncryption;
import encryption.charAlgo.XorEncryption;
import keys.NormalKey;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import static dataClass.AdapterUtils.stringToEncrypt;

public class XMLAlgoAdapter extends XmlAdapter<String, IEncryptionAlgorithm<NormalKey>> {
    @Override
    public IEncryptionAlgorithm<NormalKey> unmarshal(String algoName) throws IllegalArgumentException {
        return stringToEncrypt(algoName);
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
