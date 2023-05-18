package com.c7.corrida.crypto;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

import java.util.Objects;

public class PasswordEncoder {
    static final TextEncryptor pass = Encryptors.text("91101595", KeyGenerators.string().generateKey());
    public static boolean matches(String rawPassword, String encodedPassword){
        return pass.decrypt(encodedPassword).equals(rawPassword);
    }

    public static String encrypt(String password){
        return pass.encrypt(password);
    }
}
