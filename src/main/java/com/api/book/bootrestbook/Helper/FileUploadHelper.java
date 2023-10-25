package com.api.book.bootrestbook.Helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {

    //public final String UPLOAD_DIR="C:\\Users\\sarthak.mehta\\OneDrive - Accenture\\Desktop\\SpringBootProjects\\bootrestbook\\src\\main\\resources\\static\\Image";
    public final String UPLOAD_DIR=new ClassPathResource("static/Image").getFile().getAbsolutePath();

    public FileUploadHelper() throws IOException{

    }


    public boolean uploadfile(MultipartFile multipartFile){
        boolean f=false;
        try{
            Files.copy(multipartFile.getInputStream(),Paths.get(UPLOAD_DIR+File.separator+multipartFile.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
            f=true;
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return f;
    }

}
