package com.wgf.shop.util;

import org.apache.commons.net.ftp.FTP;
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
        String oldFileName = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString().replaceAll("-","")+oldFileName;
        try{
            InputStream input = file.getInputStream();
            ftp.connect(http,21);
            ftp.setControlEncoding("UTF-8");
            ftp.login(userName,password);
            boolean flag = ftp.changeWorkingDirectory(path);
            ftp.setFileType(FTP.BINARY_FILE_TYPE);//设置上传文件格式为二进制，默认格式会导致图片损坏.这句代码的位置不能改变
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
