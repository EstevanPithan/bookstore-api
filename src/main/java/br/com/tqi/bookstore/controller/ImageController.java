package br.com.tqi.bookstore.controller;

import br.com.tqi.bookstore.storage.Disk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/imagem")
public class ImageController {

    @Autowired
    private Disk disk;

    @PostMapping
    public void upload(@RequestParam MultipartFile image){
        disk.saveImage(image);
    }
}
