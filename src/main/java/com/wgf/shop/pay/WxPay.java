package com.wgf.shop.pay;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wgf.shop.util.MD5Util;
import com.wgf.shop.util.SendHttpUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 模拟发送http请求
 */
@Component
public class WxPay {

    final private String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";//统一下单地址

    /**
     * 发送post请求
     */
    public void sendPost(){
        //设置消息头
        ObjectMapper objMap = new ObjectMapper();
        Map<String ,String> map = new HashMap<>();
        map.put("appid","wxc6b2f5ff6ca680bc");//小程序id
        map.put("mch_id","1230000109");//微信支付商户号
        map.put("nonce_str", UUID.randomUUID().toString().replaceAll("-",""));//32位随机字符串
        map.put("body","a-b");//商品描述
        map.put("out_trade_no","1");//商户订单号
        map.put("total_fee","100");//订单总金额
        map.put("spbill_create_ip","1");//用户id
        map.put("notify_url","https://192.168.1.1");// 异步通知地址
        map.put("trade_type","JSAPI");//交易类型
        map.put("openid","oYPXG5aDa1VF5sWyH9u6KLDIYEzU");//用户openId
        String sign = this.getSign(map,"1111");
        map.put("sign",sign);
        String param = this.getXml(map);
//        String param = JSONObject.fromObject(map).toString();
        String result = SendHttpUtil.sendPost(url,param);
        try {
            result = new String(result.getBytes("ISO-8859-1"), "UTF-8");//防止中文乱码
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("返回结果"+result);
    }
    /**
     * 获取签名
     * @param map
     * @return
     */
    public String getSign(Map<String ,String> map,String key){
        ArrayList<String> list = new ArrayList<>(map.keySet());
        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        String sign = "";
        for(String str : list){
            sb.append(str).append("=").append(map.get(str)).append("&");
        }
        if(sb.length() > 0){
           //sb.deleteCharAt(sb.length()-1);//删除最后一个字符
            sb.append("key=").append(key);
           sign = sb.toString();
           sign = MD5Util.MD5Encode(sign,"UTF-8").toUpperCase();
           System.out.println("密签为:"+sign);
        }else{
            sign = "";
        }
        return sign;
    }

    /**
     * 生成xml文件
     * @param map
     * @return
     */
    public String getXml(Map<String ,String> map){
        Set<String> key = map.keySet();
        StringBuilder xml = new StringBuilder();
        xml.append("<xml>");
        for(String str : key){
            xml.append("<").append(str).append(">").append(map.get(str)).append("</").append(str).append(">");
        }
        xml.append("</xml>");
        return xml.toString();
    }
}
