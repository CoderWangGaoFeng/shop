package com.wgf.shop.repository;

import com.wgf.shop.modules.TokenModule;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

/**
 * token持久层
 */
public interface TokenRepository  extends CrudRepository<TokenModule , Serializable>{

    TokenModule findByAccountId(String accountId);
}
