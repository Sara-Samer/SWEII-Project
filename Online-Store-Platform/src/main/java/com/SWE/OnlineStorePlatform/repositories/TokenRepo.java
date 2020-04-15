package com.SWE.OnlineStorePlatform.repositories;

import com.SWE.OnlineStorePlatform.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepo extends JpaRepository<Token, Long> {
    public Token findByToken(String token);

    public Token deleteByToken(String token);
}
