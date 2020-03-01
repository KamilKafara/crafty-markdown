package pl.kafarsoon.craftymarkdown.feature.file.loader.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.kafarsoon.craftymarkdown.feature.file.loader.dto.FileDTO;

import java.util.Objects;

@Service
@Log4j2
public class FileTransformerService {
    public FileDTO convertToDTO(MultipartFile file) {
        Resource fileResource = file.getResource();
        String fileName = fileResource.getFilename();
        FileDTO fileDTO = new FileDTO();
        fileDTO.setFilename(fileName);
        fileDTO.setSize(file.getSize());
        fileDTO.setContextType(file.getContentType());

        String[] splitFilename = Objects.requireNonNull(fileName).split("\\.");
        if (splitFilename.length == 2) {
            fileDTO.setExtension(splitFilename[1].toUpperCase());
        }
        return fileDTO;
    }
}

