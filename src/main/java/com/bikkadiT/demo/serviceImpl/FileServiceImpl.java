package com.bikkadiT.demo.serviceImpl;

import com.bikkadiT.demo.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uplodImage(String path, MultipartFile file) throws IOException {
        return null;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        return null;
    }
}
