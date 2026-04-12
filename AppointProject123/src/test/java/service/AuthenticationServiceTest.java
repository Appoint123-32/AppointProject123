package service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AuthenticationServiceTest {

    @Test
    void testLoginSuccess() {
        AuthenticationService auth = new AuthenticationService();

        assertTrue(auth.login("admin", "1234"));
        assertTrue(auth.isLoggedIn());
    }

    @Test
    void testLoginFail() {
        AuthenticationService auth = new AuthenticationService();

        assertFalse(auth.login("admin", "wrong"));
        assertFalse(auth.isLoggedIn());
    }

    @Test
    void testLogout() {
        AuthenticationService auth = new AuthenticationService();

        auth.login("admin", "1234");
        auth.logout();

        assertFalse(auth.isLoggedIn());
        
    }
    @Test
    void testLogout_ResetsState() {
        AuthenticationService auth = new AuthenticationService();
        auth.login("admin", "1234");
        assertTrue(auth.isLoggedIn());
        
        auth.logout();
        assertFalse(auth.isLoggedIn());
    }

    @Test
    void testLogin_WithInvalidUser_ReturnsFalse() {
        AuthenticationService auth = new AuthenticationService();
        assertFalse(auth.login("nonexistent", "0000"));
    }
}

