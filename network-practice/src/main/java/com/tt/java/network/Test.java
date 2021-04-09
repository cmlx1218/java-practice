package com.tt.java.network;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author Administrator
 * @Date -> 2021/4/8 14:59
 * @Desc ->
 **/
public class Test {

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("AimyFitness");
        boolean matches = passwordEncoder.matches("AimyFitness", "$2a$10$VjKnRc7kAAWRSAtYEcNaIO7C5LYvmddgmxd3R2u6mE0j90DxVN0DS");
        System.out.println(matches);
        System.out.println(encode);

    }
}
