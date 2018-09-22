package com.wgf.shop.service.imp;

import com.wgf.shop.modules.GoodsModule;
import com.wgf.shop.modules.GoodsTypeModule;
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

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;
import java.math.BigDecimal;
import java.util.*;

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
        return Optional.ofNullable(
                this.goodsRepository.findByAccountId(accountId))
                .map(entitylist -> {
                    List<GoodsModule> reList = new ArrayList<>();
                    for(Object obj : entitylist){
                        Object[] objArry = (Object[])obj;
                        GoodsModule entity = new GoodsModule();
                        entity.setId(Long.parseLong(objArry[0]+""));
                        entity.setName(objArry[1]+"");
                        entity.setPrice((BigDecimal)objArry[2]);
                        entity.setStatus((Boolean)objArry[3]);
                        entity.setTypeId(Long.valueOf(objArry[4]+""));
                        entity.setSaleNumber((String)objArry[5]);
                        entity.setTypeName((String)objArry[6]);
                        entity.setImg((String)objArry[7]);
                        entity.setAccountId((String)objArry[8]);
                        reList.add(entity);
                    }
                    List<GoodsTypeModule> type = this.goodsTypeRepository.findByAccountId(accountId);
                    List<Map<String,Object>> typeList = new ArrayList<>();
                    for(GoodsTypeModule typeEntity : type){
                        Map<String ,Object> map = new HashMap<>();
                        map.put(typeEntity.getId()+"",typeEntity.getName());
                        typeList.add(map);
                    }
                    Map<String,Object> returnMap = new HashMap<>();
                    returnMap.put("goods",reList);
                    returnMap.put("goodsType",typeList);
                    return new ResponseObject().success("查询成功",returnMap);
                }).orElse(new ResponseObject().success("查询成功",null));
//        return returnList;
    }

    @Override
    public ResponseObject updateGoods(HttpServletRequest request) {
        String oper = request.getParameter("oper");
        String id = request.getParameter("id");
        if("edit".equals(oper)){
            GoodsModule goods = this.goodsRepository.findById(Long.valueOf(id)).get();
            goods.setName(request.getParameter("name"));
            goods.setTypeId(Long.valueOf(request.getParameter("typeId")));
            goods.setStatus(true);
            goods.setPrice( new  BigDecimal(request.getParameter("price")));
            goods.setSaleNumber("0");
            this.goodsRepository.save(goods);
        }else{
            this.goodsRepository.deleteById(Long.valueOf(id));
        }
        return new ResponseObject().success("保存成功",null);
    }
}
