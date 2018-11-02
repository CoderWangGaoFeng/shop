package com.wgf.shop.service.imp;

import com.wgf.shop.modules.AddressModule;
import com.wgf.shop.modules.ResponseObject;
import com.wgf.shop.modules.WxOpenModule;
import com.wgf.shop.repository.AddressRepository;
import com.wgf.shop.repository.WxOpenRepository;
import com.wgf.shop.service.WxOpenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 微信开放信息逻辑层
 */
@Service
@AllArgsConstructor
public class WxOpenServiceImp implements WxOpenService {

    private final WxOpenRepository wxOpenRepository;
    private final AddressRepository addressRepository;

    /**
     * 用户登录小程序时初始化地址等相关信息
     * @param openId
     * @param sessionId
     * @return
     */
    @Override
    public ResponseObject insert(String openId, String sessionId) {
        try{
            WxOpenModule entity = this.wxOpenRepository.findByOpenId(openId);
            if(entity != null){
                this.wxOpenRepository.save(entity.setSeesionId(sessionId));
            }else{
                this.wxOpenRepository.save(new WxOpenModule().setOpenId(openId).setSeesionId(sessionId));
            }
            List<AddressModule> list = this.addressRepository.findByOpenId(openId);
            return new ResponseObject().success("SUCCESS",list);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseObject().success("FAIL",null);
        }
    }
}
