package com.codewithshakib;

public class Auth {
    private UserRepo userRepo;
    private Hash hash;

    public Auth(UserRepo userRepo, Hash hash) {
        this.userRepo = userRepo;
        this.hash = hash;
    }

    public boolean login(String email, String password) {
        User user = userRepo.findByEmail(email);
        if (user == null) return false;
        return hash.checkPassword(password, user.getHashedPassword());
    }
}
