package com.cefetqps.spring.services;

import java.util.Base64;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class UserSecretServices {
    
    public String encodeUserSecret(String userSecret){
        return Base64.getEncoder().encodeToString(userSecret.getBytes());
    }

    public String decodeUserSecret(String encodedUserSecret){
        byte[] decodedBytes = Base64.getDecoder().decode(encodedUserSecret);
        return new String(decodedBytes);
    }
}
