package com.wgf.shop.service.imp;

import com.wgf.shop.modules.AddressModule;
import com.wgf.shop.modules.ResponseObject;
import com.wgf.shop.repository.AddressRepository;
import com.wgf.shop.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * 地址逻辑层
 */
@Service
@AllArgsConstructor
public class AddressServiceImp implements AddressService {

    private final AddressRepository addressRepository;


    /**
     * 新增地址
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public ResponseObject insert(AddressModule entity) {
        entity.setStatus(true);
        try{
            if(entity.getStatus()){//默认地址
                this.addressRepository.updateAddressStatus(entity.getOpenId());
            }
            this.addressRepository.save(entity);
            List<AddressModule> list = this.addressRepository.findByOpenId(entity.getOpenId());
            return new ResponseObject().success("SUCCESS",list);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseObject().fail("FAIL",null);
        }
    }

    @Override
    public ResponseObject findAll(String accountId, String openId) {
        List<AddressModule> list = this.addressRepository.findByAccountIdAndOpenId(accountId,openId);
        return new ResponseObject().success("查询成功",list);
    }
}
