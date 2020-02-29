package pl.kafarsoon.craftymarkdown.feature.file.loader.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.kafarsoon.craftymarkdown.feature.file.loader.dto.FileDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FileReaderService {

    private final FileTransformerService fileTransformerService;

    public FileReaderService(FileTransformerService fileTransformerService) {
        this.fileTransformerService = fileTransformerService;
    }

    public FileDTO fileReader(MultipartFile file) throws IOException {
        List<String> context = new ArrayList<>();
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            String completeData = new String(bytes);
            String[] rows = completeData.split("#");
            String[] columns = rows[0].split(",");
            Collections.addAll(context, columns);
        }
        FileDTO fileDTO = fileTransformerService.convertToDTO(file);
        fileDTO.setContext(context);
        return fileDTO;
    }


}
