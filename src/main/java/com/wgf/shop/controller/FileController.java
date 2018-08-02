package com.wgf.shop.controller;

import com.wgf.shop.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("/file")
public class FileController {

    private FileService fileService;

    /**
     * 上传文件
     * @param file
     */
    @RequestMapping(method = RequestMethod.POST)
    public void uploadFile(@RequestParam("file") MultipartFile file){
        this.fileService.uploadFile(file);
    }
}
