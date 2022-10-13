package personal_finance.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserTest {

    @Test
    public void testSetUsername() {
        assertThrows(IllegalArgumentException.class, () -> new User("abc", "password"));
        assertThrows(IllegalArgumentException.class, () -> new User("abcdabcdabcdabcdabcda", "password"));
        assertThrows(IllegalArgumentException.class, () -> new User("abcd!", "password"));
    
        // assertEquals("allowedUsername", new User("allowedUsername", "password", "password").getUsername());
        // assertNotEquals("differentUsername", new User("NewAllowedUsername", "password", "password").getUsername());
    }

    // @Test
    // public void testSetPassword() {
    //     // assertThrows(IllegalArgumentException.class, () -> new User("abcd", "passwordA"));
    // }
    
}
