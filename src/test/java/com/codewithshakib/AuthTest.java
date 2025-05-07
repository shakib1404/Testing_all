package com.codewithshakib;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AuthTest {

    private UserRepo userRepo;
    private Hash hash;
    private Auth auth;

    private String email = "test@example.com";
    private String rawPass = "password";
    private String hashedPass = "password";
    private User user;

    @Before
    public void setUp() {
        userRepo = mock(UserRepo.class);
        hash = mock(Hash.class);
        auth = new Auth(userRepo, hash);

        user = new User(email, hashedPass);

        // Default valid case
        when(userRepo.findByEmail(email)).thenReturn(user);
        when(hash.checkPassword(rawPass, hashedPass)).thenReturn(true);

        // Wrong password
        when(hash.checkPassword("wrongpass", hashedPass)).thenReturn(false);

        // Not found case
        when(userRepo.findByEmail("notfound@example.com")).thenReturn(null);

        // Invalid email format triggers exception
        when(userRepo.findByEmail("invalid_email")).thenThrow(new IllegalArgumentException("Invalid email"));
    }

    @Test
    public void testLoginSuccess() {
        assertTrue(auth.login(email, rawPass));
    }

    @Test
    public void testLoginWrongPassword() {
        assertFalse(auth.login(email, "wrongpass"));
    }

    @Test
    public void testLoginUserNotFound() {
        assertFalse(auth.login("notfound@example.com", "anyPass"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLoginInvalidEmail() {
        auth.login("invalid_email", "anyPass");
    }
}
