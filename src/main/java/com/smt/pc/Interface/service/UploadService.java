package com.smt.pc.Interface.service;

import com.smt.pc.Interface.utils.OSSClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * uploadService
 *
 * @author LIJIKAI
 * @date 18/4/8
 */
@Service
public class UploadService {

    @Autowired
    private OSSClientUtil ossClient;

    public String updateHead(MultipartFile file) throws IOException {
        if (file == null || file.getSize() <= 0) {

            return null;
        }
        String name = ossClient.uploadImg2Oss(file);
        return ossClient.getImgUrl(name);
    }
}
