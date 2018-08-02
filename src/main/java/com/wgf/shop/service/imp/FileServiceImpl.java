package com.wgf.shop.service.imp;

import com.wgf.shop.modules.ResponseObject;
import com.wgf.shop.service.FileService;
import com.wgf.shop.util.FileUploadUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传图片逻辑处理层
 * @author  wanggaofeng
 */
@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService{

    private FileUploadUtil fileUploadUtil;

    /**
     * 图片上传
     * @param file
     * @return
     */
    @Override
    public ResponseObject uploadFile(MultipartFile file) {
        try{
            this.fileUploadUtil.fileUpload(file);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
