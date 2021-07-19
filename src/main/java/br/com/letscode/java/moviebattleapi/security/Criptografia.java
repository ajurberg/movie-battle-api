package br.com.letscode.java.moviebattleapi.security;


import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

@Service
public class Criptografia {

    public String encode(String plainPassword) {
        return DigestUtils.sha1Hex(plainPassword);

    }
}
