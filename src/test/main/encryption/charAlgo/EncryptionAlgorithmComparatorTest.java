package encryption.charAlgo;

import keys.NormalKey;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EncryptionAlgorithmComparatorTest {

    @Test
    void compare() {
        ArrayList<CharEncryptionAlgorithmAbstract<NormalKey>> algorithms = new ArrayList<>();
        algorithms.add(new ShiftMultiplyEncryption<NormalKey>());
        algorithms.add(new XorEncryption<NormalKey>());
        algorithms.add(new ShiftUpEncryption<NormalKey>());

        algorithms.sort(new EncryptionAlgorithmByKeyStrengthComparator());

        assertEquals(algorithms.get(0).getEncryptionMethod(), "ShiftUp");
        assertEquals(algorithms.get(1).getEncryptionMethod(), "Xor");
        assertEquals(algorithms.get(2).getEncryptionMethod(), "ShiftMultiply");
    }
}