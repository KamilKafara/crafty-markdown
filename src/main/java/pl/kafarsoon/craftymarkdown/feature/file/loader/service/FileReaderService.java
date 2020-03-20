package pl.kafarsoon.craftymarkdown.feature.file.loader.service;

import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.kafarsoon.craftymarkdown.feature.file.loader.dto.FileDTO;
import pl.kafarsoon.craftymarkdown.feature.image.converter.dto.ImageDTO;
import pl.kafarsoon.craftymarkdown.feature.image.converter.service.ImageConverter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class FileReaderService {

    private final FileTransformerService fileTransformerService;
    private final ImageConverter imageConverter;

    public FileReaderService(FileTransformerService fileTransformerService, ImageConverter imageConverter) {
        this.fileTransformerService = fileTransformerService;
        this.imageConverter = imageConverter;
    }

    public FileDTO fileReader(MultipartFile file) throws IOException, TesseractException {
        FileDTO fileDTO = fileTransformerService.convertToDTO(file);
        List<String> context = new ArrayList<>();
        if (!file.isEmpty() && fileDTO.getExtension().equals("txt")) {
            String completeData = new String(file.getBytes());
            String[] rows = completeData.split("#");
            String[] columns = rows[0].split(",");
            Collections.addAll(context, columns);
        } else {
            context = SetContextFromImage(file);
        }
        fileDTO.setContext(context);
        return fileDTO;
    }

    private List<String> SetContextFromImage(MultipartFile file) throws IOException, TesseractException {
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setUrl(convertToFile(file).toPath().toString());
        return Collections.singletonList(imageConverter.getImageToText(imageDTO));
    }

    public File convertToFile(MultipartFile file) throws IOException {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }


}
