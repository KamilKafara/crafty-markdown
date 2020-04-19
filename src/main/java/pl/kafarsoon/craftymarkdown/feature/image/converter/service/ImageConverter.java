package pl.kafarsoon.craftymarkdown.feature.image.converter.service;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.kafarsoon.craftymarkdown.feature.image.converter.dto.ImageDTO;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageConverter {
    @Value("${trained.data.path}")
    private String trainedDataPath;

    public List<String> getImageToText(ImageDTO imageDTO) throws TesseractException {
        ITesseract instance = new Tesseract();
        instance.setDatapath(trainedDataPath);
        Tesseract tesseract = new Tesseract();
        String whiteList = "SUPER";
        tesseract.setTessVariable("VAR_CHAR_WHITELIST", whiteList);

        tesseract.setDatapath(trainedDataPath);
        String context = tesseract.doOCR(new File(imageDTO.getUrl()));
        String[] array = context.split("\n");
        List<String> result = new ArrayList<>();
        for (String item : array) {
            result.add(item);
        }
        return result;
    }
}
