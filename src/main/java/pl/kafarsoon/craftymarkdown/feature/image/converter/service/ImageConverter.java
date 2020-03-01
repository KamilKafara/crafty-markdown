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
        tesseract.setDatapath(trainedDataPath);
        String textFromImage = tesseract.doOCR(new File(imageDTO.getUrl()));
        List<String> result = new ArrayList<>();
        String[] splitText = textFromImage.split("\n");
        for (int i = 0; i < splitText.length; i++) {
            result.add(splitText[i]);
        }
        return result;
    }
}
