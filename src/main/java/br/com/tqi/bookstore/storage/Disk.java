package br.com.tqi.bookstore.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class Disk {

    @Value("${disco.raiz}")
    private String root;

    @Value("${disco.diretorio-fotos}")
    private String imageDirectory;

    public void saveImage(MultipartFile image) {
        this.save(this.imageDirectory, image);
    }

    public void save(String directory, MultipartFile file) {
        Path pathDirectory = Paths.get(this.root, directory);
        Path pathFile = pathDirectory.resolve(file.getOriginalFilename());

        try {
            Files.createDirectories(pathDirectory);
            file.transferTo(pathFile.toFile());
        } catch (IOException e) {
            throw new RuntimeException("Problemas na tentativa de salvar arquivo.", e);
        }
    }

}
