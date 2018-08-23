package com.wgf.shop.service.imp;

import com.wgf.shop.modules.GoodsModule;
import com.wgf.shop.modules.ResponseObject;
import com.wgf.shop.repository.GoodsRepository;
import com.wgf.shop.repository.GoodsTypeRepository;
import com.wgf.shop.service.GoodsService;
import com.wgf.shop.util.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

/**
 * 商品列表逻辑层
 */
@Service
//@AllArgsConstructor
@Slf4j //lombok提供的日志注解，直接使用log对象就可以
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsTypeRepository goodsTypeRepository;
    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    private FileUploadUtil fileUploadUtil;

    @Value("${file.sftp.http.visit}")
    private String pricturePath;

    /**
     * 根据用户id查询用户的分类和分类商品
     * @param accountId
     * @return
     */
    @Override
    public ResponseObject findGoodsListByTypeId(String accountId ) {
        return Optional.ofNullable(this.goodsRepository.findByAccountIdAndStatus(accountId,true))
            .map((List<GoodsModule> list) -> {
//                List<GoodsTypeModule> typeList = this.goodsTypeRepository.findByAccountId(accountId);
//                List<GoodsVo> goodsVoList = new ArrayList<GoodsVo>();
//                for(GoodsTypeModule entity : typeList){
//                    GoodsVo goodsVo = new GoodsVo();
//                    goodsVo.setType(entity);
//                    List<GoodsModule> goodsList = new ArrayList<>();
//                    for(GoodsModule goods : list){
//                        goodsList.add(goods);
//                    }
//                    goodsVo.setGoods(goodsList);
//                    goodsVoList.add(goodsVo);
//                }
                return new ResponseObject().success("产品列表",list);
            }).orElse(new ResponseObject().success("产品列表",null));
    }

    /**
     * 保存新的产品
     * @Param
     * @Return
     * @Author wanggaofeng
     * @Date 2018/8/10 11:00
     **/
    @Override
    @Transactional
    public ResponseObject saveGoods(MultipartFile file, GoodsModule goods) {
        try{
            String fileName = this.fileUploadUtil.fileUpload(file);
            goods.setImg(pricturePath+fileName);
            this.goodsRepository.save(goods);
            return new ResponseObject().success("保存成功",null);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseObject().fail("网络错误，保存失败",null);
        }
    }

    /**
     * pc端查询商品列表
     * @param accountId
     * @return
     */
    @Override
    public ResponseObject selectGoods(String accountId) {

        return null;
    }
}
