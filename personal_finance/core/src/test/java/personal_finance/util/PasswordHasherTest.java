package personal_finance.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;

public class PasswordHasherTest {
    
    @Test
    public void testHash() throws NoSuchAlgorithmException {
        String input = "test";
        String correctOutput = "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08";

        assertEquals(correctOutput, PasswordHasher.hash(input));
    }

    @Test
    public void testToHexString() {
        byte[] input = "test".getBytes();
        String correctOutput = "00000000000000000000000074657374";
        
        assertEquals(correctOutput, PasswordHasher.toHexString(input));
    }

}
