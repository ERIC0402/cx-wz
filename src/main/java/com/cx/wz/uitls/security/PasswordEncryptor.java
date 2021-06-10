package com.cx.wz.uitls.security;

import org.jasypt.util.text.BasicTextEncryptor;
import org.jasypt.util.text.TextEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PasswordEncryptor {

    private TextEncryptor textEncryptor;
    protected static Logger logger = LoggerFactory.getLogger(PasswordEncryptor.class);

    @PostConstruct
    private void init() {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword("eyJhbGciOiJIUzUxMiJ9");
        this.textEncryptor = textEncryptor;
    }

    public static void main(String[] args) {
        PasswordEncryptor passwordEncryptor = new PasswordEncryptor();
        passwordEncryptor.init();
        System.out.println(passwordEncryptor.encrypt("123456"));
    }

    public String encrypt(String text) {
        return textEncryptor.encrypt(text);
    }

    public String decrypt(String text) {
        return textEncryptor.decrypt(text);
    }

    public boolean matches(String text, String encryptedText) {
        return text.equals(decrypt(encryptedText));
    }

}

