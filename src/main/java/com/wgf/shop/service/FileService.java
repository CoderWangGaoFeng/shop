package com.wgf.shop.service;

import com.wgf.shop.modules.ResponseObject;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    public ResponseObject uploadFile(MultipartFile file);
}
