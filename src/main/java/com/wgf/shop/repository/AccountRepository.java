package com.wgf.shop.repository;

import com.wgf.shop.modules.AccountModule;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

/**
 * 帐号信息持久层
 */
public interface AccountRepository extends CrudRepository< AccountModule , Serializable>{

    AccountModule findByUserName(String userName);
}
