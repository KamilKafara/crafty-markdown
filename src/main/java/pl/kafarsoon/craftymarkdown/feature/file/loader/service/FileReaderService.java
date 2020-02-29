package pl.kafarsoon.craftymarkdown.feature.file.loader.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FileReaderService {

    public List<String> fileReader(MultipartFile file) throws IOException {
        List<String> result = new ArrayList<>();
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            String completeData = new String(bytes);
            String[] rows = completeData.split("#");
            String[] columns = rows[0].split(",");
            Collections.addAll(result, columns);
        }
        return result;
    }
}
