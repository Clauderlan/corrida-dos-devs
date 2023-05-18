package com.c7.corrida.config;


import com.c7.corrida.crypto.PasswordEncoder;
import com.c7.corrida.entities.User;
import com.c7.corrida.repositories.UserRepository;
import com.c7.corrida.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

import java.io.Serializable;

public class Test implements Serializable {

    @Autowired
    private UserService userService;

}
