package com.wgf.shop.repository;

import com.wgf.shop.modules.WxOpenModule;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

/**
 * 微信开放信息持久层
 */
public interface WxOpenRepository extends CrudRepository<WxOpenModule,Serializable>{

    WxOpenModule findByOpenId(String openId);

}
