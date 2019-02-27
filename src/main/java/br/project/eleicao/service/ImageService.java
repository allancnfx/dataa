/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.project.eleicao.service;

import br.project.eleicao.dao.CandidatoDAO;
import br.project.eleicao.domain.Candidato;
import br.project.eleicao.domain.Image;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import br.project.eleicao.generico.ImageRepository;

/**
 *
 * @author Allan
 */
@Service
public class ImageService {

    private static final String UPLOAD_ROOT = "src\\main\\resources\\static\\css\\images";
    private final ImageRepository repository;
    private final ResourceLoader resourceLoader;

    @Autowired
    public ImageService(ImageRepository repository, ResourceLoader resourceLoader) {
        this.repository = repository;
        this.resourceLoader = resourceLoader;
    }

    public Page<Image> findPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Resource findOneImage(String filename) {
        return resourceLoader.getResource("file:" + UPLOAD_ROOT + "/" + filename);
    }

    public void createImage(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            Files.copy(file.getInputStream(), Paths.get(UPLOAD_ROOT, file.getOriginalFilename()));
            repository.save(new Image(file.getOriginalFilename()));
        }
    }
    
    
    public void deleteImage(String filename) throws IOException {
        final Image byName = repository.findByName(filename);
        repository.delete(byName);
        Files.deleteIfExists(Paths.get(UPLOAD_ROOT, filename));
    }

    @Bean
    CommandLineRunner setUp() throws IOException {
        return (args) -> {
            FileSystemUtils.deleteRecursively(new File(UPLOAD_ROOT));
            Files.createDirectory(Paths.get(UPLOAD_ROOT));
        };

    }

}
