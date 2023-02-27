package encryption.charAlgo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EncryptionAlgorithmComparatorTest {

    @Test
    void compare() {
        ArrayList<CharEncryptionAlgorithmAbstract> algorithms = new ArrayList<>();
        algorithms.add(new ShiftMultiplyEncryption());
        algorithms.add(new XorEncryption());
        algorithms.add(new ShiftUpEncryption());

        algorithms.sort(new EncryptionAlgorithmByKeyStrengthComparator());

        assertEquals(algorithms.get(0).getEncryptionMethod(), "ShiftUp");
        assertEquals(algorithms.get(1).getEncryptionMethod(), "Xor");
        assertEquals(algorithms.get(2).getEncryptionMethod(), "ShiftMultiply");
    }
}