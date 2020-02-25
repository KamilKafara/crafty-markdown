package pl.kafarsoon.craftymarkdown.feature.file.loader.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileReaderService {

    public String fileReader(@RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            String completeData = new String(bytes);
            String[] rows = completeData.split("#");
            String[] columns = rows[0].split(",");
            for (int i = 0; i < columns.length; i++) {
                System.out.println(columns[0]);
            }
        }
        return null;
    }
}