package com.wgf.shop.util;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SendHttpUtil {

    /**
     * 发送post请求
     * @param url
     * @param param
     * @return
     */
    public static String sendPost(String url ,String param ){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> formEntity = new HttpEntity<String>(param, headers);
        String result = restTemplate.postForObject(url, formEntity, String.class);
        return result;
    }
}
