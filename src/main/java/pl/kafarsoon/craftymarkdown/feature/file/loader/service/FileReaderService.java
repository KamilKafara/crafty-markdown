package pl.kafarsoon.craftymarkdown.feature.file.loader.service;

import net.sourceforge.tess4j.TesseractException;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.kafarsoon.craftymarkdown.exception.handler.ErrorCode;
import pl.kafarsoon.craftymarkdown.exception.handler.FieldInfo;
import pl.kafarsoon.craftymarkdown.feature.file.loader.dto.FileDTO;
import pl.kafarsoon.craftymarkdown.feature.file.loader.dto.FileFormat;
import pl.kafarsoon.craftymarkdown.feature.image.converter.dto.ImageDTO;
import pl.kafarsoon.craftymarkdown.feature.image.converter.service.ImageConverter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import static pl.kafarsoon.craftymarkdown.exception.handler.ErrorsUtils.badRequestException;

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

        if (!file.isEmpty() && fileDTO.getExtension().equals(FileFormat.TXT)) {
            byte[] bytes = file.getBytes();
            String completeData = new String(bytes);
            String[] rows = completeData.split("#");
            String[] columns = rows[0].split(",");
            Collections.addAll(context, columns);
        } else if (ifExtensionExist(fileDTO)) {
            ImageDTO imageDTO = new ImageDTO();
            imageDTO.setUrl(convertToFile(file).toPath().toString());
            context = imageConverter.getImageToText(imageDTO);
        } else {
            badRequestException("Not supported file extension. Required : " + Arrays.toString(FileFormat.values()) + ". Found : " + fileDTO.getExtension(), new FieldInfo("file extension", ErrorCode.BAD_REQUEST));
        }
        fileDTO.setContext(context);
        return fileDTO;
    }

    private boolean ifExtensionExist(FileDTO fileDTO) {
        return EnumUtils.isValidEnum(FileFormat.class, fileDTO.getExtension());
    }

    public File convertToFile(MultipartFile file) throws IOException {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
