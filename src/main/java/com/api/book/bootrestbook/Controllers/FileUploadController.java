package com.api.book.bootrestbook.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.api.book.bootrestbook.Helper.FileUploadHelper;

@RestController
public class FileUploadController {
    
    @Autowired
    private FileUploadHelper fileUploadHelper;

    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadfile(@RequestParam("file") MultipartFile file){
        try {
            System.out.println(file.getOriginalFilename());
            System.out.println(file.getSize());
            //System.out.println(file.getContentType());
            System.out.println(file.getName());


            //validation
            if(file.isEmpty()){
                return ResponseEntity.internalServerError().body("Request must contain file");
            }

            //
            if(!file.getContentType().equals("image/jpeg")){
                return ResponseEntity.internalServerError().body("only jpeg content type are allowed");
            }

            //file upload here
            boolean f=fileUploadHelper.uploadfile(file);
            if(f){
                ServletUriComponentsBuilder.fromCurrentContextPath().path("/Image/");
                //return ResponseEntity.ok("File Uploaded Successfully.");
                return ResponseEntity.ok(UriComponentsBuilder.fromUriString(file.getOriginalFilename()).toUriString());
            }

        } catch (Exception e) {
                e.printStackTrace();
            }

            return ResponseEntity.internalServerError().build();
    }
}
