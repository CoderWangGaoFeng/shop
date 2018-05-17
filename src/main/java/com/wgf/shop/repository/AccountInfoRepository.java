package com.wgf.shop.repository;

import com.wgf.shop.modules.AccountInfoModule;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.io.Serializable;

/**
 * 用户信息持久层
 */
@Transactional
public interface AccountInfoRepository extends CrudRepository<AccountInfoModule,Serializable>{

    AccountInfoModule findByAccountId(String accountId);
}
