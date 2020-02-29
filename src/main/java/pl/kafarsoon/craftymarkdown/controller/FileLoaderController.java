package pl.kafarsoon.craftymarkdown.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pl.kafarsoon.craftymarkdown.feature.file.loader.service.FileReaderService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("file")
public class FileLoaderController {

    private final FileReaderService fileReaderService;

    public FileLoaderController(FileReaderService fileReaderService) {
        this.fileReaderService = fileReaderService;
    }

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public List<String> fileReader(MultipartFile file) throws IOException {
        return fileReaderService.fileReader(file);
    }
}
