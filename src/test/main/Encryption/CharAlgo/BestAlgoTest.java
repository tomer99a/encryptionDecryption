package Encryption.CharAlgo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BestAlgoTest {

    @Test
    void compare() {
        assertEquals(0, new BestAlgo().compare(new ShiftMultiplyEncryption(), new ShiftUpEncryption()));
    }
}