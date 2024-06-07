package com.example.passwordgenerator;

import java.util.Random;

public class PasswordGenerator {

    private boolean lowercase, uppercase, digits, special;
    private int passLength;

    public PasswordGenerator() {
    }

    public String generatePassword() {
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder pass = new StringBuilder();
        if(lowercase) {
            stringBuilder.append("abcdefghijklmnoprstuwxyz");
        }
        if(uppercase) {
            stringBuilder.append("ABCDEFGHIJKLMNOPRSTUWXYZ");
        }
        if(digits) {
            stringBuilder.append("1234567890");
        }
        if(special) {
            stringBuilder.append("!@~#$%^&*_+=-");
        }
        String pool = stringBuilder.toString();


        for (int i = 0; i < passLength; i++) {
            Random rdm = new Random();
            pass.append(pool.charAt(rdm.nextInt(0, pool.length())));
        }
        return pass.toString();
    }

    public void setDigits(boolean digits) {
        this.digits = digits;
    }

    public void setLowercase(boolean lowercase) {
        this.lowercase = lowercase;
    }

    public void setUppercase(boolean uppercase) {
        this.uppercase = uppercase;
    }

    public void setSpecial(boolean special) {
        this.special = special;
    }

    public void setPassLength(int passLength) {
        this.passLength = passLength;
    }
}
