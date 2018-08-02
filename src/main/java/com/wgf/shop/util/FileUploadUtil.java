package com.wgf.shop.util;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * 文件上传工具类
 * 将文件上传至linux的特定文件夹下
 * @Author wanggaofeng
 * @Date 2018/8/2 16 23
 */
@Component
public class FileUploadUtil {

    @Value("${file.sftp.http}")
    private String http;
    @Value("${file.sftp.path}")
    private String path;
    @Value("${file.sftp.name}")
    private String userName;
    @Value("${file.sftp.password}")
    private String password;

    public String fileUpload(MultipartFile file) throws Exception{
        FTPClient ftp = new FTPClient();
        String fileName = UUID.randomUUID().toString().replaceAll("-","");
        try{
            InputStream input = file.getInputStream();
            ftp.connect(http,22);
            ftp.setControlEncoding("UTF-8");
            ftp.login(userName,password);
            ftp.changeWorkingDirectory(path);
            ftp.storeFile(fileName,input);
            input.close();
            ftp.logout();
            return fileName;
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }finally {
            if(ftp.isConnected()){
                try{
                    ftp.disconnect();
                }catch(Exception e){
                }
            }
        }
    }
}
