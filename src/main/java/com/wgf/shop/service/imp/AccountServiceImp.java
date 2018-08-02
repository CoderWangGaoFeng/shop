package com.wgf.shop.service.imp;

import com.wgf.shop.modules.AccountInfoModule;
import com.wgf.shop.modules.AccountModule;
import com.wgf.shop.modules.ResponseObject;
import com.wgf.shop.modules.TokenModule;
import com.wgf.shop.modules.enmu.AccountStatus;
import com.wgf.shop.modules.enmu.RequestStatus;
import com.wgf.shop.repository.AccountInfoRepository;
import com.wgf.shop.repository.AccountRepository;
import com.wgf.shop.repository.TokenRepository;
import com.wgf.shop.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

/**
 * 账户信息逻辑层
 */
@Service
@AllArgsConstructor
public class AccountServiceImp implements AccountService{

    private final AccountRepository accountRepository;

    private final TokenRepository tokenRepository;

    private final AccountInfoRepository accountInfoRepository;

    /**
     * 登录
     * @param userName
     * @param password
     * @return
     */
    public ResponseObject signIn(String userName,String password){
        //Optional<AccountModule>.ofNullable(this.accountRepository.findByUaseName(userName)).
        AccountModule account = this.accountRepository.findByUserName(userName);
        if(account == null){
            return new ResponseObject().setStatus(RequestStatus.FAIL).setMsg("帐号不存在");
        }else{
            if(account.getPassword() .equals(password) && account.getStatus()== AccountStatus.ACCOUNT_NORMAL){
                /*
                帐号存在：
                    1.生成token
                    2.检查token表，存在则修改，不存在则新增
                 */
                String token = UUID.randomUUID().toString();
                TokenModule entity = Optional.ofNullable(this.tokenRepository.findByAccountId(account.getId()))
                        .map((TokenModule tokenEntity) -> {
                        return tokenEntity.setToken(token)
                                .setCreateTime(new Timestamp(System.currentTimeMillis()))
                                .setOverdueTime(new Timestamp(System.currentTimeMillis()+24*60*60*1000));
                        }).orElse(
                                new TokenModule().setToken(token)
                                    .setCreateTime(new Timestamp(System.currentTimeMillis()))
                                    .setOverdueTime(new Timestamp(System.currentTimeMillis()+24*60*60*1000)));
                this.tokenRepository.save(entity);
                AccountInfoModule accountInforEntity = Optional.ofNullable(this.accountInfoRepository.findByAccountId(account.getId()))
                        .map(infoentity -> {
                            infoentity.setToken(token);
                            return infoentity;
                        }).orElse(new AccountInfoModule().setToken(token));
//                AccountInfoModule accountInforEntity = this.accountInfoRepository.findByAccountId(account.getId());
//                accountInforEntity.setToken(token);
                return new ResponseObject().success("登录成功",accountInforEntity);
            }else{
               if(!account.getPassword().equals(password)){
                   return new ResponseObject().fail("帐号或密码错误",null);
               }else{
                   return new ResponseObject().fail("帐号冻结",null);
               }
            }
        }
    }
}
