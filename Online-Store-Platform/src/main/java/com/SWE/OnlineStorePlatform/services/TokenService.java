package com.SWE.OnlineStorePlatform.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.SWE.OnlineStorePlatform.models.Token;
import com.SWE.OnlineStorePlatform.repositories.TokenRepo;

@Service
@Transactional
public class TokenService {
    @Autowired
    private TokenRepo repo;

    public List<Token> listAll() {
        return repo.findAll();
    }

    public void addToken(Token token) {
        repo.save(token);
    }

    public Token get(String token) {
        return repo.findByToken(token);
    }

    public void delete(String token) {
        repo.deleteByToken(token);
    }
}
