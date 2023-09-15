package com.bikkadiT.demo.dtos;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;


@Service
public class FileService {

    public static String uploadFile(MultipartFile image, String imagePath) {
        return imagePath;
    }


    public InputStream getResource(String imagePath) {
        return  InputStream.nullInputStream();
    }
}


