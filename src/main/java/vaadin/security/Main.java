package vaadin.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Main {

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        String encodedPass = bCryptPasswordEncoder.encode("password");
        System.out.println(encodedPass);
        System.out.println(bCryptPasswordEncoder.matches("password", encodedPass));

        String encodedPass2 = bCryptPasswordEncoder.encode("password");
        System.out.println(encodedPass2);
        System.out.println(bCryptPasswordEncoder.matches("password", encodedPass2));
    }
}
